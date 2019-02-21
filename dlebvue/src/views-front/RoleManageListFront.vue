<template>
  <div class="page1180">
    <div class="page-content">
     <!--查询条件-->
     <el-form :inline="true" :model="searchForm" class="admin-search-form" align="left">
       <el-form-item label="创建时间:">
         <el-col :span="8" style="width: 150px">
           <el-date-picker style="width: 140px" type="date" v-model="searchForm.startTime"
                           placeholder="选择日期"></el-date-picker>
         </el-col>
         <el-col :span="1" style="width: 25px">至</el-col>
         <el-col :span="8" style="width: 150px">
           <el-date-picker style="width: 140px" type="date" v-model="searchForm.endTime"
                           placeholder="选择日期"></el-date-picker>
         </el-col>
       </el-form-item>
       <el-form-item label="关键字:">
         <el-tooltip class="item" effect="light" content="角色名" placement="bottom-start">
           <el-input v-model="searchForm.keyword" placeholder="关键字"></el-input>
         </el-tooltip>
       </el-form-item>
       <div class="admin-search-btns">
         <el-button type="primary" icon="el-icon-search" @click="getData" :loading="this.isShowLoadingIcon"
                    v-show="_btn('qiantaijueseguanli_query')">查询
         </el-button>
         <el-button type="primary" icon="el-icon-plus" @click="addBefore" v-show="_btn('qiantaijueseguanli_add')">新增
         </el-button>
         <el-button type="primary" icon="el-icon-edit" @click="editBefore" v-show="_btn('qiantaijueseguanli_update')">
           修改
         </el-button>
         <el-button type="primary" icon="el-icon-delete" @click="deleteRole" v-show="_btn('qiantaijueseguanli_delete')">
           删除
         </el-button>
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
       <el-table-column prop="roleCreateTime" label="创建时间" align="center"></el-table-column>
       <el-table-column prop="roleName" label="角色名" align="center"></el-table-column>
       <el-table-column prop="roleEnName" label="角色英文名" align="center"></el-table-column>
       <el-table-column prop="roleIndex" label="排序" align="center"></el-table-column>
       <el-table-column prop="userCnName" label="创建人" align="center"></el-table-column>
       <el-table-column label="操作" align="center">
         <template slot-scope="scope">
           <el-button type="text" @click="editTableBefore(scope.row)">修改</el-button>
         </template>
       </el-table-column>
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

     <el-dialog title="新增角色" :visible.sync="dialogFormVisible1">
       <el-form ref="form" :model="form" labelWidth="110px" labelPosition="right" style="margin-top: 10px">
         <el-row>
           <el-col :span="12">
             <el-row>
               <el-col :xs="24" :sm="20" :offset="1">
                 <el-form-item label="角色名:" prop="roleName">
                   <el-input v-model="form.roleName"></el-input>
                 </el-form-item>
               </el-col>
             </el-row>
             <el-row>
               <el-col :xs="24" :sm="20" :offset="1">
                 <el-form-item label="角色英文名:" prop="roleEnName">
                   <el-input v-model="form.roleEnName"></el-input>
                 </el-form-item>
               </el-col>
             </el-row>
             <el-row>
               <el-col :xs="24" :sm="20" :offset="1" prop="roleIndex">
                 <el-form-item label="排序:">
                   <el-input v-model="form.roleIndex"></el-input>
                 </el-form-item>
               </el-col>
             </el-row>
           </el-col>
           <el-col :span="12">
             <el-tree :data="treeData "
                      node-key="id"
                      ref="addtree"
                      :default-expand-all="true"
                      show-checkbox></el-tree>
           </el-col>
         </el-row>
         <el-form-item>
           <el-button type="primary" size="medium" @click="addRole('form')">确认</el-button>
           <el-button type="primary" size="medium" @click="dialogFormVisible1 = false">取消</el-button>
         </el-form-item>
       </el-form>
     </el-dialog>

     <el-dialog title="修改角色" :visible.sync="dialogFormVisible2">
       <el-form ref="test" :model="test" labelWidth="110px" labelPosition="right" style="margin-top: 10px">
         <el-row>
           <el-col :span="12">
             <el-row>
               <el-col :xs="24" :sm="20" :offset="1">
                 <el-form-item label="角色名:" prop="roleName">
                   <el-input v-model="test.roleName"></el-input>
                 </el-form-item>
               </el-col>
             </el-row>
             <el-row>
               <el-col :xs="24" :sm="20" :offset="1">
                 <el-form-item label="角色英文名:" prop="roleEnName">
                   <el-input v-model="test.roleEnName"></el-input>
                 </el-form-item>
               </el-col>
             </el-row>
             <el-row>
               <el-col :xs="24" :sm="20" :offset="1" prop="roleIndex">
                 <el-form-item label="排序:">
                   <el-input v-model="test.roleIndex"></el-input>
                 </el-form-item>
               </el-col>
             </el-row>
           </el-col>
           <el-col :span="12">
             <el-tree :data="treeData "
                      node-key="id"
                      ref="changetree"
                      :default-checked-keys="checkedData"
                      :default-expand-all="true"
                      :check-strictly="true"
                      show-checkbox></el-tree>
           </el-col>
         </el-row>
         <el-form-item>
           <el-button type="primary" size="medium" @click="editRole('test')">确认</el-button>
           <el-button type="primary" size="medium" @click="dialogFormVisible2 = false">取消</el-button>
         </el-form-item>
       </el-form>
     </el-dialog>

     <el-dialog title="是否确认删除" :visible.sync="dialogFormVisible3">
       <el-form ref="form" :model="test" labelWidth="110px" labelPosition="right" style="margin-top: 10px">
         <el-form-item>
           <el-button type="primary" size="medium" @click="this.delete">确认</el-button>
           <el-button type="primary" size="medium" @click="dialogFormVisible3 = false">取消</el-button>
         </el-form-item>
       </el-form>
     </el-dialog>

   </div>
  </div>
