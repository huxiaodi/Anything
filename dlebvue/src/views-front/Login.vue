<template>
  <div>
    <div class="header" style="z-index: 99;position: absolute">
      <div class="header-top-box">
        <ul class="header-top">
          <li class="header-name" style="margin:0;">
            <router-link to="/login">
              德邻e宝共享支付中心
            </router-link>
          </li>
          <li>
            <span @click="login" style="cursor:pointer">登录</span>
          </li>
          <li>
            <router-link to="/register">注册</router-link>
          </li>
          <li>
            <a href="javascript:">客服</a>
          </li>
          <li>
            {{nowTime}}&nbsp; {{nowWeek}}
          </li>
        </ul>
      </div>

      <div class="header-menu">
        <div class="menu-logo">
          <router-link to="/login">
            <img src="../assets/img/logo.png" alt="">
          </router-link>

        </div>
        <div class="menu-list">
          <a style="cursor:pointer;text-decoration:none;" a href="http://www.dllg56.com" target="_blank">德邻畅途</a>
          <a style="cursor:pointer;text-decoration:none;" a href="http://eb.ansteel.cn/" target="_blank">鞍钢电商</a>
          <a style="cursor:pointer;text-decoration:none;" a href="http://www.dllg56.com/market"
             target="_blank">货车商城</a>
          <a style="cursor:pointer;text-decoration:none;" a href="http://www.dllg56.com/our-services?type=cloudRepo"
             target="_blank">德邻云仓</a>
        </div>

      </div>
    </div>
    <div class="login">
      <div class="login-page">
        <swiper v-if="type == 1" :options="swiperOption">
          <swiper-slide>
            <div class="button-box">
              <img class="e-title" src="../assets/img/e-title.png" alt="">
              <el-button class="login-btn" @click="login()">登 录</el-button>
              <el-button class="register-btn" @click="regist()">注 册</el-button>
            </div>
            <i class="login-icon el-icon-arrow-down"></i>
          </swiper-slide>
          <swiper-slide>
            <div class="img-location">
              <img src="../assets/img/bg-text-2.png" alt="">
              <i class="login-icon el-icon-arrow-down"></i>
            </div>
          </swiper-slide>
          <swiper-slide>
            <div class="img-location">
              <img src="../assets/img/bg-text-3.png" alt="">
              <i class="login-icon el-icon-arrow-down"></i>
            </div>
          </swiper-slide>
          <swiper-slide>
            <div class="img-location">
              <img src="../assets/img/bg-text-4.png" alt="">
              <i class="login-icon el-icon-arrow-down"></i>
            </div>
          </swiper-slide>
          <swiper-slide>
            <div class="img-location">
              <img src="../assets/img/bg-text-5.png" alt="">
              <i class="login-icon el-icon-arrow-up"></i>
            </div>
          </swiper-slide>
        </swiper>


        <div class="login-sidebar">
          <div class="login-sidebar-item">
            <div class="icon-move-box">
              <div class="icon-move-item">
                <img src="../assets/img/login-icon/phone-icon.png" alt="">
                <img src="../assets/img/login-icon/phone-iconx.png" alt="">
              </div>
            </div>
          </div>
          <div class="login-sidebar-item">
            <div class="icon-move-box">
              <div class="icon-move-item">
                <img src="../assets/img/login-icon/phone-icon-2.png" alt="">
                <img src="../assets/img/login-icon/phone-icon-2x.png" alt="">
              </div>
            </div>
            <div class="login-sideba-content" style="height:40px;font-weight:bold;color:#009eff;">
              400-9855-888
            </div>
          </div>
          <div class="login-sidebar-item">
            <div class="icon-move-box">
              <div class="icon-move-item">
                <img src="../assets/img/login-icon/iphone-erweima-d.png" alt="">
                <img src="../assets/img/login-icon/iphone-erweima-dx.png" alt="">
              </div>
            </div>
            <div class="login-sideba-content">
              <img src="../assets/img/login-icon/img11.jpg" alt="">
            </div>
          </div>
          <div class="login-sidebar-item">
            <div class="icon-move-box">
              <div class="icon-move-item">
                <img src="../assets/img/login-icon/wexin-erweima-d.png" alt="">
                <img src="../assets/img/login-icon/wexin-erweima-d2.png" alt="">
              </div>
            </div>
            <div class="login-sideba-content">
              <img src="../assets/img/login-icon/img22.jpg" alt="">
            </div>
          </div>
        </div>

      </div>
      <div class="login-body">
        <div v-if="type == 2 || type == 3" class="next">
          <el-row style="font-size: 15px;height: 25px;border-bottom: grey 1px solid;margin-bottom: 10px;">
            <div style="float:left">重置登录密码</div>
            <div style="float:right">
              <a @click="type = 1" style="cursor:pointer;float:right">返回</a>
            </div>
          </el-row>

          <el-form class="one-column" v-if="type == 2" ref="passwordForm1" :model="loginForm" :rules="formRules" label-width="100px"
                   style="width: 40%;margin:0 auto;">
            <el-row style="height:70px">
              <el-form-item label="用户名" prop="userName">
                <el-input v-model="loginForm.userName"></el-input>
              </el-form-item>
            </el-row>
            <el-row>
              <el-form-item label="验证码">
                <div ref="c1" style="width: 100%;margin-bottom: 50px;"></div>
              </el-form-item>
            </el-row>
            <el-row style="width:100%;text-align:center">
                <el-button style="width:80px;margin:0 auto" type="primary btn-block" @click="checkvVerificationCode" :disabled="available">下一步
                </el-button>
            </el-row>
          </el-form>

          <el-form  class="one-column" v-if="type == 3" ref="passwordForm2" :model="loginForm" :rules="formRules" label-width="140px">
            <el-row></el-row>
            <el-row>
              <el-form-item label="找回方式">
                  <el-radio v-model="radio" label="1">手机号找回</el-radio>
                <!--<el-radio v-model="radio" label="2">邮箱找回</el-radio>-->
              </el-form-item>
            </el-row>
            <el-row style="height:70px">
              <el-form-item label="联系人手机号" prop="mobile">
                <el-input v-model="loginForm.mobile"></el-input>
              </el-form-item>
            </el-row>
            <el-row style="height:70px">
              <el-form-item label="手机验证码" prop="verification">
                  <el-input class="column-verification-input" v-model="loginForm.verification"></el-input>
                  <el-button type="primary" @click="getVerification" class="column-verification-btn"
                             :disabled="sendMsgDisabled || isDisabled">
                    <span v-if="sendMsgDisabled">{{time+'秒后再次获取'}}</span>
                    <span v-if="!sendMsgDisabled">获取验证码</span>
                  </el-button>
              </el-form-item>
            </el-row>
            <el-row style="height:70px">
              <el-form-item label="新密码" prop="newPassword">
                <el-input type="password" v-model="loginForm.newPassword"></el-input>
              </el-form-item>
            </el-row>
            <el-row style="height:70px">
              <el-form-item label="确认密码" prop="newPasswordConfirm">
                <el-input type="password" v-model="loginForm.newPasswordConfirm"></el-input>
              </el-form-item>
            </el-row>
            <el-row>
              <el-col :span="6" :offset="5">
                <el-button type="primary btn-block" @click="resetPassword">提交</el-button>
              </el-col>
              <el-col :span="6" :offset="5">
                <el-button type="primary btn-block" @click="cancel">取消</el-button>
              </el-col>
            </el-row>
          </el-form>
        </div>


        <el-dialog :visible.sync="dialogVisible" class="login-box" :before-close="handleClose">
          <div class="el-dialog-title">
            用户登录
          </div>
          <el-form ref="loginForm" :model="loginForm" class="loginForm" :rules="formRules" label-width="0px">
            <el-form-item label="" prop="userName">
              <el-input type="text" v-model="loginForm.userName" placeholder="用户名"
                        @keyup.enter.native="onSubmit"></el-input>
            </el-form-item>

            <el-form-item label="" prop="userPassword">
              <el-input type="password" v-model="loginForm.userPassword" placeholder="密码"
                        @keyup.enter.native="onSubmit"></el-input>
            </el-form-item>
            <el-row class="login-additional">
              <div style="float:left;">
                <label>
                  <input type="checkbox" v-model="isKeepLoginInfo" value="记住密码">
                  记住密码
                </label>
              </div>
              <a @click="toRestPassword" style="color:#0099ff;display:inline-block;float:right;cursor:pointer">忘记密码</a>
            </el-row>
            <el-row style="text-align:center;width:100%;">

              <el-button style="width:100px;" type="primary" @click="onSubmit">登录</el-button>
              <div style="padding-top:5px;">
                <router-link style="color:#0099ff;" to="/register">注册账号?</router-link>
              </div>


            </el-row>
          </el-form>
        </el-dialog>
      </div>
      <!-- swiper -->
      <div class="login-foot">
        <div class="login-foot-content">
          <div class="login-foot-item left">
            <img src="../assets/img/icon-1.png" alt="">
            <div class="login-foot-text">
              <span class="login-foot-text-title">转账付款</span>
              <span class="login-foot-text-content">转到德邻e宝账户，瞬间到账</span>
            </div>
          </div>
          <div class="login-foot-item">
            <img src="../assets/img/icon-2.png" alt="">
            <div class="login-foot-text">
              <span class="login-foot-text-title">提现</span>
              <span class="login-foot-text-content">便捷的体现方式，将为你带来T+0的收款体验</span>
            </div>
          </div>
          <div class="login-foot-item right">
            <img src="../assets/img/icon-3.png" alt="">
            <div class="login-foot-text">
              <span class="login-foot-text-title">双网同步</span>
              <span class="login-foot-text-content">PC端·APP端口数据同步，支付便捷</span>
            </div>
          </div>
        </div>
        <div class="login-bottom">
          Copyright (c) 2017 Copyright Holder All Rights Reserved.
        </div>
      </div>

    </div>
  </div>

