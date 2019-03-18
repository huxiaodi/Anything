<template>
  <div class="page1180">
    <div class="page-title">
        <div style="float:left;font-weight: bold;">
          业务流水
        </div>
        <div style="float:right">
          <span style="cursor:pointer;color:#409EFF" type="text" @click="moreQuery">更多查询条件</span>
        </div>
    </div>
    <div class="page-content">
        <el-form class="searchForm" ref="searchForm" :model="searchForm" :inline="true" size="medium" style="margin-top: 10px">
          <el-form-item label="子账户:" prop="no">
            <el-select style="width:150px;" v-model="searchForm.no" @change="getData">
              <el-option v-for="item in optionSubAccount" :key="item.accountSubId" :label="item.accountSubNo" :value="item.accountSubNo">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="业务类型:" prop="businessType">
            <el-select style="width:120px;" v-model="searchForm.businessType" @change="getData" placeholder="全部">
              <el-option v-for="item in optionBusinessType" :key="item.value" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="交易状态:" prop="status">
            <el-select style="width:120px;" @change="getData" v-model="searchForm.status" placeholder="全部">
              <el-option v-for="item in optionsTradeStatus" :key="item.value" :label="item.label" :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="交易时间:" prop="searchTimes">
            <el-date-picker  style="width:300px!important;" v-model="searchForm.searchTimes" type="daterange" unlink-panels range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" :picker-options="pickerOptions" @change="getData"></el-date-picker>
          </el-form-item>
          <!-- 隐藏内容 -->
          <el-row style="display:inline-block" name="fade" class="fade-input" v-if="moreQueryVisible">
            <el-form-item label="平台来源:" prop="source">
              <el-select style="width:100px" v-model="searchForm.source" placeholder="全部">
                <el-option v-for="item in optionSource" :key="item.value" :label="item.label" :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item size="medium" label="金额范围:" prop="startAmt">
                <el-input style="width:100px;" v-model="searchForm.startAmt" @blur="setStartAmt">
                  <i slot="suffix">元</i>
                </el-input>
                <span>-</span>
                <el-input style="width:100px;" v-model="searchForm.endAmt" @blur="setEndAmt">
                  <i slot="suffix">元</i>
                </el-input>
            </el-form-item>
            <el-form-item label="交易对象:" prop="accountName">
              <el-input style="width:100px" v-model="searchForm.accountName"></el-input>
            </el-form-item>
            <el-form-item label="关键字:" prop="keyword">
              <el-input style="width:100px" v-model="searchForm.keyword"></el-input>
            </el-form-item>

          </el-row>
          <div style="display:inline-block;">
            <el-button type="primary" @click="getData" :loading="this.isShowLoadingIcon" v-show="_btn('yewuliushui_query')">搜索</el-button>
            <el-button type="primary" @click="resetForm('searchForm')" v-show="_btn('yewuliushui_reset')">重置</el-button>
          </div>
        </el-form>
        <div style="margin-top:15px;">
          <el-tabs v-model="activeName" type="card" @tab-click="handleClick" >
            <el-tab-pane label="全部" name="全部"></el-tab-pane>
            <el-tab-pane label="收入" name="收入"></el-tab-pane>
            <el-tab-pane label="支出" name="支出"></el-tab-pane>
          </el-tabs>
        </div>
        <!--列表-->
        <el-table ref="myTable" stripe border :data="tableList" highlight-current-row style="width: 1180px!important;font-size:12px!important;margin:0 auto;">
          <el-table-column prop="business_trade_create_time" align="center" label="交易时间" width="150px"></el-table-column>
          <!-- <el-table-column prop="account_sub_no" align="center" label="子账户" width="150px"></el-table-column> -->
          <el-table-column prop="business_trade_business_no" align="center" label="业务单号" show-overflow-tooltip width="130px"></el-table-column>
          <el-table-column prop="account_sub_trade_no" align="center" label="流水单号" width="190px">
            <template slot-scope="scope">
                <a style="cursor:pointer;" @click="viewDetails(scope.row)">
                  {{scope.row.account_sub_trade_bank_no}}</a>
              </template>
          </el-table-column>
          <el-table-column prop="account_sub_name" align="center" label="交易对象" width="200px" show-overflow-tooltip></el-table-column>
          <el-table-column prop="pay" align="center"  label="支出" width="90px">
            <template slot-scope="scope">
                <div style="color: red">{{scope.row.pay}}</div>
              </template>
          </el-table-column>
          <el-table-column prop="income" align="center" label="收入" width="90px">
            <template slot-scope="scope">
                <div style="color: green">{{scope.row.income}}</div>
              </template>
          </el-table-column>
          <el-table-column prop="business_trade_business_fromC" align="center" label="来源平台" width="100px"></el-table-column>
          <el-table-column prop="business_trade_business_typeC" align="center" label="业务类型" width="70px"></el-table-column>
          <el-table-column prop="status" align="center" label="交易状态" width="70px"></el-table-column>
        </el-table>
        <!--分页-->
        <el-pagination :total="pageSettings.total" :current-page="pageSettings.pageNum" :page-size="pageSettings.pageSize" :page-sizes="pageSettings.pageSizes" @size-change="sizeChange" @current-change="currentChange" :layout="pageSettings.layout" align="right">
        </el-pagination>
    </div>
</div>
</template>

<script>
import api from '../api/fetch'
import {
  pageMixin
} from '../common/mixin'
import * as Message from '../utils/messageUtil'

