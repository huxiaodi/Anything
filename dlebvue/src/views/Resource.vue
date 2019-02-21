<template>
<div class="resource-page">
  <el-row class="custom-tree-node resource-tree-title">
    <el-col :span="9">英文名称</el-col>
    <el-col :span="4">中文名称</el-col>
    <el-col :span="4">地址</el-col>
    <el-col :span="2">级别</el-col>
    <el-col :span="2">排序</el-col>
    <el-col :span="3">操作</el-col>
  </el-row>
  <el-row>
    <el-tree class="resource-tree" :data="resourceData" node-key="resourceId" :props="resourceProps" :indent="indent"
             :default-expanded-keys="defaultExpand"
             @node-expand="addExpand"
             @node-collapse="removeExpand"
             :expand-on-click-node="false">
      <el-row class="custom-tree-node" slot-scope="{ node, data }">
      <el-col :span="9" class="EN-name">{{node.label}}</el-col>
        <el-col :span="4">{{node.data.resourceName}}</el-col>
        <el-col :span="4">{{node.data.resourceValue}}</el-col>
        <el-col :span="2">{{node.data.resourceRank}}</el-col>
        <el-col :span="2">{{node.data.resourceIndex}}</el-col>
        <el-col :span="3">
          <a href="javascript:"><i class="el-icon-plus" @click="preAdd(data)"></i></a>
          <a href="javascript:" v-if="data.parentResourceId"><i class="el-icon-edit" @click="preModify(data)"></i></a>
          <a href="javascript:" v-if="data.parentResourceId"><i class="el-icon-delete"
          @click="preDelete(data)"></i></a>
        </el-col>
      </el-row>
    </el-tree>

  </el-row>

  <el-dialog :title="operation == 'add' ? '新增资源':'修改资源'" :visible.sync="dialogAddSourceFormVisible" width="40%" @close="closeDialogBefore()" center>
    <el-form :model="sourceForm"  :rules="sourceRule" ref="resourceRef" label-width="25%">
      <el-form-item label="中文名称"  prop="resourceName">
        <el-input v-model="sourceForm.resourceName" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="英文名称"  prop="resourceEnName">
        <el-input v-model="sourceForm.resourceEnName" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="地址">
        <el-input v-model="sourceForm.resourceValue" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="排序" prop="resourceIndex">
        <el-input v-model="sourceForm.resourceIndex" auto-complete="off"></el-input>
      </el-form-item>

    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogAddSourceFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="save()">确 定</el-button>
    </div>
  </el-dialog>
</div>
</template>

<script>
import api from '../api/fetch'
import * as Message from '../utils/messageUtil'

export default {
  name: 'Resource',
  data() {
    var checkResourceIndex =(rule, value, callback) => {
      if ( value == undefined ||  value === '') {
        callback();
      } else if (!/^[1-9]{1}[0-9]*$/.test(value)) {
        callback(new Error('只能输入数字'));
      }else{
        callback();
      }
    };
    return {
      indent: 31,
      resourceData: [],
      sourceForm: {},
      operation: '',
      defaultExpand: [],
      dialogAddSourceFormVisible: false,
      resourceProps: {
        label: 'resourceEnName',
        children: 'children'
      },
      sourceRule: {
        resourceIndex: [
          { required: false, validator: checkResourceIndex, trigger: 'change' }
        ],
        resourceName: [
          { required: true, message: '必填', trigger: 'blur' }
        ],
        resourceEnName: [
          { required: true, message: '必填', trigger: 'blur' }
        ]

      }
    }
  },
  methods: {
    getData() {
      api.get('/basic/getResouceTreeData').then(response => {
        if (response.data.code === 1) {
          this.resourceData = response.data.data;
        }
      });
    },
    preAdd(row) {
      this.operation = 'add';
      this.sourceForm.parentResourceId = row.resourceId;
      this.sourceForm.resourceType = row.resourceType;
      this.sourceForm.resourceRank = parseInt(row.resourceRank) + 1;
      this.dialogAddSourceFormVisible = true;
    },
    preModify(row) {
      this.operation = 'modify';
      this.sourceForm = JSON.parse(JSON.stringify(row));
      this.dialogAddSourceFormVisible = true;
    },
    preDelete(row) {
      console.log(row);
      this.$confirm('确定删除该资源吗？', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        confirmButtonClass: 'el-button--danger'
      }).then(() => {
        api.delete('/basic/resource/' + row.resourceId).then(response => {
          if (response.data.code === 1) {
            this.getData();
            this.dialogAddSourceFormVisible = false;
            Message.MessageSuccess('删除成功')

          } else {
            Message.MessageError(response.data.msg);
            console.log(response);
          }
        });
      }).catch(() => {});
    },
    save() {
      this.$refs['resourceRef'].validate((valid) => {
        if (valid) {
          if (this.operation === 'add') {
            api.post('/basic/resource', this.sourceForm).then(response => {
              if (response.data.code === 1) {
                this.getData();
                Message.MessageSuccess('添加成功')
                this.dialogAddSourceFormVisible = false;
              } else {
                Message.MessageError(response.data.msg);
              }
            });
          } else if (this.operation === 'modify') {
            api.put('/basic/resource', this.sourceForm).then(response => {
              if (response.data.code === 1) {
                Message.MessageSuccess('修改成功')
                this.dialogAddSourceFormVisible = false;
                this.getData();
              } else {
                Message.MessageError(response.data.msg);
              }
            });
          }
        }
      });

    },
    addExpand(a,b,c) {
      this.defaultExpand.push(a.resourceId);
    },
    removeExpand(a,b,c) {
     for(var i=0 ; i < this.defaultExpand.length ;i++){
       if(this.defaultExpand[i] == a.resourceId){
         this.defaultExpand.splice(i,1);
       }
     }
    },
    closeDialogBefore() {
      this.sourceForm = {};
    }
  },
  mounted() {
    this.getData();
  }
}
</script>

<style scoped>
.custom-tree-node {
  flex: 1;
  display: flex;
  justify-content: space-between;
  font-size: 14px;
  padding-right: 8px;
}
.el-tree{
  text-align: left;
}

</style>
