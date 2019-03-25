<template>
  <div>
    <login-head/>
    <div class="page1180" style="padding-top:0;">
      <div class="register">
        <div class="company" >
          <el-form ref="companyForm" class="often-input-list" :rules="companyRules" :model="companyForm"
                   label-width="140px">
            <div class="form-title">
              注册信息
            </div>
            <el-row>
              <el-col :span="12">
                <el-form-item label="用户名" prop="userName">
                  <div class="user-prompt">
                    <i class="el-icon-info"></i>
                    后期仅限用户名登录，请妥善保存!
                  </div>
                  <el-input v-model="companyForm.userName"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="联系人手机号" prop="companyContactTel">
                  <el-input v-model="companyForm.companyContactTel"></el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="密码" prop="password">
                  <el-input type="password" v-model="companyForm.password"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="确认密码" prop="ensurePassword">
                  <el-input type="password" v-model="companyForm.ensurePassword"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="企业名称" prop="companyName">
                  <el-input type="text" v-model="companyForm.companyName"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="企业简称" prop="companyShortName">
                  <el-input type="text" v-model="companyForm.companyShortName"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="联系人" prop="companyContact">
                  <el-input type="text" v-model="companyForm.companyContact"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="联系人身份证" prop="companyContactIdCard">
                  <el-input type="text" v-model="companyForm.companyContactIdCard"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>

            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="公司邮箱" prop="companyEmail">
                  <el-input type="text" v-model="companyForm.companyEmail"></el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="用户类型">
                  <el-switch
                    style="margin-left: 10%;"
                    active-color="#0099ff"
                    inactive-color="#0099ff"
                    v-model="companyForm.userTypeTemp"
                    active-text="接单方"
                    inactive-text="发布方">
                  </el-switch>
                </el-form-item>
              </el-col>
            </el-row>


            <el-row style="width:100%;text-align:center">
              <el-button style="margin:20px 0 20px;display:inline-block;width:80px" type="primary"
                         @click="submitForm('companyForm')">提交
              </el-button>
            </el-row>
          </el-form>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
  import api from '@/api/fetch'
  import * as Message from '../utils/messageUtil'
  import _dx from '_dx'
  import LoginHead from './LoginHead.vue'

  var qs = require('qs');
  // 邮箱
  var emailReg = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
  // 手机号
  var mobileReg = /^1[34578]\d{9}$/;
  // 15或18位身份证
  var cardReg = /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}[0-9Xx]$)/;
  // 至少八位，小写字母、数字组合
  var letterAndNumberEight = /^(?=.*[a-z])(?=.*\d)[a-z\d]{8,}$/;
  // 至少八位，小写字母、数字、字符组合
  var allCheckEight = /^(?=.*[a-z])(?=.*\d)(?=.*[$@$!%*#?&])[a-z\d$@$!%*#?&]{8,}$/;
  // 至少六位，小写字母+数字
  var letterAndNumberSix = /^(?=.*[a-z])(?=.*\d)[a-z\d]{6,}$/;
  // 至少六位，小写字母、数字、字符组合
  var allCheckSix = /^(?=.*[a-z])(?=.*\d)(?=.*[$@$!%*#?&])[a-z\d$@$!%*#?&]{6,}$/;
  // 至少六位，字符+数字
  var charAndNumberSix = /^(?=.*[$@$!%*#?&])[a-z\d$@$!%*#?&]{6,}$/;
  // 至少六位，纯小写字母
  var letterSix = /^[a-z]{6,}$/
  // 纯数字
  var numberReg = /^[0-9]*$/;
  // 传真
  var companyFaxReg = /^(\d{3,4}-)?\d{7,8}$/;

  export default {
    name: 'Register',
    components: {
      LoginHead
    },
    data() {
      var checkPassword = (rule, value, callback) => {
        if (!value) {
          callback(new Error('请确认密码'))
        } else if (value !== this.companyForm.password && this.userType === '2') {
          callback(new Error('请确保两次输入一致'))
        } else {
          callback()
        }
      }
      var validateNum = (rule, value, callback) => {
        if (!value) {
          callback(new Error('请输入证件号码'))
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
      var userName = (rule, value, callback) => {
        api.post('/user/register/checkUserName', {'userName': value}).then(response => {
          if (response.data.code !== 1) {
            this.userNameCheck = false
          } else {
            this.userNameCheck = true
          }
        })
        if (!value) {
          callback(new Error('请输入用户名'))
        } else if (numberReg.test(value[0])) {
          callback(new Error('不能以数字开头'))
        } else if (!(letterAndNumberSix.test(value) || allCheckSix.test(value) || charAndNumberSix.test(value) || letterSix.test(value))) {
          callback(new Error('至少六位，数字，小写字母，字符组合，不能为纯数字'))
        } else if (this.userNameCheck === false) {
          callback(new Error('该用户已被注册'))
        } else {
          callback()
        }
      }
      return {
        userNameCheck: true,
        dialogRegist: false,
        dialogClickInfo: false,
        token: '',
        activeName: 'first',
        userType: '2',
        value4: false,
        radio: '1',
        provinces: [],
        citys: [],
        districts: [],
        companyForm: {
          tokenCheck: true,
          userType: '2', // 企业账户
          userTypeTemp: false,
          userName: '',
          password: '',
          ensurePassword: '',
          companyContactTel: '',
          checkNum: '',
          companyPrincipalName: '',
          companyPrincipalIdCode: '',
          companyPrincipalTel: '',
          companyTel: '',
          companyName: '',
          companyToPublicBank: '',
          companyShortName: '',
          companyToPublicAccountBank: '',
          companyContact: '',
          companyTaxpayerRegistrationNumber: '',
          companyContactIdCard: '',
          companyFax: '',
          companyRegistedCapital: '',
          companyEmail: '',
          companyProvince: '',
          companyCity: '',
          companyDistrict: '',
          companyAddress: '',
          companyIsThreeInOne: '',
          companyUnifiedSocialCreditLdentifier: '',
          companyUnifiedSocialDatetime: '',
          companyUnifiedSocialLimitDatetime: '',
          companyOrganizationCode: '',
          companyLicenseNo: '',
          companyTaxNo: ''
        },
        companyRules: {
          userName: [
            {required: true, validator: userName, trigger: 'blur'}
          ],
          password: [
            {required: true, validator: password, trigger: 'blur'}
          ],
          ensurePassword: [
            {required: true, validator: checkPassword, trigger: 'blur'}
          ],
          companyContactTel: [
            {required: true, message: '手机号不能为空', trigger: 'blur'},
            {pattern: mobileReg, message: '请输入正确的手机号', trigger: 'blur'}
          ],
          companyName: [
            {required: true, message: '企业名称不能为空', trigger: 'blur'}
          ],
          companyContact: [
            {required: true, message: '联系人不能为空', trigger: 'blur'}
          ],
          companyContactIdCard: [
            {required: true, message: '联系人身份证号不能为空', trigger: 'blur'},
            {pattern: cardReg, message: '请输入正确的身份证号', trigger: 'blur'}
          ],
          companyRegistedCapital: [
            {pattern: numberReg, message: '请输入纯数字', trigger: 'blur'}
          ],
          companyEmail: [
            {required: true, message: '邮箱不能为空', trigger: 'blur'},
            {pattern: emailReg, message: '请输入正确的邮箱', trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      // 获取省
      getProvince() {
        api.get('/bind/province').then(reponse => {
          if (reponse.data.code === 1) {
            this.provinces = reponse.data.data
          }
        })
      },
      // 选择省份后清空市和区/县并查询市
      getCity(item) {
        this.companyForm.companyCity = ''
        this.companyForm.companyDistrict = ''
        this.citys = []
        this.districts = []
        api.post('/bind/city', {
          'areaId': item.areaId
        }).then(reponse => {
          if (reponse.data.code === 1) {
            this.citys = reponse.data.data
          }
        })
      },
      // 选择市后清空区/县并查询区/县
      getDistrict(item) {
        this.companyForm.companyDistrict = ''
        this.districts = []
        api.post('/bind/district', {
          'areaId': item.areaId
        }).then(reponse => {
          if (reponse.data.code === 1) {
            this.districts = reponse.data.data
          }
        })
      },
      // 提交注册
      submitForm(formName) {
        var formData = new FormData();
        debugger
        if(this.companyForm.userTypeTemp){
          this.companyForm.userType = 3;
        }else{
          this.companyForm.userType = 2;
        }
        var userForm = this.companyForm;
        this.$refs[formName].validate((valid) => {
          if (valid) {
            formData.append('userStr', JSON.stringify(userForm));
            api.post('/user/register/', formData).then(response => {
              if (response.data.code === 1) {
                this.$refs[formName].resetFields();
                Message.MessageSuccess(response.data.data);
                this.$router.push('/login')
              } else {
                Message.MessageError(response.data.msg)
                this.companyForm.tokenCheck = true
              }
            });
          } else {
            return false;
          }
        });
      },
      disagreeRegister() {
        this.$router.push('/login');
      }
    },
    mounted() {
      setTimeout(e => {
        this.dialogRegist = true;
      }, 500);
      this.getProvince()
    }
  }
</script>
<style scoped>


  /* 表单宽度 */
  .register .el-row {
    width: 70%;
    margin: 0 auto;
  }

  /* 图片选择框大小 */
  .register .el-upload {
    width: 100%;
    margin: auto auto;
  }

  .register .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    line-height: 110px;
    text-align: center;
  }

  /* 身份证图片大小*/

  .register .avatar {
    width: 100%;
    height: 100%;
  }

  .register .avatar2 {
    width: 80%;
    height: 80%;
  }
  .user-prompt{
    position: absolute;
    font-size: 12px;
    top: -30px;
    color: #0099ff;
  }
</style>
