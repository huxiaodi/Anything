package com.dllg;

import com.alibaba.fastjson.JSONArray;
import com.dllg.common.CommonUtil;
import com.dllg.ebpay.api.RegistController;
import com.dllg.ebpay.dao.generator.*;
import com.dllg.ebpay.model.generator.*;
import com.dllg.ebpay.model.request.RegistRequest;
import com.dllg.ebpay.service.api.RegistService;
import com.dllg.ebpay.service.pay.PayService;
import com.dllg.framework.model.ApiResult;
import com.dllg.pabank.model.request.CustAcctIdBalanceQueryRequest;
import com.dllg.pabank.model.request.OpenCustAcctIdRequest;
import com.dllg.pabank.model.response.CustAcctIdBalanceQueryResponse;
import com.dllg.pabank.model.response.CustAcctIdBalanceQuerySubResponse;
import com.dllg.pabank.model.response.OpenCustAcctIdResponse;
import com.dllg.pabank.service.PABankService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegistApplicationTests {

    @Autowired
    private PABankService paBankService;
    @Autowired
    private PayService payService;
    @Autowired
    private RegistService registService;
    @Autowired
    private RegistController registController;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private userRoleMapper userRoleMapper;
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Test
    public void contextLoads() throws Exception {
        CustAcctIdBalanceQueryRequest custAcctIdBalanceQueryRequest = new CustAcctIdBalanceQueryRequest();
        custAcctIdBalanceQueryRequest.setFundSummaryAcctNo("15000081987225");
        custAcctIdBalanceQueryRequest.setSubAcctNo("");
        custAcctIdBalanceQueryRequest.setQueryFlag("3");
        custAcctIdBalanceQueryRequest.setPageNum("1");
        custAcctIdBalanceQueryRequest.setReservedMsg("");
        CustAcctIdBalanceQueryResponse custAcctIdBalanceQueryResponse = paBankService.custAcctIdBalanceQuery(custAcctIdBalanceQueryRequest);
        List<CustAcctIdBalanceQuerySubResponse> custAcctIdBalanceQueryResponse1 = JSONArray.parseArray(custAcctIdBalanceQueryResponse.getAcctArray(), CustAcctIdBalanceQuerySubResponse.class);
        System.out.println(custAcctIdBalanceQueryResponse1);
    }

    //接口流程：开户 -> 绑定银行卡 -> 短信验证 -> 绑定信息查询 -> 解绑

    /**
     * 会员开户
     *
     * @throws Exception
     */
    @Test
    public void openCustAcctId() throws Exception {

        OpenCustAcctIdRequest request = new OpenCustAcctIdRequest();
        //Y
        request.setFunctionFlag("1");                        //开户
        request.setFundSummaryAcctNo("15000081987225");     //总账号
        request.setTranNetMemberCode("2018328900");         //注册用户id
        request.setMemberProperty("00");                    //会员属性
        //N
        request.setUserNickname("maikafei");               //用户昵称
        request.setMobile("15142228347");                  //手机号码
        request.setEmail("maika.fei@163.com");            //邮箱


        OpenCustAcctIdResponse pp = paBankService.openCustAcctId(request);
        System.out.println(pp);
    }

    /**
     * 会员注册(个人)
     *
     * @throws Exception
     */
    @Test
    public void personalRegist() throws Exception {
//        ApiRegistRequest apiRegistRequest = new ApiRegistRequest();
//        apiRegistRequest.setUserType(1);
//        apiRegistRequest.setUserName("test0404");
//        apiRegistRequest.setPassword("test0404");
//        apiRegistRequest.setUserVerificationCode("3473");
//        apiRegistRequest.setAccountSubBindRelationId("123456789");
//        apiRegistRequest.setAccountSubBindFrom("徳邻畅途");
//        apiRegistRequest.setUserCnName("jht");
//        apiRegistRequest.setUserMobile("13217518654");
//        apiRegistRequest.setUserCertificateType("身份证");
//        apiRegistRequest.setUserCertificateNumber("320202188810146666");
//        apiRegistRequest.setUserIdCard("320202188810146666");
//        apiRegistRequest.setUserEmail("66@qq.com");
//        apiRegistRequest.setFont(null);
//        apiRegistRequest.setBack(null);
//        ApiResult apiResult = registController.regist(apiRegistRequest);
//        System.out.println(apiResult);
    }

    /**
     * 会员注册(企业)
     *
     * @throws Exception
     */
    @Test
    public void companyRegist() throws Exception {
//        ApiRegistRequest apiRegistRequest = new ApiRegistRequest();
//        apiRegistRequest.setUserType(2);
//        apiRegistRequest.setUserName("test0404");
//        apiRegistRequest.setPassword("test0404");
//        apiRegistRequest.setUserVerificationCode("3473");
//        apiRegistRequest.setAccountSubBindRelationId("123456789");
//        apiRegistRequest.setAccountSubBindFrom("徳邻畅途");
//        apiRegistRequest.setCompanyIsThreeInOne(true);
//        apiRegistRequest.setCompanyUnifiedSocialCreditLdentifier("123456");
//        apiRegistRequest.setFont(null);
//        apiRegistRequest.setCompanyName("dllg");
//        apiRegistRequest.setCompanyPrincipalName("zz");
//        apiRegistRequest.setCompanyPrincipalTel("13215614145");
//        apiRegistRequest.setCompanyPrincipalIdCode("320202188810146666");
//        apiRegistRequest.setCompanyContactTel("13217518654");
//        apiRegistRequest.setCompanyToPublicBank("ICBC");
//        apiRegistRequest.setCompanyToPublicAccountBank("6222021415645565458");
//        apiRegistRequest.setCompanyContact("ff");
//        apiRegistRequest.setCompanyContactIdCard("320202188810146666");
//        apiRegistRequest.setCompanyTaxpayerRegistrationNumber("456789");
//        ApiResult apiResult = registController.regist(apiRegistRequest);
//        System.out.println(apiResult);
    }

    /**
     * 绑定
     *
     * @throws Exception
     */
    @Test
    public void subBind() {
        RegistRequest registRequest = new RegistRequest();
        registRequest.setUserType(2);
        registRequest.setMobile("13217518654");
        registRequest.setUserVerificationCode("3473");
        registRequest.setAccountSubBindRelationId("123456789");
        registRequest.setAccountSubBindFrom("徳邻畅途");
        registRequest.setSubAcctNo("3284000000006128");
        ApiResult apiResult = registController.subBind(registRequest);
        System.out.println(apiResult);
    }

    @Test
    public void payTest() {
//        try {
//            ApiResult apiResult = payService.payMoney();
//            System.out.println(apiResult.getData());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Test
    public void userRole(){

        /*UserExample userExample = new UserExample();
        userExample.createCriteria().andUserTypeEqualTo(2).andUserCertificateTypeIsNotNull()
                .andUserCertificateTypeNotEqualTo("").andUserIsDeleteEqualTo(false); //user_certificate_type
        List<User> users = userMapper.selectByExample(userExample);
        for(User user : users){
            Role role = new Role();
            role.setRoleId(CommonUtil.uuid());
            role.setRoleCompanyId(user.getUserCompanyId());
            role.setRoleName("管理员");
            role.setRoleEnName("admin");
            role.setRoleType(1);
            roleMapper.insertSelective(role);

            userRole userRole = new userRole();
            userRole.setUserRoleRoleId(role.getRoleId());
            userRole.setUserRoleUserId(user.getUserId());
            userRoleMapper.insertSelective(userRole);

            ResourceExample resourceExample = new ResourceExample();
            resourceExample.createCriteria().andResourceTypeEqualTo(1).andResourceIsDeleteEqualTo(false);
            List<Resource> resources = resourceMapper.selectByExample(resourceExample);
            if(resources != null && resources.size() > 0){
                for(Resource resource : resources){
                    RoleResource roleResource = new RoleResource();
                    roleResource.setRoleResourceResourceId(resource.getResourceId());
                    roleResource.setRoleResourceRoleId(role.getRoleId());
                    roleResourceMapper.insertSelective(roleResource);
                }
            }
        }*/
        User user = userMapper.selectByPrimaryKey("359b9de9cc0c4616a81703fdf2866eda");
        Role role = new Role();
        role.setRoleId(CommonUtil.uuid());
        role.setRoleCompanyId(user.getUserCompanyId());
        role.setRoleName("管理员");
        role.setRoleEnName("admin");
        role.setRoleType(1);
        roleMapper.insertSelective(role);

        userRole userRole = new userRole();
        userRole.setUserRoleRoleId(role.getRoleId());
        userRole.setUserRoleUserId(user.getUserId());
        userRoleMapper.insertSelective(userRole);

        ResourceExample resourceExample = new ResourceExample();
        resourceExample.createCriteria().andResourceTypeEqualTo(1).andResourceIsDeleteEqualTo(false);
        List<Resource> resources = resourceMapper.selectByExample(resourceExample);
        if(resources != null && resources.size() > 0){
            for(Resource resource : resources){
                RoleResource roleResource = new RoleResource();
                roleResource.setRoleResourceResourceId(resource.getResourceId());
                roleResource.setRoleResourceRoleId(role.getRoleId());
                roleResourceMapper.insertSelective(roleResource);
            }
        }



    }

}
