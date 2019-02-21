<template>
  <el-row class="front-footer">
    <el-col :span="24">
      Copyright ©2018 德邻e宝支付平台 当前版本:V1.0.0
    </el-col>
  </el-row>
</template>
<script>
  export default {
    name: 'FrontFooter',
    data() {
      return {
      }
    }
  }
</script>
<style scoped>
  .front-footer {
    height: 30px;
    line-height: 30px;
    background-color: #000;
    color: white;
  }

</style>
