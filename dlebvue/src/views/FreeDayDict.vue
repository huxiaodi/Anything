<template>
  <div>
    <!--查询条件-->
    <el-form :inline="true" :model="searchForm" class="admin-search-form" align="left">
      <el-form-item label="创建时间:">
        <el-col :span="8" style="width: 150px">
          <el-date-picker style="width: 140px" type="date" v-model="searchForm.startTime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="选择日期"></el-date-picker>
        </el-col>
        <el-col :span="1" style="width: 25px">至</el-col>
        <el-col :span="8" style="width: 150px">
          <el-date-picker style="width: 140px" type="date" v-model="searchForm.endTime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          placeholder="选择日期"></el-date-picker>
        </el-col>
      </el-form-item>
      <el-form-item label="品种:">
        <el-select  v-model="searchForm.freeDayKind" placeholder="请选择" clearable filterable>
          <el-option
            v-for="item in optionsFreeDayKind"
            :key="item.kind_name"
            :label="item.kind_name"
            :value="item.kind_name">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="客户名称:">
        <el-select  v-model="searchForm.freeDayCustomer" placeholder="请选择" clearable filterable>
          <el-option
            v-for="item in optionsFreeDayCustomer"
            :key="item.customer_name"
            :label="item.customer_name"
            :value="item.customer_name">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="关键字:">
        <el-tooltip effect="light" content="合同月份" placement="bottom-start">
          <el-input placeholder="关键字" v-model="searchForm.keyword"></el-input>
        </el-tooltip>
      </el-form-item>
      <div class="admin-search-btns">
        <el-button type="primary" icon="el-icon-search" @click="getData('searchForm')"
                   :loading="this.isShowLoadingIcon">查询
        </el-button>
        <el-button type="primary" icon="el-icon-plus" @click="addData">创建</el-button>
        <el-button type="primary" icon="el-icon-edit" @click="editData">修改</el-button>
        <el-button type="primary" icon="el-icon-delete" @click="deleteData">删除</el-button>
      </div>
    </el-form>
    <!--列表-->
    <el-table class="borderNone" ref="myTable" stripe border
                  :data="tableList"
                  style="width: 1180px!important;font-size:13px!important;"  highlight-current-row
                  @selection-change="handleSelectionChange">
      <el-table-column type="selection" align="center" width="30px"></el-table-column>
      <el-table-column prop="freeDayKind" sortable align="center" width="90px" label="品种"></el-table-column>
      <el-table-column prop="freeDayDate" sortable align="center" width="110px" label="合同月份"></el-table-column>
      <el-table-column prop="freeDay" sortable align="center" width="105px" label="免息天数"></el-table-column>
      <el-table-column prop="freeDayCustomer" sortable align="center" width="230px" label="客户名称"></el-table-column>
      <el-table-column prop="freeDayContract" sortable align="center" width="130px" label="合同签订日期" :formatter="dateFormat"></el-table-column>
      <el-table-column prop="freeDayY" sortable align="center" width="115px" label="免息天数Y"></el-table-column>
      <el-table-column prop="freeDayYInd" sortable align="center" width="170px" label="免息天数Y是否计算" :formatter="indYFormat"></el-table-column>
      <el-table-column prop="createTime" align="center" width="180px" label="创建时间"></el-table-column>
      <el-table-column prop="createUserId" align="center" width="120px" label="创建人" show-overflow-tooltip></el-table-column>
      <el-table-column prop="updateTime" align="center" width="180px" label="修改时间"></el-table-column>
      <el-table-column prop="updateUserId" align="center" width="120px" label="修改人" show-overflow-tooltip></el-table-column>
    </el-table>
    <!--免息天数字典管理新增修改-->
    <el-dialog
      :title="titleType=='add'?'新增免息天数字典':'修改免息天数字典'"
      :visible.syne="dialogVisible"
      width="40%"
      center :show-close="false">
      <el-form ref="dialogForm" :rules="dialogFormRules" :model="dialogForm"
               label-width="120px" labelPosition="right">
        <el-form-item label="品种:" prop="freeDayKind" style="margin-top: 16px">
          <el-select  v-model="dialogForm.freeDayKind" placeholder="请选择" clearable filterable>
            <el-option
              v-for="item in optionsFreeDayKind"
              :key="item.kind_name"
              :label="item.kind_name"
              :value="item.kind_name">
            </el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="合同月份:" prop="freeDayDate" style="margin-top: 16px">
          <el-input type="text" v-model="dialogForm.freeDayDate" clearable></el-input>
        </el-form-item>
        <el-form-item label="免息天数:" prop="freeDay" style="margin-top: 16px">
          <el-input-number type="text" v-model="dialogForm.freeDay" clearable :min="0"></el-input-number>
        </el-form-item>
        <el-form-item label="客户名称:" prop="freeDayCustomer" style="margin-top: 16px">
          <el-select  v-model="dialogForm.freeDayCustomer" placeholder="请选择" clearable filterable>
            <el-option
              v-for="item in optionsFreeDayCustomer"
              :key="item.customer_name"
              :label="item.customer_name"
              :value="item.customer_name">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="合同签订日期:" prop="freeDayContract" style="margin-top: 16px">
          <el-date-picker style="width: 150px" type="date" v-model="dialogForm.freeDayContract" @change="changeIndY()"
                          value-format="yyyy-MM-dd"
                          placeholder="合同签订日期"></el-date-picker>
          <el-checkbox v-model="checked" @change="changeIndY()">免息天数Y是否参与计算</el-checkbox>
        </el-form-item>
        <el-form-item label="免息天数Y:" prop="freeDayY" style="margin-top: 16px">
          <el-input type="text" v-model="dialogForm.freeDayY" clearable :disabled="true"></el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="confirmDialogForm('dialogForm')">确定</el-button>
        <el-button @click="closeDialogForm('dialogForm')">取消</el-button>
      </div>
    </el-dialog>
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
  import {parseTime} from '../utils/index'
  import * as Message from '../utils/messageUtil'
  import {pageMixin} from '../common/mixin'
  import {exportModel} from '../common/export'
  import _ from 'lodash'
  import moment from 'moment'


  export default {
    // 字典管理
    name: 'FreeDayDictList',
    mixins: [pageMixin, exportModel],
    data() {
      return {
        checked: false,
        searchForm: {
          keyword: ''
        },
        tableList: [],
        titleType: 'add',
        dialogVisible: false,
        dataExist: false,
        dialogForm: {},
        dialogFormRules: {
          freeDayKind: [
            {required: true, message: '请输入品种', trigger: 'blur' }
          ],
          freeDay: [
            {required: true, message: '请输入免息天数', trigger: 'blur'}
          ],
          freeDayDate: [
            {required: true, message: '请输入合同月份', trigger: 'blur'}
          ]
        },
        optionsFreeDayKind: [],
        optionsFreeDayCustomer: [],
        row: []
      }
    },
    methods: {
      // 获取数据
      getData() {
        this.isShowLoadingIcon = true;
        api.post('freeDayDict/list', this.searchForm).then(response => {
          if (response.data.code === 1) {
            let list = response.data.data.list
            for (var i = 0; i < list.length; i++) {
              if (list[i].updateUserId === '') {
                list[i].updateTime = ''
              }
            }
            this.updateTableData(response.data.data)
          }
        }).finally(() => {
          this.isShowLoadingIcon = false;
        })
      },
      // 获取当前行数据
      handleSelectionChange(val) {
        this.row = val;
      },
      //检查主键
      checkKey(keyValue) {
        api.get('freeDayDict/checkKey/' + keyValue).then(response => {
          if (response.data.code === 1) {
            this.dataExist = response.data.data;
          }else {
            Message.MessageError(response.data.msg);
          }
        })
      },
      // 创建
      addData() {
        this.titleType = 'add';
        this.dialogVisible = true;
        this.dialogForm = {
          freeDayKind: '',
          freeDayCustomer: '',
          freeDayDate: '',
          freeDay: '',
          freeDayY: '0',
          freeDayYInd: '0'
        };

      },
      // 修改
      editData() {
        if (this.row.length <= 0) {
          Message.MessageInfo('请选择要修改的单据!')
          return
        }
        if (this.row.length > 1) {
          Message.MessageInfo('一次只能修改一条单据!')
          return
        }
        this.dialogVisible = true;
        this.dialogForm = _.cloneDeep(this.row[0]);
        if (this.dialogForm.freeDayYInd == "0"){
          this.checked = false;
        }else {
          this.checked = true;
        }
        this.titleType = 'edit';
      },
      // 删除
      deleteData() {
        if (this.row == null || this.row.length <= 0) {
          Message.MessageInfo('请选择要删除的单据!')
        } else {
          let ids = []
          for (var i = 0; i < this.row.length; i++) {
            ids.push(this.row[i].freeDayId)
          }
          this.$confirm('确定删除该数据吗？', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning',
            confirmButtonClass: 'el-button--danger'
          }).then(() => {
            api.post('freeDayDict/deletes', ids).then(response => {
              if (response.data.code === 1) {
                Message.MessageSuccess(response.data.msg)
                this.getData()
              } else {
                Message.MessageError(response.data.msg)
              }
            })
          }).catch(() => {
          });
        }
      },

      // 确定
      confirmDialogForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            api.post('freeDayDict/save', this.dialogForm).then(response => {
              if (response.data.code === 1) {
                Message.MessageSuccess(response.data.msg);
                this.dialogVisible = false;
                this.getData()
              } else {
                Message.MessageError(response.data.msg)
              }
            })
          } else {
            return false;
          }
        });
      },
      // 取消
      closeDialogForm(formName) {
        this.dialogVisible = false;
        this.$refs[formName].resetFields();
      },

      // 获取查询条件中的品种、客户
      getQueryInfo() {
        // 品种下拉
        api.get('/freeDayDict/getKindDictSelect').then(response => {
          if (response.data.code === 1) {
            this.optionsFreeDayKind = response.data.data;
          } else {
            Message.MessageError(response.data.msg)
          }
        });
        // 客户下拉
        api.get('/freeDayDict/getCustomerDictSelect').then(response => {
          if (response.data.code === 1) {
            this.optionsFreeDayCustomer = response.data.data;
          } else {
            Message.MessageError(response.data.msg)
          }
        });
      },



      //时间格式化
      dateFormat:function(row, column) {
        var date = row[column.property];
        if (date == undefined) {
        return "";
        }
        return moment(date).format("YYYY-MM-DD");
       },

       //Y值是否计算格式化
       indYFormat:function(val) {
        if( val.freeDayYInd == "1" ){
          return "计算";
        }else {
          return "否";
        }
       },

       //计算间隔天数
       truncateDays( dateStart , dateEnd ) {
          var aDay = 24 * 60 * 60 * 1000; // 完整一天有24小时，将小时换算成毫秒时间戳
          var diffDay = (dateEnd - dateStart) / aDay; // 计算间隔天数（注意日期隐式转换）
          return Math.floor( diffDay );
       },

      /**
      * 获取下一个月
      *
      * @date 格式为yyyy-mm-dd的日期，如：2014-01-25
      */
      getNextMonth(date) {
         var arr = date.split('-');
         var year = arr[0]; //获取当前日期的年份
         var month = arr[1]; //获取当前日期的月份
         var day = arr[2]; //获取当前日期的日
         var days = new Date(year, month, 0);
         days = days.getDate(); //获取当前日期中的月的天数
         var year2 = year;
         var month2 = parseInt(month) + 1;
         if (month2 == 13) {
             year2 = parseInt(year2) + 1;
             month2 = 1;
         }
         var day2 = day;
         var days2 = new Date(year2, month2, 0);
         days2 = days2.getDate();
         if (day2 > days2) {
             day2 = days2;
         }
         if (month2 < 10) {
             month2 = '0' + month2;
         }

         var t2 = year2 + '-' + month2 + '-15';
         return t2;
      },

      //格式化字符串到yyyy-mm-dd
      formatDate(date) {
        var d = new Date(date),
          month = '' + (d.getMonth() + 1),
          day = '' + d.getDate(),
          year = d.getFullYear();

        if (month.length < 2) month = '0' + month;
        if (day.length < 2) day = '0' + day;

        return [year, month, day].join('-');
      },


       //复选框
       changeIndY() {
          if (this.checked == true){
            this.dialogForm.freeDayYInd = '1';

            //判断日期格式正确性
            var a = /^(\d{4})-(\d{2})-(\d{2})$/;
            if (!a.test(this.formatDate( this.dialogForm.freeDayContract ))){
              this.dialogForm.FreeDayY = 0;
            }else {
              //计算间隔点数 Y
              var dateStart = new Date(this.dialogForm.freeDayContract);
              var dateEnd   = new Date( this.getNextMonth( this.formatDate( this.dialogForm.freeDayContract ) ) );
              var days = this.truncateDays(dateStart,dateEnd);

              this.dialogForm.freeDayY = days;
            }

          }else {
            this.dialogForm.freeDayYInd = '0';
            this.dialogForm.freeDayY = '0';
            //this.dialogForm.freeDayContract = '';
          }

       }



     },

    mounted() {
      this.getQueryInfo();
      this.getData();
    }
  }
</script>

<style scoped>

</style>
