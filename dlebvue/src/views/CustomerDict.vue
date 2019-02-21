<template>
  <div>
    <!--查询条件-->
    <el-form :inline="true" :model="searchForm" class="admin-search-form" align="left">
      <el-form-item label="创建时间:">
        <el-col :span="8" style="width: 150px">
          <el-date-picker style="width: 140px" type="date" v-model="searchForm.startTime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="选择日期"></el-date-picker>
        </el-col>
        <el-col :span="1" style="width: 25px">至</el-col>
        <el-col :span="8" style="width: 150px">
          <el-date-picker style="width: 140px" type="date" v-model="searchForm.endTime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="选择日期"></el-date-picker>
        </el-col>
      </el-form-item>
      <div class="admin-search-btns">
        <el-button type="primary" icon="el-icon-search" @click="getData('searchForm')"
                   :loading="this.isShowLoadingIcon">查询
        </el-button>
        <el-button type="primary" icon="el-icon-plus" @click="addData">创建</el-button>
        <el-button type="primary" icon="el-icon-edit" @click="editData">修改</el-button>
        <el-button type="primary" icon="el-icon-delete" @click="deleteData">删除</el-button>
      </div>
    </el-form>
    <!--列表-->
    <el-table ref="myTable" stripe border
              :data="tableList"
              :max-height="545"
              highlight-current-row

              @selection-change="handleSelectionChange"
              style="width: 100%">
      <el-table-column type="selection" align="center" width="70%"></el-table-column>
      <el-table-column prop="customerCode" sortable align="center" width="110px" label="客户代码"></el-table-column>
      <el-table-column prop="customerName" sortable align="center" width="230px" label="客户名称"></el-table-column>
      <el-table-column prop="createTime" align="center" width="180px" label="创建时间"></el-table-column>
      <el-table-column prop="createUserId" align="center" width="120px" label="创建人" show-overflow-tooltip></el-table-column>
      <el-table-column prop="updateTime" align="center" width="180px" label="修改时间"></el-table-column>
      <el-table-column prop="updateUserId" align="center" width="120px" label="修改人" show-overflow-tooltip></el-table-column>
    </el-table>
    <!--客户字典管理新增修改-->
    <el-dialog
      :title="titleType=='add'?'新增客户字典':'修改客户字典'"
      :visible.syne="dialogVisible"
      width="40%"
      center :show-close="false">
      <el-form ref="dialogForm" :rules="dialogFormRules" :model="dialogForm"
               label-width="120px" labelPosition="right">
        <el-form-item label="客户代码:" prop="customerCode">
          <el-input type="text" v-model="dialogForm.customerCode" clearable
                                v-on:change="checkKey(dialogForm.customerCode)"
                                :disabled="titleType == 'edit'"></el-input>
          <el-col  style="color:red" v-if="dataExist">数据已存在!</el-col>
        </el-form-item>
        <el-form-item label="客户名称:" prop="customerName" style="margin-top: 16px">
          <el-input type="text" v-model="dialogForm.customerName" clearable></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="confirmDialogForm('dialogForm')">确定</el-button>
        <el-button @click="closeDialogForm('dialogForm')">取消</el-button>
      </div>
    </el-dialog>
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
  import {parseTime} from '../utils/index'
  import * as Message from '../utils/messageUtil'
  import {pageMixin} from '../common/mixin'
  import {exportModel} from '../common/export'
  import _ from 'lodash'

  export default {
    // 字典管理
    name: 'CustomerDictList',
    mixins: [pageMixin, exportModel],
    data() {
      return {
        searchForm: {
          keyword: ''
        },
        tableList: [],
        titleType: 'add',
        dialogVisible: false,
        dataExist: false,
        dialogForm: {},
        dialogFormRules: {
          customerCode: [
            {required: true, message: '请输入客户代码', trigger: 'blur' }
          ],
          customerName: [
            {required: true, message: '请输入客户名称', trigger: 'blur'}
          ]
        },
        row: []
      }
    },
    methods: {
      // 获取数据
      getData() {
        this.isShowLoadingIcon = true;
        api.post('customerDict/list', this.searchForm).then(response => {
          if (response.data.code === 1) {
            let list = response.data.data.list
            for (var i = 0; i < list.length; i++) {
              if (list[i].updateUserId === '') {
                list[i].updateTime = ''
              }
            }
            this.updateTableData(response.data.data)
          }
        }).finally(() => {
          this.isShowLoadingIcon = false;
        })
      },
      // 获取当前行数据
      handleSelectionChange(val) {
        this.row = val;
      },
      //检查主键
      checkKey(keyValue) {
        api.get('customerDict/checkKey/' + keyValue).then(response => {
          if (response.data.code === 1) {
            this.dataExist = response.data.data;
          }else {
            Message.MessageError(response.data.msg);
          }
        })
      },
      // 创建
      addData() {
        this.titleType = 'add';
        this.dialogVisible = true;
        this.dialogForm = {
          customerCode: '',
          customerName: ''
        };
      },
      // 修改
      editData() {
        if (this.row.length <= 0) {
          Message.MessageInfo('请选择要修改的单据!')
          return
        }
        if (this.row.length > 1) {
          Message.MessageInfo('一次只能修改一条单据!')
          return
        }
        this.dialogVisible = true;
        this.dialogForm = _.cloneDeep(this.row[0]);
        this.titleType = 'edit';
      },
      // 删除
      deleteData() {
        if (this.row == null || this.row.length <= 0) {
          Message.MessageInfo('请选择要删除的单据!')
        } else {
          let ids = []
          for (var i = 0; i < this.row.length; i++) {
            ids.push(this.row[i].customerId)
          }
          this.$confirm('确定删除该数据吗？', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
            confirmButtonClass: 'el-button--danger'
          }).then(() => {
            api.post('customerDict/deletes', ids).then(response => {
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

      // 确定
      confirmDialogForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            api.post('customerDict/save', this.dialogForm).then(response => {
              if (response.data.code === 1) {
                Message.MessageSuccess(response.data.msg);
                this.dialogVisible = false;
                this.getData()
              } else {
                Message.MessageError(response.data.msg)
              }
            })
          } else {
            return false;
          }
        });
      },
      // 取消
      closeDialogForm(formName) {
        this.dialogVisible = false;
        this.$refs[formName].resetFields();
      }


     },

    mounted() {
      this.getData();
    }
  }
</script>

<style scoped>

</style>
