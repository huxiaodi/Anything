<template>
  <div class="page1180-home">
    <!-- 用户 -->
    <div class="userItem user-title">
      <el-upload class="avatar-uploader" action="/api/account/userAccount/getUploadInfo" :show-file-list="false"
                 :with-credentials="true" :headers="uploadToken" :on-success="handleAvatarSuccess"
                 :before-upload="beforeAvatarUpload">
        <img v-if="user.userHeadImg" :src="user.userHeadImg" class="avatarUpload">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
      <p style="font-weight: bold;font-size: 18px;margin:6px 0 0;">{{user.userName}}</p>
      <p style="margin:6px 0 0;">欢迎使用德邻e宝</p>
      <p style="margin:6px 0 0;color:#f35828;">积分筹建中</p>
    </div>
    <!-- 余额转账 -->
    <div class="userItem user-balance">
      <div style="height:30px;margin-bottom:15px;">
        <div class="user-balance-item number-color">{{balanceForm.acctAvailBal}}
        </div>
        <div class="user-balance-item number-color">{{balanceForm.cashAmt}}
        </div>
        <div class="user-balance-item number-color showHide" @click="showAndHide(true)" v-show="isShow"><img
          src="/static/img/monryState/open.png" width="22" title="显示余额"/></div>
        <div class="user-balance-item number-color showHide" @click="showAndHide(false)" v-show="isHide"><img
          src="/static/img/monryState/close.png" width="22" title="隐藏余额"/></div>
      </div>
      <div style="height:30px;margin-bottom:25px;">
        <div class="user-balance-item">
          <div>可用余额（元）</div>
        </div>
        <div class="user-balance-item">
          <div>可提现余额（元）</div>
        </div>
        <div class="user-balance-item">
          <a href="javascript:" style="color:#11a0fe" @click="dialogExplain = true">提现说明</a>
        </div>
      </div>
      <div style="height:30px;">
        <div class="user-balance-item">
          <el-button class="gradient-btn" type="primary" plain round size="mini" style="width: 100px"
                     v-show="_btn('wodeebao_pay')" @click="showPayDialog" icon="el-icon-circle-plus">充值
          </el-button>
        </div>
        <div class="user-balance-item">
          <el-button class="gradient-btn" type="primary" plain round size="mini" style="width: 100px"
                     v-show="_btn('wodeebao_transfer')" @click="toTransferFee" icon="el-icon-sort">转账
          </el-button>
        </div>
        <div class="user-balance-item">
          <el-button class="gradient-btn" type="primary" plain round size="mini" style="width: 100px"
                     v-show="_btn('wodeebao_withdrawal')" @click="toWithdrawals" icon="el-icon-sold-out">提现
          </el-button>
        </div>
      </div>

    </div>
    <!-- 支出收入 -->
    <div class="userItem user-bill">
      <div class="user-bill-item">
        <div class="user-bill-item-number">{{statistics.dayIncome}}</div>
        <div>日收入(元)</div>
      </div>
      <div class="user-bill-item">
        <div class="user-bill-item-number">{{statistics.monthIncome}}</div>
        <div>月收入(元)</div>
      </div>
      <div class="user-bill-item">
        <div class="user-bill-item-number">{{statistics.dayPay}}</div>
        <div>日支出(元)</div>
      </div>
      <div class="user-bill-item">
        <div class="user-bill-item-number">{{statistics.monthPay}}
        </div>
        <div>月支出(元)</div>
      </div>


    </div>
    <div class="user-form">
      <div class="user-form-title">
        <div style="float:left;font-size:18px;
            font-weight:bold;">最近交易
        </div>
        <div>
          <el-button type="text" @click="$router.push('/FeeDetailList')" style="padding: 0;float:right"
                     v-show="_btn('wodeebao_more')">更多资金流水
          </el-button>
        </div>
      </div>
      <el-table class="borderNone" ref="myTable" stripe border :data="tableList"
                style="width: 1180px!important;font-size:12px!important;" :height="230" highlight-current-row>
        <el-table-column prop="account_sub_trade_tran_date" align="center" label="交易时间" width="180px"></el-table-column>
        <!-- <el-table-column prop="account_sub_no" align="center" width="170px" label="子账户"></el-table-column> -->
        <el-table-column prop="account_sub_trade_typeC" align="center" width="80px" label="类型"></el-table-column>
        <el-table-column prop="account_sub_name" align="center" width="210px" label="交易对象"
                         show-overflow-tooltip></el-table-column>
        <el-table-column prop="account_sub_trade_no" align="center" width="200px" label="流水单号">
          <template slot-scope="scope">
            <a style="cursor:pointer;" @click="viewDetails(scope.row)">
              {{scope.row.account_sub_trade_bank_no}}</a>
          </template>
        </el-table-column>
        <el-table-column prop="business_trade_business_no" align="center" width="200px" label="订单号"
                         show-overflow-tooltip></el-table-column>
        <el-table-column width="80px" prop="payAmount" align="center" label="支出">
          <template slot-scope="scope">
            <div style="color: red">
              {{scope.row.payAmount}}
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="incomeAmount" width="80px" align="center" label="收入">
          <template slot-scope="scope">
            <div style="color:green;">
              {{scope.row.incomeAmount}}
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="account_sub_trade_tran_statusC" align="center" label="交易状态"></el-table-column>
      </el-table>
    </div>
    <div class="user-form" style="text-align:center">
      <div class="user-form-title">
        <div style="float:left;font-size:18px;
            font-weight:bold;">关联平台
        </div>
      </div>
      <a target="_blank" href="http://www.dllg56.com" @mouseover="changeSpan(1)">
        <el-col class="show-bottom-item bg1" :span="spanObj.span1">
          <div class="about-web-title">
            <img src="../assets/img/front-tit1.png" alt="">
          </div>
          <div class="about-web-content">
            多元委托，便捷结算，
            <br>
            精确跟踪，贴心服务
          </div>
        </el-col>
      </a>
      <a target="_blank" href="http://eb.ansteel.cn/" @mouseover="changeSpan(2)">
        <el-col class="show-bottom-item bg2" :span="spanObj.span2">
          <div class="about-web-title">
            <img src="../assets/img/front-tit2.png" alt="">
          </div>
          <div class="about-web-content">
            钢铁交易[一站式]服务，
            <br>
            零星物资采购新模式
          </div>
        </el-col>
      </a>
      <a target="_blank" @mouseover="changeSpan(3)" href="http://www.dllg56.com/our-services?type=cloudRepo">
        <el-col class="show-bottom-item bg3" :span="spanObj.span3">
          <div class="about-web-title">
            <img src="../assets/img/front-tit3.png" alt="">
          </div>
          <div class="about-web-content">
            管理标准化、精细化、
            <br>
            透明化、智慧化
          </div>
        </el-col>
      </a>
      <a target="_blank" href="http://dllg.qiatuchina.com/our-services?type=yunjia
  " @mouseover="changeSpan(4)">
        <el-col class="show-bottom-item bg4" :span="spanObj.span4">
          <div class="about-web-title">
            <img src="../assets/img/front-tit4.png" alt="">
          </div>
          <div class="about-web-content">
            加工需求发布，合同签署，
            <br>
            加工入库，配套运输一体化
          </div>
        </el-col>
      </a>
      <a style="
        line-height:150px;" target="_blank" href="http://delinzl.cn/">
        <el-col :span="3" style="background:#efefef;">
          更多
        </el-col>
      </a>
    </div>

    <el-dialog center :visible.sync="dialogExplain" width="33%" :close-on-click-modal="false"
               :close-on-press-escape="false" :show-close="false">
      <div class="login-uesr-title">
        提现说明
      </div>
      <div>
        <p>可提现余额:会员可提现到实体银行卡或对公账户； 待转可提现余额:会员T日不能提现到实体银行卡或对公账户具体包括:1会员当日充值金额；2买方当日付款，收款方收款金额；
          T日日终后待转可提现金额自动结转到会员可提现余额；</p>
        <p></p>
        <p>可用余额:可用余额包括可提现余额和待转可提现余额；可用于平台交易、转账付款；会员T日产生的在途清分收入和会员间交易收入当日只增加可用余额，不增加可提现余额；</p>
      </div>
      <span slot="footer" class="dialog-footer">
            <el-button type="primary" @click="dialogExplain = false">关闭</el-button>
          </span>
    </el-dialog>

    <el-dialog center :visible.sync="dialogRegist" width="33%" :close-on-click-modal="dialogClickInfo"
               :close-on-press-escape="dialogClickInfo" :show-close="dialogClickInfo">
      <div class="login-uesr-title">
        充值
      </div>
      <div>
        <el-form ref="payForm" :rules="rule" label-width="140px" :model="payForm">
          <el-row>
            <el-row>
              <el-form-item label="充值账户">
                <el-select filterable remote clearable placeholder="请填写充值账户"
                           v-model="subAcct">
                  <el-option v-for="item in subAccountList" :key="item.accountSubNo" :label="item.accountSubNo"
                             :value="item">
                    <span class="name">{{ item.accountSubNo }}</span>
                  </el-option>
                </el-select>
              </el-form-item>
            </el-row>
            <el-row>
              <el-form-item label="充值子账户名称">
                <el-input type="text" v-model="payFormer.accountName" disabled></el-input>
              </el-form-item>
            </el-row>
            <el-form-item label="充值金额" prop="money">
              <el-input type="text" v-model="payForm.money"></el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="手续费">
              <el-input type="text" v-model="payForm.fee" disabled></el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-form-item label="备注">
              <el-input type="text" v-model="payForm.remark"></el-input>
            </el-form-item>
          </el-row>
        </el-form>
      </div>
      <span slot="footer" class="dialog-footer">
            <el-button type="primary" @click="dialogRegist = false">取消</el-button>
            <el-button type="primary" @click="payMoney('payForm')">确定</el-button>
          </span>
    </el-dialog>


  </div>
