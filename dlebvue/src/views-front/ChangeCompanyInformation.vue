<template>
  <div class="page1180">
    <div class="page-title">
      <div style="float:left;">
        <router-link to="/AccountCentral">账户中心</router-link>
        <i class="el-icon-arrow-right"></i>
        修改企业信息
      </div>
    </div>
    <div class="page-content">
        <el-form ref="companyForm" class="often-input-list" :rules="companyRules" :model="companyForm" label-width="140px">
          <div class="form-title">
            企业信息
          </div>
          <el-row>
            <el-col :span="12">
              <el-row>
                <el-form-item label="用户名" prop="userName">
                  <el-input v-model="this.userName" disabled="disabled" ></el-input>
                </el-form-item>
                <el-form-item label="企业法人名称" prop="companyPrincipalName">
                  <el-input type="text" v-model="companyForm.companyPrincipalName" ></el-input>
                </el-form-item>
                <el-form-item label="企业名称" prop="companyName">
                  <el-input type="text" v-model="companyForm.companyName"></el-input>
                </el-form-item>
                <el-form-item label="企业简称" prop="companyShortName">
                  <el-input type="text" v-model="companyForm.companyShortName"></el-input>
                </el-form-item>
                <el-form-item label="联系人" prop="companyContact">
                  <el-input type="text" v-model="companyForm.companyContact"></el-input>
                </el-form-item>
                <el-form-item label="联系人身份证号码" prop="companyContactIdCard">
                  <el-input type="text" v-model="companyForm.companyContactIdCard"></el-input>
                </el-form-item>
                <el-form-item label="注册资金" prop="companyRegistedCapital">
                  <el-input type="text" v-model="companyForm.companyRegistedCapital"
                  ><i slot="suffix">万元</i></el-input>
                </el-form-item>
              </el-row>
            </el-col>
            <!-- 右边列表 -->
            <el-col :span="12">
              <el-row>
                <el-form-item label="法人身份证" prop="companyPrincipalIdCode">
                  <el-input type="text" v-model="companyForm.companyPrincipalIdCode"></el-input>
                </el-form-item>
                <el-form-item label="对公账户开户行" prop="companyToPublicBank">
                  <el-input type="text" v-model="companyForm.companyToPublicBank"></el-input>
                </el-form-item>
                <el-form-item label="对公账户账号" prop="companyToPublicAccountBank">
                  <el-input type="text" v-model="companyForm.companyToPublicAccountBank"></el-input>
                </el-form-item>
                <el-form-item label="纳税人识别号" prop="companyTaxpayerRegistrationNumber">
                  <el-input type="text" v-model="companyForm.companyTaxpayerRegistrationNumber"></el-input>
                </el-form-item>
                <el-form-item label="公司传真" prop="companyFax">
                  <el-input type="text" v-model="companyForm.companyFax"></el-input>
                </el-form-item>
                <el-form-item label="公司邮箱" prop="companyEmail">
                  <el-input type="text" v-model="companyForm.companyEmail"></el-input>
                </el-form-item>

              </el-row>
            </el-col>
          </el-row>

          <el-row>
            <el-form-item class="address-input" label="公司地址" prop="shipperCity">
                <el-select v-model="companyForm.companyProvince" placeholder="省">
                  <el-option
                    v-for="item in provinces"
                    @click.native="getCity(item)"
                    :key="item.areaId"
                    :label="item.areaName"
                    :value="item.areaName">
                  </el-option>
                </el-select>
                <el-select v-model="companyForm.companyCity" placeholder="市" >
                  <el-option
                    v-for="item in citys"
                    @click.native="getDistrict(item)"
                    :key="item.areaId"
                    :label="item.areaName"
                    :value="item.areaName">
                  </el-option>
                </el-select>
                <el-select v-model="companyForm.companyDistrict" placeholder="区">
                  <el-option
                    v-for="item in districts"
                    :key="item.areaId"
                    :label="item.areaName"
                    :value="item.areaName">
                  </el-option>
                </el-select>
                <el-input v-model="companyForm.companyAddress" placeholder="详细地址"></el-input>
            </el-form-item>
          </el-row>
          <div class="form-title">
            证件信息
          </div>
          <el-row>
            <el-col :span="12">
              <el-form-item label="统一社会信用代码" prop="companyUnifiedSocialCreditLdentifier">
                <el-input type="text" disabled v-model="companyForm.companyUnifiedSocialCreditLdentifier"></el-input>
              </el-form-item>

              <!-- 后加的内容-------------------- -->
              <el-form-item label="注册时间" prop="?">
                <div>
                  <el-date-picker type="date" placeholder="选择日期" v-model="companyForm.companyUnifiedSocialDatetime"></el-date-picker>
                </div>
              </el-form-item>

              <el-form-item label="有效期至" prop="?">
                <div>
                  <el-date-picker type="date" placeholder="选择日期" v-model="companyForm.companyUnifiedSocialLimitDatetime"></el-date-picker>
                </div>
              </el-form-item>
              <!-- 后加的内容-------------------- -->
            </el-col>
            <!-- 上传区域 -->
            <el-col :span="12">
              <el-form-item label="统一社会信用照片" prop="companyUnifiedSocialCreditLdentifier">
                <div @click="dialogFormVisible = true" style="cursor:pointer;border: 1px dashed #d9d9d9;
                  width:110px;text-align:center;height:110px;">
                  <el-form class="avatar-uploader">
                    <img v-if="this.companyForm.companyUnifiedSocialImg" :src="this.companyForm.companyUnifiedSocialImg"
                         class="avatar">
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                  </el-form>
                </div>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row style="text-align:center">
            <el-button type="primary" @click="submitForm('companyForm')">保存</el-button>
          </el-row>

        </el-form>
        <el-dialog title="修改统一社会信用照片" :visible.sync="dialogFormVisible">
          <el-upload
            ref="font"
            action=""
            :file-list="uploadFontFile"
            :show-file-list="true"
            :limit="2"
            :on-change="showFont"
            :on-remove="fontRemove"
            :auto-upload="false">
            <img v-if="uploadFontFile[0]" :src="uploadFontFile[0].url" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="pictureUpload()">确 定</el-button>
          </div>
        </el-dialog>
        </el-card>
      </div>
  </div>

