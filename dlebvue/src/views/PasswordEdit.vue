<template>
  <div class="admin-input-form">
    <div class="divTitle">修改调账密码</div>
    <div>
      <el-form ref="passwordEditForm" :rules="passwordEditRules" :model="passwordEditForm"
               label-width="140px" labelPosition="right" >
        <el-form-item label="原密码:" prop="oldPassword">
          <el-input type="password" v-model="passwordEditForm.oldPassword"></el-input>
        </el-form-item>
        <el-form-item label="设置调账密码:" prop="userPayPassword">
          <el-input type="password" v-model="passwordEditForm.userPayPassword"></el-input>
        </el-form-item>
        <el-form-item label="确认密码:" prop="checkPassword">
          <el-input type="password" v-model="passwordEditForm.checkPassword"></el-input>
        </el-form-item>
        <el-form-item label="手机号:" prop="mobile">
          <el-input v-model="passwordEditForm.mobile"></el-input>
        </el-form-item>

        <el-form-item label="">
          <div ref="c1"></div>
        </el-form-item>
        <el-form-item label="手机验证码:" class="admin-input-verification" prop="userVerificationCode">
          <el-input type="text" v-model="passwordEditForm.userVerificationCode" ></el-input>
          <el-button type="primary" :disabled="this.passwordEditForm.tokenCheck"
                     @click="getVerification(passwordEditForm.mobile)" id="getNum">
            获取验证码
          </el-button>
        </el-form-item>
        <div class="admin-input-form-btns">
          <el-button type="primary"
                     @click="submitForm('passwordEditForm')">提交
          </el-button>
        </div>

      </el-form>
    </div>
  </div>
</template>

<script>
  import api from '@/api/fetch'
  import * as Message from '../utils/messageUtil'
  import _dx from '_dx'

  var qs = require('qs');
  // 手机号
  var mobileReg = /^1[34578]\d{9}$/;
  // 至少八位，小写字母、数字组合
  var letterAndNumberEight = /^(?=.*[a-z])(?=.*\d)[a-z\d]{8,}$/;
  // 至少八位，小写字母、数字、字符组合
  var allCheckEight = /^(?=.*[a-z])(?=.*\d)(?=.*[$@$!%*#?&])[a-z\d$@$!%*#?&]{8,}$/;

  export default {
    // 密码修改
    name: 'PasswordEdit',
    data() {
      var password = (rule, value, callback) => {
        if (!value) {
          callback(new Error('请输入密码'))
        } else if (!(letterAndNumberEight.test(value) || allCheckEight.test(value))) {
          callback(new Error('至少八位，数字，小写字母，字符组合，至少包含数字及小写字母'))
        } else {
          callback()
        }
      }
      var checkPassword = (rule, value, callback) => {
        if (!value) {
          callback(new Error('请确认密码'))
        } else if (this.passwordEditForm.checkPassword !== this.passwordEditForm.userPayPassword) {
          callback(new Error('请确保两次输入一致'))
        } else {
          callback()
        }
      }
      return {
        passwordEditForm: {
          tokenCheck: true,
          oldPassword: '',
          userPayPassword: '',
          checkPassword: '',
          mobile: '',
          userVerificationCode: ''
        },
        token: '',
        passwordEditRules: {
          oldPassword: [
            {required: true, message: '请输入原密码', trigger: 'blur'}
          ],
          userPayPassword: [
            {required: true, validator: password, trigger: 'blur'}
          ],
          checkPassword: [
            {required: true, validator: checkPassword, trigger: 'blur'}
          ],
          mobile: [
            {required: true, message: '手机号不能为空', trigger: 'blur'},
            {pattern: mobileReg, message: '请输入正确的手机号'}
          ],
          userVerificationCode: [
            {required: true, message: '验证码不能为空', trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      //  获取短信验证码
      getVerification(mobile) {
        api.post('admin/roleManage/getVerification', {
          mobile: this.passwordEditForm.mobile,
          token: this.token,
          oldPassword: this.passwordEditForm.oldPassword
        }).then(response => {
          if (response.data.code === 1) {
            Message.MessageSuccess(response.data.msg);
            var validCode = true;
            var time = 60;
            if (validCode) {
              validCode = false;
              this.passwordEditForm.tokenCheck = true
              var th = this
              var t = setInterval(function () {
                time--;
                document.getElementById('getNum').innerHTML = '等待' + time + '秒';
                if (time === 0) {
                  clearInterval(t);
                  validCode = true;
                  _dx.Captcha(th.$refs.c1, {
                    appId: 'e9a1d305fc43f48f94ea8ae35ed93be2', //  appId,开通服务后可在控制台中“服务管理”模块获取
                    style: 'inline',
                    success: function (token) {
                      th.token = token
                      th.passwordEditForm.tokenCheck = false
                    }
                  }).reload()
                  document.getElementById('getNum').innerHTML = '重新获取';
                  th.passwordEditForm.tokenCheck = false
                }
              }, 1000);
            }
          } else {
            Message.MessageError(response.data.msg);
            this.passwordEditForm.tokenCheck = true
            var that = this
            _dx.Captcha(that.$refs.c1, {
              appId: 'e9a1d305fc43f48f94ea8ae35ed93be2', //  appId,开通服务后可在控制台中“服务管理”模块获取
              style: 'inline',
              success: function (token) {
                that.token = token
                that.passwordEditForm.tokenCheck = false
              }
            }).reload()
          }
        });
      },
      // 提交修改
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            api.post('admin/roleManage/changeUserPayPassword', this.passwordEditForm).then(response => {
              if (response.data.code === 1) {
                Message.MessageSuccess(response.data.data);
              } else {
                Message.MessageError(response.data.msg);
              }
            });
          } else {
            return false;
          }
        });
      }
    },
    mounted() {
      var that = this
      _dx.Captcha(this.$refs.c1, {
        appId: 'e9a1d305fc43f48f94ea8ae35ed93be2', //  appId,开通服务后可在控制台中“服务管理”模块获取
        style: 'inline',
        success: function (token) {
          that.token = token
          that.passwordEditForm.tokenCheck = false
        }
      }).reload()
    }
  }
</script>
<style scoped>
</style>
