<template>
  <div>
    <el-tabs class="admin-tabs" type="card" :value="nowTab" @tab-click="tabClick" @tab-remove="tabRemove">
      <el-tab-pane
        v-for="(item, index) in pageTabs"
        :key="item.name"
        :name="item.name"
        :closable="item.name!=='首页'"
      >
        <span slot="label"><i v-if="$route.meta.cname===item.meta.cname" class="el-icon-refresh"
                              @click="refreshMe(item.meta.cname)"></i> {{item.name}}</span>
      </el-tab-pane>
    </el-tabs>
    <section class="admin-main" style="min-height: 100px;">
      <transition v-if="isTransition" name="rvtabs" mode="out-in">
        <keep-alive :include="keepAliveInclude" :exclude="keepAliveExclude">
          <router-view v-if="routerViewRefresh"/>
        </keep-alive>
      </transition>
      <keep-alive v-else :include="keepAliveInclude" :exclude="keepAliveExclude">
        <router-view v-if="routerViewRefresh" :key="$route.name" @childCloseRouter="tabRemove" @childRefreshRouter="refreshMe"/>
      </keep-alive>
    </section>
  </div>

</template>

<script>
  import _ from 'lodash'

  export default {
    name: 'Tabs',
    data() {
      return {
        isTransition: false,
        nowTab: '',
        pageTabs: [],
        keepAliveInclude: [],
        keepAliveExclude: '',
        routerViewRefresh: true
      }
    },
    methods: {
      tabClick(tab) {
        let tmp = _.find(this.pageTabs, {'name': tab.name})
        this.$router.push({name: tmp.name, params: tmp.params})
      },
      refreshMe(cname) {
        console.log('refresh me' + cname)
        this.routerViewRefresh = false
        this.keepAliveExclude = cname
        this.$nextTick(() => {
          this.routerViewRefresh = true
          this.keepAliveExclude = ''
        })
      },
      tabAdd(route) {
        if (route.path === '/login') {
          return;
        }
        this.nowTab = route.name
        let tmpIndex = _.findIndex(this.pageTabs, {'name': route.name})
        if (tmpIndex > -1) {
          // tab中已存在，如果参数不同，刷新页面
          let tmp = this.pageTabs[tmpIndex]
          if (_.isEqual(route.name, tmp.name) && !_.isEqual(route.params, tmp.params)) {
            this.pageTabs[tmpIndex] = _.clone(route)
            this.refreshMe(this.$route.meta.cname)
          }
          if (route.name === '创建组') {
            this.pageTabs[tmpIndex] = _.clone(route)
            this.refreshMe(this.$route.meta.cname)
          }
        } else {
          // tab中不存在
          this.pageTabs.push(route)
          this.updateKeepAliveInclude()
        }
      },
      tabRemove(name) {
        console.log('tabRemove' + name)
        let i = _.findIndex(this.pageTabs, ['name', name])
        this.pageTabs.splice(i, 1)
        let tmp = this.pageTabs[i === this.pageTabs.length ? i - 1 : i]
        this.$router.push({name: tmp.name, params: tmp.params})
        this.updateKeepAliveInclude()
      },
      updateKeepAliveInclude() {
        let tmpArr = [];
        _.forEach(this.pageTabs, function (value) {
          tmpArr.push(value.meta.cname)
        })
        this.keepAliveInclude = tmpArr
      }
    },
    watch: {
      '$route'(to, from) {
        this.tabAdd(to)
      }
    },
    created() {
      let nowRoute = this.$route
      if (nowRoute.name !== '首页') {
        this.pageTabs.push({path: '/home', name: '首页', meta: {keepAlive: true, cname: 'Home'}})
      }
      this.tabAdd(nowRoute)
    }
  }
</script>

<style>

</style>
