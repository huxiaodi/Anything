<template>
  <div class="admin-input-form">
    <div class="divTitle">充值调账</div>

    <el-form ref="ledgerAccountForm" :rules="ledgerAccountFormRules" :model="ledgerAccountForm" labelWidth="100px"
             labelPosition="right">
      <el-row>
        <el-col :xs="24" :sm="20" :offset="1">
          <el-form-item label="充值卡号:">
            <div style="text-align: left">{{ledgerAccountForm.accountSubTradeBankNum}}</div>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :xs="24" :sm="20" :offset="1">
          <el-form-item label="充值途径:">
            <div style="text-align: left">{{ledgerAccountForm.accountSubTradeTypeC}}</div>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :xs="24" :sm="20" :offset="1">
          <el-form-item label="充值金额:">
            <div style="text-align: left">{{ledgerAccountForm.accountSubTradeTranAmt}}</div>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row style="height: 62px">
        <el-col :xs="24" :sm="20" :offset="1">
          <el-form-item label="调入账户:" prop="accountSubId" ref="item1">
            <el-select style="width: 100%" v-model="ledgerAccountForm.accountSubId" clearable filterable>
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.userAccountName"
                :value="item.subAcctId">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row style="height: 62px">
        <el-col :xs="24" :sm="20" :offset="1">
          <el-form-item label="调入账号:" prop="accountSubId" ref="item2">
            <el-select style="width: 100%" v-model="ledgerAccountForm.accountSubId" clearable filterable>
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.subAcctNo"
                :value="item.subAcctId">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row style="height: 62px">
        <el-col :xs="24" :sm="20" :offset="1">
          <el-form-item label="调账密码:" prop="password">
            <el-input type="password" v-model="ledgerAccountForm.password" clearable></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :xs="24" :sm="20" :offset="1">
          <el-form-item label="备注:" prop="remark">
            <el-input v-model="ledgerAccountForm.remark" clearable></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col align="center">
          <el-button type="primary" @click="comfirmLedgerAccount('ledgerAccountForm')" :disabled="this.isDisabled">
            确认调账
          </el-button>
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script>
  import api from '../api/fetch'
  import {parseTime} from '../utils/index'
  import * as Message from '../utils/messageUtil'

  // 至少八位，小写字母、数字组合
  var letterAndNumberEight = /^(?=.*[a-z])(?=.*\d)[a-z\d]{8,}$/;
  // 至少八位，小写字母、数字、字符组合
  var allCheckEight = /^(?=.*[a-z])(?=.*\d)(?=.*[$@$!%*#?&])[a-z\d$@$!%*#?&]{8,}$/;

  export default {
    // 子账户调账
    name: 'LedgerAccountAdjust',
    data() {
      var validateaccountSubId = (rule, value, callback) => {
        if (!value) {
          callback(new Error('请选择调入账户、账号'))
        } else {
          callback()
        }
      }
      var password = (rule, value, callback) => {
        if (!value) {
          callback(new Error('请输入密码'))
        } else if (!(letterAndNumberEight.test(value) || allCheckEight.test(value))) {
          callback(new Error('至少八位，数字，小写字母，字符组合，至少包含数字及小写字母'))
        } else {
          callback()
        }
      }
      return {
        ledgerAccountForm: {
          accountSubTradeId: '',
          accountSubId: ' ',
          password: '',
          remark: ''
        },
        ledgerAccountFormRules: {
          accountSubId: [
            {validator: validateaccountSubId, trigger: 'change'}
          ],
          password: [
            {validator: password, trigger: 'blur'}
          ]
        },
        options: [],
        keyword: '',
        // 确认调账按钮是否禁用
        isDisabled: false
      }
    },
    methods: {
      // 获取个人用户信息
      getLedgerAccountInfo() {
        api.get('/masterAccount/getAllAccountSub').then(response => {
          if (response.data.code === 1) {
            this.options = response.data.data;
          } else {
            Message.MessageError(response.data.msg)
          }
        })
      },
      checkAdjustPsw() {
        api.get('/user/checkAdjustPsw').then(response => {
          if (response.data.code === 1) {
            if(!response.data.data){
              this.$alert('调账密码未设置,点击确定,跳转到《调账密码设置》', '提示', {
                confirmButtonText: '确定',
                callback: action => {
                  this.$router.push("/PasswordSetting");
                }
              });
            }
          } else {
            Message.MessageError(response.data.msg)
          }
        })
      },
      comfirmLedgerAccount(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            api.post('/masterAccount/adjustAccountSub', this.ledgerAccountForm).then(response => {
              if (response.data.code === 1) {
                this.isDisabled = true;
                Message.MessageSuccess(response.data.msg);
                this.$emit('childRefreshRouter','LedgerAccountList');
                this.$emit('childCloseRouter','子账户调账');
                this.$router.push("/LedgerAccountList");
              } else {
                if(response.data.msg.indexOf("调账密码未设置") != -1){
                  this.$alert('调账密码未设置,点击确定,跳转到《调账密码设置》', '提示', {
                    confirmButtonText: '确定',
                    callback: action => {
                        this.$router.push("/PasswordSetting");
                    }

                  });
                }else{
                  Message.MessageError(response.data.msg);
                }

              }
            });
          } else {
            return false;
          }
        });
      }
    },
    mounted() {
      this.ledgerAccountForm = JSON.parse(JSON.stringify(this.$route.params));
      this.getLedgerAccountInfo();
      this.checkAdjustPsw();
    },
    watch: {
      'ledgerAccountForm.accountSubId'(newval) {
        if (newval === ' '){
          return;
        }
        this.$refs.item2.onFieldChange();
        this.$refs.item1.onFieldChange();
      }
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
