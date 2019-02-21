<template>
  <div class="AccountManagement-page">
    <div class="AccountManagement-first AM-item">
      <div class="AccountManagement-first-item">
        <div class="AccountManagement-list-title">
          平安总账户资产
        </div>
        <div class="AccountManagement-list-content">
          {{form.totalAccountBalance}}元
        </div>
      </div>
      <el-row class="AccountManagement-first-item">
        <el-col :span="10" >
          <el-row class="AccountManagement-list-capital">日收入</el-row>
          <el-row class="AccountManagement-list-capital-content">+{{form.dayIncome}}</el-row>
          <el-row class="AccountManagement-list-capital">日支出</el-row>
          <el-row class="capital-red AccountManagement-list-capital-content">-{{form.dayPay}}</el-row>
        </el-col>
        <el-col :span="10" >
          <el-row class="AccountManagement-list-capital">月收入</el-row>
          <el-row class="AccountManagement-list-capital-content">+{{form.monthIncome}}</el-row>
          <el-row class="AccountManagement-list-capital">月支出</el-row>
          <el-row class="capital-red AccountManagement-list-capital-content">-{{form.monthPay}}</el-row>
        </el-col>
      </el-row>
    </div>
    <!--<div class="AM-item">-->
      <!--<div class="AccountManagement-first-item">-->
        <!--<div class="AccountManagement-list-title">-->
          <!--德邻业务子账户-->
        <!--</div>-->
        <!--<div class="AccountManagement-list-content capital-yellow">-->
          <!--{{form.ywSubAcctAvailBal}}元-->
        <!--</div>-->
      <!--</div>-->
      <!--<div class="AccountManagement-first-item">-->
        <!--<div class="AccountManagement-list-title">-->
          <!--担保子账户-->
        <!--</div>-->
        <!--<div class="AccountManagement-list-content capital-yellow">-->
          <!--{{form.dbSubAcctAvailBal}}元-->
        <!--</div>-->
      <!--</div>-->
    <!--</div>-->
    <div class="AM-item">
      <div class="AccountManagement-other-list">
        业务子账户
        <span class="capital-yellow">{{form.ywSubAcctAvailBal}}</span>
        元
      </div>
      <div class="AccountManagement-other-list">
        利息子账户
        <span class="capital-yellow">{{form.lxSubAcctAvailBal}}</span>
        元
      </div>
      <div class="AccountManagement-other-list">
        担保子账户
        <span class="capital-yellow">{{form.dbSubAcctAvailBal}}</span>
        元
      </div>
    </div>
    <div class="AM-item">
      <div class="AccountManagement-other-list">
        在途子账户
        <span class="capital-yellow">{{form.ztSubAcctAvailBal}}</span>
        元
      </div>
      <div class="AccountManagement-other-list">
        挂账子账户
        <span class="capital-yellow">{{form.gzSubAcctAvailBal}}</span>
        元
      </div>
      <div class="AccountManagement-other-list">
        手续费子账户
        <span class="capital-yellow">{{form.sxfSubAcctAvailBal}}</span>
        元
      </div>
    </div>

  </div>
</template>

<script>
  import api from '@/api/fetch'
  import * as Message from '../utils/messageUtil'

  export default {
    name: 'AccountManagement',
    data() {
      return {
        form: {}
      }
    },
    methods: {
      getData() {
        api.get('/admin/accountManage').then(response => {
          if (response.data.code === 1) {
            var result = response.data.data;
            console.log(result);
            this.form = result;
          } else {
            Message.MessageError(response.data.msg)
          }
        });
      }
    },
    mounted() {
      this.getData();
    }
  }
</script>
<style scoped>
.AccountManagement-page{
  min-width: 800px;
  text-align:center;
}
.AM-item{
  width: 800px;
  margin: 0 auto;
  overflow: hidden;
  padding: 40px 0;
  border-bottom: 1px dashed #ddd;
}
.AccountManagement-first-item{
  width: 50%;
  float: left;
  overflow: hidden;
}
.AccountManagement-list-title{
  font-size: 20px;
  color: #999;
}
.AccountManagement-list-content{
  color: #409eff;
  font-size: 24px;
  margin-top: 15px;
}
.AccountManagement-list-capital{
  font-size: 14px;
  color: #999;
}
.AccountManagement-list-capital-content{
  font-size: 18px;
  color: #409eff;
  margin-bottom: 15px;
  margin-top: 5px;
}
.capital-red{
  color: #ff4040;
}
.capital-yellow{
  color:#ff7626;
}
.AccountManagement-other-list{
  font-size: 16px;
  width: 33.333%;
  color: #999;
  float: left;
}

</style>
