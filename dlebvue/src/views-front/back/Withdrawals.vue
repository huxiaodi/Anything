<template>
  <div class="page1180">
    <div class="page-title">
      <div style="float:left;">
        <router-link to="/FrontHome">我的e宝</router-link>
        <i class="el-icon-arrow-right"></i>
        提现
      </div>
      <div style="float:right">
        <router-link to="/FeeDetailList/4" style="float: right;">提现记录</router-link>
      </div>
    </div>
    <el-row class="page-content">
      <el-form class="one-column" ref="form" :model="form" :rules="rule" label-width="140px"
               v-show="currentShowIndex === 1">
        <el-form-item label="提现子账户" prop="SubAcctNo">
          <el-select style="width:100%;" clearable placeholder="请选择账户" v-model="form.SubAcctNo">
            <el-option v-for="item in outSubAcctList" :key="item.subAcctNo" :label="item.subAcctNo"
                       :value="item.subAcctNo">
              <span class="name">{{ item.subAcctNo }}</span>
              <span class="addr" v-if="item.accountType === 1">平安银行</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="提现子账户名" prop="SubAcctName">
          <el-input v-model="form.SubAcctName" disabled></el-input>
        </el-form-item>
        <el-form-item label="转入账户" prop="TakeCashAcctNo">
          <el-select style="width:100%;" clearable placeholder="请选择转入账户" value-key="bankCardId"
                     no-data-text="尚未绑卡，请先绑定银行卡" v-model="inCard">
            <el-option v-for="item in bindCardList" :key="item.bankCardId" :label="item.bankCardNo" :value="item">
              <span class="name">{{item.bankCardBank}}{{ item.bankCardNo }}</span>
            </el-option>
          </el-select>
          <router-link to="/AddBankCard">添加提现账户</router-link>
        </el-form-item>
        <el-form-item label="提现金额" prop="ApplyTakeCashAmt">
          <el-input style="width:100%;" v-model="form.ApplyTakeCashAmt" :controls="false"
                    :min="1" ref="amount"><i slot="suffix">元</i></el-input>
        </el-form-item>
        <el-form-item label="手续费">
          {{form.TakeCashCommission}} 元
        </el-form-item>
        <div style="width:100%;text-align:center;">
          <el-button type="primary" @click="withdrawal('form')" :loading="isShowLoadingIcon">立即提现</el-button>
        </div>

      </el-form>

      <iframe name="iFrame" style="width: 100%;height: 900px;position: absolute;left:0"
              v-show="currentShowIndex === 2"></iframe>
      <div v-show="false">
        <form target="iFrame" ref="PayForm" :action="payForm.frontUrl"
              METHOD="POST">
          <input type="hidden" v-model="payForm.orderId" name="orderid" ref="orderid">
          <!—本次交易流水号 -->
          <input type="hidden" v-model="payForm.type" name="type">
          <!-- S为设置支付密码，C为修改支付密码，R为重置支付密码，V为验证支付密码 M为修改手机号码-->
          <input type="hidden" v-model="payForm.p2PCode" name="P2PCode">
          <!—平台代码 -->
          <input type="hidden" v-model="payForm.p2PType" name="P2PType">
          <!—平台类型 -->
          <input type="hidden" v-model="payForm.thirdCustId" name="thirdCustId">
          <!—会员代码 -->
          <input type="hidden" v-model="payForm.custAccId" name="custAccId">
          <!—会员子账号 -->
          <input type="hidden" v-model="payForm.name" name="name">
          <!—会员名称 -->
          <input type="hidden" v-model="payForm.idType" name="idType" ref="idType">
          <!—会员证件类型 -->
          <input type="hidden" v-model="payForm.idNo" name="idNo">
          <!—会员证件号码 -->
          <input type="hidden" v-model="payForm.mobile" name="mobile">
          <!—会员手机号 -->
          <input type="hidden" v-model="payForm.orig" name="orig">
          <!—签名原数据串 -->
          <input type="hidden" v-model="payForm.returnurl " name="returnurl">
          <!—回调第三方URL -->
          <input type="hidden" v-model="payForm.notifyUrl" name="notifyUrl">
          <!—回调第三方URL -->
          <input type="hidden" v-model="payForm.channelType" name="channelType">
          <!—渠道类型 -->
          <input type="submit">
        </form>
      </div>

      <div v-show="currentShowIndex === 4">
        <el-card class="box-card">
          <div class="text item">
            {{tradeResult}}
          </div>
          <div class="text item">
            {{countDown}}秒后跳转至首页
          </div>
        </el-card>
      </div>
    </el-row>

  </div>
</template>

