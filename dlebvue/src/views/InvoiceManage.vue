<template>
  <div class="aaa">
    <!--查询条件-->
    <el-form :inline="true" :model="searchForm" class="admin-search-form" align="left">
      <el-form-item label="创建时间:">
        <el-col :span="8" style="width: 150px">
          <el-date-picker style="width: 140px" type="date" v-model="searchForm.startTime" id="startTime"
                          format="yyyy-MM-dd" value-format="yyyy-MM-dd" placeholder="选择日期"></el-date-picker>
        </el-col>
        <el-col :span="1" style="width: 25px">至</el-col>
        <el-col :span="8" style="width: 150px">
          <el-date-picker style="width: 140px" type="date" v-model="searchForm.endTime" id="endTime"
                          format="yyyy-MM-dd" value-format="yyyy-MM-dd" placeholder="选择日期"></el-date-picker>
        </el-col>
      </el-form-item>

      <el-form-item label="状态:">
        <el-select class="el-select" v-model="searchForm.state" placeholder="全部" clearable filterable>
          <el-option
            v-for="item in optionsStatus"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="关键字:">
        <el-tooltip class="item" effect="light" content="关键字查询【购方名称、购方税号、购方银行帐号】" placement="bottom-start">
          <el-input placeholder="关键字" v-model="searchForm.keyword"></el-input>
        </el-tooltip>
      </el-form-item>
      <div class="admin-search-btns">
        <el-button type="primary" icon="el-icon-refresh" @click="generateXml">生成xml文件</el-button>
        <el-button type="primary"  @click="getData()" :loading="this.isShowLoadingIcon">查询</el-button>
        <el-button type="primary"  @click="refreshList">刷新</el-button>
        <!--<el-button type="primary" icon="el-icon-refresh" @click="refreshList">导入</el-button>-->

        <el-button type="primary" @click="dialogFormVisible1 = true">导入</el-button>

        <!--导入窗体-->
        <el-dialog title="请选择Excel文件导入" :visible.sync="dialogFormVisible1">
          <el-upload ref="impFile" action="" :file-list="uploadFile" :show-file-list="true" :limit="2"
                     :on-change="impFile" :auto-upload="false" style="text-align: center">

            <img v-if="uploadFile[0]" src="/static/img/excel.jpg" class="avatar" >
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible1 = false">取 消</el-button>
            <el-button type="primary" @click="selectUpload()">确 定</el-button>
          </div>
        </el-dialog>

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
      <el-table-column type="selection" width="40px" align="center"></el-table-column>
      <el-table-column prop="djh" label="单据号" align="center" width="110px">
        <template slot-scope="scope">
          <a style="cursor:pointer;color: #409EFF;"
             @click="showDetail(scope.row)">{{scope.row.djh}}</a>
        </template>
      </el-table-column>
      <el-table-column prop="state" label="状态" align="center" :formatter="formatType"></el-table-column>
      <el-table-column prop="gfmc" label="购方名称" align="center" width="180px"></el-table-column>
      <el-table-column prop="gfsh" label="购方税号" align="center" width="150px"></el-table-column>
      <el-table-column prop="gfyhzh" label="购方银行账号" align="center" width="210px"></el-table-column>
      <el-table-column prop="gfdzdh" label="购方地址电话" align="center" width="210px"></el-table-column>
      <el-table-column prop="bz" label="备注" align="center"></el-table-column>
      <el-table-column prop="fhr" label="复核人" align="center"></el-table-column>
      <el-table-column prop="skr" label="收款人" align="center"></el-table-column>
      <el-table-column prop="spbmbbh" label="商品编码版本号" align="center" width="120px"></el-table-column>
      <el-table-column prop="hsbz" label="含税标志" align="center"></el-table-column>

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
    name: 'InvoiceManage',
    mixins: [pageMixin],
    data() {
      return {
        dialogFormVisible1: false,
        uploadFile: [],

        searchForm: {  },
        isReadOnly: false,
        tableList: [],
        //状态
        optionsStatus:[
          { value: '0',label: '待处理'},
          { value: '1',label: '已处理'}
        ],

        dialogVisibleDisable: false,
        dialogVisibleEnable: false,
        dialogVisibleAdd: false,
        multipleSelection: [],
        roleSelection: [],
        teamSelection: []
      }
    },
    methods: {
      // 获取数据
      getData() {
        api.post('invoiceManage/getFpMainList', this.searchForm).then(response => {
          if (response.data.code === 1) {
          this.updateTableData(response.data.data)
        } else {
          Message.MessageError('列表获取失败')
        }
      }).finally(() => {
          this.isShowLoadingIcon = false;
      })
      },
      // 转换会员状态
      formatType(row, column) {
        var str = "";
        if(row.state == "0") {str= "待处理";}
        if(row.state == "1") {str= "已处理";}
        return str;
      },


      refreshList() {
        this.getData();
      },

      //=====================================================
      //选择上传文件
      selectUpload() {
        if (this.uploadFile.length !== 1) {  Message.MessageError('请选择文件') }
        //构建上传
        var formData = new FormData();
        formData.append('impFile', this.uploadFile[0].raw);
        api.post('invoiceManage/imp', formData).then(response => {
          this.dialogFormVisible1 = false;

         this.$message({
          message: response.data.data,
          type: 'success'
        });
          this.getData();
        })
      },
      //选择文件,准备上传
      impFile(file, fileList) {
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isLt2M) {
          this.$message.error('上传文件大小不能超过 2MB!');
        }
        this.uploadFile.pop();
        this.uploadFile.push(file);
      },
      showDetail(row) {
        this.$router.push(`/InvoiceManageDetail/${row.djh}`)
      },

      //生成xml
      generateXml(){
          var djhs = [];
          for(var i=0;i<this.multipleSelection.length;i++){
                djhs.push(this.multipleSelection[i].djh);
          }
          if(djhs.length>0){

            this.httpPost(process.env.BASE_API+"/invoiceManage/generateXml", djhs,"djh");
            this.getData();
            /*api.get('invoiceManage/generateXml', {djhs:djhs}).then(response => {
              var url = window.URL.createObjectURL(new Blob([response.data]));   //{type: "application/xml"}
              var link = document.createElement('a');
                  link.style.display = 'none';
                  link.href = url;
                  link.setAttribute('download', '发票管理.xml');

                  document.body.appendChild(link);
                  link.click();

                  this.getData();
              })*/
          }else{
                this.$alert('请选择数据再进行导出', '提示', {
                    confirmButtonText: '确定',
                    callback: action => {
                }
              });
          }
      },

      // 获取选中的数据
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },

    httpPost(URL, PARAMS,field) {
          var temp = document.createElement("form");
              //temp.setRequestHeader("token",sessionStorage.getItem('dleb_token'));
              temp.action = URL;
              temp.method = "post";
              temp.style.display = "none";

              for (var x in PARAMS) {
                  var opt = document.createElement("input");
                      opt.name = field;
                      opt.value = PARAMS[x];
                      temp.appendChild(opt);
              }
              document.body.appendChild(temp);
              temp.submit(); return temp;
      }

    },
    mounted() {
      this.getData();
    }
  }
</script>

<style scoped>

</style>
