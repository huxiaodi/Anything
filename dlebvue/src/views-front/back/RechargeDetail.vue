<template>
  <div class="page1180">
    <div class="page-title">
      <div style="float:left;">
        <router-link to="/FeeDetailList">资金流水</router-link>
        <i class="el-icon-arrow-right"></i>
        充值详情
      </div>
    </div>
    <el-row class="page-content" style="padding-left:95px;">
      <el-form :model="infoDetails" label-position="left" labelWidth="120px" size="mini">
        <div style="font-weight: bold;margin:20px 0;font-size:16px;color:#000; ">
          【 账户充值 】
        </div>
        <el-form-item label="提现账户">平安银行子账户</el-form-item>
        <el-form-item label="当前状态">{{ infoDetails.account_sub_trade_tran_statusC }} 运费{{infoDetails.account_sub_trade_tran_amt}}</el-form-item>
        <div style="font-weight: bold;margin:20px 0;font-size:16px;color:#000; ">
          【 充值详情 】
        </div>

        <el-form-item label="充值信息">{{ infoDetails.account_sub_trade_take_cash_account_name}}</el-form-item>
        <el-form-item label="充值类型">{{ infoDetails.account_sub_trade_typeC }}</el-form-item>
        <el-form-item label="交易时间">{{ infoDetails.account_sub_trade_tran_date }}</el-form-item>
        <el-form-item label="流水单号" style="color: #00ACD6;">{{ infoDetails.account_sub_trade_no }}</el-form-item>
        <el-form-item label="交易币种">{{ infoDetails.account_sub_trade_ccy }}</el-form-item>
        <el-form-item label="充值金额" style="color: red;">{{ infoDetails.incomeAmount }}元</el-form-item>
        <el-form-item label="备注">{{ infoDetails.account_sub_trade_remark }}</el-form-item>
      </el-form>
    </el-row>
  </div>
</template>

<script>
  import api from '../api/fetch'
  import * as Message from '../utils/messageUtil'

  export default {
    name: 'RechargeDetail',
    data() {
      return {
        infoDetails: {}
      }
    },
    methods: {
      // 获取数据
      getData() {
//        this.isShowLoadingIcon = true;
//        api.post('/finance/waybillListNew', this.searchForm).then(response => {
//          if (response.data.code === 1) {
//            this.tableList = response.data.data.data.list
//            this.pageSettings.total = response.data.data.data.total
//          }
//        }).finally(() => {
//          this.isShowLoadingIcon = false;
//        })
      },
      returnLastStep() {
        this.$router.push(`/FeeDetailList`)
      }
    },
    mounted() {
      this.infoDetails = this.$route.params;
      this.getData()
    }
  }
</script>
<style scoped>
.el-form-item.el-form-item--mini{
  margin-left: 7px;
}
</style>
