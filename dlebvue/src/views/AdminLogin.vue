<template>
  <div class="admin-login">
    <header>
    </header>
    <aside class="left"></aside>
    <aside class="right"></aside>
    <article>
      <div class="login-box">
        <span class="login-box-title">用户登录</span>
        <el-form :model="loginForm" ref="loginForm" :rules="formRules">
          <el-form-item prop="userName">
            <el-input placeholder="用户名" v-model="loginForm.userName"></el-input>
          </el-form-item>
          <el-form-item prop="userPassword">
            <el-input type="password" placeholder="密码" v-model="loginForm.userPassword"></el-input>
          </el-form-item>
          <div class="login-button">
            <el-button type="primary" @click="submitForm('loginForm')">登 录</el-button>
          </div>
        </el-form>

      </div>

    </article>
    <footer>
    </footer>

  </div>
</template>

<script>
  import api from '@/api/fetch'
  import * as Message from '../utils/messageUtil'

  export default {
    name: 'AdminLogin',
    data() {
      return {
        loginForm: {
          userName: '',
          userPassword: ''
        },
        formRules: {
          userName: [
            {required: true, message: '用户名不能为空', trigger: 'blur'}
          ],
          userPassword: [
            {required: true, message: '密码不能为空', trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.loginForm.userType = 0;
            api.post('/user/login', this.loginForm).then((response) => {
              if (response.data.code === 1) {
                let data = response.data.data;
                this.$root.$data.user = data;
                let {userToken} = data;
                sessionStorage.setItem('dleb_token', userToken);
                sessionStorage.setItem('dleb_user', JSON.stringify(data));
                sessionStorage.removeItem('errorCnt');
                this.$router.push('/home')
              } else {
                var msg = response.data.msg;
                // 用户登录密码错误前两次提示用户密码错误，第三次后提示用户“密码错误，若忘记密码请联系管理员？”
                if (msg === '密码错误') {
                  var errorCnt = sessionStorage.getItem('errorCnt') === null ? 0 : sessionStorage.getItem('errorCnt');
                  errorCnt = parseInt(errorCnt);
                  if (errorCnt >= 2) {
                    Message.MessageError('密码错误，若忘记密码请联系管理员？')
                  } else {
                    Message.MessageError(msg)
                  }
                  sessionStorage.setItem('errorCnt', errorCnt + 1);
                } else {
                  Message.MessageError(msg)
                }
              }
            })
          } else {
            return false;
          }
        });
      }
    },
    mounted() {

    }
  }
</script>

<style scoped>
  .admin-login {
    position: absolute;
    width: 100%;
    height: 100%;
    background-image: url(../assets/img/admin-bg.png);
    background-repeat: no-repeat;
    background-position: center;
    background-size: cover;
    padding: 70px;
    background-color: #000;
  }

  .admin-login a, .admin-login a:hover {
    color: inherit;
  }

  .admin-login header, .admin-login footer, .admin-login aside {
    position: fixed;
    background-color: #fff;
  }

  .admin-login header, .admin-login footer {
    width: 100%;
    height: 70px;
    padding: 0 70px;
    line-height: 70px;
    left: 0;
    text-align: left;
  }

  .admin-login header {
    top: 0;
    z-index: 999;
  }

  .admin-login header img {
    height: 36px;
  }

  .admin-login footer {
    bottom: 0;
    z-index: 999;
    color: #666;
  }

  .admin-login aside {
    width: 70px;
    height: 100%;
    top: 0;
  }

  .admin-login aside.left {
    left: 0;
  }

  .admin-login aside.right {
    right: 0;
  }

  .admin-login-title {
    height: 125px;
    position: absolute;
    top: 50%;
    left: 145px;
    margin-top: -160px;
  }

  .admin-login-title img {
    height: 100%;
  }

  .login-box {
    width: 365px;
    height: 350px;
    position: absolute;
    border-radius: 10px;
    top: 50%;
    right: 190px;
    margin-top: -175px;
    background: rgba(255, 255, 255, 0.85);
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  }

  .login-box-title {
    display: inline-block;
    font-size: 18px;
    color: #666;
    padding: 25px 0 25px 25px;
    width: 100%;
    border-bottom: 1px dashed #ddd;
    margin-bottom: 30px;
  }

  .login-box .el-form {
    width: 100%;
    padding: 0 25px;
  }

  .login-box .el-form .el-form-item {
    margin-bottom: 30px;
  }

  .login-box .el-form .login-button {
    padding-top: 15px;
  }

  .login-box .el-form .el-button {
    display: block;
    width: 100px;
    height: 30px;
    padding: 0;
    text-align: center;
    line-height: 30px;
    margin: 0 auto;
  }
</style>
