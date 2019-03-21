<template>
  <div style="margin: 0 auto; width: 800px;">
    <el-form ref="companyForm" :model="companyForm" labelPosition="right" label-width="140px">

      <el-row>
        <el-col :span="12">
          <el-form-item label="用户名:">
            <div align="left">{{this.companyForm.companyUserName}}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="绑定手机号:">
            <div align="left">{{this.companyForm.companyContactTel}}</div>
          </el-form-item>
        </el-col>
      </el-row>

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
          <el-form-item label="联系人:" prop="companyPrincipalName">
            <div align="left">{{companyForm.companyPrincipalName}}</div>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="联系人证件号:" prop="companyPrincipalIdCode">
            <div align="left">{{companyForm.companyPrincipalIdCode}}</div>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="公司邮箱:" prop="companyEmail">
            <div align="left">{{companyForm.companyEmail}}</div>
          </el-form-item>
        </el-col>
        <el-form-item label="公司地址:" prop="shipperCity">
          <div align="left">
            {{companyForm.companyProvince + companyForm.companyCity + companyForm.companyDistrict + companyForm.companyAddress}}
          </div>
        </el-form-item>
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
        companyId: '',
        companyForm: {
          userType: '2', // 企业账户
          companyUserName: '',
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
        api.post('admin/companyManage/company/info', {'id': this.companyId}).then(response => {
          if (response.data.code === 1) {
            this.companyForm = response.data.data
          }
        })
      }
    },
    mounted() {
      this.companyId = this.$route.params.companyId;
      this.getBusinessMemberInfo()
    }
  }
</script>
<style scoped>
</style>