</template>
<script>
  import {swiper, swiperSlide} from 'vue-awesome-swiper'
  import '../assets/css/swiper.css'

  import api from '@/api/fetch'
  import * as Message from '../utils/messageUtil'
  import _dx from '_dx'

  var moment = require('moment');
  var qs = require('qs');
  // 手机号
  var mobileReg = /^1[34578]\d{9}$/;
  // 用户名
  var userNameReg = /^(?![0-9]+$)(?!\d)[^A-Z]{6,50}$/;
  // 密码
  var passwordReg = /^(?=[^A-Z\u4e00-\u9fa5]*[0-9])(?=[^A-Z\u4e00-\u9fa5]*[a-z])[^A-Z\u4e00-\u9fa5]{8,50}$/;
  // 当前时间
  var now = new Date();
  // 超时时间
  var overTime = 24 * 60 * 60 * 1000;
  export default {
    name: 'login',
    components: {
      swiper,
      swiper,
      swiper,
      swiperSlide
      // LoginHead
    },
    data() {
      var validatePass = (rule, value, callback) => {
        if (value === '' || value === undefined) {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.loginForm.newPassword) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        nowTime: moment().format('YYYY年M月D日'),
        nowWeek: '',
        swiperOption: {
          direction: 'vertical',
          slidesPerView: 1,
          spaceBetween: 30,
          mousewheel: true,
          pagination: {
            el: '.swiper-pagination',
            clickable: true
          }
        },
        sendMsgDisabled: false,
        time: 60,
        isShow: false,
        isKeepLoginInfo: false, // 是否记住账号密码
        type: 1,
        radio: '1',
        available: true,
        dialogRegist: false,
        dialogVisible: false,
        loginForm: {
          userName: '',
          userPassword: '',
          verification: '',
          mobile: '',
          newPassword: '',
          newPasswordConfirm: '',
          token: ''
        },
        formRules: {
          userName: [{
            required: true,
            message: '用户名不能为空',
            trigger: 'blur'
          },
            {
              min: 6,
              message: '用户名至少6位',
              trigger: 'blur'
            }
          ],
          userPassword: [{
            required: true,
            message: '密码不能为空',
            trigger: 'blur'
          },
            {
              min: 8,
              message: '密码至少8位',
              trigger: 'blur'
            }
          ],
          verification: [{
            required: true,
            message: '验证码不能为空',
            trigger: 'blur'
          }],
          mobile: [{
            required: true,
            message: '手机号不能为空',
            trigger: 'blur'
          },
            {
              pattern: mobileReg,
              message: '请输入正确的手机号'
            }
          ],
          newPassword: [{
            required: true,
            message: '新密码不能为空',
            trigger: 'blur'
          },
            {
              pattern: passwordReg,
              message: '密码至少8位，支持数字、字母（小写）、字符，至少包含数字、字母组合'
            }
          ],
          newPasswordConfirm: [{
            validator: validatePass,
            trigger: 'blur'
          }]
        }
      }
    },
    methods: {
      formatData() {
        switch (moment().weekday()) {
          case 0:
            this.nowWeek = '星期日';
            break;
          case 1:
            this.nowWeek = '星期一';
            break;
          case 2:
            this.nowWeek = '星期二';
            break;
          case 3:
            this.nowWeek = '星期三';
            break;
          case 4:
            this.nowWeek = '星期四';
            break;
          case 5:
            this.nowWeek = '星期五';
            break;
          case 6:
            this.nowWeek = '星期六';
            break;
        }
      },
      toggle: function () {
        this.isShow = !this.isShow;
      },
      // 显示登录窗口
      login() {
        this.loginForm = {};
        this.dialogVisible = true;
        this.type = 1;
        // 判断是否记住密码
        var endTime = parseInt(localStorage.getItem('endTime'));
        if (now < new Date(endTime)) {
          var userName = localStorage.getItem('userName');
          var userPassword = localStorage.getItem('userPassword')
          if (userName && userPassword) {
            this.isKeepLoginInfo = true;
            this.loginForm.userName = userName;
            this.loginForm.userPassword = userPassword;
          }
        }
      },
      regist() {
        this.$router.push('/Register')
      },
      handleClose(done) {
        this.dialogVisible = false;
      },
      //  找回密码
      toRestPassword() {
        this.loginForm = {};
        this.dialogVisible = false;
        this.type = 2;
        // 验证码
        this.$nextTick(function () {
          this.captcha()
        });
      },
      // 验证码
      captcha() {
        var that = this;
        var myCaptcha = _dx.Captcha(this.$refs.c1, {
          appId: 'e9a1d305fc43f48f94ea8ae35ed93be2',
          style: 'inline',
          inlineFloatPosition: 'down',
          success: function (token) {
            that.available = false;
            that.loginForm.token = token;
          }
        })
      },
      // 下一步
      checkvVerificationCode() {
        this.$refs['passwordForm1'].validate((valid) => {
          if (valid && this.loginForm.token) {
            api.post('/user/login/checkToken', this.loginForm).then(response => {
              if (response.data.code === 1) {
                this.type = 3;
              } else {
                Message.MessageError(response.data.msg);
              }
            });
          } else {
            return false;
          }
        })
      },
      // 重置登录密码
      resetPassword() {
        this.$refs['passwordForm2'].validate((valid) => {
          if (valid) {
            api.post('/user/login/resetPassword', this.loginForm).then(response => {
              if (response.data.code === 1) {
                Message.MessageSuccess(response.data.msg);
                this.type = 1;
                this.resetForm('loginForm');
              } else {
                Message.MessageError(response.data.msg);
              }
            });
          } else {
            return false;
          }
        })
      },
      // 退回登录界面
      cancel() {
        this.type = 1;
        this.loginForm = {};
        this.available = true;
      },
      //  获取短信验证码
      getVerification() {
        var mobile = this.loginForm.mobile;
        if (mobile && mobileReg.test(mobile)) {
          api.post('/user/register/sendVerificationCode', qs.stringify({
            mobile: mobile
          })).then(response => {
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
              Message.MessageError(response.data.data);
            }
          });
        } else {
          Message.MessageError('请输入正确的手机号码');
        }
      },

      // 登录
      onSubmit() {
        var formName = 'loginForm';
        this.$refs[formName].validate((valid) => {
          if (valid) {
            api.post('/user/login', this.loginForm).then((response) => {
              if (response.data.code === 1) {
                let data = response.data.data;
                this.$root.$data.user = data;
                sessionStorage.setItem('dleb_token', data.userToken);
                sessionStorage.setItem('dleb_user', JSON.stringify(data));
                // 是否记住账号密码
                if (this.isKeepLoginInfo) {
                  localStorage.setItem('userName', this.loginForm.userName);
                  localStorage.setItem('userPassword', this.loginForm.userPassword);
                  localStorage.setItem('endTime', (now.getTime() + overTime).toString());
                } else {
                  localStorage.clear();
                  localStorage.removeItem('userName');
                  localStorage.removeItem('userPassword');
                }
                api.get('basic/getFrontResouceTreeData').then(response => {
                  let data = response.data.data;
                  localStorage.setItem('menus', JSON.stringify(data.frontMenu));
                  localStorage.setItem('buttonRight', JSON.stringify(data.frontButton));
                  this.$router.push('/FrontHome')
                })
              } else {
                Message.MessageError(response.data.msg)
              }
            })
          } else {
            return false;
          }
        })
      },
      // 重置表单
      resetForm(formName) {
        this.$refs[formName].resetFields()
      },

      show() {
        var vm = new Vue({
          el: '#box',
          methods: {
            show: function (ev) {
              if (ev.keyCode === 13) {
                alert('你按回车键了');
              }
            },
          }
        });
      }
    },
    mounted() {
      window.login = this
      this.formatData();
    }
  }
