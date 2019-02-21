package com.dllg.ebpay.web;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.dllg.common.CommonUtil;
import com.dllg.ebpay.configurer.EbpayProperties;
import com.dllg.ebpay.dao.generator.*;
import com.dllg.ebpay.model.generator.*;
import com.dllg.ebpay.service.home.HomeService;
import com.dllg.ebpay.service.msgCode.MsgCodeService;
import com.dllg.framework.controller.BaseController;
import com.dllg.framework.exception.AnyException;
import com.dllg.framework.model.ApiResult;
import com.dllg.framework.utils.FileUploadUtils;
import com.dllg.pabank.model.request.CustAcctIdBalanceQueryRequest;
import com.dllg.pabank.model.request.MemberBindQueryRequest;
import com.dllg.pabank.model.request.Params;
import com.dllg.pabank.model.response.CustAcctIdBalanceQueryResponse;
import com.dllg.pabank.model.response.CustAcctIdBalanceQuerySubResponse;
import com.dllg.pabank.model.response.MemberBindQueryResponse;
import com.dllg.pabank.model.response.MemberBindQuerySubResponse;
import com.dllg.pabank.service.PABankService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 主页面
 */
@RestController
@RequestMapping("/home")
public class HomeController extends BaseController {

    private final static Logger logger = LogManager.getLogger(HomeController.class);

    @Autowired
    private HomeService homeService;
    @Autowired
    private FileUploadUtils fileUploadUtils;
    @Autowired
    private PABankService paBankService;
    @Autowired
    private EbpayProperties ebpayProperties;
    @Autowired
    private MsgCodeService msgCodeService;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private com.dllg.ebpay.dao.generator.userRoleMapper userRoleMapper;
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @PostMapping("/user")
    public ApiResult user(@RequestBody User user) {
        User user2 = homeService.selectUser(user.getUserId());
        return success(user2);
    }

    @PostMapping("/bankCard")
    public ApiResult bankCard(@RequestBody User user) {
        List<BankCard> list = homeService.selectBankCard(user.getUserId());
        return success(list);
    }

    @PostMapping("/upload")
    public void upload(MultipartFile font, String userId) {
        String userHead = fileUploadUtils.upload(font, userId, FileUploadUtils.FOLDER_USER);
        User user = new User();
        user.setUserHeadImg(userHead);
        user.setUserId(userId);
        homeService.upload(user);
    }

    @PostMapping("/MemberBindQuery")
    public ApiResult MemberBindQuery(@RequestBody MemberBindQueryRequest request) throws Exception {
        request.setFundSummaryAcctNo(ebpayProperties.getFundSummaryAcctNo());
        MemberBindQueryResponse memberBindQueryResponse = paBankService.memberBindQuery(request);
        List<MemberBindQuerySubResponse> memberBindQuerySubResponse = JSONArray.parseArray(memberBindQueryResponse.getTranItemArray(), MemberBindQuerySubResponse.class);
        logger.debug(JSON.toJSONString(memberBindQueryResponse));
        if ("000000".equals(memberBindQueryResponse.getTxnReturnCode())) {
            return success(memberBindQuerySubResponse);
        } else {
            throw new AnyException(memberBindQueryResponse.getTxnReturnMsg());
        }
    }

    @PostMapping("/changeAgentMobile")
    public void changeAgentMobile(@RequestBody User user) {
        homeService.changeAgentMobile(user);
    }

    @PostMapping("/getCheckNum")
    public void getCheckNum(@RequestBody Params params) throws Exception {
        msgCodeService.sendVerificationCode(params.getUserMobile(), "您的手机验证码为：");
    }

    @PostMapping("/changePayPassword")
    public ApiResult changePayPassword(@RequestBody Params params) {
        boolean flag = msgCodeService.judgeCode(params.getUserMobile(), params.getCheckNum());
        if (flag) {
            return success(homeService.changePayPassword(params));
        } else {
            throw new AnyException("验证码错误");
        }
    }

    @PostMapping("/CustAcctIdBalanceQuery")
    public ApiResult CustAcctIdBalanceQuery(@RequestBody CustAcctIdBalanceQueryRequest request) throws Exception {
        request.setFundSummaryAcctNo(ebpayProperties.getFundSummaryAcctNo());
        CustAcctIdBalanceQueryResponse custAcctIdBalanceQueryResponse = paBankService.custAcctIdBalanceQuery(request);
        List<CustAcctIdBalanceQuerySubResponse> custAcctIdBalanceQuerySubResponses = JSONArray.parseArray(custAcctIdBalanceQueryResponse.getAcctArray(), CustAcctIdBalanceQuerySubResponse.class);
        logger.debug(JSON.toJSONString(custAcctIdBalanceQueryResponse));
        if ("000000".equals(custAcctIdBalanceQueryResponse.getTxnReturnCode())) {
            return success(custAcctIdBalanceQuerySubResponses);
        } else {
            throw new AnyException(custAcctIdBalanceQueryResponse.getTxnReturnMsg());
        }
    }

    @GetMapping("/getAllUser")
    public ApiResult getAllUser() {
        if (homeService.getAllUser().size() != 0) {
            return success(homeService.getAllUser());
        } else {
            return null;
        }
    }

    @GetMapping("/putRole")
    public ApiResult putRole() {
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andRoleTypeEqualTo(1);
        List<Role> roleList = roleMapper.selectByExample(roleExample);
        if(roleList != null && roleList.size() > 0){
            return success("已存在");
        }
        UserExample userExample = new UserExample();
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
        }
        return  success("创建完成");
    }
}

