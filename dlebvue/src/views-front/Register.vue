<template>
  <div>
    <login-head/>
    <div class="page1180" style="padding-top:0;">
      <div class="register">
        <div>
          <el-tabs v-model="userType" @tab-click="userTypeTabClick" id="registerTab">
            <el-tab-pane name="2">
            <span slot="label">
              <i class="re-tit-icon"></i>
              企业账户
            </span>
            </el-tab-pane>
            <el-tab-pane name="1">
            <span slot="label">
              <i class="re-tit-icon"></i>
              个人账户
            </span>
            </el-tab-pane>
          </el-tabs>
        </div>
        <div class="company" v-show="userType == 2">
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
                <el-form-item  label="">
                  <div ref="c2"></div>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="确认密码" prop="ensurePassword">
                  <el-input type="password" v-model="companyForm.ensurePassword"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item class="verification-input" label="手机验证码" prop="checkNum">
                  <el-input type="text" v-model="companyForm.checkNum"></el-input>
                </el-form-item>
                <el-button class="verification-btn" type="primary" :disabled="this.companyForm.tokenCheck"
                           @click="getVerification2(companyForm.companyContactTel)"
                           id="company">获取验证码
                </el-button>
              </el-col>
            </el-row>

            <div class="form-title">
              企业信息
            </div>
            <el-row>
              <el-col :span="12">
                <el-form-item label="企业法人名称" prop="companyPrincipalName">
                  <el-input type="text" v-model="companyForm.companyPrincipalName"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="法人身份证" prop="companyPrincipalIdCode">
                  <el-input type="text" v-model="companyForm.companyPrincipalIdCode"></el-input>
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
                <el-form-item label="对公账户开户行" prop="companyToPublicBank">
                  <el-input type="text" v-model="companyForm.companyToPublicBank"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="企业简称" prop="companyShortName">
                  <el-input type="text" v-model="companyForm.companyShortName"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="对公账户账号" prop="companyToPublicAccountBank">
                  <el-input type="text" v-model="companyForm.companyToPublicAccountBank"></el-input>
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
                <el-form-item label="纳税人识别号" prop="companyTaxpayerRegistrationNumber">
                  <el-input type="text" v-model="companyForm.companyTaxpayerRegistrationNumber"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="联系人身份证" prop="companyContactIdCard">
                  <el-input type="text" v-model="companyForm.companyContactIdCard"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="公司传真" prop="companyFax">
                  <el-input type="text" v-model="companyForm.companyFax"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="注册资金" prop="companyRegistedCapital">
                  <el-input type="text" v-model="companyForm.companyRegistedCapital"><i
                    slot="suffix">万元</i></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="公司邮箱" prop="companyEmail">
                  <el-input type="text" v-model="companyForm.companyEmail"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-form-item class="address-input" label="公司地址" prop="shipperCity">
                  <el-select v-model="companyForm.companyProvince" placeholder="全部">
                    <el-option v-for="item in provinces" @click.native="getCity(item)" :key="item.areaId"
                               :label="item.areaName" :value="item.areaName">
                    </el-option>
                  </el-select>
                  <el-select v-model="companyForm.companyCity" placeholder="全部">
                    <el-option v-for="item in citys" @click.native="getDistrict(item)" :key="item.areaId"
                               :label="item.areaName" :value="item.areaName">
                    </el-option>
                  </el-select>
                  <el-select v-model="companyForm.companyDistrict" placeholder="全部">
                    <el-option v-for="item in districts" :key="item.areaId" :label="item.areaName"
                               :value="item.areaName">
                    </el-option>
                  </el-select>
                  <el-input v-model="companyForm.companyAddress" placeholder="详细地址"></el-input>
                </el-col>
              </el-form-item>
            </el-row>
            <div class="form-title">
              证件信息
            </div>
            <!-- 三证合一是 -->
            <el-row v-show="!value4">
              <el-col :span="12">
                <el-form-item label="是否三证合一" prop="password">
                  <el-switch v-model="value4" active-color="#ff4949" inactive-color="#13ce66" active-text="否"
                             inactive-text="是">
                  </el-switch>
                </el-form-item>
                <el-form-item label="统一社会信用代码" prop="companyUnifiedSocialCreditLdentifier">
                  <el-input type="text" v-model="companyForm.companyUnifiedSocialCreditLdentifier"></el-input>
                </el-form-item>
                <el-form-item label="注册时间">
                  <el-date-picker type="date" v-model="companyForm.companyUnifiedSocialDatetime"
                                  format="yyyy-MM-dd" value-format="yyyy-MM-dd" placeholder="选择日期"></el-date-picker>
                </el-form-item>
                <el-form-item label="有效期至">
                  <el-date-picker type="date"
                                  v-model="companyForm.companyUnifiedSocialLimitDatetime" format="yyyy-MM-dd"
                                  value-format="yyyy-MM-dd" placeholder="选择日期"></el-date-picker>
                </el-form-item>
              </el-col>
              <el-col :span="8" :offset="2" class="upload-box">
                <el-form-item label="统一社会信用照片">
                </el-form-item>
                <el-upload ref="font" action="" :file-list="uploadUnifiedSocialFile" :show-file-list="true" :limit="2"
                           :on-change="showUnifiedSocial" :on-remove="unifiedSocialRemove" :auto-upload="false">
                  <img v-if="uploadUnifiedSocialFile[0]" :src="uploadUnifiedSocialFile[0].url" class="avatar">
                  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>
              </el-col>
            </el-row>
            <!-- 三证合一否 -->
            <el-row v-show="value4">
              <el-col :span="12">
                <el-form-item label="是否三证合一" prop="password">
                  <el-switch v-model="value4" active-color="#ff4949" inactive-color="#13ce66" active-text="否"
                             inactive-text="是">
                  </el-switch>
                </el-form-item>
                <el-form-item label="营业执照号码" prop="companyLicenseNo">
                  <el-input type="text" v-model="companyForm.companyLicenseNo"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="组织机构代码" prop="companyOrganizationCode">
                  <el-input type="text" v-model="companyForm.companyOrganizationCode"></el-input>
                </el-form-item>
                <el-form-item label="税务登记证" prop="companyTaxNo">
                  <el-input type="text" v-model="companyForm.companyTaxNo"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="24">
                <el-col :span="8" class="upload-box">
                  <el-form-item label="营业执照照片">
                  </el-form-item>
                  <el-upload ref="font" action="" :file-list="uploadLicenseFile" :show-file-list="true" :limit="2"
                             :on-change="showLicense" :on-remove="licenseRemove" :auto-upload="false">
                    <img v-if="uploadLicenseFile[0]" :src="uploadLicenseFile[0].url" class="avatar">
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                  </el-upload>
                </el-col>
                <el-col :span="8" class="upload-box">
                  <el-form-item label="组织机构代码照片">
                  </el-form-item>
                  <el-upload ref="font" action="" :file-list="uploadOrganizationFile" :show-file-list="true" :limit="2"
                             :on-change="showOrganization" :on-remove="organizationRemove" :auto-upload="false">
                    <img v-if="uploadOrganizationFile[0]" :src="uploadOrganizationFile[0].url" class="avatar">
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                  </el-upload>
                </el-col>
                <el-col :span="8" class="upload-box">
                  <el-form-item label="税务登记证照片">
                  </el-form-item>
                  <el-upload ref="font" action="" :file-list="uploadTaxFile" :show-file-list="true" :limit="2"
                             :on-change="showTax" :on-remove="taxRemove" :auto-upload="false">
                    <img v-if="uploadTaxFile[0]" :src="uploadTaxFile[0].url" class="avatar">
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                  </el-upload>
                </el-col>

              </el-col>
            </el-row>
            <el-row style="width:100%;text-align:center">
              <el-button style="margin:20px 0 20px;display:inline-block;width:80px" type="primary"
                         @click="submitForm('companyForm')">提交
              </el-button>
            </el-row>
          </el-form>
        </div>

        <div class="person" v-show="userType == 1">
          <el-form ref="personalForm" class="often-input-list" :rules="personalRules" :model="personalForm"
                   label-width="140px">
            <div class="form-title">
              注册信息
            </div>
            <el-row>
              <el-col :span="12">
                <el-form-item label="用户名" prop="userName">
                  <el-input v-model="personalForm.userName"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="联系人手机号" prop="userMobile">
                  <el-input v-model="personalForm.userMobile"></el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="12">
                <el-form-item label="密码" prop="password">
                  <el-input type="password" v-model="personalForm.password"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="">
                  <div ref="c1"></div>
                </el-form-item>

              </el-col>
            </el-row>
            <el-row>
              <el-col :span="12">
                <el-form-item label="确认密码" prop="checkPassword">
                  <el-input type="password" v-model="personalForm.checkPassword"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-col :span="18">
                  <el-form-item class="verification-input" label="手机验证码" prop="checkNum">
                    <el-input type="text" v-model="personalForm.checkNum"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="4">
                  <el-button type="primary" :disabled="this.personalForm.tokenCheck"
                             @click="getVerification1(personalForm.userMobile)" class="verification-btn" id="personal">
                    获取验证码
                  </el-button>
                </el-col>
              </el-col>
            </el-row>
            <div class="form-title">
              个人信息
            </div>
            <el-row>
              <el-col :span="12">
                <el-form-item label="姓名" prop="userCnName">
                  <el-input v-model="personalForm.userCnName"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="userEmail">
                  <el-input v-model="personalForm.userEmail"></el-input>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="证件类型">
                  <el-input v-model="personalForm.userCertificateType" disabled="disabled"></el-input>
                </el-form-item>
                <el-form-item label="证件号码" prop="userCertificateNumber">
                  <el-input v-model="personalForm.userCertificateNumber"></el-input>
                </el-form-item>
              </el-col>
            </el-row>

            <el-row>
              <el-col :span="8" :offset="4" class="upload-box">
                <el-form-item label="上传证件（正面）">
                </el-form-item>
                <el-upload ref="font" action="" :file-list="uploadFontFile" :show-file-list="true" :limit="2"
                           :on-change="showFont" :on-remove="fontRemove" :auto-upload="false">
                  <img v-if="uploadFontFile[0]" :src="uploadFontFile[0].url" class="avatar">
                  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>
              </el-col>
              <el-col :span="8" class="upload-box">
                <el-form-item label="上传证件（反面）">
                </el-form-item>
                <el-upload ref="back" action="" :file-list="uploadBackFile" :show-file-list="true" :limit="2"
                           :on-change="showBack" :on-remove="backRemove" :auto-upload="false">
                  <img v-if="uploadBackFile[0]" :src="uploadBackFile[0].url" class="avatar">
                  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>
              </el-col>
            </el-row>
            <el-row style="text-align:center">
              <el-button style="margin:20px 0 20px;display:inline-block;width:80px" type="primary"
                         @click="submitForm('personalForm')">提交
              </el-button>
            </el-row>

          </el-form>
        </div>

        <el-dialog center :visible.sync="dialogRegist" :close-on-click-modal="dialogClickInfo"
                   :close-on-press-escape="dialogClickInfo" :show-close="dialogClickInfo">
          <div class="el-dialog-title">
            服务协议及隐私权政策
          </div>
          <div class="register-dialog-content">
            <p>
              亲爱的用户，德邻e宝依照相关法规要求进行实名制管理、履行反洗钱职责和采取风险防范措施。为了您可以正常使用德邻e宝服务，您的身份信息、联系方式、交易信息需要被依法收集并使用，德邻宝将严格保护您的个人信息，确保信息安全。</p>
            <p></p>
            <p>您在点击同意下列协议前，请您务必审慎阅读，并充分理解协议条款内容，具体详见
              <router-link to="/ServiceAgreement" style="color: #00acd6;text-decoration: none" target="_blank">
                《德邻e宝服务协议》。
              </router-link>
            </p>
          </div>
          <el-row style="text-align:center;width:100%;">
            <el-button style="margin:0 20px;" type="primary" @click="dialogRegist = false">同意</el-button>
            <el-button style="margin:0 20px;" type="primary" @click="disagreeRegister">不同意</el-button>
          </el-row>

        </el-dialog>
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
  // 组织机构代码
  var companyOrganizationCodeReg = /^[a-zA-Z0-9]{8}-[a-zA-Z0-9]$/;
  // 统一信用代码
  var companyUnifiedSocialCreditLdentifierReg = /^([0-9ABCDEFGHJKLMNPQRTUWXY]{2})([0-9]{6})([0-9ABCDEFGHJKLMNPQRTUWXY]{9})([0-9Y])$/;
  // 15位数字
  var fifteenReg = /^\d{15}$/;
  // 纳税人识别号
  var companyTaxpayerRegistrationNumberReg = /^((\d{6}[0-9A-Z]{9})|([0-9A-Za-z]{2}\d{6}[0-9A-Za-z]{10}))$/;
  // 银行卡号
  var bankCardReg = /^([1-9]{1})(\d{14,18})$/;
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
        } else if (value !== this.personalForm.password && this.userType === '1') {
          callback(new Error('请确保两次输入一致'))
        } else if (value !== this.companyForm.password && this.userType === '2') {
          callback(new Error('请确保两次输入一致'))
        } else {
          callback()
        }
      }
      var validateNum = (rule, value, callback) => {
        if (!value) {
          callback(new Error('请输入证件号码'))
        } else if (!cardReg.test(value) && this.personalForm.userCertificateType === '身份证') {
          callback(new Error('请输入正确的身份证格式'))
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
        checkNum: null, // 手机验证码
        uploadFontFile: [],
        uploadBackFile: [],
        uploadUnifiedSocialFile: [],
        uploadLicenseFile: [],
        uploadOrganizationFile: [],
        uploadTaxFile: [],
        provinces: [],
        citys: [],
        districts: [],
        personalForm: {
          tokenCheck: true,
          userType: '1', // 个人账户
          userName: '',
          password: '',
          checkPassword: '',
          userMobile: '',
          checkNum: '',
          userCnName: '',
          userCertificateType: '身份证',
          userCertificateNumber: '',
          userEmail: '',
          userIdCardFontImg: '',
          userIdCardBackImg: ''
        },
        personalRules: {
          userName: [
            {required: true, validator: userName, trigger: 'blur'}
          ],
          password: [
            {required: true, validator: password, trigger: 'blur'}
          ],
          checkPassword: [
            {required: true, validator: checkPassword, trigger: 'blur'}
          ],
          userMobile: [
            {required: true, message: '手机号不能为空', trigger: 'blur'},
            {pattern: mobileReg, message: '请输入正确的手机号', trigger: 'blur'}
          ],
          checkNum: [
            {required: true, message: '验证码不能为空', trigger: 'blur'}
          ],
          userCnName: [
            {required: true, message: '姓名不能为空', trigger: 'blur'}
          ],
          userCertificateNumber: [
            {required: true, validator: validateNum, trigger: 'blur'}
          ],
          userEmail: [
            {required: true, message: '邮箱不能为空', trigger: 'blur'},
            {pattern: emailReg, message: '请输入正确的邮箱', trigger: 'blur'}
          ]
        },
        companyForm: {
          tokenCheck: true,
          userType: '2', // 企业账户
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
          checkNum: [
            {required: true, message: '验证码不能为空', trigger: 'blur'}
          ],
          companyFax: [
            {pattern: companyFaxReg, message: '请输入正确的传真号码', trigger: 'blur'}
          ],
          companyPrincipalName: [
            {required: true, message: '法人名称不能为空', trigger: 'blur'}
          ],
          companyPrincipalIdCode: [
            {required: true, message: '法人身份证号不能为空', trigger: 'blur'},
            {pattern: cardReg, message: '请输入正确的身份证号', trigger: 'blur'}
          ],
          companyPrincipalTel: [
            {required: true, message: '法人手机号不能为空', trigger: 'blur'},
            {pattern: mobileReg, message: '请输入正确的手机号', trigger: 'blur'}
          ],
          companyName: [
            {required: true, message: '企业名称不能为空', trigger: 'blur'}
          ],
          companyToPublicBank: [
            {required: true, message: '对公账户开户行不能为空', trigger: 'blur'}
          ],
          companyToPublicAccountBank: [
            {required: true, message: '对公账户账号不能为空', trigger: 'blur'},
            {pattern: bankCardReg, message: '请输入正确的对公账户账号', trigger: 'blur'}
          ],
          companyContact: [
            {required: true, message: '联系人不能为空', trigger: 'blur'}
          ],
          companyTaxpayerRegistrationNumber: [
            {required: true, message: '纳税人识别号不能为空', trigger: 'blur'},
            {pattern: companyTaxpayerRegistrationNumberReg, message: '请输入正确的纳税人识别号', trigger: 'blur'}
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
          ],
          companyOrganizationCode: [
            {pattern: companyOrganizationCodeReg, message: '请输入正确的组织机构代码', trigger: 'blur'}
          ],
          companyUnifiedSocialCreditLdentifier: [
            {pattern: companyUnifiedSocialCreditLdentifierReg, message: '请输入正确的统一信用代码', trigger: 'blur'}
          ],
          companyLicenseNo: [
            {pattern: fifteenReg, message: '请输入正确的营业执照号码', trigger: 'blur'}
          ],
          companyTaxNo: [
            {pattern: fifteenReg, message: '请输入正确的税务登记证号码', trigger: 'blur'}
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
      //  获取个人短信验证码
      getVerification1(mobile) {
        if (this.token === '') {
          Message.MessageError('请先完成图片验证')
        } else {
          if (mobile && mobileReg.test(mobile)) {
            api.post('/user/register/sendRegistVerificationCode', qs.stringify({
              mobile: mobile,
              token: this.token
            })).then(response => {
              if (response.data.code === 1) {
                Message.MessageSuccess(response.data.msg);
                var validCode = true;
                var time = 60;
                if (validCode) {
                  validCode = false;
                  this.personalForm.tokenCheck = true
                  var th = this
                  var t = setInterval(function () {
                    time--;
                    document.getElementById('personal').innerHTML = '等待' + time + '秒';
                    if (time === 0) {
                      clearInterval(t);
                      validCode = true;
                      _dx.Captcha(th.$refs.c1, {
                        appId: 'e9a1d305fc43f48f94ea8ae35ed93be2', //  appId,开通服务后可在控制台中“服务管理”模块获取
                        style: 'inline',
                        success: function (token) {
                          th.token = token
                        }
                      }).reload()
                      document.getElementById('personal').innerHTML = '重新获取';
                      th.personalForm.tokenCheck = false
                    }
                  }, 1000);
                }
              } else {
                Message.MessageError(response.data.msg);
                this.personalForm.tokenCheck = true
                var that = this
                _dx.Captcha(this.$refs.c1, {
                  appId: 'e9a1d305fc43f48f94ea8ae35ed93be2', //  appId,开通服务后可在控制台中“服务管理”模块获取
                  style: 'inline',
                  success: function (token) {
                    that.token = token
                  }
                }).reload()
              }
            });
          } else {
            Message.MessageError('请输入正确的手机号码');
          }
        }
      },
      //  获取企业短信验证码
      getVerification2(mobile) {
        if (this.token === '') {
          Message.MessageError('请先完成图片验证')
        } else {
          if (mobile && mobileReg.test(mobile)) {
            api.post('/user/register/sendRegistVerificationCode', qs.stringify({
              mobile: mobile,
              token: this.token
            })).then(response => {
              if (response.data.code === 1) {
                Message.MessageSuccess(response.data.msg);
                var validCode = true;
                var time = 60;
                if (validCode) {
                  validCode = false;
                  this.companyForm.tokenCheck = true
                  var th = this
                  var t = setInterval(function () {
                    time--;
                    document.getElementById('company').innerHTML = '等待' + time + '秒';
                    if (time === 0) {
                      clearInterval(t);
                      validCode = true;
                      _dx.Captcha(th.$refs.c2, {
                        appId: 'e9a1d305fc43f48f94ea8ae35ed93be2', //  appId,开通服务后可在控制台中“服务管理”模块获取
                        style: 'inline',
                        success: function (token) {
                          th.token = token
                        }
                      }).reload()
                      document.getElementById('company').innerHTML = '重新获取';
                      th.companyForm.tokenCheck = false
                    }
                  }, 1000);
                }
              } else {
                Message.MessageError(response.data.msg);
                this.personalForm.tokenCheck = true
                this.companyForm.tokenCheck = true
                var that = this
                _dx.Captcha(this.$refs.c1, {
                  appId: 'e9a1d305fc43f48f94ea8ae35ed93be2', //  appId,开通服务后可在控制台中“服务管理”模块获取
                  style: 'inline',
                  success: function (token) {
                    that.token = token
                    that.personalForm.tokenCheck = false
                  }
                }).reload()
                _dx.Captcha(this.$refs.c2, {
                  appId: 'e9a1d305fc43f48f94ea8ae35ed93be2', //  appId,开通服务后可在控制台中“服务管理”模块获取
                  style: 'inline',
                  success: function (token) {
                    that.token = token
                    that.companyForm.tokenCheck = false
                  }
                }).reload()
              }
            });
          } else {
            Message.MessageError('请输入正确的手机号码');
          }
        }
      },
      // 身份证正面上传预览
      showFont(file, fileList) {
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isLt2M) {
          this.$message.error('上传图片大小不能超过 2MB!');
        }
        this.uploadFontFile.pop();
        this.uploadFontFile.push(file);
      },
      // 身份证反面上传预览
      showBack(file, fileList) {
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isLt2M) {
          this.$message.error('上传图片大小不能超过 2MB!');
        }
        this.uploadBackFile.pop();
        this.uploadBackFile.push(file);
      },
      // 统一信用代码照片上传预览
      showUnifiedSocial(file, fileList) {
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isLt2M) {
          this.$message.error('上传图片大小不能超过 2MB!');
        }
        this.uploadUnifiedSocialFile.pop();
        this.uploadUnifiedSocialFile.push(file);
      },
      // 营业执照上传预览
      showLicense(file, fileList) {
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isLt2M) {
          this.$message.error('上传图片大小不能超过 2MB!');
        }
        this.uploadLicenseFile.pop();
        this.uploadLicenseFile.push(file);
      },
      // 组织机构代码证上传预览
      showOrganization(file, fileList) {
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isLt2M) {
          this.$message.error('上传图片大小不能超过 2MB!');
        }
        this.uploadOrganizationFile.pop();
        this.uploadOrganizationFile.push(file);
      },
      // 税务登记照片上传预览
      showTax(file, fileList) {
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isLt2M) {
          this.$message.error('上传图片大小不能超过 2MB!');
        }
        this.uploadTaxFile.pop();
        this.uploadTaxFile.push(file);
      },
      // 身份证正面图片移除
      fontRemove() {
        this.uploadFontFile.pop();
      },
      // 身份证反面图片移除
      backRemove() {
        this.uploadBackFile.pop();
      },
      // 统一信用代码照片移除
      unifiedSocialRemove() {
        this.uploadUnifiedSocialFile.pop();
      },
      // 营业执照移除
      licenseRemove() {
        this.uploadLicenseFile.pop();
      },
      // 组织机构代码证移除
      organizationRemove() {
        this.uploadOrganizationFile.pop();
      },
      // 税务登记照片移除
      taxRemove() {
        this.uploadTaxFile.pop();
      },
      // 提交注册
      submitForm(formName) {
        var formData = new FormData();
        var userType = this.userType;
        var userForm = {};
        this.$refs[formName].validate((valid) => {
          if (valid) {
            if (userType === '1') { // 个人注册
              if ((this.uploadFontFile.length !== 1) || (this.uploadBackFile.length !== 1)) {
                Message.MessageInfo('请上传身份证！');
                return false;
              }
              this.personalForm.userCertificateType = '1'
              userForm = this.personalForm;
              formData.append('font', this.uploadFontFile[0].raw);
              formData.append('back', this.uploadBackFile[0].raw);
              formData.append('companyOrganizationCodeImg', '');
              formData.append('companyLicenseNoImg', '');
              formData.append('companyTaxNoImg', '');
              formData.append('userVerificationCode', this.personalForm.checkNum);
            } else { // 企业注册
              if (!this.value4) {
                if (this.companyForm.companyUnifiedSocialCreditLdentifier === '') {
                  Message.MessageInfo('统一信用代码不能为空！');
                  return false;
                }
                if (this.uploadUnifiedSocialFile.length === 1) { // 三证合一
                  this.companyForm.companyIsThreeInOne = 1;
                  userForm = this.companyForm;
                  formData.append('font', this.uploadUnifiedSocialFile[0].raw);
                  formData.append('back', '');
                  formData.append('companyOrganizationCodeImg', '');
                  formData.append('companyLicenseNoImg', '');
                  formData.append('companyTaxNoImg', '');
                  formData.append('userVerificationCode', this.companyForm.checkNum);
                } else {
                  Message.MessageInfo('请上传统一信用照片！');
                  return false;
                }
              } else {
                if (this.companyForm.companyOrganizationCode === '' || this.companyForm.companyLicenseNo === '' || this.companyForm.companyTaxNo === '') {
                  Message.MessageInfo('三证代码不能有空！');
                  return false;
                }
                if (this.uploadLicenseFile.length === 1 && this.uploadOrganizationFile.length === 1 && this.uploadTaxFile.length === 1) { // 非三证合一
                  this.companyForm.companyIsThreeInOne = 0;
                  userForm = this.companyForm;
                  formData.append('font', '');
                  formData.append('back', '');
                  formData.append('companyOrganizationCodeImg', this.uploadOrganizationFile[0].raw);
                  formData.append('companyLicenseNoImg', this.uploadLicenseFile[0].raw);
                  formData.append('companyTaxNoImg', this.uploadTaxFile[0].raw);
                  formData.append('userVerificationCode', this.companyForm.checkNum);
                } else {
                  Message.MessageInfo('请上传全部照片！');
                  return false;
                }
              }
            }
            formData.append('userStr', JSON.stringify(userForm));
            api.post('/user/register/', formData).then(response => {
              if (response.data.code === 1) {
                this.$refs[formName].resetFields();
                this.uploadFontFile = [];
                this.uploadBackFile = [];
                Message.MessageSuccess(response.data.data);
                this.$router.push('/login')
              } else {
                Message.MessageError(response.data.msg)
                this.personalForm.tokenCheck = true
                this.companyForm.tokenCheck = true
                var that = this
                _dx.Captcha(this.$refs.c1, {
                  appId: 'e9a1d305fc43f48f94ea8ae35ed93be2', //  appId,开通服务后可在控制台中“服务管理”模块获取
                  style: 'inline',
                  success: function (token) {
                    that.token = token
                    that.personalForm.tokenCheck = false
                  }
                }).reload()
                _dx.Captcha(this.$refs.c2, {
                  appId: 'e9a1d305fc43f48f94ea8ae35ed93be2', //  appId,开通服务后可在控制台中“服务管理”模块获取
                  style: 'inline',
                  success: function (token) {
                    that.token = token
                    that.companyForm.tokenCheck = false
                  }
                }).reload()
              }
            });
          } else {
            return false;
          }
        });
      },
      userTypeTabClick(val) {
        if (this.userType === '2') {
          this.personalForm.userName = ''
          this.personalForm.userMobile = ''
          this.personalForm.password = ''
          this.personalForm.checkPassword = ''
          this.personalForm.checkNum = ''
          this.personalForm.userCnName = ''
          this.personalForm.userCertificateNumber = ''
          this.personalForm.userEmail = ''
          this.uploadFontFile = []
          this.uploadBackFile = []
        } else if (this.userType === '1') {
          this.companyForm.userName = ''
          this.companyForm.password = ''
          this.companyForm.ensurePassword = ''
          this.companyForm.companyContactTel = ''
          this.companyForm.checkNum = ''
          this.companyForm.companyPrincipalName = ''
          this.companyForm.companyPrincipalTel = ''
          this.companyForm.companyPrincipalIdCode = ''
          this.companyForm.companyTel = ''
          this.companyForm.companyName = ''
          this.companyForm.companyToPublicBank = ''
          this.companyForm.companyToPublicAccountBank = ''
          this.companyForm.companyShortName = ''
          this.companyForm.companyContact = ''
          this.companyForm.companyTaxpayerRegistrationNumber = ''
          this.companyForm.companyContactIdCard = ''
          this.companyForm.companyFax = ''
          this.companyForm.companyRegistedCapital = ''
          this.companyForm.companyEmail = ''
          this.companyForm.companyProvince = ''
          this.companyForm.companyCity = ''
          this.companyForm.companyDistrict = ''
          this.companyForm.companyAddress = ''
          this.companyForm.companyIsThreeInOne = true
          this.companyForm.companyUnifiedSocialCreditLdentifier = ''
          this.companyForm.companyUnifiedSocialDatetime = ''
          this.companyForm.companyUnifiedSocialLimitDatetime = ''
          this.companyForm.companyOrganizationCode = ''
          this.companyForm.companyLicenseNo = ''
          this.companyForm.companyTaxNo = ''
          this.uploadUnifiedSocialFile = []
          this.uploadLicenseFile = []
          this.uploadOrganizationFile = []
          this.uploadTaxFile = []
        }
        setTimeout(e => {
          this.dialogRegist = true;
        }, 500);
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
      var that = this
      var myCaptcha = _dx.Captcha(this.$refs.c1, {
        appId: 'e9a1d305fc43f48f94ea8ae35ed93be2', //  appId,开通服务后可在控制台中“服务管理”模块获取
        style: 'inline',
        success: function (token) {
          that.token = token
          that.personalForm.tokenCheck = false
        }
      })
      var myCaptcha2 = _dx.Captcha(this.$refs.c2, {
        appId: 'e9a1d305fc43f48f94ea8ae35ed93be2', //  appId,开通服务后可在控制台中“服务管理”模块获取
        style: 'inline',
        success: function (token) {
          that.token = token
          that.companyForm.tokenCheck = false
        }
      })
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
