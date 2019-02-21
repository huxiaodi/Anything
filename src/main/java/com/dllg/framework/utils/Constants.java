package com.dllg.framework.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: Constants
 * @Description: 常量
 * @date 2018年10月22日
 */
public class Constants {


	//////////////////////////////////////////////////////////////
    // 发票主表导入
	//////////////////////////////////////////////////////////////
	public static Map<String,String> fpMainImport = new HashMap<>();
	static {
		fpMainImport.put("单据号", "djh");
		fpMainImport.put("购方名称", "gfmc");
		fpMainImport.put("购方税号", "gfsh");
		fpMainImport.put("购方银行账号", "gfyhzh");
		fpMainImport.put("购方地址电话", "gfdzdh");
		fpMainImport.put("备注", "bz");
		fpMainImport.put("复核人", "fhr");
		fpMainImport.put("收款人", "skr");
		fpMainImport.put("商品编码版本号", "spbmbbh");
		fpMainImport.put("含税标志", "hsbz");

	}
    //////////////////////////////////////////////////////////////


	//////////////////////////////////////////////////////////////
	// 发票明细表导入
	//////////////////////////////////////////////////////////////
	public static Map<String,String> fpDetailImport = new HashMap<>();
	static {
		fpDetailImport.put("单据号", "djh");
		fpDetailImport.put("序号", "xh");
		fpDetailImport.put("商品名称", "spmc");
		fpDetailImport.put("规格型号", "ggxh");
		fpDetailImport.put("计量单位", "jldw");
		fpDetailImport.put("商品编码", "spbm");
		fpDetailImport.put("企业商品编码", "qyspbm");
		fpDetailImport.put("优惠政策标识", "syyhzcbz");
		fpDetailImport.put("零税率标识", "lslbz");
		fpDetailImport.put("优惠政策说明", "yhzcsm");
		fpDetailImport.put("单价", "dj");
		fpDetailImport.put("数量", "sl");
		fpDetailImport.put("金额", "je");
		fpDetailImport.put("税率", "slv");
		fpDetailImport.put("扣除额", "kce");
	}
	//////////////////////////////////////////////////////////////


	//////////////////////////////////////////////////////////////
	// 财务贴息导入
	//////////////////////////////////////////////////////////////
	public static Map<String,String> interestImport = new HashMap<>();
	static {
		interestImport.put("收到日期", "interestManagerDateGet");
		interestImport.put("汇票编号", "interestManagerTicketNo");
		interestImport.put("前手背书人", "interestManagerCustomerName");
		interestImport.put("金额", "interestManagerMoneyAll");
		interestImport.put("开票日期", "interestManagerDateStart");
		interestImport.put("汇票到期日", "interestManagerDateEnd");
		interestImport.put("出票人", "interestManagerTicketUser");
		interestImport.put("付款行", "interestManagerTicketBank");
//		interestImport.put("贴息减免天数", "interestManagerFreeDay");
//		interestImport.put("计息天数", "interestManagerFreeDayY");
		interestImport.put("日贴息率", "interestManagerInterest");
		interestImport.put("贴息金额", "interestManagerMoney");
		interestImport.put("货款使用", "interestManagerMemo");
	}
	//////////////////////////////////////////////////////////////






}