export default {
  // 业务查询
  name: 'BusinessQuery',
  mixins: [pageMixin],
  data() {
    return {
      searchForm: {
        no: [],
        searchTimes: null,
        businessType: '',
        source: '',
        startAmt: '',
        endAmt: '',
        accountName: '',
        keyword: '',
        income: '',
        status: ''
      },
      moreQueryVisible: false,
      optionSubAccount: [],
      pickerOptions: {
        shortcuts: [{
          text: '最近一周',
          onClick(picker) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
            picker.$emit('pick', [start, end])
          }
        }, {
          text: '最近一月',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
            picker.$emit('pick', [start, end]);
          }
        }, {
          text: '最近一年',
          onClick(picker) {
            const end = new Date();
            const start = new Date();
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30 * 12);
            picker.$emit('pick', [start, end]);
          }
        }]
      },
      optionBusinessType: [{
        value: null,
        label: '全部'
      }, {
        value: '1',
        label: '运输'
      }, {
        value: '3',
        label: '采购'
      }, {
        value: '4',
        label: '仓储'
      }, {
        value: '2',
        label: '加工'
      }],
      // 状态，1-创建，2-交易中，3-交易成功，4-交易失败，5-交易撤销
      optionsTradeStatus: [{
        value: null,
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
      optionSource: [
        {
          value: '',
          label: '全部'
        }, {
          value: '1',
          label: '德邻畅途'
        }, {
          value: '2',
          label: '德邻电商'
        }, {
          value: '3',
          label: '德邻云嘉'
        }
      ],
      tableList: [],
      activeName: '全部'
    }
  },
  methods: {
    // 列表上方的tab选项
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
    // 获取列表数据
    getData() {
      this.isShowLoadingIcon = true;
      if (this.searchForm.searchTimes !== null) {
        this.searchForm.startTime = this.searchForm.searchTimes[0];
        this.searchForm.endTime = this.searchForm.searchTimes[1];
      } else {
        this.searchForm.startTime = '';
        this.searchForm.endTime = '';
      }
      api.post('/trade/business/list', this.searchForm).then(response => {
        if (response.data.code === 1) {
          var list = response.data.data.list
          list.forEach((item) => {
            // 交易相对主体收支类型，1:支出 2:收入
            if (item.account_sub_trade_tran_payment_type === 1) {
              item.pay = item.account_sub_trade_tran_amt
            } else if (item.account_sub_trade_tran_payment_type === 2) {
              item.income = item.account_sub_trade_tran_amt
            }
            // 来源平台
            if (item.business_trade_business_from === '1') {
              item.business_trade_business_from = '德邻畅途'
            } else if (item.business_trade_business_from === '2') {
              item.business_trade_business_from = '德邻云商'
            } else if (item.business_trade_business_from === '3') {
              item.business_trade_business_from = '德邻云嘉'
            }

            // 业务平台
            if (item.business_trade_business_type === '1') {
              item.business_trade_business_type = '运输'
            } else if (item.business_trade_business_type === '2') {
              item.business_trade_business_type = '加工'
            } else if (item.business_trade_business_type === '3') {
              item.business_trade_business_type = '采购'
            }else if (item.business_trade_business_type === '4') {
              item.business_trade_business_type = '仓储'
            }


            // 状态，1-创建，2-交易中，3-交易成功，4-交易失败，5-交易撤销
            if (item.business_trade_status === '1') {
              item.status = '创建'
            } else if (item.business_trade_status === '2') {
              item.status = '交易中'
            } else if (item.business_trade_status === '3') {
              item.status = '交易成功'
            } else if (item.business_trade_status === '4') {
              item.status = '交易失败'
            } else if (item.business_trade_status === '5') {
              item.status = '交易撤销'
            }
            // 来源平台 1:转出 2:转入
            if (item.business_trade_business_from === '1') {
              item.business_trade_business_fromC = '德邻畅途';
            } else if (item.business_trade_business_from === '2') {
              item.business_trade_business_fromC = '德邻云商';
            } else if (item.business_trade_business_from === '3') {
              item.business_trade_business_fromC = '德邻云嘉';
            } else {
              item.business_trade_business_fromC = item.business_trade_business_from;
            }
            // 业务类型 1:转出 2:转入
            if (item.business_trade_business_type === '2') {
              item.business_trade_business_typeC = '加工';
            } else if (item.business_trade_business_type === '3') {
              item.business_trade_business_typeC = '采购';
            } else if (item.business_trade_business_type === '1') {
              item.business_trade_business_typeC = '运输';
            } else if (item.business_trade_business_from === '4') {
              item.business_trade_business_fromC = '仓储';
            } else {
              item.business_trade_business_typeC = item.business_trade_business_type;
            }
          })
          this.tableList = list
          this.pageSettings.total = response.data.data.total
        }
      }).finally(() => {
        this.isShowLoadingIcon = false;
      })
    },
    // 获取子账户数据
    getSubAccount() {
      api.get('/accountSub/accountSubController/getAccountSubs').then(response => {
        if (response.data.code === 1) {
          this.optionSubAccount = response.data.data
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
    // 更多查询条件是否显示
    moreQuery() {
      if (this.moreQueryVisible) {
        this.moreQueryVisible = false
      } else {
        this.moreQueryVisible = true
      }
    },
    // 重置
    resetForm(formName) {
      this.$refs[formName].resetFields()
      this.searchForm.endAmt = ''
      this.getData()
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
    // 点击流水单号查看详情页面
    viewDetails(row) {
      this.$router.push({
        name: '业务详情',
        params: row
      })
    }
  },
  mounted() {
    this.getSubAccount()
    this.getData()
    this.setOMDate()
  }
}
</script>

<style scoped>
.fade-input {
  width: 100%;
  margin: 0;
  padding: 0;
}
</style>
