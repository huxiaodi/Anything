<template>
  <div>
    <el-card class="el-card">
      <el-form ref="bankForm" style="text-align: left;background-color: transparent" class="form" :model="bankForm"
               :rules="rules" label-width="110px" label-position="left">
        <el-row>
          <el-col :xs="20" :sm="6" class="col">
            <el-radio v-model="radio" label="1">银联鉴权</el-radio>
            <el-radio v-model="radio" label="2" style="margin-left:210px;">小额鉴权</el-radio>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="20" :sm="6" class="col">
            <el-form-item label="会员证件类型" prop="MemberGlobalType">
              <el-select v-model="bankForm.MemberGlobalType" placeholder="请选择">
                <el-option
                  v-for="item in MemberGlobalTypes"
                  @click.native="cleanNum()"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="20" :sm="6" class="col">
            <el-form-item label="会员证件号码" prop="MemberGlobalId">
              <el-input v-model="bankForm.MemberGlobalId"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="20" :sm="6" class="col">
            <el-form-item label="银行类型" prop="BankType">
              <el-select v-model="bankForm.BankType" placeholder="请选择">
                <el-option
                  v-for="item in BankTypes"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="20" :sm="6" class="col">
            <el-form-item label="是否支持单笔5万以上提现">
              <el-tabs v-model="activeName2" type="card" @tab-click="handleClick">
                <el-tab-pane label="是" name="first">
                  <el-form-item label="开户行省市区" prop="shipperCity">
                    <el-col :xs="20" :sm="26">
                      <el-select v-model="bankForm.province" placeholder="全部" class="select">
                        <el-option
                          v-for="item in provinces"
                          @click.native="getCity(item)"
                          :key="item.nodeNodecode"
                          :label="item.nodeNodename"
                          :value="item.nodeNodecode">
                        </el-option>
                      </el-select>
                    </el-col>
                    <el-col :xs="20" :sm="26">
                      <el-select v-model="bankForm.city" placeholder="全部" class="select">
                        <el-option
                          v-for="item in citys"
                          @click.native="getDistrict(item)"
                          :key="item.cityTopareacode2"
                          :label="item.cityAreaname"
                          :value="item.cityTopareacode2">
                        </el-option>
                      </el-select>
                    </el-col>
                    <el-col :xs="20" :sm="26">
                      <el-select v-model="bankForm.district" placeholder="全部" class="select">
                        <el-option
                          v-for="item in districts"
                          @click.native="getBank(item)"
                          :key="item.cityOraareacode"
                          :label="item.cityAreaname"
                          :value="item.cityOraareacode">
                        </el-option>
                      </el-select>
                    </el-col>
                  </el-form-item>
                  <el-form-item label="开户行名称" prop="AcctOpenBranchName">
                    <el-select v-model="bankForm.AcctOpenBranchName" placeholder="请选择">
                      <el-option
                        v-for="item in banks"
                        @click.native="setCnapsBranchId(item)"
                        :key="item.bankno"
                        :label="item.bankname"
                        :value="item.bankname">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-tab-pane>
                <el-tab-pane label="否" name="second">
                  <el-form-item label="开户行名称" prop="AcctOpenBranchName">
                    <el-select v-model="bankForm.AcctOpenBranchName" placeholder="请选择">
                      <el-option
                        v-for="item in banks"
                        @click.native="setEiconBankBranchId(item)"
                        :key="item.bankNo"
                        :label="item.bankAliasName"
                        :value="item.bankAliasName">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-tab-pane>
              </el-tabs>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="20" :sm="6" class="col">
            <el-form-item label="银行账户名称">
              <el-input v-model="bankForm.bankAccountName"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="20" :sm="6" class="col">
            <el-form-item label="银行账号" prop="MemberAcctNo">
              <el-input v-model="bankForm.MemberAcctNo"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :xs="20" :sm="6" class="col">
            <el-form-item label="银行预留手机" prop="Mobile">
              <el-input v-model="bankForm.Mobile"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col align="center">
            <el-button type="primary" @click="bankFormSubmit('bankForm')" :disabled="this.isSaveDisable">下一步</el-button>
          </el-col>
        </el-row>
      </el-form>
    </el-card>
  </div>
</template>

