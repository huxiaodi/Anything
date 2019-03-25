<template>
  <div>
    <div class="header">
      <div class="header-top-box">
        <ul class="header-top">
          <li class="header-name" style="margin:0;">
          </li>
          <li>
            <router-link to="/login">登录</router-link>
          </li>
          <li>
            <router-link to="/register">注册</router-link>
          </li>
          <li>
            {{nowTime}}&nbsp; {{nowWeek}}
          </li>
        </ul>
      </div>
    </div>

  </div>
</template>
<script>
  var moment = require('moment');
  export default {
    name: 'LoginHead',
    data() {
      return {
        nowTime: moment().format('YYYY年M月D日'),
        nowWeek: ''
      }
    },
    methods: {
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
      }
    },
    mounted() {
      this.formatData();
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

  /* 菜单栏 */
  .header-menu{
    width: 1180px;
    margin: 0 auto;
    height: 70px;
    font-size: 0;
    background-color: #fff;
  }
  .menu-logo {
    float: left;
  }
  .menu-logo img{
    height: 45px;
    margin-top: 12px;
  }
  .menu-list{
    float: right;
    text-align: right;
    width: 500px;
    height: 100%;
    line-height: 70px;
  }
  .menu-list a{
    display: inline-block;
    margin-left: 35px;
    font-size: 14px;
    color: #000;
  }
  .menu-list a:hover{
    color: #009eff;
  }
</style>
