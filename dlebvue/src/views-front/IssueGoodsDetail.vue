<template xmlns:v-bind="http://www.w3.org/1999/xhtml">
  <div class="page1180">
    <div class="page-title">
      <div style="float:left;">
        <router-link class="parent" to="/IssueManager">物流发布管理</router-link>
        <i class="el-icon-arrow-right"></i> 物流详情
      </div>
    </div>
    <div class="page-content">
      <div style="width: 800px;margin: 0 auto;">
        <el-form ref="formDataForm" label-position="left"  :model="formData" label-width="140px">
          <el-row>
            <el-col :span="12">
              <el-form-item label="货物名称:"  prop="orderGoodsName">
                <el-input v-model="formData.orderGoodsName" readonly style="width:220px;"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item
                label="估计费用：" prop="orderMoney">
                <el-input v-model="formData.orderMoney" readonly style="width:220px;"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item label="货物重量(吨):" prop="orderGoodsWeight">
                <el-input v-model="formData.orderGoodsWeight" readonly style="width:220px;"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="货物体积（m³）:" prop="orderGoodsCube">
                <el-input v-model="formData.orderGoodsCube" readonly style="width:220px;"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item
                label="发布日期：" prop="orderIssueTime">
                <el-date-picker
                  style="width:220px;"
                  disabled
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
                  disabled
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
                  disabled
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
                <el-input v-model="formData.orderPickupAddress" readonly style="width:220px;"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item label="目的地："
                            prop="endingPlaceCode">
                <el-cascader
                  style="width:220px;"
                  disabled
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
                <el-input v-model="formData.orderReceiveAddress" readonly style="width:220px;"></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item label="是否指定：">
                <el-checkbox disabled v-model="formData.orderAssign">{{assignInfo}}</el-checkbox>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="接单方：" >
                <el-select :disabled="!formData.orderAssign" v-model="formData.orderReceiveId" clearable placeholder="请选择">
                  <el-option
                    disabled
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
            <el-input readonly v-model="formData.orderRemark"
            ></el-input>
          </el-form-item>
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
        id: '',
        loading: false,
        assignInfo: '否',
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
      }
    },
    methods: {
      getData () {
      },
      startingPlaceCodeChange (value) {
        this.formData.startingPlaceName = [ CodeToText[value[0]], CodeToText[value[1]], CodeToText[value[2]] ];
        console.log(this.formData.startingPlaceName)
      },
      endingPlaceCodeChange (value) {
        this.formData.endingPlaceName = [ CodeToText[value[0]], CodeToText[value[1]], CodeToText[value[2]] ];
        console.log(this.formData.endingPlaceName )
      }
    },
    mounted() {
      this.id = this.$route.params.orderId;
      console.log( this.id);
      this.getData()
    },
    watch: {
    }
  }
</script>

<style scoped>

</style>