</script>


<style scoped>
  /* 登录注册按钮 */

  .button-box {
    height: 50px;
    width: 100%;
    text-align: center;
    position: absolute;
    left: 0;
    top: 50%;
    margin-top: -25px;
  }

  /* 按钮样式 */

  .button-box .el-button {
    display: inline-block;
    width: 225px;
    height: 40px;
    margin: 0 8px;
    font-size: 16px;
    color: #fff;
  }

  .button-box .el-button.login-btn:hover {
    background: #2094d1 !important;
    transition: 0s;
    box-shadow: 0 0 5px rgba(0, 0, 0, 0.6)
  }

  /* 登录按钮 */

  .button-box .el-button.login-btn {
    background: linear-gradient(to right, #1e94d4, #47c5ee);
    transition: 0s;
    border: 0;
  }

  /* 注册按钮 */

  .button-box .el-button.register-btn {
    background-color: rgba(0, 0, 0, 0);
    border: 1px #fff solid;
  }

  .button-box .el-button.register-btn:hover {
    background-color: rgba(255, 255, 255, 0.2);
    transition: 0s;
    box-shadow: 0 0 5px rgba(0, 0, 0, 0.6)
  }

  /* e宝无限文字 */

  .button-box .e-title {
    position: absolute;
    width: 400px;
    bottom: 110px;
    left: 50%;
    margin-left: -200px;
  }

  .swiper-container.swiper-container-vertical {
    position: absolute;
    left: 0;
    top: 0;
  }

  .swiper-container.swiper-container-vertical,
  .swiper-wrapper,
  .swiper-slide {
    height: 100%;
    width: 100%;
    z-index: 0;
  }

  /* 全屏切换 */

  .swiper-slide:nth-child(1) {
    background-image: url(../assets/img/bg-1.jpg);
    background-repeat: no-repeat;
    background-position: center;
    background-size: cover;
  }

  .swiper-slide:nth-child(2) {
    background-image: url(../assets/img/bg-2.jpg);
    background-repeat: no-repeat;
    background-position: center;
    background-size: cover;
  }

  .swiper-slide:nth-child(3) {
    background-image: url(../assets/img/bg-3.jpg);
    background-repeat: no-repeat;
    background-position: center;
    background-size: cover;

  }

  .swiper-slide:nth-child(4) {
    background-image: url(../assets/img/bg-4.jpg);
    background-repeat: no-repeat;
    background-position: center;
    background-size: cover;
  }

  .swiper-slide:nth-child(5) {
    background-image: url(../assets/img/bg-5.jpg);
    background-repeat: no-repeat;
    background-position: center;
    background-size: cover;
  }

  .swiper-slide div.img-location {
    width: 1180px;
    margin: 0 auto;
  }

  .swiper-slide:nth-child(2) img {
    width: 520px;
    margin-top: 150px;
  }

  .swiper-slide:nth-child(3) img {
    width: 530px;
    margin-top: 270px;
  }

  .swiper-slide:nth-child(4) img {
    width: 455px;
    margin-top: 120px;
    float: right;
  }

  .swiper-slide:nth-child(5) img {
    width: 600px;
    margin-top: 185px;
  }

  /* 向下箭头 */

  .login-icon {
    width: 100%;
    text-align: center;
    position: absolute;
    left: 0;
    bottom: 120px;
    color: #fff;
    font-size: 50px;
    opacity: 0.5;
    animation: moveicon 1s;
    animation-iteration-count: infinite;
  }

  @keyframes moveicon {
    0% {
      bottom: 120px;
    }
    25% {
      bottom: 125px;
    }
    50% {
      bottom: 130px;
    }
    75% {
      bottom: 125px;
    }
    100% {
      bottom: 120px;
    }
  }

  .login li.user-class {
    float: left;
  }

  .login .pull-right {
    float: right;
  }

  /* 页脚 */

  .login-foot {
    height: 100px;
    width: 100%;
    position: absolute;
    left: 0;
    bottom: 0;
    background-color: #fff;
  }

  .login-foot-content {
    height: 70px;
    width: 1180px;
    margin: 0 auto;
    background-color: #fff;;
    overflow: hidden;
    text-align: center;
  }

  .login-foot-content .left {
    float: left;
  }

  .login-foot-content .right {
    float: right;
  }

  .login-foot-item {
    padding-top: 10px;
    display: inline-block;
    text-align: left;
  }

  .login-foot-item img {
    vertical-align: middle;
  }

  .login-foot-item .login-foot-text {
    display: inline-block;
    text-align: left;
    overflow: hidden;
    padding-left: 5px;
    vertical-align: middle;
  }

  .login-foot-text-title {
    display: block;
    font-size: 20px;
    font-weight: bold;
    color: #444;
  }

  .login-foot-text-content {
    font-size: 12px;
    color: #555;
  }

  .login-bottom {
    height: 30px;
    width: 100%;
    background-color: #333;
    text-align: center;
    font-size: 12px;
    line-height: 30px;
    color: #eee;
  }

  /* 侧边栏 */

  .login-sidebar {
    position: absolute;
    width: 40px;
    right: 0;
    bottom: 30%;
    background-color: #fff;
    border: 1px solid #eee;
    text-align: center;
    line-height: 40px;
  }

  .login-sidebar-item {
    width: 100%;
    height: 40px;
    cursor: pointer;
    position: relative;

  }

  .login-sidebar-item .icon-move-box .icon-move-item img {
    width: 40px;
    height: 40px;
  }

  .login-sidebar-item .icon-move-box {
    width: 100%;
    height: 40px;
    overflow: hidden;
    position: absolute;
    left: 0;
    top: 0;
  }

  .login-sidebar-item .icon-move-box .icon-move-item {
    margin-top: 0px;
    transition: .5s;
  }

  .login-sidebar-item:hover .icon-move-box .icon-move-item {
    margin-top: -40px;
    transition: .5s;
  }

  .login-sidebar-item:hover .login-sideba-content {

    width: 150px;
    transition: .5s;
  }

  .login-sideba-content {
    transition: .5s;

    position: absolute;
    width: 0px;
    height: auto;
    overflow: hidden;
    top: 0;
    right: 40px;
    background-color: #fff;
    line-height: 40px;
  }

  .login-sideba-content img {
    width: 150px;
    height: 150px;
  }

  .login-page {
    /*position: absolute;*/
    overflow: hidden;
    width: 100%;
    height: 100%;
    z-index: 2
  }

  .next {
    min-height: 500px;
    width: 1180px;
    box-sizing: border-box;
    background-color: #fff;
    text-align: left;
    margin: 110px auto 0;
    padding: 20px;

  }
  /* 页眉部分 */
  .header {
    width: 100%;
    height: 100px;
    line-height: 30px;
    background-color: #fff;
    font-size: 14px;
    color: #fff;
    position: relative;
    z-index: 5;
  }
  .header-top-box{
    background-color: #000;
    width: 100%;
    height: 30px;
    line-height: 30px;
  }
  ul.header-top{
    width: 1180px;
    list-style: none;
    padding: 0;
    text-align: right;
    margin: 0 auto;
    color: #fff;
  }
  li.header-name {
    float: left;
  }
  ul.header-top li a {
    text-decoration: none;
    color: inherit;
  }
  ul.header-top li {
    display: inline-block;
    margin-left: 30px;
  }

  /* 菜单栏 */
  .header-menu{
    width: 1180px;
    margin: 0 auto;
    height: 70px;
    font-size: 0;
    background-color: #fff;
  }
  .menu-logo {
    float: left;
  }
  .menu-logo img{
    height: 45px;
    margin-top: 12px;
  }
  .menu-list{
    float: right;
    text-align: right;
    width: 500px;
    height: 100%;
    line-height: 70px;
  }
  .menu-list a{
    display: inline-block;
    margin-left: 35px;
    font-size: 14px;
    color: #000;
  }
  .menu-list a:hover{
    color: #009eff;
  }
</style>
