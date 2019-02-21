<template>
  <div style="margin: 0 auto; width: 500px;">
    <div class="divTitle">新增角色</div>
    <el-form ref="form" :model="form" labelWidth="110px" labelPosition="right" style="margin-top: 10px">
      <el-row>
        <el-col :xs="24" :sm="20" :offset="1">
          <el-form-item label="角色名:">
            <el-input v-model="form.verification"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :xs="24" :sm="20" :offset="1">
          <el-form-item label="角色英文名:">
            <el-input v-model="form.verification"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :xs="24" :sm="20" :offset="1">
          <el-form-item label="排序:">
            <el-input v-model="form.verification"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-form-item>
        <el-button type="primary" size="medium" @click="confirmInfo">确认</el-button>
        <el-button type="primary" size="medium" @click="cancleOptions">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import api from '@/api/fetch'
  import * as Message from '../utils/messageUtil'

  var qs = require('qs');
  // 手机号
  var mobileReg = /^1[34578]\d{9}$/;
  // 用户名
  var userNameReg = /^[a-z][0-9a-z]{7,49}$/;
  // 密码
  var passwordReg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9a-z]{8,50}$/;
  export default {
    name: 'RoleAddEdit',
    data() {
      return {
        info: '',
        form: {}
      }
    },
    methods: {
      // 获取企业会员信息
      getBusinessMemberInfo() {
        api.post('admin/companyManage/company/info', {'id': this.info.company_id}).then(response => {
          if (response.data.code === 1) {
            this.form = response.data.data
          }
        })
      },
      // 确定
      confirmInfo() {
      },
      // 取消
      cancleOptions() {
        this.$router.go(-1);
      }
    },
    mounted() {
      this.info = this.$route.params;
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
