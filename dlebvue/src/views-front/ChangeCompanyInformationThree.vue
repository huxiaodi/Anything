<template>
  <div class="content-page">
    <el-row class="page-title">
      <el-col :span="12">
        <router-link to="/AccountCentral">账户中心</router-link>
        <i class="el-icon-arrow-right"></i> 修改企业信息
      </el-col>
    </el-row>
    <el-row class="content-page">
      <div class="company">
        <el-form ref="companyForm" :rules="companyRules" :model="companyForm" label-width="140px">
          <div class="form-title">
            企业信息
          </div>
          <el-row>
            <el-col :span="12">
              <el-form-item label="用户名" prop="userName">
                <el-input style="width: 60%" v-model="this.userName" disabled="disabled"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="绑定手机号" prop="companyContactTel">
                <el-input style="width: 60%" v-model="companyForm.companyContactTel" disabled="disabled"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="企业法人名称" prop="companyPrincipalName">
                <el-input style="width: 60%" type="text" v-model="companyForm.companyPrincipalName"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="法人身份证" prop="companyPrincipalIdCode">
                <el-input style="width: 60%" type="text" v-model="companyForm.companyPrincipalIdCode"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="企业名称" prop="companyName">
                <el-input style="width: 60%" type="text" v-model="companyForm.companyName"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="对公账户开户行" prop="companyToPublicBank">
                <el-input style="width: 60%" type="text" v-model="companyForm.companyToPublicBank"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="企业简称" prop="companyShortName">
                <el-input style="width: 60%" type="text" v-model="companyForm.companyShortName"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="对公账户账号" prop="companyToPublicAccountBank">
                <el-input style="width: 60%" type="text" v-model="companyForm.companyToPublicAccountBank"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="联系人" prop="companyContact">
                <el-input style="width: 60%" type="text" v-model="companyForm.companyContact"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="纳税人识别号" prop="companyTaxpayerRegistrationNumber">
                <el-input style="width: 60%" type="text" v-model="companyForm.companyTaxpayerRegistrationNumber"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="联系人身份证" prop="companyContactIdCard">
                <el-input style="width: 60%" type="text" v-model="companyForm.companyContactIdCard"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="公司传真" prop="companyFax">
                <el-input style="width: 60%" type="text" v-model="companyForm.companyFax"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="注册资金" prop="companyRegistedCapital">
                <el-input style="width: 60%" type="text" v-model="companyForm.companyRegistedCapital"><i slot="suffix">万元</i></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="公司邮箱" prop="companyEmail">
                <el-input style="width: 60%" type="text" v-model="companyForm.companyEmail"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-form-item label="公司地址" prop="shipperCity">
              <el-col :span="10">
                <el-select v-model="companyForm.companyProvince" placeholder="全部" style="width: 31%;">
                  <el-option
                    v-for="item in provinces"
                    @click.native="getCity(item)"
                    :key="item.areaId"
                    :label="item.areaName"
                    :value="item.areaName">
                  </el-option>
                </el-select>
                <el-select v-model="companyForm.companyCity" placeholder="全部" style="width: 31%;">
                  <el-option
                    v-for="item in citys"
                    @click.native="getDistrict(item)"
                    :key="item.areaId"
                    :label="item.areaName"
                    :value="item.areaName">
                  </el-option>
                </el-select>
                <el-select v-model="companyForm.companyDistrict" placeholder="全部" style="width: 31%;">
                  <el-option
                    v-for="item in districts"
                    :key="item.areaId"
                    :label="item.areaName"
                    :value="item.areaName">
                  </el-option>
                </el-select>
              </el-col>
              <el-col :span="14">
                <el-input v-model="companyForm.companyAddress" placeholder="详细地址" style="width: 70%"></el-input>
              </el-col>
            </el-form-item>
          </el-row>
          <div class="form-title">
            证件信息
          </div>
          <el-row>
            <el-col :span="12">
              <el-form-item label="组织机构代码" prop="companyOrganizationCode">
                <el-input style="width: 60%" type="text" v-model="companyForm.companyOrganizationCode"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="营业执照号码" prop="companyLicenseNo">
                <el-input style="width: 60%" type="text" disabled v-model="companyForm.companyLicenseNo"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="税务登记证" prop="companyTaxNo">
                <el-input style="width: 60%" type="text" v-model="companyForm.companyTaxNo"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="8" style="text-align:center">
              <el-form class="avatar-uploader">
                <img v-if="this.companyForm.companyOrganizationCodeImg" :src="this.companyForm.companyOrganizationCodeImg"
                     class="avatar">
                <i @click="dialogFormVisible1 = true" v-else class="el-icon-plus avatar-uploader-icon company-form-img"></i>
              </el-form>
              <el-button type="text" @click="dialogFormVisible1 = true">上传图片(组织机构代码证)</el-button>
            </el-col>
            <el-col :span="8" style="text-align:center">
              <el-form class="avatar-uploader">
                <img v-if="this.companyForm.companyLicenseNoImg" :src="this.companyForm.companyLicenseNoImg"
                     class="avatar">
                <i @click="dialogFormVisible2 = true" v-else class="el-icon-plus avatar-uploader-icon company-form-img"></i>
              </el-form>
              <el-button type="text" @click="dialogFormVisible2 = true">上传图片(营业执照)</el-button>
            </el-col>
            <el-col :span="8" style="text-align:center">
              <el-form class="avatar-uploader">
                <img v-if="this.companyForm.companyTaxNoImg" :src="this.companyForm.companyTaxNoImg"
                     class="avatar">
                <i @click="dialogFormVisible3 = true" v-else class="el-icon-plus avatar-uploader-icon company-form-img"></i>
              </el-form>
              <el-button type="text" @click="dialogFormVisible3 = true">上传图片(税务登记证)</el-button>
            </el-col>
          </el-row>
          <el-row style="text-align:center;margin-top:20px;">
            <el-button type="primary" @click="submitForm('companyForm')">保存</el-button>
          </el-row>
        </el-form>
        <el-dialog title="修改组织机构代码证" :visible.sync="dialogFormVisible1">
          <el-upload
            ref="font"
            action=""
            :file-list="uploadOrganizationFile"
            :show-file-list="true"
            :limit="2"
            :on-change="showOrganization"
            :on-remove="organizationRemove"
            :auto-upload="false">
            <img v-if="uploadOrganizationFile[0]" :src="uploadOrganizationFile[0].url" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible1 = false">取 消</el-button>
            <el-button type="primary" @click="uploadOrganization()">确 定</el-button>
          </div>
        </el-dialog>
        <el-dialog title="修改营业执照" :visible.sync="dialogFormVisible2">
          <el-upload
            ref="font"
            action=""
            :file-list="uploadLicenseFile"
            :show-file-list="true"
            :limit="2"
            :on-change="showLicense"
            :on-remove="licenseRemove"
            :auto-upload="false">
            <img v-if="uploadLicenseFile[0]" :src="uploadLicenseFile[0].url" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible2 = false">取 消</el-button>
            <el-button type="primary" @click="uploadLicense()">确 定</el-button>
          </div>
        </el-dialog>
        <el-dialog title="修改税务登记证" :visible.sync="dialogFormVisible3">
          <el-upload
            ref="font"
            action=""
            :file-list="uploadTaxFile"
            :show-file-list="true"
            :limit="2"
            :on-change="showTax"
            :on-remove="taxRemove"
            :auto-upload="false">
            <img v-if="uploadTaxFile[0]" :src="uploadTaxFile[0].url" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible3 = false">取 消</el-button>
            <el-button type="primary" @click="uploadTax()">确 定</el-button>
          </div>
        </el-dialog>
      </div>
    </el-row>
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
  // 组织机构代码
  var companyOrganizationCodeReg = /^[a-zA-Z0-9]{8}-[a-zA-Z0-9]$/;
  // 15位数字
  var fifteenReg = /^\d{15}$/;
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
        dialogFormVisible1: false,
        dialogFormVisible2: false,
        dialogFormVisible3: false,
        userName: '',
        uploadOrganizationFile: [],
        uploadLicenseFile: [],
        uploadTaxFile: [],
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
          companyUnifiedSocialCreditLdentifier: '',
          companyOrganizationCode: '',
          companyLicenseNo: '',
          companyTaxNo: '',
          companyOrganizationCodeImg: '',
          companyLicenseNoImg: '',
          companyTaxNoImg: ''
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
          companyOrganizationCode: [
            {required: true, message: '组织机构代码不能为空', trigger: 'blur'},
            {pattern: companyOrganizationCodeReg, message: '请输入正确的组织机构代码', trigger: 'blur'}
          ],
          companyLicenseNo: [
            {required: true, message: '营业执照号码不能为空', trigger: 'blur'},
            {pattern: fifteenReg, message: '请输入正确的营业执照号码', trigger: 'blur'}
          ],
          companyTaxNo: [
            {required: true, message: '税务登记证号不能为空', trigger: 'blur'},
            {pattern: fifteenReg, message: '请输入正确的税务登记证号码', trigger: 'blur'}
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
      // 修改组织机构代码证
      uploadOrganization() {
        if (this.uploadOrganizationFile.length !== 1) {
          Message.MessageError('请选择图片')
        }
        var formData = new FormData();
        formData.append('organization', this.uploadOrganizationFile[0].raw);
        formData.append('companyId', this.$root.$data.user.userCompanyId);
        api.post('user/uploadOrganization', formData).then(response => {
          this.dialogFormVisible1 = false
          this.getCompanyInformation()
        })
      },
      // 修改营业执照
      uploadLicense() {
        if (this.uploadLicenseFile.length !== 1) {
          Message.MessageError('请选择图片')
        }
        var formData = new FormData();
        formData.append('license', this.uploadLicenseFile[0].raw);
        formData.append('companyId', this.$root.$data.user.userCompanyId);
        api.post('user/uploadLicense', formData).then(response => {
          this.dialogFormVisible2 = false
          this.getCompanyInformation()
        })
      },
      // 修改税务登记证
      uploadTax() {
        if (this.uploadTaxFile.length !== 1) {
          Message.MessageError('请选择图片')
        }
        var formData = new FormData();
        formData.append('tax', this.uploadTaxFile[0].raw);
        formData.append('companyId', this.$root.$data.user.userCompanyId);
        api.post('user/uploadTax', formData).then(response => {
          this.dialogFormVisible3 = false
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
      // 营业执照上传预览
      showLicense(file, fileList) {
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isLt2M) {
          this.$message.error('上传图片大小不能超过 2MB!');
        }
        this.uploadLicenseFile.pop();
        this.uploadLicenseFile.push(file);
      },
      // 组织机构代码证上传预览
      showOrganization(file, fileList) {
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isLt2M) {
          this.$message.error('上传图片大小不能超过 2MB!');
        }
        this.uploadOrganizationFile.pop();
        this.uploadOrganizationFile.push(file);
      },
      // 税务登记照片上传预览
      showTax(file, fileList) {
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isLt2M) {
          this.$message.error('上传图片大小不能超过 2MB!');
        }
        this.uploadTaxFile.pop();
        this.uploadTaxFile.push(file);
      },
      // 营业执照移除
      licenseRemove() {
        this.uploadLicenseFile.pop();
      },
      // 组织机构代码证移除
      organizationRemove() {
        this.uploadOrganizationFile.pop();
      },
      // 税务登记照片移除
      taxRemove() {
        this.uploadTaxFile.pop();
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
      }
    },
    mounted() {
      this.getProvince()
      this.getCompanyInformation()
      this.userName = this.$root.$data.user.userName
    }
  }
</script>
<style scoped>
#tab-1, #tab-2 {
  width: 100%;
  height: 40px;
  font-size: 200%;
}

/* tab标签大小 */
#registerTab .el-tabs__nav {
  width: 50%;
}

#registerTab .el-tabs__nav-scroll {
  width: 60%;
  margin: 0 auto;
}

/* 图片选择框大小 */
.el-upload {
  width: 100%;
  margin: auto auto;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  line-height: 110px;
  text-align: center;
}

/* 身份证图片大小*/
.avatar {
  width: 100%;
  height: 100%;
}

.avatar2 {
  width: 80%;
  height: 80%;
}

.form-title {
  width: 95px;
  border-radius: 5px;
  text-align: center;
  padding: 5px;
  margin: 20px auto;
  border: 1px solid #ddd;
  border-left: 20px solid #0099ff;
  font-size: 14px;
  color: #000;
}
.company-form-img{
  width: 110px;
  height: 110px;
  text-align: center;
  border: 1px dashed #d9d9d9;
  cursor: pointer;
}
</style>
