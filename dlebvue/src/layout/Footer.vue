<template>
  <div style="height:100%;">
      Copyright ©2018 德邻e宝支付平台
     {{companyLine}}
      当前版本:V1.0.0
  </div>
</template>
<script>
  export default {
    name: 'Footer',
    data() {
      return {
        companyLine: sessionStorage.getItem('dleb_companyLine')
      }
    }
  }
</script>
