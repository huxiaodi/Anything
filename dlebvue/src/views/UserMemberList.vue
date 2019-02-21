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
      <el-form-item label="用户状态:">
        <el-select v-model="searchForm.status" placeholder="请选择">
          <el-option
            v-for="item in optionsUserStatus"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="关键字:">
        <el-tooltip class="item" effect="light" content="关键字查询支持用户名、绑定手机号、真实姓名查询" placement="bottom-start">
          <el-input placeholder="关键字" v-model="searchForm.keyword"></el-input>
        </el-tooltip>
      </el-form-item>
      <div class="admin-search-btns">
        <el-button type="primary" icon="el-icon-search" @click="getData()" :loading="this.isShowLoadingIcon">查询
        </el-button>
        <el-button type="primary" icon="el-icon-edit" @click="showDialogAdd">创建</el-button>
        <el-button type="primary" icon="el-icon-edit" @click="showDialogUpdate">修改</el-button>
        <el-button type="primary" icon="el-icon-close" @click="showDialog(0)">禁用</el-button>
        <el-button type="primary" icon="el-icon-check" @click="showDialog(1)">启用</el-button>
        <el-button type="primary" icon="el-icon-refresh" @click="refreshList">刷新</el-button>
      </div>
      <!--创建/修改弹窗-->
      <el-dialog class="UserMemberList-dialog"
                 :visible.sync="dialogVisibleAdd"
                 :showClose="false"
                 :close-on-click-modal="false" :close-on-press-escape="false">
        <div class="UserMemberList-dialog-items">
          <!--用户信息-->
          <div class="UserMemberList-dialog-item">
            <div class="formHead">用户信息</div>
            <el-form class="userForm" ref="userForm" :model="userForm" :rules="rules">
              <el-form-item label="用户名" prop="userName">
                <el-input v-model="userForm.userName" :readonly="isReadOnly"></el-input>
              </el-form-item>
              <el-form-item label="姓名" v-if="isReadOnly" prop="userCnName">
                <el-input v-model="userForm.userCnName"></el-input>
              </el-form-item>
              <el-form-item label="手机号" prop="mobile">
                <el-input v-model="userForm.mobile"></el-input>
              </el-form-item>
              <el-form-item label="密码" prop="userPassword" v-if="!isReadOnly">
                <el-input type="password" v-model="userForm.userPassword" :readonly="isReadOnly"></el-input>
              </el-form-item>
              <el-form-item label="确认密码" prop="passwordConfirm" v-if="!isReadOnly">
                <el-input type="password" v-model="userForm.passwordConfirm"></el-input>
              </el-form-item>
            </el-form>
          </div>
          <!--角色列表-->
          <div class="UserMemberList-dialog-item">
            <div class="formHead">角色列表</div>
            <el-table ref="roleTable" stripe border
                      :show-header=false
                      v-if="dialogVisibleAdd "
                      :data="roleList"
                      :height="300"
                      style="width: 100%;"
                      highlight-current-row
                      @selection-change="roleSelectionChange">
              <el-table-column type="selection" width="50px" align="center"></el-table-column>
              <el-table-column prop="roleName" align="center"></el-table-column>
              <el-table-column align="center">
                <template slot-scope="scope">
                  <el-button type="primary" size="mini" @click="showRoleResourceTree(scope.row)">权限树</el-button>
                </template>
              </el-table-column>
            </el-table>
          </div>
          <!--选择组-->
          <div class="UserMemberList-dialog-item">
            <div class="formHead">选择组</div>
            <el-table ref="teamTable" stripe border
                      :show-header=false
                      v-if="dialogVisibleAdd "
                      :data="teamList"
                      :height="300"
                      style="width: 100%;"
                      highlight-current-row
                      @selection-change="teamSelectionChange">
              <el-table-column type="selection" width="50px" align="center"></el-table-column>
              <el-table-column prop="teamName" align="center"></el-table-column>
            </el-table>
          </div>
        </div>
        <div class="UserMemberList-dialog-items">
          <div class="UserMemberList-dialog-item">
            <div class="formHead">功能权限展示</div>
            <el-tree :data="treeData " node-key="id" :default-expand-all="true" :default-checked-keys="treeSelected"
                     check-strictly show-checkbox></el-tree>
          </div>
        </div>
        <div style="width:100%;clear:both;">
          <el-button style="float: right" type="primary" @click="addUser" v-if="!isReadOnly">提交</el-button>
          <el-button style="float: right" type="primary" @click="editUser" v-if="isReadOnly">保 存</el-button>
          <el-button style="float: left" @click="close">关 闭</el-button>
        </div>
      </el-dialog>
      <!--禁用按钮弹窗-->
      <el-dialog
        title="用户禁用"
        :visible.sync="dialogVisibleDisable"
        width="25%"
        center
        :showClose="false"
        :close-on-click-modal="false"
        style="font-size: large;font-weight: bold;">
        <div style="font-size: large; text-align: center">
          <el-form-item label="禁用原因">
            <el-input v-model="disableForm.keyword"></el-input>
          </el-form-item>
        </div>
        <div slot="footer" align="center">
          <el-button type="primary" @click="disableUser">确 定</el-button>
          <el-button @click="dialogVisibleDisable = false">取 消</el-button>
        </div>
      </el-dialog>
      <!--启用按钮弹窗-->
      <el-dialog
        :visible.sync="dialogVisibleEnable"
        :showClose="false"
        :close-on-click-modal="false"
        width="25%">
        <div style="font-size: large; text-align: center;color: #00acd6">
          确定重新启用该用户吗？
        </div>
        <div slot="footer" align="center">
          <el-button type="primary" @click="enableUser">确 定</el-button>
          <el-button @click="dialogVisibleEnable = false">取 消</el-button>
        </div>
      </el-dialog>
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
      <el-table-column prop="userCreateTime" label="创建时间" align="center" width="170px"></el-table-column>
      <el-table-column prop="userName" label="用户名" align="center"></el-table-column>
      <el-table-column prop="userMobile" label="绑定手机号" align="center"></el-table-column>
      <el-table-column prop="userCnName" label="真实姓名" align="center"></el-table-column>
      <el-table-column prop="userStatus" :formatter="formatRole" label="会员状态" align="center"></el-table-column>
      <el-table-column prop="userCreateName" label="创建人" align="center"></el-table-column>
      <el-table-column prop="teamNames" label="隶属项目组" align="center"></el-table-column>
      <el-table-column prop="roleNames" label="关联角色" align="center"></el-table-column>
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

  // 手机号
  var mobileReg = /^1[34578]\d{9}$/;
  // 密码
  var passwordReg = /^(?=[^A-Z\u4e00-\u9fa5]*[0-9])(?=[^A-Z\u4e00-\u9fa5]*[a-z])[^A-Z\u4e00-\u9fa5]{8,50}$/;

  export default {
    // 用户管理
    name: 'UserMemberList',
    mixins: [pageMixin],
    data() {
      var validatePass = (rule, value, callback) => {
        if (value !== this.userForm.userPassword) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        // 权限树
        props: {
          //  配置父子树的属性名
          label: 'parent',
          children: 'sub'
        },
        treeData: [],
        treeSelected: [],
        searchForm: {
          keyword: '',
          status: undefined
        },
        disableForm: {
          id: '',
          keyword: ''
        },
        userForm: {
          userName: '',
          userCnName: '',
          mobile: '',
          userPassword: '',
          passwordConfirm: ''
        },
        isReadOnly: false,
        rules: {
          userName: [
            {required: true, message: '用户名不能为空', trigger: 'blur'},
            {min: 6, message: '用户名至少6位', trigger: 'blur'}
          ],
          userCnName: [],
          mobile: [
            {required: true, message: '手机号不能为空', trigger: 'blur'},
            {pattern: mobileReg, message: '请输入正确的手机号'}
          ],
          userPassword: [
            {required: true, message: '密码不能为空', trigger: 'blur'},
            {pattern: passwordReg, message: '密码至少8位，支持数字、字母（小写）、字符，至少包含数字、字母组合'}
          ],
          passwordConfirm: [
            {required: true, message: '确认密码不能为空', trigger: 'blur'},
            {validator: validatePass, trigger: 'blur'}
          ]
        },
        tableList: [],
        roleList: [],
        teamList: [],
        optionsUserStatus: [{
          value: undefined,
          label: '全部'
        }, {
          value: 1,
          label: '启用'
        }, {
          value: 0,
          label: '禁用'
        }],
        dialogVisibleDisable: false,
        dialogVisibleEnable: false,
        dialogVisibleAdd: false,
        multipleSelection: [],
        roleSelection: [],
        teamSelection: []
        // userStatusExplain: ''
      }
    },
    methods: {
      // 获取数据
      getData() {
        api.post('admin/userManage/getUserMemberList', this.searchForm).then(response => {
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
      formatRole(row, column) {
        return row.userStatus === '1' ? '启用' : '禁用';
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
      // 创建/修改页面获取角色
      roleSelectionChange(val) {
        this.roleSelection = val;
      },
      // 创建/修改页面获取分组
      teamSelectionChange(val) {
        this.teamSelection = val;
      },
      // 打开创建窗口
      showDialogAdd() {
        this.userForm = {};
        api.post('admin/userManage/getAllRoleAndTeam').then(response => {
          if (response.data.code === 1) {
            this.dialogVisibleAdd = true;
            let data = response.data.data;
            this.isReadOnly = false;
            this.roleList = data.allRoles;
            this.teamList = data.allTeams;
          } else {
            Message.MessageError(response.data.msg)
          }
        })
      },
      // 打开修改窗口
      showDialogUpdate() {
        if (this.multipleSelection.length <= 0) {
          Message.MessageError('请选择要修改的单据!')
          return
        }
        if (this.multipleSelection.length > 1) {
          Message.MessageError('一次只能修改一条单据!')
          return
        }
        var row = this.multipleSelection[0];
        this.roleSelection = [];
        this.teamSelection = [];
        api.post('admin/userManage/getAllRoleAndTeam').then(response => {
          if (response.data.code === 1) {
            this.dialogVisibleAdd = true;
            this.isReadOnly = true;
            this.userForm.userName = row.userName;
            this.userForm.userCnName = row.userCnName;
            this.userForm.mobile = row.userMobile;
            let data = response.data.data;
            this.roleList = data.allRoles;
            this.teamList = data.allTeams;
            let checkedRoles = row.roleIds;
            let checkedTeams = row.teamIds;
            this.$nextTick(function () {
              if (checkedRoles.length) {
                this.checkedRole(checkedRoles)
              } else {
                this.treeData = [];
              }
              if (checkedTeams.length) {
                this.checkedTeam(checkedTeams)
              }
            });
          } else {
            Message.MessageError(response.data.msg)
          }
        })
      },
      // 勾选已选择的角色
      checkedRole(checkedRoles) {
        let roles = this.roleList;
        for (let i = 0; i < roles.length; i++) {
          for (let j = 0; j < checkedRoles.length; j++) {
            if (roles[i].roleId === checkedRoles[j]) {
              this.$refs.roleTable.toggleRowSelection(this.roleList[i]);
              break;
            }
          }
        }
        this.showRoleResourceTree(this.roleSelection[0]);
      },
      // 勾选已选择的分组
      checkedTeam(checkedTeams) {
        let teams = this.teamList;
        for (let i = 0; i < teams.length; i++) {
          for (let j = 0; j < checkedTeams.length; j++) {
            if (teams[i].teamId === checkedTeams[j]) {
              this.$refs.teamTable.toggleRowSelection(this.teamList[i]);
              break;
            }
          }
        }
      },
      // 显示权限树
      showRoleResourceTree(row) {
        api.get('admin/userManage/roleResources?roleId=' + row.roleId).then(response => {
          if (response.data.code === 1) {
            let data = response.data.data;
            let resources = data.allResources;
            this.toTree(resources);
            this.treeSelected = data.roleResources;
          } else {
            Message.MessageError(response.data.msg)
          }
        })
      },
      getRoleIds() {
        let roles = this.roleSelection
        let roleIds = [];
        for (let i = 0; i < roles.length; i++) {
          roleIds.push(roles[i].roleId);
        }
        return roleIds;
      },
      getTeamIds() {
        let teams = this.teamSelection
        let teamIds = [];
        for (let i = 0; i < teams.length; i++) {
          teamIds.push(teams[i].teamId);
        }
        return teamIds;
      },
      // 创建
      addUser() {
        this.userForm.roleIds = this.getRoleIds();
        this.userForm.teamIds = this.getTeamIds();
        this.$refs['userForm'].validate((valid) => {
          if (valid) {
            api.post('admin/userManage/addUser', this.userForm).then((response) => {
              if (response.data.code === 1) {
                Message.MessageSuccess(response.data.msg);
                this.dialogVisibleAdd = false;
                this.refreshList();
              } else {
                Message.MessageError(response.data.msg)
              }
            })
          } else {
            return false;
          }
        })
      },
      // 修改
      editUser() {
        this.userForm.roleIds = this.getRoleIds();
        this.userForm.teamIds = this.getTeamIds();
        this.userForm.userId = this.multipleSelection[0].userId;
        this.$refs['userForm'].validate((valid) => {
          if (valid) {
            api.post('admin/userManage/modify', this.userForm).then((response) => {
              if (response.data.code === 1) {
                Message.MessageSuccess(response.data.msg);
                this.dialogVisibleAdd = false;
                this.refreshList();
              } else {
                Message.MessageError(response.data.msg)
              }
            })
          } else {
            return false;
          }
        })
      },
      // 显示禁用/启用弹窗 0--禁用 1--启用
      showDialog(type) {
        if (this.multipleSelection.length <= 0) {
          Message.MessageError('请选择要修改的单据!')
          return
        }
        if (this.multipleSelection.length > 1) {
          Message.MessageInfo('一次只能修改一条单据!')
          return
        }
        if (type === 0) {
          if (this.multipleSelection[0].userStatus === '0') {
            Message.MessageError('该用户已禁用，请勿重复操作')
            return
          }
          this.dialogVisibleDisable = true;
        }
        if (type === 1) {
          if (this.multipleSelection[0].userStatus === '1') {
            Message.MessageError('该用户已启用，请勿重复操作')
            return
          }
          this.dialogVisibleEnable = true;
        }
      },
      // 禁用
      disableUser() {
        api.post('admin/userManage/switch/disable', {
          'id': this.multipleSelection[0].userId,
          'keyword': this.disableForm.keyword
        }).then(response => {
          if (response.data.code === 1) {
            Message.MessageSuccess(response.data.msg)
            this.dialogVisibleDisable = false
            this.getData()
          } else {
            Message.MessageError(response.data.msg)
          }
        })
      },
      // 启用
      enableUser() {
        api.post('admin/userManage/switch/enable', {'id': this.multipleSelection[0].userId}).then(response => {
          if (response.data.code === 1) {
            Message.MessageSuccess(response.data.msg)
            this.dialogVisibleEnable = false
            this.getData()
          } else {
            Message.MessageError(response.data.msg)
          }
        })
      },
      close() {
        this.$refs['userForm'].resetFields();
        this.dialogVisibleAdd = false
      },
      // 刷新
      refreshList() {
        if (this.$refs['userForm']) {
          this.$refs['userForm'].resetFields();
        }
        this.userForm = {};
        this.getData();
      },
      // 生成树
      toTree(list) {
        var result = [];
        for (var i = 0; i < list.length; i++) {
          var resource = list[i];
          if (resource.parentResourceId === '') {
            var node = {};
            let {resourceName} = resource
            let {resourceId} = resource
            node.label = resourceName;
            node.id = resourceId;
            node.disabled = true;
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
            let {resourceName} = resource
            let {resourceId} = resource
            node1.label = resourceName;
            node1.id = resourceId;
            node1.disabled = true;
            this.toNode(node1, list);
            children.push(node1)
          }
        }
        node.children = children;
        return node;
      }
    },
    mounted() {
      // this.setDate();
      this.getData();
    }
  }
</script>

<style scoped>

</style>
