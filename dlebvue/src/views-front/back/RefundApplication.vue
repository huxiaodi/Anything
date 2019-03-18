<template>
  <div class="page1180">
    <div class="page-title">
      <div style="float:left;font-weight: bold;">
        退款申请
      </div>
    </div>
    <div class="page-content">
      <el-form class="searchForm" ref="searchForm" :model="searchForm" :inline="true" size="medium"
               style="margin-top: 10px">
        <el-form-item label="申请时间:" prop="searchTimes">
          <el-date-picker style="width:300px!important;" @change="getData" v-model="searchForm.searchTimes"
                          type="daterange" unlink-panels range-separator="至" start-placeholder="开始日期"
                          end-placeholder="结束日期" :picker-options="pickerOptions"></el-date-picker>
        </el-form-item>
        <!--<el-form-item label="申请企业:">-->
        <!--<el-select style="width:150px;" v-model="searchForm.refundCompany" @change="getData">-->
        <!--<el-option v-for="item in optionSubAccount" :key="item.accountSubId" :label="item.accountSubNo"-->
        <!--:value="item.accountSubNo">-->
        <!--</el-option>-->
        <!--</el-select>-->
        <!--</el-form-item>-->
        <el-form-item label="交易平台:">
          <el-select style="width:120px;" v-model="searchForm.businessFrom" @change="getData" placeholder="全部">
            <el-option v-for="item in optionSource" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="申请状态:">
          <el-select style="width:120px;" @change="getData" v-model="searchForm.refundStatus" placeholder="全部">
            <el-option v-for="item in StatusList" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="getData" :loading="this.isShowLoadingIcon"
                     v-show="_btn('tuikuanshenqin_query')">查询
          </el-button>
        </el-form-item>
      </el-form>
      <!--列表-->
      <el-table ref="myTable" stripe border :data="tableList" highlight-current-row
                style="width: 1180px!important;font-size:12px!important;margin:0 auto;">
        <el-table-column prop="refund_apply_for_data" align="center" label="申请时间" width="150px"></el-table-column>
        <el-table-column prop="business_trade_business_no" align="center" label="订单号" show-overflow-tooltip
                         width="130px">
          <template slot-scope="scope">
            <a href="javascript:void(0)" @click="viewDetails(scope.row)">{{scope.row.business_trade_business_no}}</a>
          </template>
        </el-table-column>
        <el-table-column prop="account_sub_trade_bank_no" align="center" label="付款流水单号"
                         width="190px"></el-table-column>
        <el-table-column prop="refund_proposer" align="center" label="申请人"
                         width="100px"></el-table-column>
        <el-table-column prop="refund_company" align="center" label="申请企业"
                         width="100px"></el-table-column>
        <el-table-column prop="refund_amount" align="center" label="申请金额"
                         width="100px"></el-table-column>
        <el-table-column prop="business_trade_business_fromC" align="center" label="交易平台"
                         width="70px"></el-table-column>
        <el-table-column prop="business_trade_business_typeC" align="center" label="交易类型"
                         width="70px"></el-table-column>
        <el-table-column prop="refund_statusC" align="center" label="申请状态"
                         width="70px"></el-table-column>
        <el-table-column prop="account_sub_trade_typeC" align="center" label="退款类型"
                         width="70px"></el-table-column>
        <el-table-column prop="refund_fee" align="center" label="手续费" width="70px"></el-table-column>
        <el-table-column prop="refund_remark" align="center" label="备注" width="70px"></el-table-column>
      </el-table>
      <!--分页-->
      <el-pagination :total="pageSettings.total" :current-page="pageSettings.pageNum" :page-size="pageSettings.pageSize"
                     :page-sizes="pageSettings.pageSizes" @size-change="sizeChange" @current-change="currentChange"
                     :layout="pageSettings.layout" align="right">
      </el-pagination>
    </div>
  </div>
</template>

<script>
  import api from '../api/fetch'
  import {pageMixin} from '../common/mixin'
  import * as Message from '../utils/messageUtil'

  export default {
    // 退款申请
    name: 'RefundApplication',
    mixins: [pageMixin],
    data() {
      return {
        searchForm: {
          searchTimes: null,
          businessFrom: '',
          refundStatus: ''
        },
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
        // 状态，0、待初审 1、待复审 2、待终审 3、待退款 4、已完成 5、已否决
        StatusList: [
          {
            value: null,
            label: '全部'
          }, {
            value: '0',
            label: '待初审'
          }, {
            value: '1',
            label: '待复审'
          }, {
            value: '2',
            label: '待终审'
          }, {
            value: '3',
            label: '待退款'
          }, {
            value: '4',
            label: '已完成'
          }, {
            value: '5',
            label: '已否决'
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
        tableList: []
      }
    },
    methods: {
      // 获取列表数据
      getData() {
        if (this.searchForm.searchTimes !== null) {
          this.searchForm.startTime = this.searchForm.searchTimes[0];
          this.searchForm.endTime = this.searchForm.searchTimes[1];
        } else {
          this.searchForm.startTime = '';
          this.searchForm.endTime = '';
        }
        api.post('/trade/getRefund', this.searchForm).then(response => {
          if (response.data.code === 1) {
            let list = response.data.data.list
            list.forEach((item) => {
              // 0、待初审 1、待复审 2、待终审 3、待退款 4、已完成 5、已否决
              if (item.refund_status === '0') {
                item.refund_statusC = '待初审';
              } else if (item.refund_status === '1') {
                item.refund_statusC = '待复审';
              } else if (item.refund_status === '2') {
                item.refund_statusC = '待终审';
              } else if (item.refund_status === '3') {
                item.refund_statusC = '待退款';
              } else if (item.refund_status === '4') {
                item.refund_statusC = '已完成';
              } else {
                item.refund_statusC = '已否决';
              }
              // 交易类型 1、运输 2、加工 3、采购 4、仓储
              if (item.business_trade_business_type === '1') {
                item.business_trade_business_typeC = '运输';
              } else if (item.business_trade_business_type === '2') {
                item.business_trade_business_typeC = '加工';
              } else if (item.business_trade_business_type === '3') {
                item.business_trade_business_typeC = '采购';
              } else {
                item.business_trade_business_typeC = '仓储';
              }
              // 退款类型 1-会员交易，2-转账，3-充值，4-提现，5-服务费，6-退款，7-挂账
              if (item.account_sub_trade_type === '1') {
                item.account_sub_trade_typeC = '会员交易';
              } else {
                item.account_sub_trade_typeC = '充值';
              }
              // 交易平台  1-德邻畅途 ，2-德邻电商 ，3-德邻云嘉
              if (item.business_trade_business_from === '1') {
                item.business_trade_business_fromC = '德邻畅途';
              } else if (item.business_trade_business_from === '2') {
                item.business_trade_business_fromC = '德邻电商';
              } else {
                item.business_trade_business_fromC = '德邻云嘉';
              }
            });
            this.tableList = list
            this.pageSettings.total = response.data.data.total
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
      // 点击流水单号查看详情页面
      viewDetails(row) {
        console.log(row);
        this.$router.push({
          name: '退款详情',
          params: row
        })
      }
    },
    mounted() {
      this.setOMDate()
      this.getData()
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
