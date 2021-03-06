export const exportModel = {
  data() {
    return {
      // 交易管理列表
      tradeManageListListHeader: [
        '交易时间',
        '交易单号',
        '账户体系',
        '交易类型',
        '交易金额',
        '子账户名称',
        '账户编号',
        '交易对象',
        '交易状态',
        '订单号',
        '订单内容',
        '备注'
      ],
      tradeManageListRow: [
        'account_sub_trade_tran_date',
        'account_sub_trade_no',
        'accountType',
        'type',
        'account_sub_trade_tran_amt',
        'account_sub_trade_take_cash_account_name',
        'account_sub_trade_take_cash_account_no',
        'account_sub_name',
        'status',
        'business_trade_business_no',
        'business_trade_business_content',
        'account_sub_trade_remark'],
      // 预约提货列表
      pickUpListHeader: [
        '提货单号',
        '提货单状态',
        '提货时间',
        '提货地点',
        '提货联系人',
        '提货联系电话',
        '货名',
        '件数',
        '重量',
        '体积',
        '备注',
        '提货注意事项',
        '提货创建时间',
        '退回原因',
        '退回时间'
      ],
      pickUpListRow: [
        'pickNo',
        'pickBillStatus',
        'businessTime',
        'addr',
        'name',
        'mobile',
        'cargoName',
        'cargoQty',
        'cargoWeight',
        'cargoVolume',
        'cargoRemark',
        'remark',
        'createTime',
        'returnReason',
        'returnTime'
      ],
      // 我的运单
      myWaybillListHeader: [
        '托运单号',
        '厂家单号',
        '开单时间',
        '回单状态',
        '最后跟踪记录',
        '是否签收',
        '起运城市',
        '到货城市',
        '货名',
        '包装',
        '件数',
        '重量',
        '体积',
        '运单总运费',
        '交接方式',
        '发货人',
        '发货人手机',
        '发货人固话',
        '发货地址',
        '收货人',
        '收货单位',
        '收货人手机',
        '收货人固话',
        '收货人地址',
        '要求到货日期',
        '备注',
        '货物备注',
        '计费方式',
        '服务方式',
        '回单要求',
        '回单份数',
        '付款方式',
        '提货费',
        '送货费',
        '保险费',
        '代收货款',
        '代收货款手续费',
        '声明价值',
        '单价',
        '进仓费',
        '其他费',
        '装卸费',
        '打卡',
        '到付',
        '回单付',
        '扣付',
        '欠付',
        '现付',
        '月结'
      ],
      myWaybillListRow: [
        'waybillNo',
        'factoryNo',
        'businessTime',
        'receiptState',
        'trackNote',
        'isSignedField',
        'startCity',
        'arrivalCity',
        'cargoName',
        'packages',
        'qty',
        'weight',
        'volume',
        'total',
        'deliveryType',
        'shipperName',
        'shipperMobile',
        'shipperTel',
        'shipperAddr',
        'consigneeName',
        'consigneeContact',
        'consigneeMobile',
        'consigneeTel',
        'deliveryAddr',
        'requireTime',
        'remark',
        'cRemark',
        'chargeStandard',
        'serviceType',
        'receiptReq',
        'receiptQty',
        'paymentType',
        'pickUp',
        'delivery',
        'insure',
        'agent',
        'agentCommission',
        'declaredValue',
        'price',
        'entryFee',
        'waybillOtherIncome',
        'handlingCost',
        'bankTransfer',
        'collect',
        'receiptPay',
        'unpaid',
        'arrear',
        'prepay',
        'monthly'],
      // 财务统计列表
      financeListHeader: [
        '托运单号',
        '厂家单号',
        '开单时间',
        '是否对账',
        '对账单号',
        '发货人',
        '到货城市',
        '回单状态',
        '货名',
        '件数',
        '重量',
        '体积',
        '结算类型',
        '结算总金额',
        '已结算金额',
        '未结算金额',
        '未付现付',
        '未付月结',
        '未付回单付',
        '未付欠付',
        '未付打卡',
        '未付发货人附加费用',
        '未付扣付',
        '未收代收货款',
        '未收代收运费',
        '异常赔付未收',
        '已付月结',
        '已付现付',
        '已付回单付',
        '已付欠付',
        '已付打卡',
        '已付发货人附加费用',
        '已付扣付',
        '已收代收货款',
        '已收代收运费',
        '异常赔付已收',
        '已付到付',
        '未付到付',
        '已付代收货款',
        '未付代收货款',
        '已付代收运费',
        '未付代收运费',
        '已付收货人附加费用',
        '未付收货人附加费用',
        '收货单位',
        '收货人',
        '收货人手机',
        '收货人固话',
        '付款方式'
      ],
      financeListRow: [
        'waybillNo',
        'factoryNo',
        'businessTime',
        'waybillAccountId',
        'waybillAccountNo',
        'shipperName',
        'arrivalCity',
        'receiptState',
        'cargoName',
        'qty',
        'weight',
        'volume',
        'amountType',
        'amount',
        'amountReady',
        'amountBalance',
        'prepayBalance',
        'monthlyBalance',
        'receiptPayBalance',
        'arrearBalance',
        'bankTransferBalance',
        'debtorBalance',
        'unpaidBalance',
        'agentInBalance',
        'agentFreightInBalance',
        'abnormityAmountBalance',
        'monthlyPay',
        'prepayPay',
        'receiptPayPay',
        'arrearPay',
        'bankTransferPay',
        'debtorAmountPay',
        'unpaidPay',
        'agentInPay',
        'agentFreightInPay',
        'abnormityAmountPay',
        'collectPay',
        'collectBalance',
        'agentOutPay',
        'agentOutBalance',
        'agentFreightOutPay',
        'agentFreightOutBalance',
        'debtorAmountPayC',
        'debtorBalanceC',
        'consigneeContact',
        'consigneeName',
        'consigneeMobile',
        'consigneeTel',
        'paymentType'
      ],
      // 对账单详情
      reconciliationDetailHeader: [
        '托运单号',
        '厂家单号',
        '开单时间',
        '发货人',
        '到货城市',
        '回单状态',
        '货名',
        '件数',
        '重量',
        '体积',
        '结算类型',
        '结算总金额',
        '已结算金额',
        '未结算金额',
        '未付现付',
        '未付月结',
        '未付回单付',
        '未付欠付',
        '未付打卡',
        '未付发货人附加费用',
        '未付扣付',
        '未收代收货款',
        '未收代收运费',
        '异常赔付未收',
        '已付月结',
        '已付现付',
        '已付回单付',
        '已付欠付',
        '已付打卡',
        '已付发货人附加费用',
        '已付扣付',
        '已收代收货款',
        '已收代收运费',
        '异常赔付已收',
        '已付到付',
        '未付到付',
        '已付代收货款',
        '未付代收货款',
        '已付代收运费',
        '未付代收运费',
        '已付收货人附加费用',
        '未付收货人附加费用',
        '收货单位',
        '收货人',
        '收货人手机',
        '收货人固话',
        '付款方式'
      ],
      reconciliationDetailRow: [
        'waybillNo',
        'factoryNo',
        'businessTime',
        'shipperName',
        'arrivalCity',
        'receiptState',
        'cargoName',
        'qty',
        'weight',
        'volume',
        'amountType',
        'amount',
        'amountReady',
        'amountBalance',
        'prepayBalance',
        'monthlyBalance',
        'receiptPayBalance',
        'arrearBalance',
        'bankTransferBalance',
        'debtorBalance',
        'unpaidBalance',
        'agentInBalance',
        'agentFreightInBalance',
        'abnormityAmountBalance',
        'monthlyPay',
        'prepayPay',
        'receiptPayPay',
        'arrearPay',
        'bankTransferPay',
        'debtorAmountPay',
        'unpaidPay',
        'agentInPay',
        'agentFreightInPay',
        'abnormityAmountPay',
        'collectPay',
        'collectBalance',
        'agentOutPay',
        'agentOutBalance',
        'agentFreightOutPay',
        'agentFreightOutBalance',
        'debtorAmountPayC',
        'debtorBalanceC',
        'consigneeContact',
        'consigneeName',
        'consigneeMobile',
        'consigneeTel',
        'paymentType'
      ],
      // 挂账账户
      LedgerAccountListHeader: [
        '序号',
        '充值时间',
        '交易单号',
        '充值渠道',
        '充值金额',
        '充值银行名称',
        '账户编号',
        '充值状态',
        '调账时间',
        '调账人',
        '备注'
      ],
      LedgerAccountListRow: [
        'index',
        'accountSubTradeTranDate',
        'accountSubTradeNo',
        'accountSubTradeTypeC',
        'accountSubTradeTranAmt',
        'accountSubTradeBankName',
        'accountSubNo',
        'accountSubTradeTranStatusC',
        'accountSubTradeAdjustTime',
        'accountSubTradeAdjustUserName',
        'accountSubTradeRemark'
      ],
      // 业务管理
      BusinessManageListListHeader: [
        '交易时间',
        '交易单号',
        '交易类型',
        '业务金额',
        '业务来源',
        '业务类型',
        '交易主体',
        '交易对象',
        '交易状态',
        '订单号',
        '订单内容'
      ],
      BusinessManageListRow: [
        'business_trade_create_time',
        'account_sub_trade_no',
        'account_sub_trade_tran_payment_typeC',
        'account_sub_trade_tran_amt',
        'business_trade_business_fromC',
        'business_trade_business_typeC',
        'account_sub_name',
        'account_sub_name',
        'business_trade_statusC',
        'business_trade_business_no',
        'business_trade_business_content'
      ],
      // 手续费账户
      FeeAccountListHeader: [
        '序号',
        '交易时间',
        '流水单号',
        '交易金额',
        '手续费',
        '手续费比例',
        '手续费类型',
        '交易对象',
        '子账户账号',
        '交易状态'
      ],
      FeeAccountListRow: [
        'index',
        'accountSubTradeTranDate',
        'accountSubTradeNo',
        'accountSubTradeTranAmt',
        'accountSubTradeTranFee',
        'percentage',
        'accountSubTradeTypeC',
        'accountSubName',
        'accountSubNo',
        'accountSubTradeTranStatusC'
      ]
    }
  }
}
