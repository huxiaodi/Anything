<template>
  <div class="aaa">
    <!--查询条件-->
    <el-form :inline="true" :model="searchForm" class="admin-search-form" align="left">
      <el-form-item label="创建时间:">
        <el-col :span="8" style="width: 150px">
          <el-date-picker style="width: 140px" type="date" v-model="searchForm.startTime" id="startTime"
                          format="yyyy-MM-dd" value-format="yyyy-MM-dd" placeholder="选择日期"></el-date-picker>
        </el-col>
        <el-col :span="1" style="width: 25px">至</el-col>
        <el-col :span="8" style="width: 150px">
          <el-date-picker style="width: 140px" type="date" v-model="searchForm.endTime" id="endTime"
                          format="yyyy-MM-dd" value-format="yyyy-MM-dd" placeholder="选择日期"></el-date-picker>
        </el-col>
      </el-form-item>
      <el-form-item label="用户类型:">
        <el-select v-model="searchForm.userType" placeholder="请选择">
          <el-option
            v-for="item in optionsUserStatus"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="关键字:">
        <el-tooltip class="item" effect="light" content="关键字查询支持用户名" placement="bottom-start">
          <el-input placeholder="关键字" v-model="searchForm.keyword"></el-input>
        </el-tooltip>
      </el-form-item>
      <div class="admin-search-btns">
        <el-button type="primary" icon="el-icon-search" @click="getData()" :loading="this.isShowLoadingIcon">查询</el-button>
        <el-button type="primary" icon="el-icon-refresh" @click="refreshList">刷新</el-button>
      </div>
    </el-form>

    <!--列表-->
    <el-table ref="myTable" stripe border
              :data="tableList"
              :max-height="500"
              :height="400"
              style="width: 100%"
              highlight-current-row
              @selection-change="handleSelectionChange">

      <el-table-column prop="loginDate" label="登录时间" align="center" width="170px"
                       type="date" value-format="yyyy-MM-dd HH:mm:ss"
      ></el-table-column>
      <el-table-column prop="userName" label="用户名" align="center"></el-table-column>
      <el-table-column prop="userCnName" label="真实姓名" align="center"></el-table-column>
      <el-table-column prop="userType" :formatter="formatType" label="用户类型" align="center"></el-table-column>
      <el-table-column prop="loginIp" label="客户端ip" align="center"></el-table-column>
      <el-table-column prop="loginMode" label="登录方式" align="center"></el-table-column>
      <el-table-column prop="loginDevice" label="登录设备" align="center"></el-table-column>

    </el-table>
    <!--分页-->
    <el-pagination
      :total="pageSettings.total"
      :current-page="pageSettings.pageNum"
      :page-size="pageSettings.pageSize"
      :page-sizes="pageSettings.pageSizes"
      @size-change="sizeChange"
      @current-change="currentChange"
      :layout="pageSettings.layout">
    </el-pagination>
  </div>
</template>

<script>
  import api from '../api/fetch'
  import * as Message from '../utils/messageUtil'
  import {pageMixin} from '../common/mixin'

  export default {
    // 用户管理
    name: 'MemberLog',
    mixins: [pageMixin],
    data() {
      return {
        searchForm: {  },
        isReadOnly: false,
        tableList: [],
        optionsUserStatus: [{
          value: undefined,
          label: '全部'
        }, {
          value: 1,
          label: '个人'
        }, {
          value: 2,
          label: '企业'
        }],
        dialogVisibleDisable: false,
        dialogVisibleEnable: false,
        dialogVisibleAdd: false,
        multipleSelection: [],
        roleSelection: [],
        teamSelection: []

      }
    },
    methods: {
      // 获取数据
      getData() {
        api.post('admin/userLog/getLogList', this.searchForm).then(response => {
          if (response.data.code === 1) {
            this.updateTableData(response.data.data)
          } else {
            Message.MessageError('列表获取失败')
          }
        }).finally(() => {
          this.isShowLoadingIcon = false;
        })
      },
      // 转换会员状态
      formatType(row, column) {
          var str = "";
          if(row.userType == "1") {str= "个人";}
          if(row.userType == "2") {str= "企业";}
        return str;
      },

      // 获取选中的数据
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      refreshList() {
        this.getData();
      }
    },
    mounted() {
      this.getData();
    }
  }
</script>

<style scoped>

</style>
