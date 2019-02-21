<template>
    <div class="page1180">
      <el-row class="page-content" style="padding-left:95px;">

        <div style="display:inline-block">
          <el-button style="width:120px;" type="primary" v-if="applicationDetailData.applicationTradeApplicationStatus === 0 && _btn('fukuanxiangqing_chushen')" @click="applicationAuditBefor(0)" >初审</el-button>
          <el-button style="width:120px;" type="primary" v-if="applicationDetailData.applicationTradeApplicationStatus === 1 && _btn('fukuanxiangqing_fushen')"  @click="applicationAuditBefor(1)">复审</el-button>
          <el-button style="width:120px;" type="primary" v-if="applicationDetailData.applicationTradeApplicationStatus === 2 && _btn('fukuanxiangqing_zhongshen')"  @click="applicationAuditBefor(2)">终审</el-button>
          <el-button style="width:120px;" type="primary" v-if="(applicationDetailData.applicationTradeApplicationStatus === 3 || applicationDetailData.applicationTradeApplicationStatus === 6) && _btn('fukuanxiangqing_fukuan')"  @click="applicationPay">付款</el-button>
        </div>
        <el-form :model="applicationDetailData" label-position="left" labelWidth="120px" size="mini">
          <div style="font-weight: bold;margin:20px 0;font-size:16px;color:#000; ">
            【 付款申请 】
          </div>
          <el-form-item label="交易子账户">{{ applicationDetailData.applicationTradeAccountSubNo}}  {{applicationDetailData.applicationTradeAccountTypeName}}</el-form-item>
          <el-form-item label="当前状态">
            <template v-if="applicationDetailData.applicationTradeApplicationStatus === 0">待初审</template>
            <template v-else-if="applicationDetailData.applicationTradeApplicationStatus === 1">待复审</template>
            <template v-else-if="applicationDetailData.applicationTradeApplicationStatus === 2">待终审</template>
            <template v-else-if="applicationDetailData.applicationTradeApplicationStatus === 3">待付款</template>
            <template v-else-if="applicationDetailData.applicationTradeApplicationStatus === 4">已完成</template>
            <template v-else-if="applicationDetailData.applicationTradeApplicationStatus === 5">已否决</template>
            <template v-else-if="applicationDetailData.applicationTradeApplicationStatus === 6">付款失败</template>
            <template v-else>{{applicationDetailData.applicationTradeApplicationStatus}}</template>
          </el-form-item>
          <div style="font-weight: bold;margin:20px 0;font-size:16px;color:#000; ">
            【 申请详情 】
          </div>
          <el-form-item label="申请时间">{{ applicationDetailData.applicationTradeCreateTime}}</el-form-item>
          <el-form-item label="申请人">{{ applicationDetailData.applicationTradeProposer }}</el-form-item>
          <el-form-item label="付款对象">{{ applicationDetailData.applicationTradeTrasactionObject }}</el-form-item>
          <el-form-item label="订单号" style="color: #00ACD6;">{{ applicationDetailData.applicationTradeApplicationNo }}</el-form-item>
          <el-form-item label="申请金额" style="color: red;">{{ applicationDetailData.applicationTradeTranAmt }}元</el-form-item>
          <el-form-item label="业务来源" style="color: #00ACD6;">
            <template v-if="applicationDetailData.applicationTradeTrasactionFrom === '1'">德邻畅途</template>
            <template v-else-if="applicationDetailData.applicationTradeTrasactionFrom === '2'">德邻电商</template>
            <template v-else-if="applicationDetailData.applicationTradeTrasactionFrom === '3'">德邻云嘉</template>
            <template v-else>{applicationDetailData.applicationTradeTrasactionFrom}</template>
          </el-form-item>
          <el-form-item label="业务类型">
            <template v-if="applicationDetailData.applicationTradeBusinessType === '1'">运输</template>
            <template v-else-if="applicationDetailData.applicationTradeBusinessType === '2'">加工</template>
            <template v-else-if="applicationDetailData.applicationTradeBusinessType === '3'">采购</template>
            <template v-else>仓储</template>
          </el-form-item>
          <el-form-item label="清分类型">{{ applicationDetailData.applicationTradeClearingType }}</el-form-item>
          <el-form-item label="备注">{{ applicationDetailData.applicationTradeRemark }}</el-form-item>
        </el-form>
      </el-row>

      <el-dialog title="审核" :visible.sync="auditFormVisible" @close="restert" center width ="30%">
        <el-form :model="auditForm" ref="auditForm" :rules="auditFormRules">
          <el-form-item label="审核" label-width="80px">
            <el-radio-group v-model="auditForm.isPass" size="medium">
              <el-radio  label="1">通过</el-radio>
              <el-radio  label="0">不通过</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="原因" label-width="80px" prop="reason">
            <el-input type="textarea" maxlength="200" :autosize="{ minRows: 2, maxRows: 4}" v-model="auditForm.reason" auto-complete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="applicationAudit">确 定</el-button>
          <el-button @click="auditFormVisible = false">取 消</el-button>
        </div>
      </el-dialog>
    </div>

