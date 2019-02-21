<template>
  <div style="margin: 0 auto;">
    <el-form ref="accountForm" :model="accountForm" labelPosition="right" label-width="140px">

      <div class="divTitle">
        <div style="float: left;">子账户详情（{{this.accountForm.accountSubStatusC}}）</div>
        <div style="float: right;">账户余额:{{this.accountForm.accountSubAvailBalance}}元</div>
      </div>
      <hr style="background-color:#FF9933;height:2px;border:none;"/>

      <div>
        <div style="width: 50%;height: 120px;float: left;border-right:black 1px solid">
          <el-row>
            <el-col :span="12">
              <div align="left" style="padding: 10px">账户名称:{{this.accountForm.accountSubName}}</div>
            </el-col>
            <el-col :span="12">
              <div align="left" style="padding: 10px">账户编号:{{this.accountForm.accountSubNo}}</div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <div align="left" style="padding: 10px">账户可提现余额:{{this.accountForm.accountSubAshBalance}}</div>
            </el-col>
            <el-col :span="12">
              <div align="left" style="padding: 10px">
                账户不可提现余额:{{this.accountForm.accountSubAvailBalance - this.accountForm.accountSubAshBalance}}
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <div align="left" style="padding: 10px">账户类型:{{this.accountForm.accountSubRelationIdTypeC}}</div>
            </el-col>
            <el-col :span="12">
              <div align="left" style="padding: 10px">隶属用户:{{this.accountForm.accountSubRelationId}}</div>
            </el-col>
          </el-row>
        </div>
        <div style="width: 50%;height:120px;float: right">
          <div style="width: 40%;height:120px;float: left;text-align: center;line-height: 120px">绑卡详情</div>
          <div style="width: 60%;height:120px;float: right;text-align: left">
            <el-button type="text" style="float: right;line-height: 120px"
                       @click="showDialog">
              点击查看全部
            </el-button>
            <div v-for="item in bankCardList3" :key="item.bank_card_no" style="font-size: 14px;padding: 10px 20px;">
              {{item.bank_card_bank}}:{{formatBankNumberShow(item.bank_card_no)}}
            </div>
          </div>
        </div>
      </div>

      <div style="float:left;margin:0 auto; width:100%; height:100%;">
        <div class="divTitle divDotted">交易记录</div>
        <div class="divDotted">
          <el-col :span="18" align="left">
            <el-button type="text" @click="getTransactionInfo(5)">最近交易记录</el-button>
            <el-button type="text" @click="getTransactionInfo(5,3)">充值记录</el-button>
            <el-button type="text" @click="getTransactionInfo(5,4)">提现记录</el-button>
            <el-button type="text" @click="getTransactionInfo(5,6)">退款记录</el-button>
          </el-col>
          <el-col :span="4">
            <el-input v-model="keyword" placeholder="关键字" size="mini" style="padding-top: 6px"></el-input>
          </el-col>
          <el-col :span="2">
            <el-button type="text" @click="getTransactionInfo()">搜索</el-button>
          </el-col>
        </div>
        <div class="divDotted">
          <el-table stripe border
                    :data="transactionList"
                    :max-height="500"
                    :height="250"
                    style="width: 100%"
                    highlight-current-row>
            <el-table-column prop="account_sub_trade_tran_date" label="交易时间" align="center"></el-table-column>
            <el-table-column prop="account_sub_trade_typeC" label="交易类型" align="center"></el-table-column>
            <el-table-column prop="business_trade_business_no" label="业务单号" align="center"
                             show-overflow-tooltip></el-table-column>
            <el-table-column prop="account_sub_trade_tran_amt" label="费用金额" align="center"></el-table-column>
            <el-table-column prop="account_sub_trade_no" label="流水单号" align="center"
                             show-overflow-tooltip></el-table-column>
            <el-table-column prop="account_sub_trade_tran_statusC" label="交易状态" align="center"></el-table-column>
            <el-table-column label="操作" align="center">
              <template slot-scope="scope">
                <a style="cursor:pointer;color: #409EFF;" @click="viewTransactionInfo(scope.row)">详情</a>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div class="divDotted" style="padding-left: 80%">
          <el-button type="text" @click="viewTransactionManageList">查看全部记录</el-button>
        </div>
      </div>
    </el-form>
    <!--绑卡详情数据的弹出框-->
    <el-dialog
      title="绑卡详情"
      :visible.sync="dialogVisible"
      width="50%"
      center>
      <el-table stripe border
                :data="bankCardList"
                :max-height="500"
                :height="300"
                style="width: 100%"
                highlight-current-row>
        <el-table-column type="index" label="序号" width="70" align="center"></el-table-column>
        <el-table-column prop="bank_card_no" label="银行卡号" align="center"></el-table-column>
        <el-table-column prop="bank_card_reserve_bank_account_name" label="持卡人" align="center"></el-table-column>
        <el-table-column prop="bank_card_bind_bank_card_date" label="绑卡时间" align="center"></el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button @click="colseDialog">取消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import api from '@/api/fetch'
  import * as Message from '../utils/messageUtil'

  export default {
    name: 'AccountDetails',
    data() {
      return {
        accountForm: {},
        // 显示更多绑卡详情
        bankCardList: [],
        // 显示3条绑卡详情
        bankCardList3: [],
        transactionList: [],
        keyword: '',
        dialogVisible: false,
        accountSubid: ''
      }
    },
    methods: {
      // 获取子账户的相关信息
      getAccountInfo() {
        api.post('admin/accountManage/details/account', {'id': this.accountSubid}).then(response => {
          if (response.data.code === 1) {
            let item = response.data.data;
            if (item.accountSubStatus === 1) {
              item.accountSubStatusC = '正常';
            } else if (item.accountSubStatus === 0) {
              item.accountSubStatusC = '禁用';
            }
            if (item.accountSubRelationIdType === 1) {
              item.accountSubRelationIdTypeC = '个人账户';
            } else if (item.accountSubRelationIdType === 2) {
              item.accountSubRelationIdTypeC = '企业账户';
            }
            this.accountForm = item;
          } else {
            Message.MessageError(response.data.msg)
          }
        });
      },
      // 获取绑卡详情
      getBankCardfo(pageSize) {
        api.post('admin/accountManage/details/bankcard', {
          'id': this.accountSubid,
          'pageSize': pageSize
        }).then(response => {
          if (response.data.code === 1) {
            let list = response.data.data.list;
            if (pageSize) {
              this.bankCardList3 = list;
            } else {
              this.bankCardList = list;
            }
          }
        });
      },
      // 获取交易记录
      getTransactionInfo(pageSize, type) {
        api.post('admin/accountManage/details/trade', {
          'id': this.accountSubid,
          // 数据显示5条
          'pageSize': pageSize,
          // 空:最近交易记录 3:充值记录 4:提现记录 6:退款记录
          'tradeType': type,
          'keyword': this.keyword
        }).then(response => {
          if (response.data.code === 1) {
            let list = response.data.data.list;
            list.forEach((item) => {
              if (item.account_sub_trade_tran_type === 1) {
                item.account_sub_trade_tran_typeC = '普通交易'
              }
              // 交易类型
              let objT = {'1': '会员交易', '2': '转账', '3': '充值', '4': '提现', '5': '服务费', '6': '退款', '7': '挂账'}
              item.account_sub_trade_typeC = objT[item.account_sub_trade_type] || item.account_sub_trade_type;
              // 交易状态（0:成功，1:失败，2:待确认, 5:待处理，6:处理中, 7: 待挂账，8:挂账成功，9:挂账失败， 10:提现退单）
              let objS = {
                '0': '成功',
                '1': '失败',
                '2': '待确认',
                '5': '待处理',
                '6': '处理中',
                '7': '待挂账',
                '8': '挂账成功',
                '9': '挂账失败',
                '10': '提现退单'
              }
              item.account_sub_trade_tran_statusC = objS[item.account_sub_trade_tran_status] || item.account_sub_trade_tran_status;
            })
            this.transactionList = list;
          }
        });
      },
      // 查看全部记录（进入交易管理页面）
      viewTransactionManageList() {
        this.$router.push(`/TransactionManageList`);
      },
      // 详情（查看该交易的交易详情）
      viewTransactionInfo(row) {
        this.$router.push(`/TransactionInfo/${row.account_sub_trade_id}`)
        // this.$router.push({name: '交易管理详情', params: row})
      },
      showDialog() {
        this.dialogVisible = true;
        this.getBankCardfo();
      },
      colseDialog() {
        this.dialogVisible = false;
      },
      // 格式化显示
      formatBankNumberShow(bankNumber) {
        if (bankNumber != null) {
          return bankNumber.substr(0, 4) + '  ****  ****  ' + bankNumber.substr(-4);
        } else {
          return '';
        }
      }
    },
    mounted() {
      this.accountSubid = this.$route.params.id;
      this.getAccountInfo();
      this.getBankCardfo(3);
      this.getTransactionInfo(5);
    }
  }
</script>
<style scoped>
  .divTitle {
    text-align: left;
    padding-bottom: 10px;
    font-weight: bold;
    font-size: large;
    color: #FF9933
  }

  .divDotted {
    float: left;
    margin: 0 auto;
    width: 100%;
    height: 100%;
  }
</style>
