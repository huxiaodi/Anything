<template>
  <el-menu
    default-active="2"
    class="el-menu-vertical-demo"
    @open="handleOpen"
    @close="handleClose"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b" router>
    <el-submenu index="1">
      <template slot="title">
        <i class="el-icon-menu"></i>
        <span>会员管理</span>
      </template>
      <el-menu-item-group>
        <el-menu-item index="/personalMemberList">个人会员管理</el-menu-item>
        <el-menu-item index="/BusinessMemberList">企业会员管理</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
    <el-menu-item index="/AccountManageList">
      <i class="el-icon-menu"></i>
      <span slot="title">账户管理</span>
    </el-menu-item>
    <el-menu-item index="/TransactionManageList">
      <i class="el-icon-menu"></i>
      <span slot="title">交易管理</span>
    </el-menu-item>
    <el-menu-item index="/BusinessManageList">
      <i class="el-icon-menu"></i>
      <span slot="title">业务管理</span>
    </el-menu-item>
    <el-submenu index="2">
      <template slot="title">
        <i class="el-icon-menu"></i>
        <span slot="title">总账户管理</span>
      </template>
      <el-menu-item-group>
        <el-menu-item index="/LedgerAccountList">挂账账户</el-menu-item>
        <el-menu-item index="/FeeAccountList">手续费账户</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
    <el-submenu index="3">
      <template slot="title">
        <i class="el-icon-setting"></i>
        <span slot="title">系统设置</span>
      </template>
      <el-menu-item-group>
        <el-menu-item index="/DataGroupManageList">数据组管理</el-menu-item>
        <el-menu-item index="/RoleManageList">角色管理</el-menu-item>
        <el-menu-item index="/UserMemberList">用户管理</el-menu-item>
        <el-menu-item index="/Resource">资源管理</el-menu-item>
        <el-menu-item index="/PersonalCenter">个人中心</el-menu-item>
        <el-menu-item index="/InvoiceManage">发票管理</el-menu-item>
        <el-submenu index="5">
          <template slot="title">密码设置</template>
          <el-menu-item index="/PasswordSetting">密码设置</el-menu-item>
          <el-menu-item index="/PasswordEdit">密码修改</el-menu-item>
        </el-submenu>
      </el-menu-item-group>
      <el-menu-item index="/DictionaryManageList">字典管理</el-menu-item>
    </el-submenu>
  </el-menu>
</template>


<script>
  export default {
    name: 'Aside',
    data() {
      return {
        menus: []
      }
    },
    methods: {
      handleOpen(key, keyPath) {
//        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
//        console.log(key, keyPath);
      },
      handleSelect(key, keyPath) {
        // console.log(key, keyPath)
      }
    },
    created() {
    }
  }
</script>

<style scoped>

  .pull-right {
    float: right;
  }


</style>
