<template>
  <div style="margin: 0 auto; width: 800px;">
    <el-form ref="companyForm" :model="companyForm" labelPosition="right" label-width="140px">

      <div class="divTitle">用户信息</div>
      <el-row>
        <el-col :span="12">
          <el-form-item label="用户名:">
            <div align="left">{{this.companyInfo.user_name}}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="绑定手机号:">
            <div align="left">{{this.companyInfo.user_mobile}}</div>
          </el-form-item>
        </el-col>
      </el-row>

      <div class="divTitle">企业信息</div>
      <el-row>
        <el-col :span="12">
          <el-form-item label="企业名称:" prop="companyName">
            <div align="left"> {{companyForm.companyName}}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="企业简称:" prop="companyShortName">
            <div align="left">{{companyForm.companyShortName}}</div>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="企业法人:" prop="companyPrincipalName">
            <div align="left">{{companyForm.companyPrincipalName}}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="法人身份证:" prop="companyPrincipalIdCode">
            <div align="left">{{companyForm.companyPrincipalIdCode}}</div>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="对公账户开户行:" prop="companyToPublicBank">
            <div align="left">{{companyForm.companyToPublicBank}}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="对公账户账号:" prop="companyToPublicAccountBank">
            <div align="left"> {{companyForm.companyToPublicAccountBank}}</div>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="纳税人识别号:" prop="companyTaxpayerRegistrationNumber">
            <div align="left">{{companyForm.companyTaxpayerRegistrationNumber}}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="注册资金:" prop="companyRegistedCapital">
            <div align="left">{{companyForm.companyRegistedCapital}}</div>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="公司传真:" prop="companyFax">
            <div align="left"> {{companyForm.companyFax}}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="公司邮箱:" prop="companyEmail">
            <div align="left">{{companyForm.companyEmail}}</div>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-form-item label="公司地址:" prop="shipperCity">
          <div align="left">
            {{companyForm.companyProvince + companyForm.companyCity + companyForm.companyDistrict + companyForm.companyAddress}}
          </div>
        </el-form-item>
      </el-row>
      <!-- 三证合一是 -->
      <el-row v-if="this.companyForm.companyIsThreeInOne">
        <el-col :xs="20" :sm="4">
          <el-form-item label="统一社会信用照片:" class="avatar-uploader">
          </el-form-item>
          <el-form class="avatar-uploader">
            <img v-if="this.companyForm.companyUnifiedSocialImg" :src="this.companyForm.companyUnifiedSocialImg"
                 class="avatar">
          </el-form>
        </el-col>
      </el-row>
      <!-- 三证合一否 -->
      <el-row v-if="!this.companyForm.companyIsThreeInOne">
        <el-col :xs="20" :sm="4" align="left" style="margin-left: 80px">
          <el-form-item label="营业执照照片:">
          </el-form-item>
          <img :src="this.companyForm.companyLicenseNoImg">
        </el-col>
        <el-col :xs="20" :sm="4" align="left" style="margin-left: 80px">
          <el-form-item label="组织结构代码照片:">
          </el-form-item>
          <img :src="this.companyForm.companyOrganizationCodeImg">
        </el-col>
        <el-col :xs="20" :sm="4" align="left" style="margin-left: 80px">
          <el-form-item label="税务登记证照片:">
          </el-form-item>
          <img :src="this.companyForm.companyTaxNoImg">
        </el-col>
      </el-row>
    </el-form>
  </div>
</template>

<script>
  import api from '@/api/fetch'
  import * as Message from '../utils/messageUtil'

  export default {
    name: 'BusinessMemberDetails',
    data() {
      return {
        companyInfo: '',
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
      }
    },
    mounted() {
      this.companyInfo = this.$route.params;
      this.getBusinessMemberInfo()
    }
  }
</script>
<style scoped>
  .divTitle {
    text-align: left;
    border-bottom: 2px solid #FF9933;
    padding-bottom: 10px;
    font-weight: bold;
    font-size: large;
    color: #FF9933
  }

  /* 统一信用照片图片大小*/
  .avatar {
    width: 100%;
    height: 100%;
  }
</style>
