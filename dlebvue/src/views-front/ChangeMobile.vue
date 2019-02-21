<template>
  <div class="page1180">
    <div class="page-title">
      <div style="float:left;">
        <router-link to="/AccountCentral">账户中心</router-link>
        <i class="el-icon-arrow-right"></i>
        更换联系人
      </div>
    </div>
    <div class="one-column">
      <el-form ref="mobileForm" :model="mobileForm" :rules="rules" label-width="140px" class="form">

        <el-row>
          <el-form-item label="旧联系人手机号">
            <el-input v-model="mobileForm.mobile" :readonly="true"></el-input>
          </el-form-item>
        </el-row>

        <el-row>
          <el-form-item label="验证码" prop="verification">

              <el-input class="column-verification-input" v-model="mobileForm.verification"></el-input>
              <el-button size="medium" :disabled="sendMsgDisabled || isDisabled" type="primary" @click="getVerificationCode(mobileForm.mobile)" class="column-verification-btn">
                <span v-if="sendMsgDisabled">{{time+'秒后再次获取'}}</span>
                <span v-if="!sendMsgDisabled">获取验证码</span>
              </el-button>

          </el-form-item>
        </el-row>

        <el-row style="height:60px">
          <el-form-item label="新联系人手机号" prop="newMobile">
            <el-input v-model="mobileForm.newMobile"></el-input>
          </el-form-item>
        </el-row>

        <el-row>
          <el-form-item label="验证码" prop="newVerification">
            <el-input class="column-verification-input" v-model="mobileForm.newVerification">
            </el-input>
            <el-button  size="medium" :disabled="sendMsgDisabled2 || isDisabled" type="primary" @click="getNewVerificationCode(mobileForm.newMobile)" class="column-verification-btn">
              <span v-if="sendMsgDisabled2">{{time2+'秒后再次获取'}}</span>
              <span v-if="!sendMsgDisabled2">获取验证码</span>
            </el-button>
          </el-form-item>
        </el-row>
        <el-row style="width:100%;text-align:center">
          <el-button style="margin:20px 25px 20px;display:inline-block;width:80px" type="primary" :disabled="isDisabled" @click="mobileFormSubmit">提交
          </el-button>
          <el-button type="primary " :disabled="isDisabled" @click="cancel" style="margin:20px 25px 20px;display:inline-block;width:80px">取消</el-button>
        </el-row>
      </el-form>
    </div>

  </div>
</template>

<script>
  import api from '@/api/fetch'
  import * as Message from '../utils/messageUtil'

  var qs = require('qs');
  // 手机号
  var mobileReg = /^1[34578]\d{9}$/;

  export default {
    name: 'changeMobile',
    data() {
      return {
        sendMsgDisabled: false,
        time: 60,
        sendMsgDisabled2: false,
        time2: 60,
        isDisabled: false,
        mobileForm: {
          mobile: '',
          verification: '',
          newMobile: '',
          newVerification: ''
        },
        rules: {
          verification: [
            {required: true, message: ' ', trigger: 'blur'}
          ],
          newMobile: [
            {required: true, message: '请输入新修改手机号', trigger: 'blur'},
            {pattern: mobileReg, message: '请输入正确的手机号'}
          ],
          newVerification: [
            {required: true, message: ' ', trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      // 获取验证码
      getVerificationCode(mobile) {
        if (mobile && mobileReg.test(mobile)) {
          api.post('/user/register/sendVerificationCode', qs.stringify({mobile: mobile})).then(response => {
            if (response.data.code === 1) {
              Message.MessageSuccess(response.data.data);
              // 1分钟后再次点击获取验证码
              let me = this;
              me.sendMsgDisabled = true;
              let interval = window.setInterval(function() {
                if ((me.time--) <= 0) {
                  me.time = 60;
                  me.sendMsgDisabled = false;
                  window.clearInterval(interval);
                }
              }, 1000);
            } else {
              Message.MessageError(response.data.msg)
            }
          })
        } else {
          Message.MessageError('请填写正确的手机号')
        }
      },
      // 获取验证码
      getNewVerificationCode(mobile) {
        if (mobile && mobileReg.test(mobile)) {
          api.post('/user/changeMobile/sendNewVerificationCode', qs.stringify({mobile: mobile})).then(response => {
            if (response.data.code === 1) {
              Message.MessageSuccess(response.data.data);
              // 1分钟后再次点击获取验证码
              let me = this;
              me.sendMsgDisabled2 = true;
              let interval = window.setInterval(function() {
                if ((me.time2--) <= 0) {
                  me.time2 = 60;
                  me.sendMsgDisabled2 = false;
                  window.clearInterval(interval);
                }
              }, 1000);
            } else {
              Message.MessageError(response.data.msg)
            }
          })
        } else {
          Message.MessageError('请填写正确的手机号')
        }
      },
      // 提交修改
      mobileFormSubmit() {
        if (this.mobileForm.mobile === this.mobileForm.newMobile) {
          Message.MessageError('新联系人手机号与旧联系人手机号相同');
          return false;
        }
        this.$refs['mobileForm'].validate((valid) => {
          if (valid) {
            api.post('/user/changeMobile', this.mobileForm).then(response => {
              if (response.data.code === 1) {
                this.isDisabled = true;
                this.$refs['mobileForm'].resetFields()
                var router = this.$router;
                this.$message({
                  message: response.data.data,
                  type: 'success',
                  duration: 2000,
                  onClose: function () {
                    router.push('/AccountCentral');
                  }
                });
              } else {
                Message.MessageError(response.data.msg)
              }
            })
          } else {
            return false;
          }
        })
      },
      //  取消
      cancel() {
        this.$refs['mobileForm'].resetFields()
        this.$router.push('/AccountCentral');
      }
    },
    mounted() {
      this.mobileForm.mobile = this.$route.params.mobile;
    }
  }
</script>

<style>


</style>
