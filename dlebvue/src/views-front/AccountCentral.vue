<template xmlns:v-bind="http://www.w3.org/1999/xhtml">
<div class="page1180-home">
  <!--企业基本信息-->
  <div>
    <div class="accountItem account-title">
        <p style="font-weight: bold;font-size: 18px;margin:6px 0 0;">{{people.userName}}</p>
    </div>
    <!-- 用户 -->
    <div class="accountItem account-information">
      <!-- 企业信息 -->
      <div v-if="people.userType==2 || people.userType==3">
        <!-- 头部标题 -->
        <div class="user-form-title">
          <div class="" style="float:left;font-size:18px;
            font-weight:bold;">
            企业基本信息
          </div>
          <div class="" style="float:right">
          </div>
        </div>
        <div class="account-content">
          <div class="account-content-item">
            <!-- ------1--------- -->
            <div class="account-content-item-tit">
              公司名称:
            </div>
            <div class="account-content-item-content">
              {{enterprise.companyName}}
            </div>
          </div>
          <!-- -------2-------- -->
          <div class="account-content-item">
            <div class="account-content-item-tit">
              法人:
            </div>
            <div class="account-content-item-content">
            {{enterprise.companyPrincipalName }}
            </div>
          </div>
          <!-- -------1-------- -->
          <div class="account-content-item">
            <div class="account-content-item-tit">
              公司简称:
            </div>
            <div class="account-content-item-content">
              {{enterprise.companyShortName }}
            </div>
          </div>
          <!-- -------2-------- -->
          <div class="account-content-item">
            <div class="account-content-item-tit">
              联系人:
            </div>
            <div class="account-content-item-content">
              {{enterprise.companyContact }}
            </div>
          </div>
          <!-- -------1-------- -->
          <div class="account-content-item">
            <div class="account-content-item-tit">
              联系人手机号:
            </div>
            <div class="account-content-item-content">
              {{enterprise.companyContactTel }}
            </div>
          </div>
          <!-- ------2--------- -->
          <div class="account-content-item">
            <div class="account-content-item-tit">
              公司传真:
            </div>
            <div class="account-content-item-content">
                {{enterprise.companyFax }}
            </div>
          </div>
          <!-- ------1--------- -->
          <div class="account-content-item">
            <div class="account-content-item-tit">
              对公账户开户行:
            </div>
            <div class="account-content-item-content">
              {{enterprise.companyToPublicBank }}
            </div>
          </div>
          <!-- ------2--------- -->
          <div class="account-content-item">
            <div class="account-content-item-tit">
              纳税人识别号:
            </div>
            <div class="account-content-item-content">
              {{enterprise.companyTaxpayerRegistrationNumber }}
            </div>
          </div>
          <!-- ------1--------- -->
          <div class="account-content-item">
            <div class="account-content-item-tit">
              注册资本:
            </div>
            <div class="account-content-item-content">
              {{enterprise.companyRegistedCapital }} 万元
            </div>
          </div>
          <!-- ------2--------- -->
          <div class="account-content-item">
            <div class="account-content-item-tit">
              公司邮箱:
            </div>
            <div class="account-content-item-content">
              {{enterprise.companyEmail }}
            </div>
          </div>
          <!-- ------1--------- -->
          <div class="account-content-item">
            <div class="account-content-item-tit">
              公司地址:
            </div>

            <div class="account-content-item-content">
              {{enterprise.companyProvince +enterprise.companyCity+ enterprise.companyDistrict + enterprise.companyAddress }}
            </div>
          </div>
          <div class="certificates-btn" @click="certificatesShow=!certificatesShow">
            查看证件信息
          </div>
        </div>
      </div>
    </div>

  </div>
  <!-- 证件信息 -->
  <transition name="el-zoom-in-top">
    <div v-show="certificatesShow" class="user-form">
      <div class="user-form-title">
        <div style="float:left;font-size:18px;
            font-weight:bold;">证件信息
        </div>
      </div>
      <div v-if="people.userType==2">
        <!-- 三证企业内容 -->
        <div class="certificates-content">
          <div class="certificates-content-item">
            是否三证合一:
            {{enterprise.companyIsThreeInOne==true?'是':'否' }}
          </div>
          <div class="certificates-content-item">
            企业注册时间:
            {{enterprise.companyUnifiedSocialDatetime }}
          </div>
          <div class="certificates-content-item">
            有效期至:
            {{enterprise.companyUnifiedSocialLimitDatetime }}
          </div>
        </div>
        <!--三证合一-->
        <div v-if="enterprise.companyIsThreeInOne==true">
          <div class="certificates-content">
            <div class="certificates-content-item">
              <img v-bind:src="enterprise.companyUnifiedSocialImg">
              <div>统一社会信用代码:{{enterprise.companyUnifiedSocialCreditLdentifier}}</div>
            </div>
          </div>
        </div>

        <!--非三证合一-->
        <div v-if="enterprise.companyIsThreeInOne==false">
        <div class="certificates-content">
          <div class="certificates-content-item">
            <img v-bind:src="enterprise.companyLicenseNoImg">
            <div>营业执照号:{{enterprise.companyLicenseNo}}</div>
          </div>
          <div class="certificates-content-item">
            <img v-bind:src="enterprise.companyTaxNoImg">
            <div>税务登记证号:{{enterprise.companyTaxNo}}</div>
          </div>
          <div class="certificates-content-item">
            <img v-bind:src="enterprise.companyOrganizationCodeImg">
            <div>组织结构代码号:{{enterprise.companyOrganizationCode}}</div>
          </div>
        </div>
        </div>
      </div>
    </div>
  </transition>



</div>
</template>

<script>
import api from '@/api/fetch'
import * as Message from '../utils/messageUtil'

export default {
  name: 'AccountCentral',
  data() {
    return {
      certificatesShow:false,
      user: JSON.parse(sessionStorage.getItem('dleb_user')),
      people: {}, //个人信息
      enterprise: {}, //企业信息
    }
  },
  //定义方法
  methods: {
    //获取基本信息
    getData() {
      api.get('/user/getUserInfo').then(response => {
        if(response.data.code === 1){
          this.enterprise =response.data.data.company;
          this.people = response.data.data.personal;
        } else {
          Message.MessageError(response.data.msg);
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
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 85px;
  height: 85px;
  line-height: 85px;
  text-align: center;
}

.avatarUpload {
  width: 85px;
  height: 85px;
  display: block;
}

.showHide {
  color: #333;
  text-align: left;
  font-size: 13px;
  cursor: pointer;
}
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
.certificates-content-item{
  width: 310px;
  overflow: hidden;
  float: left;
}
.certificates-content-item img{
  width: 110px;
  height: 110px;
}
.management-content{
  width: 50%;
  height: 200px;
  float: left;
  padding: 20px 0px 20px 60px;
  border-bottom: 1px solid #eee;
}
.management-item{
  width: 140px;
  float: left;
}
.management-content-item > div{
  overflow: hidden;
}

</style>
