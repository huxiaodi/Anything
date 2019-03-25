<template>
  <div style="height:100%;">
     {{companyLine}}
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
