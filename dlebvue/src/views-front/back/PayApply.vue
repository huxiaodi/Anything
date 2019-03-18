<template>
  <div class="page1180">
    <div class="page-title">
      <div style="float:left;font-weight: bold;">
        付款申请
      </div>
    </div>
    <div class="page-content">
      <el-form ref="searchForm" :model="searchForm" label-width="90px" :inline="true">
        <el-form-item label="申请时间:" prop="searchTimes">
          <el-date-picker style="width:300px!important;" @change="getData" v-model="searchForm.searchTimes"
                          type="daterange" unlink-panels range-separator="至" start-placeholder="开始日期"
                          end-placeholder="结束日期" :picker-options="pickerOptions"></el-date-picker>
        </el-form-item>
        <el-form-item label="付款对象:">
          <el-select style="width:120px;" clearable  v-model="searchForm.applicationTradeTrasactionObject" @change="getData" placeholder="全部">
            <el-option v-for="item in objecSource" :key="item" :label="item" :value="item">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="交易平台:">
          <el-select style="width:120px;" clearable  v-model="searchForm.applicationTradeTrasactionFrom" @change="getData" placeholder="全部">
            <el-option v-for="item in optionSource" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="申请状态:">
          <el-select style="width:120px;"  clearable  @change="getData" v-model="searchForm.applicationStatus" placeholder="全部">
            <el-option v-for="item in StatusList" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="关键字:" prop="keyword">
          <el-input style="width:100px" v-model="searchForm.keyword"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="getData" :loading="this.isShowLoadingIcon"
                     v-show="_btn('fukuanshenqin_query')">查询
          </el-button>
        </el-form-item>
      </el-form>
      <!--列表-->
      <el-table ref="myTable" stripe border :data="tableList" highlight-current-row
                style="width: 1180px!important;font-size:12px!important;margin:0 auto;">
        <el-table-column prop="application_trade_create_time" align="center" label="申请时间" width="150px"></el-table-column>
        <el-table-column prop="application_trade_application_no" align="center" label="订单号" show-overflow-tooltip
                         width="130px">
          <template slot-scope="scope">
            <a href="javascript:void(0)" @click="viewDetails(scope.row)">{{scope.row.application_trade_application_no}}</a>
          </template>
        </el-table-column>
        <el-table-column prop="application_trade_account_sub_no" align="center" label="付款子账户"
                         width="190px"></el-table-column>
        <el-table-column prop="account_sub_trade_bank_no" align="center" label="付款流水单号"
                         width="190px"></el-table-column>
        <el-table-column prop="application_trade_proposer" align="center" label="申请人"
                         width="100px"></el-table-column>
        <el-table-column prop="application_trade_trasaction_object" align="center" label="付款对象"
                         width="100px"></el-table-column>
        <el-table-column prop="application_trade_tran_amt" align="center" label="申请金额"
                         width="100px"></el-table-column>
        <el-table-column prop="application_trade_business_fromC" align="center" label="业务来源"
                         width="70px"></el-table-column>
        <el-table-column prop="application_trade_business_typeC" align="center" label="业务类型"
                         width="70px"></el-table-column>
        <el-table-column prop="application_statusC" align="center" label="申请状态"
                         width="70px"></el-table-column>
        <el-table-column prop="application_trade_tran_free_amt" align="center" label="手续费" width="70px"></el-table-column>
        <el-table-column prop="application_trade_remark" align="center" label="备注" width="70px"></el-table-column>
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
  import { exportModel } from '../common/export'
  import * as Message from '../utils/messageUtil'

  export default {
    name: 'PayApply',
    mixins: [pageMixin, exportModel],
    data(){
      return {
        searchForm: {
          searchTimes: null,
          businessFrom: '',
          applicationStatus: ''
        },
        isShowLoadingIcon: false,
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
            label: '待付款'
          }, {
            value: '4',
            label: '已完成'
          }, {
            value: '5',
            label: '已否决'
          },
          {
            value: '6',
            label: '付款失败'
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
        objecSource: [],
        tableList: []
      }
    },
    methods: {
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
      // 获取列表数据
      getData() {
        if (this.searchForm.searchTimes !== null) {
          this.searchForm.startTime = this.searchForm.searchTimes[0];
          this.searchForm.endTime = this.searchForm.searchTimes[1];
        } else {
          this.searchForm.startTime = '';
          this.searchForm.endTime = '';
        }
        api.post('/pay/getPayApplicationList', this.searchForm).then(response => {
          if (response.data.code === 1) {
            let list = response.data.data.list
            list.forEach((item) => {
              // 0、待初审 1、待复审 2、待终审 3、待退款 4、已完成 5、已否决
              if (item.application_trade_application_status === '0') {
                item.application_statusC = '待初审';
              } else if (item.application_trade_application_status === '1') {
                item.application_statusC = '待复审';
              } else if (item.application_trade_application_status === '2') {
                item.application_statusC = '待终审';
              } else if (item.application_trade_application_status === '3') {
                item.application_statusC = '待付款';
              } else if (item.application_trade_application_status === '4') {
                item.application_statusC = '已完成';
              } else if (item.application_trade_application_status === '5') {
                item.application_statusC = '已否决';
              } else {
                item.application_statusC = '付款失败';
              }
              // 交易类型 1、运输 2、加工 3、采购 4、仓储
              if (item.application_trade_business_type === '1') {
                item.application_trade_business_typeC = '运输';
              } else if (item.application_trade_business_type === '2') {
                item.application_trade_business_typeC = '加工';
              } else if (item.application_trade_business_type === '3') {
                item.application_trade_business_typeC = '采购';
              } else {
                item.application_trade_business_typeC = '仓储';
              }
              // 交易平台  1-德邻畅途 ，2-德邻电商 ，3-德邻云嘉
              if (item.application_trade_trasaction_from === '1') {
                item.application_trade_business_fromC = '德邻畅途';
              } else if (item.application_trade_trasaction_from === '2') {
                item.application_trade_business_fromC = '德邻电商';
              } else {
                item.application_trade_business_fromC = '德邻云嘉';
              }
            });
            this.tableList = list
            this.pageSettings.total = response.data.data.total
          } else {
            Message.MessageError(response.data.msg)
          }
        })
      },
      getObjecSource() {
        api.get('/pay/getObjecSource').then(response => {
          if (response.data.code === 1) {
            this.objecSource = response.data.data;
          }
        })
      },
      viewDetails(row) {
        this.$router.push({
          name: '付款详情',
          params: row
        })
      }
    },
    mounted() {
      this.setOMDate();
      this.getData();
      this.getObjecSource();
    }
  }
</script>

<style scoped>

</style>
