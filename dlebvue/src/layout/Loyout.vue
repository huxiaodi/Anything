<template>
  <div class="admin">
    <!-- 页头 -->
    <layout-head/>
    <!-- 主页内容 -->
    <div class="admin-page">
      <layout-tabs/>
    </div>
    <!-- 页脚 -->
    <div class="admin-footer">
      <layout-footer/>
    </div>
  </div>
</template>


<script>
  import LayoutHead from './Head.vue'
  import LayoutTabs from './Tabs.vue'
  import LayoutFooter from './Footer.vue'
  import LayoutAside from './Aside.vue'
  import _ from 'lodash'

  export default {
    name: 'Loyout',
    data() {
      return {
        minHeight: document.documentElement.clientHeight - 90
      }
    },
    computed: {
      getMinHeight: function () {
        return this.$root.$data.documentClientHeight - 90
      }
    },
    components: {LayoutAside, LayoutHead, LayoutTabs, LayoutFooter},
    mounted() {
    }
  }
</script>

<style scoped>

</style>
