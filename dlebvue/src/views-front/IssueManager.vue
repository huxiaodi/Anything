<template>
  <div class="page1180">
    <div class="page-title">
      <div style="float:left;font-weight: bold;">
        发布管理
      </div>
    </div>
  <el-row class="page-content">
    <el-form class="searchForm" ref="searchForm" :model="searchForm" :inline="true" size="medium" style="margin-top: 10px">
      <el-form-item label="是否指定:" prop="tradeType">
        <el-select style="width:120px;" @change="getData" v-model="searchForm.tradeType" placeholder="全部">
          <el-option v-for="item in optionsTradeType" :key="item.value" :label="item.label" :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="物流状态:" prop="status">
        <el-select style="width:120px;" @change="getData" v-model="searchForm.status" placeholder="全部">
          <el-option v-for="item in optionsTradeStatus" :key="item.value" :label="item.label" :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="发布时间:" prop="searchTimes">
        <el-date-picker style="width:300px!important;" @change="getData" v-model="searchForm.searchTimes" type="daterange" unlink-panels range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" :picker-options="pickerOptions"></el-date-picker>
      </el-form-item>
      <el-form-item size="medium" label="关键字:" prop="keyword">
        <el-tooltip class="item" effect="dark" content="货物名称，接单方名称，起始地，目的地，金额，总量，体积" placement="top">
          <el-input style="width:100px;" v-model="searchForm.keyword"></el-input>
        </el-tooltip>
      </el-form-item>
      <div style="display:inline-block">
        <el-button size="medium" type="primary" @click="getData" :loading="this.isShowLoadingIcon" v-show="_btn('zijinliushui_query')">搜索</el-button>
        <el-button size="medium" type="primary" @click="searchReset('searchForm')" v-show="_btn('zijinliushui_reset')">重置</el-button>
        <el-button size="medium" type="primary" @click="issue" v-show="_btn('zijinliushui_reset')">发布</el-button>
      </div>
    </el-form>
    <div style="margin-top:15px;">
      <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
        <el-tab-pane label="全部" name="全部"></el-tab-pane>
        <el-tab-pane label="待发布" name="待发布"></el-tab-pane>
        <el-tab-pane label="已发布" name="已发布"></el-tab-pane>
      </el-tabs>
    </div>
    <el-table ref="myTable" stripe border :data="tableList" style="width: 1180px!important;font-size:12px!important;" highlight-current-row>
      <el-table-column prop="account_sub_trade_tran_date" align="center" label="交易时间" width="150px"></el-table-column>
      <!-- <el-table-column prop="account_sub_no" align="center" label="子账户" width="140px"></el-table-column> -->
      <el-table-column prop="account_sub_trade_typeC" align="center" label="类型" width="80px"></el-table-column>
      <el-table-column prop="account_sub_name" align="center" label="交易对象" show-overflow-tooltip width="210px"></el-table-column>
      <el-table-column prop="account_sub_trade_no" align="center" label="流水单号" width="220px">
        <template slot-scope="scope">
              <a style="cursor:pointer;" @click="viewDetails(scope.row)">
                {{scope.row.account_sub_trade_bank_no}}</a>
            </template>
      </el-table-column>
      <el-table-column prop="business_trade_business_no" align="center" label="订单号" width="140px" show-overflow-tooltip></el-table-column>
      <el-table-column prop="payAmount" align="center"  label="支出" width="100px">
        <template slot-scope="scope">
              <div style="color: red">
                {{scope.row.payAmount}}
              </div>
            </template>
      </el-table-column>
      <el-table-column prop="incomeAmount" align="center" label="收入" width="100px">
        <template slot-scope="scope">
              <div style="color:green;">
                {{scope.row.incomeAmount}}
              </div>
            </template>
      </el-table-column>
      <el-table-column prop="account_sub_trade_tran_statusC" align="center" label="交易状态" width="80px"></el-table-column>
    </el-table>
    <el-pagination :total="pageSettings.total" :current-page="pageSettings.pageNum" :page-size="pageSettings.pageSize" :page-sizes="pageSettings.pageSizes" @size-change="sizeChange" @current-change="currentChange" :layout="pageSettings.layout" align="right">
    </el-pagination>
  </el-row>


</div>
</template>

<script>
import api from '../api/fetch'
import * as Message from '../utils/messageUtil'
import {
  pageMixin
} from '../common/mixin.js'
import {
  parseTime
} from '../utils/index'
import {
  exportModel
} from '../common/export'

