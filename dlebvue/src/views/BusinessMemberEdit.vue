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
          <el-form-item label="企业名称:" prop="companyName">
            <el-input type="text" v-model="companyForm.companyName"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="企业简称:" prop="companyShortName">
            <el-input type="text" v-model="companyForm.companyShortName"></el-input>
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
          <el-form-item label="联系人身份证号码:" prop="companyPrincipalIdCode">
            <el-input type="text" v-model="companyForm.companyPrincipalIdCode"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
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
      <div class="admin-input-form-btns">
        <el-button type="primary" @click="submitForm('companyForm')">提交</el-button>
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
          companyPrincipalIdCode: [
            {required: true, message: '联系人身份证号不能为空', trigger: 'blur'},
            {pattern: cardReg, message: '请输入正确的身份证号'}
          ],
          companyName: [
            {required: true, message: '企业名称不能为空', trigger: 'blur'}
          ],
          companyContact: [
            {required: true, message: '联系人不能为空', trigger: 'blur'}
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
