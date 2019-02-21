<template>
  <div class="page1180">
    <div class="page-title">
      <div style="float:left;">
        <router-link style="display:inline;
         text-decoration: none;" to="/AccountCentral">账户中心</router-link>
        <i class="el-icon-arrow-right"></i>
        <router-link style="display:inline;
         text-decoration: none;" to="/SubAccount">子账户管理</router-link>
        <i class="el-icon-arrow-right"></i>
        修改手机号
      </div>
    </div>
    <el-row class="page-content" >
      <!--警告-->
      <el-alert
        title="提示"
        description=" 请确保手机号为本人所持有"
        type="warning" show-icon>
      </el-alert>

      <!--步骤条-->
      <el-steps :active="active" finish-status="success" align-center  style="margin:20px 0 20px 0;">
        <el-step title="输入新手机号" description=""></el-step>
        <el-step title="选择验证方式" description=""></el-step>
        <el-step title="成功" description=""></el-step>
      </el-steps>

      <!--表单-->

      <div class="form" v-show="showForm">

        <!--步骤0 ----------------------------------------------------------------------------------------------------->
        <el-form ref="form" class="one-column" :model="form" :rules="rules"  label-width="140px" v-show="show" >
          <!--文本信息-->
          <el-form-item label="新手机号码:" prop="newPhone">
            <el-input v-model="form.newPhone" style="width:250px" ></el-input>
          </el-form-item>

          <!--按钮-->
          <el-form-item>
            <el-button type="primary" @click="onNext0('form')" style="margin:20px 0 0 40px;" icon="el-icon-d-arrow-right">下一步</el-button>
          </el-form-item>
        </el-form>



        <!--步骤1 -------------------------------------------------------------------------------------------------- -->
        <el-form ref="form1" class="one-column" :model="form1" :rules="rules"  label-width="140px" v-show="show1" >

          <el-row>
            <el-col class="changeType">
            <el-radio v-model="radio" label="1" @change="changeType('1')" >短信验证</el-radio>
            <el-radio v-model="radio" label="2" @change="changeType('2')" >银联鉴权</el-radio>
            </el-col>
          </el-row>

          <!--文本信息-->
          <el-form-item label="原手机号码:" prop="phone" v-show="radio1">
            <el-input v-model="form1.phone" style="width:250px" :disabled="true"></el-input>
          </el-form-item>

          <el-form-item label="银行卡号:" prop="bankCardNo" v-show="radio2">
              <el-select v-model="form1.bankCardNo" placeholder="请选择" filterable allow-create>
                <el-option
                  v-for="item in bankCardsList"
                  :key="item.bankCardNo"
                  :label="item.bankCardNo"
                  :value="item.bankCardNo">
                </el-option>
              </el-select>
          </el-form-item>


          <el-form-item label="" prop="unImgCode">
            <div ref="imgCode"></div>
            <el-input type="text" v-model="form1.unImgCode"  id="unImgCode" v-show="false"/>
          </el-form-item>

          <el-form-item label="短信验证码:" prop="code">
            <el-input class="column-verification-input" v-model.number="form1.code"  style="width:100px;"></el-input>

            <el-button class="column-verification-btn" @click.prevent="getCode(form1.phone)"  :disabled="getCodeState" type="primary"  >{{verifyingText}}</el-button>
          </el-form-item>

          <!--按钮-->
          <el-form-item>
            <el-button type="primary" @click="onUp()" style="margin:20px 0 0 0px;" icon="el-icon-d-arrow-left">上一步</el-button>
            <el-button type="primary" @click="onNext('form1')" style="margin:20px 0 0 20px;" icon="el-icon-d-arrow-right">下一步</el-button>
          </el-form-item>
        </el-form>


        <!-- ------------------------------------------------------------------------------------------------------- -->
        <el-row v-show="show2" >
          <el-col>
            <div class="success">恭喜!手机号更改成功<label>{{second}}</label></div>
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
    name: 'ModifyAccountPhone',
    data() {

      return {
        // post接收参数
        account: [{code: localStorage.getItem('UnBankCardSubAcctNo'), name: localStorage.getItem('UnBankCardSubAcctNoName')}],        // 子账户
        card: [{code: localStorage.getItem('UnBankCardMemberAcctNo'), name: localStorage.getItem('UnBankCardAcctOpenBranchName')}],   // 卡号
        active: 0,

        show: true,
        show1: false,
        show2: false,
        show3: false,
        showForm: true,
        getCodeState: true,
        bankCardId:this.$route.params.bankCardId,  //卡id
        second:3,
        //验证码变量
        verifyingText:'获取验证码',
        readSecondNum:60,

        radio: '1',
        radio1:true,
        radio2:false,
        radioVal:'1',

        form: {
          newPhone:''
        },
        form1: {
          subAccount: localStorage.getItem('SubAccountModifyCode'), //子账户id
          code: '',       //验证码
          unImgCode: '',
          bankCardNo:'0000', //选择的银行卡号
          subAccountObj:{},
          checkCode:''  //接口返回 - 短信指令号
        },
        form2: {
          account: '',
          member: '',
          SubAcctNo: localStorage.getItem('UnBankCardSubAcctNo'),      //this.$route.params.subAcctNo,
          MemberAcctNo: localStorage.getItem('UnBankCardMemberAcctNo')  //this.$route.params.memberAcctNo
        },
        form3: {},
        bankCardsList:[],

        // 字段校验
        rules: {
          phone: [ // 手机号
            {required: true, trigger: 'blur', validator: validPhone}// 这里需要用到全局变量
          ],
          newPhone: [ // 手机号
            {required: true, trigger: 'blur', validator: validPhone}// 这里需要用到全局变量
          ],
          code: [ // 验证码
            {required: true, message: '验证码不能为空'},
            {type: 'number', message: '验证码必须为数字值'}
          ],

          bankCardNo:[
            {required: true, message: '银行卡号不能为空'},
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

      //获取接口验证码
      getCode(mobile) {

          // 获取正确手机号,并发送验证码
          api.post('/pabank/ApplyForChangeOfCellPhoneNum', {
            TranNetMemberCode:  this.form1.subAccountObj.accountSubRelationId, //交易网会员代码
            SubAcctNo:this.form1.subAccount,                          //子账户账号
            ModifyType:this.radioVal,                                 //修改方式 - 1：短信验证码 2：银联鉴权
            NewMobile:this.form.newPhone,                             //新手机号码
            BankCardNo:this.radioVal=="1"?"":this.form1.bankCardNo  //银行卡号

          }).then(response => {
            if (response.data.code === 1) {
                      if(response.data.data.txnReturnMsg=="交易成功"){
                            Message.MessageSuccess("短信发送成功");
                            this.form1.checkCode =  response.data.data.messageOrderNo;  //获取返回 - 短信指令号
                      }
            } else {
              Message.MessageError(response.data.msg);
            }
          });
      },
      //验证码 end---------------------------------------

      //上一步
      onUp(){
        this.active--;
        this.show = true;
        this.show1 = false;
      },
      onNext0(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            //查询子账户交易手机号
            api.post('/accountSub/modifyAccountPhone/getAccountSub',{}).then(response => {
              if (response.data.code === 1) {

                this.form1.subAccountObj = response.data.data[0];
                this.form1.phone = response.data.data[0].accountTransactionPhone;

                //获取预留手机号
                if(this.form1.phone!=null && this.form1.phone!=""){
                    // 进行下一步操作
                    this.active++;
                    this.show = false;
                    this.show1 = true;
                }else{
                  this.$alert("请先绑定银行卡,并设置预留手机号", '提示', {confirmButtonText: '确定'});
                }
              } else {
                console.info(response);
                this.$alert(response.data.msg, '提示', {confirmButtonText: '确定'});
              }
            });
          }
        })
      },
      // 表单1
      onNext(formName) {

        this.$refs[formName].validate((valid) => {
          if (valid) { // submit
            // 验证手机验证码正确性
            api.post('/pabank/BackfillDynamicPassword', {
              TranNetMemberCode:  this.form1.subAccountObj.accountSubRelationId, //交易网会员代码
              SubAcctNo:this.form1.subAccount,                          //子账户账号
              ModifyType:this.radioVal,                                 //修改方式 - 1：短信验证码 2：银联鉴权
              NewMobile:this.form.newPhone,                             //新手机号码
              BankCardNo:this.form1.bankCardNo,                         //银行卡号
              MessageOrderNo:this.form1.checkCode,                       //短信指令号
              MessageCheckCode:this.form1.code                           //手机验证码

            }).then(response => {
              if (response.data.code === 1) {
                  if(response.data.data.txnReturnMsg=="交易成功"){
                    //Message.MessageSuccess("成功");
                      // 进行下一步操作
                      this.active++;
                      this.show1 = false;
                      this.show2 = true;

                    //回写新手机号到系统
                    api.post('/accountSub/modifyAccountPhone/setAccountSubPhone', {
                          accountSubNo:           this.form1.subAccount,        //子账户账号
                          accountTransactionPhone:this.form.newPhone            //新手机号码
                    }).then(response => {
                      if (response.data.code === 1) {

                      }
                    })

                      var self=this;
                      //每秒执行 - 修改读秒
                      var interval = setInterval(function(){
                        self.second--;
                        if(self.second==0){
                          clearInterval(interval);
                          self.$router.push({
                            name: '子账户管理',
                            params: { }
                          })
                        }
                      }, 1000);
                  }
            } else {
              this.$alert(response.data.msg, '提示', {confirmButtonText: '确定'});
            }
          });
          } else {
            console.log('error submit!!');
        return false;
      }
      });
      },


      changeType(val){
          if(val=="1"){
            //短信验证
            this.radio1 = true;
            this.radio2 = false;
            this.form1.bankCardNo = "0000";
          }else if(val=="2"){
            //银联鉴权
            this.radio1 = false;
            this.radio2 = true;
            this.getBankCard();
            this.form1.bankCardNo = "";
          }
          this.radioVal=val;
      },

      getBankCard() {
        api.post('/bankCards/getLocalBankCradList', {}).then(response => {
          if (response.data.code === 1) {
            if (response.data.data.length > 0) {
              var data  = response.data.data;
              this.bankCardsList = data;
            }
          }
        })
      },
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
    width: 300px;
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
  .changeType{
    text-align: center;
    padding: 20px;
  }
</style>