export default {
  name: 'FeeDetailList',
  mixins: [pageMixin, exportModel],
  data() {
    return {
      searchForm: {
        no: '',
        searchTimes: null,
        tradeType: '',
        accountName: '',
        startAmt: '',
        endAmt: '',
        keyword: '',
        income: '',
        status: ''
      },
      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近一个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近三个月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
            picker.$emit('pick', [start, end]);
          }
        }]
      },
      moreQueryVisible: false,
      tableList: [],
      optionsSubAccount: [],
      // 财务类型
      optionsTradeType: [{
        value: '',
        label: '全部'
      }, {
        value: '1',
        label: '会员交易'
      }, {
        value: '4',
        label: '提现'
      }, {
        value: '3',
        label: '充值'
      }, {
        value: '2',
        label: '转账'
      }],
      optionsTradeStatus: [{
        value: null,
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
        value: '10',
        label: '退单'
      }],
      activeName: '全部'
    }
  },
  methods: {
    // 搜索 获取数据
    getData() {
      this.isShowLoadingIcon = true;
      if (this.searchForm.searchTimes !== null) {
        this.searchForm.startTime = this.searchForm.searchTimes[0];
        this.searchForm.endTime = this.searchForm.searchTimes[1];
      } else {
        this.searchForm.startTime = '';
        this.searchForm.endTime = '';
      }
      api.post('/order/getOrderList', this.searchForm).then(response => {
        if (response.data.code === 1) {
          let list = response.data.data.list;
          list.forEach((item) => {
            // 支出还是收入
            if (item.account_sub_trade_tran_payment_type === 1) {
              // 支出
              item.payAmount = item.account_sub_trade_tran_amt;
            } else if (item.account_sub_trade_tran_payment_type === 2) {
              // 收入
              item.incomeAmount = item.account_sub_trade_tran_amt;
            }
            // 类型
            if (item.account_sub_trade_type === '1') {
              item.account_sub_trade_typeC = '会员交易';
            } else if (item.account_sub_trade_type === '2') {
              item.account_sub_trade_typeC = '转账';
            } else if (item.account_sub_trade_type === '3') {
              item.account_sub_trade_typeC = '充值';
            } else if (item.account_sub_trade_type === '4') {
              item.account_sub_trade_typeC = '提现';
            } else if (item.account_sub_trade_type === '5') {
              item.account_sub_trade_typeC = '服务费';
            } else if (item.account_sub_trade_type === '6') {
              item.account_sub_trade_typeC = '退款';
            } else if (item.account_sub_trade_type === '7') {
              item.account_sub_trade_typeC = '充值';
            }
            // 交易状态
            if (item.account_sub_trade_tran_status === 0) {
              item.account_sub_trade_tran_statusC = '成功';
            } else if (item.account_sub_trade_tran_status === 1) {
              item.account_sub_trade_tran_statusC = '失败';
            } else if (item.account_sub_trade_tran_status === 2) {
              item.account_sub_trade_tran_statusC = '待确认';
            } else if (item.account_sub_trade_tran_status === 5) {
              item.account_sub_trade_tran_statusC = '待处理';
            } else if (item.account_sub_trade_tran_status === 6) {
              item.account_sub_trade_tran_statusC = '处理中';
            }else if (item.account_sub_trade_tran_status === 8) {
              item.account_sub_trade_tran_statusC = '成功';
            } else if (item.account_sub_trade_tran_status === -1) {
              item.account_sub_trade_tran_statusC = '无效';
            } else if (item.account_sub_trade_tran_status === 10) {
              item.account_sub_trade_tran_statusC = '提现退单';
            }
          })
          this.tableList = list;
          this.pageSettings.total = response.data.data.total
        }
      }).finally(() => {
        this.isShowLoadingIcon = false;
      })
    },
    getSubAccount() {
      api.get('/accountSub/accountSubController/getAccountSubs').then(response => {
        if (response.data.code === 1) {
          this.optionsSubAccount = response.data.data;
        } else {
          Message.MessageError(response.data.msg)
        }
      })
    },
    // 设置列表时间查询条件默认为最近30天
    setOMDate() {
      const start = new Date();
      const end = new Date();
      start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
      start.setHours(0);
      start.setMinutes(0);
      start.setSeconds(0);
      start.setMilliseconds(0);
      end.setHours(0);
      end.setMinutes(0);
      end.setSeconds(0);
      end.setMilliseconds(0);
      this.searchForm.searchTimes = [start, end];
    },
    // 金额只能输入正整数
    setStartAmt() {
      // 正整数
      const intReg = /^[0-9]*$/;
      if (!intReg.test(this.searchForm.startAmt)) {
        this.searchForm.startAmt = 0
      }
    },
    setEndAmt() {
      // 正整数
      const intReg = /^[0-9]*$/;
      if (!intReg.test(this.searchForm.endAmt)) {
        this.searchForm.endAmt = 0
      }
      // 金额范围支持只输入一个，当只输入上限值时，下限值默认为0
      if (!this.searchForm.startAmt) {
        if (this.searchForm.endAmt > 0) {
          this.searchForm.startAmt = 0
        }
      }
    },
    // 重置
    searchReset(formName) {
      this.$refs[formName].resetFields();
      this.searchForm.endAmt = '';
      // 查询条件重置之后重新查询列表
      this.getData();
    },
    // 控制更多查询条件是否显示
    moreQuery() {
      if (this.moreQueryVisible) {
        this.moreQueryVisible = false
      } else {
        this.moreQueryVisible = true
      }
    },
    handleClick(tab, event) {
      if (tab.index === '1') {
        // 收入
        this.searchForm.income = true;
      } else if (tab.index === '2') {
        // 支出
        this.searchForm.income = false;
      } else {
        // 全部
        this.searchForm.income = '';
      }
      this.getData();
    },
    // 点击流水单号查看详情页面
    viewDetails(row) {
      row.pageType = "FeeDetailList";
      // 1-会员交易，2-转账，3-充值，4-提现，5-服务费，6-退款
      if (row.account_sub_trade_typeC.indexOf('交易') !== -1) {
        this.$router.push({
          name: '交易详情',
          params: row
        });
      } else if (row.account_sub_trade_typeC === '充值') {
        this.$router.push({
          name: '充值详情',
          params: row
        })
      } else if (row.account_sub_trade_typeC === '提现') {
        this.$router.push({
          name: '提现详情',
          params: row
        })
      } else if (row.account_sub_trade_typeC === '转账') {
        this.$router.push({
          name: '交易详情',
          params: row
        })
      } else {
        this.$router.push({
          name: '交易详情',
          params: row
        })
      }
    },
    issue(){
      this.$router.push({
        name: '添加银行卡',
        params: {}
      })
    }
  },
  mounted() {
    this.searchForm.tradeType = this.$route.params.tradeType;
    this.setOMDate();
    this.getData();
  }
}
</script>
<style scoped>

</style>
