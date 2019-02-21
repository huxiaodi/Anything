<template xmlns:v-bind="http://www.w3.org/1999/xhtml">
  <div class="page1180">
    <div class="page-title">
      <div style="float:left;">
        <router-link to="/AccountCentral">账户中心</router-link>
        <i class="el-icon-arrow-right"></i>
        银行卡查询
      </div>
    </div>
    <el-row class="page-content">
      <!--循环列表-->
      <div class="showArea">
        <el-col style="float:left;width:260px">
          <!--卡片布局-->
          <el-card class="box-card" style="border-radius:10px">
            <div class="ceAdd">
              <i class="el-icon-circle-plus-outline"></i><br>
              <el-button type="text" @click="openAdd">添加银行卡</el-button>
            </div>
          </el-card>
        </el-col>
        <div v-for="item in tableList">
          <el-col style="float:left;width:260px">

            <!--卡片布局-->
            <el-card class="box-card bankshow" style="border-radius:10px;">
              <div>
              <span class="imgPadding">
                <img v-bind:src="item.bankLogo"/>
              </span>
              </div>

              <div class="codeSty">{{ formatBankNumberShow(item.MemberAcctNo) }}</div>
              <div >
                <span style="font-size: 10px;color:#409EFF;margin-left:22px;">交易额度: {{item.bankCardTransactionAmountType === 2 ? '<= 5万' : '> 5万'}} </span>
                <el-button style="float: right; padding-top: 28px;margin-left:15px;" type="text" @click="openUn(item.SubAcctNo,
                                                                                                       item.MemberName,
                                                                                                       item.MemberAcctNo,
                                                                                                       item.AcctOpenBranchName,
                                                                                                       item.bankCardId,
                                                                                                       item.bankCardReserveMobile)"
                           icon="el-icon-setting">
                  解绑
                </el-button>

                <el-button style="float: right; padding-top: 9px;" type="text" @click="openWithdrawals(item.SubAcctNo,
                                                                                                      item.MemberName,
                                                                                                      item.MemberAcctNo,
                                                                                                      item.AcctOpenBranchName,
                                                                                                      item.bankCardId,item.bankCardBank)"
                           icon="el-icon-sold-out">
                  提现
                </el-button>
              </div>
            </el-card>
          </el-col>
        </div>
      </div>

    </el-row>


  </div>
</template>

