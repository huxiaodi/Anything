<template>
  <div>
    <!--查询条件-->
    <el-form :inline="true" :model="searchForm" class="admin-search-form" align="left">
      <el-form-item label="交易时间:">
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
      <el-form-item label="交易对象:">
        <el-select v-model="searchForm.relationId" placeholder="请选择" clearable filterable>
          <el-option
            v-for="item in optionsSubAccount"
            :key="item.accountSubId"
            :label="item.accountSubName"
            :value="item.accountSubId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="手续费类型:">
        <el-select v-model="searchForm.type" placeholder="请选择" clearable filterable>
          <el-option
            v-for="item in optionsType"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="关键字:">
        <el-tooltip class="item" effect="light" content="流水单号、交易金额、手续费、交易对象、子账户账号" placement="bottom-start">
          <el-input v-model="searchForm.keyword" placeholder="关键字" clearable></el-input>
        </el-tooltip>
      </el-form-item>
      <div class="admin-search-btns">
        <el-button type="primary" icon="el-icon-search" @click="getData" :loading="this.isShowLoadingIcon">查询
        </el-button>
        <el-button type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
        <el-button type="primary" icon="el-icon-refresh" @click="refreshList">刷新</el-button>
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
      <el-table-column prop="accountSubTradeTranDate" label="交易时间" align="center" width="180px"></el-table-column>
      <el-table-column prop="accountSubTradeNo" label="流水单号" align="center" width="180px">
        <template slot-scope="scope">
          <a style="cursor:pointer;color: #409EFF;"
             @click="viewTransactionInfo(scope.row)">{{scope.row.accountSubTradeNo}}</a>
        </template>
      </el-table-column>
      <el-table-column prop="accountSubTradeTranAmt" label="交易金额" align="center" width="110px"
                       show-overflow-tooltip></el-table-column>
      <el-table-column prop="accountSubTradeTranFee" label="手续费" align="center" width="180px"
                       show-overflow-tooltip></el-table-column>
      <el-table-column prop="percentage" label="手续费比例" align="center" width="180px"
                       show-overflow-tooltip></el-table-column>
      <el-table-column prop="accountSubTradeTypeC" label="手续费类型" align="center" width="180px"></el-table-column>
      <el-table-column prop="accountSubName" label="交易对象" align="center" width="130px"
                       show-overflow-tooltip></el-table-column>
      <el-table-column prop="accountSubNo" label="子账户账号" align="center" width="180px"></el-table-column>
      <el-table-column prop="accountSubTradeTranStatusC" label="交易状态" align="center" width="110px"
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
  import * as Message from '../utils/messageUtil'
  import {pageMixin} from '../common/mixin.js'
  import {exportModel} from '../common/export'
  import {parseTime} from '../utils/index'

  export default {
    // 手续费账户
    name: 'FeeAccountList',
    mixins: [pageMixin, exportModel],
    data() {
      return {
        searchForm: {
          keyword: '',
          relationId: '',
          type: '',
          startTime: '',
          endTime: ''
        },
        tableList: [],
        multipleSelection: [],
        // 1-会员交易，2-转账，3-充值，4-提现
        optionsType: [{
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
        }],
        optionsSubAccount: []
      }
    },
    methods: {
      // 获取数据
      getData() {
        this.isShowLoadingIcon = true;
        api.post('/admin/tradeManage/getServiceChargeList', this.searchForm).then(response => {
          if (response.data.code === 1) {
            let list = response.data.data.list;
            list.forEach((item) => {
              // 手续费类型1-会员交易，2-转账，3-充值，4-提现，5-服务费，6-退款，7-挂账
              if (item.accountSubTradeType === 1) {
                item.accountSubTradeTypeC = '会员交易';
              } else if (item.accountSubTradeType === 2) {
                item.accountSubTradeTypeC = '转账';
              } else if (item.accountSubTradeType === 3) {
                item.accountSubTradeTypeC = '充值';
              } else if (item.accountSubTradeType === 4) {
                item.accountSubTradeTypeC = '提现';
              } else if (item.accountSubTradeType === 5) {
                item.accountSubTradeTypeC = '服务费';
              } else if (item.accountSubTradeType === 6) {
                item.accountSubTradeTypeC = '退款';
              } else if (item.accountSubTradeType === 7) {
                item.accountSubTradeTypeC = '挂账';
              }
              // 交易状态，（0:成功，1:失败，2:待确认, 5:待处理，6:处理中,7: 待挂账，8:挂账成功，9:挂账失败）
              if (item.accountSubTradeTranStatus === 0) {
                item.accountSubTradeTranStatusC = '成功';
              } else if (item.accountSubTradeTranStatus === 1) {
                item.accountSubTradeTranStatusC = '失败';
              } else if (item.accountSubTradeTranStatus === 2) {
                item.accountSubTradeTranStatusC = '待确认';
              } else if (item.accountSubTradeTranStatus === 5) {
                item.accountSubTradeTranStatusC = '待处理';
              } else if (item.accountSubTradeTranStatus === 6) {
                item.accountSubTradeTranStatusC = '处理中';
              } else if (item.accountSubTradeTranStatus === 7) {
                item.accountSubTradeTranStatusC = '待挂账';
              } else if (item.accountSubTradeTranStatus === 8) {
                item.accountSubTradeTranStatusC = '挂账成功';
              } else if (item.accountSubTradeTranStatus === 9) {
                item.accountSubTradeTranStatusC = '挂账失败';
              }
            })
            this.tableList = list;
            this.pageSettings.total = response.data.data.total;
          }
        }).finally(() => {
          this.isShowLoadingIcon = false;
        })
      },
      // 获取查询条件中的子账户名称
      getSubAccount() {
        api.get('/admin/tradeManage/getServiceChargeAccountName').then(response => {
          if (response.data.code === 1) {
            this.optionsSubAccount = response.data.data;
          } else {
            Message.MessageError(response.data.msg)
          }
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
      // 刷新
      refreshList() {
        this.getData();
      },
      // 导出
      handleDownload() {
        if (this.tableList.length <= 0) {
          Message.MessageInfo('列表暂无数据!')
          return
        }
        this.downloadLoading = true
        require.ensure([], () => {
          const {export_json_to_excel} = require('vendor/Export2Excel')
          for (let i = 0; i < this.tableList.length; i++) {
            this.tableList[i].index = i + 1;
          }
          const data = this.formatJson(this.FeeAccountListRow, this.tableList)
          export_json_to_excel(this.FeeAccountListHeader, data, '手续费账户')
          this.downloadLoading = false
        })
      },
      // 列表数据转换为Json格式
      formatJson(filterVal, jsonData) {
        return jsonData.map(v => filterVal.map(j => {
          if (j === 'timestamp') {
            return parseTime(v[j]);
          } else {
            return v[j]
          }
        }))
      },
      // 点击交易单号（查看该交易的交易详情）
      viewTransactionInfo(row) {
        this.$router.push(`/TransactionInfo/${row.accountSubTradeId}`)
        // this.$router.push({name: '交易管理详情', params: row})
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
      // this.setDate();
      // this.show()
      this.getSubAccount();
      this.getData();
    }
  }
</script>

<style scoped>
  .borderStyle {
    border-bottom: 1px solid gainsboro
  }
</style>
