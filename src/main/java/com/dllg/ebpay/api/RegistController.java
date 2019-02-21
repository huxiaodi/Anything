package com.dllg.ebpay.api;

import com.dllg.ebpay.model.dto.UserAccountInfoDto;
import com.dllg.ebpay.model.generator.AccountSubBind;
import com.dllg.ebpay.model.generator.Company;
import com.dllg.ebpay.model.generator.User;
import com.dllg.ebpay.model.request.ApiRegistRequest;
import com.dllg.ebpay.model.request.RegistRequest;
import com.dllg.ebpay.service.account.UserAccountService;
import com.dllg.ebpay.service.api.RegistService;
import com.dllg.ebpay.service.msgCode.MsgCodeService;
import com.dllg.framework.model.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

import static com.dllg.framework.utils.ApiResultUtil.error;
import static com.dllg.framework.utils.ApiResultUtil.success;

@RestController
@RequestMapping("/api")
@Api(value = "/api", description = "用户业务平台调用的注册相关接口")
public class RegistController {

    @Autowired
    private MsgCodeService msgCodeService;
    @Autowired
    private RegistService registService;
    @Autowired
    private UserAccountService userAccountService;

    /**
     * 注册获取验证码
     *
     * @param mobile
     * @return
     */
    @ApiOperation("注册获取验证码")
    @PostMapping("/sendRegistVerificationCode")
    @ApiImplicitParam(name = "mobile", dataType = "string", value = "手机号", required = true)
    public ApiResult sendRegistVerificationCode(@RequestBody String mobile) {
        List<User> userList = registService.checkUserMobile(mobile);
        if (userList.size() != 0) {
            return error("该手机已注册过");
        }
        List<Company> companyList = registService.checkCompanyContactTel(mobile);
        if (companyList.size() != 0) {
            return error("该手机已注册过");
        }
        msgCodeService.sendVerificationCode(mobile, "您的验证码为：");
        return success("验证码发送成功");
    }

