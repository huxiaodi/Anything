<template>
  <div class="admin-input-two-form">
    <div class="divTitle">会员信息修改</div>
    <el-form ref="companyForm" :rules="companyRules" :model="companyForm" label-width="150px">
      <el-row>
        <el-col :span="12">
          <el-form-item label="用户名:">
            <div align="left">{{this.companyInfo.user_name}}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="绑定手机号:">
            <div align="left"> {{this.companyInfo.user_mobile}}</div>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="企业法人名称:" prop="companyPrincipalName">
            <el-input type="text" v-model="companyForm.companyPrincipalName"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="法人身份证:" prop="companyPrincipalIdCode">
            <el-input type="text" v-model="companyForm.companyPrincipalIdCode"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="法人手机号:" prop="companyPrincipalTel">
            <el-input type="text" v-model="companyForm.companyPrincipalTel"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="公司电话:" prop="companyTel">
            <el-input type="text" v-model="companyForm.companyTel"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="企业名称:" prop="companyName">
            <el-input type="text" v-model="companyForm.companyName"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="对公账户开户行:" prop="companyToPublicBank">
            <el-input type="text" v-model="companyForm.companyToPublicBank"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="企业简称:" prop="companyShortName">
            <el-input type="text" v-model="companyForm.companyShortName"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="对公账户账号:" prop="companyToPublicAccountBank">
            <el-input type="text" v-model="companyForm.companyToPublicAccountBank"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="联系人:" prop="companyContact">
            <el-input type="text" v-model="companyForm.companyContact"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="纳税人识别号:" prop="companyTaxpayerRegistrationNumber">
            <el-input type="text" v-model="companyForm.companyTaxpayerRegistrationNumber"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="联系人身份证号码:" prop="companyContactIdCard">
            <el-input type="text" v-model="companyForm.companyContactIdCard"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="公司传真:" prop="companyFax">
            <el-input type="text" v-model="companyForm.companyFax"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="注册资金:" prop="companyRegistedCapital">
            <el-input type="text" v-model="companyForm.companyRegistedCapital"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="公司邮箱:" prop="companyEmail">
            <el-input type="text" v-model="companyForm.companyEmail"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row class="admin-address-input">
        <el-form-item label="公司地址:" prop="shipperCity">
          <el-col style="width:720px">
            <el-select v-model="companyForm.companyProvince" placeholder="全部" style="width: 20%;">
              <el-option
                v-for="item in provinces"
                @click.native="getCity(item)"
                :key="item.areaId"
                :label="item.areaName"
                :value="item.areaName">
              </el-option>
            </el-select>
            <el-select v-model="companyForm.companyCity" placeholder="全部" style="width: 20%;">
              <el-option
                v-for="item in citys"
                @click.native="getDistrict(item)"
                :key="item.areaId"
                :label="item.areaName"
                :value="item.areaName">
              </el-option>
            </el-select>
            <el-select v-model="companyForm.companyDistrict" placeholder="全部" style="width: 20%;">
              <el-option
                v-for="item in districts"
                :key="item.areaId"
                :label="item.areaName"
                :value="item.areaName">
              </el-option>
            </el-select>
            <el-input style="width: 38%;" v-model="companyForm.companyAddress" placeholder="详细地址"></el-input>
          </el-col>

        </el-form-item>
      </el-row>
      <!-- 三证合一是 -->
      <el-row style="margin-bottom:20px;" v-if="this.companyForm.companyIsThreeInOne">
        <el-col :xs="20" :sm="8" align="center"
        style="box-sizing:border-box;padding:0 20px;text-align:center!important;">
          <el-form-item label="统一社会信用照片" class="avatar-uploader">
          </el-form-item>
          <img :src="this.companyForm.companyUnifiedSocialImg">
        </el-col>
      </el-row>
      <!-- 三证合一否 -->
      <el-row style="margin-bottom:20px;" v-if="!this.companyForm.companyIsThreeInOne">
        <el-col :xs="20" :sm="8" align="center" style="box-sizing:border-box;padding:0 20px;text-align:center!important;">
          <el-form-item label="营业执照照片">
          </el-form-item>
          <img :src="this.companyForm.companyLicenseNoImg">
        </el-col>
        <el-col :xs="20" :sm="8" align="center" style="box-sizing:border-box;padding:0 20px;text-align:center!important;">
          <el-form-item label="组织结构代码照片">
          </el-form-item>
          <img :src="this.companyForm.companyOrganizationCodeImg">
        </el-col>
        <el-col :xs="20" :sm="8" align="center" style="box-sizing:border-box;padding:0 20px;text-align:center!important;">
          <el-form-item label="税务登记证照片">
          </el-form-item>
          <img :src="this.companyForm.companyTaxNoImg">
        </el-col>
      </el-row>
      <div class="admin-input-form-btns">
        <el-button type="primary" @click="submitForm('passwordSettingForm')">提交</el-button>
      </div>
    </el-form>
  </div>
