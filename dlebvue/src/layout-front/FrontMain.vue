<template>
  <div>
    <section class="app-main" style="min-height: 100px;">
      <transition v-if="isTransition" name="rvtabs" mode="out-in">
        <keep-alive :include="keepAliveInclude" :exclude="keepAliveExclude">
          <router-view v-if="routerViewRefresh"/>
        </keep-alive>
      </transition>
      <keep-alive v-else :include="keepAliveInclude" :exclude="keepAliveExclude">
        <router-view v-if="routerViewRefresh" :key="$route.name"/>
      </keep-alive>
    </section>
  </div>
</template>

<script>
  import _ from 'lodash'

  export default {
    name: 'FrontMain',
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

<style scoped>
  .el-tabs__item .el-icon-refresh:hover {
    background-color: #c0c4cc;
    color: #fff;
  }

  .rvtabs-enter-active,
  .rvtabs-leave-active {
    transition: all 0.1s linear;
  }

  .rvtabs-enter,
  .rvtabs-leave-active {
    opacity: 0;
  }

  .el-tabs__header {
    padding: 0;
    position: relative;
    margin: 4px 0 0px 0px;
  }

  .el-tabs__item {
    padding: 0 15px;
    height: 35px;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    line-height: 35px;
    display: inline-block;
    list-style: none;
    font-size: 14px;
    font-weight: 500;
    color: #24292e;
    position: relative;
  }

  .el-tabs--card > .el-tabs__header .el-tabs__item.is-active.is-closable {
    padding-left: 10px;
    padding-right: 10px;
    border-top: 3px solid #FBBC05;
  }
</style>
