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
      <el-form-item label="关键字:">
        <el-tooltip effect="light" content="货物名称，发布方，接单方，起始地，目的地，金额，总量，体积" placement="bottom-start">
          <el-input v-model="searchForm.keyword" placeholder="关键字" clearable></el-input>
        </el-tooltip>
      </el-form-item>
      <div class="admin-search-btns">
        <el-button type="primary" icon="el-icon-search" @click="getData" :loading="this.isShowLoadingIcon">查询
        </el-button>
        <el-button type="primary" icon="el-icon-refresh" @click="addNoticePre">新增</el-button>
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
          <a @click="modify(scope.row)" href="javascript:void(0)">修改</a>
          <a @click="deletOrder(scope.row.sysNoticeId)" href="javascript:void(0)">删除</a>
        </template>
      </el-table-column>
      <el-table-column prop="sysNoticeTitle" align="center" label="标题" width="140px" show-overflow-tooltip>
        <template slot-scope="scope">
          <a @click="viewDetails(scope.row)" href="javascript:void(0)">{{scope.row.sysNoticeTitle}}</a>
        </template>
      </el-table-column>
      <el-table-column prop="sysNoticeContent" align="center" label="内容"width="300px" show-overflow-tooltip>
        <template slot-scope="scope">
          <a @click="viewDetails(scope.row)" href="javascript:void(0)">{{scope.row.sysNoticeContent}}</a>
        </template>
      </el-table-column>
      <el-table-column prop="sysNoticeIssueTime" align="center" label="发布开始时间" width="200px"></el-table-column>
      <el-table-column prop="sysNoticeEndTime" align="center" label="发布结束时间"  width="200px"></el-table-column>
      <el-table-column prop="sysNoticeCreateUserName" align="center" label="发布人" width="140px"></el-table-column>
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


    <el-dialog :title="title" :visible.sync="dialogFormVisible" width="60%"  >
      <el-form :model="formData"  label-position="left" label-width="80px" ref="formDataForm" :rules="formDataRules">
        <el-form-item label="标题："  prop="sysNoticeTitle" >
          <el-input v-model="formData.sysNoticeTitle" :disabled="!optType" ></el-input>
        </el-form-item>
        <el-form-item label="内容："  prop="sysNoticeContent">
          <el-input type="textarea"  :rows="2" v-model="formData.sysNoticeContent" :disabled="!optType"></el-input>
        </el-form-item>
        <el-row>
          <el-col :span="12">
            <el-form-item label="发布开始时间：" label-width="112px"  prop="sysNoticeIssueTime">
              <el-date-picker style="width: 200px" type="datetime" v-model="formData.sysNoticeIssueTime"
                              format="yyyy-MM-dd hh:mm:ss" value-format="yyyy-MM-dd hh:mm:ss" placeholder="选择日期" :disabled="!optType"></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="发布结束时间：" label-width="112px"  prop="sysNoticeEndTime">
              <el-date-picker style="width: 200px" type="datetime" v-model="formData.sysNoticeEndTime"
                              format="yyyy-MM-dd hh:mm:ss" value-format="yyyy-MM-dd hh:mm:ss" placeholder="选择日期" :disabled="!optType"></el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button v-if="optType" @click="dialogFormVisible = false">取 消</el-button>
        <el-button v-if="optType" type="primary" @click="addNotice">确 定</el-button>
      </div>
    </el-dialog>

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
        multipleSelection: [],
        formData: {},
        dialogFormVisible: false,
        formDataRules: {

        },
        title: '',
        optType: true
      }
    },
    methods: {
      // 获取数据
      getData() {
        this.isShowLoadingIcon = true;
        api.post('/notice/getNoticeListAll', this.searchForm).then(response => {
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
          api.get('/notice/deleteNotice?id='+value).then(response => {
            if (response.data.code === 1) {
              this.getData();
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
      viewDetails(value) {
        this.title = '公告详情';
        this.optType = false;
        this.dialogFormVisible = true;
        this.formData = value;
      },
      modify(value){
        this.title = '修改公告';
        this.optType = true;
        this.dialogFormVisible = true;
        this.formData = value;
      },
      // 获取选中的数据
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      addNoticePre(){
        this.title = '新增公告';
        this.optType = true;
        this.dialogFormVisible = true;
        this.formData = {};
      },
      addNotice(){
        this.$refs.formDataForm.validate((valid) => {
          if(valid){
            api.post('/notice/insertNotice', this.formData).then(response => {
              if (response.data.code === 1) {
                Message.MessageSuccess("操作成功");
                this.dialogFormVisible = false;
                this.getData();
              }else{
                Message.MessageError(response.data.msg);
              }
            })
          }
        });

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
