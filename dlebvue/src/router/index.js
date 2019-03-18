import Vue from 'vue'
import Router from 'vue-router'
// 管理端页面导入
import Loyout from '@/layout/Loyout'
import NotFound from '@/views/404'
import Home from '@/views/Home'
import AdminLogin from '@/views/AdminLogin'
import CompanyBindCard from '@/views/CompanyBindCard'
import PersonalBindCard from '@/views/PersonalBindCard'
import PersonalMemberList from '@/views/PersonalMemberList'
import MemberInfoEdit from '@/views/MemberInfoEdit'
import TransactionManageList from '@/views/TransactionManageList'
import TransactionInfo from '@/views/TransactionInfo'
import DictionaryManageList from '@/views/DictionaryManageList'
import UserMemberList from '@/views/UserMemberList'
import UserCreate from '@/views/UserCreate'
import ReturnShortMessage from '@/views/ReturnShortMessage'
import ReturnMoney from '@/views/ReturnMoney'
import BusinessMemberList from '@/views/BusinessMemberList'
import BusinessMemberEdit from '@/views/BusinessMemberEdit'
import BusinessMemberDetails from '@/views/BusinessMemberDetails'
import BusinessManageList from '@/views/BusinessManageList'
import AccountManageList from '@/views/AccountManageList'
import AccountDetails from '@/views/AccountDetails'
import DataGroupManageList from '@/views/DataGroupManageList'
import DataGroupManage from '@/views/DataGroupManage'
import RoleManageList from '@/views/RoleManageList'
import RoleAddEdit from '@/views/RoleAddEdit'
import PersonalCenter from '@/views/PersonalCenter'
import PasswordSetting from '@/views/PasswordSetting'
import PasswordEdit from '@/views/PasswordEdit'
import LedgerAccountList from '@/views/LedgerAccountList'
import LedgerAccountAdjust from '@/views/LedgerAccountAdjust'
import LedgerAccountDetails from '@/views/LedgerAccountDetails'
import FeeAccountList from '@/views/FeeAccountList'
import Resource from '@/views/Resource'
import AccountManagement from '@/views/AccountManagement'
import MemberLog from '@/views/MemberLog'
import UserMemberLog from '@/views/UserMemberLog'
import InterestDict from '@/views/InterestDict'
import InterestManager from '@/views/InterestManager'
import KindDict from '@/views/KindDict'
import CustomerDict from '@/views/CustomerDict'
import FreeDayDict from '@/views/FreeDayDict'
import InvoiceManage from '@/views/InvoiceManage'
import InvoiceManageDetail from '@/views/InvoiceManageDetail'
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
  {path: '/companyBindCard', component: CompanyBindCard, name: '企业绑定银行卡', meta: {keepAlive: true, cname: CompanyBindCard.name, show: false}},
  {path: '/personalBindCard', component: PersonalBindCard, name: '个人绑定银行卡', meta: {keepAlive: true, cname: PersonalBindCard.name, show: false}},
  {path: '/personalMemberList', component: PersonalMemberList, name: '个人会员管理', meta: {keepAlive: true, cname: PersonalMemberList.name, show: false}},
  {path: '/MemberInfoEdit/:id', component: MemberInfoEdit, name: '个人会员修改', meta: {keepAlive: true, cname: MemberInfoEdit.name, show: false}},
  {path: '/returnShortMessage', component: ReturnShortMessage, name: '回填短信验证码', meta: {keepAlive: true, cname: ReturnShortMessage.name, show: false}},
  {path: '/returnMoney', component: ReturnMoney, name: '回填小额鉴权金额', meta: {keepAlive: true, cname: ReturnMoney.name, show: false}},
  {path: '/BusinessMemberList', component: BusinessMemberList, name: '企业会员管理', meta: {keepAlive: true, cname: BusinessMemberList.name, show: false}},
  {path: '/BusinessMemberEdit', component: BusinessMemberEdit, name: '企业会员修改', meta: {keepAlive: true, cname: BusinessMemberEdit.name, show: false}},
  {path: '/BusinessMemberDetails', component: BusinessMemberDetails, name: '企业会员详情', meta: {keepAlive: true, cname: BusinessMemberDetails.name, show: false}},
  {path: '/TransactionManageList', component: TransactionManageList, name: '交易管理', meta: {keepAlive: true, cname: TransactionManageList.name, show: false}},
  {path: '/TransactionInfo/:id', component: TransactionInfo, name: '交易管理详情', meta: {keepAlive: true, cname: TransactionInfo.name, show: false}},
  {path: '/BusinessManageList', component: BusinessManageList, name: '业务管理', meta: {keepAlive: true, cname: BusinessManageList.name, show: false}},
  {path: '/AccountManageList', component: AccountManageList, name: '账户管理', meta: {keepAlive: true, cname: AccountManageList.name, show: false}},
  {path: '/AccountDetails/:id', component: AccountDetails, name: '账户详情', meta: {keepAlive: true, cname: AccountDetails.name, show: false}},
  {path: '/DataGroupManageList', component: DataGroupManageList, name: '数据组管理', meta: {keepAlive: true, cname: DataGroupManageList.name, show: false}},
  {path: '/DataGroupManage', component: DataGroupManage, name: '创建组', meta: {keepAlive: true, cname: DataGroupManage.name, show: false}},
  {path: '/RoleManageList', component: RoleManageList, name: '角色管理', meta: {keepAlive: true, cname: RoleManageList.name, show: false}},
  {path: '/RoleAddEdit', component: RoleAddEdit, name: '新增角色', meta: {keepAlive: true, cname: RoleAddEdit.name, show: false}},
  {path: '/UserMemberList', component: UserMemberList, name: '用户管理', meta: {keepAlive: true, cname: UserMemberList.name, show: false}},
  {path: '/UserCreate', component: UserCreate, name: '创建用户', meta: {keepAlive: true, cname: UserCreate.name, show: false}},
  {path: '/DictionaryManageList', component: DictionaryManageList, name: '字典管理', meta: {keepAlive: true, cname: DictionaryManageList.name, show: false}},
  {path: '/PersonalCenter', component: PersonalCenter, name: '个人中心', meta: {keepAlive: true, cname: PersonalCenter.name, show: false}},
  {path: '/PasswordSetting', component: PasswordSetting, name: '密码设置', meta: {keepAlive: true, cname: PasswordSetting.name, show: false}},
  {path: '/PasswordEdit', component: PasswordEdit, name: '密码修改', meta: {keepAlive: true, cname: PasswordEdit.name, show: false}},
  {path: '/LedgerAccountList', component: LedgerAccountList, name: '挂账账户', meta: {keepAlive: true, cname: LedgerAccountList.name, show: false}},
  {path: '/LedgerAccountAdjust', component: LedgerAccountAdjust, name: '子账户调账', meta: {keepAlive: true, cname: LedgerAccountAdjust.name, show: false}},
  {path: '/LedgerAccountDetails/:id', component: LedgerAccountDetails, name: '挂账详情', meta: {keepAlive: true, cname: LedgerAccountDetails.name, show: false}},
  {path: '/FeeAccountList', component: FeeAccountList, name: '手续费账户', meta: {keepAlive: true, cname: FeeAccountList.name, show: false}},
  {path: '/Resource', component: Resource, name: '资源管理', meta: {keepAlive: true, cname: Resource.name, show: false}},
  {path: '/AccountManagement', component: AccountManagement, name: '总账户管理', meta: {keepAlive: true, cname: AccountManagement.name, show: false}},
  {path: '/MemberLog', component: MemberLog, name: '会员登录日志', meta: {keepAlive: true, cname: MemberLog.name, show: false}},
  {path: '/UserMemberLog', component: UserMemberLog, name: '系统用户登录日志', meta: {keepAlive: true, cname: UserMemberLog.name, show: false}},
  {path: '/InvoiceManage', component: InvoiceManage, name: '发票管理', meta: {keepAlive: true, cname: InvoiceManage.name, show: true}},
  {path: '/InterestDict' , component: InterestDict, name: '利率字典', meta: {keepAlive: true, cname: InterestDict.name, show:false}},
  {path: '/KindDict' , component: KindDict, name: '品种字典', meta: {keepAlive: true, cname: KindDict.name, show:false}},
  {path: '/CustomerDict' , component: CustomerDict, name: '客户字典', meta: {keepAlive: true, cname: CustomerDict.name, show:false}},
  {path: '/FreeDayDict' , component: FreeDayDict, name: '免息天数字典', meta: {keepAlive: true, cname: FreeDayDict.name, show:false}},
  {path: '/InterestManager' , component: InterestManager, name: '贴息计算', meta: {keepAlive: true, cname: InterestManager.name, show:false}},
  {path: '/InvoiceManageDetail/:id', component: InvoiceManageDetail, name: '发票管理详情', meta: {keepAlive: true, cname: InvoiceManageDetail.name, show: true}}

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
