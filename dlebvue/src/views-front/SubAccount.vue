<template>
  <div class="page1180">
    <div class="page-title">
      <div style="float:left;">
        <router-link to="/AccountCentral">账户中心</router-link>
        <i class="el-icon-arrow-right"></i>
        子账户管理
      </div>
    </div>
    <el-row class="page-content">
      <el-row>
        <el-button class="gradient-btn-hover" type="primary" round @click="toTransaction">交易查询</el-button>
        <el-button class="gradient-btn-hover" type="primary" round @click="setPassword('S')">设置支付密码</el-button>
        <el-button class="gradient-btn-hover" type="primary" round @click="setPassword('R')">重置支付密码</el-button>
        <el-button class="gradient-btn-hover" type="primary" round @click="setPassword('C')">修改支付密码</el-button>
        <el-button class="gradient-btn-hover" type="primary" round @click="showModifMobile()">修改手机号</el-button>
      </el-row>
      <el-row class="SubAccount" style="margin-top:30px;">
        <el-table ref="myTable" stripe border
                  :data="tableList"
                  highlight-current-row
                  @row-click="handleSelectionChange"
                  @selection-change="selectionChange"
                  width="100%"
                >
          <el-table-column style="background-color:#fff;" type="selection" width="40px" align="center">
          </el-table-column>
          <el-table-column prop="orderNo" sortable align="center" width="250px" label="子账户类型">
            <template slot-scope="scope">
              <div v-if="scope.row.accountType == 1">
                平安银行子账户
              </div>
            </template>
          </el-table-column>
          <el-table-column prop="accountSubNo" sortable align="center" width="250px" label="子账户账号">
          </el-table-column>
          <el-table-column prop="accountType" sortable align="center" width="110px" label="账户状态">
            <template slot-scope="scope">
              <div v-if="scope.row.accountSubStatus == 0">
                停用
              </div>
              <div v-if="scope.row.accountSubStatus == 1">
                启用
              </div>
            </template>
          </el-table-column>
          <el-table-column prop="orderStatus" align="center" width="240px" label="创建时间">
            <template slot-scope="scope">
              {{scope.row.accountSubCreateTime}}
            </template>
          </el-table-column>
          <el-table-column prop="createUser" align="center" width="169px" label="创建人"> <!--show-overflow-tooltip-->

          </el-table-column>

        </el-table>
      </el-row>
      <el-row style="margin-top:40px;" v-show="showTab">
        <div class="management-content">
          <div class="management-content-item">
            <div style="margin:20px 0 10px;">
              <div class="management-item number-color">
                {{balanceForm.acctAvailBal}}
              </div>
              <div class="management-item number-color">
                  {{balanceForm.cashAmt}}
              </div>
              <div class="user-balance-item number-color showHide" @click="showAndHide(true)" v-show="isShow"><img src="/static/img/monryState/open.png" width="22" title="显示余额" /></div>
              <div class="user-balance-item number-color showHide" @click="showAndHide(false)" v-show="isHide"><img src="/static/img/monryState/close.png" width="22" title="隐藏余额" /></div>
            </div>
            <div style="margin-bottom:30px;">
              <div class="management-item">
                可用余额(元)
              </div>
              <div class="management-item">
                可提现余额（元）
              </div>
              <div class="management-item">
                <a href="javascript:" style="color:#11a0fe" @click="dialogExplain = true">提现说明</a>
              </div>
            </div>
            <div>
              <div class="management-item">
                <el-button class="gradient-btn" type="primary" plain round disabled size="mini" style="width: 80px" icon="el-icon-circle-plus">充值
                </el-button>
              </div>
              <div class="management-item">
                <el-button class="gradient-btn" type="primary" plain round size="mini" style="width: 80px" @click="toTransferFee" icon="el-icon-sort">转账
                </el-button>
              </div>
              <div class="management-item">
                <el-button class="gradient-btn" type="primary" plain round size="mini" style="width: 80px" @click="toTransferFee" icon="el-icon-sold-out">提现
                </el-button>
              </div>
            </div>
          </div>
        </div>
        <div class="management-content">
          <div style="width:350px;margin-bottom:35px;">
            <span style="font-size:16px;color:#000;">银行卡</span>
            <router-link style="float:right" to="/BankCard">银行卡管理</router-link>
          </div>
          <el-dropdown style="margin-bottom:20px;" size="mini" split-button  type="primary"   @command="handleCommand" @click="bankGo">
            {{selected}}
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item v-for="item in bankCardsList" :key="item.key" :command="item" >{{item.show}}</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>

          <el-button plain style="width:350px;" size="mini" @click="openAdd" icon="el-icon-circle-plus-outline">添加银行卡</el-button>
        </div>
        <!-- <el-col :span="12" style="padding-left:10px;">
          <div class="userItem">
            <el-row style="width:90%;margin-left:20px;margin-top:20px;;ext-align:left;">
              <el-row>
                <el-col :span="8">
                  <div class="fontStyle" style="color: red;text-align: left;font-size: 18px">
                    {{balanceForm.acctAvailBal}}
                  </div>
                </el-col>
                <el-col :span="9">
                  <div class="fontStyle" style="color: red;text-align: left;font-size: 18px">
                    {{balanceForm.cashAmt}}
                  </div>
                </el-col>
                <el-col :span="7">
                  <div class="fontStyle" style="color: red;text-align: left;font-size: 18px"></div>
                </el-col>
              </el-row>

              <el-row style="padding-top: 20px;">
                <el-col :span="8">
                  <div class="fontStyle">可用余额（元）</div>
                </el-col>
                <el-col :span="9">
                  <div class="fontStyle">可提现余额（元）</div>
                </el-col>
                <el-col :span="7">
                  <a href="javascript:" style="color:#11a0fe" @click="dialogExplain = true">提现说明</a>
                </el-col>
              </el-row>

              <el-row style="padding-top: 20px;">
                <el-col :span="8">
                  <el-button class="linear" type="primary" round disabled size="mini" style="width: 100px">充值
                  </el-button>
                </el-col>
                <el-col :span="8">
                  <el-button type="primary" plain round size="mini" style="width: 100px" @click="toTransferFee">转账
                  </el-button>
                </el-col>
                <el-col :span="7">
                  <el-button type="primary" plain round size="mini" style="width: 100px" @click="toWithdrawals">提现
                  </el-button>
                </el-col>
              </el-row>
            </el-row>
          </div>
        </el-col>
        <el-col :span="12" style="padding:25px 20px 0 50px;">
          <el-row>
            <el-col :span="10">银行卡</el-col>
            <el-col :span="10">
              <router-link to="/BankCard">银行卡管理</router-link>
            </el-col>
          </el-row>


          <el-row style="margin-top:10px;">
            <el-dropdown size="small" split-button type="primary" @command="handleCommand" @click="bankGo">
              {{selected}}
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item v-for="item in bankCardsList" :key="item.key" :command="item">{{item.show}}
                </el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </el-row>

          <el-row style="margin-top:10px;">
            <el-button plain style="width:280px;" size="mini" @click="openAdd">+添加银行卡</el-button>
          </el-row>
        </el-col> -->
      </el-row>
    </el-row>

    <el-dialog center :visible.sync="dialogRegist" width="33%" :close-on-click-modal="dialogClickInfo"
               :close-on-press-escape="dialogClickInfo" :show-close="dialogClickInfo">
      <div class="login-uesr-title">
        修改手机号
      </div>
      <div>
        <el-form ref="personalForm" :rules="rule" label-width="140px" :model="changeMobile">
          <el-row>
            <el-form-item label="请输入新手机号" prop="mobile">
              <el-input type="text" v-model="changeMobile.mobile"></el-input>
            </el-form-item>
          </el-row>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
            <el-button type="primary" @click="dialogRegist = false">取消</el-button>
            <el-button type="primary" @click="modifMobile('personalForm')">确定</el-button>
          </span>
    </el-dialog>

    <el-dialog center :visible.sync="dialogExplain" width="33%" :close-on-click-modal="false"
    :close-on-press-escape="false" :show-close="false">
    <div class="login-uesr-title">
      提现说明
    </div>
    <div>
      <p>可提现余额:会员可提现到实体银行卡或对公账户；
        待转可提现余额:会员T日不能提现到实体银行卡或对公账户具体包括:1会员当日充值金额；2买方当日付款，收款方收款金额； T日日终后待转可提现金额自动结转到会员可提现余额；</p>
      <p></p>
      <p>可用余额:可用余额包括可提现余额和待转可提现余额；可用于平台交易、转账付款；会员T日产生的在途清分收入和会员间交易收入当日只增加可用余额，不增加可提现余额；</p>
    </div>
    <span slot="footer" class="dialog-footer">
            <el-button type="primary" @click="dialogExplain = false">关闭</el-button>
          </span>
    </el-dialog>
  </div>
