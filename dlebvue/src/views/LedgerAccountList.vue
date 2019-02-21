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
      <el-form-item label="充值状态状态:">
        <el-select  v-model="searchForm.tranStatus" placeholder="请选择" clearable filterable>
          <el-option
            v-for="item in optionsStatus"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="充值渠道:">
        <el-select v-model="searchForm.type" placeholder="请选择" clearable filterable>
          <el-option
            v-for="item in optionsType"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="子账户名称:">
        <el-select  v-model="searchForm.accountSubId" placeholder="请选择" clearable filterable>
          <el-option
            v-for="item in optionsSubAccount"
            :key="item.accountSubId"
            :label="item.accountSubName"
            :value="item.accountSubId">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="关键字:">
        <el-tooltip class="item" effect="light" content="交易单号、充值金额、充值银行名称、账户编号、调账人" placement="bottom-start">
          <el-input v-model="searchForm.keyword" placeholder="关键字" clearable></el-input>
        </el-tooltip>
      </el-form-item>
      <div class="admin-search-btns">
        <el-button type="primary" icon="el-icon-search" @click="getData" :loading="this.isShowLoadingIcon">查询
        </el-button>
        <el-button type="primary" icon="el-icon-edit" @click="accountAdjus">调账</el-button>
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
      <el-table-column prop="accountSubTradeTranDate" label="充值时间" align="center" width="180px"></el-table-column>
      <el-table-column prop="accountSubTradeNo" label="交易单号" align="center" width="180px">
        <template slot-scope="scope">
          <a style="cursor:pointer;color: #409EFF;"
             @click="viewTransactionInfo(scope.row)">{{scope.row.accountSubTradeNo}}</a>
        </template>
      </el-table-column>
      <el-table-column prop="accountSubTradeTypeC" label="充值渠道" align="center"
                       width="120px"></el-table-column>
      <el-table-column prop="accountSubTradeTranAmt" label="充值金额" align="center" width="110px"
                       show-overflow-tooltip></el-table-column>
      <el-table-column prop="accountSubTradeBankName" label="充值银行名称" align="center" width="180px"
                       show-overflow-tooltip></el-table-column>
      <el-table-column prop="accountSubTradeBankNum" label="充值银行卡号" align="center" width="180px"
                       show-overflow-tooltip></el-table-column>
      <el-table-column prop="accountSubTradeBankAccount" label="充值账户名称" align="center" width="180px"
                       show-overflow-tooltip></el-table-column>
      <el-table-column prop="accountSubName" label="子账户名称" align="center" width="180px"
                       show-overflow-tooltip></el-table-column>
      <el-table-column prop="accountSubNo" label="账户编号" align="center" width="180px"></el-table-column>
      <el-table-column prop="accountSubTradeTranStatusC" label="充值状态" align="center" width="130px"
                       show-overflow-tooltip></el-table-column>
      <el-table-column prop="accountSubTradeBankNo" label="调账流水" align="center" width="180px">
        <template slot-scope="scope">
          <a style="cursor:pointer;color: #409EFF;"
             @click="viewAdjustnfo(scope.row)">{{scope.row.accountSubTradeBankNo}}</a>
        </template>
      </el-table-column>
      <el-table-column prop="accountSubTradeAdjustTime" label="调账时间" align="center" width="180px"></el-table-column>
      <el-table-column prop="accountSubTradeAdjustUserName" label="调账人" align="center" width="110px"
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
    // 挂账账户
    name: 'LedgerAccountList',
    mixins: [pageMixin, exportModel],
    data() {
      return {
        searchForm: {
          keyword: '',
          tranStatus: '',
          accountSubId: '',
          type: '',
          startTime: '',
          endTime: ''
        },
        tableList: [],
        isDisable: false,
        multipleSelection: [],
        optionsStatus: [{
          value: '',
          label: '全部'
        }, {
          value: 0,
          label: '充值成功'
        }, {
          value: 7,
          label: '待挂账'
        }, {
          value: 8,
          label: '挂账成功'
        }, {
          value: 9,
          label: '挂账失败'
        }],
        optionsType: [{
          value: '',
          label: '全部'
        }, {
          value: 3,
          label: '会员充值'
        }, {
          value: 7,
          label: '资金挂账'
        }],
        optionsSubAccount: [],
        isAdjus: false
      }
    },
    methods: {
      // 获取数据
      getData() {
        this.isShowLoadingIcon = true;
        api.post('/masterAccount/getHangingAccountList', this.searchForm).then(response => {
          if (response.data.code === 1) {
            let list = response.data.data.list;
            list.forEach((item) => {
              // 充值渠道，3-会员充值  7-资金挂账
              if (item.accountSubTradeType === 3) {
                item.accountSubTradeTypeC = '会员充值';
              } else if (item.accountSubTradeType === 7) {
                item.accountSubTradeTypeC = '资金挂账';
              }
              // 充值状态 7: 待挂账，8:挂账成功，9:挂账失败）
              if (item.accountSubTradeTranStatus === 0) {
                item.accountSubTradeTranStatusC = '充值成功';
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
        api.get('/masterAccount/getHangingAccountSub').then(response => {
          if (response.data.code === 1) {
            this.optionsSubAccount = response.data.data;
            this.optionsSubAccount.unshift({accountSubId:'',accountSubName:'全部'})
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
      // 调账
      accountAdjus() {
        if (this.multipleSelection.length <= 0) {
          Message.MessageError('请选择要调账的数据!')
          return
        }
        if (this.multipleSelection.length > 1) {
          Message.MessageError('一次只能调账一条数据!')
          return
        }
        for (let i = 0; i < this.multipleSelection.length; i++) {
          let item = this.multipleSelection[i];
          if (item.accountSubTradeType !== 7) {
            Message.MessageError('请选择资金挂账的数据进行调账!')
            return
          }
          // 判断选择的数据是否调过账
          this.checkAccountSubTrade(item);
        }
      },
      // 检查是否可调账
      checkAccountSubTrade(item) {
        api.get('/masterAccount/checkAccountSubTrade', {
          params: {
            accountSubTradeId: item.accountSubTradeId
          }
        }).then(response => {
          if (response.data.code === -1) {
            Message.MessageError(response.data.msg);
            this.isAdjus = false;
          } else {
            this.isAdjus = true;
          }
          if (this.isAdjus === false) {
            return;
          }
          this.$router.push({name: '子账户调账', params: this.multipleSelection[0]})
        })
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
          const data = this.formatJson(this.LedgerAccountListRow, this.tableList)
          export_json_to_excel(this.LedgerAccountListHeader, data, '挂账账户')
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
        this.$router.push(`/LedgerAccountDetails/${row.accountSubTradeId}`)
        // this.$router.push({name: '交易管理详情', params: row})
      },
      // 点击调账流水（查看该流水的详情）
      viewAdjustnfo(row) {
        this.$router.push(`/LedgerAccountDetails/${row.accountSubTradeId}`)
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
