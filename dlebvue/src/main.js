// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import axios from './api/fetch'
import VueAxios from 'vue-axios'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
// import '@/assets/scss/element-variables.scss'
import '@/assets/css/app.css'
// import '@/assets/css/front-loyout.css'
import '@/assets/css/front-loyout-new.css'
import '@/assets/css/admin.css'
import App from './App.vue'
import router, {getLoginPath} from './router'
import _ from 'lodash'
import ElTreeGrid from 'element-tree-grid'
// import common from '@/common/common'
// mock.bootstrap()

// Vue.use(common);
Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.use(VueAxios, axios)
Vue.component(ElTreeGrid.name, ElTreeGrid);
Vue.prototype._btn = function (value) {
  if (this.$root.$data.user.userType !== 1){
    const button = JSON.parse(localStorage.getItem('buttonRight'));
    for (let currentValue of button) {
      if (currentValue === value) {
        return true;
      }
    }
    return false;
  } else {
    return true;
  }
  // return true;
};
let paths = ['/login', '/404', '/Register', '/AdminLogin', '/PostTest', '/ServiceAgreement', '/Payment', '/orderPay']

/* eslint-disable no-new */
const vue = new Vue({
  router,
  render: h => h(App),
  data() {
    return {
      defaultActive: '',
      user: {},
      documentClientHeight: document.documentElement.clientHeight
    };
  },
  methods: {
    judgeLogin() {
      let token = sessionStorage.getItem('dleb_token')
      if (!token) {
        let pathname = location.pathname
        if (_.indexOf(paths, pathname) === -1) {
          location.href = getLoginPath(pathname)
        }
      }
    }
  },
  created() {
    this.judgeLogin();

    let user = JSON.parse(sessionStorage.getItem('dleb_user'))
    this.user = user
    window.onresize = _.debounce(() => {
      this.documentClientHeight = document.documentElement.clientHeight
    }, 100)
  }
}).$mount('#app')

router.beforeEach((to, from, next) => {
  if (_.indexOf(paths, to.path) === -1) {
    vue.judgeLogin();
  }
  next()
})
