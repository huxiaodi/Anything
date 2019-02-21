<template>
  <div class="page1180">
    <div class="page-title">
      <div style="float:left;">
        <router-link to="/AccountCentral">账户中心</router-link>
        <i class="el-icon-arrow-right"></i>
        修改个人信息
      </div>
    </div>
    <div class="one-column">
        <el-form ref="personalForm" :rules="personalRules" :model="personalForm" label-width="120px">
          <el-row style="width:500px;margin:0 auto;">
            <el-row>
              <el-form-item label="用户名" prop="userName">
                <el-input disabled="disabled" v-model="personalForm.userName"></el-input>
              </el-form-item>
            </el-row>
            <el-row>
              <el-form-item label="姓名" prop="userCnName">
                <el-input v-model="personalForm.userCnName"></el-input>
              </el-form-item>
            </el-row>
            <el-row>
              <el-form-item label="手机号" prop="userMobile">
                <el-input disabled="disabled" v-model="personalForm.userMobile"></el-input>
              </el-form-item>
            </el-row>
            <el-row>
              <el-col>
                <el-form-item label="证件类型">
                  <el-input v-model="personalForm.userCertificateType" disabled="disabled"></el-input>
                </el-form-item>
              </el-col>
            </el-row>
            <el-row>
              <el-form-item label="证件号码" prop="userCertificateNumber">
                <el-input disabled v-model="personalForm.userCertificateNumber"></el-input>
              </el-form-item>
            </el-row>
          </el-row>
          <el-row >
            <el-form-item label="证件图片">
              <el-col :span="11" class="upload-box">

                <img v-if="this.personalForm.userIdCardFontImg" :src="this.personalForm.userIdCardFontImg"
                     class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"></i></br>
                <el-button type="text" @click="dialogFormVisible1 = true">上传图片（正面）</el-button>
              </el-col>
              <el-col :span="11" :offset="2" class="upload-box">
                <img v-if="this.personalForm.userIdCardBackImg" :src="this.personalForm.userIdCardBackImg"
                     class="avatar">
                <i v-else class="el-icon-plus avatar-uploader-icon"></i></br>
                <el-button type="text" @click="dialogFormVisible2 = true">上传图片（反面）</el-button>
              </el-col>
            </el-form-item>

          </el-row>
          <el-row style="text-align:center">
            <el-button type="primary" @click="submitForm('personalForm')">提交</el-button>
          </el-row>
        </el-form>
        <el-dialog title="修改证件照片(正面)" :visible.sync="dialogFormVisible1">
          <el-upload ref="font" action="" :file-list="uploadFontFile" :show-file-list="true" :limit="2"
                     :on-change="showFont" :on-remove="fontRemove" :auto-upload="false">
            <img v-if="uploadFontFile[0]" :src="uploadFontFile[0].url" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible1 = false">取 消</el-button>
            <el-button type="primary" @click="pictureFontUpload()">确 定</el-button>
          </div>
        </el-dialog>
        <el-dialog title="修改证件照片(反面)" :visible.sync="dialogFormVisible2">
          <el-upload ref="back" action="" :file-list="uploadBackFile" :show-file-list="true" :limit="2"
                     :on-change="showBack" :on-remove="backRemove" :auto-upload="false">
            <img v-if="uploadBackFile[0]" :src="uploadBackFile[0].url" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible2 = false">取 消</el-button>
            <el-button type="primary" @click="pictureBackUpload()">确 定</el-button>
          </div>
        </el-dialog>
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

  export default {
    name: 'ChangePersonalInformation',
    data() {
      var validateNum = (rule, value, callback) => {
        if (!value) {
          callback(new Error('请输入证件号码'))
        } else if (!cardReg.test(value) && this.personalForm.userCertificateType === '身份证') {
          callback(new Error('请输入正确的身份证格式'))
        } else {
          callback()
        }
      }
      return {
        dialogFormVisible1: false,
        dialogFormVisible2: false,
        uploadFontFile: [],
        uploadBackFile: [],
        personalForm: {
          userName: '',
          password: '',
          checkPassword: '',
          userMobile: '',
          checkNum: '',
          userCnName: '',
          userCertificateType: '身份证',
          userCertificateNumber: '',
          userEmail: '',
          userIdCardFontImg: '',
          userIdCardBackImg: ''
        },
        personalRules: {
          userMobile: [{
            required: true,
            message: '手机号不能为空',
            trigger: 'blur'
          },
            {
              pattern: mobileReg,
              message: '请输入正确的手机号',
              trigger: 'blur'
            }
          ],
          checkNum: [{
            required: true,
            message: '验证码不能为空',
            trigger: 'blur'
          }],
          userCnName: [{
            required: true,
            message: '姓名不能为空',
            trigger: 'blur'
          }],
          userCertificateNumber: [{
            required: true,
            validator: validateNum,
            trigger: 'blur'
          }],
          userEmail: [{
            max: 50,
            message: '长度小于50个字符',
            trigger: 'blur'
          }]
        }
      }
    },
    methods: {
      // 获取个人用户信息
      getPersonalInformation() {
        api.post('user/getPersonalInformation', {
          'userId': this.$root.$data.user.userId
        }).then(response => {
          if (response.data.code === 1) {
            this.personalForm = response.data.data
            if (response.data.data.userCertificateType === '1') {
              this.personalForm.userCertificateType = '身份证'
            }
          }
        })
      },
      pictureFontUpload() {
        if (this.uploadFontFile.length !== 1) {
          Message.MessageError('请选择图片')
        }
        var formData = new FormData();
        formData.append('font', this.uploadFontFile[0].raw);
        formData.append('userId', this.$root.$data.user.userId);
        api.post('user/uploadFont', formData).then(response => {
          this.dialogFormVisible1 = false
          this.getPersonalInformation()
        })
      },
      pictureBackUpload() {
        if (this.uploadBackFile.length !== 1) {
          Message.MessageError('请选择图片')
        }
        var formData = new FormData();
        formData.append('back', this.uploadBackFile[0].raw);
        formData.append('userId', this.$root.$data.user.userId);
        api.post('user/uploadBack', formData).then(response => {
          this.dialogFormVisible2 = false
          this.getPersonalInformation()
        })
      },
      // 身份证正面上传预览
      showFont(file, fileList) {
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isLt2M) {
          this.$message.error('上传图片大小不能超过 2MB!');
        }
        this.uploadFontFile.pop();
        this.uploadFontFile.push(file);
      },
      // 身份证反面上传预览
      showBack(file, fileList) {
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isLt2M) {
          this.$message.error('上传图片大小不能超过 2MB!');
        }
        this.uploadBackFile.pop();
        this.uploadBackFile.push(file);
      },
      // 身份证正面图片移除
      fontRemove() {
        this.uploadFontFile.pop();
      },
      // 身份证反面图片移除
      backRemove() {
        this.uploadBackFile.pop();
      },
      // 提交修改
      submitForm(formName) {
        var formData = new FormData();
        var userForm = {};
        this.personalForm.userCertificateType = '1'
        userForm = this.personalForm;
        this.$refs[formName].validate((valid) => {
          if (valid) {
            formData.append('userStr', JSON.stringify(userForm));
            api.post('user/changePersonalInformation', formData);
            Message.MessageSuccess('修改成功')
            this.$router.push('/AccountCentral')
          } else {
            return false;
          }
        });
      }
    },
    mounted() {
      this.getPersonalInformation()
      this.userName = this.$root.$data.user.userName
    }
  }
</script>
<style scoped>
.upload-box.el-col.el-col-11 br{
  display: none;
}

</style>
