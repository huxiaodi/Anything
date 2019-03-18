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
            <!--<el-button type="text" @click="changeInformation" style="padding: 0;display: inline"><i class="el-icon-edit-outline"></i>修改企业信息</el-button>-->
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
      <!-- 个人信息 -->
      <div  v-if="people.userType==1">
        <div class="certificates-btn" @click="certificatesShow=!certificatesShow" v-show="_btn('zhanghuzhongxin_idInformation')">
          查看证件信息
        </div>
        <!-- 头部标题 -->
        <div class="user-form-title">
          <div class="" style="float:left;font-size:16px;
            font-weight:bold;">
            个人基本信息:
          </div>
          <div class="" style="float:right">
            <el-button type="text" @click="changeInformation" style="padding: 0;display: inline"><i class="el-icon-edit-outline"></i>修改个人信息</el-button>
          </div>
        </div>
        <div class="account-content personal">
          <div class="account-content-item">
            <!-- ------1--------- -->
            <div class="account-content-item-tit">
              个人姓名:
            </div>
            <div class="account-content-item-content">
              {{people.userCnName}}
            </div>
          </div>
          <div class="account-content-item">
            <!-- ------1--------- -->
            <div class="account-content-item-tit">
              证件类型:
            </div>
            <div class="account-content-item-content">
              {{people.userCertificateType==1 || people.userCertificateType=='身份证'?'身份证':''}}
            </div>
          </div>
          <div class="account-content-item">
            <!-- ------1--------- -->
            <div class="account-content-item-tit">
              联系人手机号:
            </div>
            <div class="account-content-item-content">
              {{people.userMobile}}
            </div>
          </div>
          <div class="account-content-item">
            <!-- ------1--------- -->
            <div class="account-content-item-tit">
              身份证号:
            </div>
            <div class="account-content-item-content">
              {{people.userCertificateNumber}}
            </div>
          </div>
          <div class="account-content-item">
            <!-- ------1--------- -->
            <div class="account-content-item-tit">
              邮箱:
            </div>
            <div class="account-content-item-content">
              {{people.userEmail}}
            </div>
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
      <div v-if="people.userType==1">
        <div class="certificates-content-item">
          <img v-bind:src="people.userIdCardFontImg">
        </div>
        <div class="certificates-content-item">
          <img v-bind:src="people.userIdCardBackImg">
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
      infoTitle: 4,
      infoText: 8,
      people: {}, //个人信息
      enterprise: {}, //企业信息
      uploadToken:{token: sessionStorage.getItem('dleb_token')},
      balance: 0, //可用余额
      balanceT: 0, //可提现

      bankCards: {
        show: ''
      }, //银行卡列表
      bankCardsList:[],
      bankList:[],
      selected:'-----------未绑卡-----------',

      accountDataList: [{
        accountId: '',
        acctAvailBal: '',
        cashAmt: ''
      }],
      dialogExplain: false,
      isShow:false,
      isHide:true,
      balanceForm: {
        acctAvailBal: '',
        acctAvailBalSave:'',
        cashAmt:'',
        cashAmtSave:''
      },
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
    },

    bankGo(){
      this.$router.push({
        name: '银行卡管理',
        params: {}
      })
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
