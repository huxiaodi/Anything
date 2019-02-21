package com.dllg.ebpay.web.AccountSub;

import com.dllg.ebpay.model.generator.Company;
import com.dllg.ebpay.model.generator.User;
import com.dllg.ebpay.service.accountSub.AccountCentralService;
import com.dllg.ebpay.service.accountSub.AccountSubService;
import com.dllg.framework.controller.BaseController;
import com.dllg.framework.enums.ApiCodeEnum;
import com.dllg.framework.model.ApiResult;
import com.dllg.framework.model.AuthUser;
import com.dllg.framework.security.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.dllg.framework.utils.ApiResultUtil.success;

/**
 * 账户中心
 */
@RestController
@RequestMapping("/accountCentral")
public class AccountCentralController extends BaseController{

    @Autowired
    private AccountCentralService accountCentralService;
    @Autowired
    private Auth auth;


    @RequestMapping(value = "/getAccountCentralInfo")
    public ApiResult getAccountCentralInfo(){

        AuthUser user = auth.getCurrentUser();
        User u = new User();
        u.setUserId(user.getUserId());

        Map m = new HashMap();
            m.put("user",accountCentralService.getAccountCentralInfo(u));               //查询个人信息

            if(user.getUserType()==2) {
                m.put("company", accountCentralService.getAccountCentralEnterpriseInfo(u));  //查询企业
            }else{
                m.put("company", new Company());  //查询企业
            }

        return success(m);
    }



    /**
     * 获得子账户列表
     * @param relationId 关系id
     * @return
     * @throws Exception
     */
    @RequestMapping("/getAccountList")
    public ApiResult getAccountList(@RequestBody String relationId) throws Exception {

        AuthUser user = auth.getCurrentUser();
        if(user.getUserType()==1){
            relationId = user.getUserId();
        }else if(user.getUserType()==2){
            relationId = user.getUserCompanyId();
        }
        return success(accountCentralService.getAccountList(relationId));
    }




}
