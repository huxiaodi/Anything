<template>
  <div class="page1180">
    <div class="page-title">
      <div style="float:left;">
        <router-link to="/FeeDetailList">资金流水</router-link>
        <i class="el-icon-arrow-right"></i>
        交易详情
      </div>
    </div>
    <el-row class="page-content" style="padding-left:95px;">
      <el-form :model="transactionForm" label-position="left" size="mini" labelWidth="120px">
        <div style="font-weight: bold;margin:20px 0;font-size:16px;color:#000; ">
          【 余额支付 】
        </div>
        <el-form-item label="交易子账户" style="margin-top: 15px">{{transactionForm.account_sub_no}}</el-form-item>
        <el-form-item label="当前状态">{{transactionForm.account_sub_trade_tran_statusC}}</el-form-item>
        <div style="font-weight: bold;margin:20px 0;font-size:16px;color:#000; ">
          【 订单详情 】
        </div>
        <el-form-item label="交易对象" style="margin-top: 15px">{{transactionForm.account_sub_name}}</el-form-item>
        <el-form-item label="交易时间">{{transactionForm.account_sub_trade_tran_date}}</el-form-item>
        <el-form-item :label="transactionForm.pageType == 'FeeDetailList'?'订单号':'业务单号'">{{transactionForm.business_trade_business_no}}</el-form-item>
        <el-form-item label="流水单号" style="color: #00ACD6;">{{transactionForm.account_sub_trade_bank_no}}</el-form-item>
        <el-form-item label="退单原因">{{transactionForm.account_sub_trade_back_reason}}</el-form-item>
        <el-form-item label="记账标记" style="color: #257F54;">
          <template slot-scope="scope">
            <div v-if="transactionForm.account_sub_trade_tran_payment_type===1">支出</div>
            <div v-if="transactionForm.account_sub_trade_tran_payment_type===2">收入</div>
          </template>
        </el-form-item>
        <el-form-item label="交易金额" style="color: red;">{{transactionForm.account_sub_trade_tran_amt}}</el-form-item>
        <el-form-item label="备注">{{transactionForm.account_sub_trade_remark}}</el-form-item>
      </el-form>
    </el-row>


  </div>
</template>

<script>
  import api from '../api/fetch'
  import * as Message from '../utils/messageUtil'
  import {pageMixin} from '../common/mixin.js'
  import {parseTime} from '../utils/index'
  import {exportModel} from '../common/export'

  export default {
    name: 'TransactionDetail',
    mixins: [pageMixin, exportModel],
    data() {
      return {
        transactionForm: ''
      }
    },
    methods: {
    },
    mounted() {
      this.transactionForm = this.$route.params
    }
  }
</script>

<style scoped>
.el-form-item.el-form-item--mini{
  margin-left: 7px;
}
</style>
