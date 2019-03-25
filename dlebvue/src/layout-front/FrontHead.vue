<template>
  <div class="header">
    <div class="header-top-box">
      <ul class="header-top">
        <li class="header-name" style="margin:0;">
          {{userType === 2 ? '发布方' : (userType === 3 ? '接单方' : '未知用户')}}
        </li>
        <li>
          <router-link to="/AccountCentral">
              {{userName}}
          </router-link>
        </li>
        <li>
          <a href="javascript:" @click="signOut">退出</a>
        </li>
        <li>
          {{nowTime}}&nbsp; {{nowWeek}}
        </li>
      </ul>
    </div>
    <el-row class="header-main">
      <el-col :span="4">
      </el-col>
      <el-menu class="el-menu-demo" :default-active="defaultActive" mode="horizontal" router>
        <el-menu-item index="/AccountCentral">我的信息</el-menu-item>
        <el-menu-item v-if="userType === 2" index="/IssueManager">物流发布管理</el-menu-item>
        <el-menu-item v-if="userType === 3" index="/OrderManager">订单管理</el-menu-item>
      </el-menu>
    </el-row>
  </div>
</template>


<script>
  var moment = require('moment');
  import _ from 'lodash'

  export default {
    name: 'FrontHead',
    data() {
      return {
        user: this.$root.$data.user,
        userName: this.$root.$data.user.userName,
        menus: JSON.parse(localStorage.getItem('menus')),
        nowTime: moment().format('YYYY年M月D日'),
        nowWeek: '',
        userType: '',
        defaultActive: ''
      }
    },
    methods: {
      getDefaultActive() {
        let path = this.$route.path
        let result = ''
        let pathList = ['/IssueManager', '/OrderManager', '/AccountCentral']
        _.forEach(pathList, function (value) {
          console.log(value)
          if (_.startsWith(path, value)) {
            result = value
          }
        });
        this.defaultActive = result
      },
      handleSelect(key, keyPath) {
        // console.log(key, keyPath)
      },
      signOut() {
        this.$http.get('/user/loginOut?token=' + sessionStorage.getItem('dleb_token')).then((response) => {
          if (response.data.code === 1) {
            sessionStorage.removeItem('dleb_token');
            sessionStorage.removeItem('dleb_user');
            this.$router.push('/login')
          } else {
            Message.MessageError(response.data.msg)
          }
        });
      },
      formatData() {
        switch (moment().weekday()) {
          case 0:
            this.nowWeek = '星期日';
            break;
          case 1:
            this.nowWeek = '星期一';
            break;
          case 2:
            this.nowWeek = '星期二';
            break;
          case 3:
            this.nowWeek = '星期三';
            break;
          case 4:
            this.nowWeek = '星期四';
            break;
          case 5:
            this.nowWeek = '星期五';
            break;
          case 6:
            this.nowWeek = '星期六';
            break;
        }
        var user = sessionStorage.getItem('dleb_user');
        this.userType = JSON.parse(user).userType;
      }
    },
    created() {
      this.formatData();
      this.getDefaultActive();
      window.sd = this;
    },
    watch: {
      '$route'(to, from) {
        this.getDefaultActive()
      }
    }
  }
</script>

<style scoped>
/* 页眉部分 */
.header {
  width: 100%;
  height: 100px;
  line-height: 30px;
  background-color: #fff;
  font-size: 14px;
  color: #fff;
  position: relative;
  z-index: 5;
}
.header-top-box{
  background-color: #000;
  width: 100%;
  height: 30px;
  line-height: 30px;
}
ul.header-top{
  width: 1180px;
  list-style: none;
  padding: 0;
  text-align: right;
  margin: 0 auto;
  color: #fff;
}
li.header-name {
  float: left;
}
ul.header-top li a {
  text-decoration: none;
  color: inherit;
}
ul.header-top li {
  display: inline-block;
  margin-left: 30px;
}

  /* 导航目录设置 */
.header-main{
  width: 1180px;
  height: 70px;
  margin: 0 auto;
}
.header-main  .el-menu--horizontal > li.el-menu-item  {
    display: inline-block !important;
    float: none;
    font-size: 18px;
    line-height: 80px;
    height: 70px !important;
    font-weight: bold;
    color: #383838;
  }

.header-main  .el-menu--horizontal > li.el-submenu  {
  display: inline-block !important;
  float: none;
  font-size: 18px;
  line-height: 80px;
  height: 70px !important;
  font-weight: bold;
  color: #383838;
}
.header-main .el-submenu > div.el-submenu__title  {
  font-size: 18px;
}
.header-main  .el-menu-item.is-active.el-submenu {
    color: #0099ff !important;
  }

.header-main  .el-menu.el-menu-demo {
    /*text-align: center;*/
    margin-left: 450px;
  }

.header-main  .el-menu.el-menu-demo.el-menu--horizontal {
    height: 70px;;
    border: 0;
  }
.header-main  #logo{
  text-align: left;
}
.header-main  #logo img {
    height: 45px;
    line-height: none;
    margin-top: 12px;
    display: inline;
  }


</style>
