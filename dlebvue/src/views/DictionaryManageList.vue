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
      <el-form-item label="字典类型:">
        <el-select class="el-select" v-model="searchForm.type" placeholder="系统参数">
          <el-option
            v-for="item in optionsDictionaryStatus"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="关键字:">
        <el-tooltip effect="light" placement="bottom-start">
          <el-input placeholder="关键字" v-model="searchForm.keyword"></el-input>
        </el-tooltip>
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
              :max-height="500"
              :height="400"
              show-summary
              highlight-current-row
              :summary-method="getSummaries"
              @selection-change="handleSelectionChange"
              style="width: 100%">
      <el-table-column type="selection" align="center" width="80%"></el-table-column>
      <el-table-column prop="dictKey" sortable align="center" width="160px" label="字典名称"></el-table-column>
      <el-table-column prop="dictValue" sortable align="center" width="170px" label="字典值"></el-table-column>
      <el-table-column prop="dictDesc" align="center" width="120px" label="描述" show-overflow-tooltip></el-table-column>
      <el-table-column prop="dictType" align="center" width="120px" label="字典类型"></el-table-column>
      <el-table-column prop="dictCreateTime" align="center" width="180px" label="创建时间"></el-table-column>
      <el-table-column prop="dictCreateUserId" align="center" width="150px" label="创建人"
                       show-overflow-tooltip></el-table-column>
      <el-table-column prop="dictUpdateTime" align="center" width="180px" label="修改时间"></el-table-column>
      <el-table-column prop="dictUpdateUserId" align="center" width="120px" label="修改人"
                       show-overflow-tooltip></el-table-column>
    </el-table>
    <!--字典管理新增修改-->
    <el-dialog
      :title="titleType=='add'?'新增字典':'修改字典'"
      :visible.syne="dialogVisible"
      width="40%"
      center :show-close="false">
      <el-form ref="dialogForm" :rules="dialogFormRules" :model="dialogForm"
               label-width="120px" labelPosition="right">
        <el-form-item label="字典名称:" prop="dictKey">
          <el-input type="text" v-model="dialogForm.dictKey" clearable></el-input>
        </el-form-item>
        <el-form-item label="字典值:" prop="dictValue" style="margin-top: 16px">
          <el-input type="text" v-model="dialogForm.dictValue" clearable></el-input>
        </el-form-item>
        <el-form-item label="字典类型:" prop="dictType" style="margin-top: 16px">
          <el-select class="el-select" v-model="dialogForm.dictType" placeholder="系统参数" clearable filterable>
            <el-option
              v-for="item in optionsDictionaryStatus"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="描述:" prop="dictDesc" style="margin-top: 16px">
          <el-input type="textarea" v-model="dialogForm.dictDesc" clearable></el-input>
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
    name: 'DictionaryManageList',
    mixins: [pageMixin, exportModel],
    data() {
      return {
        searchForm: {
          dictionaryStatus: '',
          keyword: ''
        },
        optionsDictionaryStatus: [{
          value: '系统参数',
          label: '系统参数'
        }, {
          value: '短信字典',
          label: '短信字典'
        }, {
          value: '费率字典',
          label: '费率字典'
        }, {
          value: '提现额度字典',
          label: '提现额度字典'
        }, {
          value: '结算类型字典',
          label: '结算类型字典'
        }, {
          value: '证件类型字典',
          label: '证件类型字典'
        }, {
          value: '会员注册来源',
          label: '会员注册来源'
        }, {
          value: '业务来源',
          label: '业务来源'
        }, {
          value: '退款额度字典',
          label: '退款额度字典'
        }, {
          value: '付款申请额度字典',
          label: '付款申请额度字典'
        }, {
          value: '业务类型',
          label: '业务类型'
        }],
        tableList: [],
        titleType: 'add',
        dialogVisible: false,
        dialogForm: {},
        dialogFormRules: {
          dictKey: [
            {required: true, message: '请输入字典名称', trigger: 'blur'}
          ],
          dictValue: [
            {required: true, message: '请输入字典值', trigger: 'blur'}
          ],
          dictType: [
            {required: true, message: '请输入字典类型', trigger: 'change'}
          ]
        },
        row: []
      }
    },
    methods: {
      // 获取数据
      getData() {
        this.isShowLoadingIcon = true;
        api.post('admin/dictionaryManage/list', this.searchForm).then(response => {
          if (response.data.code === 1) {
            let list = response.data.data.list
            for (var i = 0; i < list.length; i++) {
              if (list[i].dictUpdateUserId === '') {
                list[i].dictUpdateTime = ''
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
      // 创建
      addData() {
        this.titleType = 'add';
        this.dialogVisible = true;
        this.dialogForm = {
          dictKey: '',
          dictValue: '',
          dictType: '',
          dictDesc: ''
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
            ids.push(this.row[i].dictId)
          }
          this.$confirm('确定删除该数据吗？', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
            confirmButtonClass: 'el-button--danger'
          }).then(() => {
            api.post('admin/dictionaryManage/deletes', ids).then(response => {
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
      // 列表合计
      getSummaries(param) {
        const {columns, data} = param
        const sums = []
        columns.forEach((column, index) => {
          if (index === 0) {
            sums[index] = `合计:`
            return
          }
          if (index === 1) {
            sums[index] = `${this.tableList.length}`
            return
          }
          const values = data.map(item => item[column.property])
          if (values.every(value => typeof value === 'number')) {
            sums[index] = values.reduce((prev, curr) => {
              if (typeof curr === 'number') {
                return prev + curr
              } else {
                return prev
              }
            }, 0)
          }
        })
        return sums
      },
      // 确定
      confirmDialogForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            if (this.dialogForm.dictType === '退款额度字典') {
              if ( !/^[1-9]{1}[0-9]*$/.test(this.dialogForm.dictValue)) {
                Message.MessageError('请输入正确格式的退款额度');
                return;
              }
            }
            api.post('admin/dictionaryManage/save', this.dialogForm).then(response => {
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
