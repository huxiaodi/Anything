<template>
  <div>
    <!--查询条件-->
    <el-form :inline="true" :model="searchForm" class="admin-search-form" align="left">
      <el-form-item label="注册时间:">
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
      <el-form-item label="会员状态:">
        <el-select class="el-select" v-model="searchForm.status" placeholder="全部" clearable filterable>
          <el-option
            v-for="item in optionsMemberStatus"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="注册来源:">
        <el-select class="el-select" v-model="searchForm.source" placeholder="全部" clearable filterable>
          <el-option
            v-for="item in optionsSource"
            :key="item.dictKey"
            :label="item.dictValue"
            :value="item.dictKey">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="证件类型:">
        <el-select class="el-select" v-model="searchForm.type" placeholder="全部" clearable filterable>
          <el-option
            v-for="item in optionsDocumentType"
            :key="item.dictKey"
            :label="item.dictValue"
            :value="item.dictKey">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="关键字:">
        <el-tooltip effect="light" content="关键字查询支持用户名、绑定手机号、真实姓名、证件号码查询"  placement="bottom-start">
          <el-input placeholder="关键字" v-model="searchForm.keyword" clearable></el-input>
        </el-tooltip>
      </el-form-item>
      <div class="admin-search-btns">
        <el-button type="primary" icon="el-icon-search" @click="queryData"
                   :loading="this.isShowLoadingIcon">查询
        </el-button>
        <el-button type="primary" icon="el-icon-edit" @click="editData">修改</el-button>
        <el-button type="primary" icon="el-icon-close" @click="getDisable">禁用</el-button>
        <el-button type="primary" icon="el-icon-check" @click="getEnable">启用</el-button>
        <el-button type="primary" icon="el-icon-refresh" @click="refreshData"
                   :loading="this.isShowLoadingIcon">刷新
        </el-button>
      </div>

    </el-form>
    <!--列表-->
    <el-table ref="myTable" stripe border
              :data="tableList"
              :max-height="500"
              :height="400"
              highlight-current-row
              @selection-change="handleSelectionChange"
              style="width: 100%">
      <el-table-column type="selection" align="center" width="80%"></el-table-column>
      <el-table-column prop="user_create_time" align="center" width="180px" label="注册时间"></el-table-column>
      <el-table-column prop="user_name" align="center" label="用户名"></el-table-column>
      <el-table-column prop="user_mobile" align="center" label="绑定手机号"></el-table-column>
      <el-table-column prop="user_cn_name" align="center" label="真实姓名"></el-table-column>
      <el-table-column prop="user_certificate_typeC" align="center" label="证件类型"></el-table-column>
      <el-table-column prop="user_certificate_number" align="center" width="180px" label="证件号码"></el-table-column>
      <el-table-column prop="status" align="center" label="会员状态"></el-table-column>
      <el-table-column prop="dict_value" align="center" label="注册来源"></el-table-column>
      <el-table-column prop="user_id_card_back_img" align="center" label="附件">
        <template slot-scope="scope">
          <a style="cursor:pointer;" @click="dialogImage(scope.row)">身份证证件</a>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog
      :visible.sync="dialogVisible"
      width="30%">
      <!--<div><img src="../assets/img/1.png"></div>-->
      <div><img :src="this.dialogImageUrlFont"></div>
      <br/>
      <!--<div><img src="../assets/img/2.jpg"></div>-->
      <div><img :src="this.dialogImageUrlBack"></div>
    </el-dialog>
    <el-dialog
      title="用户禁用"
      :visible.sync="dialogVisibleDisable"
      width="30%"
      center
      style="font-size: large;font-weight: bold;">
      <div style="font-size: large; text-align: center">
        <el-form ref="dialogForm" :rules="dialogForm" :model="dialogForm"
                 label-width="110px">
          <el-form-item label="禁用原因" prop="keyword">
            <el-input v-model="dialogForm.keyword"></el-input>
          </el-form-item>
        </el-form>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="setDisable('dialogForm')">确 定</el-button>
        <el-button @click="closeDialogForm('dialogForm')">取 消</el-button>
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

  export default {
    // 个人会员管理
    name: 'PersonalMemberList',
    mixins: [pageMixin, exportModel],
    data() {
      return {
        searchForm: {
          status: '',
          source: '',
          type: '',
          keyword: ''
        },
        optionsMemberStatus: [{
          value: null,
          label: '全部'
        }, {
          value: '1',
          label: '正常'
        }, {
          value: '0',
          label: '禁用'
        }],
        optionsSource: [],
        optionsDocumentType: [{
          value: null,
          label: '全部'
        }, {
          value: '1',
          label: '身份证'
        }, {
          value: '2',
          label: '护照'
        }],
        tableList: [],
        multipleSelection: [],
        dialogVisible: false,
        dialogVisibleDisable: false,
        dialogImageUrlFont: '',
        dialogImageUrlBack: '',
        dialogForm: {
          keyword: '',
          id: ''
        }
      }
    },
    methods: {
      // 获取数据
      getData() {
        this.isShowLoadingIcon = true;
        api.post('admin/userManage/list', this.searchForm).then(response => {
          if (response.data.code === 1) {
            let list = response.data.data.list;
            list.forEach((item) => {
              // 会员状态
              if (item.user_status === '0') {
                item.status = '禁用'
              } else if (item.user_status === '1') {
                item.status = '正常'
              }
              let obj = {'1': '身份证', '2': '护照', '5': '统一信用代码', '68': '营业执照'}
              item.user_certificate_typeC = obj[item.user_certificate_type] || item.user_certificate_type;
            })
            this.tableList = list;
            this.pageSettings.total = response.data.data.total;
          }
        }).finally(() => {
          this.isShowLoadingIcon = false;
        })
      },
      // 获取查询条件中的下拉列表
      getQueryInfo() {
        // 注册来源
        api.get('/dictManager/getBusinesTradeTypeDict').then(response => {
          if (response.data.code === 1) {
            this.optionsSource = response.data.data;
          } else {
            Message.MessageError(response.data.msg)
          }
        });
        // 证件类型
        api.get('/dictManager/getDict?type=证件类型字典').then(response => {
          if (response.data.code === 1) {
            this.optionsDocumentType = response.data.data;
          } else {
            Message.MessageError(response.data.msg)
          }
        });
      },
      // 获取当前行数据
      handleSelectionChange(val) {
        this.multipleSelection = val
      },
      queryData() {
        this.getData()
      },
      // 修改
      editData() {
        if (this.multipleSelection.length <= 0) {
          Message.MessageInfo('请选择要修改的单据!')
          return
        }
        if (this.multipleSelection.length > 1) {
          Message.MessageInfo('一次只能修改一条单据!')
          return
        }
        this.$router.push(`/MemberInfoEdit/${this.multipleSelection[0].user_id}`)
      },
      // 禁用
      getDisable() {
        if (this.multipleSelection.length <= 0) {
          Message.MessageInfo('请选择要禁用的单据!')
          return
        }
        if (this.multipleSelection.length > 1) {
          Message.MessageInfo('一次只能禁用一条单据!')
          return
        }
        if (this.multipleSelection[0].status === '禁用') {
          Message.MessageError('该用户已禁用，请勿重复操作')
          return
        }
        this.dialogForm.id = this.multipleSelection[0].user_id;
        this.dialogVisibleDisable = true
      },
      setDisable(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            api.post('admin/userManage/switch/disable', this.dialogForm).then(response => {
              if (response.data.code === 1) {
                Message.MessageSuccess(response.data.msg)
                this.dialogVisibleDisable = false
                this.getData()
                this.$refs[formName].resetFields();
              } else {
                Message.MessageError(response.data.msg)
              }
            })
          }
        });
      },
      // 禁用弹框的取消按钮
      closeDialogForm(formName) {
        this.dialogVisibleDisable = false;
        this.$refs[formName].resetFields();
      },
      // 启用
      getEnable() {
        if (this.multipleSelection.length <= 0) {
          Message.MessageInfo('请选择要启用的单据!')
          return
        }
        if (this.multipleSelection.length > 1) {
          Message.MessageInfo('一次只能启用一条单据!')
          return
        }
        if (this.multipleSelection[0].status === '正常') {
          Message.MessageError('该用户已启用，请勿重复操作')
          return
        }
        this.$confirm('确定重新启用该会员吗？', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
          confirmButtonClass: 'el-button--danger'
        }).then(() => {
          api.post('admin/userManage/switch/enable', {'id': this.multipleSelection[0].user_id}).then(response => {
            if (response.data.code === 1) {
              Message.MessageSuccess(response.data.msg)
              this.getData()
            } else {
              Message.MessageError(response.data.msg)
            }
          })
        }).catch(() => {
        });
      },
      // 刷新
      refreshData() {
        this.getData()
      },
      // 显示证件图片
      dialogImage(row) {
        this.dialogVisible = true
        this.dialogImageUrlFont = row.user_id_card_font_img
        this.dialogImageUrlBack = row.user_id_card_back_img
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
