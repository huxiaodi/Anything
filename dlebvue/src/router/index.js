import Vue from 'vue'
import Router from 'vue-router'
// 管理端页面导入
import Loyout from '@/layout/Loyout'
import NotFound from '@/views/404'
import Home from '@/views/Home'
import AdminLogin from '@/views/AdminLogin'
import NoticeManageList from '@/views/NoticeManageList'
import UserMemberList from '@/views/UserMemberList'
import UserCreate from '@/views/UserCreate'
import BusinessMemberList from '@/views/BusinessMemberList'
import BusinessMemberEdit from '@/views/BusinessMemberEdit'
import BusinessMemberDetails from '@/views/BusinessMemberDetails'
import OrderManageList from '@/views/OrderManageList'
import orderDetail from '@/views/orderDetail'
import PersonalCenter from '@/views/PersonalCenter'
import PasswordSetting from '@/views/PasswordSetting'
import PasswordEdit from '@/views/PasswordEdit'
// 用户端页面导入
import Login from '@/views-front/Login'
import FrontLoyout from '@/layout-front/FrontLoyout'
import AccountCentral from '@/views-front/AccountCentral'
import ChangePassword from '@/views-front/ChangePassword'
import IssueGoods from '@/views-front/IssueGoods'
import IssueGoodsDetail from '@/views-front/IssueGoodsDetail'
import IssueGoodsModify from '@/views-front/IssueGoodsModify'
import IssueManager from '@/views-front/IssueManager'
import OrderManager from '@/views-front/OrderManager'
import Register from '@/views-front/Register'


import _ from "lodash";

Vue.use(Router)

export const FrontViews = [
  {path: '/AccountCentral', component: AccountCentral, name: '账户中心', meta: {keepAlive: true, cname: AccountCentral.name}},
  {path: '/IssueGoods', component: IssueGoods, name: '发布', meta: {keepAlive: true, cname: IssueGoods.name}},
  {path: '/IssueGoodsDetail/:id', component: IssueGoodsDetail, name: '发布详情', meta: {keepAlive: true, cname: IssueGoodsDetail.name}},
  {path: '/IssueGoodsModify/:id', component: IssueGoodsModify, name: '发布修改', meta: {keepAlive: true, cname: IssueGoodsModify.name}},
  {path: '/IssueManager', component: IssueManager, name: '发布管理', meta: {keepAlive: true, cname: IssueManager.name}},
  {path: '/OrderManager', component: OrderManager, name: '订单管理', meta: {keepAlive: true, cname: OrderManager.name}},
  {path: '/ChangePassword/:mobile', component: ChangePassword, name: '修改登录密码', meta: {keepAlive: true, cname: ChangePassword.name}},
]

export const AdminViews = [
  {path: '/home', component: Home, name: '首页', meta: {keepAlive: true, cname: Home.name}},
  {path: '/BusinessMemberList', component: BusinessMemberList, name: '会员管理', meta: {keepAlive: true, cname: BusinessMemberList.name, show: false}},
  {path: '/BusinessMemberEdit', component: BusinessMemberEdit, name: '会员修改', meta: {keepAlive: true, cname: BusinessMemberEdit.name, show: false}},
  {path: '/BusinessMemberDetails', component: BusinessMemberDetails, name: '会员详情', meta: {keepAlive: true, cname: BusinessMemberDetails.name, show: false}},
  {path: '/NoticeManageList', component: NoticeManageList, name: '公告管理', meta: {keepAlive: true, cname: NoticeManageList.name, show: false}},
  {path: '/OrderManageList', component: OrderManageList, name: '物流管理', meta: {keepAlive: true, cname: OrderManageList.name, show: false}},
  {path: '/orderDetail/:id', component: orderDetail, name: '物流详情', meta: {keepAlive: true, cname: orderDetail.name, show: false}},
  {path: '/UserMemberList', component: UserMemberList, name: '用户管理', meta: {keepAlive: true, cname: UserMemberList.name, show: false}},
  {path: '/UserCreate', component: UserCreate, name: '创建用户', meta: {keepAlive: true, cname: UserCreate.name, show: false}},
  {path: '/PersonalCenter', component: PersonalCenter, name: '个人中心', meta: {keepAlive: true, cname: PersonalCenter.name, show: false}},
  {path: '/PasswordSetting', component: PasswordSetting, name: '密码设置', meta: {keepAlive: true, cname: PasswordSetting.name, show: false}},
  {path: '/PasswordEdit', component: PasswordEdit, name: '密码修改', meta: {keepAlive: true, cname: PasswordEdit.name, show: false}}
]

const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'FrontLoyout',
      component: FrontLoyout,
      redirect: '/AccountCentral',
      children: FrontViews
    },
    {
      path: '/admin',
      name: 'Loyout',
      component: Loyout,
      redirect: '/home',
      children: AdminViews
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/AdminLogin',
      name: 'AdminLogin',
      component: AdminLogin
    },
    {
      path: '/Register',
      name: 'Register',
      component: Register
    },
    {
      path: '/404',
      name: 'NotFound',
      component: NotFound
    },
    {
      path: '*',
      redirect: '/404'
    }
  ]
})

export function isFrontPath(path) {
  let isFront = true
  _.forEach(AdminViews, (item) => {
    if (_.startsWith(path, item.path)) {
      isFront = false
      return false
    }
  })
  return isFront
}

export function getLoginPath(path) {
  return isFrontPath(path) ? '/login' : '/AdminLogin'
}


export default router
