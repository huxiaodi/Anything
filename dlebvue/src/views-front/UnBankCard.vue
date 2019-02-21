<template>
  <div class="page1180">
    <div class="page-title">
      <div style="float:left;">
        <router-link to="/FrontHome">我的e宝</router-link>
        <i class="el-icon-arrow-right"></i>
        <router-link style="display:inline;
      text-decoration: none;" to="/BankCard">账户设置</router-link>
        <i class="el-icon-arrow-right"></i>
        解绑银行卡
      </div>
    </div>
    <el-row class="page-content" >
      <!--警告-->
      <el-alert
        title="消息提示"
        description="请谨慎操作，一旦您解除绑定银行卡，将无法再完成余额服务"
        type="warning" show-icon>


      </el-alert>

      <!--步骤条-->
      <el-steps :active="active" finish-status="success" align-center  style="margin:20px 0 20px 0;">
        <el-step title="手机验证" description=""></el-step>
        <el-step title="账户信息验证" description=""></el-step>
        <el-step title="完成" description=""></el-step>
      </el-steps>

      <!--表单-->

      <div class="form" v-show="showForm">

        <!--步骤1-->
        <el-form ref="form" class="one-column" :model="form" :rules="rules"  label-width="140px" v-show="show" >
          <!--文本信息-->
          <el-form-item label="联系人手机号:" prop="phone">
            <el-input v-model="form.phone" style="width:250px" :disabled="true"></el-input>
          </el-form-item>

          <el-form-item label="" prop="unImgCode">
            <div ref="imgCode"></div>
            <el-input type="text" v-model="form.unImgCode"  id="unImgCode" v-show="false"/>
          </el-form-item>

          <el-form-item label="短信验证码:" prop="code">
            <el-input class="column-verification-input" v-model.number="form.code"  style="width:100px;"></el-input>

            <el-button class="column-verification-btn" @click.prevent="getCode(form.phone)"  :disabled="getCodeState" type="primary"  >{{verifyingText}}</el-button>
          </el-form-item>

          <!--按钮-->
          <el-form-item>
            <el-button type="primary" @click="onNext('form')" style="margin:20px 0 0 40px;" icon="el-icon-d-arrow-right">下一步</el-button>
          </el-form-item>
        </el-form>

        <!--步骤2-->
        <el-form ref="form2" class="one-column" :model="form2" :rules="rules2"  label-width="120px" v-show="show2">
          <!--文本信息-->
          <el-form-item label="解绑账户:" prop="SubAcctNo">

            <el-select v-model="form2.SubAcctNo" placeholder="解绑账户" style="width:270px">
              <el-option
                v-for="item in account"
                :key="item.value"
                :label="item.name"
                :value="item.code">
              </el-option>
              </el-option>

            </el-select>
          </el-form-item>
          <el-form-item label="银行帐户:" prop="MemberAcctNo">
            <el-select v-model="form2.MemberAcctNo" placeholder="银行帐户" style="width:270px">
              <!--银行卡号-->
              <el-option
                v-for="item in card"
                :key="item.value"
                :label="item.name"
                :value="item.code">
              </el-option>
            </el-select>
          </el-form-item>

          <!--按钮-->
          <el-form-item>
            <el-button type="primary" @click="unBank('form2')"  style="margin-left:20px;">同意并解除绑定</el-button>
          </el-form-item>
        </el-form>

        <el-row v-show="show3" >
          <el-col>
            <div class="success">解绑成功<label>{{second}}</label></div>
          </el-col>
        </el-row>
      </div>
    </el-row>





  </div>
</template>

