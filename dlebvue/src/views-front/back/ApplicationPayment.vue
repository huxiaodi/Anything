<template>
  <div class="page1180">
    <el-row class="page-content" style="padding-left:95px;">
      <el-card v-show="isShow" class="box-card">
        <div class="text item">
          付款方: {{memberTransactionsInspectionRequest.outSubAcctName}}
        </div>
        <div class="text item">
          收款方: {{memberTransactionsInspectionRequest.inSubAcctName}}
        </div>
        <div class="text item">
          收款账户: {{memberTransactionsInspectionRequest.inSubAcctNo}}
        </div>
        <div class="text item">
          付款金额: {{memberTransactionsInspectionRequest.tranAmt}} 元
          <span class="addr">含手续费{{memberTransactionsInspectionRequest.tranFee}}元，对方实收{{memberTransactionsInspectionRequest.tranAmt-memberTransactionsInspectionRequest.tranFee}}元</span>
        </div>
      </el-card>
      <el-button v-show="isShow" type="primary" @click="transfer" style="text-align: center;align: center;margin-top: 8px;" :loading="isShowLoadingIcon">确认转账</el-button>
      <br>
      <el-checkbox v-if="isSendSMSDisplay" v-model="isSendSMS">短信通知对方</el-checkbox>
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
            {{countDown}}秒后跳转到付款详情
          </div>
        </el-card>
      </div>
    </el-row>
  </div>
</template>
<script>
  import * as Message from '../utils/messageUtil'
  import api from '../api/fetch'

  export default {
    name: 'ApplicationPayment',
    data() {
      return {
        id: '',
        isShowLoadingIcon: false,
        payForm: {},
        pollingFunction: null,
        countDown: 10,
        isShow: true,
        isSendSMS: false,
        isSendSMSDisplay: true,
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
        this.isSendSMSDisplay = false;
        this.sendSMS();
        this.$refs.PayForm.submit();
        this.polling(this.payForm.orderId);
      },
      startCountDown() {
        setInterval(() => {
          if (this.countDown === 0) {
            this.$router.push({
              name: '付款详情',
              params: {'application_trade_id': this.$route.params.applicationTradeId}
            })
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
        var id = this.$route.query.businessTradeId;
        if (id === undefined || id == null) {
          id = this.$route.params.businessTradeId;
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
      },
      sendSMS: function () {
        if(this.isSendSMS){
          var id = this.$route.query.businessTradeId;
          if (id === undefined || id == null) {
            id = this.$route.params.businessTradeId;
          }
          api.post('/accountSub/accountSubController/setSendSMS?id=' + id).then(response => {
            console.log(response.data.msg);
          });
        }
      }
    },
    mounted() {
      this.getDate();
    }
  }
</script>
