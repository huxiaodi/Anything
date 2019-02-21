<template>
  <div class="page1180">
    <div class="page-title">
      <div style="float:left;">
        <router-link to="/FrontHome">我的e宝</router-link>
        <i class="el-icon-arrow-right"></i>
        转账
      </div>
      <div style="float:right">
        <router-link to="/FeeDetailList/2" style="float: right;" v-show="_btn('fukuanzhuanz_transferRecord')">转账记录 </router-link>
      </div>
    </div>
    <el-row class="page-content" style="padding:25px 0;">
      <div class="one-column">
        <el-form ref="form" :model="form" :rules="rule" label-width="140px" v-show="currentShowIndex === 1">
          <el-form-item label="转出账户" prop="OutSubAcctName">
            <el-input v-model="form.OutSubAcctName" disabled></el-input>
          </el-form-item>
          <el-form-item label="转出账号" prop="OutSubAcctNo">
            <el-select clearable placeholder="请选择转出账户" v-model="form.OutSubAcctNo" @change="outSubAcctChange">
              <el-option v-for="item in outSubAcctList" :key="item.subAcctNo" :label="item.subAcctNo"
                         :value="item.subAcctNo">
                <span class="name">{{ item.subAcctNo }}</span>
                <span class="addr" v-if="item.accountType === 1">平安银行</span>
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="转账金额" prop="TranAmt">
            <el-input v-model="form.TranAmt" :controls="false" :min="0.01"><i slot="suffix">元</i></el-input>

          </el-form-item>
          <el-form-item label="转入账号" prop="InSubAcctNo">
            <el-select filterable remote clearable placeholder="请填写转入账号" :loading="inLoading"
                       :remote-method="getInSubAcct" value-key="subAcctNo" @blur="inSubAcctBlur" v-model="inSubAcct">
              <el-option v-for="item in inSubAcctList" :key="item.subAcctNo" :label="item.subAcctNo" :value="item">
                <span class="name">{{ item.subAcctNo }}</span>
                <span class="addr" v-if="item.accountType === 1">平安银行</span>
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="转入账户" prop="InSubAcctName">
            <el-input v-model="form.InSubAcctName" disabled></el-input>
          </el-form-item>
          <el-form-item label="手续费">
            {{form.TranFee}} 元
          </el-form-item>
          <el-form-item label="备注" prop="Remark">
            <el-input maxlength='60' v-model="form.Remark"></el-input>
          </el-form-item>
          <el-row style="width:100%;text-align:center">
            <el-button type="primary" @click="nextStep('form')">下一步</el-button>
          </el-row>
        </el-form>
        <div v-show="currentShowIndex === 2">
          <div class="payee">
            <div class="text-item">
              收款人信息:{{form.InSubAcctName}}
            </div>
            <div class="text-item">
              收款账户:{{form.InSubAcctNo}}
            </div>
            <div class="text-item">
              付款金额:{{form.TranAmt}} 元
              <span class="addr">含手续费{{form.TranFee}}元，对方实收<span
                style="color:#fe883e;">{{form.TranAmt - form.TranFee | numberFormat}}</span>
              元</span>
            </div>
          </div>
          <div class="btn-box">
            <el-button type="primary" @click="transfer" :loading="isShowLoadingIcon">确认转账</el-button>
            <a class="currentShowIndex" @click="currentShowIndex = 1">返回上一步</a>
          </div>
          <el-checkbox v-model="form.sendSMS">短信通知对方</el-checkbox>
        </div>
      </div>

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
      <iframe name="iFrame" style="width:100%;height:900px;
    position: absolute;left:0" v-show="currentShowIndex === 3"></iframe>
    </el-row>

  </div>
</template>

