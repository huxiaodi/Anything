<template>
  <div class="admin-input-form">
    <div class="divTitle">设置调账密码</div>
    <el-form ref="passwordSettingForm" class="admin-input-form" :model="passwordSettingForm" labelPosition="right" label-width="140px"
             :rules="rules">

      <div>
        <el-form-item label="设置调账密码:"  prop="userPayPassword">
          <el-input :disabled="disabledInfo" v-model="passwordSettingForm.userPayPassword"></el-input>
        </el-form-item>
        <el-form-item label="确认密码:" prop="checkPassword">
          <el-input :disabled="disabledInfo"  v-model="passwordSettingForm.checkPassword"></el-input>
        </el-form-item>
      </div>
      <div class="admin-input-form-btns">
        <el-button :disabled="disabledInfo" type="primary" @click="submitForm('passwordSettingForm')">提交</el-button>
      </div>
    </el-form>
  </div>
</template>

<script>
  import api from '@/api/fetch'
  import * as Message from '../utils/messageUtil'

  // 至少八位，小写字母、数字组合
  var letterAndNumberEight = /^(?=.*[a-z])(?=.*\d)[a-z\d]{8,}$/;
  // 至少八位，小写字母、数字、字符组合
  var allCheckEight = /^(?=.*[a-z])(?=.*\d)(?=.*[$@$!%*#?&])[a-z\d$@$!%*#?&]{8,}$/;
  export default {
    // 密码设置
    name: 'PasswordSetting',
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
        } else if (this.passwordSettingForm.checkPassword !== this.passwordSettingForm.userPayPassword) {
          callback(new Error('请确保两次输入一致'))
        } else {
          callback()
        }
      }
      return {
        disabledInfo: false,
        passwordSettingForm: {
          userPayPassword: '',
          checkPassword: ''
        },
        rules: {
          userPayPassword: [
            {required: true, validator: password, trigger: 'blur'}
          ],
          checkPassword: [
            {required: true, validator: checkPassword, trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      // 提交修改
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            api.post('admin/roleManage/setUserPayPassword', this.passwordSettingForm).then(response => {
              if (response.data.code === 1) {
                Message.MessageSuccess(response.data.msg)
              } else {
                Message.MessageError(response.data.msg)
              }
            })
          } else {
            return false;
          }
        });
      }
    },
    mounted() {
      api.get('admin/roleManage/checkUserPayPassword').then(response => {
        if (response.data.code === 1) {
          this.disabledInfo =  false;
          return true
        } else {
          this.disabledInfo =  true;
          Message.MessageError(response.data.msg)
          this.$emit('childCloseRouter','密码设置');
          this.$router.push('/PasswordEdit')
        }
      })
    }
  }
</script>
<style scoped>

</style>