<script>
  // 引入相关
  import api from '../api/fetch'
  import * as Message from '../utils/messageUtil'
  import {pageMixin} from '../common/mixin.js'
  import {exportModel} from '../common/export'
  import {parseTime} from '../utils/index'
  import _dx from '_dx'

  var qs = require('qs');

  // 手机号
  function isvalidPhone(str) {
    const reg = /^1[3|4|5|7|8][0-9]\d{8}$/
    return reg.test(str)
  }

  var validPhone = (rule, value, callback) => {
    if (!value) {
      callback(new Error('请输入电话号码'))
    } else if (!isvalidPhone(value)) {
      callback(new Error('请输入正确的11位手机号码'))
    } else {
      callback()
    }
  }

  export default {
    name: 'UnBankCard',
    data() {

      return {
        // post接收参数
        account: [{code: localStorage.getItem('UnBankCardSubAcctNo'), name: localStorage.getItem('UnBankCardSubAcctNoName')}],        // 子账户
        card: [{code: localStorage.getItem('UnBankCardMemberAcctNo'), name: localStorage.getItem('UnBankCardAcctOpenBranchName')}],   // 卡号
        active: 0,
        show: true,
        show2: false,
        show3: false,
        showForm: true,
        getCodeState: true,
        bankCardId:this.$route.params.bankCardId,  //卡id
        second:3,
        //验证码变量
        verifyingText:'获取验证码',
        readSecondNum:60,

        form: {
          phone: localStorage.getItem('UnBankCardPhone'), //this.$route.params.phone, //手机号
          code: '',
          unImgCode: ''
        },
        form2: {
          account: '',
          member: '',
          SubAcctNo: localStorage.getItem('UnBankCardSubAcctNo'),      //this.$route.params.subAcctNo,
          MemberAcctNo: localStorage.getItem('UnBankCardMemberAcctNo')  //this.$route.params.memberAcctNo
        },
        form3: {},
        // 字段校验
        rules: {
          phone: [ // 手机号
            {required: true, trigger: 'blur', validator: validPhone}// 这里需要用到全局变量
          ],
          code: [ // 验证码
            {required: true, message: '验证码不能为空'},
            {type: 'number', message: '验证码必须为数字值'}
          ]
        },
        rules2: {
          account: [ // 账号
            {required: true, message: '请选择解绑账户'}
          ],
          member: [ // 卡号
            {required: true, message: '请选择银行帐户'}
          ]
        }
      }
    },

    methods: {

      //验证码 start--------------------------------------
      //读秒
      readSecond(){
        this.getCodeState = true;       // 禁用按钮
        var self=this;
        //每秒执行 - 修改读秒
        var interval = setInterval(function(){
          self.verifyingText = "重新获取短信（"+self.readSecondNum+"）";
          if(self.readSecondNum==0){
            clearInterval(interval);
            self.getCodeState = false; // 启用按钮
            self.verifyingText = "重新获取验证码";
            self.readSecondNum = 60;
          }
          self.readSecondNum--;
        }, 1000);
      },
      getCode(mobile) {
        if (mobile && isvalidPhone(mobile)) {
          // 获取正确手机号,并发送验证码
          api.post('/user/register/sendVerificationCode', qs.stringify({mobile: mobile})).then(response => {
            if (response.data.code === 1) {
            Message.MessageSuccess(response.data.data);
          } else {
            Message.MessageError(response.data.data);
          }
          //读秒
          this.readSecond();
        });
        } else {
          Message.MessageError('请输入正确的手机号码');
        }
      },
      //验证码 end---------------------------------------
      // 表单1
      onNext(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) { // submit
            // 验证手机验证码正确性
            api.post('/bankCards/validateCode', this.form).then(response => {
              if (response.data.code === 1) {
              //Message.MessageSuccess(response.data.data);
              // 进行下一步操作
              this.active++;
              this.show = false;
              this.show2 = true;
            } else {
              console.info(response);
              //Message.MessageError(response.data.msg);
              this.$alert(response.data.msg, '提示', {confirmButtonText: '确定'});
            }
          });
          } else {
            console.log('error submit!!');
        return false;
      }
      });
      },
      // 表单2(解绑)
      unBank(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            // 调用接口 - 执行解绑操作
            api.post('/pabank/UnbindRelateAcct', this.form2).then(response => {
              if (response.data.code === 1) {
              if (response.data.data.txnReturnCode !== '000000') { // 000000为成功状态
                //Message.MessageError(response.data.data.txnReturnMsg);
                this.$alert(response.data.data.txnReturnMsg, '提示', {confirmButtonText: '确定'});
              } else {

                console.info(this.$route.params.subAcctNo);
                console.info(this.$route.params.memberAcctNo);
                console.info(this.$route.params.bankCardId);

                //重置本地银行卡信息(用户id、子账户id、银行卡号)
                api.post('/bankCards/unLocalBankCrad', {
                  bankCardAccountSubId:this.$route.params.subAcctNo, //使用子账户id暂存子账户号
                  bankCardId: this.$route.params.bankCardId,
                  bankCardNo:this.$route.params.memberAcctNo }).then(response => {
                  if (response.data.code === 1) {
                  if(response.data.data =="解绑成功"){
                    // 交易成功
                    this.active++;
                    this.active++;
                    this.show2 = false;
                    this.show3 = true;
                    this.showForm = true;

                    var self=this;
                    //每秒执行 - 修改读秒
                    var interval = setInterval(function(){
                      self.second--;
                      if(self.second==0){
                        clearInterval(interval);
                        self.$router.push({
                          name: '银行卡管理',
                          params: { }
                        })
                      }
                    }, 1000);

                  }
                }
              })
              }
            }
          });

          } else {
            console.log('error submit!!');
        return false;
      }
      })
      }

    },
    mounted() {


      var that = this; // 转换变量名冲突

      // 图形验证码
      var myCaptcha = _dx.Captcha(this.$refs.imgCode, {
        appId: 'e9a1d305fc43f48f94ea8ae35ed93be2',   //  appId,开通服务后可在控制台中“服务管理”模块获取
        type: 'basic',
        style: 'inline',
        width: 270,
        success: function (token) {
          that.form.unImgCode = token;
          that.getCodeState = false; // 启用按钮
          // this.getCodeState = false;  // 启用按钮
//          form.find('input[name=verify_token]').val(token)
//          form.find('input[type=submit]').attr('disabled', false)
//          window.console && console.log('success, token:', token)
        }
      })
    }
  }
</script>

<style scoped>

  .nav {
    font-size: 13px;
    padding: 15px 0 0 0;
  }

  .form {
    width: 500px;
    margin: 0px auto;
    padding: 20px 30px 5px 10px;
    background: #fbfbfb
  }

  .hr {
    color: #fff;
    margin: 10px 0 30px 0;
    border: none;
    border-top: 1px solid #eee;
  }

  .success {
    padding: 130px 50px;
    text-align: center;
    font-size: 18px;
    background: #fcfcfc;
    width: 180px;
    margin: 0px auto;
  }
  .head {
    width: 80%;
    background-color: #ffffff;
    color: black;
    border-bottom: #ddd 1px solid;
    padding-bottom: 20px;
    margin: 30px auto;
  }
  .content-page{
    width: 80%;
    margin: 0 auto;
  }
</style>
