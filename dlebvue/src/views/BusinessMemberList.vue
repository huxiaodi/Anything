<template>
  <div>
    <!--查询条件-->
    <el-form :inline="true" :model="searchForm" class="admin-search-form" align="left">
      <el-form-item label="注册时间:">
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
      <el-form-item label="会员状态:">
        <el-select v-model="searchForm.status" placeholder="请选择" filterable clearable>
          <el-option
            v-for="item in optionsStatus"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="注册来源:">
        <el-select v-model="searchForm.source" placeholder="请选择" filterable clearable>
          <el-option
            v-for="item in optionsSource"
            :key="item.dictKey"
            :label="item.dictValue"
            :value="item.dictKey">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="证件类型:">
        <el-select v-model="searchForm.type" placeholder="请选择" filterable clearable>
          <el-option
            v-for="item in optionsType"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="关键字:">
        <el-tooltip class="item" effect="light" content="用户名、绑定手机号、企业名称、证件号码、联系人、纳税人识别号" placement="bottom-start">
          <el-input v-model="searchForm.keyword" placeholder="关键字" clearable></el-input>
        </el-tooltip>
      </el-form-item>
      <div class="admin-search-btns">
        <el-button type="primary" icon="el-icon-search" @click="getData" :loading="this.isShowLoadingIcon">查询
        </el-button>
        <el-button type="primary" icon="el-icon-edit" @click="editUser">修改</el-button>
        <el-button type="primary" icon="el-icon-close" @click="disableUser">禁用</el-button>
        <el-button type="primary" icon="el-icon-check" @click="enableUser">启用</el-button>
        <el-button type="primary" icon="el-icon-refresh" @click="refreshList">刷新</el-button>
      </div>
    </el-form>
    <!--列表-->
    <el-table ref="myTable" stripe border
              :data="tableList"
              :max-height="500"
              :height="400"
              style="width: 100%"
              show-summary
              highlight-current-row
              :summary-method="getSummaries"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="70px" align="center"></el-table-column>
      <el-table-column prop="user_create_time" label="注册时间" align="center" width="180px"></el-table-column>
      <el-table-column prop="user_name" label="用户名" align="center" width="180px">
        <template slot-scope="scope">
          <a style="cursor:pointer;color: #409EFF;" @click="viewDetails(scope.row)">
            {{scope.row.user_name}}</a>
        </template>
      </el-table-column>
      <el-table-column prop="user_mobile" label="绑定手机号" align="center" width="150px"></el-table-column>
      <el-table-column prop="company_name" label="企业名称" align="center" width="150px"
                       show-overflow-tooltip></el-table-column>
      <el-table-column prop="user_certificate_typeC" label="证件类型" align="center" width="150px"></el-table-column>
      <el-table-column prop="user_certificate_number" label="证件号码" align="center"
                       show-overflow-tooltip width="180px"></el-table-column>
      <el-table-column prop="user_statusC" label="会员状态" align="center" width="150px"></el-table-column>
      <el-table-column prop="dict_value" label="注册来源" align="center" width="150px"></el-table-column>
      <el-table-column prop="company_contact" label="联系人姓名" align="center" width="150px"></el-table-column>
      <el-table-column prop="company_principal_id_code" label="纳税人识别号" align="center"
                       show-overflow-tooltip width="180px"></el-table-column>
      <el-table-column prop="user_id_card_back_img" align="center" width="150%" label="附件">
        <template slot-scope="scope">
          <a style="cursor:pointer;color: #409EFF;" @click="dialogImage(scope.row)">证件</a>
        </template>
      </el-table-column>
    </el-table>

    <!--查看附件-->
    <el-dialog
      :visible.sync="dialogImgVisible"
      width="30%"
      center>
      <div v-if="this.companyIsThreeInOne">
        统一社会信用照片:
        <img :src="dialogImageUrlT">
      </div>
      <div v-if="!this.companyIsThreeInOne">
        营业执照照片:
        <img :src="this.dialogImageUrlY">
        税务登记证照片:
        <img :src="this.dialogImageUrlS">
        组织结构代码照片:
        <img :src="this.dialogImageUrlZ">
      </div>
    </el-dialog>
    <!--禁用的弹出框-->
    <el-dialog
      title="会员禁用"
      :visible.sync="dialogVisible"
      width="30%"
      center>
      <el-form ref="dialogForm" :rules="dialogForm" :model="dialogForm"
               label-width="110px">
        <el-form-item label="禁用原因:" prop="keyword">
          <el-input v-model="dialogForm.keyword" maxlength="100"></el-input>
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
  import * as Message from '../utils/messageUtil'
  import {pageMixin} from '../common/mixin.js'

  export default {
    // 企业会员管理
    name: 'BusinessMemberList',
    mixins: [pageMixin],
    data() {
      return {
        searchForm: {
          keyword: '',
          status: '',
          source: '',
          type: '',
          startTime: '',
          endTime: ''
        },
        tableList: [],
        isDisable: false,
        multipleSelection: [],
        optionsStatus: [{
          value: '',
          label: '全部'
        }, {
          value: '1',
          label: '正常'
        }, {
          value: '0',
          label: '禁用'
        }],
        optionsSource: [{
          value: '',
          label: '全部'
        }, {
          value: '德邻e宝',
          label: '德邻e宝'
        }, {
          value: '德邻畅途',
          label: '德邻畅途'
        }, {
          value: '德邻云仓',
          label: '德邻云仓'
        }],
        optionsType: [{
          value: '',
          label: '全部'
        }, {
          value: '68',
          label: '营业执照'
        }, {
          value: '5',
          label: '统一信用代码'
        }],
        // 控制禁用弹出框显示不显示
        dialogVisible: false,
        dialogForm: {
          keyword: '',
          id: ''
        },
        // 控制查看附件弹出框显示不显示
        dialogImgVisible: false,
        dialogImageUrlT: '',
        dialogImageUrlY: '',
        dialogImageUrlS: '',
        dialogImageUrlZ: '',
        companyIsThreeInOne: ''
      }
    },
    methods: {
      // 获取数据
      getData() {
        this.isShowLoadingIcon = true;
        api.post('admin/companyManage/list', this.searchForm).then(response => {
          if (response.data.code === 1) {
            let list = response.data.data.list;
            list.forEach((item) => {
              if (item.user_status === '1') {
                item.user_statusC = '正常';
              } else {
                item.user_statusC = '禁用';
              }
              // let obj = {'1': '身份证', '2': '护照', '5': '统一信用代码', '68': '营业执照'}
              // item.user_certificate_typeC = obj[item.user_certificate_type] || item.user_certificate_type;
              if (item.company_is_three_in_one === true) {
                item.user_certificate_number = item.company_unified_social_credit_ldentifier;
                item.user_certificate_typeC = '统一信用代码';
              } else {
                item.user_certificate_number = item.company_license_no;
                item.user_certificate_typeC = '营业执照';
              }
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
      },
      // 设置合计
      getSummaries(param) {
        const {columns, data} = param;
        const sums = [];
        columns.forEach((column, index) => {
          if (index === 0) {
            sums[index] = `合计:`;
            return;
          }
          if (index === 1) {
            sums[index] = `${this.tableList.length}`;
            return;
          }
          const values = data.map(item => item[column.property]);
          if (values.every(value => typeof value === 'number')) {
            sums[index] = values.reduce((prev, curr) => {
              if (typeof curr === 'number') {
                return prev + curr;
              } else {
                return prev;
              }
            }, 0);
          }
        });
        return sums;
      },
      // 获取选中的数据
      handleSelectionChange(val) {
        this.multipleSelection = val;
      },
      // 点击用户名查看详情页面
      viewDetails(row) {
        this.$router.push({name: '企业会员详情', params: row})
      },
      // 修改
      editUser() {
        if (this.multipleSelection.length <= 0) {
          Message.MessageError('请选择要修改的单据!')
          return
        }
        if (this.multipleSelection.length > 1) {
          Message.MessageError('一次只能修改一条单据!')
          return
        }
        // this.$router.push(`/BusinessMemberEdit/${this.multipleSelection[0].company_id}`)
        this.$router.push({name: '企业会员修改', params: this.multipleSelection[0]})
      },
      // 启用
      enableUser() {
        if (this.multipleSelection.length <= 0) {
          Message.MessageError('请选择要启用的会员!')
          return
        }
        if (this.multipleSelection.length > 1) {
          Message.MessageError('一次只能启用一条会员!')
          return
        }
        for (let i = 0; i < this.multipleSelection.length; i++) {
          let item = this.multipleSelection[i];
          if (item.user_statusC === '正常') {
            Message.MessageError('该用户已启用，请勿重复操作!')
            return
          }
        }
        this.$confirm('确定重新启用该会员吗？', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
          confirmButtonClass: 'el-button--danger'
        }).then(() => {
//            let ids = [];
//            this.multipleSelection.forEach((item, index) => {
//              ids.push(item.user_id);
//            });
          api.post('/admin/userManage/switch/enable', {'id': this.multipleSelection[0].user_id}).then((response) => {
            if (response.data.code === 1) {
              Message.MessageSuccess('启用成功');
              // 刷新列表数据
              this.getData();
            } else {
              Message.MessageError(response.data.msg);
            }
          });
        }).catch(() => {
        });
      },
      // 禁用
      disableUser(row) {
        if (this.multipleSelection.length <= 0) {
          Message.MessageError('请选择要禁用的会员!')
          return
        }
        if (this.multipleSelection.length > 1) {
          Message.MessageError('一次只能禁用一条会员!')
          return
        }
        for (let i = 0; i < this.multipleSelection.length; i++) {
          let item = this.multipleSelection[i];
          if (item.user_statusC === '禁用') {
            Message.MessageError('该用户已禁用，请勿重复操作!')
            return
          }
        }
        this.dialogForm.id = this.multipleSelection[0].user_id;
        this.dialogVisible = true
      },
      // 刷新
      refreshList(row) {
        this.getData();
      },
      // 禁用弹框的确认按钮
      confirmDialogForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            api.post('/admin/userManage/switch/disable', this.dialogForm).then((response) => {
              if (response.data.code === 1) {
                Message.MessageSuccess(response.data.msg);
                // 禁用成功之后关闭弹窗
                this.dialogVisible = false;
                // 重新查询列表
                this.getData();
                this.$refs[formName].resetFields();
              } else {
                Message.MessageError(response.data.msg);
              }
            });
          }
        });
      },
      // 禁用弹框的取消按钮
      closeDialogForm(formName) {
        this.dialogVisible = false;
        this.$refs[formName].resetFields();
      },
      // 查看附件
      dialogImage(row) {
        this.dialogImgVisible = true;
        // 统一社会信用照片
        this.dialogImageUrlT = row.company_unified_social_img;
        // 营业执照照片
        this.dialogImageUrlY = row.company_license_no_img;
        // 税务登记证照片
        this.dialogImageUrlS = row.company_tax_no_img;
        // 组织结构代码照片
        this.dialogImageUrlZ = row.company_organization_code_img;
        this.companyIsThreeInOne = row.company_is_three_in_one;
      },
      show() {
        var homePage = this.$route.params.homePage;
        if (homePage !== undefined) {
          this.searchForm.status = homePage.status;
          this.searchForm.startTime = homePage.startTime;
          this.searchForm.endTime = homePage.endTime;
        }
      }
    },
    mounted() {
      // this.setDate()
      // this.show()
      this.getQueryInfo();
      this.getData();
    }
  }
</script>

<style scoped>
</style>