</template>

<script>
  import api from '@/api/fetch'
  import * as Message from '../utils/messageUtil'

  var AmountReg = /^\d{1,12}(?:\.\d{1,2})?$/;
  export default {
    name: 'FrontHome',
    data() {
      let checkAmount = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('充值金额必填'));
        }
        if (!AmountReg.test(value)) {
          return callback(new Error('金额填写有误，仅支持最多12位的两位小数'));
        }
        callback();
      };
      return {
        subAcct: {},
        dialogClickInfo: false,
        dialogRegist: false,
        payForm: {
          money: null,
          fee: null,
          remark: null
        },
        payFormer: {
          accountId: null,
          accountName: null
        },
        spanObj: {
          span1: 12,
          span2: 3,
          span3: 3,
          span4: 3
        },
        user: JSON.parse(sessionStorage.getItem('dleb_user')),
        uploadToken: {
          token: sessionStorage.getItem('dleb_token')
        },
        searchForm: {
          no: '',
          searchTimes: null,
          tradeType: '',
          accountName: '',
          startAmt: '',
          endAmt: '',
          keyword: '',
          income: '',
          pageSize: 5
        },
        balanceForm: {
          acctAvailBal: '',
          cashAmt: '',
          QueryFlag: 2,
          SubAcctNo: '',
          PageNum: 1,
          ReservedMsg: '',
          acctAvailBalSave: '',
          cashAmtSave: ''
        },
        tableList: [],
        rate: 0, // 支付费率
        // 可用余额
        acctAvailBal: 0,
        // 可提现余额
        cashAmt: 0,
        statistics: {},
        dialogExplain: false,
        subAccountList: [],
        isShow: false,
        isHide: true,
        rule: {
          money: [{
            validator: checkAmount,
            trigger: 'change'
          }
          ]
        }
      }
    },
    watch: {
      subAcct: function () {
        this.payFormer.accountName = this.subAcct.accountSubName;
      },
      'payForm.money': function () {
        var momey = (this.rate / 100) * this.payForm.money;
        this.payForm.fee = Number(momey).toFixed(2)
      }
    },
    methods: {
      changeSpan(index) {
        console.log(index);
        this.spanObj.span1 = 3
        this.spanObj.span2 = 3
        this.spanObj.span3 = 3
        this.spanObj.span4 = 3
        this.spanObj['span' + index] = 12
      },
      toTransferFee: function () {
        this.$router.push('TransferFee')
      },
      toWithdrawals: function () {
        this.$router.push('Withdrawals')
      },
      // 搜索 获取数据
      getData() {
        this.isShowLoadingIcon = true;
        if (this.searchForm.searchTimes !== null) {
          this.searchForm.startTime = this.searchForm.searchTimes[0];
          this.searchForm.endTime = this.searchForm.searchTimes[1];
        } else {
          this.searchForm.startTime = '';
          this.searchForm.endTime = '';
        }
        // 获取充值子账户
        api.post('/accountSub/accountSubController/getAccountSubs').then(response => {
          if (response.data.code === 1) {
            this.subAccountList = response.data.data;
          }
        })
        // 获取充值费率
        api.get('/dictManager/getPayRate?key=支付费率').then(response => {
          if (response.data.code === 1) {
            this.rate = response.data.data;
          }
        })
        api.post('/trade/record/list', this.searchForm).then(response => {
          if (response.data.code === 1) {
            let list = response.data.data.list;
            list.forEach((item) => {
              // 支出还是收入
              if (item.account_sub_trade_tran_payment_type === 1) {
                // 支出
                item.payAmount = item.account_sub_trade_tran_amt;
              } else if (item.account_sub_trade_tran_payment_type === 2) {
                // 收入
                item.incomeAmount = item.account_sub_trade_tran_amt;
              }
              // 类型
              if (item.account_sub_trade_type === '1') {
                item.account_sub_trade_typeC = '会员交易';
              } else if (item.account_sub_trade_type === '2') {
                item.account_sub_trade_typeC = '转账';
              } else if (item.account_sub_trade_type === '3') {
                item.account_sub_trade_typeC = '充值';
              } else if (item.account_sub_trade_type === '4') {
                item.account_sub_trade_typeC = '提现';
              } else if (item.account_sub_trade_type === '5') {
                item.account_sub_trade_typeC = '服务费';
              } else if (item.account_sub_trade_type === '6') {
                item.account_sub_trade_typeC = '退款';
              } else if (item.account_sub_trade_type === '7') {
                item.account_sub_trade_typeC = '充值';
              }
              // 交易状态
              if (item.account_sub_trade_tran_status === 0) {
                item.account_sub_trade_tran_statusC = '成功';
              } else if (item.account_sub_trade_tran_status === 1) {
                item.account_sub_trade_tran_statusC = '失败';
              } else if (item.account_sub_trade_tran_status === 2) {
                item.account_sub_trade_tran_statusC = '待确认';
              } else if (item.account_sub_trade_tran_status === 5) {
                item.account_sub_trade_tran_statusC = '待处理';
              } else if (item.account_sub_trade_tran_status === 6) {
                item.account_sub_trade_tran_statusC = '处理中';
              } else if (item.account_sub_trade_tran_status === 8) {
                item.account_sub_trade_tran_statusC = '成功';
              } else if (item.account_sub_trade_tran_status === -1) {
                item.account_sub_trade_tran_statusC = '无效';
              } else if (item.account_sub_trade_tran_status === 10) {
                item.account_sub_trade_tran_statusC = '提现退单';
              }

            })
            this.tableList = list;
            //            this.pageSettings.total = response.data.data.total
          }
        }).finally(() => {
          this.isShowLoadingIcon = false;
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
            this.balanceForm.cashAmtSave = response.data.data.cashAmt;            //可提现余额
            this.balanceForm.acctAvailBal = ""; //默认显示
            this.balanceForm.cashAmt = "";

            //            this.acctAvailBal = response.data.data.acctAvailBal || 0;
            //            this.cashAmt = response.data.data.cashAmt || 0;

            this.queryMoneyState();
          } else {
            Message.MessageError(response.msg)
          }
        })
      },
      // 点击流水单号查看详情页面
      viewDetails(row) {
        // 1-会员交易，2-转账，3-充值，4-提现，5-服务费，6-退款
        if (row.account_sub_trade_typeC.indexOf('交易') !== -1) {
          this.$router.push({
            name: '交易详情',
            params: row
          });
        } else if (row.account_sub_trade_typeC === '充值') {
          this.$router.push({
            name: '充值详情',
            params: row
          })
        } else if (row.account_sub_trade_typeC === '提现') {
          this.$router.push({
            name: '提现详情',
            params: row
          })
        } else if (row.account_sub_trade_typeC === '转账') {
          this.$router.push({
            name: '交易详情',
            params: row
          })
        }
      },
      getStatistics() {
        api.post('/trade/time/statistics').then(response => {
          if (response.data.code === 1) {
            this.statistics = response.data.data;
          } else {
            Message.MessageError(response.msg)
          }
        })
      },
      handleAvatarSuccess(res, file) {
        if (res.code === 1) {
          this.user.userHeadImg = res.data;
          sessionStorage.setItem('dleb_user', JSON.stringify(this.user));
        }
        //
      },
      beforeAvatarUpload(file) {
        const isJPG = (file.type === 'image/jpeg' || file.type === 'image/png');
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG,PNG 格式');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB');
        }
        return isJPG && isLt2M;
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

      queryMoneyState() {
        api.post('/bankCards/queryMoneyState', {}).then(response => {
          if (response.data.code === 1) {
            var data = response.data.data;
            if (data.length > 0) {
              if (data[0].account_money_state == "0") {
                this.isShow = false;
                this.isHide = true;
                this.balanceForm.acctAvailBal = this.balanceForm.acctAvailBalSave;
                this.balanceForm.cashAmt = this.balanceForm.cashAmtSave;
              } else {
                //隐藏余额
                this.isShow = true;
                this.isHide = false;
                this.balanceForm.acctAvailBal = "***";
                this.balanceForm.cashAmt = "***";
              }
            }
          }
        })
      },
      saveState(state) {
        api.post('/bankCards/saveState', {
          state: state
        }).then(response => {
          if (response.data.code === 1) {

          }
        })
      },

      showPayDialog() {
        this.payForm.money = null;
        this.dialogRegist = true;
        this.subAcct = this.subAccountList[0];
        this.payFormer.accountName = this.subAcct.accountSubName;
      },
      payMoney(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$router.push({
              path: '/UpTo',
              query: {money: this.payForm.money, fee: this.payForm.fee, remark: this.payForm.remark}
            });
          }
        });
      }
    },
    created() {
      window.sd = this;
    },
    mounted() {
      this.getData();
      this.getBalance();
      this.getStatistics();
    }
  }
