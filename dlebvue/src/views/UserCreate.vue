<template>
  <div>
    <el-container style="border-bottom: black 1px solid">
      <el-aside style="border-right:black 1px solid;width:30%">
        <el-form ref="userCreateForm" labelWidth="80px" labelPosition="right" style="height: 100%"
                 :model="userCreateForm" :rules="userCreateRule">
          <div style="font-size: large;color: #FF9933">用户信息</div>
          <el-form-item label="用户名" prop="userName">
            <el-input v-model="userCreateForm.userName"></el-input>
          </el-form-item>
          <el-form-item label="手机号" prop="userMobile">
            <el-input v-model="userCreateForm.userMobile"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="userCreateForm.password"></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="checkPassword">
            <el-input v-model="userCreateForm.checkPassword"></el-input>
          </el-form-item>
          <hr style="background-color: black"/>
          <div style="font-size: large;color: #FF9933">选择角色</div>
          <el-form-item>
            <el-checkbox style="margin-right: 200px">管理员</el-checkbox>
          </el-form-item>
          <el-form-item>
            <el-checkbox style="margin-right: 185px">客服人员</el-checkbox>
          </el-form-item>
          <hr style="background-color: black"/>
          <div style="font-size: large;color: #FF9933">选择组</div>
          <el-form-item>
            <el-checkbox style="margin-right: 200px">全能组</el-checkbox>
          </el-form-item>
          <el-form-item>
            <el-checkbox style="margin-right: 145px">德邻畅途平安组</el-checkbox>
          </el-form-item>
        </el-form>
      </el-aside>
      <el-main>
        <div style="height: 100%">
          <div style="font-size: large;color: #FF9933;text-align: left;margin-left: 10px">功能权限展示</div>
        </div>
      </el-main>
    </el-container>
    <el-button type="primary" style="margin-top: 10px;width: 200px" @click="submitForm('userCreateForm')">提交</el-button>
  </div>
</template>

<script>
  import api from '../api/fetch'
  import {parseTime} from '../utils/index'
  import * as Message from '../utils/messageUtil'

  // 至少六位，小写字母+数字
  var letterAndNumberSix = /^(?=.*[a-z])(?=.*\d)[a-z\d]{6,}$/;
  // 至少六位，小写字母、数字、字符组合
  var allCheckSix = /^(?=.*[a-z])(?=.*\d)(?=.*[$@$!%*#?&])[a-z\d$@$!%*#?&]{6,}$/;
  // 至少六位，字符+数字
  var charAndNumberSix = /^(?=.*[$@$!%*#?&])[a-z\d$@$!%*#?&]{6,}$/;
  // 纯数字
  var numberReg = /^[0-9]*$/;
  // 至少八位，小写字母、数字组合
  var letterAndNumberEight = /^(?=.*[a-z])(?=.*\d)[a-z\d]{8,}$/;
  // 至少八位，小写字母、数字、字符组合
  var allCheckEight = /^(?=.*[a-z])(?=.*\d)(?=.*[$@$!%*#?&])[a-z\d$@$!%*#?&]{8,}$/;
  // 手机号
  var mobileReg = /^1[34578]\d{9}$/;

  export default {
    // 创建用户
    name: 'UserCreate',
    data() {
      var userName = (rule, value, callback) => {
        debugger
        if (!value) {
          callback(new Error('请输入用户名'))
        } else if (numberReg.test(value[0])) {
          callback(new Error('不能以数字开头'))
        } else if (!(letterAndNumberSix.test(value) || allCheckSix.test(value) || charAndNumberSix.test(value))) {
          callback(new Error('请输入正确的用户名格式'))
        } else {
          callback()
        }
      }
      var password = (rule, value, callback) => {
        if (!value) {
          callback(new Error('请输入密码'))
        } else if (!(letterAndNumberEight.test(value) || allCheckEight.test(value))) {
          callback(new Error('请输入正确的密码格式'))
        } else {
          callback()
        }
      }
      var checkPassword = (rule, value, callback) => {
        if (!value) {
          callback(new Error('请确认密码'))
        } else if (this.userCreateForm.checkPassword !== this.userCreateForm.password) {
          callback(new Error('请确保两次输入一致'))
        } else {
          callback()
        }
      }
      return {
        userCreateForm: {
          userId: '',
          userName: '',
          password: '',
          checkPassword: '',
          userMobile: ''
        },
        userCreateRule: {
          userName: [{required: true, validator: userName, trigger: 'blur'}],
          password: [{required: true, validator: password, trigger: 'blur'}],
          checkPassword: [{required: true, validator: checkPassword, trigger: 'blur'}],
          userMobile: [{required: true, message: '手机号不能为空', trigger: 'blur'},
            {pattern: mobileReg, message: '请输入正确的手机号'}]
        }
      }
    },
    methods: {
      // 获取个人用户信息
      getMemberInfo() {
        api.post('admin/userManage/user/info', {'id': this.$route.params.id}).then(response => {
          if (response.data.code === 1) {
            this.userCreateForm = response.data.data
          }
        })
      },
      // 提交修改
      submitForm(formName) {
        var formData = new FormData();
        this.$refs[formName].validate((valid) => {
          if (valid) {
            api.post('admin/userManage/user/save', this.userCreateForm).then(response => {
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
      this.getMemberInfo()
    }
  }
</script>

<style scoped>
  .el-form-item {
    margin-left: 20px;
  }

  /* .el-input {
    width: 80%;
  } */
</style>
