<template>
<el-container class="front-loyout">
  <el-header>
    <layout-front-head/>
  </el-header>
  <el-main :style="{'min-height': getMinHeight+'px' + ' !important'}">
    <router-view />
  </el-main>
  <el-footer height="30px" style="padding: 0px;">
  <layout-front-footer/>
  </el-footer>
</el-container>
</template>


<script>
import LayoutFrontHead from './FrontHead.vue'
import LayoutFrontMain from './FrontMain.vue'
import LayoutFrontFooter from './FrontFooter.vue'

export default {
  name: 'FrontLoyout',
  data() {
    return {
      minHeight: document.documentElement.clientHeight - 160
    }
  },
  computed: {
    getMinHeight: function() {
      return this.$root.$data.documentClientHeight - 160
    }
  },
  components: {
    LayoutFrontHead,
    LayoutFrontMain,
    LayoutFrontFooter
  },
  mounted() {}
}
</script>

<style>

</style>