</template>

<script>
  import api from '@/api/fetch'
  import * as Message from '../utils/messageUtil'

  var qs = require('qs');
  // 手机号
  var mobileReg = /^1[34578]\d{9}$/;
  // 15或18位身份证
  var cardReg = /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}[0-9Xx]$)/;
  // 纯数字
  var numberReg = /^[0-9]*$/;
  // 邮箱
  var emailReg = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
  // 统一信用代码
  var companyUnifiedSocialCreditLdentifierReg = /^([0-9ABCDEFGHJKLMNPQRTUWXY]{2})([0-9]{6})([0-9ABCDEFGHJKLMNPQRTUWXY]{9})([0-9Y])$/;
  // 纳税人识别号
  var companyTaxpayerRegistrationNumberReg = /^((\d{6}[0-9A-Z]{9})|([0-9A-Za-z]{2}\d{6}[0-9A-Za-z]{10}))$/;
  // 银行卡号
  var bankCardReg = /^([1-9]{1})(\d{14}|\d{18})$/;
  // 传真
  var companyFaxReg = /^(\d{3,4}-)?\d{7,8}$/;

  export default {
    name: 'ChangeCompanyInformation',
    data() {
      return {
        ruleForm: {
          name: '',
          region: '',
          date1: '',

        },
        rules: {
          name: [
            {required: true, message: '请输入活动名称', trigger: 'blur'},
            {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'}
          ],
          date1: [
            {type: 'date', required: true, message: '请选择日期', trigger: 'blur'}
          ]
        },
        dialogFormVisible: false,
        userName: '',
        uploadFontFile: [],
        provinces: [],
        citys: [],
        districts: [],
        companyForm: {
          userCompanyId: '',
          companyContactTel: '',
          companyPrincipalName: '',
          companyPrincipalIdCode: '',
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
          companyUnifiedSocialCreditLdentifier: ''
        },
        companyRules: {
          companyFax: [
            {pattern: companyFaxReg, message: '请输入正确的传真号码', trigger: 'blur'}
          ],
          companyPrincipalName: [
            {required: true, message: '法人名称不能为空', trigger: 'blur'}
          ],
          companyPrincipalIdCode: [
            {required: true, message: '法人身份证号不能为空', trigger: 'blur'},
            {pattern: cardReg, message: '请输入正确的身份证号', trigger: 'blur'}
          ],
          companyName: [
            {required: true, message: '企业名称不能为空', trigger: 'blur'}
          ],
          companyToPublicBank: [
            {required: true, message: '对公账户开户行不能为空', trigger: 'blur'}
          ],
          companyToPublicAccountBank: [
            {required: true, message: '对公账户账号不能为空', trigger: 'blur'},
            {pattern: bankCardReg, message: '请输入正确的银行卡号', trigger: 'blur'}
          ],
          companyContact: [
            {required: true, message: '联系人不能为空', trigger: 'blur'}
          ],
          companyTaxpayerRegistrationNumber: [
            {required: true, message: '纳税人识别号不能为空', trigger: 'blur'},
            {pattern: companyTaxpayerRegistrationNumberReg, message: '请输入正确的纳税人识别号', trigger: 'blur'}
          ],
          companyContactIdCard: [
            {required: true, message: '联系人身份证号不能为空', trigger: 'blur'},
            {pattern: cardReg, message: '请输入正确的身份证号', trigger: 'blur'}
          ],
          companyUnifiedSocialCreditLdentifier: [
            {required: true, message: '统一社会信用代码不能为空', trigger: 'blur'},
            {pattern: companyUnifiedSocialCreditLdentifierReg, message: '请输入正确的统一信用代码', trigger: 'blur'}
          ],
          companyRegistedCapital: [
            {pattern: numberReg, message: '请输入纯数字', trigger: 'blur'}
          ],
          companyEmail: [
            {required: true, message: '邮箱不能为空', trigger: 'blur'},
            {pattern: emailReg, message: '请输入正确的邮箱', trigger: 'blur'}
          ]
        }
      }
    },

    methods: {
      // 获取企业信息
      getCompanyInformation() {
        api.post('user/getCompanyInformation', {'userCompanyId': this.$root.$data.user.userCompanyId}).then(response => {
          if (response.data.code === 1) {
            this.companyForm = response.data.data
          }
        })
      },
      pictureUpload() {
        if (this.uploadFontFile.length !== 1) {
          Message.MessageError('请选择图片')
        }
        var formData = new FormData();
        formData.append('font', this.uploadFontFile[0].raw);
        formData.append('companyId', this.$root.$data.user.userCompanyId);
        api.post('user/upload', formData).then(response => {
          this.dialogFormVisible = false
          this.getCompanyInformation()
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
      // 企业证书上传预览
      showFont(file, fileList) {
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isLt2M) {
          this.$message.error('上传图片大小不能超过 2MB!');
        }
        this.uploadFontFile.pop();
        this.uploadFontFile.push(file);
      },
      // 企业证书图片移除
      fontRemove() {
        this.uploadFontFile.pop();
      },
      // 提交修改
      submitForm(formName) {
        var formData = new FormData();
        var userForm = {};
        userForm = this.companyForm;
        this.$refs[formName].validate((valid) => {
          if (valid) {
            formData.append('userStr', JSON.stringify(userForm));
            api.post('user/changeCompanyInformation', formData);
            Message.MessageSuccess('修改成功')
            this.$router.push('/AccountCentral')
          } else {
            return false;
          }
        });
      },
      // 返回业务查询页面
      returnAccountCentral() {
        this.$router.push(`/AccountCentral`)
      }
    },
    mounted() {
      this.getProvince()
      this.getCompanyInformation()
      this.userName = this.$root.$data.user.userName
    },

  }
</script>
<style scoped>

</style>
