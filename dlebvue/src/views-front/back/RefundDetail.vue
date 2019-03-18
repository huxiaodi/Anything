<template>
  <div class="page1180">

    <el-row class="page-content" style="padding-left:95px;">

      <div style="display:inline-block">
        <el-button style="width:120px;" type="primary" v-if="refundDetailData.refundStatus === '0' && _btn('tuikuanxiangqing_chushen')" @click="refundAuditBefor(0)" >初审</el-button>
        <el-button style="width:120px;" type="primary" v-if="refundDetailData.refundStatus === '1' && _btn('tuikuanxiangqing_fushen')"  @click="refundAuditBefor(1)">复审</el-button>
        <el-button style="width:120px;" type="primary" v-if="refundDetailData.refundStatus === '2' && _btn('tuikuanxiangqing_zhongshen')"  @click="refundAuditBefor(2)">终审</el-button>
        <el-button style="width:120px;" type="primary" v-if="refundDetailData.refundStatus === '3' && _btn('tuikuanxiangqing_tuikuan')"  @click="refund">退款</el-button>
      </div>
      <el-form :model="refundDetailData" label-position="left" labelWidth="120px" size="mini">
        <div style="font-weight: bold;margin:20px 0;font-size:16px;color:#000; ">
          【 退款申请 】
        </div>
        <el-form-item label="交易子账户">{{ refundDetailData.accountSubName}}</el-form-item>
        <el-form-item label="当前状态">
          <template v-if="refundDetailData.refundStatus === '0'">待初审</template>
          <template v-else-if="refundDetailData.refundStatus === '1'">待复审</template>
          <template v-else-if="refundDetailData.refundStatus === '2'">待终审</template>
          <template v-else-if="refundDetailData.refundStatus === '3'">待退款</template>
          <template v-else-if="refundDetailData.refundStatus === '4'">已完成</template>
          <template v-else-if="refundDetailData.refundStatus === '5'">已否决</template>
          <template v-else>{{refundDetailData.refundStatus}}</template>
        </el-form-item>
        <div style="font-weight: bold;margin:20px 0;font-size:16px;color:#000; ">
          【 申请详情 】
        </div>
        <el-form-item label="申请时间">{{ refundDetailData.refundApplyForData}}</el-form-item>
        <el-form-item label="退款人">{{ refundDetailData.refundProposer }}</el-form-item>
        <el-form-item label="申请企业">{{ refundDetailData.refundCompany }}</el-form-item>
        <el-form-item label="流水单号" style="color: #00ACD6;">{{ refundDetailData.account_sub_trade_no }}</el-form-item>
        <el-form-item label="申请金额" style="color: red;">{{ refundDetailData.refundAmount }}元</el-form-item>
        <el-form-item label="订单号" style="color: #00ACD6;">{{ refundDetailData.businessTradeBusinessNo }}</el-form-item>
        <el-form-item label="交易平台">{{ refundDetailData.businessTradeBusinessFrom }}</el-form-item>
        <el-form-item label="业务类型">{{ refundDetailData.businessTradeBusinessType }}</el-form-item>
        <el-form-item label="清分类型">{{ refundDetailData.businessTradeClearingType }}</el-form-item>
        <el-form-item label="备注">{{ refundDetailData.refundRemark }}</el-form-item>
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
        <el-button type="primary" @click="refundAudit">确 定</el-button>
        <el-button @click="auditFormVisible = false">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  import api from '../api/fetch'
  import * as Message from '../utils/messageUtil'
  export default {
    name: "RefundDetail",
    data() {
      var checkReson =  (rule, value, callback) => {
        if (this.auditForm.isPass === '0' && value === '') {
          callback(new Error('请输入原因'));
        } else {
          callback();
        }
      }
      return {
        refundId: '',
        auditFormVisible: false,
        refundDetailData: {},
        auditForm: {
          isPass: '1',
          reason: '',
          type: '', // 0: 初审  1：复审  2：终审
          refundId: ''
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
        api.get('/trade/getRefundDetail?refundId=' + this.refundId).then(res => {
          if (res.data.code === 1) {
            this.refundDetailData = res.data.data;
          } else {
            Message.MessageError(res.data.msg);
          }
        })
      },
      refundAuditBefor(type) {
        this.auditFormVisible = true;
        this.auditForm.type = type;
      },
      refundAudit() {
        this.auditForm.refundId = this.refundId;
        this.$refs['auditForm'].validate((valid) => {
          if (valid) {
            api.post('/trade/refundAudit', this.auditForm).then(res => {
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
      refund() {
        this.$confirm('确认退款吗？', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning',
          confirmButtonClass: 'el-button--danger'
        }).then(() => {
          api.post('refund/drawBack?refundTradeBankNo=' + this.refundDetailData.refundTradeBankNo).then(response => {
            if (response.data.code === 1) {
              Message.MessageSuccess(response.data.msg)
              this.getData()
            } else {
              Message.MessageError(response.data.msg)
            }
          })
        }).catch(() => {
        });
      }
    },
    mounted() {
      this.refundId = this.$route.params.refund_id;
      this.getData();
    }
  }
</script>

<style scoped>

</style>