</template>

<script>
  import api from '@/api/fetch'
  import * as Message from '../utils/messageUtil'

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
  // 纯数字
  var numberReg = /^[0-9]*$/;

  export default {
    name: 'BusinessMemberEdit',
    data() {
      return {
        companyInfo: '',
        userType: '2',
        uploadUnifiedSocialFile: [],
        provinces: [],
        citys: [],
        districts: [],
        companyForm: {
          userType: '2', // 企业账户
          userName: '',
          companyContactTel: '',
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
          companyTaxNo: '',
          companyUnifiedSocialImg: ''
        },
        companyRules: {
          companyPrincipalName: [
            {required: true, message: '法人名称不能为空', trigger: 'blur'}
          ],
          companyPrincipalIdCode: [
            {required: true, message: '法人身份证号不能为空', trigger: 'blur'},
            {pattern: cardReg, message: '请输入正确的身份证号'}
          ],
          companyPrincipalTel: [
            {required: true, message: '法人手机号不能为空', trigger: 'blur'},
            {pattern: mobileReg, message: '请输入正确的手机号'}
          ],
          companyName: [
            {required: true, message: '企业名称不能为空', trigger: 'blur'}
          ],
          companyToPublicBank: [
            {required: true, message: '对公账户开户行不能为空', trigger: 'blur'}
          ],
          companyToPublicAccountBank: [
            {required: true, message: '对公账户账号不能为空', trigger: 'blur'}
          ],
          companyContact: [
            {required: true, message: '联系人不能为空', trigger: 'blur'}
          ],
          companyTaxpayerRegistrationNumber: [
            {required: true, message: '纳税人识别号不能为空', trigger: 'blur'}
          ],
          companyContactIdCard: [
            {required: true, message: '联系人身份证号不能为空', trigger: 'blur'},
            {pattern: cardReg, message: '请输入正确的身份证号'}
          ]
        }
      }
    },
    methods: {
      // 获取企业会员信息
      getBusinessMemberInfo() {
        api.post('admin/companyManage/company/info', {'id': this.companyInfo.company_id}).then(response => {
          if (response.data.code === 1) {
            this.companyForm = response.data.data
          }
        })
      },
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
        api.post('/bind/city', {'areaId': item.areaId}).then(reponse => {
          if (reponse.data.code === 1) {
            this.citys = reponse.data.data
          }
        })
      },
      // 选择市后清空区/县并查询区/县
      getDistrict(item) {
        this.companyForm.companyDistrict = ''
        this.districts = []
        api.post('/bind/district', {'areaId': item.areaId}).then(reponse => {
          if (reponse.data.code === 1) {
            this.districts = reponse.data.data
          }
        })
      },
      // 提交注册
      submitForm(formName) {
        // let userForm = this.companyForm;
        this.$refs[formName].validate((valid) => {
          if (valid) {
            api.post('/admin/companyManage/company/save', this.companyForm).then(response => {
              if (response.data.code === 1) {
                Message.MessageSuccess(response.data.msg);
                this.$router.go(-1);
              } else {
                Message.MessageError(response.data.msg);
              }
            });
          } else {
            return false;
          }
        });
      }
    },
    mounted() {
      this.companyInfo = this.$route.params;
      this.getBusinessMemberInfo();
      this.getProvince();
    }
  }
</script>
<style scoped>
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    line-height: 110px;
    text-align: center;
  }

  /* 统一信用照片图片大小*/
  .avatar {
    width: 100%;
    height: 100%;
  }
</style>
