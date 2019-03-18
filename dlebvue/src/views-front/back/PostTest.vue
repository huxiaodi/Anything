<template>
  <div>
    PostTest
    <div ref="c1"></div>
    <el-upload
      class="avatar-uploader"
      action="https://jsonplaceholder.typicode.com/posts/"
      :show-file-list="false"
      :on-success="handleAvatarSuccess"
      :before-upload="beforeAvatarUpload">
      <img v-if="imageUrl" :src="imageUrl" class="avatar">
      <i v-else class="el-icon-plus avatar-uploader-icon"></i>
    </el-upload>

    <el-table :data="model.menus" border max-height="400">
      <el-table-column prop="id" label="id" type="selection" fixed></el-table-column>
      <el-table-tree-column
        fixed :expand-all="!1"
        :remote="remote"
        file-icon="icon icon-file" folder-icon="icon icon-folder"
        prop="label" label="title"
        width="320" class-name="123" header-align="center"
        :formatter="formatter">
      </el-table-tree-column>
      <el-table-column prop="description" label="Description"
                       :show-overflow-tooltip="true" width="180"></el-table-column>
    </el-table>

  </div>
</template>

<script>
  import _dx from '_dx'

  let flatTree = [{
    "id": 1,
    "label": "System",
    "parent_id": null,
    "url": null,
    "depth": 0,
    "child_num": 3,
    "description": "System Manager"
  }, {
    "id": 2,
    "label": "base",
    "parent_id": 1,
    "depth": 1,
    "child_num": 5,
    "description": "Base Manager",
  }, {
    "id": 3,
    "label": "Menus",
    "parent_id": 2,
    "depth": 2,
    "child_num": 0,
    "description": "menu manager",
  }, {
    "id": 4,
    "label": "Roles",
    "parent_id": 2,
    "depth": 2,
    "child_num": 0,
    "description": "Role Manager",
  }, {
    "id": 5,
    "label": "Users",
    "parent_id": 2,
    "depth": 2,
    "child_num": 0,
    "description": "User Manager",
  }, {
    "id": 6,
    "label": "Customs",
    "parent_id": null,
    "url": null,
    "depth": 0,
    "child_num": 2,
    "description": "Custom Manager",
  }, {
    "id": 7,
    "label": "CustomList",
    "parent_id": 6,
    "depth": 1,
    "child_num": 0,
    "description": "CustomList",
  }, {
    "id": 8,
    "label": "Templates",
    "parent_id": null,
    "url": null,
    "depth": 0,
    "child_num": 1,
    "description": "Template Manager",
  }, {
    "id": 9,
    "label": "TemplateList",
    "parent_id": 8,
    "depth": 1,
    "child_num": 0,
    "description": "Template Manager",
  }, {
    "id": 10,
    "label": "Bussiness",
    "parent_id": null,
    "url": null,
    "depth": 0,
    "child_num": 2,
    "description": "Bussiness Manager",
  },
    {
      "id": 11,
      "label": "BussinessList",
      "parent_id": 10,
      "url": null,
      "depth": 1,
      "child_num": 2,
      "description": "BussinessList",
      "children": []
    }, {
      "id": 12,
      "label": "Currencies",
      "parent_id": 11,
      "depth": 2,
      "child_num": 0,
      "description": "Currencies",
    }, {
      "id": 13,
      "label": "Dealtypes",
      "parent_id": 11,
      "depth": 2,
      "child_num": 0,
      "description": "Dealtypes",
    }, {
      "id": 14,
      "label": "Products",
      "parent_id": 10,
      "url": null,
      "depth": 1,
      "child_num": 2,
      "description": "Products"
    }, {
      "id": 15,
      "label": "ProductTypes",
      "parent_id": 14,
      "depth": 2,
      "child_num": 0,
      "description": "ProductTypes",
    }, {
      "id": 16,
      "label": "ProductList",
      "parent_id": 14,
      "depth": 2,
      "child_num": 0,
      "description": "ProductList",
    }]
  var trees = [{
    "id": 1,
    "label": "System",
    "parent_id": null,
    "depth": 0,
    "child_num": 3,
    "description": "System Manager",
    "children": [{
      "id": 2,
      "label": "base",
      "parent_id": 1,
      "depth": 1,
      "child_num": 5,
      "description": "Base Manager",
      "children": [{
        "id": 3,
        "label": "Menus",
        "parent_id": 2,
        "depth": 2,
        "child_num": 0,
        "description": "menu manager",
      }, {
        "id": 4,
        "label": "Roles",
        "parent_id": 2,
        "depth": 2,
        "child_num": 0,
        "description": "Role Manager",
      }, {
        "id": 5,
        "label": "Users",
        "parent_id": 2,
        "depth": 2,
        "child_num": 0,
        "description": "User Manager",
      }]
    }]
  }, {
    "id": 6,
    "label": "Customs",
    "parent_id": null,
    "url": null,
    "depth": 0,
    "child_num": 2,
    "description": "Custom Manager",
    "children": [{
      "id": 7,
      "label": "CustomList",
      "parent_id": 6,
      "depth": 1,
      "child_num": 0,
      "description": "CustomList",
    }]
  }, {
    "id": 8,
    "label": "Templates",
    "parent_id": null,
    "url": null,
    "depth": 0,
    "child_num": 1,
    "description": "Template Manager",
    "children": [{
      "id": 9,
      "label": "TemplateList",
      "parent_id": 8,
      "depth": 1,
      "child_num": 0,
      "description": "Template Manager",
    }]
  }, {
    "id": 10,
    "label": "Bussiness",
    "parent_id": null,
    "url": null,
    "depth": 0,
    "child_num": 2,
    "description": "Bussiness Manager",
    "children": [{
      "id": 11,
      "label": "BussinessList",
      "parent_id": 10,
      "url": null,
      "depth": 1,
      "child_num": 2,
      "description": "BussinessList",
      "children": [{
        "id": 12,
        "label": "Currencies",
        "parent_id": 11,
        "depth": 2,
        "child_num": 0,
        "description": "Currencies",
      }, {
        "id": 13,
        "label": "Dealtypes",
        "parent_id": 11,
        "depth": 2,
        "child_num": 0,
        "description": "Dealtypes",
      }]
    }, {
      "id": 14,
      "label": "Products",
      "parent_id": 10,
      "url": null,
      "depth": 1,
      "child_num": 2,
      "description": "Products",
      "children": [{
        "id": 15,
        "label": "ProductTypes",
        "parent_id": 14,
        "depth": 2,
        "child_num": 0,
        "description": "ProductTypes",
      }, {
        "id": 16,
        "label": "ProductList",
        "parent_id": 14,
        "depth": 2,
        "child_num": 0,
        "description": "ProductList",
      }]
    }]
  }]
  export default {
    name: 'PostTest',
    data() {
      return {
        imageUrl: '',
        model: {
          menus: flatTree.filter(f => f['parent_id'] == null)
        }
      }
    },
    methods: {
      formatter(row, column) {
        return ' ---  ' + row.label;
      },
      remote(row, callback) {
        callback(flatTree.filter(f => f['parent_id'] == row['id']))
      },
      handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      }
    },
    mounted() {
      var myCaptcha = _dx.Captcha(this.$refs.c1, {
        appId: 'e9a1d305fc43f48f94ea8ae35ed93be2',   //  appId,开通服务后可在控制台中“服务管理”模块获取
        success: function (token) {
          console.log('token:', token)
        }
      })
    }
  }
</script>

<style scoped>


  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }

  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