</script>

<style scoped>

  .show-bottom-item {
    height: 150px;
    overflow: hidden;
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
  }

  .show-bottom-item.bg1 {
    background-image: url(../assets/img/front-bg1.png);
  }

  .show-bottom-item.bg2 {
    background-image: url(../assets/img/front-bg2.png);
  }

  .show-bottom-item.bg3 {
    background-image: url(../assets/img/front-bg3.png);
  }

  .show-bottom-item.bg4 {
    background-image: url(../assets/img/front-bg4.png);
  }

  .about-web-title,
  .about-web-content {
    float: left;
  }

  .about-web-title {
    width: 100%;
    height: 100%;
    padding-top: 30px;
    transition: .5s;
  }

  .about-web-content {
    width: 0%;
    height: 60px;
    transition: .5s;
  }

  .show-bottom-item.el-col.el-col-3 {
    width: 12.5%;
    transition: .5s;
  }

  .show-bottom-item.el-col.el-col-12 {
    width: 50%;
    transition: .5s;
  }

  .show-bottom-item.el-col.el-col-12 .about-web-title {
    width: 40%;
    transition: .5s;
  }

  .show-bottom-item.el-col.el-col-12 .about-web-content {
    width: 60%;
    color: #fff;
    height: 60px;
    text-align: left;
    font-size: 18px;
    line-height: 30px;
    vertical-align: middle;
    padding-top: 45px;
    transition: .5s;
  }

  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 85px;
    height: 85px;
    line-height: 85px;
    text-align: center;
  }

  .avatarUpload {
    width: 85px;
    height: 85px;
    display: block;
  }

  .showHide {
    color: #333;
    text-align: left;
    font-size: 13px;
    cursor: pointer;
  }
</style>