</template>

<script>
  import api from '../api/fetch'
  import {parseTime} from '../utils/index'
  import * as Message from '../utils/messageUtil'
  import {pageMixin} from '../common/mixin'
  import {exportModel} from '../common/export'
  // 手机号
  var mobileReg = /^1[34578]\d{9}$/;
  window.aa = this;
  export default {
    name: 'SubAccount',
    data() {
      return {
        dialogClickInfo: false,
        dialogExplain: false,
        dialogRegist: false,
        changeMobile: {},
        tableList: [],
        showTab: false,
        isShow: false,
        isHide: true,
        bankCards: {
          show: ''
        },
        bankCardsList: [],
        selected: '------------------未绑卡-----------------',
        form: {},
        balanceForm: {
          acctAvailBal: '',
          cashAmt: '',
          QueryFlag: 2,
          SubAcctNo: '',
          PageNum: 1,
          ReservedMsg: '',
          acctAvailBalSave: '',
          cashAmtSave:''
        },
        rule: {
          mobile: [{
            required: true,
            message: '手机号不能为空',
            trigger: 'blur'
          }, {
            pattern: mobileReg,
            message: '请输入正确的手机号'
          }
          ]
        }
      }
    },
    methods: {
      getData() {
        api.get('/accountSub/accountSubController/getAccountSubs').then(response => {
          if (response.data.code === 1) {
            this.tableList = response.data.data;
          } else {
            Message.MessageError(response.data.msg)
          }
        })
      },
      toTransferFee: function () {
        this.$router.push('TransferFee')
      },
      toWithdrawals: function () {
        this.$router.push('Withdrawals')
      },
      openAdd() {
        this.$router.push({
          name: '添加银行卡',
          params: {}
        })
      },
      getBalance() {
//        this.balanceForm.SubAcctNo = sessionStorage['dleb_accountSubNo'] // 会员子账户账号
//        api.post('home/CustAcctIdBalanceQuery', this.balanceForm).then(response => {
//          if (response.data.code === 1) {
//            this.balanceForm = response.data.data[0]
//          } else {
//            Message.MessageError(response.data.msg)
//          }
//        })
        api.get('/account/userAccount/getAvailableBalance').then(response => {
          if (response.data.code === 1) {
            this.balanceForm = response.data.data;
            this.balanceForm.acctAvailBalSave = response.data.data.acctAvailBal; //暂存余额
            this.balanceForm.cashAmtSave = response.data.data.cashAmt;
//            this.acctAvailBal = response.data.data.acctAvailBal || 0;
//            this.cashAmt = response.data.data.cashAmt || 0;
          } else {
            Message.MessageError(response.msg)
          }
        })
      },
      //格式化显示
      formatBankNumberShow(bankNumber) {
        if (bankNumber != null) {
          return bankNumber.substr(0, 4) + '  ****  ****  ' + bankNumber.substr(-4);
        } else {
          return "";
        }
      },
      formatBankButton(bankNumber) { //优化银行名称
        if (bankNumber != null) {
          return bankNumber.substr(0, 6);
        } else {
          return "";
        }
      },
      //获得银行卡信息
      getBankCard() {

        api.post('/bankCards/getLocalBankCradList', {}).then(response => {
          if (response.data.code === 1) {

            if (response.data.data.length > 0) {

              var data = response.data.data;
              var list = [];

              for (var i = 0; i < data.length; i++) {
                list.push({
                  show: this.formatBankButton(data[i].bankCardBank) + " | 储蓄卡 | " + this.formatBankNumberShow(data[i].bankCardNo),
                  key: data[i].bankCardNo,
                  val: data[i].bankCardNo
                });
              }
              this.selected = list[0].show;

              this.bankCardsList = list;
            } else {
              this.bankCards.show = '未绑卡';
            }
          }
        })

      },
      setPassword(type) {
        if (this.row === undefined || this.row.length < 1) {
          Message.MessageError('请选择一条记录');
          return;
        }
        if (this.row.length > 1) {
          Message.MessageError('只能选择一条记录');
          return;
        }
        if (type === 'S' && this.row[0].accountSubPwdStatus === 1) {
          Message.MessageError('已设置支付密码');
          return;
        }
        if (type !== 'S' && this.row[0].accountSubPwdStatus === 0) {
          Message.MessageError('请先设置支付密码');
          return;
        }
        api.post('/bankCards/isBundBankCard', {}).then(response => {
          if (response.data.code === 1) {
            if (response.data.data.length > 0) {
              this.$router.push({path: '/SetPaymentCode', query: {accountSubNo: this.row[0].accountSubNo, type: type}});
            } else {
              Message.MessageError('请先绑定银行卡');
              return;
            }
          }
        })
      },
      showAndHide(state) {
        if (state) {
          //控制按钮显示
          this.isShow = false;
          this.isHide = true;
          this.balanceForm.acctAvailBal = this.balanceForm.acctAvailBalSave;
          this.balanceForm.cashAmt = this.balanceForm.cashAmtSave;

          this.saveState("0"); //保存显示状态
        } else {
          //控制按钮显示
          this.isShow = true;
          this.isHide = false;
          this.balanceForm.acctAvailBal = "***";
          this.balanceForm.cashAmt = "***";

          this.saveState("1"); //保存显示状态
        }
      },
      saveState(state) {
        api.post('/bankCards/saveState', {
          state: state
        }).then(response => {
          if (response.data.code === 1) {

          }
        })
      },
      showModifMobile() {
        if (this.row === undefined || this.row.length < 1) {
          Message.MessageError('请选择一条记录');
          return;
        }
        if (this.row.length > 1) {
          Message.MessageError('只能选择一条记录');
          return;
        }
        if (this.row[0].accountSubPwdStatus === 0) {
          Message.MessageError('请先设置支付密码');
          return;
        }
        //this.dialogRegist = true;
        //this.changeMobile.mobile = null;

        localStorage.setItem('SubAccountModifyCode', this.row[0].accountSubNo);
        this.$router.push({
          name: '修改手机号',
          params: {}
        })
      },
      modifMobile(formName) {
        if (this.row === undefined || this.row.length < 1) {
          Message.MessageError('请选择一条记录');
          return;
        }
        if (this.row.length > 1) {
          Message.MessageError('只能选择一条记录');
          return;
        }
        if (this.row[0].accountSubPwdStatus === 0) {
          Message.MessageError('请先设置支付密码');
          return;
        }
        this.$refs[formName].validate((valid) => {
            if(valid){
              this.$router.push({
                path: '/SetPaymentCode',
                query: {accountSubNo: this.row[0].accountSubNo, type: 'M', mobile: this.changeMobile.mobile}
              });
            }
        });
      },
      selectionChange(val){
        this.row = val;
      },
      handleSelectionChange(val) {
        this.showTab = true;
      },
      toTransaction() {
        this.$router.push({path: '/BusinessQuery'});
      },
      handleCommand(command) {
        this.selected = command.show;
      },
      bankGo() {
        this.$router.push({
          name: '银行卡管理',
          params: {}
        })
      }
    },
    mounted() {
      this.getData();
      this.getBalance();
      this.getBankCard();
    }
  }
</script>

<style>
.SubAccount .has-gutter > tr > th{
  background-color: #eee!important;
}

.management-content{
  width: 50%;
  height: 200px;
  float: left;
  padding: 20px 0px 20px 60px;
  border-bottom: 1px solid #eee;
}
.management-item{
  width: 140px;
  float: left;
}
.management-content-item > div{
  overflow: hidden;
}
</style>