<script>
  import api from '@/api/fetch'
  import * as Message from '../utils/messageUtil'
  import axios from 'axios'

  var qs = require('qs');
  export default {
    name: 'order',
    data() {
      var validateNum = (rule, value, callback) => {
        if (!value) {
          callback(new Error('请输入会员证件号码'))
        } else if (this.bankForm.MemberGlobalType === '') {
          callback(new Error('请选择会员证件类型'))
        } else {
          callback()
        }
      }
      return {
        bankForm: {
          province: '',
          city: '',
          district: '',
          AcctOpenBranchName: '',
          BankType: '',
          CnapsBranchId: '',
          EiconBankBranchId: '',
          Mobile: '',
          MemberAcctNo: '',
          MemberGlobalId: '',
          MemberGlobalType: '',
          MemberName: '',
          TranNetMemberCode: '',
          SubAcctNo: ''
        },
        rules: {
          MemberGlobalId: [
            {required: true, validator: validateNum, trigger: 'change'}
          ],
          MemberAcctNo: [
            {required: true, message: '请输入银行账号', trigger: 'blur'}
          ],
          Mobile: [
            {required: true, message: '请输入银行预留手机', trigger: 'blur'},
            {pattern: /^[0-1][0-9]{10}$/, message: '请输入正确的手机号'}
          ],
          AcctOpenBranchName: [
            {required: true}
          ],
          BankType: [
            {required: true}
          ],
          MemberGlobalType: [
            {required: true}
          ]
        },
        provinces: [],
        citys: [],
        districts: [],
        banks: [],
        radio: '1',
        activeName2: 'first',
        MemberGlobalTypes: [{
          value: 52,
          label: '组织机构代码证'
        }, {
          value: 68,
          label: '营业执照'
        }, {
          value: 60,
          label: '香港商业登记证'
        }],
        BankTypes: [{
          value: 1,
          label: '本行'
        }, {
          value: 2,
          label: '他行'
        }],
        isSaveDisable: false
      }
    },
    methods: {
      // 获取省市区
      getProvince() {
        api.get('/bind/province').then(reponse => {
          if (reponse.data.code === 1) {
            this.provinces = reponse.data.data
          }
        })
      },
      // 选择省份后清空市和区/县/银行并查询市
      getCity(item) {
        this.bankForm.city = ''
        this.bankForm.district = ''
        this.bankForm.AcctOpenBranchName = ''
        this.citys = []
        this.districts = []
        this.banks = []
        api.post('/bind/city', {'nodeNodecode': item.nodeNodecode}).then(reponse => {
          if (reponse.data.code === 1) {
            this.citys = reponse.data.data
          }
        })
      },
      // 选择市后清空区/县/银行并查询市/区/县
      getDistrict(item) {
        this.bankForm.district = ''
        this.bankForm.AcctOpenBranchName = ''
        this.districts = []
        this.banks = []
        api.post('/bind/district', {'cityTopareacode2': item.cityTopareacode2}).then(reponse => {
          if (reponse.data.code === 1) {
            this.districts = reponse.data.data
          }
        })
      },
      // 选择区/县后清空银行并查询银行
      getBank(item) {
        this.bankForm.AcctOpenBranchName = ''
        this.banks = []
        api.post('/bind/bank', {'cityOraareacode': item.cityOraareacode}).then(reponse => {
          if (reponse.data.code === 1) {
            this.banks = reponse.data.data
          }
        })
      },
      // 选择会员证件类型后清空会员证件号码
      cleanNum() {
        this.bankForm.MemberGlobalId = ''
      },
      // 选择大小额银行后设置大小额行号
      setCnapsBranchId(item) {
        this.bankForm.CnapsBranchId = item.bankno
      },
      // 选择超级网银银行后设置超级网银行号
      setEiconBankBranchId(item) {
        this.bankForm.EiconBankBranchId = item.bankNo
      },
      // tab点击
      handleClick(tab, event) {
        if (this.activeName2 === 'first') { // 点击“是”
          this.bankForm.AcctOpenBranchName = ''
          this.banks = []
        } else { // 点击“否”
          this.bankForm.province = ''
          this.bankForm.city = ''
          this.bankForm.district = ''
          this.bankForm.AcctOpenBranchName = ''
          this.citys = []
          this.districts = []
          this.banks = []
          api.get('/bind/superBank').then(reponse => {
            if (reponse.data.code === 1) {
              this.banks = reponse.data.data
            }
          })
        }
      },
      // 提交
      bankFormSubmit(formName) {
        var $router = this.$router;
        this.bankForm.MemberName = this.$root.$data.user.userName // 会员名称
        this.bankForm.TranNetMemberCode = this.$root.$data.user.userId // 交易网会员代码
        this.bankForm.SubAcctNo = sessionStorage['dleb_userAccountId'] // 会员子账户账号
        if (this.radio === '1') {
          api.post('/bind/BindRelateAcctUnionPay', this.bankForm).then(response => {
            if (response.data.code === 1) {
              this.isSaveDisable = true
              sessionStorage.setItem('dleb_MemberAcctNo', this.bankForm.MemberAcctNo)
              this.$router.push('/returnShortMessage')
            } else {
              Message.MessageError(response.data.msg)
            }
          })
        } else {
          api.post('/bind/BindRelateAcctSmallAmount', this.bankForm).then(response => {
            if (response.data.code === 1) {
              this.isSaveDisable = true
              sessionStorage.setItem('dleb_MemberAcctNo', this.bankForm.MemberAcctNo)
              this.$router.push('/returnMoney')
            } else {
              Message.MessageError(response.data.msg)
            }
          })
        }
      },
      updateTableData(result) {
        this.bankForm = result.list
      }
    },
    mounted() {
      this.getProvince()
    }
  }
</script>

<style scoped>
  .el-card {
    margin: auto;
    background-color: #edf2fc;
  }

  .el-select {
    width: 100%;
  }

  .el-input-number {
    width: 100%;
  }

  .el-row {
    margin-top: 6px;
  }

  .col {
    margin-left: 38%;
  }
</style>
