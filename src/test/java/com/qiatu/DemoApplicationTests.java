package com.dllg;

import cfca.sadk.util.Base64;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dcfs.esb.ftp.client.https.FtpGet;
import com.dcfs.esb.ftp.server.msg.FileMsgBean;
import com.dllg.common.SignUtil;
import com.dllg.ebpay.dao.generator.AccountSubMapper;
import com.dllg.ebpay.model.dto.AccountSubDto;
import com.dllg.ebpay.model.generator.AccountSub;
import com.dllg.ebpay.model.generator.AccountSubExample;
import com.dllg.ebpay.service.accountSub.AccountSubService;
import com.dllg.ebpay.service.api.RegistService;
import com.dllg.ebpay.service.resource.ResourceService;
import com.dllg.ebpay.service.trading.RefundService;
import com.dllg.framework.model.ApiResult;
import com.dllg.pabank.model.request.*;
import com.dllg.pabank.model.response.*;
import com.dllg.pabank.service.PABankService;
import com.dllg.ebpay.service.pay.PayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.test.context.junit4.SpringRunner;



import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private PABankService paBankService;
	@Autowired
	private PayService payService;
	@Autowired
	private AccountSubService accountSubService;
	@Autowired
	private RegistService registService;
	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;
	@Autowired
	private AccountSubMapper accountSubMapper;
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private RefundService refundService;


	@Test
	public void deleteScheduler() throws Exception{
		Scheduler sched = schedulerFactoryBean.getScheduler();
		JobKey jobKey = new JobKey("paAccountTradeResultFactoryBean");
//		TriggerKey triggerKey = new TriggerKey("paAccountTradeResultCronTriggerFactoryBean");
//		sched.pauseTrigger(triggerKey);// 停止触发器
//		sched.unscheduleJob(triggerKey);// 移除触发器
//		sched.deleteJob(jobKey);
	}

	@Test
	public void contextLoads() throws Exception {
		CustAcctIdBalanceQueryRequest custAcctIdBalanceQueryRequest = new CustAcctIdBalanceQueryRequest();
		custAcctIdBalanceQueryRequest.setFundSummaryAcctNo("15000081987225");
		custAcctIdBalanceQueryRequest.setSubAcctNo("3284000000006197");
		custAcctIdBalanceQueryRequest.setQueryFlag("2");
		custAcctIdBalanceQueryRequest.setPageNum("1");
		custAcctIdBalanceQueryRequest.setReservedMsg("");
		CustAcctIdBalanceQueryResponse custAcctIdBalanceQueryResponse = paBankService.custAcctIdBalanceQuery(custAcctIdBalanceQueryRequest);
		List<CustAcctIdBalanceQuerySubResponse> custAcctIdBalanceQueryResponse1 = JSONArray.parseArray(custAcctIdBalanceQueryResponse.getAcctArray(), CustAcctIdBalanceQuerySubResponse.class);
		System.out.println(custAcctIdBalanceQueryResponse1);
	}

	//接口流程：开户 -> 绑定银行卡 -> 短信验证 -> 绑定信息查询 -> 解绑

	/**
	 * 会员开户
	 * @throws Exception
	 */
	@Test
	public void openCustAcctId() throws Exception {

		OpenCustAcctIdRequest request = new OpenCustAcctIdRequest();
		//Y
		request.setFunctionFlag("1");                        //开户
		request.setFundSummaryAcctNo("15000081987225");     //总账号
		request.setTranNetMemberCode("20180606");         //注册用户id
		request.setMemberProperty("00");                    //会员属性
		//N
		request.setUserNickname("maikafei");               //用户昵称
		request.setMobile("15142228347");                  //手机号码
		request.setEmail("maika.fei@163.com");            //邮箱


		OpenCustAcctIdResponse pp= paBankService.openCustAcctId(request);
		System.out.println(pp);
	}

	/**
	 * 会员绑定信息查询
	 * @throws Exception
	 */
	@Test
	public void memberBindQuery() throws Exception {

		MemberBindQueryRequest mm = new MemberBindQueryRequest();
		//Y
		mm.setQueryFlag("1");                            //1全部2单个
		mm.setFundSummaryAcctNo("15000081987225");     //总账户
		mm.setSubAcctNo("3284000000006177");           //子账户
		//mm.setSubAcctNo("3284000000011528");           //子账户
		mm.setPageNum("1");                             //页码


		MemberBindQueryResponse ret = paBankService.memberBindQuery(mm);

		JSONArray newList = JSON.parseArray(ret.getTranItemArray());
		System.out.println(newList);

		System.out.println(ret);
	}

	/**
	 * 查询子账户余额
	 * @throws Exception
	 */
	@Test
	public void custAcctIdBalanceQuery() throws Exception {

		CustAcctIdBalanceQueryRequest cb = new CustAcctIdBalanceQueryRequest();
		cb.setFundSummaryAcctNo("15000081987225");      //总账户
		cb.setQueryFlag("2");                            //2普通子账户 3功能之账户
		cb.setSubAcctNo("3284000000006067");           //之账户
		cb.setPageNum("1");                             //页码

		CustAcctIdBalanceQueryResponse ret = paBankService.custAcctIdBalanceQuery(cb);

		JSONArray newList = JSON.parseArray(ret.getAcctArray());
		System.out.println(newList);

	}

	@Test
	public void SmallAmountTransferQuery() throws Exception {

        SmallAmountTransferQueryRequest smallAmountTransferQueryRequest = new SmallAmountTransferQueryRequest();
        smallAmountTransferQueryRequest.setFundSummaryAcctNo("15000081987225");
        smallAmountTransferQueryRequest.setOldTranSeqNo("N9542020180418q1432221");
        smallAmountTransferQueryRequest.setTranDate("20180418");

        SmallAmountTransferQueryResponse smallAmountTransferQueryResponse = paBankService.smallAmountTransferQuery(smallAmountTransferQueryRequest);

    }

	/**
	 * 会员绑定提现账户-银联鉴权(绑定银行卡)---------------------------------
	 * @throws Exception
	 */
	@Test
	public void bindRelateAcctUnionPay() throws Exception {

		BindRelateAcctUnionPayRequest bb = new BindRelateAcctUnionPayRequest();
		bb.setFundSummaryAcctNo("15000081987225");    //资金汇总账号
		bb.setSubAcctNo("3284000000006067");         //子账户账号
		bb.setTranNetMemberCode("2018328900");        //交易网会员代码(用户注册id)
		bb.setMemberName("关鹏");                     //会员名称
		bb.setMemberGlobalType("1");                  //身份证
		bb.setMemberGlobalId("210311198308011517"); //证件号码
		bb.setMemberAcctNo("4367420580600175188");  //银行卡号
		bb.setBankType("2");                          //1本行2他行

		bb.setAcctOpenBranchName("中国建设银行");      //开户行名称
		bb.setCnapsBranchId("105100000017");          //大小额行号(对应银行列表找到)
		bb.setEiconBankBranchId("105100000017");                  //超级网银行号
		bb.setMobile("15142228347");       //


		BindRelateAcctUnionPayResponse ret = paBankService.bindRelateAcctUnionPay(bb);


		System.out.println(ret);
	}


	/**
	 * 会员绑定提现账户(确认绑定填写【短信验证码】)
	 * 回填银联【鉴权短信码】
	 * @throws Exception
	 */
	@Test
	public void bindRelateAccReUnionPay() throws Exception {

		BindRelateAccReUnionPayRequest cb = new BindRelateAccReUnionPayRequest();
		cb.setFundSummaryAcctNo("15000081987225");  //资金汇总账号
		cb.setTranNetMemberCode("2018328900");      //交易网会员代码
		cb.setSubAcctNo("3284000000006067");       //子账户账号
		cb.setMemberAcctNo("4367420580600175188"); //会员账号(银行卡号)
		cb.setMessageCheckCode("088650");           //短信验证码  查询地址：https://my-st1.orangebank.com.cn/corporbank/otp.jsp

		BindRelateAccReUnionPayResponse ret = paBankService.bindRelateAccReUnionPay(cb);

		System.out.println(ret);

	}


	/**
	 * 会员解绑体现账户(解绑银行卡)
	 * @throws Exception
	 */
	@Test
	public void unbindRelateAcct() throws Exception{

		UnbindRelateAcctRequest ur = new UnbindRelateAcctRequest();
		ur.setFunctionFlag("1");                       //1解绑
		ur.setFundSummaryAcctNo("15000081987225");   //总帐号
		ur.setTranNetMemberCode("b85ec1ac799d4cab8e8e868fe9abd449");       //会员代码
		ur.setSubAcctNo("3284000000011528");        //子帐号
		ur.setMemberAcctNo("6227234567788899001"); //会员帐号(卡号)
        ur.setReservedMsg("");
        UnbindRelateAcctResponse ret = paBankService.unbindRelateAcct(ur);

		System.out.println(ret);
	}


	/**
	 * 查询子账户余额
	 * @throws Exception
	 */
	@Test
	public void supAcctIdBalanceQueryRequest() throws Exception {
		SupAcctIdBalanceQueryRequest supAcctIdBalanceQueryRequest = new SupAcctIdBalanceQueryRequest();
		supAcctIdBalanceQueryRequest.setFundSummaryAcctNo("15000081987225");
		SupAcctIdBalanceQueryResponse custAcctIdBalanceQueryResponse = paBankService.supAcctIdBalanceQuery(supAcctIdBalanceQueryRequest);
		System.out.println(supAcctIdBalanceQueryRequest);
	}
	/**
	 * 挂账
	 * @throws Exception
	 */
	@Test
	public void registerBillRequest() throws Exception {
		AccountSubExample accountSubExample = new AccountSubExample();
		accountSubExample.createCriteria().andAccountSubNoEqualTo("3284000000021039");
		AccountSub accountSub = accountSubMapper.selectByExample(accountSubExample).stream().findFirst().orElse(null);
		RegisterBillRequest registerBillRequest = new RegisterBillRequest();
		registerBillRequest.setFundSummaryAcctNo("15000081987225");
		registerBillRequest.setSubAcctNo(accountSub.getAccountSubNo());
		registerBillRequest.setSubAcctName(accountSub.getAccountSubName());
		registerBillRequest.setTranNetMemberCode(accountSub.getAccountSubRelationId());
		registerBillRequest.setTranAmt("10000");
		registerBillRequest.setCcy("RMB");
		registerBillRequest.setRemark("");
		registerBillRequest.setReservedMsg("");
		RegisterBillResponse custAcctIdBalanceQueryResponse = paBankService.registerBill(registerBillRequest);
		System.out.println(custAcctIdBalanceQueryResponse);
	}
	@Test
	public void BankWithdrawCashBackQuery() throws Exception {
		BankWithdrawCashBackQueryRequest bankWithdrawCashBackQueryRequest = new BankWithdrawCashBackQueryRequest();
		bankWithdrawCashBackQueryRequest.setFunctionFlag("1");
		bankWithdrawCashBackQueryRequest.setFundSummaryAcctNo("15000081987225");
		bankWithdrawCashBackQueryRequest.setEndDate("20180411");
		bankWithdrawCashBackQueryRequest.setStartDate("20180411");
		bankWithdrawCashBackQueryRequest.setReservedMsg("");

		BankWithdrawCashBackQueryResponse bankWithdrawCashBackQueryResponse = paBankService.bankWithdrawCashBackQuery(bankWithdrawCashBackQueryRequest);
	}
	@Test
	public void CommonTransferRechargeQuery
            () throws Exception {
        CommonTransferRechargeQueryRequest commonTransferRechargeQuery = new CommonTransferRechargeQueryRequest();
        commonTransferRechargeQuery.setFunctionFlag("0");
        commonTransferRechargeQuery.setFundSummaryAcctNo("15000081987225");
        commonTransferRechargeQuery.setPageNum("1");
        commonTransferRechargeQuery.setStartDate("20150101");
        commonTransferRechargeQuery.setEndDate("20180413");


        CommonTransferRechargeQueryResponse commonTransferRechargeQueryResponse = paBankService.commonTransferRechargeQuery(commonTransferRechargeQuery);
	}
	//对账
	@Test
	public void ReconciliationDocumentQuery() throws Exception {
        ReconciliationDocumentQueryRequest reconciliationDocumentQueryRequest = new ReconciliationDocumentQueryRequest();
        reconciliationDocumentQueryRequest.setFundSummaryAcctNo("15000081987225");
        reconciliationDocumentQueryRequest.setFileType("YE");
        reconciliationDocumentQueryRequest.setFileDate("20180412");

        ReconciliationDocumentQueryResponse commonTransferRechargeQueryResponse = paBankService.reconciliationDocumentQuery(reconciliationDocumentQueryRequest);
	}
	//下载对账文件
	@Test
	public void FtpGet() throws Exception {
		FileMsgBean fileMsgBean = new FileMsgBean();
		FtpGet ftpGet = new FtpGet("/ejzb/3284/YE2018041232841.txt.enc","D://1.txt.enc",false,null,"c7dafe2a-06f9-4d9f-8846-d2fcc96874c0",fileMsgBean);
		ftpGet.doGetFile();
		String ALG = "DesEde/CBC/PKCS5Padding";
		String fsrc = "D://1.txt";
		String key = "Qke+dWuKedBh63QriOO9n+B/buGx356c";
		String srcFile = fsrc + ".enc";
		String zipFile = srcFile + ".zip";
		String desFile = srcFile + ".pre";
		byte[] bkey = Base64.decode(key.getBytes());
		// 解密
		SignUtil.decrypt(srcFile, zipFile, bkey, ALG, "DesEde", null);
//		SignUtil.uncompress(zipFile, desFile);
		System.out.println(fileMsgBean.getFileRetMsg());
	}
	@Test
	public void payTest(){
//		try {
//			ApiResult apiResult = payService.payMoney();
//			System.out.println(apiResult.getData());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	@Test
	public void test(){
		try {
			OpenCustAcctIdRequest openCustAcctIdRequest = new OpenCustAcctIdRequest();
			openCustAcctIdRequest.setFunctionFlag("1");
			openCustAcctIdRequest.setFundSummaryAcctNo("15000081987225");
			openCustAcctIdRequest.setTranNetMemberCode("2222");
			openCustAcctIdRequest.setMemberProperty("00");
			openCustAcctIdRequest.setUserNickname("");
			openCustAcctIdRequest.setMobile("");
			openCustAcctIdRequest.setEmail("");
			openCustAcctIdRequest.setReservedMsg("");
			OpenCustAcctIdResponse openCustAcctIdResponse = paBankService.openCustAcctId(openCustAcctIdRequest);
			System.out.println(openCustAcctIdResponse);
		} catch (Exception e) {
            e.printStackTrace();
        }
	}
	@Test
	public void AccountSubService(){
//		try {
//			List<AccountSubDto> accountSubs = accountSubService.getAccountSubs();
//			System.out.println(accountSubs);
//		} catch (Exception e) {
//            e.printStackTrace();
//        }
	}
	@Test
	public void getPaymentMessage(){
		try {
			Map<String, Object> accountSubs = registService.getPaymentMessage("1");
			System.out.println(accountSubs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void singleTransactionStatusQuery(){
		try {
            SingleTransactionStatusQueryRequest request = new SingleTransactionStatusQueryRequest();
            request.setFundSummaryAcctNo("15000081987225");
            request.setFunctionFlag("3");
            request.setTranNetSeqNo("N9542020180409q1422049");
            request.setSubAcctNo("3284000000006338");

            SingleTransactionStatusQueryResponse response = paBankService.singleTransactionStatusQuery(request);
            System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    @Test
	public void QueryCustAcctIdByThirdCustId(){
		try {
            QueryCustAcctIdByThirdCustIdRequest queryCustAcctIdByThirdCustIdRequest = new QueryCustAcctIdByThirdCustIdRequest();
            queryCustAcctIdByThirdCustIdRequest.setFundSummaryAcctNo("15000081987225");
            queryCustAcctIdByThirdCustIdRequest.setTranNetMemberCode("6b3f735066204c06b41b727278e56f3d");
            QueryCustAcctIdByThirdCustIdResponse response = paBankService.queryCustAcctIdByThirdCustId(queryCustAcctIdByThirdCustIdRequest);
            System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	//修改手机号
	@Test
	public void applyForChangeOfCellPhoneNum() throws Exception {

		ApplyForChangeOfCellPhoneNumRequest bb = new ApplyForChangeOfCellPhoneNumRequest();
		bb.setFundSummaryAcctNo("15000092454462");    //资金汇总账号
		bb.setTranNetMemberCode("f40cedad6e8f4173bbad6019183120eb");        //交易网会员代码(用户注册id)
		bb.setSubAcctNo("3284000000006766");         //子账户账号
		bb.setModifyType("2");                        //修改方式 - 1：短信验证码 2：银联鉴权
		bb.setNewMobile("15142228347");

		bb.setBankCardNo("4367420580600175188");    //银行卡号 - 当修改方式为2时必输。
		ApplyForChangeOfCellPhoneNumResponse ret = paBankService.applyForChangeOfCellPhoneNum(bb);

		System.out.println(ret);
	}


	@Test
	public void backfillDynamicPassword() throws Exception {

		BackfillDynamicPasswordRequest bb = new BackfillDynamicPasswordRequest();
		bb.setFundSummaryAcctNo("15000092454462");    //资金汇总账号
		bb.setTranNetMemberCode("f40cedad6e8f4173bbad6019183120eb");        //交易网会员代码(用户注册id)
		bb.setSubAcctNo("3284000000006766");         //子账户账号
		bb.setModifyType("1");                        //修改方式 - 1：短信验证码 2：银联鉴权

        bb.setMessageOrderNo("1806070038537054");    //短信指令号
		bb.setMessageCheckCode("525294");             //短信验证码
		BackfillDynamicPasswordResponse ret = paBankService.backfillDynamicPassword(bb);

		System.out.println(ret);
	}

	@Test
	public void sf0008() throws Exception {
		com.sdb.payclient.core.PayclientInterfaceUtil  util = new com.sdb.payclient.core.PayclientInterfaceUtil();
		com.ecc.emp.data.KeyedCollection input = new com.ecc.emp.data.KeyedCollection("input");
		com.ecc.emp.data.KeyedCollection output = new com.ecc.emp.data.KeyedCollection("output");

		input.put("masterId","2000903938");  //商户号，注意生产环境上要替换成商户自己的生产商户号
		input.put("orderId","20009039382018071234653477");  //订单号，严格遵守格式：商户号+8位日期YYYYMMDD+8位流水

		try {
			output = util.execute(input,"SF0008"); //执行发送（SF0008）单笔订单状态查询请求，并返回结果对象
			String errorCode = (String)output.getDataValue("errorCode");
			String errorMsg = (String)output.getDataValue("errorMsg");

			System.out.println("---单笔订单支付状态查询结果详细信息---"+output);

			if((errorCode == null || errorCode.equals(""))&& (errorMsg == null || errorMsg.equals(""))){
				System.out.println("---订单状态---"+output.getDataValue("status"));
				System.out.println("---支付完成时间---"+output.getDataValue("date"));
				System.out.println("---手续费金额---"+output.getDataValue("charge"));
				System.out.println("---商户号---"+output.getDataValue("masterId"));
				System.out.println("---订单号---"+output.getDataValue("orderId"));
				System.out.println("---币种---"+output.getDataValue("currency"));
				System.out.println("---订单金额---"+output.getDataValue("amount"));
				System.out.println("---下单时间---"+output.getDataValue("paydate"));
				System.out.println("---商品描述---"+output.getDataValue("objectName"));
				System.out.println("---订单有效期---"+output.getDataValue("validtime"));
				System.out.println("---备注---"+output.getDataValue("remark"));
				System.out.println("---本金清算标志---"+output.getDataValue("settleflg"));  //1已清算，0待清算
				System.out.println("---本金清算时间---"+output.getDataValue("settletime"));
				System.out.println("---手续费清算标志---"+output.getDataValue("chargeflg"));  //1已清算，0待清算
				System.out.println("---手续费清算时间---"+output.getDataValue("chargetime"));
				System.out.println("---商户订单类型---"+output.getDataValue("orderType"));
			}else{
				System.out.println("---错误码---"+output.getDataValue("errorCode"));
				System.out.println("---错误说明---"+output.getDataValue("errorMsg"));
			}

		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void queryRefundStatus() throws Exception{
		Map<String, Object> stringObjectMap = refundService.queryRefundStatus("1");
		System.out.println(stringObjectMap);
	}

}