<script>
  import api from '@/api/fetch'
  import {MessageError, MessageSuccess} from '@/utils/messageUtil'
  import {AmountReg} from '@/common/constants'

  export default {
    name: 'Withdrawals',
    data() {
      let checkAmount = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('提现金额必填'));
        }
        if (!AmountReg.test(value)) {
          return callback(new Error('金额填写有误，仅支持最多17位的两位小数'));
        }
        if (value > this.maxTranAmt) {
          return callback(new Error(`该账户可提现金额为${this.maxTranAmt}`));
        }
        callback();
      };
      let checkSubAcctNo = (rule, value, callback) => {
        if (!this.form.SubAcctNo) {
          return callback(new Error('提现子账户必填'));
        }
        callback();
      };
      let checkTakeCashAcctNo = (rule, value, callback) => {
        if (!this.inCard || !this.inCard.bankCardNo) {
          return callback(new Error('转入账户必填'));
        }
        callback();
      };
      return {
        currentShowIndex: 1,
        maxTranAmt: 0,
        inSubAcctList: [],
        outSubAcctList: [],
        currentAccount: [],
        bindCardList: [],
        inCard: {
          bankCardId: '',
          bankCardNo: '',
          bankCardBank: ''
        },
        isShowLoadingIcon: false,
        form: {
          SubAcctNo: '',
          TakeCashCommission: 0
        },
        payForm: {},
        tradeResult: '',
        countDown: 10,
        rule: {
          SubAcctNo: [{
            required: true,
            message: '提现子账户必填',
            trigger: 'change'
          },
            {validator: checkSubAcctNo, trigger: 'change'}
          ],
          ApplyTakeCashAmt: [{
            validator: checkAmount,
            trigger: 'change'
          }],
          TakeCashAcctNo: [{
            validator: checkTakeCashAcctNo,
            trigger: 'change'
          }],
          InSubAcctNo: [
            //            {required: true, message: '转入账户必填', validator: checkInAcctName, trigger: 'change'}
          ]
        }
      }
    },
    methods: {
      outSubAcctChange(newValue) {
        if (!newValue) {
          this.maxTranAmt = 0;
        } else {
          api.get('/account/userAccount/getAccountBalance/' + newValue).then(response => {
            if (response.data.code === 1) {
              this.maxTranAmt = response.data.data.cashAmt || 0;
            } else {
              MessageError(response.data.msg)
            }
          })
        }
      },
      withdrawal(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.isShowLoadingIcon = true;
            api.post('/pay/withdrawal', this.form).then(response => {
              if (response.data.code === 1) {
                this.payForm = response.data.data;
                setTimeout(() => {
                  this.$refs.PayForm.submit();
                  this.polling(this.payForm.orderId);
                }, 500)
                this.currentShowIndex = 2;
              } else {
                MessageError(response.data.msg)
              }
            }).finally(() => {
              this.isShowLoadingIcon = false;
            })
          } else {
            return false;
          }
        });
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
              } else if (result.accountSubTradeTranStatus === 6) {
                this.tradeResult = '交易处理中'
              } else {
                return;
              }
              this.currentShowIndex = 4;
              window.clearInterval(this.pollingFunction);
              this.startCountDown();
            } else {
              MessageError(response.data.msg)
            }
          })
        }, 5000)
      },
      startCountDown() {
        setInterval(() => {
          if (this.countDown === 0) {
            this.$router.push('/FrontHome');
          }
          this.countDown--;
        }, 1000)
      }
    },
    mounted() {
      api.get('/pay/withdrawal/getInitData').then(response => {
        if (response.data.code === 1) {
          this.form.SubAcctName = response.data.data.accountName;
          this.outSubAcctList = response.data.data.accountList;
          if (this.outSubAcctList && this.outSubAcctList.length > 0) {
            this.currentAccount = this.outSubAcctList[0];
          }
        } else {
          MessageError(response.data.msg)
        }
      })
    },
    watch: {
      inCard(newValue) {
        this.form.TakeCashAcctNo = newValue ? newValue.bankCardNo : '';
      },
      currentAccount(newValue, oldValue) {
        this.form.SubAcctNo = newValue ? newValue.subAcctNo : '';
        this.outSubAcctChange(this.form.SubAcctNo);
        if (!newValue || !newValue.subAcctNo) {
          return;
        }
        if ((!oldValue || oldValue.accountType !== newValue.accountType) && (!this.bindCardList || this.bindCardList.length < 1)) {
          api.get('/pay/withdrawal/getBindList/' + newValue.subAcctNo).then(response => {
            if (response.data.code === 1) {
              this.bindCardList = response.data.data;
              // 从银行管理提现过来时，把银行卡号自动填充到转入账户上
              let obj = this.$route.params;
              this.bindCardList.forEach((item) => {
                if (obj.memberAcctNo === item.bankCardNo) {
                  this.inCard.bankCardId = obj.bankCardId;
                  this.inCard.bankCardNo = obj.memberAcctNo;
                  this.inCard.bankCardBank = obj.bankCardBank;
                }
              })
            } else {
              MessageError(response.data.msg)
            }
          })
        }
      },
      maxTranAmt(newValue) {
        this.$refs.amount.$el.getElementsByTagName('input')[0].setAttribute('placeholder', `当前用户可提现余额${newValue}元`);
      }
    }
  }
</script>
