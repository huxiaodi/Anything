package pabank;
import java.io.IOException;
import java.util.Map;

import com.pabank.sdk.PABankSDK;

public class Test {

	public static void main(String[] args) throws IOException {
		//JSON请求报文
		String req ="{\"AcctNo\":\"55423\",\"BankSeqNo\":\"2652685651566546\",\"BsnSeqNo\":\"545124541541613245\",\"BussTypeNo\":\"100157\",\"CnsmrSeqNo\":\"00062714985309527897\",\"CorpAgreementNo\":\"133565\",\"EndDate\":\"20170626\",\"RequestSeqNo\":\"78941216\",\"StartDate\":\"20170626\",\"TranStatus\":\"0\"}";
		//初始化配置
		PABankSDK.init("./conf/config.properties");
//		PABankSDK.init("D:/SOFTWARE/eclipse-jee-kepler-R-win321/eclipse-jee-kepler-R-win32/eclipse/workspace_test/Open-API/conf/config.properties");
		//验证开发者
		PABankSDK.getInstance().approveDev();
//		服务调用，传入参数请求报文与服务ID
		Map<String, Object> returnMap =PABankSDK.getInstance().apiInter(req,"QuerySingleInterbankTran");	
//		Map<String, Object> returnMap =PABankSDK.getInstance().apiInter(req,"QuerySingleInterbankTran","AAA");	
//		System.err.println(returnMap.toString());
		
//		PABankSDK.getInstance().apiInter(c);
		
//		Map<String, Object> returnMap = PABankSDK.getInstance().apiInter(c);
//		Map<String, Object> returnMap = PABankSDK.getInstance().apiInter(c,"BBB");
		System.err.println(returnMap);
	}
	
}
