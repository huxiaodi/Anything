<template xmlns:v-bind="http://www.w3.org/1999/xhtml">
  <div class="page1180">
    <div class="page-title">
      <div style="float:left;">
        <router-link class="parent" to="/IssueManager">物流发布管理</router-link>
        <i class="el-icon-arrow-right"></i>物流详情
      </div>
      <div style="margin-left: 34%">
         运单号：{{formData.orderNo}}&nbsp;&nbsp;(
          <span v-if="formData.orderStatus === 1"> 待接单</span>
          <span v-else-if="formData.orderStatus === 2"> 已接单</span>
          <span v-else-if="formData.orderStatus === 3"> 已提货</span>
          <span v-else-if="formData.orderStatus === 4"> 已发货</span>
          <span v-else-if="formData.orderStatus === 5"> 已签收</span>
          <span v-else> {{formData.orderStatus}}</span>
        )
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
                <el-input :value="formData.orderPickupProvince+'/'+formData.orderPickupCity+'/'+formData.orderPickupDistrict" readonly style="width:220px;"></el-input>
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
                <el-input :value="formData.orderReceiveProvince+'/'+formData.orderReceiveCity+'/'+formData.orderReceiveDistrict" readonly style="width:220px;"></el-input>
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
            <el-col v-if="userType === 2" :span="12">
              <el-form-item label="接单方：" >
                <el-input v-model="formData.receiveCompanyName" placeholder="无" readonly style="width:220px;"></el-input>
              </el-form-item>
            </el-col>
            <el-col v-if="userType === 3" :span="12">
              <el-form-item label="发布方：" >
                <el-input v-model="formData.issueCompanyName" placeholder="无" readonly style="width:220px;"></el-input>
              </el-form-item>
            </el-col>
          </el-row>


          <el-row>
            <el-col :span="12">
              <el-form-item
                label="实际提货时间：" prop="orderFinalPickupTime">
                <el-date-picker
                  style="width:220px;"
                  disabled
                  v-model="formData.orderFinalPickupTime"
                  type="datetime"
                  placeholder="无"
                >
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="物流单号：" >
                <el-input v-model="formData.orderLogisticsNo" placeholder="无" readonly style="width:220px;"></el-input>
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
        userType: '',
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
        api.get('/order/getOrderInfo?orderId='+this.id).then(response => {
          if (response.data.code === 1) {
            this.formData = response.data.data;
          }else{
            this.$message({
              type: 'error',
              message: response.data.msg
            });
          }
        })
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
      this.id = this.$route.params.id;
      this.userType = JSON.parse(sessionStorage.getItem('dleb_user')).userType;
      this.getData()
    },
    watch: {
    }
  }
</script>

<style scoped>

</style>