<script>
  import api from '@/api/fetch'
  import {
    MessageError,
    MessageSuccess
  } from '@/utils/messageUtil'
  import {
    AmountReg
  } from '@/common/constants'

  export default {
    name: 'TransferFee',
    data() {
      let checkAmount = (rule, value, callback) => {
        if (!value) {
          return callback(new Error('转账金额必填'));
        }
        if (!AmountReg.test(value)) {
          return callback(new Error('金额填写有误，仅支持最多17位的两位小数'));
        }
        if (value > this.maxTranAmt) {
          return callback(new Error(`该账户可用余额为${this.maxTranAmt}`));
        }
        this.form.TranFee = (value * (this.percent.replace("%", "") / 100)).toFixed(2)
        callback();
      };
      let checkInAcctNo = (rule, value, callback) => {
        if (!this.form.OutSubAcctNo) {
          return callback(new Error('请先选择转出账户'));
        }
        if (!this.value()) {
          return callback(new Error('转入账户必填'));
        }
        callback();
      };
      let checkInAcctName = (rule, value, callback) => {
        if (!this.inSubAcct || !this.inSubAcct.subAcctNo) {
          return callback(new Error('转入账户必填'));
        }
        callback();
      };
      return {
        currentShowIndex: 1,
        payForm: {},
        form: {
          OutSubAcctName: '',
          OutSubAcctNo: '',
          InSubAcctName: '',
          InSubAcctNo: '',
          TranFee: 0,
          Remark: '',
          sendSMS: true
        },
        outSubAcctList: [],
        inSubAcctNameList: [],
        inSubAcctList: [],
        inLoading: false,
        keywordLength: 0,
        inSubAcct: {},
        maxTranAmt: 0,
        isShowLoadingIcon: false,
        pollingFunction: null,
        tradeResult: '',
        percent: 0,
        countDown: 10,
        rule: {
          OutSubAcctName: [{
            required: true,
            message: '转出账户名必填',
            trigger: 'change'
          }],
          OutSubAcctNo: [{
            required: true,
            message: '转出账户必填',
            trigger: 'change'
          }],
          TranAmt: [{
            validator: checkAmount,
            trigger: 'change'
          }],
          InSubAcctName: [{
            required: true,
            message: '转入账户名必填',
            trigger: 'change'
          }],
          InSubAcctNo: [{
            required: true,
            message: '转入账户必填',
            validator: checkInAcctName,
            trigger: 'change'
          }]
        }
      }
    },
    methods: {
      transfer() {
        this.isShowLoadingIcon = true;
        api.post('/pay/transfer', this.form).then(response => {
          if (response.data.code === 1) {
            this.payForm = response.data.data;
            setTimeout(() => {
              this.$refs.PayForm.submit();
              this.polling(this.payForm.orderId);
            }, 500)
            this.currentShowIndex = 3;
          } else {
            MessageError(response.data.msg)
          }
        }).finally(() => {
          this.isShowLoadingIcon = false;
        })
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
      },
      inSubAcctBlur() {
        this.keywordLength = 0;
      },
      outSubAcctChange(value) {
        if (!value) {
          this.maxTranAmt = 0;
          return;
        }
        api.get('/account/userAccount/getAccountBalance/' + value).then(response => {
          if (response.data.code === 1) {
            this.maxTranAmt = response.data.data.acctAvailBal || 0;
          } else {
            MessageError(response.data.msg)
          }
        })
      },
      getInSubAcct(keyword) {
        if (!keyword || keyword.length === this.keywordLength) {
          this.keywordLength = 0;
          return;
        }
        this.keywordLength = keyword.length;
        if (!this.form.OutSubAcctNo) {
          MessageError('请先选择转出账户')
        }
        this.inLoading = true;
        api.get('/pay/transfer/getInSubAcct/', {
          params: {
            keyword: keyword,
            OutSubAcctNo: this.form.OutSubAcctNo
          }
        }).then(response => {
          this.inLoading = false;
          if (response.data.code === 1) {
            this.inSubAcctList = response.data.data;
          } else {
            MessageError(response.data.msg)
          }
        })
      },
      getInSubAcctName(keyword) {
        if (!keyword) {
          return;
        }
        this.inLoading = true;
        api.get('/pay/transfer/getOutSubAcctName/' + keyword).then(response => {
          this.inLoading = false;
          if (response.data.code === 1) {
            this.inSubAcctNameList = response.data.data;
          } else {
            MessageError(response.data.msg)
          }
        })
      },
      nextStep(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.currentShowIndex = 2;
          } else {
            return false;
          }
        });
      }
    },
    mounted() {
      api.get('/pay/transfer/getInitData').then(response => {
        if (response.data.code === 1) {
          this.form.OutSubAcctName = response.data.data.accountName;
          this.outSubAcctList = response.data.data.accountList;
          if (this.outSubAcctList && this.outSubAcctList.length === 1) {
            this.form.OutSubAcctNo = this.outSubAcctList[0].subAcctNo;
            this.outSubAcctChange(this.form.OutSubAcctNo)
          }
        } else {
          MessageError(response.data.msg)
        }
      });
      api.get('/pay/transfer/getPercent').then(response => {
        if (response.data.code === 1) {
          this.percent = response.data.data.dictValue
        } else {
          MessageError(response.data.msg)
        }
      })
    },
    filters: {
      numberFormat: function (value) {
        return value.toFixed(2);
      }
    },
    watch: {
      inSubAcct(newValue) {
        this.form.InSubAcctName = newValue ? newValue.userAccountName : '';
        this.form.InSubAcctNo = newValue ? newValue.subAcctNo : '';
      }
    }
  }
</script>

<style scoped>
  .name {
    text-overflow: ellipsis;
    overflow: hidden;
  }

  .addr {
    font-size: 12px;
    color: #b4b4b4;
  }

  .payee .text-item {
    margin-bottom: 30px;
  }

  {

  }
  .btn-box {
    height: 40px;
    margin-bottom: 10px
  }

  .currentShowIndex {
    vertical-align: bottom;
    cursor: pointer;
    margin-left: 20px;

  }
</style>
