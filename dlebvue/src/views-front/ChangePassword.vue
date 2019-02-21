<template>
  <div class="page1180">
    <div class="page-title">
      <div style="float:left;">
        <router-link to="/AccountCentral">账户中心</router-link>
        <i class="el-icon-arrow-right"></i>
        修改登录密码
      </div>
    </div>
    <div class="one-column">
      <el-form ref="passwordForm" :model="passwordForm" :rules="rules"
               label-width="140px" class="form">

        <el-row>
          <el-form-item label="联系人手机号">
            <el-input v-model="passwordForm.mobile" :readonly="true"></el-input>
          </el-form-item>
        </el-row>

        <el-row>
          <el-form-item label="验证码" prop="verification">
              <el-input class="column-verification-input" v-model="passwordForm.verification"></el-input>
              <el-button size="medium" :disabled="sendMsgDisabled || isDisabled" type="primary" @click="getVerificationCode"style="min-width:80px;" class="column-verification-btn">
                <span v-if="sendMsgDisabled">{{time+'秒后再次获取'}}</span>
                <span v-if="!sendMsgDisabled">获取验证码</span>
              </el-button>
          </el-form-item>
        </el-row>

        <el-row>
          <el-form-item label="旧密码" prop="userPassword">
            <el-input type="password" v-model="passwordForm.userPassword"></el-input>
          </el-form-item>
        </el-row>

        <el-row>
          <el-form-item label="新密码" prop="newPassword">
            <el-input type="password" v-model="passwordForm.newPassword"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="确认密码" prop="newPasswordConfirm">
            <el-input type="password" v-model="passwordForm.newPasswordConfirm"></el-input>
          </el-form-item>
        </el-row>
        <el-row style="width:100%;text-align:center">
          <el-button style="margin:20px 25px 20px;display:inline-block;width:80px" type="primary" :disabled="isDisabled" @click="passwordFormSubmit">提交
          </el-button>
          <el-button type="primary " :disabled="isDisabled" @click="cancel" style="margin:20px 25px 20px;display:inline-block;width:80px">取消</el-button>
        </el-row>
      </el-form>
    </div>
  </div>
</template>

<script>
  import api from '@/api/fetch'
  import * as Message from '../utils/messageUtil'

  var qs = require('qs');
  // 手机号
  var mobileReg = /^1[34578]\d{9}$/;
  // 密码
  var passwordReg = /^(?=[^A-Z\u4e00-\u9fa5]*[0-9])(?=[^A-Z\u4e00-\u9fa5]*[a-z])[^A-Z\u4e00-\u9fa5]{8,50}$/;

  export default {
    name: 'changePassword',
    data() {
      var validatePass = (rule, value, callback) => {
        if (value === '' || value === undefined) {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.passwordForm.newPassword) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        isDisabled: false,
        sendMsgDisabled: false,
        time: 60,
        passwordForm: {
          mobile: '',
          verification: '',
          userPassword: '',
          newPassword: '',
          newPasswordConfirm: ''
        },
        rules: {
          verification: [
            {required: true, message: ' ', trigger: 'blur'}
          ],
          userPassword: [
            {required: true, message: '请输入旧密码', trigger: 'blur'},
            {pattern: passwordReg, message: '密码至少8位，支持数字、字母（小写）、字符，至少包含数字、字母组合'}
          ],
          newPassword: [
            {required: true, message: '请输入新密码', trigger: 'blur'},
            {pattern: passwordReg, message: '密码至少8位，支持数字、字母（小写）、字符，至少包含数字、字母组合'}
          ],
          newPasswordConfirm: [
            {required: true, message: '请输入确认密码', trigger: 'blur'},
            {pattern: passwordReg, message: '密码至少8位，支持数字、字母（小写）、字符，至少包含数字、字母组合'},
            {validator: validatePass, trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      // 获取验证码
      getVerificationCode() {
        var mobile = this.passwordForm.mobile;
        if (mobile && mobileReg.test(mobile)) {
          api.post('/user/register/sendVerificationCode', qs.stringify({mobile: mobile})).then(response => {
            if (response.data.code === 1) {
              Message.MessageSuccess(response.data.data);
              // 1分钟后再次点击获取验证码
              let me = this;
              me.sendMsgDisabled = true;
              let interval = window.setInterval(function () {
                if ((me.time--) <= 0) {
                  me.time = 60;
                  me.sendMsgDisabled = false;
                  window.clearInterval(interval);
                }
              }, 1000);
            } else {
              Message.MessageError(response.data.msg)
            }
          })
        } else {
          Message.MessageError('请填写正确的手机号')
        }
      },
      // 提交修改
      passwordFormSubmit() {
        this.$refs['passwordForm'].validate((valid) => {
          if (valid) {
            api.post('/user/changePassword', this.passwordForm).then(response => {
              if (response.data.code === 1) {
                this.isDisabled = true;
                this.$refs['passwordForm'].resetFields()
                var router = this.$router;
                this.$message({
                  message: response.data.data,
                  type: 'success',
                  duration: 2000,
                  onClose: function () {
                    localStorage.clear();
                    router.push('/login');
                  }
                });
              } else {
                Message.MessageError(response.data.msg)
              }
            })
          } else {
            return false;
          }
        })
      },
      //  取消
      cancel() {
        this.$refs['passwordForm'].resetFields()
        this.$router.push('/AccountCentral');
      }
    },
    mounted() {
      this.passwordForm.mobile = this.$route.params.mobile;
    }
  }
</script>

<style>

</style>
