<template>
  <div>
    <el-card class="el-card">
      <el-form ref="messageForm" style="text-align: left;background-color: transparent" class="form"
               :model="messageForm"
               :rules="rules" label-width="110px" label-position="left">
        <el-row>
          <el-col :xs="20" :sm="6" class="col">
            <el-form-item label="回填手机验证码">
              <el-input v-model="messageForm.MessageCheckCode"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col align="center">
            <el-button type="primary" @click="messageFormSubmit('messageForm')" :disabled="this.isSaveDisable">提交绑定
            </el-button>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
  </div>
</template>

<script>
  import api from '@/api/fetch'
  import * as Message from '../utils/messageUtil'
  import axios from 'axios'

  var qs = require('qs');
  export default {
    name: 'order',
    data() {
      return {
        messageForm: {
          MessageCheckCode: '',
          MemberAcctNo: '',
          SubAcctNo: '',
          TranNetMemberCode: ''
        },
        shortMessage: '',
        isSaveDisable: false
      }
    },
    methods: {
      // 提交
      messageFormSubmit(formName) {
        this.messageForm.TranNetMemberCode = this.$root.$data.user.userId // 交易网会员代码
        this.messageForm.SubAcctNo = sessionStorage['dleb_userAccountId'] // 会员子账户账号
        this.messageForm.MemberAcctNo = sessionStorage['dleb_MemberAcctNo'] // 银行卡号
        api.post('/bind/BindRelateAccReUnionPay', this.messageForm).then(response => {
          if (response.data.code === 1) {
            Message.MessageSuccess('绑定成功')
            this.isSaveDisable = true
          } else {
            Message.MessageError(response.data.msg)
          }
        })
      }
    },
    mounted() {
      this.messageForm.MemberAcctNo = this.$route.params.MemberAcctNo
    }
  }
</script>

<style scoped>
  .el-card {
    margin: auto;
    background-color: #edf2fc;
  }

  .el-select {
    width: 100%;
  }

  .el-input-number {
    width: 100%;
  }

  .el-row {
    margin-top: 6px;
  }

  .col {
    margin-left: 38%;
  }
</style>