<script>
  // 引入相关
  import api from '../api/fetch'
  import * as Message from '../utils/messageUtil'
  import {pageMixin} from '../common/mixin.js'
  import {exportModel} from '../common/export'
  import {parseTime} from '../utils/index'

  export default {
    name: 'BankCard',
    // 初始配置
    data() {
      return {
        searchForm: {keyword: ''},
        tableList: []
        // cacheList: []
      }
    },
    // 定义方法
    methods: {
      // 查询(调接口)
      getData() {
        /*var params = this.$route.params;
         this.searchForm.QueryFlag = "2";                        // 个人
         this.searchForm.SubAcctNo = "3284000000006067";         // 子帐号
         this.searchForm.PageNum = "1";                          // 页码
         api.post('/pabank/MemberBindQuery', this.searchForm).then(response => {
         if(response.data.code === 1)
         {
         this.tableList = [];
         var cacheList = eval('(' + response.data.data.tranItemArray + ')');  //获取绑定的银行卡列表
         for (var i = 0; i < cacheList.length; i++) {
         if (cacheList[i].MemberAcctNo == "") {
         // 有账号信息【但未绑卡】
         } else {
         // 有账号信息【已经绑卡】
         this.tableList.push(cacheList[i]);
         }
         }
         }
         })*/

        //查询本地
        api.post('/bankCards/getLocalBankCradList', {}).then(response => {
          if (response.data.code === 1) {
            var accountDataList = response.data.data;

            //查询本地,把银行原字段用【本地字段】代替
            for (var i = 0; i < accountDataList.length; i++) {
              accountDataList[i].SubAcctNo = accountDataList[i].bankCardAccountSubId;               //匹配子账号
              accountDataList[i].AcctOpenBranchName = accountDataList[i].bankCardBank;               //开户银行
              accountDataList[i].MemberAcctNo = accountDataList[i].bankCardNo;                       //银行卡号
              accountDataList[i].MemberName = accountDataList[i].bankCardReserveBankAccountName;     //银行账户名称
              accountDataList[i].Mobile = accountDataList[i].bankCardReserveMobile;                 //手机号码
              accountDataList[i].MemberGlobalType = accountDataList[i].bankCardDocumentType;       //证件类型
              accountDataList[i].MemberGlobalId = accountDataList[i].bankCardDocumentNo;           //证件号码
              accountDataList[i].TranNetMemberCode = accountDataList[i].bankCardCreateUserId;     //创建人
            }

            this.tableList = accountDataList;
          }
        })
      },
      // 添加银行卡
      openAdd() {
        this.$router.push({
          name: '添加银行卡',
          params: {}
        })
      },
      // 解绑
      openUn(subAcctNo, subAcctNoName, MemberAcctNo, acctOpenBranchName, bankCardId, phone) { // 子账号,账户

        localStorage.setItem('UnBankCardPhone', phone);
        localStorage.setItem('UnBankCardSubAcctNo', subAcctNo);
        localStorage.setItem('UnBankCardMemberAcctNo', MemberAcctNo);
        localStorage.setItem('UnBankCardSubAcctNoName', subAcctNoName);
        localStorage.setItem('UnBankCardAcctOpenBranchName', acctOpenBranchName + ' | ' + this.formatBankNumber(MemberAcctNo));

        this.$router.push({
          name: '解绑银行卡',
          params: {
            subAcctNo: subAcctNo,
            subAcctNoName: subAcctNoName,
            memberAcctNo: MemberAcctNo,
            acctOpenBranchName: acctOpenBranchName + ' | ' + this.formatBankNumber(MemberAcctNo),
            bankCardId: bankCardId,
            phone: phone
          }
        })
      },
      // 提现
      openWithdrawals(subAcctNo, subAcctNoName, MemberAcctNo, acctOpenBranchName, bankCardId, bankCardBank) { // 子账号,账户

        this.$router.push({
          name: '提现',
          params: {
            subAcctNo: subAcctNo,
            subAcctNoName: subAcctNoName,
            memberAcctNo: MemberAcctNo,
            acctOpenBranchName: acctOpenBranchName + ' | ' + this.formatBankNumber(MemberAcctNo),
            bankCardId: bankCardId,
            bankCardBank: bankCardBank
          }
        })
      },
      // 格式化卡号显示
      formatBankNumber(bankNumber) {
        return bankNumber.substr(0, 4) + '********' + bankNumber.substr(-4);
      },
      formatBankNumberShow(bankNumber) {
        return bankNumber.substr(0, 4) + '  ****  ****  ' + bankNumber.substr(-4);
      }
    },
    // 调用方法
    mounted() {
      this.getData();
    }
  }
</script>

<style scoped>

  .text {
    font-size: 13px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: '';
  }

  .clearfix span {
    font-size: 13px;
    display: block;
    margin: 0 auto;
    text-align: center
  }

  .box-card {
    width: 240px;
    height: 160px;
    margin: 10px;
    background: url('../assets/banklogo/card.png') 0 -8px;
  }

  .showArea {

  }

  .nav {
    font-size: 13px;
    padding: 15px 0 0 0;
  }

  .codeSty {
    font-size: 18px;
    margin: 0 auto;
    padding: 22px 0 0 20px;
    color: #333;
  }

  .ceAdd {
    padding-top: 35px;
    text-align: center;
  }

  .imgPadding img {
    width: 140px;

  }

  .el-card__header {
    padding: 18px 0px 0 0;
  }

  font {
    font-family: 'SimHei'
  }

  .head {
    width: 80%;
    background-color: #ffffff;
    color: black;
    border-bottom: #ddd 1px solid;
    padding-bottom: 20px;
    margin: 30px auto;
  }

  .content-page {
    width: 80%;
    margin: 30px auto;
  }

</style>
