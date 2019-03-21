<template>
  <div>
    <!--查询条件-->
    <el-form :inline="true" :model="searchForm" class="admin-search-form" align="left">
      <el-form-item label="交易时间:">
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
      <el-form-item label="交易状态:">
        <el-select class="el-select" v-model="searchForm.status" placeholder="全部" clearable filterable>
          <el-option
            v-for="item in optionsTransactionStatus"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="交易类型:">
        <el-select class="el-select" v-model="searchForm.tradeType" placeholder="全部" clearable filterable>
          <el-option
            v-for="item in optionsTransactionType"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="账户体系:">
        <el-select class="el-select" v-model="searchForm.accountType" placeholder="全部" clearable filterable>
          <el-option
            v-for="item in optionsAccountSystem"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="子账户名称:">
        <el-select class="el-select" v-model="searchForm.subaccountName" placeholder="全部" clearable filterable>
          <el-option
            v-for="item in optionsSubaccountName"
            :key="item.account_sub_trade_take_cash_account_name"
            :label="item.account_sub_trade_take_cash_account_name"
            :value="item.account_sub_trade_take_cash_account_name">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="关键字:">
        <el-tooltip effect="light" content="关键字支持交易单号、交易金额，账户编号、交易对象、订单号、订单内容查询" placement="bottom-start">
          <el-input v-model="searchForm.keyword" placeholder="关键字" clearable></el-input>
        </el-tooltip>
      </el-form-item>
      <div class="admin-search-btns">
        <el-button type="primary" icon="el-icon-search" @click="getData('searchForm')"
                   :loading="this.isShowLoadingIcon">查询
        </el-button>
        <el-button type="primary" icon="el-icon-download" @click="exportList">导出</el-button>
        <el-button type="primary" icon="el-icon-delete" @click="refreshData">刷新</el-button>
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
      <el-table-column prop="account_sub_trade_tran_date" align="center" width="180%"
                       label="交易时间"></el-table-column>
      <el-table-column prop="account_sub_trade_no" align="center" width="180%" label="流水单号">
        <template slot-scope="scope">
          <a style="cursor:pointer;color: #409EFF" @click="viewDetails(scope.row)">
            {{scope.row.account_sub_trade_no}}</a>
        </template>
      </el-table-column>
      <el-table-column prop="accountType" align="center" width="120%" label="账户体系"></el-table-column>
      <el-table-column prop="type" align="center" width="120%" label="交易类型"></el-table-column>
      <el-table-column prop="account_sub_trade_tran_amt" align="center" width="120%" label="交易金额"></el-table-column>
      <el-table-column prop="account_sub_name" align="center" width="180%"
                       label="子账户名称" show-overflow-tooltip></el-table-column>
      <el-table-column prop="account_sub_no" align="center" width="180%"
                       label="账户编号" show-overflow-tooltip></el-table-column>
      <el-table-column prop="account_sub_name_to" align="center" width="180%" label="交易对象"
                       show-overflow-tooltip></el-table-column>
      <el-table-column prop="status" align="center" width="150%" label="交易状态"></el-table-column>
      <el-table-column prop="business_trade_business_no" align="center" width="150%" label="订单号"
                       show-overflow-tooltip></el-table-column>
      <el-table-column prop="business_trade_business_content" align="center" width="150%"
                       label="订单内容"></el-table-column>
      <el-table-column prop="account_sub_trade_remark" align="center" width="180%" label="备注"
                       show-overflow-tooltip></el-table-column>
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
  import {parseTime} from '../utils/index'
  import * as Message from '../utils/messageUtil'
  import {pageMixin} from '../common/mixin'
  import {exportModel} from '../common/export'

  export default {
    // 交易管理
    name: 'transactionManageList',
    mixins: [pageMixin, exportModel],
    data() {
      return {
        searchForm: {
          status: '',
          accountType: '',
          tradeType: '',
          subaccountName: '',
          keyword: ''
        },
        optionsTransactionStatus: [{
          value: '',
          label: '全部'
        }, {
          value: '0',
          label: '成功'
        }, {
          value: '1',
          label: '失败'
        }, {
          value: '2',
          label: '待确认'
        }, {
          value: '5',
          label: '待处理'
        }, {
          value: '6',
          label: '处理中'
        }, {
          value: '7',
          label: '待调账'
        }, {
          value: '8',
          label: '调账成功'
        }, {
          value: '9',
          label: '调账失败'
        }],
        // 流水类型，1-会员交易，2-转账，3-充值，4-提现，5-服务费，6-退款，7-挂账
        optionsTransactionType: [{
          value: '',
          label: '全部'
        }, {
          value: '1',
          label: '会员交易'
        }, {
          value: '2',
          label: '转账'
        }, {
          value: '3',
          label: '充值'
        }, {
          value: '4',
          label: '提现'
        }, {
          value: '5',
          label: '服务费'
        }, {
          value: '6',
          label: '退款'
        }, {
          value: '7',
          label: '挂账'
        }],
        optionsAccountSystem: [{
          value: '1',
          label: '平安银行'
        }, {
          value: '2',
          label: '工商银行'
        }],
        optionsSubaccountName: [],
        tableList: [],
        dialogVisible: false
      }
    },
    methods: {
      // 获取数据
      getData() {
        this.isShowLoadingIcon = true;
        api.post('admin/tradeManage/listAdmin', this.searchForm).then(response => {
          if (response.data.code === 1) {
            let list = response.data.data.list;
            list.forEach((item) => {
              if (item.account_type === '1') {
                item.accountType = '平安银行'
              }
              // 交易状态，（0:成功，1:失败，2:待确认, 5:待处理，6:处理中,7: 待挂账，8:挂账成功，9:挂账失败）
              let objStatus = {0: '成功', 1: '失败', 2: '待确认', 5: '待处理', 6: '处理中', 7: '待调账', 8: '调账成功', 9: '调账失败'}
              item.status = objStatus[item.account_sub_trade_tran_status] || item.account_sub_trade_tran_status;
              // 流水类型，1-会员交易，2-转账，3-充值，4-提现，5-服务费，6-退款，7-挂账
              let objType = {'1': '会员交易', '2': '转账', '3': '充值', '4': '提现', '5': '服务费', '6': '退款', '7': '挂账'}
              item.type = objType[item.account_sub_trade_type] || item.account_sub_trade_type;
              item.account_sub_trade_tran_payment_type = 1;
            })
            this.tableList = list;
            this.pageSettings.total = response.data.data.total;
          }
        }).finally(() => {
          this.isShowLoadingIcon = false;
        })
      },
      // 获取查询条件中的子账户名称
      getQueryInfo() {
//        api.get('/masterAccount/getHangingAccountSub').then(response => {
//          if (response.data.code === 1) {
//            this.optionsSubaccountName = response.data.data;
//          } else {
//            Message.MessageError(response.data.msg)
//          }
//        })
//        api.get('/dictManager/getBusinesSourceDict').then(response => {
//          if (response.data.code === 1) {
//            this.optionsSource = response.data.data;
//          } else {
//            Message.MessageError(response.data.msg)
//          }
//        });
      },
      // 获取当前行数据
      handleSelectionChange(val) {
        this.row = val
      },
      // 刷新
      refreshData() {
        this.getData()
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
      // 导出按钮
      exportList() {
        this.downloadLoading = true
        require.ensure([], () => {
          const {export_json_to_excel} = require('vendor/Export2Excel')
          const data = this.formatJson(this.tradeManageListRow, this.tableList)
          export_json_to_excel(this.tradeManageListListHeader, data, '交易管理')
          this.downloadLoading = false
        })
      },
      formatJson(filterVal, jsonData) {
        return jsonData.map(v => filterVal.map(j => {
          if (j === 'timestamp') {
            return parseTime(v[j]);
          } else {
            return v[j]
          }
        }))
      },
      // 点击交易单号查看交易详情页面
      viewDetails(row) {
        this.$router.push(`/TransactionInfo/${row.account_sub_trade_id}`)
        // this.$router.push({name: '交易管理详情', params: row})
      }
    },
    mounted() {
      this.getQueryInfo();
      this.getData()
    }
  }
</script>

<style>

</style>
