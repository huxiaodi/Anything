<template xmlns:v-bind="http://www.w3.org/1999/xhtml">
<div class="page1180-home">
  <!--企业基本信息-->
  <div>
    <div class="accountItem account-title">
        <el-upload
          class="avatar-uploader"
          action="/api/account/userAccount/getUploadInfo"
          :show-file-list="false"
          :with-credentials="true"
          :headers="uploadToken"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload">
            <img  v-if="user.userHeadImg"  :src="user.userHeadImg" class="avatarUpload">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
        <p style="font-weight: bold;font-size: 18px;margin:6px 0 0;">{{people.userName}}</p>
        <div style="padding:8px 0 20px 0;">欢迎使用德邻e宝</div>
        <div class="userInfo">
          <router-link :to="`/ChangePassword/${people.userType==1?people.userMobile:enterprise.companyContactTel}`" v-show="_btn('zhanghuzhongxin_changePassword')"><i class="el-icon-edit">修改登录密码</i></router-link>
        </div>
        <div class="userInfo" v-if="people.userType==2">
          <router-link :to="`/ChangeMobile/${people.userType==1?people.userMobile:enterprise.companyContactTel}`" v-show="_btn('zhanghuzhongxin_changeMobile')"><i class="el-icon-edit">更换联系人</i></router-link>
        </div>
    </div>
    <!-- 用户 -->
    <div class="accountItem account-information">
      <!-- 企业信息 -->
      <div v-if="people.userType==2">
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

  <div class="user-form">
    <div class="user-form-title">
      <div style="float:left;font-size:18px;
          font-weight:bold;">e宝账户
      </div>
      <div style="float:right">
        <router-link    to="/SubAccount" v-show="_btn('zhanghuzhongxin_accountManagement')">账户管理</router-link>
      </div>
    </div>
    <!-- 循环银行卡 -->
    <div v-for="item in accountDataList">
      <div class="management-content">
        <div class="management-content-item">
          <div>
            账户名称:{{ item.accountSubNo }}
            <router-link style="margin-left:30px;" to="/SubAccount" v-show="_btn('zhanghuzhongxin_accountManagement')">交易密码管理</router-link>
          </div>
          <div style="margin:20px 0 10px;">
            <div class="management-item number-color">
              {{balanceForm.acctAvailBal}}
            </div>
            <div class="management-item number-color">
                {{balanceForm.cashAmt}}
            </div>
            <div class="management-item number-color showHide" @click="showAndHide(true)" v-show="isShow"><img src="/static/img/monryState/open.png" width="22" title="显示余额" /></div>
            <div class="management-item number-color showHide" @click="showAndHide(false)" v-show="isHide"><img src="/static/img/monryState/close.png" width="22" title="隐藏余额" /></div>
          </div>
          <div style="margin-bottom:30px;">
            <div class="management-item">
              可用余额(元)
            </div>
            <div class="management-item">
              可提现余额（元）
            </div>
            <div class="management-item">
              <a href="javascript:" style="color:#11a0fe" @click="dialogExplain = true">提现说明</a>
            </div>
          </div>
          <div>
            <div class="management-item">
              <el-button class="gradient-btn" type="primary" plain round disabled size="mini" style="width: 80px" icon="el-icon-circle-plus" v-show="_btn('zhanghuzhongxin_recharge')">充值
              </el-button>
            </div>
            <div class="management-item">
              <el-button class="gradient-btn" type="primary" plain round size="mini" style="width: 80px" @click="transferAccounts" icon="el-icon-sort" v-show="_btn('zhanghuzhongxin_transfer')">转账
              </el-button>
            </div>
            <div class="management-item">
              <el-button class="gradient-btn" type="primary" plain round size="mini" style="width: 80px" @click="withdrawals" icon="el-icon-sold-out" v-show="_btn('zhanghuzhongxin_withdrawal')">提现
              </el-button>
            </div>
          </div>
        </div>
      </div>
      <div class="management-content">
        <div style="width:350px;margin-bottom:35px;">
          <span style="font-size:16px;color:#000;">银行卡</span>
          <router-link style="float:right" to="/BankCard" v-show="_btn('zhanghuzhongxin_bankCardManagement')">银行卡管理</router-link>
        </div>
        <el-dropdown style="margin-bottom:20px;" size="mini" split-button  type="primary"   @command="handleCommand" @click="bankGo" v-show="_btn('zhanghuzhongxin_bankCardManagement')">
          {{selected}}
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item v-for="item in bankCardsList" :key="item.key" :command="item" >{{item.show}}</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>

        <el-button plain style="width:350px;" size="mini" @click="openAdd" icon="el-icon-circle-plus-outline">添加银行卡</el-button>
      </div>
    </div>
  </div>


  <el-dialog center :visible.sync="dialogExplain" width="33%" :close-on-click-modal="false"
             :close-on-press-escape="false" :show-close="false">
    <div class="login-uesr-title">
      提现说明
    </div>
    <div>
      <p>可提现余额:会员可提现到实体银行卡或对公账户；
        待转可提现余额:会员T日不能提现到实体银行卡或对公账户具体包括:1会员当日充值金额；2买方当日付款，收款方收款金额； T日日终后待转可提现金额自动结转到会员可提现余额；</p>
      <p></p>
      <p>可用余额:可用余额包括可提现余额和待转可提现余额；可用于平台交易、转账付款；会员T日产生的在途清分收入和会员间交易收入当日只增加可用余额，不增加可提现余额；</p>
    </div>
    <span slot="footer" class="dialog-footer">
            <el-button type="primary" @click="dialogExplain = false">关闭</el-button>
          </span>
  </el-dialog>
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
      api.post('/accountCentral/getAccountCentralInfo', {
        userType: ''
      }).then(response => {
        if (response.data.code === 1) {

          this.people = response.data.data.user; //个人
          //this.people.userHeadImg = "src/assets/img/1.png";
          this.enterprise = response.data.data.company; //企业
        }
      });
    },
    //获得账号信息
    getAccountList() {
      api.post('/accountCentral/getAccountList', {}).then(response => {
        if (response.data.code === 1) {
          this.accountDataList = response.data.data;
        }
      });
    },

    //获得银行卡列表
    getCardList() {
      /*api.post('/bankCards/getLocalBankCradList', {bankCardCreateUserId:'1517dd97002e4945aee3be025177e794',   //用户id
              bankCardAccountSubId:'b582b8f95d274077a8885ef21e25a821'   //子账户id
            }).then(response => {
              if (response.data.code === 1) {
              this.accountDataList = response.data.data; //企业
            }
          });*/
    },

    // 添加银行卡
    openAdd() {
      this.$router.push({
        name: '添加银行卡',
        params: {}
      })
    },
    recharge() {
      this.$router.push({
        name: '充值',
        params: {}
      })
    },
    withdrawals() {
      this.$router.push({
        name: '提现',
        params: {}
      })
    },
    transferAccounts() {
      this.$router.push({
        name: '转账付款',
        params: {}
      })
    },

    //修改信息
    changeInformation() {

      if (this.people.userType == '1') {
        this.$router.push('/ChangePersonalInformation')
      } else {
        api.post('user/getCompanyInformation', {
          'userCompanyId': this.$root.$data.user.userCompanyId
        }).then(response => {
          if (response.data.code === 1) {
            if (response.data.data.companyIsThreeInOne === true) {
              this.$router.push('/ChangeCompanyInformation')
            } else {
              this.$router.push('/ChangeCompanyInformationThree')
            }
          } else {
            Message.MessageError(response.data.msg)
          }
        })
      }
    },

    //格式化显示
    formatBankNumberShow(bankNumber) {
      if (bankNumber != null) {
        return bankNumber.substr(0, 4) + '  ****  ****  ' + bankNumber.substr(-4);
      } else {
        return "";
      }
    },

    formatBankNumberShowButton(bankNumber) {
      if (bankNumber != null) {
        return '**' + bankNumber.substr(-4);
      } else {
        return ""; //优化卡号
      }
    },
    formatBankButton(bankNumber) { //优化银行名称
      if (bankNumber != null) {
        return bankNumber.substr(0, 6);
      } else {
        return "";
      }
    },

    //可用余额 - 可提现余额
    getBalance() {
      api.get('/account/userAccount/getAvailableBalance').then(response => {
        if (response.data.code === 1) {
          //this.balance = response.data.data.acctAvailBal;
          //this.balanceT = response.data.data.acctAvailBal;

          this.balanceForm.acctAvailBalSave = response.data.data.acctAvailBal; //暂存余额
          this.balanceForm.cashAmtSave = response.data.data.cashAmt;            //可提现余额
          this.balanceForm.acctAvailBal = ""; //默认显示
          this.balanceForm.cashAmt = "";

          this.queryMoneyState();

        } else {
          Message.MessageError(response.msg)
        }
      })
    },
    //获得银行卡信息
    getBankCard() {

      api.post('/bankCards/getLocalBankCradList', {}).then(response => {
        if (response.data.code === 1) {

        if (response.data.data.length > 0) {

          var data  = response.data.data;
          var list = [];

          for(var i=0;i<data.length;i++){
            list.push({show:this.formatBankButton(data[i].bankCardBank) + " | 储蓄卡 | " + this.formatBankNumberShow(data[i].bankCardNo),
              key:data[i].bankCardNo,
              val:data[i].bankCardNo});
          }
          this.selected = list[0].show;

          this.bankCardsList = list;
        } else {
          this.bankCards.show = '未绑卡';
        }
      }
    })

    },
    showAndHide(state) {
      if (state) {
        //控制按钮显示
        this.isShow = false;
        this.isHide = true;
        this.balanceForm.acctAvailBal = this.balanceForm.acctAvailBalSave;
        this.balanceForm.cashAmt = this.balanceForm.cashAmtSave;

        this.saveState("0"); //保存显示状态
      } else {
        //控制按钮显示
        this.isShow = true;
        this.isHide = false;
        this.balanceForm.acctAvailBal = "***";
        this.balanceForm.cashAmt = "***";

        this.saveState("1"); //保存显示状态
      }
    },

    queryMoneyState() {
      api.post('/bankCards/queryMoneyState', {}).then(response => {
        if (response.data.code === 1) {
        var data = response.data.data;
        if (data.length > 0) {
          if (data[0].account_money_state == "0") {
            this.isShow = false;
            this.isHide = true;
            this.balanceForm.acctAvailBal = this.balanceForm.acctAvailBalSave;
            this.balanceForm.cashAmt = this.balanceForm.cashAmtSave;
          } else {
            //隐藏余额
            this.isShow = true;
            this.isHide = false;
            this.balanceForm.acctAvailBal = "***";
            this.balanceForm.cashAmt = "***";
          }
        }
      }
    })
    },
    saveState(state){
      api.post('/bankCards/saveState',{state:state}).then(response => {
        if (response.data.code === 1) {
      }
    })
    },

    handleAvatarSuccess(res, file) {
      if(res.code === 1){
        this.user.userHeadImg = res.data;
        sessionStorage.setItem('dleb_user', JSON.stringify(this.user))
      }
      //
    },
    beforeAvatarUpload(file) {
      const isJPG = (file.type === 'image/jpeg' || file.type === 'image/png');
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG,PNG 格式');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB');
      }
      return isJPG && isLt2M;
    },
    //银行列表选中事件
    handleCommand(command) {
      this.selected = command.show;
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
    this.getAccountList();
    this.getBalance();
    this.getBankCard();
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
