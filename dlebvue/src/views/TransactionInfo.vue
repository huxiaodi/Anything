<template>
  <div align="center">
    <el-row class="content-page" style="padding-left:30px;">
      <el-form ref="transactionInfoForm" :model="transactionInfoForm" labelWidth="100px"
               labelPosition="right" style="width: 50%;height: 30%" size="mini">
        <div style="font-size: large;color: #FF9933">交易详情</div>
        <hr style="background-color:#FF9933;height:1px;"/>
        <el-form-item label="交易子账户:">
          <div style="text-align: left">{{transactionInfoForm.accountSubName}}</div>
        </el-form-item>
        <el-form-item label="交易对象:">
          <div style="text-align: left">{{transactionInfoForm.accountSubTradeInAccountSubName}}</div>
        </el-form-item>
        <el-form-item label="交易时间:">
          <div style="text-align: left">{{transactionInfoForm.accountSubTradeTranDate}}</div>
        </el-form-item>
        <el-form-item label="业务单号:">
          <div style="text-align: left">{{transactionInfoForm.businessTradeBusinessNo}}</div>
        </el-form-item>
        <el-form-item label="流水单号:">
          <div style="text-align: left">{{transactionInfoForm.accountSubTradeNo}}</div>
        </el-form-item>
        <el-form-item label="当前状态:">
          <template>
            <div style="text-align: left" v-if="transactionInfoForm.accountSubTradeTranStatus==0">成功</div>
            <div style="text-align: left" v-if="transactionInfoForm.accountSubTradeTranStatus==1">失败</div>
            <div style="text-align: left" v-if="transactionInfoForm.accountSubTradeTranStatus==2">待确认</div>
            <div style="text-align: left" v-if="transactionInfoForm.accountSubTradeTranStatus==5">待处理</div>
            <div style="text-align: left" v-if="transactionInfoForm.accountSubTradeTranStatus==6">处理中</div>
            <div style="text-align: left" v-if="transactionInfoForm.accountSubTradeTranStatus==7">待挂账</div>
            <div style="text-align: left" v-if="transactionInfoForm.accountSubTradeTranStatus==8">挂账成功</div>
            <div style="text-align: left" v-if="transactionInfoForm.accountSubTradeTranStatus==9">挂账失败</div>
          </template>
        </el-form-item>
        <el-form-item label="记账标志:">
          <template slot-scope="scope">
            <div style="text-align: left" v-if="transactionInfoForm.accountSubTradeTranPaymentType==1">支出</div>
            <div style="text-align: left" v-if="transactionInfoForm.accountSubTradeTranPaymentType==2">收入</div>
          </template>
        </el-form-item>
        <el-form-item label="交易金额:">
          <div style="text-align: left">{{transactionInfoForm.accountSubTradeTranAmt}}</div>
        </el-form-item>
        <el-form-item label="备注:">
          <div style="text-align: left">{{transactionInfoForm.accountSubTradeRemark}}</div>
        </el-form-item>
      </el-form>
    </el-row>
  </div>
</template>

<script>
  import api from '../api/fetch'
  import {parseTime} from '../utils/index'
  import * as Message from '../utils/messageUtil'
  import {pageMixin} from '../common/mixin'
  import {exportModel} from '../common/export'

  export default {
    // 交易详情
    name: 'TransactionInfo',
    mixins: [pageMixin, exportModel],
    data() {
      return {
        transactionInfoForm: {
          userId: '',
          userName: '',
          userCnName: '',
          userMobile: '',
          userCertificateNumber: '',
          userCertificateType: '',
          userIdCardFontImg: '',
          userIdCardBackImg: ''
        },
        accountSubTradeId: ''
      }
    },
    methods: {
      // 获取个人用户信息
      getTransactionInfo() {
        api.get('/masterAccount/getHangingTradeDetailById?accountSubTradeId=' + this.accountSubTradeId).then(response => {
          if (response.data.code === 1) {
            console.log(response.data.data);
            this.transactionInfoForm = response.data.data;
          }
        })
      }
    },
    mounted() {
      this.accountSubTradeId = this.$route.params.id
      this.getTransactionInfo();
    }
  }
</script>

<style scoped>
  .content-page {
    width: 80%;
    margin: 30px auto;
  }

  .el-form-item {
    margin-left: 30px;
  }
</style>
