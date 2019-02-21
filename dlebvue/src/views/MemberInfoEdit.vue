<template>
  <div class="admin-input-form">
    <el-form ref="memberInfoForm" :model="memberInfoForm" labelWidth="140px" labelPosition="right"
             :rules="rules">
     <div class="divTitle">会员信息修改</div>

      <el-row>
        <el-col>
          <el-form-item label="用户名">
            <el-input v-model="memberInfoForm.userName" :disabled="true"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <el-form-item label="姓名">
            <el-input v-model="memberInfoForm.userCnName" :disabled="true"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <el-form-item label="手机号">
            <el-input v-model="memberInfoForm.userMobile" :disabled="true"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <el-form-item label="证件类型" prop="userCertificateType">
            <el-select style="width: 100%" v-model="memberInfoForm.userCertificateType">
              <el-option
                v-for="item in optionsCertificatesType"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <el-form-item label="证件号码" prop="userCertificateNumber">
            <el-input v-model="memberInfoForm.userCertificateNumber"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
      <div class="admin-input-upload ">
        <el-form>
          <img v-if="this.memberInfoForm.userIdCardFontImg" :src="this.memberInfoForm.userIdCardFontImg"
               class="avatar" @click="dialogFormVisible1 = true">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-form>
        <el-button type="text" @click="dialogFormVisible1 = true">上传图片（正面）</el-button>
      </div>

      <div class="admin-input-upload ">
        <el-form >
          <img v-if="this.memberInfoForm.userIdCardBackImg" :src="this.memberInfoForm.userIdCardBackImg"
               class="avatar" @click="dialogFormVisible2 = true">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-form>
        <el-button type="text" @click="dialogFormVisible2 = true">上传图片（反面）</el-button>
      </div>


      </el-row>


      <div class="admin-input-form-btns">
        <el-button type="primary" @click="submitForm('memberInfoForm')">提交</el-button>
      </div>
    </el-form>
    <el-dialog title="修改证件照片(正面)" :visible.sync="dialogFormVisible1">
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
        <el-button @click="dialogFormVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="pictureFontUpload()">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="修改证件照片(反面)" :visible.sync="dialogFormVisible2">
      <el-upload
        ref="back"
        action=""
        :file-list="uploadBackFile"
        :show-file-list="true"
        :limit="2"
        :on-change="showBack"
        :on-remove="backRemove"
        :auto-upload="false">
        <img v-if="uploadBackFile[0]" :src="uploadBackFile[0].url" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible2 = false">取 消</el-button>
        <el-button type="primary" @click="pictureBackUpload()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import api from '../api/fetch'
  import {parseTime} from '../utils/index'
  import * as Message from '../utils/messageUtil'
  import {pageMixin} from '../common/mixin'
  import {exportModel} from '../common/export'

  // 15或18位身份证
  var cardReg = /(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}[0-9Xx]$)/;
  var passportReg = /^[a-zA-Z0-9]{5,17}$/

  export default {
    // 会员信息修改
    name: 'MemberInfoEdit',
    mixins: [pageMixin, exportModel],
    data() {
      var validateType = (rule, value, callback) => {
        if (!value) {
          callback(new Error('证件类型不能为空'))
        } else {
          callback()
        }
      }
      var validateNum = (rule, value, callback) => {
        if (!value) {
          callback(new Error('请输入证件号码'))
        } else if (this.memberInfoForm.userCertificateType === '1' && !cardReg.test(value)) {
          callback(new Error('请输入正确的身份证格式'))
        } else if (this.memberInfoForm.userCertificateType === '2' && !passportReg.test(value)) {
          callback(new Error('请输入正确的护照格式'))
        } else {
          callback()
        }
      }
      return {
        memberInfoForm: {
          userId: '',
          userName: '',
          userCnName: '',
          userMobile: '',
          userCertificateNumber: '',
          userCertificateType: '',
          userIdCardFontImg: '',
          userIdCardBackImg: ''
        },
        optionsCertificatesType: [{
          value: '1',
          label: '身份证'
        }, {
          value: '2',
          label: '护照'
        }],
        dialogFormVisible1: false,
        dialogFormVisible2: false,
        uploadFontFile: [],
        uploadBackFile: [],
        rules: {
          userCertificateType: [
            {validator: validateType, trigger: 'blur'}
          ],
          userCertificateNumber: [
            {validator: validateNum, trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      // 获取个人用户信息
      getMemberInfo() {
        api.post('admin/userManage/user/info', {'id': this.$route.params.id}).then(response => {
          if (response.data.code === 1) {
            this.memberInfoForm = response.data.data
          }
        })
      },
      // 上传证件正面
      pictureFontUpload() {
        if (this.uploadFontFile.length !== 1) {
          Message.MessageError('请选择图片')
        }
        var formData = new FormData();
        formData.append('file', this.uploadFontFile[0].raw);
        formData.append('userId', this.$route.params.id);
        api.post('admin/userManage/file/upload', formData).then(response => {
          this.memberInfoForm.userIdCardFontImg = response.data.data
          this.dialogFormVisible1 = false
        })
      },
      // 上传证件反面
      pictureBackUpload() {
        if (this.uploadBackFile.length !== 1) {
          Message.MessageError('请选择图片')
        }
        var formData = new FormData();
        formData.append('file', this.uploadBackFile[0].raw);
        formData.append('userId', this.$route.params.id);
        api.post('admin/userManage/file/upload', formData).then(response => {
          this.memberInfoForm.userIdCardBackImg = response.data.data
          this.dialogFormVisible2 = false
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
        this.$refs[formName].validate((valid) => {
          if (valid) {
            api.post('admin/userManage/user/save', this.memberInfoForm).then(response => {
              if (response.data.code === 1) {
                Message.MessageSuccess(response.data.msg)
                this.$router.push('/PersonalMemberList')
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
      this.getMemberInfo()
    }
  }
</script>

<style scoped>

</style>
