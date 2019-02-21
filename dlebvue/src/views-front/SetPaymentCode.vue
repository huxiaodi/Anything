<template>
  <div width="100%" height="1000px;">
    <iframe name="myFrame" width="100%" style="height: 500px"></iframe>
    <el-form target="myFrame" ref="PayForm" NAME="PayForm"
             :action="form.frontUrl" v-show="false"
             METHOD="POST">
      <el-input type="hidden" name="orderid" v-model="form.orderId"></el-input>
      <el-input type="hidden" id="type" name="type" v-model="form.type"></el-input>
      <el-input type="hidden" id="P2PCode" name="P2PCode" v-model="form.p2PCode"></el-input>
      <el-input type="hidden" id="P2PType" name="P2PType" v-model="form.p2PType"></el-input>
      <el-input type="hidden" id="thirdCustId" name="thirdCustId" v-model="form.thirdCustId"></el-input>
      <el-input type="hidden" id="custAccId" name="custAccId" v-model="form.custAccId"></el-input>
      <el-input type="hidden" id="name" name="name" v-model="form.name"></el-input>
      <el-input type="hidden" id="idType" name="idType" v-model="form.idType"></el-input>
      <el-input type="hidden" id="idNo" name="idNo" v-model="form.idNo"></el-input>
      <el-input type="hidden" id="mobile" name="mobile" v-model="form.mobile"></el-input>
      <el-input type="hidden" id="accNo" name="accNo" v-model="form.accNo"></el-input>
      <el-input type="hidden" id="orig" name="orig" v-model="form.orig"></el-input>
      <el-input type="hidden" id="returnurl" name="returnurl" v-model="form.returnurl"></el-input>
      <el-input type="hidden" id="notifyUrl" name="notifyUrl" v-model="form.notifyUrl"></el-input>
      <el-input type="hidden" id="channelType" name="channelType"></el-input>
      <el-input TYPE=SUBMIT NAME="FormsButton1" VALUE="提交" ID="FormsButton2"></el-input>
    </el-form>

  </div>
</template>

<script>
  import api from '../api/fetch'
  import {parseTime} from '../utils/index'
  import * as Message from '../utils/messageUtil'
  import {pageMixin} from '../common/mixin'
  import {exportModel} from '../common/export'

  export default {
    name: 'SetPaymentCode',
    data() {
      return {
        tableList: [],
        form: {
          orderId: '',
          type: '',
          p2PCode: '',
          p2PType: '',
          thirdCustId: '',
          custAccId: '',
          name: '',
          idType: '',
          idNo: '',
          accNo: '',
          accName: '',
          mobile: '',
          orig: '',
          returnurl: '',
          notifyUrl: '',
          channelType: ''
        }
      }
    },
    methods: {
      getData() {
        window.sp = this;
        var accountSubNo = this.$route.query.accountSubNo;
        var type = this.$route.query.type;
        var mobile = this.$route.query.mobile;
        api.get('/accountSub/accountSubController/paymentCodeManage?accountSubNo=' + accountSubNo + '&type=' + type + '&mobile=' + mobile).then(response => {
          if (response.data.code === 1) {
            this.form = response.data.data;
            this.timeOut = setTimeout(() => {
              this.$refs.PayForm.$el.submit();
            }, 1000);
          } else {
            Message.MessageError(response.data.msg)
          }
        })
      }
    },
    mounted() {
      this.getData();
    }
  }
</script>

<style scoped>

</style>
