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
      <el-form-item label="交易状态:">
        <el-select  v-model="searchForm.status" placeholder="请选择" clearable filterable>
          <el-option
            v-for="item in optionsStatus"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="业务类型:">
        <el-select  v-model="searchForm.businessType" placeholder="请选择" clearable filterable>
          <el-option
            v-for="item in optionsBusinessType"
            :key="item.dictKey"
            :label="item.dictValue"
            :value="item.dictKey">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="业务来源:">
        <el-select  v-model="searchForm.source" placeholder="请选择" clearable filterable>
          <el-option
            v-for="item in optionsSource"
            :key="item.dictKey"
            :label="item.dictValue"
            :value="item.dictKey">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="关键字:">
        <el-tooltip class="item" effect="light" content="交易单号、业务金额，交易主体、交易对象、订单号、订单内容" placement="bottom-start">
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
      <el-table-column prop="business_trade_create_time" label="交易时间" align="center" width="180px"></el-table-column>
      <el-table-column prop="account_sub_trade_no" label="交易单号" align="center" width="200px">
        <template slot-scope="scope">
          <a style="cursor:pointer;color: #409EFF;"
             @click="viewTransactionInfo(scope.row)">{{scope.row.account_sub_trade_no}}</a>
        </template>
      </el-table-column>
      <el-table-column prop="account_sub_trade_tran_payment_typeC" label="交易类型" align="center"
                       width="120px"></el-table-column>
      <el-table-column prop="account_sub_trade_tran_amt" label="业务金额" align="center" width="110px"
                       show-overflow-tooltip></el-table-column>
      <el-table-column prop="business_trade_business_fromC" label="业务来源" align="center" width="150px"></el-table-column>
      <el-table-column prop="business_trade_business_typeC" label="业务类型" align="center" width="130px"></el-table-column>
      <el-table-column prop="account_sub_name" label="交易主体" align="center" width="130px"
                       show-overflow-tooltip></el-table-column>
      <el-table-column prop="account_sub_name_to" label="交易对象" align="center" width="180px"
                       show-overflow-tooltip></el-table-column>
      <el-table-column prop="business_trade_statusC" label="交易状态" align="center" width="110px"></el-table-column>
      <el-table-column prop="business_trade_business_no" label="订单号" align="center" width="180px"
                       show-overflow-tooltip></el-table-column>
      <el-table-column prop="business_trade_business_content" label="订单内容" align="center"
                       width="180px" show-overflow-tooltip></el-table-column>
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
    // 业务管理
    name: 'BusinessManageList',
    mixins: [pageMixin, exportModel],
    data() {
      return {
        searchForm: {
          keyword: '',
          status: '',
          source: '',
          businessType: '',
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
          value: '1',
          label: '创建'
        }, {
          value: '2',
          label: '交易中'
        }, {
          value: '3',
          label: '交易成功'
        }, {
          value: '4',
          label: '交易失败'
        }, {
          value: '5',
          label: '交易撤销'
        }],
        optionsBusinessType: [],
        optionsSource: []
      }
    },
    methods: {
      // 获取数据
      getData() {
        this.isShowLoadingIcon = true;
        api.post('admin/businessManage/listAdmin', this.searchForm).then(response => {
          if (response.data.code === 1) {
            let list = response.data.data.list;
            list.forEach((item) => {
              // 状态，1-创建，2-交易中，3-交易成功，4-交易失败，5-交易撤销
              if (item.business_trade_status === '1') {
                item.business_trade_statusC = '创建';
              } else if (item.business_trade_status === '2') {
                item.business_trade_statusC = '交易中';
              } else if (item.business_trade_status === '3') {
                item.business_trade_statusC = '交易成功';
              } else if (item.business_trade_status === '4') {
                item.business_trade_statusC = '交易失败';
              } else if (item.business_trade_status === '5') {
                item.business_trade_statusC = '交易撤销';
              }
              // 交易类型 1:转出 2:转入
              if (item.account_sub_trade_tran_payment_type === '1') {
                item.account_sub_trade_tran_payment_typeC = '支出';
              } else if (item.account_sub_trade_tran_payment_type === '2') {
                item.account_sub_trade_tran_payment_typeC = '收入';
              }
              // 业务类型
              if (item.business_trade_business_type === '1') {
                item.business_trade_business_typeC = '运输';
              } else if (item.business_trade_business_type === '2') {
                item.business_trade_business_typeC = '加工';
              } else if (item.business_trade_business_type === '3') {
                item.business_trade_business_typeC = '采购';
              } else if (item.business_trade_business_type === '4') {
                item.business_trade_business_typeC = '仓储';
              }
              // 业务来源
              if (item.business_trade_business_from === '2') {
                item.business_trade_business_fromC = '德邻玛特';
              } else if (item.business_trade_business_from === '3') {
                item.business_trade_business_fromC = '德邻云嘉';
              } else if (item.business_trade_business_from === '1') {
                item.business_trade_business_fromC = '德邻畅途';
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
      getQueryInfo() {
        // 业务来源
        api.get('/dictManager/getBusinesTradeTypeDict').then(response => {
          if (response.data.code === 1) {
            this.optionsSource = response.data.data;
          } else {
            Message.MessageError(response.data.msg)
          }
        });
        // 业务类型
        api.get('/dictManager/getBusinesSourceDict').then(response => {
          if (response.data.code === 1) {
            this.optionsBusinessType = response.data.data;
          } else {
            Message.MessageError(response.data.msg)
          }
        });
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
                return Math.round((prev + curr) * 100) / 100;
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
      // 导出
      handleDownload() {
        if (this.tableList.length <= 0) {
          Message.MessageInfo('列表暂无数据!')
          return
        }
        this.downloadLoading = true
        require.ensure([], () => {
          const {export_json_to_excel} = require('vendor/Export2Excel')
          const data = this.formatJson(this.BusinessManageListRow, this.tableList)
          export_json_to_excel(this.BusinessManageListListHeader, data, '业务管理')
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
      // 刷新
      refreshList(row) {
        this.getData();
      },
      // 点击交易单号（查看该交易的交易详情）
      viewTransactionInfo(row) {
        this.$router.push(`/TransactionInfo/${row.account_sub_trade_id}`)
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
      // this.setDate()
      // this.show()
      this.getQueryInfo();
      this.getData();
    }
  }
</script>

<style scoped>
  .borderStyle {
    border-bottom: 1px solid gainsboro
  }
</style>