</template>

<script>
  import api from '../api/fetch'
  import * as Message from '../utils/messageUtil'

    export default {
      name: 'PayDetails',
      data() {
        var checkReson =  (rule, value, callback) => {
          if (this.auditForm.isPass === '0' && value === '') {
            callback(new Error('请输入原因'));
          } else {
            callback();
          }
        }
        return {
          applicationTradeId: '',
          auditFormVisible: false,
          applicationDetailData: {},
          auditForm: {
            isPass: '1',
            reason: '',
            type: '', // 0: 初审  1：复审  2：终审
            applicationTradeId: ''
          },
          auditFormRules: {
            reason: [
              { validator: checkReson, trigger: 'blur' }
            ]
          }
        }
      },
      methods: {
        getData() {
          api.get('/pay/getPayApplicationDetail?applicationTradeId=' + this.applicationTradeId).then(res => {
            if (res.data.code === 1) {
              this.applicationDetailData = res.data.data;
            } else {
              Message.MessageError(res.data.msg);
            }
          })
        },
        applicationAuditBefor(type) {
          this.auditFormVisible = true;
          this.auditForm.type = type;
        },
        applicationAudit() {
          this.auditForm.applicationTradeId = this.applicationTradeId;
          this.$refs['auditForm'].validate((valid) => {
            if (valid) {
              api.post('/pay/payApplicationAudit', this.auditForm).then(res => {
                if (res.data.code === 1) {
                  this.auditFormVisible = false;
                  Message.MessageSuccess('操作成功');
                  this.getData();
                } else {
                  Message.MessageError(res.data.msg);
                }
              })
            } else {
              return false;
            }
          });
        },
        restert() {
          this.auditForm.isPass = '1';
          this.auditForm.reason = '';
        },
        applicationPay() {
          api.post('pay/payApplicationPay',{applicationTradeId: this.applicationTradeId, type: this.applicationDetailData.applicationTradeApplicationStatus}).then(response => {
            if (response.data.code === 1) {
              debugger
              this.$router.push({
                name: '支付申请付款',
                params: {businessTradeId: response.data.data,applicationTradeId: this.applicationTradeId}
              });
            } else {
              Message.MessageError(response.data.msg)
            }
          })
        }
      },
      mounted() {
        this.applicationTradeId = this.$route.params.application_trade_id;
        this.getData();
      }
    }
</script>

<style scoped>
  .pd-body{
    margin: 26px auto;
    max-width: 900px;
    width: 100%;
  }
  .pd-body h3{
    font-size: 20px;
    margin: 16px 0;
  }
  .pdb-item{
    margin-bottom: 10px;
  }
  .pdb-item b{
    font-weight: normal;
    font-size: 12px;
    display: inline-block;
    width: 150px;
  }
  .pdb-item span{
    font-size: 12px;
  }
  .dialog-footer{
    text-align: center;
  }
</style>
