<template>
  <div class="admin-input-form">
    <div class="divTitle">个人信息</div>
    <div v-if="!this.isShowResetPassword" style="margin: 0 auto; width: 500px;">
      <div style="padding: 15px;margin-top: 5px;background-color: transparent;"></div>
      <el-form ref="form" :model="form" labelPosition="right">
        <el-row>
          <el-col :xs="24" :sm="20" :offset="4">
            <el-form-item label="用户名:">
              <div style="text-align: left">{{userInfo.userName}}</div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="24" :sm="20" :offset="4">
            <el-form-item label="真实姓名:">
              <div style="text-align: left">{{userInfo.userCnName}}</div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="24" :sm="20" :offset="4">
            <el-form-item label="联系电话:">
              <div style="text-align: left">{{userInfo.userMobile}}</div>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="24" :sm="20" :offset="4">
            <el-form-item label="关联角色:">
              <div style="text-align: left">{{userInfo.roleNames}}</div>
            </el-form-item>
          </el-col>
        </el-row>
        <div class="admin-input-form-btns">
        <el-button type="primary" size="medium" @click="resetPassword">重置密码</el-button>
        </div>

      </el-form>
    </div>
    <div v-if="this.isShowResetPassword" class="admin-input-form-content">
      <div style="font-size:16px;
      font-weight:bold;
      color:#555;
      margin:0 0 20px;">重置密码</div>
      <el-form ref="form" :model="form" :rules="rules" labelPosition="right" label-width="140px" size="medium">

            <el-form-item label="旧密码:" prop="userPassword">
              <el-input v-model="form.userPassword" type="password"></el-input>
            </el-form-item>

            <el-form-item label="新密码:" prop="newPassword">
              <el-input v-model="form.newPassword" type="password"></el-input>
            </el-form-item>

            <el-form-item label="确认密码:" prop="newPasswordConfirm">
              <el-input v-model="form.newPasswordConfirm" type="password"></el-input>
            </el-form-item>

            <el-form-item label="手机号:">
              <el-input v-model="form.mobile" readonly></el-input>
          </el-form-item>
            <div class="admin-input-form-btns">
              <el-button type="primary" style="margin-right:30px;" @click="confirmPassword">确定</el-button>
              <el-button type="primary" @click="canclePassword">取消</el-button>
            </div>
      </el-form>
    </div>
  </div>
</template>

<script>
  import api from '@/api/fetch'
  import * as Message from '../utils/messageUtil'

  var qs = require('qs');

  // 密码
  var passwordReg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9a-z]{8,50}$/;
  export default {
    name: 'PersonalCenter',
    data() {
      var validatePass = (rule, value, callback) => {
        if (value === '' || value === undefined) {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.form.newPassword) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        userInfo: {},
        form: {},
        rules: {
          userPassword: [
            {required: true, message: '旧密码不能为空', trigger: 'blur'},
            {min: 8, message: '密码至少8位', trigger: 'blur'}
          ],
          newPassword: [
            {required: true, message: '新密码不能为空', trigger: 'blur'},
            {pattern: passwordReg, message: '密码至少8位，支持数字、字母（小写）、字符，至少包含数字、字母组合'}
          ],
          newPasswordConfirm: [
            {required: true, message: '确认密码不能为空', trigger: 'blur'},
            {pattern: passwordReg, message: '密码至少8位，支持数字、字母（小写）、字符，至少包含数字、字母组合'},
            {validator: validatePass, trigger: 'blur'}
          ],
          verification: [{required: true, message: '验证码不能为空', trigger: 'blur'}]
        },
        // 获取验证码按钮
        sendMsgDisabled: false,
        time: 60,
        isShowResetPassword: false
      }
    },
    methods: {
      // 获取用户信息
      getData() {
        api.get('admin/userManage/getUserInfo').then(response => {
          if (response.data.code === 1) {
            this.userInfo = response.data.data.list[0]
          } else {
            Message.MessageError('用户信息获取失败')
          }
        })
      },
      // 重置密码
      resetPassword() {
        this.form = {};
        this.form.mobile = this.userInfo.userMobile;
        this.isShowResetPassword = true;
      },
      // 获取验证码
      getVerificationCode() {
        var mobile = this.form.mobile;
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
      },
      // 确定
      confirmPassword() {
        this.$refs['form'].validate((valid) => {
          if (valid) {
            let that = this;
            api.post('/user/changePassword', this.form).then(response => {
              if (response.data.code === 1) {
                Message.MessageSuccess('重置密码成功，请重新登录！')
                window.setTimeout(function () {
                  sessionStorage.removeItem('dleb_token');
                  sessionStorage.removeItem('dleb_user');
                  that.$router.push('/AdminLogin')
                }, 2000);
              } else {
                Message.MessageError(response.data.msg)
              }
            })
          } else {
            return false;
          }
        });
      },
      // 取消
      canclePassword() {
        this.isShowResetPassword = false;
      }
    },
    mounted() {
      this.getData()
    }
  }
</script>
<style scoped>

  /* 统一信用照片图片大小*/
  .avatar {
    width: 100%;
    height: 100%;
  }
</style>
