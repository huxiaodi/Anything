<template xmlns:v-bind="http://www.w3.org/1999/xhtml">
  <div class="page1180">
    <div class="page-title">
      <div style="float:left;">
        <router-link class="parent" to="/AccountCentral">账户中心</router-link>
        <i class="el-icon-arrow-right"></i> 添加银行卡
      </div>
    </div>
    <div class="page-content">
      <div class="one-column">
        <el-form ref="formDataForm" :rules="formDataRules" :model="formData" label-width="140px">
          <el-form-item label="子账户类型:" prop="accountSubId">
            <el-select v-model="formData.accountSubId" placeholder="请选择证件类型" change="accountSubIdSelect">
              <el-option v-for="item in accountSubList"
                         :label="item.accountType == 1 ? item.accountSubNo+'      平安银行' : item.accountType == 2 ?item.accountSubNo+'      工商银行':item.accountSubNo "
                         :value="item.accountSubId" :key="item.accountSubId"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="单笔交易额度" prop="bankCardTransactionAmountType">
            <el-radio-group @change="getBankCardBankList" v-model="formData.bankCardTransactionAmountType">
              <el-radio label="1">
                <el-tooltip effect="dark" content="工作日的8点30-17点间可提现，第二日到账" placement="top">
                  <span>大于5万</span>
                </el-tooltip>
              </el-radio>
              <el-radio label="2">
                <el-tooltip effect="dark" content="7*24小时提现实时到账" placement="top">
                  <span>小等于5万</span>
                </el-tooltip>
              </el-radio>
            </el-radio-group>
            <span v-if="formData.bankCardTransactionAmountType == ''" style="margin-left: 10%;font-size: 10px;color:red;position: absolute;
    right: -50px;">(请先选择交易额度)</span>
          </el-form-item>
          <el-form-item label="开户名称:" prop="accountName">
            <el-input v-model="formData.accountName" disabled></el-input>
          </el-form-item>
          <el-form-item
            v-if="formData.bankCardTransactionAmountType == '1' || formData.bankCardTransactionAmountType == ''"
            label="证件类型:" prop="bankCardDocumentType">
            <el-select disabled v-model="formData.bankCardDocumentType" placeholder="请选择证件类型">
              <el-option v-for="item in bankCardDocumentTypeList" :key="item.value" :label="item.label"
                         :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="证件号:" prop="bankCardDocumentNo">
            <el-input v-model="formData.bankCardDocumentNo" disabled></el-input>
          </el-form-item>

          <el-form-item
            v-if="formData.bankCardTransactionAmountType == '1'  || formData.bankCardTransactionAmountType == ''"
            label="开户网点:" prop="bankCardBankTemp">
            <el-select :disabled="formData.bankCardTransactionAmountType == ''" v-model="formData.bankCardBankTemp"
                       clearable filterable @change="bankCardBankSelect" placeholder="请选择开户网点" value-key="bankId">
              <el-option v-for="item in bankCardBankList" :key="item.bankId" :label="item.bankName" :value="item">
                <span style="float: left">{{ item.bankName }}</span>
                <span style="float: right; color: #8492a6; font-size: 13px"><img v-bind:src="item.bankLogo"
                                                                                 style="height: 18px;"/></span>
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item v-if="formData.bankCardTransactionAmountType == '2'" label="开户网点:" prop="bankCardBankTemp">
            <el-select v-model="formData.bankCardBankTemp" clearable filterable @change="bankCardBankSelectTwo"
                       placeholder="请选择开户网点" value-key="bankNo" style="width: 100%;">
              <el-option v-for="item in bankCardBankList" :key="item.bankNo" :label="item.bankName" :value="item">
                <span style="float: left">{{ item.bankName }}</span>
                <span style="float: right; color: #8492a6; font-size: 13px"><img v-bind:src="item.bankLogo"
                                                                                 style="height: 18px;"/></span>
              </el-option>
            </el-select>
          </el-form-item>
          <el-row>
            <el-form-item label="开户网点所在地"
                          v-if="formData.bankCardTransactionAmountType == '1'  || formData.bankCardTransactionAmountType == ''"
                          prop="companyDistrict">
              <el-select class="one-column-address" :disabled="formData.bankCardTransactionAmountType == ''"
                         v-model="companyForm.companyProvince" placeholder="全部">
                <el-option v-for="item in provinces" @click.native="getCity(item)" :key="item.nodeNodecode"
                           :label="item.nodeNodename" :value="item.nodeNodecode">
                </el-option>
              </el-select>
              <el-select class="one-column-address" :disabled="formData.bankCardTransactionAmountType == ''"
                         v-model="companyForm.companyCity" placeholder="全部">
                <el-option v-for="item in citys" @click.native="getDistrict(item)" :key="item.cityAreacode"
                           :label="item.cityAreaname" :value="item.cityAreacode">
                </el-option>
              </el-select>
              <el-select class="one-column-address" :disabled="formData.bankCardTransactionAmountType == ''"
                         v-model="companyForm.companyDistrict" placeholder="全部">
                <el-option v-for="item in districts" @click.native="getBankCardBankBranch()" :key="item.cityAreacode"
                           :label="item.cityAreaname" :value="item.cityAreacode">
                </el-option>
              </el-select>
            </el-form-item>
          </el-row>
          <el-form-item label="开户网点名称:" prop="bankCardBankBranchTemp"
                        v-if="formData.bankCardTransactionAmountType == '1'  || formData.bankCardTransactionAmountType == ''">
            <el-select no-data-text="请更换开户网点或者所以地对应的市区" v-model="formData.bankCardBankBranchTemp" value-key="bankno"
                       :disabled="formData.bankCardBank == '' ? true : companyForm.companyDistrict== '' ? true : false"
                       :placeholder="formData.bankCardBank == ''?'请先选择开户网点': companyForm.companyDistrict== '' ? '请先选择开户网点所在地' : '请选择开户网点名称'"
                       clearable filterable @change="bankCardBankBranchSelect" style="width: 100%">
              <el-option v-for="item in bankCardBankBranchList" :key="item.bankno" :label="item.bankname" :value="item">
              </el-option>
            </el-select>
          </el-form-item>
          <!-- <el-form-item label="银行账户名称:" prop="bankCardReserveBankAccountName">
             <el-input v-model="formData.bankCardReserveBankAccountName"></el-input>
           </el-form-item>-->
          <el-form-item label="银行账号:" prop="bankCardNo">
            <el-input :disabled="formData.bankCardTransactionAmountType == ''" v-model="formData.bankCardNo"
                      @blur="bankCardNoInput" :step="4"></el-input>
          </el-form-item>
          <el-form-item
            label="预留手机号：" prop="mobilePhone">
            <el-input v-model="formData.mobilePhone"></el-input>
          </el-form-item>
          <el-row style="text-align: center;margin:20px 0;">
            <el-button type="primary" @click="submitData">同意协议并绑定</el-button>
          </el-row>
        </el-form>
      </div>
    </div>


    <el-dialog width="500px" title="确认信息" :visible.sync="confirmDataVisible" :before-close="winCancel">
      <el-form :model="confirmData" ref="confirmDataForm" :rules="confirmDataRule">
        <el-form-item v-if="userType == '1'" prop="verification" label="请输入验证码" label-width="20">
          <el-input v-model="confirmData.verification"></el-input>
        </el-form-item>
        <el-form-item v-if="userType == '2'" prop="authentication" label="请输入鉴权金额" label-width="20">
          {{countDown}}s后关闭弹框!
          <el-input v-model="confirmData.authentication"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="winCancel">取 消</el-button>
        <el-button type="primary" @click="submitconfirmData('confirmDataForm')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import api from '@/api/fetch'

  export default {
    name: 'AddBankCard',
    data() {
      var authenticationCheck = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入鉴权金额'));

        } else if (!((/^[0]+\.[0-9]{1,2}$/.test(value)) && parseFloat(value) <= 0.5 && parseFloat(value) > 0)) {
          callback(new Error('请输入正确的金额'));
        } else {
          callback();
        }
      };
      var bankCardNoCheck = (rule, value, callback) => {
        value = value.replace(/\s/g, '');
        if (value === '') {
          callback(new Error('请填写银行账号'));

        } else if (!/^[0-9]*$/.test(value)) {
          callback(new Error('请填写正确的银行账号'));
        } else {
          callback();
        }
      };
      var verificationCheck = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请填写验证码'));

        } else if (!/^[0-9]{6}$/.test(value)) {
          callback(new Error('请填写正确验证码'));
        } else {
          callback();
        }
      };
      return {
        loading: false,
        countDown: 120,
        companyForm: {
          companyProvince: '',
          companyCity: '',
          companyDistrict: ''
        },
        startCount: null,
        querySmall: null,
        provinces: [],
        citys: [],
        districts: [],
        formData: {
          accountSubId: '',
          accountSubNo: '',
          accountType: '',
          bankCardTransactionAmountType: '',
          accountName: '',
          bankCardDocumentType: '',
          bankCardDocumentNo: '',
          bankCardBank: '',
          bankCardBankName: '',
          bankCardBankTemp: {},
          bankCardBankBranchTemp: '',
          bankCardBankBranch: '',
          bankCardBankBranchName: '',
          bankCardReserveBankAccountName: '',
          bankCardNo: '',
          mobilePhone: '',
          bankLogo: ''
        },
        confirmData: {
          accountSubId: '',
          bankCardId: '',
          bankCardNo: '',
          verification: '',
          authentication: ''
        },
        userType: '',
        confirmDataVisible: false,
        accountSubList: [],
        bankCardDocumentTypeList: [{
          label: '身份证',
          value: '1'
        }, {
          label: '统一社会信用代码',
          value: '5'
        }, {
          label: '营业执照',
          value: '68'
        }],
        bankCardBankList: [],
        accountSubTemp: {},
        bankCardBankBranchList: [],
        formDataRules: {
          accountSubId: [{
            required: true,
            message: '请选择子账户类型',
            trigger: 'change'
          }],
          bankCardTransactionAmountType: [{
            required: true,
            message: '请选择交易额度',
            trigger: 'change'
          }],
          accountName: [{
            required: true,
            message: '请填写开户名称',
            trigger: 'blur'
          }],
          bankCardDocumentType: [{
            required: true,
            message: '请选择证件类型',
            trigger: 'change'
          }],
          bankCardDocumentNo: [{
            required: true,
            message: '请填写证件号',
            trigger: 'blur'
          }],
          bankCardBankTemp: [{
            required: true,
            message: '请选择开户网点',
            trigger: 'change'
          }],
          bankCardBankBranchTemp: [{
            required: true,
            message: '请选择开户网点名称',
            trigger: 'change'
          }],
          bankCardReserveBankAccountName: [{
            required: true,
            message: '请填写银行账户名称',
            trigger: 'blur'
          }],
          bankCardNo: [{
            required: true,
            validator: bankCardNoCheck,
            trigger: 'blur'
          }],
          mobilePhone: [{
            required: true,
            message: '请填写手机号',
            trigger: 'blur'
          }, {
            pattern: /^1[34578]\d{9}$/,
            message: '请输入正确的手机号'
          }]
        },
        confirmDataRule: {
          verification: [{
            required: true,
            validator: verificationCheck,
            trigger: 'blur'
          }],
          authentication: [{
            required: true,
            validator: authenticationCheck,
            trigger: 'blur'
          }]
        }
      }
    },
    methods: {
      //取消
      winCancel() {
        this.confirmData.verification = '';
        this.confirmDataVisible = false;
      },
      // 获取省
      getProvince() {
        api.get('/bankCard/province').then(reponse => {
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
        this.bankCardBankBranchList = []
        this.getBankCardBankBranchInit();
        api.post('/bankCard/city', {
          'nodeNodecode': item.nodeNodecode
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
        this.bankCardBankBranchList = []
        this.getBankCardBankBranchInit();
        api.post('/bankCard/district', {
          'cityAreacode': item.cityAreacode
        }).then(reponse => {
          if (reponse.data.code === 1) {
            this.districts = reponse.data.data
          }
        })
      },
      getBankCardBankBranch() {
        this.formData.bankCardBankBranch = '';
        this.formData.bankCardBankBranchName = '';
        this.bankCardBankBranchList = [];
        this.getBankCardBankBranchInit();
        this.getBankCardBankBranchGet();

      },
      getBankCardBankBranchGet() {
        if (this.companyForm.companyDistrict !== '' && this.formData.bankCardBank !== '') {
          api.post('/bankCard/getBankCardBankBranch', {
            'citycode': this.companyForm.companyDistrict,
            'bankclscode': this.formData.bankCardBank
          }).then(reponse => {
            if (reponse.data.code === 1) {
              this.bankCardBankBranchList = reponse.data.data
            }
          })
        }
      },
      bankCardBankBranchSelect(item) {
        if (item == '') {
          this.formData.bankCardBankBranch = '';
          this.formData.bankCardBankBranchName = '';
        } else {
          this.formData.bankCardBankBranch = item.bankno;
          this.formData.bankCardBankBranchName = item.bankname;
        }
      },
      getData() {
        api.get('/accountSub/accountSubController/getAccountSubs').then((response) => {
          if (response.data.code === 1) {
            this.accountSubList = response.data.data;
            if (this.accountSubList != null) {
              this.accountSubTemp = this.accountSubList[0];
              this.setFormDataValue(this.accountSubTemp);
            }
          }
        });
      },
      getBankCardBankList(val) {
        this.bankCardBankBranchList = [];
        this.formData.bankCardNo = '';
        this.getBankCardBankBranchInit();
        this.formData.bankCardBankTemp = '';
        api.get('/bankCard/getBankCardBankList?bankCardTransactionAmountType=' + val).then((response) => {
          if (response.data.code === 1) {
            this.bankCardBankList = response.data.data;
          }
        });
      },
      accountSubIdSelect(val) {
        this.confirmData.accountSubId = val;
      },
      bankCardNoInput() {
        this.confirmData.bankCardNo = this.formData.bankCardNo;
      },
      setFormDataValue(value) {
        this.formData.accountSubId = value.accountSubId;
        this.formData.accountSubNo = value.accountSubNo;
        this.formData.accountType = value.accountType;
        this.userType = value.userType;
        this.formData.accountName = (value.userType === '2' ? value.companyName : value.userType === '1' ? value.userCnName : '');
        this.formData.bankCardDocumentType = (value.userType === '2' ? (value.companyIsThreeInOne ? '5' : '68') : value.userType === '1' ? '1' : value.userType);
        this.formData.bankCardDocumentNo = (value.userType === '2' ? (value.companyIsThreeInOne ? value.companyUnifiedSocialCreditLdentifier : value.companyLicenseNo) : value.userType === '1' ? value.userCertificateNumber : '');
        this.formData.mobilePhone = '';
      },
      bankCardBankSelect(val) {
        this.getBankCardBankBranchInit();
        if (val == '') {
          this.formData.bankCardBank = '';
          this.formData.bankCardBankName = '';
        } else {
          this.formData.bankCardBank = val.bankCode;
          this.formData.bankCardBankName = val.bankName;
          this.formData.bankLogo = val.bankLogo;
          this.getBankCardBankBranchGet();
        }

      },
      bankCardBankSelectTwo(val) {
        this.getBankCardBankBranchInit();
        if (val == '') {
          this.formData.bankCardBank = '';
          this.formData.bankCardBankName = '';
        } else {
          this.formData.bankCardBank = val.bankNo;
          this.formData.bankCardBankName = val.bankName;
          this.formData.bankLogo = val.bankLogo;
        }
      },
      querySmallAmount() {
        this.querySmall = setInterval(() => {
          api.get('/bankCard/querySmallAmount?accountSubId=' + this.formData.accountSubId + '&bankCardNo=' + this.formData.bankCardNo).then((response) => {
            if (response.data.code === 1) {
              if (response.data.data.returnStatu === '1') {
                this.$alert(response.data.data.returnMsg, '提示', {
                  confirmButtonText: '确定'
                });
                this.confirmDataVisible = false;
                window.clearInterval(this.querySmall);
                window.clearInterval(this.startCount);
              }
              if (response.data.data.returnStatu === '0') {
                window.clearInterval(this.querySmall);
                window.clearInterval(this.startCount);
              }
            }
          });
        }, 5000)
      },
      startCountDown() {
        this.startCount = setInterval(() => {
          if (this.countDown === 0) {
            this.confirmDataVisible = false;
            window.clearInterval(this.querySmall);
            window.clearInterval(this.startCount);
          }
          this.countDown--;
        }, 1000)
      },
      submitData() {
        this.$refs.formDataForm.validate((valid) => {
          if (valid) {

            this.formData.bankCardNo = this.trim(this.formData.bankCardNo, "g");

            api.post('/bankCard/sumitBankCard', this.formData).then((response) => {
              if (response.data.code === 1) {

                if (response.data.data.bankCardBankBranch == "error") {
                  this.$alert(response.data.data.bankCardBank, '提示', {
                    confirmButtonText: '确定'
                  });
                } else {
                  this.startCount = 120;
                  this.confirmDataVisible = true;
                  this.confirmData.accountSubId = response.data.data.bankCardAccountSubId;
                  this.confirmData.bankCardId = response.data.data.bankCardId;

                  if (this.userType === '2') {
                    if (this.startCountDown) {
                      window.clearInterval(this.startCountDown);
                    }
                    if (this.querySmallAmount) {
                      window.clearInterval(this.querySmallAmount);
                    }
                    debugger
                    /**
                     * 120s后关闭弹框
                     */
                    this.startCountDown();
                    /**
                     * 5s去查询一次鉴权结果
                     */
                    this.querySmallAmount();
                  }
                }
              } else {
                this.$alert('绑定失败', '提示', {
                  confirmButtonText: '确定'
                });
              }
            });
          }
        });
      },

      trim(str, is_global) {
        var result;
        result = str.replace(/(^\s+)|(\s+$)/g, "");
        if (is_global.toLowerCase() == "g") {
          result = result.replace(/\s/g, "");
        }
        return result;
      },
      submitconfirmData(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            api.post('/bankCard/submitconfirmData', this.confirmData).then((response) => {
              if (response.data.code === 1) {
                if (response.data.data.bankCardBankBranch == "error") {
                  this.$alert(response.data.data.bankCardBank, '提示', {
                    confirmButtonText: '确定'
                  });
                } else {
                  var self = this;
                  this.confirmDataVisible = false; //关闭验证码
                  this.$alert('绑定成功,点击确定,跳转到《银行卡管理》', '提示', {
                    confirmButtonText: '确定',
                    callback: action => {
                      setTimeout(function () {
                        self.$router.push({
                          name: '银行卡管理',
                          params: {}
                        })
                      }, 1000);
                    }

                  });
                }
              } else {
                this.$alert('绑定失败', '提示', {
                  confirmButtonText: '确定'
                });
              }
            });
          }
        });
      },
      getBankCardBankBranchInit() {
        this.formData.bankCardBankBranchTemp = '';
        this.formData.bankCardBankBranch = '';
        this.formData.bankCardBankBranchName = '';
      }
    },
    mounted() {
      this.getData();
      this.getBankCardBankList(1);
      this.getProvince();
    },
    watch: {
      'formData.bankCardNo'(val) {
        this.formData.bankCardNo = val.replace(/\s/g, '').replace(/[^\d]/g, '').replace(/(\d{4})(?=\d)/g, '$1 ');
      }
    }
  }
</script>

<style scoped>

</style>
