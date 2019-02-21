<template>
  <div class="page1180">
    <div class="page-title">
      <div style="float:left;">
        <router-link to="/BusinessQuery">业务流水</router-link>
        <i class="el-icon-arrow-right"></i>
        业务详情
      </div>
    </div>
    <el-row class="page-content" style="padding-left:95px;">
      <el-form v-model="businessForm" label-position="left" labelWidth="120px" size="mini">
        <div style="font-weight: bold;margin:20px 0;font-size:16px;color:#000; ">
          【 余额支付 】
        </div>
        <el-form-item label="交易子账户">{{businessForm.account_sub_no}}</el-form-item>
        <el-form-item label="当前状态">{{businessForm.status}}</el-form-item>

        <div style="font-weight: bold;margin:20px 0;font-size:16px;color:#000; ">
          【 订单详情 】
        </div>

        <el-form-item label="交易对象">{{businessForm.account_sub_name}}</el-form-item>
        <el-form-item label="交易时间">{{businessForm.business_trade_create_time}}</el-form-item>
        <el-form-item label="业务单号">{{businessForm.business_trade_business_no}}</el-form-item>
        <el-form-item label="流水单号" style="color: #00ACD6;">{{businessForm.account_sub_trade_bank_no}}</el-form-item>
        <el-form-item label="记账标志" style="color: #257F54;">
          <template slot-scope="scope">
            <div v-if="businessForm.account_sub_trade_tran_payment_type===1">支出</div>
            <div v-if="businessForm.account_sub_trade_tran_payment_type===2">收入</div>
          </template>
        </el-form-item>
        <el-form-item label="交易金额" style="color: red;">{{businessForm.account_sub_trade_tran_amt}}</el-form-item>
        <el-form-item label="交易来源">{{businessForm.business_trade_business_from}}</el-form-item>
        <el-form-item label="业务类型">{{businessForm.business_trade_business_type}}</el-form-item>
        <el-form-item label="备注">{{businessForm.account_sub_trade_remark}}</el-form-item>
      </el-form>
    </el-row>

  </div>
</template>

<script>
  export default {
    name: 'BusinessDetail',
    data() {
      return {
        businessForm: []
      }
    },
    methods: {
      // 返回业务查询页面
      returnBusinessQuery() {
        this.$router.push(`/BusinessQuery`)
      }
    },
    mounted() {
      this.businessForm = this.$route.params
    }
  }
</script>

<style scoped>
  .el-form-item.el-form-item--mini{
    margin-left: 7px;
  }
</style>
