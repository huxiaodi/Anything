<template>
  <div width="100%" height="1000px;">
    {{errorMsg}}
    <iframe name="myFrame" width="100%" style="height: 500px"></iframe>
    <el-form target="myFrame" ref="PayForm" NAME="PayForm"
             :action="form.action" v-show="false"
             METHOD="POST">
      <el-input type="hidden" name="orig" v-model="form.orig"></el-input>
      <el-input type="hidden" id="sign" name="sign" v-model="form.sign"></el-input>
      <el-input type="hidden" id="returnurl" name="returnurl" v-model="form.returnurl"></el-input>
      <el-input type="hidden" id="NOTIFYURL" name="NOTIFYURL" v-model="form.notifyurl"></el-input>
    </el-form>
  </div>
</template>

<script>
  import api from '../api/fetch'
  import {parseTime} from '../utils/index'
  import * as Message from '../utils/messageUtil'
  import {pageMixin} from '../common/mixin'
  import {exportModel} from '../common/export'

  export default {
    name: 'UpTo',
    data() {
      return {
        tableList: [],
        errorMsg: '',
        form: {
          action: '',
          orig: '',
          sign: '',
          returnurl: '',
          notifyurl: ''
        }
      }
    },
    methods: {
      getData() {
        window.sp = this;
        var money = this.$route.query.amount;
        var orderId = this.$route.query.orderId;
        var businessTradeBusinessType = this.$route.query.businessTradeBusinessType;
        var businessTradeBusinessFrom = this.$route.query.businessTradeBusinessFrom;
        var url = this.$route.query.returnurl;
        var businessTradeObject = this.$route.query.tradeObject;
        var remark = this.$route.query.remark;
        var fee = this.$route.query.fee;
        var accountSubNo = this.$route.query.accountSubNo;
        var businessTradeClearingType = this.$route.query.businessTradeClearingType;
        var businessTradeGoodsName = this.$route.query.businessTradeGoodsName;
        var businessTradeTrasactionNo = this.$route.query.businessTradeTrasactionNo;
        debugger
        var obj = {
          'money': money,
          'orderId': orderId,
          'businessTradeBusinessType': businessTradeBusinessType,
          'businessTradeBusinessFrom': businessTradeBusinessFrom,
          'url': url,
          'businessTradeObject': businessTradeObject,
          'remark': remark,
          'fee': fee,
          'accountSubNo': accountSubNo,
          'businessTradeClearingType': businessTradeClearingType,
          'businessTradeGoodsName': businessTradeGoodsName,
          'businessTradeTrasactionNo': businessTradeTrasactionNo
        }
        api.post('/pay/getPayInfoForBusiness', obj).then(response => { 
          if (response.data.code === 1) {
            this.form = response.data.data;
            this.timeOut = setTimeout(() => {
              this.$refs.PayForm.$el.submit();
            }, 1000);
          } else {
            Message.MessageError(response.data.msg)
          }
        })
      }
    },
    mounted() {
      this.getData();
    }
  }
</script>

<style scoped>

</style>
