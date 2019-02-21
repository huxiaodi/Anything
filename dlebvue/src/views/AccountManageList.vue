<template>
  <div>
    <el-form :inline="true" :model="searchForm" class="admin-search-form" align="left">
      <el-form-item label="开户时间:">
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
      <el-form-item label="账户状态:">
        <el-select v-model="searchForm.status" placeholder="请选择" clearable filterable>
          <el-option
            v-for="item in optionsStatus"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="账户类型:">
        <el-select v-model="searchForm.type" placeholder="请选择" clearable filterable>
          <el-option
            v-for="item in optionsType"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="账户体系:">
        <el-select v-model="searchForm.accountType" placeholder="请选择" clearable filterable>
          <el-option
            v-for="item in optionsAccountType"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="关键字:">
        <el-tooltip effect="light" content="账户名称、账户编号、账户余额、可提现余额、隶属用户、绑定手机、绑定银行卡数" placement="bottom-start">
          <el-input v-model="searchForm.keyword" placeholder="关键字" clearable></el-input>
        </el-tooltip>
      </el-form-item>
      <div class="admin-search-btns">
        <el-button type="primary" icon="el-icon-search" @click="getData" :loading="this.isShowLoadingIcon">查询
        </el-button>
        <el-button type="primary" icon="el-icon-close" @click="disableAccount">禁用</el-button>
        <el-button type="primary" icon="el-icon-check" @click="enableAccount">启用</el-button>
        <el-button type="primary" icon="el-icon-refresh" @click="refreshList">刷新</el-button>
      </div>
    </el-form>

    <el-table ref="myTable" stripe border
              :data="tableList"
              :max-height="500"
              :height="400"
              style="width: 100%"
              show-summary
              highlight-current-row
              :summary-method="getSummaries"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="70" align="center"></el-table-column>
      <el-table-column prop="account_sub_account_opening_date" label="开户时间" align="center"
                       width="180px"></el-table-column>
      <el-table-column prop="account_sub_name" label="账户名称" align="center" width="200px" show-overflow-tooltip>
        <template slot-scope="scope">
          <a style="cursor:pointer;color: #409EFF;" @click="viewDetails(scope.row)">
            {{scope.row.account_sub_name}}</a>
        </template>
      </el-table-column>
      <el-table-column prop="account_sub_no" label="账户编号" align="center" show-overflow-tooltip
                       width="180px"></el-table-column>
      <el-table-column prop="account_sub_avail_balance" label="账户余额" align="center" width="120px"></el-table-column>
      <el-table-column prop="account_sub_ash_balance" label="可提现余额" align="center" width="120px"></el-table-column>
      <el-table-column prop="account_sub_statusC" label="账户状态" align="center" width="120px"></el-table-column>
      <el-table-column prop="account_sub_relation_id_typeC" label="账户类型" align="center" width="120px"></el-table-column>
      <el-table-column prop="account_typeC" label="账户体系" align="center" width="120px"></el-table-column>
      <el-table-column prop="owner" label="隶属用户" align="center" width="120px" show-overflow-tooltip></el-table-column>
      <el-table-column prop="user_mobile" label="绑定手机" align="center" width="120px"></el-table-column>
      <el-table-column prop="bank_card_cnt" label="账户绑卡数" align="center" width="120px"></el-table-column>
    </el-table>
    <!--禁用的弹出框-->
    <el-dialog
      title="账户禁用"
      :visible.syne="dialogVisible"
      width="30%"
      center :show-close="false">
      <el-form ref="dialogForm" :rules="dialogForm" :model="dialogForm"
               label-width="110px">
        <el-form-item label="禁用原因:" prop="keyword">
          <el-input v-model="dialogForm.keyword" maxlength="100"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="confirmDialogForm('dialogForm')">确定</el-button>
        <el-button @click="closeDialogForm('dialogForm')">取消</el-button>
      </div>
    </el-dialog>
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
  import _ from 'lodash'

  export default {
    // 账户管理
    name: 'AccountManageList',
    mixins: [pageMixin],
    data() {
      return {
        searchForm: {
          keyword: '',
          status: '',
          system: '',
          type: '',
          accountType: '',
          startTime: '',
          endTime: ''
        },
        tableList: [],
        multipleSelection: [],
        optionsStatus: [{
          value: '',
          label: '全部'
        }, {
          value: 1,
          label: '正常'
        }, {
          value: 0,
          label: '禁用'
        }, {
          value: 2,
          label: '异常'
        }],
        optionsType: [{
          value: '',
          label: '全部'
        }, {
          value: 1,
          label: '个人账户'
        }, {
          value: 2,
          label: '企业账户'
        }],
        optionsAccountType: [{
          value: '1',
          label: '平安结算体系'
        }, {
          value: '2',
          label: '工行结算体系'
        }],
        // 控制禁用弹出框显示不显示
        dialogVisible: false,
        dialogForm: {
          keyword: '',
          id: ''
        }
      }
    },
    methods: {
      // 获取数据
      getData() {
        this.isShowLoadingIcon = true;
        api.post('/admin/accountManage/list', this.searchForm).then(response => {
          if (response.data.code === 1) {
            let list = response.data.data.list;
            list.forEach((item) => {
              if (item.account_sub_status === '1') {
                item.account_sub_statusC = '正常';
              } else {
                item.account_sub_statusC = '禁用';
              }
              if (item.account_sub_relation_id_type === '2' && item.company_id === null) {
                item.account_sub_statusC = '异常';
              }
              if (item.account_sub_relation_id_type === '1') {
                item.account_sub_relation_id_typeC = '个人账户';
              } else if (item.account_sub_relation_id_type === '2') {
                item.account_sub_relation_id_typeC = '企业账户';
              }
              if (item.account_type === '1') {
                item.account_typeC = '平安银行';
              }
            })
            this.tableList = list;
            this.pageSettings.total = response.data.data.total;
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
        this.multipleSelection = val;
      },
      // 启用
      enableAccount(row) {
        if (this.multipleSelection.length <= 0) {
          Message.MessageError('请选择要启用的账户!')
          return
        }
        if (this.multipleSelection.length > 1) {
          Message.MessageError('一次只能启用一条账户!')
          return
        }
        for (let i = 0; i < this.multipleSelection.length; i++) {
          let item = this.multipleSelection[i];
          if (item.account_sub_statusC === '正常') {
            Message.MessageError('该账户已启用，请勿重复操作!')
            return
          }
        }
        this.$confirm('确定重新启用该账户吗？', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
          confirmButtonClass: 'el-button--danger'
        }).then(() => {
//            let ids = [];
//            this.multipleSelection.forEach((item, index) => {
//              ids.push(item.user_id);
//            });
          api.post('/admin/accountManage/switch/enable', {'id': this.multipleSelection[0].account_sub_id}).then((response) => {
            if (response.data.code === 1) {
              Message.MessageSuccess('启用成功');
              // 刷新列表数据
              this.getData();
            } else {
              Message.MessageError(response.data.msg);
            }
          });
        }).catch(() => {
        });
      },
      // 禁用
      disableAccount(row) {
        if (this.multipleSelection.length <= 0) {
          Message.MessageError('请选择要禁用的账户!')
          return
        }
        if (this.multipleSelection.length > 1) {
          Message.MessageError('一次只能禁用一条账户!')
          return
        }
        for (let i = 0; i < this.multipleSelection.length; i++) {
          let item = this.multipleSelection[i];
          if (item.account_sub_statusC === '禁用') {
            Message.MessageError('该账户已禁用，请勿重复操作!')
            return
          }
        }
        this.dialogForm.id = this.multipleSelection[0].account_sub_id;
        this.dialogVisible = true
      },
      // 禁用弹框的确认按钮
      confirmDialogForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            api.post('/admin/accountManage/switch/disable', this.dialogForm).then((response) => {
              if (response.data.code === 1) {
                Message.MessageSuccess(response.data.msg);
                // 禁用成功之后关闭弹窗
                this.dialogVisible = false;
                // 重新查询列表
                this.getData();
              } else {
                Message.MessageError(response.data.msg);
              }
            });
          }
        });
      },
      // 禁用弹框的取消按钮
      closeDialogForm(formName) {
        this.dialogVisible = false;
        this.$refs[formName].resetFields();
      },
      // 刷新
      refreshList(row) {
        this.getData();
      },
      // 点击账户名称查看详情页面
      viewDetails(row) {
        this.$router.push(`/AccountDetails/${row.account_sub_id}`)
      }
    },
    mounted() {
      // this.setDate()
      this.getData()
    }
  }
</script>
