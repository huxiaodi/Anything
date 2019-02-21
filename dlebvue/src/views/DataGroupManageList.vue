<template>
  <div>
    <!--查询条件-->
    <el-form :inline="true" :model="searchForm" class="admin-search-form" align="left">
      <el-form-item label="创建时间:">
        <el-col :span="8" style="width: 150px">
          <el-date-picker style="width: 140px" type="date" v-model="searchForm.startTime"
                          format="yyyy-MM-dd" value-format="yyyy-MM-dd" placeholder="选择日期"></el-date-picker>
        </el-col>
        <el-col :span="1" style="width: 25px">至</el-col>
        <el-col :span="8" style="width: 150px">
          <el-date-picker style="width: 140px" type="date" v-model="searchForm.endTime"
                          format="yyyy-MM-dd" value-format="yyyy-MM-dd" placeholder="选择日期"></el-date-picker>
        </el-col>
      </el-form-item>
      <el-form-item label="关键字:">
        <el-tooltip class="item" effect="light" content="组名称" placement="bottom-start">
          <el-input v-model="searchForm.keyword" placeholder="关键字"></el-input>
        </el-tooltip>
      </el-form-item>
      <div class="admin-search-btns">
        <el-button type="primary" icon="el-icon-search" @click="getData" :loading="this.isShowLoadingIcon">查询
        </el-button>
        <el-button type="primary" icon="el-icon-plus" @click="addDataGroup">创建</el-button>
        <el-button type="primary" icon="el-icon-edit" @click="editDataGroup">修改</el-button>
        <el-button type="primary" icon="el-icon-delete" @click="deleteDataGroup">删除</el-button>
      </div>
    </el-form>
    <!--列表-->
    <el-table ref="myTable" stripe border
              :data="tableList"
              :max-height="500"
              :height="400"
              style="width: 100%"
              show-summary
              highlight-current-row
              :summary-method="getSummaries"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="70px" align="center"></el-table-column>
      <el-table-column prop="teamCreateTime" label="创建时间" align="center"></el-table-column>
      <el-table-column prop="teamName" label="组名称" align="center"></el-table-column>
      <el-table-column prop="teamCreateUser" label="创建人" align="center"></el-table-column>
      <el-table-column prop="teamUpdateTime" label="更新时间" align="center"></el-table-column>
      <el-table-column prop="teamUpdateUser" label="更新人" align="center"></el-table-column>
      <el-table-column prop="teamRemark" label="备注" align="center"></el-table-column>
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
  import {pageMixin} from '../common/mixin.js'

  export default {
    // 数据组管理
    name: 'DataGroupManageList',
    mixins: [pageMixin],
    data() {
      return {
        searchForm: {
          keyword: '',
          startTime: '',
          endTime: ''
        },
        tableList: [],
        multipleSelection: [],
        row: []
      }
    },
    methods: {
      // 获取数据
      getData() {
        this.isShowLoadingIcon = true;
        api.post('admin/dataGroupManage/list', this.searchForm).then(response => {
          if (response.data.code === 1) {
            let list = response.data.data.list
            for (var i = 0; i < list.length; i++) {
              if (list[i].teamUpdateUserId === '') {
                list[i].teamUpdateTime = ''
              }
            }
            this.updateTableData(response.data.data)
          }
        }).finally(() => {
          this.isShowLoadingIcon = false;
        })
      },
      // 设置合计
      getSummaries(param) {
        const {columns, data} = param;
        const sums = [];
        columns.forEach((column, index) => {
          if (index === 0) {
            sums[index] = `合计:`;
            return;
          }
          if (index === 1) {
            sums[index] = `${this.tableList.length}`;
            return;
          }
          const values = data.map(item => item[column.property]);
          if (values.every(value => typeof value === 'number')) {
            sums[index] = values.reduce((prev, curr) => {
              if (typeof curr === 'number') {
                return prev + curr;
              } else {
                return prev;
              }
            }, 0);
          }
        });
        return sums;
      },
      // 获取选中的数据
      handleSelectionChange(val) {
        this.row = val;
      },
      // 创建
      addDataGroup(row) {
        localStorage.setItem('DataGroupManageGroupId', '');
        this.$router.push(`/DataGroupManage`)
      },
      // 修改
      editDataGroup(row) {
        if (this.row.length <= 0) {
          Message.MessageError('请选择要修改的单据!')
          return
        }
        if (this.row.length > 1) {
          Message.MessageError('一次只能修改一条单据!')
          return
        }
        localStorage.setItem('DataGroupManageGroupId', this.row[0].teamId);
        this.$router.push(`/DataGroupManage`);
      },
      // 删除
      deleteDataGroup(row) {
        if (this.row.length <= 0) {
          Message.MessageError('请选择要删除的单据!')
        } else {
          let ids = []
          for (var i = 0; i < this.row.length; i++) {
            ids.push(this.row[i].teamId)
          }
          this.$confirm('确定删除该数据组吗？', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
            confirmButtonClass: 'el-button--danger'
          }).then(() => {
            api.post('admin/dataGroupManage/deletes', ids).then(response => {
              if (response.data.code === 1) {
                Message.MessageSuccess(response.data.msg)
                this.getData()
              } else {
                Message.MessageError(response.data.msg)
              }
            })
          }).catch(() => {
          });
        }
      },
      show() {
        var homePage = this.$route.params.homePage;
        if (homePage !== undefined) {
          this.searchForm.status = homePage.status;
          this.searchForm.startTime = homePage.startTime;
          this.searchForm.endTime = homePage.endTime;
        }
      }
    },
    mounted() {
      this.getData()
    }
  }
</script>

<style scoped>
  .borderStyle {
    border-bottom: 1px solid gainsboro
  }
</style>
