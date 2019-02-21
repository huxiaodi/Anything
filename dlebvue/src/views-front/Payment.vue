<template>
  <div>
    <el-card v-show="isShow" class="box-card">
      <div class="text item">
        付款方:{{memberTransactionsInspectionRequest.outSubAcctName}}
      </div>
      <div class="text item">
        收款方:{{memberTransactionsInspectionRequest.inSubAcctName}}
      </div>
      <div class="text item">
        付款金额:{{memberTransactionsInspectionRequest.tranAmt}} 元
        <span class="addr">含手续费{{memberTransactionsInspectionRequest.tranFee}}元，对方实收{{memberTransactionsInspectionRequest.tranAmt-memberTransactionsInspectionRequest.tranFee}}元</span>
      </div>
    </el-card>
    <el-button v-show="isShow" @click="transfer" :loading="isShowLoadingIcon">确认转账</el-button>
    <div v-show="false">
      <form target="iFrame" ref="PayForm" :action="payForm.frontUrl"
            METHOD="POST">
        <input type="hidden" v-model="payForm.orderId" name="orderid" ref="orderid">
        <input type="hidden" v-model="payForm.type" name="type">
        <input type="hidden" v-model="payForm.p2PCode" name="P2PCode">
        <input type="hidden" v-model="payForm.p2PType" name="P2PType">
        <input type="hidden" v-model="payForm.thirdCustId" name="thirdCustId">
        <input type="hidden" v-model="payForm.custAccId" name="custAccId">
        <input type="hidden" v-model="payForm.name" name="name">
        <input type="hidden" v-model="payForm.idType" name="idType" ref="idType">
        <input type="hidden" v-model="payForm.idNo" name="idNo">
        <input type="hidden" v-model="payForm.mobile" name="mobile">
        <input type="hidden" v-model="payForm.orig" name="orig">
        <input type="hidden" v-model="payForm.returnurl " name="returnurl">
        <input type="hidden" v-model="payForm.notifyUrl" name="notifyUrl">
        <input type="hidden" v-model="payForm.channelType" name="channelType">
        <input type="submit">
      </form>
    </div>
    <iframe name="iFrame" style="width: 100%;height: 700px" v-show="currentShowIndex != 4">
    </iframe>
    <div v-show="currentShowIndex === 4">
      <el-card class="box-card">
        <div class="text item">
          {{tradeResult}}
        </div>
        <div class="text item">
          {{countDown}}秒后关闭页面
        </div>
      </el-card>
    </div>
  </div>

</template>
<script>
  import * as Message from '../utils/messageUtil'
  import api from '../api/fetch'

  export default {
    name: 'LoginHead',
    data() {
      return {
        isShowLoadingIcon: false,
        payForm: {},
        pollingFunction: null,
        countDown: 10,
        isShow: true,
        businessTrade: {},
        accountSubTrade: {},
        currentShowIndex: 1,
        tradeResult: '',
        memberTransactionsInspectionRequest: {}
      }
    },
    methods: {
      transfer: function () {
        this.isShow = false;
        this.$refs.PayForm.submit();
        this.polling(this.payForm.orderId);
      },
      startCountDown() {
        setInterval(() => {
          if (this.countDown === 0) {
            window.close();
          }
          this.countDown--;
        }, 1000)
      },
      polling(id) {
        if (this.pollingFunction) {
          window.clearInterval(this.pollingFunction);
        }
        this.pollingFunction = setInterval(() => {
          api.get('/trade/accountSubTrade/' + id).then(response => {
            if (response.data.code === 1) {
              let result = response.data.data;
              if (!result) {
                this.tradeResult = '交易验密失败';
              } else if (result.accountSubTradeTranStatus === 1) {
                this.tradeResult = '交易失败'
              } else if (result.accountSubTradeTranStatus === 0) {
                this.tradeResult = '交易成功'
              } else {
                return;
              }
              this.currentShowIndex = 4;
              window.clearInterval(this.pollingFunction);
              this.startCountDown();
            } else {
              Message.MessageError(response.data.msg)
            }
          })
        }, 5000)

      },
      getDate: function () {
        var id = this.$route.query.id;
        if (id === undefined || id == null) {
          id = this.$route.params.id;
        }
        api.get('/api/pay/payment?id=' + id).then(response => {
          if (response.data.code === 1) {
            this.payForm = response.data.data.form;
            this.businessTrade = response.data.data.businessTrade;
            this.accountSubTrade = response.data.data.accountSubTrade;
            this.memberTransactionsInspectionRequest = response.data.data.memberTransactionsInspectionRequest;
          } else {
            Message.MessageError(response.data.msg);
          }
        });
      }
    },
    mounted() {
      this.getDate();
    }
  }
</script>