    /**
     * 绑定获取验证码
     *
     * @param registRequest
     * @return
     */
    @ApiOperation("绑定获取验证码")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userType", dataType = "integer", value = "用户类型(1：个人/2：企业)", required = true),
                    @ApiImplicitParam(name = "SubAcctNo", dataType = "string", value = "子账户号", required = true),
                    @ApiImplicitParam(name = "mobile", dataType = "string", value = "手机号", required = true)
            }
    )
    @PostMapping("/sendBindVerificationCode")
    public ApiResult sendBindVerificationCode(@RequestBody @ApiIgnore() RegistRequest registRequest) {
        ApiResult apiResult = registService.checkBindMobile(registRequest);
        if (apiResult.getCode() == 1) {
            msgCodeService.sendVerificationCode(registRequest.getMobile(), "您的验证码为：");
            return success("验证码发送成功");
        } else {
            return apiResult;
        }
    }

    /**
     * 验证是否已绑定/注册
     */
    @ApiOperation("验证是否已绑定/注册")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userType", dataType = "integer", value = "用户类型(1：个人/2：企业)", required = true),
                    @ApiImplicitParam(name = "accountSubBindRelationId", dataType = "string", value = "关联id", required = true),
                    @ApiImplicitParam(name = "companyIsThreeInOne", dataType = "boolean", value = "是否三证合一(企业),是则传统一信用代码，否则传营业执照号码+税务登记号+组织机构代码", required = true),
                    @ApiImplicitParam(name = "companyUnifiedSocialCreditLdentifier", dataType = "string", value = "统一信用代码(企业)", required = true),
                    @ApiImplicitParam(name = "companyLicenseNo", dataType = "string", value = "营业执照号码(企业)", required = true),
                    @ApiImplicitParam(name = "companyOrganizationCode", dataType = "string", value = "组织机构代码(企业)", required = true),
                    @ApiImplicitParam(name = "companyTaxNo", dataType = "string", value = "税务登记号(企业)", required = true),
                    @ApiImplicitParam(name = "userIdCard", dataType = "string", value = "身份证号(个人)", required = true)
            }
    )
    @PostMapping("/isBind")
    public ApiResult isBind(@RequestBody @ApiIgnore() RegistRequest registRequest) {
        List<AccountSubBind> accountSubBindList = registService.checkIsBind(registRequest.getAccountSubBindRelationId());
        if (accountSubBindList.size() != 0) {
            return success("已绑定");
        } else {
            return isRegist(registRequest);
        }
    }

    /**
     * 是否已注册
     */
    public ApiResult isRegist(RegistRequest registRequest) {
        if (registRequest.getAccountSubBindRelationId() == null) {
            return error("关联id不能为空");
        }
        if (registRequest.getCompanyIsThreeInOne() == null) {
            return error("是否三证合一不能为空");
        }
        if (registRequest.getUserType() == 1) {
            if (registRequest.getUserIdCard() != null) {
                List<User> userList = registService.checkPersonalRegist(registRequest.getUserIdCard());
                if (userList.size() != 0) {
                    return success("已注册");
                } else {
                    return success("未注册");
                }
            } else {
                return error("身份证号不能为空");
            }
        } else {
            if (registRequest.getCompanyIsThreeInOne() == true) {
                if (registRequest.getCompanyUnifiedSocialCreditLdentifier() != null) {
                    List<Company> companyList = registService.checkCompanyRegist(registRequest.getCompanyUnifiedSocialCreditLdentifier());
                    if (companyList.size() != 0) {
                        return success("已注册");
                    } else {
                        return success("未注册");
                    }
                } else {
                    return error("统一信用代码不能为空");
                }
            } else {
                if (registRequest.getCompanyLicenseNo() == null) {
                    return error("营业执照号码不能为空");
                }
                if (registRequest.getCompanyTaxNo() == null) {
                    return error("组织机构代码不能为空");
                }
                if (registRequest.getCompanyOrganizationCode() == null) {
                    return error("税务登记证号不能为空");
                }
                List<Company> companyList = registService.checkCompanyRegist(registRequest);
                if (companyList.size() != 0) {
                    return success("已注册");
                } else {
                    return success("未注册");
                }
            }
        }
    }

    /**
     * 绑定
     *
     * @param registRequest
     * @return
     */
    @ApiOperation("绑定")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userType", dataType = "integer", value = "用户类型(1：个人/2：企业)", required = true),
                    @ApiImplicitParam(name = "SubAcctNo", dataType = "string", value = "子账户号", required = true),
                    @ApiImplicitParam(name = "mobile", dataType = "string", value = "手机号", required = true),
                    @ApiImplicitParam(name = "userVerificationCode", dataType = "string", value = "手机验证码", required = true),
                    @ApiImplicitParam(name = "accountSubBindRelationId", dataType = "string", value = "关联id", required = true),
                    @ApiImplicitParam(name = "accountSubBindFrom", dataType = "string", value = "注册来源", required = true)
            }
    )
    @PostMapping("/subBind")
    public ApiResult subBind(@RequestBody @ApiIgnore() RegistRequest registRequest) {
        List<AccountSubBind> accountSubBindList = registService.checkIsBind(registRequest.getAccountSubBindRelationId());
        if (accountSubBindList.size() != 0) {
            return error("已绑定");
        }
        // 校对验证码
        boolean flag = msgCodeService.judgeCode(registRequest.getMobile(), registRequest.getUserVerificationCode());
        if (flag) {
            ApiResult apiResult = registService.subBind(registRequest);
            return apiResult;
        } else {
            return error("验证码错误");
        }

    }

    /**
     * 查询已绑子账户
     *
     * @param registRequest
     * @return
     */
    @ApiOperation("查询已绑子账户")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userType", dataType = "integer", value = "用户类型(1：个人/2：企业)", required = true),
                    @ApiImplicitParam(name = "accountSubBindRelationId", dataType = "string", value = "关联id", required = true)
            }
    )
    @PostMapping("/getAccount")
    public ApiResult getAccount(@RequestBody @ApiIgnore() RegistRequest registRequest) {
        return success(registService.getAccount(registRequest));
    }

    /**
     * 用户注册
     *
     * @return
     */
    @PostMapping("/regist")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "userType", dataType = "integer", value = "用户类型(1：个人/2：企业)", required = true),
                    @ApiImplicitParam(name = "userCnName", dataType = "string", value = "真实姓名(个人)", required = true),
                    @ApiImplicitParam(name = "userMobile", dataType = "string", value = "注册用手机号(个人)", required = true),
                    @ApiImplicitParam(name = "userCertificateType", dataType = "string", value = "证件类型(个人)", required = true),
                    @ApiImplicitParam(name = "userCertificateNumber", dataType = "string", value = "证件号码(个人)", required = true),
                    @ApiImplicitParam(name = "userIdCard", dataType = "string", value = "身份证号(个人)", required = true),
                    @ApiImplicitParam(name = "userEmail", dataType = "string", value = "邮箱(个人)", required = true),
                    @ApiImplicitParam(name = "font", dataType = "multipartFile", value = "身份证正面(个人)/统一信用代码照片(企业)", required = true),
                    @ApiImplicitParam(name = "back", dataType = "multipartFile", value = "身份证背面(个人)", required = true),
                    @ApiImplicitParam(name = "userName", dataType = "string", value = "用户名", required = true),
                    @ApiImplicitParam(name = "password", dataType = "string", value = "密码", required = true),
                    @ApiImplicitParam(name = "userVerificationCode", dataType = "string", value = "手机验证码", required = true),
                    @ApiImplicitParam(name = "accountSubBindRelationId", dataType = "string", value = "关联id", required = true),
                    @ApiImplicitParam(name = "accountSubBindFrom", dataType = "string", value = "注册来源", required = true),
                    @ApiImplicitParam(name = "companyIsThreeInOne", dataType = "boolean", value = "是否三证合一(企业),是则传统一信用代码及对应照片，否则传营业执照号码+税务登记号+组织机构代码及对应照片", required = true),
                    @ApiImplicitParam(name = "companyName", dataType = "string", value = "企业名称(企业)", required = true),
                    @ApiImplicitParam(name = "companyPrincipalName", dataType = "string", value = "法人姓名(企业)", required = true),
                    @ApiImplicitParam(name = "companyPrincipalTel", dataType = "string", value = "法人手机号(企业)", required = true),
                    @ApiImplicitParam(name = "companyPrincipalIdCode", dataType = "string", value = "法人身份证(企业)", required = true),
                    @ApiImplicitParam(name = "companyContactTel", dataType = "string", value = "注册用手机号(企业)", required = true),
                    @ApiImplicitParam(name = "companyToPublicBank", dataType = "string", value = "对公账户开户行(企业)", required = true),
                    @ApiImplicitParam(name = "companyToPublicAccountBank", dataType = "string", value = "对公账户账号(企业)", required = true),
                    @ApiImplicitParam(name = "companyShortName", dataType = "string", value = "企业简称(企业)"),
                    @ApiImplicitParam(name = "companyContact", dataType = "string", value = "联系人(企业)", required = true),
                    @ApiImplicitParam(name = "companyContactIdCard", dataType = "string", value = "联系人身份证(企业)", required = true),
                    @ApiImplicitParam(name = "companyTaxpayerRegistrationNumber", dataType = "string", value = "纳税人识别号(企业)", required = true),
                    @ApiImplicitParam(name = "companyFax", dataType = "string", value = "公司传真(企业)"),
                    @ApiImplicitParam(name = "companyEmail", dataType = "string", value = "公司邮箱(企业)"),
                    @ApiImplicitParam(name = "companyRegistedCapital", dataType = "string", value = "注册资金(企业)"),
                    @ApiImplicitParam(name = "companyProvince", dataType = "string", value = "公司所在省(企业)"),
                    @ApiImplicitParam(name = "companyCity", dataType = "string", value = "公司所在市(企业)"),
                    @ApiImplicitParam(name = "companyDistrict", dataType = "string", value = "公司所在区/县(企业)"),
                    @ApiImplicitParam(name = "companyAddress", dataType = "string", value = "详细地址(企业)"),
                    @ApiImplicitParam(name = "companyUnifiedSocialCreditLdentifier", dataType = "string", value = "统一信用代码(企业)", required = true),
                    @ApiImplicitParam(name = "companyUnifiedSocialDatetime", dataType = "string", value = "注册时间(企业)"),
                    @ApiImplicitParam(name = "companyUnifiedSocialLimitDatetime", dataType = "string", value = "过期时间(企业)"),
                    @ApiImplicitParam(name = "companyLicenseNo", dataType = "string", value = "营业执照号码(企业)", required = true),
                    @ApiImplicitParam(name = "companyLicenseNoImg", dataType = "multipartFile", value = "营业执照(企业)", required = true),
                    @ApiImplicitParam(name = "companyOrganizationCode", dataType = "string", value = "组织机构代码(企业)", required = true),
                    @ApiImplicitParam(name = "companyOrganizationCodeImg", dataType = "multipartFile", value = "组织机构代码照片(企业)", required = true),
                    @ApiImplicitParam(name = "companyTaxNo", dataType = "string", value = "税务登记号(企业)", required = true),
                    @ApiImplicitParam(name = "companyTaxNoImg", dataType = "multipartFile", value = "税务登记证(企业)", required = true)
            }
    )
    @ApiOperation("注册")
    @Transactional
    public ApiResult regist(@ApiIgnore() ApiRegistRequest apiRegistRequest) throws Exception {
        Boolean b = registService.checkUserName(apiRegistRequest.getUserName());
        if (b) {
            Integer userType = apiRegistRequest.getUserType();
            String mobile = null;
            // 0-后台用户,1-个人,2-企业
            if (userType == 1) {
                mobile = apiRegistRequest.getUserMobile();
            } else if (userType == 2) {
                mobile = apiRegistRequest.getCompanyContactTel();
            }
            // 校对验证码
            boolean flag = msgCodeService.judgeCode(mobile, apiRegistRequest.getUserVerificationCode());
            if (flag) {
                // 生成新用户以及对应账户
                return registService.save(apiRegistRequest, mobile);
            } else {
                return error("验证码错误");
            }
        } else {
            return error("该用户名已注册过");
        }
    }





    /**
     * 查询e宝账户信息 查询条件【e宝账户名称或e宝账号】
     *
     * @param userAccountInfoDto
     * @return
     */
    @ApiOperation("查询e宝账户信息")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "SubAcctNo", dataType = "string", value = "账号号码", required = true),
                    @ApiImplicitParam(name = "userAccountName", dataType = "string", value = "账户名称", required = true)
            }
    )
    @PostMapping("/getAccountInfo")
    public ApiResult getAccountInfo(@RequestBody @ApiIgnore() UserAccountInfoDto userAccountInfoDto) {
        if (userAccountInfoDto.getUserAccountName() == "" && userAccountInfoDto.getSubAcctNo() == ""){
            return null;
        }
        return success(userAccountService.getInSubAcctInfo(userAccountInfoDto));
    }



}
