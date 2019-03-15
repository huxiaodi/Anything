<template>
  <div class="page1180">
    <div class="page-title">
      <div style="float:left;font-weight: bold;">
        发布管理
      </div>
    </div>
  <el-row class="page-content">
    <el-form class="searchForm" ref="searchForm" :model="searchForm" :inline="true" size="medium" style="margin-top: 10px">
      <el-form-item label="是否指定:" prop="orderAssign">
        <el-select style="width:120px;" @change="getData" v-model="searchForm.orderAssign" placeholder="全部">
          <el-option v-for="item in optionsTradeType" :key="item.value" :label="item.label" :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="物流状态:" prop="orderStatus">
        <el-select style="width:120px;" @change="getData" v-model="searchForm.orderStatus" placeholder="全部">
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
        <el-tab-pane label="已发布" name="已发布"></el-tab-pane>
        <el-tab-pane label="待发布" name="待发布"></el-tab-pane>
      </el-tabs>
    </div>
    <el-table ref="myTable" stripe border :data="tableList" style="width: 1180px!important;font-size:12px!important;" highlight-current-row>
      <el-table-column prop="orderIssueTime" align="center" label="操作" width="150px">
        <template slot-scope="scope">
          <a @click="delete(scope.row)" >删除</a>
          <a @click="modify(scope.row)" >修改</a>
        </template>
      </el-table-column>
      <el-table-column prop="orderIssueTime" align="center" label="发布时间" width="150px"></el-table-column>
      <el-table-column prop="orderNo" align="center" label="订单号" width="150px">
        <template slot-scope="scope">
          <a style="cursor:pointer;" @click="viewDetails(scope.row)">
            {{scope.row.orderNo}}</a>
        </template>
      </el-table-column>
      <el-table-column prop="userName" align="center" label="发布人"  width="150px"></el-table-column>
      <el-table-column prop="orderStatus" align="center" label="物流状态" width="100px">
        <template slot-scope="scope">
          <span v-if="scope.row.orderStatus === 1"> 待接单</span>
          <span v-else-if="scope.row.orderStatus === 2"> 已接单</span>
          <span v-else-if="scope.row.orderStatus === 3"> 已提货</span>
          <span v-else-if="scope.row.orderStatus === 4"> 已发货</span>
          <span v-else-if="scope.row.orderStatus === 5"> 已签收</span>
          <span v-else> {{scope.row.orderStatus}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="orderGoodsName" align="center" label="货物名称" show-overflow-tooltip width="150px"></el-table-column>
      <el-table-column prop="orderMoney" align="center" label="金额" width="50px"></el-table-column>
      <el-table-column prop="orderPickupTime" align="center" label="预计提货日期" width="140px" show-overflow-tooltip></el-table-column>
      <el-table-column prop="orderAssign" align="center" label="是否指定" width="100px">
        <template slot-scope="scope">
          <span v-if="scope.row.orderAssign"> 是</span>
          <span v-else>否</span>
        </template>
      </el-table-column>
      <el-table-column prop="companyName" align="center" label="接单方" width="220px"></el-table-column>
      <el-table-column prop="orderFinalPickupTime" align="center" label="实际提货时间"width="140px" show-overflow-tooltip></el-table-column>
      <el-table-column prop="orderLogisticsNo" align="center" label="物流单号" width="150px"></el-table-column>
      <el-table-column prop="orderGoodsWeight" align="center"  label="重量" width="80px"></el-table-column>
      <el-table-column prop="orderGoodsCube" align="center" label="体积" width="80px"></el-table-column>
      <el-table-column prop="orderPickupProvince" align="center" label="起始地" width="150px" show-overflow-tooltip>
        <template slot-scope="scope">
          <span>{{scope.row.orderPickupProvince}}/{{scope.row.orderPickupCity}}/{{scope.row.orderPickupDistrict}}/{{scope.row.orderPickupAddress}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="orderReceiveProvince" align="center" label="目的地" width="150px" show-overflow-tooltip>
        <template slot-scope="scope">
          <span>{{scope.row.orderReceiveProvince}}/{{scope.row.orderReceiveCity}}/{{scope.row.orderReceiveDistrict}}/{{scope.row.orderReceiveAddress}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="orderRemark" align="center" label="备注" width="100px" show-overflow-tooltip></el-table-column>
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
        searchTimes: null,
        keyword: '',
        orderIsIssue: '',
        orderAssign: '',
        orderStatus: ''
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
      tableList: [],
      // 是否指定
      optionsTradeType: [{
        value: '',
        label: '全部'
      }, {
        value: true,
        label: '是'
      }, {
        value: false,
        label: '否'
      }],
      // 物流状态
      optionsTradeStatus: [{
        value: null,
        label: '全部'
      }, {
        value: 1,
        label: '待接单'
      }, {
        value: 2,
        label: '已接单'
      }, {
        value: 3,
        label: '已提货'
      }, {
        value: 4,
        label: '已发货'
      }, {
        value: 5,
        label: '已签收'
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

          })
          this.tableList = list;
          this.pageSettings.total = response.data.data.total
        }
      }).finally(() => {
        this.isShowLoadingIcon = false;
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
    // 重置
    searchReset(formName) {
      this.$refs[formName].resetFields();
      this.setOMDate();
      // 查询条件重置之后重新查询列表
      this.getData();
    },
    handleClick(tab, event) {
      if (tab.index === '1') {
        // 已发布
        this.searchForm.orderIsIssue = true;
      } else if (tab.index === '2') {
        // 待发布
        this.searchForm.orderIsIssue = false;
      } else {
        // 全部
        this.searchForm.orderIsIssue = '';
      }
      this.getData();
    },
    // 点击单号查看详情页面
    viewDetails(row) {
      this.$router.push({
        name: '发布详情',
        params: {id:row.orderId}
      })
    },
    issue(){
      this.$router.push({
        name: '发布',
        params: {}
      })
    },
    modify(row){

    },
    delete(row){

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
