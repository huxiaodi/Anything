<template>
  <div class="aaa">
    <!--查询条件-->
    <el-form :inline="true" :model="searchForm" class="admin-search-form" align="left">

      <el-form-item label="关键字:">
        <el-tooltip class="item" effect="light" content="关键字查询【商品名称、规格型号】" placement="bottom-start">
          <el-input placeholder="关键字" v-model="searchForm.keyword"></el-input>
        </el-tooltip>
      </el-form-item>
      <div class="admin-search-btns">

        <el-button type="primary"  @click="getData()" :loading="this.isShowLoadingIcon">查询</el-button>

      </div>
    </el-form>

    <!--列表-->
    <el-table ref="myTable" stripe border
              :data="tableList"
              :max-height="500"
              :height="400"
              style="width: 100%"
              highlight-current-row
              @selection-change="handleSelectionChange">
      <!--<el-table-column type="selection" width="40px" align="center"></el-table-column>-->

      <el-table-column prop="djh" label="单据号" align="center" width="110px"></el-table-column>
      <el-table-column prop="xh" label="序号" align="center"></el-table-column>
      <el-table-column prop="spmc" label="商品名称" align="center" width="180px"></el-table-column>
      <el-table-column prop="ggxh" label="规格型号" align="center" width="150px"></el-table-column>
      <el-table-column prop="jldw" label="计量单位" align="center" width="210px"></el-table-column>
      <el-table-column prop="spbm" label="商品编码" align="center" width="210px"></el-table-column>
      <el-table-column prop="qyspbm" label="企业商品编码" align="center"></el-table-column>
      <el-table-column prop="syyhzcbz" label="优惠政策标识" align="center"></el-table-column>
      <el-table-column prop="lslbz" label="零税率标识" align="center"></el-table-column>
      <el-table-column prop="yhzcsm" label="优惠政策说明" align="center" width="120px"></el-table-column>
      <el-table-column prop="dj" label="单价" align="center"></el-table-column>
      <el-table-column prop="sl" label="数量" align="center"></el-table-column>
      <el-table-column prop="je" label="金额" align="center"></el-table-column>
      <el-table-column prop="slv" label="税率" align="center"></el-table-column>
      <el-table-column prop="kce" label="扣除额" align="center"></el-table-column>

      <el-table-column prop="createUser" label="创建人" align="center"></el-table-column>
      <el-table-column prop="createDate" label="创建时间" align="center" width="170px"></el-table-column>

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
  import {pageMixin} from '../common/mixin'

  export default {
    // 用户管理
    name: 'InvoiceManageDeteil',
    mixins: [pageMixin],
    data() {
      return {
        searchForm: {  },
        isReadOnly: false,
        tableList: [],
        multipleSelection: [],
        roleSelection: [],
        teamSelection: [],
        djh:''


      }
    },
    methods: {
      // 获取数据
      getData() {
        this.searchForm.codeDetail = this.djh;
        api.post('invoiceManage/getFpDetailList', this.searchForm).then(response => {
          if (response.data.code === 1) {
          this.updateTableData(response.data.data)
        } else {
          Message.MessageError('列表获取失败')
        }
      }).finally(() => {
          this.isShowLoadingIcon = false;
      })
      },
      refreshList() {
        this.getData();
      },
      // 获取选中的数据
      handleSelectionChange(val) {
        this.multipleSelection = val;
      }
    },
    mounted() {
      this.djh = this.$route.params.id
      this.getData();
    }
  }
</script>

<style scoped>

</style>