</template>

<script>
  import api from '../api/fetch'
  import * as Message from '../utils/messageUtil'
  import {pageMixin} from '../common/mixin.js'

  var qs = require('qs');
  // 纯数字
  var numberReg = /^[0-9]*$/;
  export default {
    // 角色管理
    name: 'RoleManageList',
    mixins: [pageMixin],
    data() {
      return {
        // 权限树
        props: {
          //  配置父子树的属性名
          label: 'parent',
          children: 'sub'
        },
        treeData: [],
        checkedData: [],
        searchForm: {
          roleId: '',
          keyword: ''
        },
        test: {
          roleId: '',
          roleName: '',
          roleEnName: '',
          roleIndex: '',
          resourceIds: []
        },
        form: {
          roleName: '',
          roleEnName: '',
          roleIndex: '',
          resourceIds: []
        },
        dialogFormVisible1: false,
        dialogFormVisible2: false,
        dialogFormVisible3: false,
        tableList: [],
        multipleSelection: []
      }
    },
    methods: {
      // 获取数据
      getData() {
        api.post('admin/roleManage/roleListFront', this.searchForm).then(response => {
          if (response.data.code === 1) {
            this.updateTableData(response.data.data)
          }
        })
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
      // 新增页面
      addBefore() {
        this.dialogFormVisible1 = true
        api.get('admin/roleManage/frontRoleResources').then(response => {
          if (response.data.code === 1) {
            this.toTree(response.data.data);
          } else {
            Message.MessageError(response.data.msg)
          }
        })
      },
      // 新增
      addRole(formName) {
        if (this.form.roleName === '') {
          Message.MessageError('角色名不能为空!')
          return
        }
        if (!numberReg.test(this.form.roleIndex)) {
          Message.MessageError('请输入正确格式的排序!')
          return
        }
        for (var i = 0; i < this.$refs.addtree.getCheckedKeys().length; i++) {
          this.form.resourceIds[i] = this.$refs.addtree.getCheckedKeys()[i]
        }
        api.post('admin/roleManage/addRoleFront', this.form).then(response => {
          if (response.data.code === 1) {
            Message.MessageSuccess(response.data.data)
            this.dialogFormVisible1 = false
            this.form.roleName = ''
            this.form.roleEnName = ''
            this.form.roleIndex = ''
            this.getData()
          } else {
            Message.MessageError(response.data.msg)
          }
        })
      },
      // 修改页面
      editBefore() {
        if (this.multipleSelection.length <= 0) {
          Message.MessageError('请选择要修改的角色!')
          return
        }
        if (this.multipleSelection.length > 1) {
          Message.MessageError('一次只能修改一个角色!')
          return
        }
        this.test.roleId = this.multipleSelection[0].roleId;
        this.test.roleName = this.multipleSelection[0].roleName;
        this.test.roleEnName = this.multipleSelection[0].roleEnName;
        this.test.roleIndex = this.multipleSelection[0].roleIndex;
        var roleId = this.multipleSelection[0].roleId
        var th = this
        api.get('admin/roleManage/frontRoleResources').then(response => {
          if (response.data.code === 1) {
            var aa = response.data.data;
            this.tree = response.data.data;
            api.get('admin/userManage/roleResources?roleId=' + roleId).then(response => {
              if (response.data.code === 1) {
                th.dialogFormVisible2 = true
                th.checkedData = []
                th.$nextTick(function () {
                  th.toTree(aa);
                  th.checkedData = response.data.data.roleResources
                })
              } else {
                Message.MessageError(response.data.msg)
              }
            })
          } else {
            Message.MessageError(response.data.msg)
          }
        })
      },
      // 表格点击修改页面
      editTableBefore(row) {
        this.test.roleId = row.roleId;
        this.test.roleName = row.roleName;
        this.test.roleEnName = row.roleEnName;
        this.test.roleIndex = row.roleIndex;
        var roleId = row.roleId
        var th = this
        api.get('admin/roleManage/frontRoleResources').then(response => {
          if (response.data.code === 1) {
            var aa = response.data.data;
            this.tree = response.data.data;
            api.get('admin/userManage/roleResources?roleId=' + roleId).then(response => {
              if (response.data.code === 1) {
                th.dialogFormVisible2 = true
                th.checkedData = []
                th.$nextTick(function () {
                  th.toTree(aa);
                  th.checkedData = response.data.data.roleResources
                })
              } else {
                Message.MessageError(response.data.msg)
              }
            })
          } else {
            Message.MessageError(response.data.msg)
          }
        })
      },
      // 修改
      editRole(formName) {
        if (this.test.roleName === '') {
          Message.MessageError('角色名不能为空!')
          return
        }
        if (!numberReg.test(this.test.roleIndex)) {
          Message.MessageError('请输入正确格式的排序!')
          return
        }
        for (var i = 0; i < this.$refs.changetree.getCheckedKeys().length; i++) {
          let id = this.$refs.changetree.getCheckedKeys()[i];
          this.test.resourceIds[i] = id;
        }
        this.getParentKey();
        api.post('admin/roleManage/updateRole', this.test).then(response => {
          if (response.data.code === 1) {
            Message.MessageSuccess(response.data.data)
            this.dialogFormVisible2 = false
            this.getData()
          } else {
            Message.MessageError(response.data.msg)
          }
        })
      },
      //  获取当前节点的父节点
      getParentKey() {
        let keys = this.test.resourceIds;
        for (var key of keys) {
          for (var item of this.tree) {
            if (key === item.resourceId && item.parentResourceId) {
              this.test.resourceIds.push(item.parentResourceId);
            }
          }
        }
        this.test.resourceIds = Array.from(new Set(this.test.resourceIds));
      },
      // 生成树
      toTree(list) {
        var result = [];
        for (var i = 0; i < list.length; i++) {
          var resource = list[i];
          if (resource.parentResourceId === '') {
            var node = {};
            node.label = resource.resourceName;
            node.id = resource.resourceId;
            result.push(node);
          }
        }
        for (var j = 0; j < result.length; j++) {
          var node1 = result[j];
          this.toNode(node1, list);
        }
        this.treeData = result;
      },
      //  生成子节点
      toNode(node, list) {
        var id = node.id;
        var children = [];
        for (var i = 0; i < list.length; i++) {
          var resource = list[i];
          if (id === resource.parentResourceId) {
            var node1 = {};
            node1.label = resource.resourceName;
            node1.id = resource.resourceId;
            this.toNode(node1, list);
            children.push(node1)
          }
        }
        node.children = children;
        return node;
      },
      // 确认删除
      deleteRole(row) {
        if (this.multipleSelection.length <= 0) {
          Message.MessageError('请选择要删除的角色!')
        } else if (this.multipleSelection.length > 1) {
          Message.MessageError('一次只能删除一个角色!')
        } else {
          this.dialogFormVisible3 = true
        }
      },
      // 删除
      delete() {
        this.searchForm.roleId = this.multipleSelection[0].roleId
        api.post('admin/roleManage/deleteRole', this.searchForm).then(response => {
          if (response.data.code === 1) {
            Message.MessageSuccess(response.data.msg)
            this.dialogFormVisible3 = false
            this.getData();
          } else {
            Message.MessageError(response.data.msg)
          }
        })
      },
      // 刷新
      refreshList(row) {
        /* this.searchForm.startTime = ''
        this.searchForm.endTime = ''
        this.searchForm.keyword = '' */
        this.getData();
      }
    },
    mounted() {
      this.getData()
    }
  }
</script>

<style scoped>
  .borderStyle {
    border-bottom: 1px solid gainsboro
  }
</style>
