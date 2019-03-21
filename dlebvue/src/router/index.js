import Vue from 'vue'
import Router from 'vue-router'
// 管理端页面导入
import Loyout from '@/layout/Loyout'
import NotFound from '@/views/404'
import Home from '@/views/Home'
import AdminLogin from '@/views/AdminLogin'
import AnnouncementManageList from '@/views/AnnouncementManageList'
import TransactionInfo from '@/views/TransactionInfo'
import UserMemberList from '@/views/UserMemberList'
import UserCreate from '@/views/UserCreate'
import BusinessMemberList from '@/views/BusinessMemberList'
import BusinessMemberEdit from '@/views/BusinessMemberEdit'
import BusinessMemberDetails from '@/views/BusinessMemberDetails'
import OrderManageList from '@/views/OrderManageList'
import AccountDetails from '@/views/AccountDetails'
import RoleManageList from '@/views/RoleManageList'
import RoleAddEdit from '@/views/RoleAddEdit'
import PersonalCenter from '@/views/PersonalCenter'
import PasswordSetting from '@/views/PasswordSetting'
import PasswordEdit from '@/views/PasswordEdit'
import Resource from '@/views/Resource'
import MemberLog from '@/views/MemberLog'
import UserMemberLog from '@/views/UserMemberLog'
// 用户端页面导入
import Login from '@/views-front/Login'
import FrontLoyout from '@/layout-front/FrontLoyout'
import AccountCentral from '@/views-front/AccountCentral'
import ChangePassword from '@/views-front/ChangePassword'
import ChangeMobile from '@/views-front/ChangeMobile'
import IssueGoods from '@/views-front/IssueGoods'
import IssueGoodsDetail from '@/views-front/IssueGoodsDetail'
import IssueGoodsModify from '@/views-front/IssueGoodsModify'
import IssueManager from '@/views-front/IssueManager'
import OrderManager from '@/views-front/OrderManager'
import ChangeCompanyInformation from '@/views-front/ChangeCompanyInformation'
import ChangeCompanyInformationThree from '@/views-front/ChangeCompanyInformationThree'
import ChangePersonalInformation from '@/views-front/ChangePersonalInformation'
import Register from '@/views-front/Register'
import roleManageListFront from '@/views-front/RoleManageListFront'
import UserMemberListFont from '@/views-front/UserMemberListFont'


import _ from "lodash";

Vue.use(Router)

export const FrontViews = [
  {path: '/AccountCentral', component: AccountCentral, name: '账户中心', meta: {keepAlive: true, cname: AccountCentral.name}},
  {path: '/IssueGoods', component: IssueGoods, name: '发布', meta: {keepAlive: true, cname: IssueGoods.name}},
  {path: '/IssueGoodsDetail/:id', component: IssueGoodsDetail, name: '发布详情', meta: {keepAlive: true, cname: IssueGoodsDetail.name}},
  {path: '/IssueGoodsModify/:id', component: IssueGoodsModify, name: '发布修改', meta: {keepAlive: true, cname: IssueGoodsModify.name}},
  {path: '/ChangeCompanyInformation', component: ChangeCompanyInformation, name: '修改企业信息', meta: {keepAlive: true, cname: ChangeCompanyInformation.name}},
  {path: '/ChangeCompanyInformationThree', component: ChangeCompanyInformationThree, name: '修改三方企业信息', meta: {keepAlive: true, cname: ChangeCompanyInformationThree.name}},
  {path: '/ChangePersonalInformation', component: ChangePersonalInformation, name: '修改个人信息', meta: {keepAlive: true, cname: ChangePersonalInformation.name}},
  {path: '/IssueManager', component: IssueManager, name: '发布管理', meta: {keepAlive: true, cname: IssueManager.name}},
  {path: '/OrderManager', component: OrderManager, name: '订单管理', meta: {keepAlive: true, cname: OrderManager.name}},
  {path: '/ChangePassword/:mobile', component: ChangePassword, name: '修改登录密码', meta: {keepAlive: true, cname: ChangePassword.name}},
  {path: '/ChangeMobile/:mobile', component: ChangeMobile, name: '更换联系人', meta: {keepAlive: true, cname: ChangeMobile.name}},
  {path: '/RoleManageListFront', component: roleManageListFront, name: '前台角色管理', meta: {keepAlive: true, cname: roleManageListFront.name}},
  {path: '/UserMemberListFont', component: UserMemberListFont, name: '用户管理前台', meta: {keepAlive: true, cname: UserMemberList.name, show: false}},
]

export const AdminViews = [
  {path: '/home', component: Home, name: '首页', meta: {keepAlive: true, cname: Home.name}},
  {path: '/BusinessMemberList', component: BusinessMemberList, name: '会员管理', meta: {keepAlive: true, cname: BusinessMemberList.name, show: false}},
  {path: '/BusinessMemberEdit', component: BusinessMemberEdit, name: '会员修改', meta: {keepAlive: true, cname: BusinessMemberEdit.name, show: false}},
  {path: '/BusinessMemberDetails', component: BusinessMemberDetails, name: '会员详情', meta: {keepAlive: true, cname: BusinessMemberDetails.name, show: false}},
  {path: '/AnnouncementManageList', component: AnnouncementManageList, name: '公告管理', meta: {keepAlive: true, cname: AnnouncementManageList.name, show: false}},
  {path: '/TransactionInfo/:id', component: TransactionInfo, name: '公告详情', meta: {keepAlive: true, cname: TransactionInfo.name, show: false}},
  {path: '/OrderManageList', component: OrderManageList, name: '物流管理', meta: {keepAlive: true, cname: OrderManageList.name, show: false}},
  {path: '/AccountDetails/:id', component: AccountDetails, name: '物流详情', meta: {keepAlive: true, cname: AccountDetails.name, show: false}},
  {path: '/RoleManageList', component: RoleManageList, name: '角色管理', meta: {keepAlive: true, cname: RoleManageList.name, show: false}},
  {path: '/RoleAddEdit', component: RoleAddEdit, name: '新增角色', meta: {keepAlive: true, cname: RoleAddEdit.name, show: false}},
  {path: '/UserMemberList', component: UserMemberList, name: '用户管理', meta: {keepAlive: true, cname: UserMemberList.name, show: false}},
  {path: '/UserCreate', component: UserCreate, name: '创建用户', meta: {keepAlive: true, cname: UserCreate.name, show: false}},
  {path: '/PersonalCenter', component: PersonalCenter, name: '个人中心', meta: {keepAlive: true, cname: PersonalCenter.name, show: false}},
  {path: '/PasswordSetting', component: PasswordSetting, name: '密码设置', meta: {keepAlive: true, cname: PasswordSetting.name, show: false}},
  {path: '/PasswordEdit', component: PasswordEdit, name: '密码修改', meta: {keepAlive: true, cname: PasswordEdit.name, show: false}},
  {path: '/Resource', component: Resource, name: '资源管理', meta: {keepAlive: true, cname: Resource.name, show: false}},
  {path: '/MemberLog', component: MemberLog, name: '会员登录日志', meta: {keepAlive: true, cname: MemberLog.name, show: false}},
  {path: '/UserMemberLog', component: UserMemberLog, name: '系统用户登录日志', meta: {keepAlive: true, cname: UserMemberLog.name, show: false}}
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
