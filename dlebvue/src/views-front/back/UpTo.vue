<template>
  <div width="100%" height="1000px;">
    <iframe name="myFrame" width="100%" style="height: 500px"></iframe>
    <el-form target="myFrame" ref="PayForm" NAME="PayForm"
             :action="form.action" v-show="false"
             METHOD="POST">
      <el-input type="hidden" name="orig" v-model="form.orig"></el-input>
      <el-input type="hidden" id="sign" name="sign" v-model="form.sign"></el-input>
      <el-input type="hidden" id="returnurl" name="returnurl" v-model="form.returnurl"></el-input>
      <el-input type="hidden" id="NOTIFYURL" name="NOTIFYURL" v-model="form.notifyurl"></el-input>
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
    name: 'UpTo',
    data() {
      return {
        tableList: [],
        form: {
          action: '',
          orig: '',
          sign: '',
          returnurl: '',
          notifyurl: ''
        }
      }
    },
    methods: {
      getData() {
        window.sp = this;
        var money = this.$route.query.money;
        var fee = this.$route.query.fee;
        var remark = this.$route.query.remark;
        api.get('/pay/getPayInfo?money=' + money + '&fee=' + fee + '&remark=' + remark).then(response => {
          debugger
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
