<template xmlns:v-bind="http://www.w3.org/1999/xhtml">
<div class="page1180-home">
  <div>
    <div class="accountItem account-title">
        <p style="font-weight: bold;font-size: 18px;margin:6px 0 0;">用户信息</p>
        <p style=" margin-top: 25px;">用户名：{{people.userName}}</p>
         <a href="javascript:void(0)" @click="modifyPasswordPre" style="margin-top: 30px;">修改密码</a>
    </div>
    <div class="accountItem account-information">
      <div v-if="people.userType==2 || people.userType==3">
        <div class="user-form-title">
          <div class="" style="float:left;font-size:18px;
            font-weight:bold;">
            基本信息
          </div>
          <div class="" style="float:right">
          </div>
        </div>
        <div class="account-content">
          <div class="account-content-item">
            <div class="account-content-item-tit">
              公司名称:
            </div>
            <div class="account-content-item-content">
              {{enterprise.companyName}}
            </div>
          </div>
          <div class="account-content-item">
            <div class="account-content-item-tit">
              公司简称:
            </div>
            <div class="account-content-item-content">
              {{enterprise.companyShortName }}
            </div>
          </div>
          <div class="account-content-item">
            <div class="account-content-item-tit">
              联系人:
            </div>
            <div class="account-content-item-content">
              {{enterprise.companyContact }}
            </div>
          </div>
          <div class="account-content-item">
            <div class="account-content-item-tit">
              联系人手机号:
            </div>
            <div class="account-content-item-content">
              {{enterprise.companyContactTel }}
            </div>
          </div>
          <div class="account-content-item">
            <div class="account-content-item-tit">
              联系人证件号:
            </div>
            <div class="account-content-item-content">
              {{enterprise.companyContactTel }}
            </div>
          </div>
          <div class="account-content-item">
            <div class="account-content-item-tit">
              公司邮箱:
            </div>
            <div class="account-content-item-content">
              {{enterprise.companyEmail }}
            </div>
          </div>
          <div class="account-content-item">
            <div class="account-content-item-tit">
              公司地址:
            </div>
            <div class="account-content-item-content">
              {{enterprise.companyProvince +enterprise.companyCity+ enterprise.companyDistrict + enterprise.companyAddress }}
            </div>
          </div>
        </div>
      </div>
    </div>

  </div>
  <transition name="el-zoom-in-top">
    <div  class="user-form">
      <div class="user-form-title">
        <div style="float:left;font-size:18px;
            font-weight:bold;">公告信息
        </div>
      </div>
      <div class="certificates-content">
        <el-carousel :interval="5000" arrow="always">
          <el-carousel-item v-for="item in notices" :key="item.sysNoticeId">
            <div>
              <h4 align="center">{{ item.sysNoticeTitle }}</h4>
              <h5 align="center">{{ item.sysNoticeContent }}</h5>
              <h6 align="center">{{ item.sysNoticeIssueTime }}</h6>
            </div>
          </el-carousel-item>
        </el-carousel>
      </div>
    </div>
  </transition>

  <el-dialog title="修改密码" :visible.sync="dialogFormVisible" width="40%"  >
    <el-form :model="formData" style="margin-left: 20%;" ref="formDataForm" :rules="formDataRules">
      <el-form-item label="旧密码："  prop="userPassword" >
        <el-input type="password" v-model="formData.userPassword" style="width:120px;"></el-input>
      </el-form-item>
      <el-form-item label="新密码："  prop="newPassword">
        <el-input type="password" v-model="formData.newPassword" style="width:120px;"></el-input>
      </el-form-item>
      <el-form-item   label="再次输入新密码："  prop="newPasswordConfirm">
        <el-input type="password" v-model="formData.newPasswordConfirm" style="width:120px;"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="modifyPassword">确 定</el-button>
    </div>
  </el-dialog>


</div>
</template>

<script>
import api from '@/api/fetch'
import * as Message from '../utils/messageUtil'

var passwordReg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9a-z]{8,50}$/;
export default {
  name: 'AccountCentral',
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '' || value === undefined) {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.formData.newPassword) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      user: JSON.parse(sessionStorage.getItem('dleb_user')),
      people: {},
      enterprise: {},
      notices: [],
      formData: {},
      dialogFormVisible: false,
      formDataRules: {
        userPassword: [
          {required: true, message: '旧密码不能为空', trigger: 'blur'},
          {min: 8, message: '密码至少8位', trigger: 'blur'}
        ],
        newPassword: [
          {required: true, message: '新密码不能为空', trigger: 'blur'},
          {pattern: passwordReg, message: '密码至少8位，支持数字、字母（小写）、字符，至少包含数字、字母组合'}
        ],
        newPasswordConfirm: [
          {required: true, message: '确认密码不能为空', trigger: 'blur'},
          {pattern: passwordReg, message: '密码至少8位，支持数字、字母（小写）、字符，至少包含数字、字母组合'},
          {validator: validatePass, trigger: 'blur'}
        ],
      }

    }
  },
  methods: {
    getData() {
      api.get('/user/getUserInfo').then(response => {
        if(response.data.code === 1){
          this.enterprise =response.data.data.company;
          this.people = response.data.data.personal;
          this.notices = response.data.data.notices;
        } else {
          Message.MessageError(response.data.msg);
        }
      });
    },
    modifyPasswordPre(){
      this.dialogFormVisible = true;
    },
    modifyPassword(){
      this.$refs['formDataForm'].validate((valid) => {
        if (valid) {
          let that = this;
          api.post('/user/changePassword', this.formData).then(response => {
            if (response.data.code === 1) {
              this.dialogFormVisible = false;
              Message.MessageSuccess('重置密码成功，请重新登录！')
              window.setTimeout(function () {
                sessionStorage.removeItem('dleb_token');
                sessionStorage.removeItem('dleb_user');
                that.$router.push('/Login')
              }, 2000);
            } else {
              Message.MessageError(response.data.msg)
            }
          })
        } else {
          return false;
        }
      });
    }
  },

  mounted() {
    this.getData();
  }
}
</script>

<style scoped>

.account-content{
  padding-top: 15px;
}
.account-content.personal > .account-content-item{
  width: 100%;
}
.account-content-item{
  width: 50%;
  height: 30px;
  box-sizing: border-box;
  float: left;
  padding-left: 10px;
}
.account-content-item-tit{
  width: 130px;
  float: left;
  color: #888;
}
.account-content-item-content{
  float: left;
  width: 300px;
  white-space: nowrap;
  text-overflow:ellipsis;
  overflow:hidden;
  color: #000;
}
.certificates-content{
  height: auto;
  overflow: hidden;
  width: 100%;
  padding: 15px 30px 0;
}
.certificates-content-item img{
  width: 110px;
  height: 110px;
}
.management-content-item > div{
  overflow: hidden;
}

.el-carousel__item div {
  line-height: 77px;
  margin: 0;
  background-color: #99a9bf;
}

</style>
