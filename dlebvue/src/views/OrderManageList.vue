<template>
  <div>
    <el-form :inline="true" :model="searchForm" class="admin-search-form" align="left">
      <el-form-item label="发布时间:">
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
      <el-form-item label="关键字:">
        <el-tooltip effect="light" content="货物名称，发布方，接单方，起始地，目的地，金额，总量，体积" placement="bottom-start">
          <el-input v-model="searchForm.keyword" placeholder="关键字" clearable></el-input>
        </el-tooltip>
      </el-form-item>
      <div class="admin-search-btns">
        <el-button type="primary" icon="el-icon-search" @click="getData" :loading="this.isShowLoadingIcon">查询
        </el-button>
        <el-button type="primary" icon="el-icon-refresh" @click="refreshList">刷新</el-button>
      </div>
    </el-form>

    <el-table ref="myTable" stripe border
              :data="tableList"
              :max-height="500"
              :height="400"
              style="width: 100%"
              highlight-current-row
              @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="70" align="center"></el-table-column>
      <el-table-column prop="orderIssueTime" align="center" label="操作" width="100px">
        <template slot-scope="scope">
          <a @click="deletOrder(scope.row.orderId)" href="javascript:void(0)"  v-if="scope.row.orderStatus === 1" >删除</a>
        </template>
      </el-table-column>
      <el-table-column prop="orderIssueTime" align="center" label="发布时间" width="150px" show-overflow-tooltip></el-table-column>
      <el-table-column prop="orderNo" align="center" label="订单号" width="200px">
        <template slot-scope="scope">
          <a style="cursor:pointer;" @click="viewDetails(scope.row.orderId)">
            {{scope.row.orderNo}}</a>
        </template>
      </el-table-column>
      <el-table-column prop="issueCompanyName" align="center" label="发布方"  width="150px">
        <template slot-scope="scope">
          <a style="cursor:pointer;color: #409EFF;" @click="viewDetails(scope.row.issueCompanyId)">
            {{scope.row.issueCompanyName}}</a>
        </template>
      </el-table-column>
      <el-table-column prop="orderAssign" align="center" label="是否指定" width="100px">
        <template slot-scope="scope">
          <span v-if="scope.row.orderAssign"> 是</span>
          <span v-else>否</span>
        </template>
      </el-table-column>
      <el-table-column prop="receiveCompanyName" align="center" label="接单方"  width="150px">
        <template slot-scope="scope">
          <a style="cursor:pointer;color: #409EFF;" @click="viewDetails(scope.row.receiveCompanyId)">
            {{scope.row.receiveCompanyName}}</a>
        </template>
      </el-table-column>
      <el-table-column prop="orderPickupTime" align="center" label="预计提货日期" width="140px" show-overflow-tooltip></el-table-column>
      <el-table-column prop="orderFinalPickupTime" align="center" label="实际提货时间"width="140px" show-overflow-tooltip></el-table-column>
      <el-table-column prop="orderStatus" align="center" label="物流状态" width="100px">
        <template slot-scope="scope">
          <span v-if="scope.row.orderStatus === 1"> {{scope.row.orderAssign ? '待接单' : '待抢单'}}</span>
          <span v-else-if="scope.row.orderStatus === 2"> 已接单</span>
          <span v-else-if="scope.row.orderStatus === 3"> 已提货</span>
          <span v-else-if="scope.row.orderStatus === 4"> 已发货</span>
          <span v-else-if="scope.row.orderStatus === 5"> 已签收</span>
          <span v-else-if="scope.row.orderStatus === 6"> 已拒单</span>
          <span v-else> {{scope.row.orderStatus}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="orderFinalPickupTime" align="center" label="实际提货时间"width="140px" show-overflow-tooltip></el-table-column>
      <el-table-column prop="orderLogisticsNo" align="center" label="物流单号" width="150px"></el-table-column>
      <el-table-column prop="orderGoodsName" align="center" label="货物名称" show-overflow-tooltip width="150px"></el-table-column>
      <el-table-column prop="orderMoney" align="center" label="金额" width="50px"></el-table-column>
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
          label: '待接单/待抢单'
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
        multipleSelection: []
      }
    },
    methods: {
      // 获取数据
      getData() {
        this.isShowLoadingIcon = true;
        api.post('/order/getOrderList', this.searchForm).then(response => {
          if (response.data.code === 1) {
            this.tableList = response.data.data.list;
            this.pageSettings.total = response.data.data.total;
          }
        }).finally(() => {
          this.isShowLoadingIcon = false;
        })
      },
      deletOrder(value){
        this.$confirm('是否删除?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
          center: true
        }).then(() => {
          api.post('/order/deleteIssueOrder?orderId='+value).then(response => {
            if (response.data.code === 1) {
              this.$message({
                type: 'success',
                message: '删除成功!'
              });
            }else{
              this.$message({
                type: 'error',
                message: response.data.msg
              });
            }
            this.getData();
          })
        })
      },
      viewDetails(id) {
        this.$router.push({name: '物流详情', params: {'id':id}})
      },
      // 获取选中的数据
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      // 刷新
      refreshList(row) {
        this.getData();
      }
    },
    mounted() {
      this.getData()
    }
  }
</script>
