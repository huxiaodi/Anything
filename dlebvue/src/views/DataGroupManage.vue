<template>
  <div class="admin-input-form">
    <el-form ref="form" :model="form" labelPosition="right" label-width="140px">
      <div class="divTitle" v-if="sid==null || sid=='' ">创建数据组</div>
      <div class="divTitle" v-if="sid!=''">修改数据组</div>

      <el-row>
        <el-col align="center">
          <el-form-item label="组名称:">
            <el-input type="text" v-model="form.teamName"></el-input>
            <el-input type="hidden" v-model="form.teamId" style="display: none"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col align="center">
          <el-form-item label="备注:">
            <el-input type="text" v-model="form.teamRemark"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <el-row align="center">
          <div style="font-size:14px;font-weight:bold;margin:10px 0;">
            选择业务数据来源
          </div>
          <div align="center">
            <el-checkbox-group v-model="form.source" align="center" style="display:inline-block" @change="handleCheckedCitiesChange">
              <div v-for='item in businessList' class="businessListSty">
                <el-checkbox :label="item.dictId" :key="item.dictId" name="source" class="businessListItem">
                  {{item.dictValue}}
                </el-checkbox>
              </div>
            </el-checkbox-group>
          </div>
      </el-row>
      <el-row align="center">
          <div style="font-size:14px;font-weight:bold;margin:10px 0;">
            选择结算体系
          </div>
          <div align="center">
            <el-checkbox label="pa_system" v-model="checked" align="left" name="system">平安银行体系</el-checkbox>
          </div>

          <div style="display: none">
            <el-checkbox label="aaa" v-model="select"></el-checkbox>
          </div>
      </el-row>
      <div class="admin-input-form-btns">
        <el-button type="primary"
                   @click="confirmAccount">确认
        </el-button>
      </div>

    </el-form>
  </div>
</template>

<script>
  import api from '@/api/fetch'
  import * as Message from '../utils/messageUtil'

  export default {
    name: 'DataGroupManage',
    data() {
      return {
        sid: localStorage.getItem('DataGroupManageGroupId'),
        checked: true,
        info: '',
        form: {
          teamName: '',
          system: [],
          source: []
        },
        team: {},
        businessList: [],
        businessListSelect: [],
        select: true,
        systemList: [{systemId: 'pa_system', systemKey: '平安银行体系'}, {systemId: 'gs_system', systemKey: '工商银行体系'}],
        systemListSelect: []

      }
    },
    methods: {
      // 手动重新渲染视图
      handleCheckedCitiesChange(value) {
        this.select = !this.select;
        // console.info(this.form.source);
      },
      handleChange(value) {
        console.info(this.systemListSelect);
      },
      // 获得组信息
      getGroupInfo() {
        api.post('admin/dataGroupManage/getRelationInfo', {teamId: localStorage.getItem('DataGroupManageGroupId')}).then(response => {
          if (response.data.code === 1) {
            // this.businessList = response.data.data.list;
            this.team = response.data.data[0][0];              // 组信息
            this.form = this.team;                             // 加载组信息到表单
            this.businessListSelect = response.data.data[1];   // 业务系统
            this.form.source = [];
            for (var i = 0; i < this.businessListSelect.length; i++) {
              this.form.source.push(this.businessListSelect[i].teamDictDictId);
            }
          }
        })
      },
      // 查询业务系统(业务系统字典)
      getBusiness() {
        this.isShowLoadingIcon = true;
        api.post('admin/dictionaryManage/list', {type: '业务来源'}).then(response => {
          if (response.data.code === 1) {
            this.businessList = response.data.data.list;
          }
        })
      },
      // 保存
      confirmAccount() {

        if (this.form.teamName == null || this.form.teamName == "") {
          Message.MessageError('请输入组名称!');
          return false;
        }
        if (this.form.source.length == 0) {
          Message.MessageError('请选择业务数据来源!');
          return false;
        }
        if (this.checked === true) {
          api.post('admin/dataGroupManage/save', this.form).then(response => {
            if (response.data.code === 1) {
              Message.MessageSuccess(response.data.msg);
              this.$router.go(-1);
              this.form.teamName = ''
              this.form.teamRemark = ''
              this.form.source = []
            } else {
              Message.MessageError(response.data.msg);
            }
          })
        } else {
          Message.MessageError('请选择结算体系!')
        }
      }
    },
    mounted() {
      this.getBusiness();
      if (localStorage.getItem('DataGroupManageGroupId') != null && localStorage.getItem('DataGroupManageGroupId') != "") {
        this.getGroupInfo();
      }
    }
  }
</script>
<style scoped>

  /* 统一信用照片图片大小*/
  .avatar {
    width: 100%;
    height: 100%;
  }

  .businessListSty {
    float: left;
    padding-right: 10px;
  }
</style>
