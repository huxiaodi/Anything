<template xmlns:v-bind="http://www.w3.org/1999/xhtml">
  <div class="page1180">
    <div class="page-title">
      <div style="float:left;">
        <router-link class="parent" to="/IssueManager">物流发布管理</router-link>
        <i class="el-icon-arrow-right"></i> 物流发布
      </div>
    </div>
    <div class="page-content">
      <div style="width: 800px;margin: 0 auto;">
        <el-form ref="formDataForm" label-position="left" :rules="formDataRules" :model="formData" label-width="140px">
          <el-row>
            <el-col :span="12">
              <el-form-item label="货物名称:" prop="orderGoodsName">
                <el-input v-model="formData.orderGoodsName" style="width:220px;"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item
                label="估计费用：" prop="orderMoney">
                <el-input v-model="formData.orderMoney" style="width:220px;"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item label="货物重量(吨):" prop="orderGoodsWeight">
                <el-input v-model="formData.orderGoodsWeight" style="width:220px;"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="货物体积（m³）:" prop="orderGoodsCube">
                <el-input v-model="formData.orderGoodsCube" style="width:220px;"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item
                label="发布日期：" prop="orderIssueTime">
                <el-date-picker
                  style="width:220px;"
                  v-model="formData.orderIssueTime"
                  type="datetime"
                  placeholder="选择发布日期"
                  default-time="12:00:00">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item
                label="提货日期：" prop="orderPickupTime">
                <el-date-picker
                  style="width:220px;"
                  v-model="formData.orderPickupTime"
                  type="datetime"
                  placeholder="选择提货日期"
                >
                </el-date-picker>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item label="起始地："
                            prop="startingPlaceCode">
                <el-cascader
                  style="width:220px;"
                  size="large"
                  :options="regionData"
                  v-model="formData.startingPlaceCode"
                  @change="startingPlaceCodeChange">
                </el-cascader>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="起始地详细地址："
                            prop="orderPickupAddress">
                <el-input v-model="formData.orderPickupAddress" style="width:220px;"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item label="目的地："
                            prop="endingPlaceCode">
                <el-cascader
                  style="width:220px;"
                  size="large"
                  :options="regionData"
                  v-model="formData.endingPlaceCode"
                  @change="endingPlaceCodeChange">
                </el-cascader>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="目的地详细地址："
                            prop="orderReceiveAddress">
                <el-input v-model="formData.orderReceiveAddress" style="width:220px;"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item label="是否指定：">
                <el-checkbox v-model="formData.orderAssign">{{assignInfo}}</el-checkbox>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="接单方：" >
                <el-select :disabled="!formData.orderAssign" v-model="formData.orderReceiveId" clearable placeholder="请选择">
                  <el-option
                    v-for="item in receiveUserList"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                </el-select>

              </el-form-item>
            </el-col>
          </el-row>

          <el-form-item label="备注:" prop="orderRemark">
            <el-input  v-model="formData.orderRemark"
            ></el-input>
          </el-form-item>
          <el-row style="text-align: center;margin:20px 0;">
            <el-button type="primary" :loading="loading" @click="submitData">发布</el-button>
            <el-button type="primary" @click="resetForm">重置</el-button>
          </el-row>
        </el-form>
      </div>
    </div>


  </div>
</template>

<script>
  import api from '@/api/fetch'
  import * as Message from '../utils/messageUtil'
  import { regionData ,CodeToText } from 'element-china-area-data'


  export default {
    name: 'AddBankCard',
    data() {
      return {
        loading: false,
        assignInfo: '',
        formData: {
          orderGoodsName: '',
          orderGoodsWeight: '',
          orderGoodsCube: '',
          startingPlaceCode: [],
          startingPlaceName:[],
          orderPickupAddress: '',
          endingPlaceCode: [],
          endingPlaceName: [],
          orderReceiveAddress: '',
          orderPickupTime: '',
          orderRemark: '',
          orderIssueTime: '',
          orderMoney: '',
          orderAssign: false,
          orderReceiveId: ''
        },
        regionData: regionData,
        receiveUserList: [],
        formDataRules: {
          orderGoodsName: [{
            required: true,
            message: '请填货物名称',
            trigger: 'blur'
          }],
          orderIssueTime: [{
            required: true,
            message: '请填写发布日期',
            trigger: 'change'
          }],
          orderPickupTime: [{
            required: true,
            message: '请填写提货日期',
            trigger: 'change'
          }],
          startingPlaceCode: [{
            required: true,
            message: '请选择起始地',
            trigger: 'change'
          }],
          orderPickupAddress: [{
            required: true,
            message: '请填写起始地详细地址',
            trigger: 'blur'
          }],
          endingPlaceCode: [{
            required: true,
            message: '请选择目的地',
            trigger: 'change'
          }],
          orderReceiveAddress: [{
            required: true,
            message: '请填写目的地详细地址',
            trigger: 'change'
          }],
          orderMoney: [{
            required: true,
            message: '请填写金额',
            trigger: 'blur'
          }, {
            pattern: /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$/,
            message: '请填写正确金额'
          }],
          orderGoodsCube: [{
            required: true,
            message: '请填写体积',
            trigger: 'blur'
          }, {
            pattern: /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$/,
            message: '请填写正确体积'
          }],
          orderGoodsWeight: [{
            required: true,
            message: '请填写重量',
            trigger: 'blur'
          }, {
            pattern: /^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$/,
            message: '请填写正确重量'
          }]
        },
      }
    },
    methods: {
      getData () {
      },
      submitData() {
        this.$refs['formDataForm'].validate((valid) => {
          if (valid) {
            this.loading = true;
            api.post('/order/assignOrder', this.formData).then((response) => {
              this.loading = false;
              if(response.data.code === 1){
                Message.MessageSuccess("发布成功")
                this.formData.orderGoodsName = '';
              } else {
                Message.MessageError(response.data.msg);
              }
            });
          }
        });
      },
      startingPlaceCodeChange (value) {
        this.formData.startingPlaceName = [ CodeToText[value[0]], CodeToText[value[1]], CodeToText[value[2]] ];
        console.log(this.formData.startingPlaceName)
      },
      endingPlaceCodeChange (value) {
        this.formData.endingPlaceName = [ CodeToText[value[0]], CodeToText[value[1]], CodeToText[value[2]] ];
        console.log(this.formData.endingPlaceName )
      },
      resetForm () {
        this.$refs['formDataForm'].resetFields();
      }
    },
    mounted() {
      this.getData()
    },
    watch: {
      'formData.orderAssign' (value){
        if (value) {
          this.assignInfo = '是';
        } else {
          this.assignInfo = '否';
          this.formData.orderReceiveId = '';
        }
      }
    }
  }
</script>

<style scoped>

</style>
