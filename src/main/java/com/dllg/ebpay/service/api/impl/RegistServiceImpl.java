package com.dllg.ebpay.service.api.impl;

import com.dllg.common.CommonUtil;
import com.dllg.ebpay.configurer.EbpayProperties;
import com.dllg.ebpay.dao.generator.*;
import com.dllg.ebpay.model.form.PaymentCodeForm;
import com.dllg.ebpay.model.generator.*;
import com.dllg.ebpay.model.request.ApiRegistRequest;
import com.dllg.ebpay.model.request.RegistRequest;
import com.dllg.ebpay.service.accountSub.AccountSubService;
import com.dllg.ebpay.service.api.RegistService;
import com.dllg.ebpay.service.role.RoleService;
import com.dllg.framework.enums.ApiCodeEnum;
import com.dllg.framework.exception.AnyException;
import com.dllg.framework.model.ApiResult;
import com.dllg.framework.security.Auth;
import com.dllg.framework.utils.FileUploadUtils;
import com.dllg.framework.utils.SecurityUtils;
import com.dllg.pabank.model.request.MemberTransactionsInspectionRequest;
import com.dllg.pabank.model.request.OpenCustAcctIdRequest;
import com.dllg.pabank.model.response.OpenCustAcctIdResponse;
import com.dllg.pabank.service.PABankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.dllg.framework.utils.ApiResultUtil.error;
import static com.dllg.framework.utils.ApiResultUtil.success;

@Service
@Transactional
public class RegistServiceImpl implements RegistService {

    @Autowired
    private Auth auth;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private FileUploadUtils fileUploadUtils;
    @Autowired
    private PABankService paBankService;
    @Autowired
    private EbpayProperties ebpayProperties;
    @Autowired
    private AccountSubMapper accountSubMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private BusinessTradeMapper businessTradeMapper;
    @Autowired
    private AccountSubBindMapper accountSubBindMapper;
    @Autowired
    private AccountSubTradeMapper accountSubTradeMapper;
    @Autowired
    private AccountSubService accountSubService;
    @Autowired
    private RoleService roleService;


    /**
     * 关联
     *
     * @param registRequest
     * @return
     */
    @Override
    public ApiResult subBind(RegistRequest registRequest) {
        String accountSubBindId = CommonUtil.uuid();
        AccountSubExample accountSubExample = new AccountSubExample();
        accountSubExample.createCriteria().andAccountSubNoEqualTo(registRequest.getSubAcctNo());
        AccountSub accountSub = accountSubMapper.selectByExample(accountSubExample).stream().findFirst().orElse(null);
        if (accountSub != null) {
            AccountSubBind accountSubBind = new AccountSubBind();
            accountSubBind.setAccountSubBindId(accountSubBindId);//id
            accountSubBind.setAccountSubBindAccountSubId(accountSub.getAccountSubId());//子账户id
            accountSubBind.setAccountSubBindRelationId(registRequest.getAccountSubBindRelationId());//关联id
            accountSubBind.setAccountSubBindFrom(registRequest.getAccountSubBindFrom());//来源
            accountSubBindMapper.insertSelective(accountSubBind);
            return success(accountSubBind);
        } else {
            return error("子账户账号不存在");
        }
    }

    @Override
    public List<User> checkPersonalRegist(String userIdCard) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdCardEqualTo(userIdCard);
        return userMapper.selectByExample(userExample);
    }

    @Override
    public List<Company> checkCompanyRegist(String companyUnifiedSocialCreditLdentifier) {
        CompanyExample companyExample = new CompanyExample();
        companyExample.createCriteria().andCompanyUnifiedSocialCreditLdentifierEqualTo(companyUnifiedSocialCreditLdentifier);
        return companyMapper.selectByExample(companyExample);
    }

    @Override
    public List<Company> checkCompanyRegist(RegistRequest registRequest) {
        CompanyExample companyExample = new CompanyExample();
        companyExample.createCriteria().andCompanyTaxNoEqualTo(registRequest.getCompanyTaxNo()).andCompanyLicenseNoEqualTo(registRequest.getCompanyLicenseNo()).andCompanyOrganizationCodeEqualTo(registRequest.getCompanyOrganizationCode());
        return companyMapper.selectByExample(companyExample);
    }

    @Override
    public List<AccountSubBind> checkIsBind(String accountSubBindRelationId) {
        AccountSubBindExample accountSubBindExample = new AccountSubBindExample();
        accountSubBindExample.createCriteria().andAccountSubBindRelationIdEqualTo(accountSubBindRelationId);
        return accountSubBindMapper.selectByExample(accountSubBindExample);
    }

    @Override
    public List<Map<String, String>> getAccount(RegistRequest registRequest) {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        AccountSubBindExample accountSubBindExample = new AccountSubBindExample();
        accountSubBindExample.createCriteria().andAccountSubBindRelationIdEqualTo(registRequest.getAccountSubBindRelationId());
        List<AccountSubBind> accountSubBindList = accountSubBindMapper.selectByExample(accountSubBindExample);
        for (AccountSubBind accountSubBind : accountSubBindList) {
            if (registRequest.getUserType() == 1) {
                AccountSub accountSub = accountSubMapper.selectByPrimaryKey(accountSubBind.getAccountSubBindAccountSubId());
                User user = userMapper.selectByPrimaryKey(accountSub.getAccountSubRelationId());
                map.put("subAccountNo", accountSub.getAccountSubNo());
                map.put("name", user.getUserCnName());
                map.put("accountType", "平安体系");
                list.add(map);
            } else {
                AccountSub accountSub = accountSubMapper.selectByPrimaryKey(accountSubBind.getAccountSubBindAccountSubId());
                Company company = companyMapper.selectByPrimaryKey(accountSub.getAccountSubRelationId());
                map.put("subAccountNo", accountSub.getAccountSubNo());
                map.put("name", company.getCompanyName());
                map.put("accountType", "平安体系");
                list.add(map);
            }
        }
        return list;
    }

    @Override
    public ApiResult checkBindMobile(RegistRequest registRequest) {
        UserExample userExample = new UserExample();
        AccountSubExample accountSubExample = new AccountSubExample();
        accountSubExample.createCriteria().andAccountSubNoEqualTo(registRequest.getSubAcctNo());
        AccountSub accountSub = accountSubMapper.selectByExample(accountSubExample).stream().findFirst().orElse(null);
        if (accountSub != null) {
            if (registRequest.getUserType() == 1) {
                User user = userMapper.selectByPrimaryKey(accountSub.getAccountSubRelationId());
                if (!registRequest.getMobile().equals(user.getUserMobile())) {
                    return error("预留手机错误");
                } else {
                    return success("发送成功");
                }
            } else {
                userExample.createCriteria().andUserCompanyIdEqualTo(accountSub.getAccountSubRelationId());
                User user = userMapper.selectByExample(userExample).stream().findFirst().orElse(null);
                if (!registRequest.getMobile().equals(user.getUserMobile())) {
                    return error("预留手机错误");
                } else {
                    return success("发送成功");
                }
            }
        } else {
            return error("子账户账号不存在");
        }
    }

    @Override
    public List<User> checkUserMobile(String mobile) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserMobileEqualTo(mobile);
        return userMapper.selectByExample(userExample);
    }

    @Override
    public List<Company> checkCompanyContactTel(String mobile) {
        CompanyExample companyExample = new CompanyExample();
        companyExample.createCriteria().andCompanyContactTelEqualTo(mobile);
        return companyMapper.selectByExample(companyExample);
    }

    @Override
    public Map<String, Object> getPaymentMessage(String id) {
        Map<String, Object> result = new HashMap<>();
        BusinessTrade businessTrade = businessTradeMapper.selectByPrimaryKey(id);
        if (businessTrade != null) {
            AccountSubTrade accountSubTrade = accountSubTradeMapper.selectByPrimaryKey(businessTrade.getBusinessTradeAccountSubTradeId());
            result.put("businessTrade", businessTrade);
            result.put("accountSubTrade", accountSubTrade);
            MemberTransactionsInspectionRequest memberTransactionsInspectionRequest = getMemberTransactionsInspectionRequest(accountSubTrade, businessTrade);
            AccountSub accountSub = accountSubMapper.selectByPrimaryKey(accountSubTrade.getAccountSubTradeAccountSubId());
            PaymentCodeForm paymentCodeForm = accountSubService.paymentCodeManage(accountSubTrade.getAccountSubTradeId(), accountSub.getAccountSubNo(), "V", memberTransactionsInspectionRequest, " ", ebpayProperties.getBusinessCallBackUrl(), null);
            result.put("form", paymentCodeForm);
            result.put("memberTransactionsInspectionRequest", memberTransactionsInspectionRequest);
            return result;
        } else {
            return null;
        }
    }

    public MemberTransactionsInspectionRequest getMemberTransactionsInspectionRequest(AccountSubTrade accountSubTrade, BusinessTrade businessTrade) {
        MemberTransactionsInspectionRequest memberTransactionsInspectionRequest = new MemberTransactionsInspectionRequest();

        AccountSub outAccount = accountSubMapper.selectByPrimaryKey(accountSubTrade.getAccountSubTradeAccountSubId());
        AccountSub inAccount = accountSubMapper.selectByPrimaryKey(accountSubTrade.getAccountSubTradeInAccountSubId());
        if (outAccount == null) {
            throw new AnyException(ApiCodeEnum.OUT_ACCOUNT_NOT_FOUND);
        }
        if (inAccount == null) {
            throw new AnyException(ApiCodeEnum.IN_ACCOUNT_NOT_FOUND);
        }
        Account account = accountMapper.selectByPrimaryKey(outAccount.getAccountSubAccountId());
        if (account == null) {
            throw new AnyException(ApiCodeEnum.ACCOUNT_NOT_FOUND);
        }
        String outAccountName = "";
        String outAccountCode = "";
        String inAccountName = "";
        String inAccountCode = "";

        if (outAccount.getAccountSubRelationIdType() == 1) {
            User outUser = userMapper.selectByPrimaryKey(outAccount.getAccountSubRelationId());
            outAccountName = outUser.getUserCnName();
            outAccountCode = outUser.getUserId();
        } else {
            Company company = companyMapper.selectByPrimaryKey(outAccount.getAccountSubRelationId());
            outAccountName = company.getCompanyName();
            outAccountCode = company.getCompanyId();
        }
        if (inAccount.getAccountSubRelationIdType() == 1) {
            User inUser = userMapper.selectByPrimaryKey(inAccount.getAccountSubRelationId());
            inAccountName = inUser.getUserCnName();
            inAccountCode = inUser.getUserId();
        } else {
            Company incompany = companyMapper.selectByPrimaryKey(inAccount.getAccountSubRelationId());
            inAccountName = incompany.getCompanyName();
            inAccountCode = incompany.getCompanyId();
        }
        memberTransactionsInspectionRequest.setFunctionFlag("6");
        memberTransactionsInspectionRequest.setOutSubAcctNo(outAccount.getAccountSubNo());
        memberTransactionsInspectionRequest.setFundSummaryAcctNo(account.getAccountFundSummaryAccountNo());
        memberTransactionsInspectionRequest.setOutMemberCode(outAccountCode);
        memberTransactionsInspectionRequest.setOutSubAcctName(outAccountName);
        memberTransactionsInspectionRequest.setInSubAcctNo(inAccount.getAccountSubNo());
        memberTransactionsInspectionRequest.setInMemberCode(inAccountCode);
        memberTransactionsInspectionRequest.setInSubAcctName(inAccountName);
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        memberTransactionsInspectionRequest.setTranAmt(decimalFormat.format(accountSubTrade.getAccountSubTradeTranAmt()));
        memberTransactionsInspectionRequest.setTranFee(decimalFormat.format(accountSubTrade.getAccountSubTradeTranFee()));
        memberTransactionsInspectionRequest.setTranType("01");
        memberTransactionsInspectionRequest.setCcy("RMB");
        if(businessTrade.getBusinessTradeBusinessNo() !=null && !"".equals(businessTrade.getBusinessTradeBusinessNo()) && businessTrade.getBusinessTradeBusinessNo().length()>51){
            memberTransactionsInspectionRequest.setOrderNo(businessTrade.getBusinessTradeBusinessNo().substring(0,49));
        }else{
            memberTransactionsInspectionRequest.setOrderNo(businessTrade.getBusinessTradeBusinessNo());
        }
        memberTransactionsInspectionRequest.setOrderContent(businessTrade.getBusinessTradeBusinessContent());
        memberTransactionsInspectionRequest.setRemark(businessTrade.getBusinessTradeRemark());
        memberTransactionsInspectionRequest.setReservedMsg("");
        return memberTransactionsInspectionRequest;
    }

    /**
     * 注册
     *
     * @param apiRegistRequest
     * @param mobile
     * @return
     * @throws Exception
     */
    @Override
    public ApiResult save(ApiRegistRequest apiRegistRequest, String mobile) throws Exception {
        String userId = CommonUtil.uuid();
        String companyId = CommonUtil.uuid();
        String userIdConst = userId;
        User user = new User();
        Company company = new Company();
        OpenCustAcctIdResponse response = new OpenCustAcctIdResponse();
        if (apiRegistRequest.getUserType() == 1) {
            if (apiRegistRequest.getFont().getSize() == 0) {
                return error("身份证正面缺失");
            }
            if (apiRegistRequest.getBack().getSize() == 0) {
                return error("身份证反面缺失");
            }
            if (apiRegistRequest.getUserCnName() == null) {
                return error("姓名缺失");
            }
            if (apiRegistRequest.getUserCertificateNumber() == null) {
                return error("证件号码缺失");
            }
            if (apiRegistRequest.getUserIdCard() == null) {
                return error("身份证号缺失");
            }
            if (apiRegistRequest.getUserEmail() == null) {
                return error("邮箱缺失");
            }
            if (apiRegistRequest.getUserName() == null) {
                return error("用户名缺失");
            }
            if (apiRegistRequest.getPassword() == null) {
                return error("密码缺失");
            }
            if (apiRegistRequest.getUserCertificateType() == null) {
                return error("证件类型缺失");
            }
            response = OpenCustAcctIdRequest(apiRegistRequest, userId, userIdConst, mobile);
            if ("000000".equals(response.getTxnReturnCode())) {
                // 存储身份证正反面图片
                user.setUserIdCardFontImg(fileUploadUtils.upload(apiRegistRequest.getFont(), userId, FileUploadUtils.FOLDER_USER));
                user.setUserIdCardBackImg(fileUploadUtils.upload(apiRegistRequest.getBack(), userId, FileUploadUtils.FOLDER_USER));
                user.setUserCnName(apiRegistRequest.getUserCnName());
                user.setUserCertificateNumber(apiRegistRequest.getUserCertificateNumber());
                user.setUserIdCard(apiRegistRequest.getUserIdCard());
                user.setUserType(apiRegistRequest.getUserType());
                user.setUserEmail(apiRegistRequest.getUserEmail());
                user.setUserId(userId);
                user.setPassword(SecurityUtils.encrypt(apiRegistRequest.getPassword()));
                user.setUserStatus(1);
                user.setUserName(apiRegistRequest.getUserName());
                user.setUserMobile(mobile);
                user.setUserCertificateType(apiRegistRequest.getUserCertificateType());
                user.setUserCreateTime(new Date());
                user.setUserIsDelete(false);
                user.setUserRegisterFrom(apiRegistRequest.getAccountSubBindFrom());
                userMapper.insertSelective(user);
                apiRegistRequest.setMobile(mobile);
                apiRegistRequest.setSubAcctNo(response.getSubAcctNo());
                ApiResult apiResult = subBind(apiRegistRequest);
                if (apiResult.getCode() == 1) {
                    return success("注册成功");
                } else {
                    return error("绑定失败");
                }
            } else {
                return error("开户失败");
            }
        } else {
            if (apiRegistRequest.getCompanyIsThreeInOne() == false) {
                if (apiRegistRequest.getCompanyLicenseNoImg().getSize() == 0) {
                    return error("营业执照照片缺失");
                }
                if (apiRegistRequest.getCompanyTaxNoImg().getSize() == 0) {
                    return error("税务登记照片缺失");
                }
                if (apiRegistRequest.getCompanyOrganizationCodeImg().getSize() == 0) {
                    return error("组织机构照片缺失");
                }
            } else if (apiRegistRequest.getCompanyIsThreeInOne() == true) {
                if (apiRegistRequest.getFont().getSize() == 0) {
                    return error("统一信用照片缺失");
                }
            }
            if (apiRegistRequest.getUserName() == null) {
                return error("用户名缺失");
            }
            if (apiRegistRequest.getPassword() == null) {
                return error("密码缺失");
            }
            response = OpenCustAcctIdRequest(apiRegistRequest, companyId, userIdConst, mobile);
            if ("000000".equals(response.getTxnReturnCode())) {
                if (apiRegistRequest.getCompanyIsThreeInOne() == false) {
                    company.setCompanyLicenseNo(apiRegistRequest.getCompanyLicenseNo());
                    company.setCompanyOrganizationCode(apiRegistRequest.getCompanyOrganizationCode());
                    company.setCompanyTaxNo(apiRegistRequest.getCompanyTaxNo());
                    company.setCompanyOrganizationCodeImg(fileUploadUtils.upload(apiRegistRequest.getCompanyOrganizationCodeImg(), companyId, FileUploadUtils.FOLDER_USER));
                    company.setCompanyLicenseNoImg(fileUploadUtils.upload(apiRegistRequest.getCompanyLicenseNoImg(), companyId, FileUploadUtils.FOLDER_USER));
                    company.setCompanyTaxNoImg(fileUploadUtils.upload(apiRegistRequest.getCompanyTaxNoImg(), companyId, FileUploadUtils.FOLDER_USER));
                    user.setUserCertificateType("68");
                } else {
                    company.setCompanyUnifiedSocialCreditLdentifier(apiRegistRequest.getCompanyUnifiedSocialCreditLdentifier());
                    company.setCompanyUnifiedSocialImg(fileUploadUtils.upload(apiRegistRequest.getFont(), companyId, FileUploadUtils.FOLDER_USER));
                    user.setUserCertificateType("5");
                }
                user.setUserType(apiRegistRequest.getUserType());
                user.setUserId(userId);
                user.setPassword(SecurityUtils.encrypt(apiRegistRequest.getPassword()));
                user.setUserStatus(1);
                user.setUserMobile(mobile);
                user.setUserName(apiRegistRequest.getUserName());
                user.setUserCreateTime(new Date());
                user.setUserIsDelete(false);
                user.setUserCompanyId(companyId);
                user.setUserRegisterFrom(apiRegistRequest.getAccountSubBindFrom());
                userMapper.insertSelective(user);
                company.setCompanyName(apiRegistRequest.getCompanyName());
                company.setCompanyPrincipalName(apiRegistRequest.getCompanyPrincipalName());
                company.setCompanyPrincipalTel(apiRegistRequest.getCompanyPrincipalTel());
                company.setCompanyContactTel(apiRegistRequest.getCompanyContactTel());
                company.setCompanyPrincipalIdCode(apiRegistRequest.getCompanyPrincipalIdCode());
                company.setCompanyToPublicBank(apiRegistRequest.getCompanyToPublicBank());
                company.setCompanyToPublicAccountBank(apiRegistRequest.getCompanyToPublicAccountBank());
                company.setCompanyShortName(apiRegistRequest.getCompanyShortName());
                company.setCompanyContact(apiRegistRequest.getCompanyContact());
                company.setCompanyTaxpayerRegistrationNumber(apiRegistRequest.getCompanyTaxpayerRegistrationNumber());
                company.setCompanyContactIdCard(apiRegistRequest.getCompanyContactIdCard());
                company.setCompanyFax(apiRegistRequest.getCompanyFax());
                company.setCompanyRegistedCapital(apiRegistRequest.getCompanyRegistedCapital());
                company.setCompanyEmail(apiRegistRequest.getCompanyEmail());
                company.setCompanyProvince(apiRegistRequest.getCompanyProvince());
                company.setCompanyCity(apiRegistRequest.getCompanyCity());
                company.setCompanyDistrict(apiRegistRequest.getCompanyDistrict());
                company.setCompanyAddress(apiRegistRequest.getCompanyAddress());
                company.setCompanyIsThreeInOne(apiRegistRequest.getCompanyIsThreeInOne());
                if (apiRegistRequest.getCompanyUnifiedSocialDatetime() != null) {
                    company.setCompanyUnifiedSocialDatetime(new Date(Long.valueOf(apiRegistRequest.getCompanyUnifiedSocialDatetime())));
                }
                if (apiRegistRequest.getCompanyUnifiedSocialLimitDatetime() != null) {
                    company.setCompanyUnifiedSocialLimitDatetime(new Date(Long.valueOf(apiRegistRequest.getCompanyUnifiedSocialLimitDatetime())));
                }
                company.setCompanyId(companyId);
                company.setCompanyStatus(1);
                company.setCompanyCreateTime(new Date());
                company.setCompanyIsDelete(false);
                companyMapper.insertSelective(company);
                apiRegistRequest.setMobile(mobile);
                apiRegistRequest.setSubAcctNo(response.getSubAcctNo());
                ApiResult apiResult = subBind(apiRegistRequest);
                roleService.addFrontUserRole(user);
                if (apiResult.getCode() == 1) {
                    return success("注册成功");
                } else {
                    return error("绑定失败");
                }
            } else {
                return error("开户失败");
            }
        }
    }

    @Override
    public Boolean checkUserName(String userName) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserNameEqualTo(userName);
        List<User> list = userMapper.selectByExample(userExample);
        if (list.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 调用平安开户接口
     *
     * @param apiRegistRequest
     * @param id
     * @param userIdConst
     * @param mobile
     * @return
     * @throws Exception
     */
    public OpenCustAcctIdResponse OpenCustAcctIdRequest(ApiRegistRequest apiRegistRequest, String id, String userIdConst, String mobile) throws Exception {
        OpenCustAcctIdRequest request = new OpenCustAcctIdRequest();
        request.setFunctionFlag("1");
        request.setFundSummaryAcctNo(ebpayProperties.getFundSummaryAcctNo());
        request.setTranNetMemberCode(id);
        request.setMemberProperty("00");
        request.setUserNickname(apiRegistRequest.getUserName());
        request.setMobile(mobile);
        request.setEmail("");
        request.setReservedMsg("");
        OpenCustAcctIdResponse response = paBankService.openCustAcctId(request);
        if (!"000000".equals(response.getTxnReturnCode())) {
            return response;
        } else {
            String accountId = response.getSubAcctNo(); //子账户账号
            String accountSubId = CommonUtil.uuid(); // 子账户id
            // 新建账户
            AccountSub accountSub = new AccountSub();
            accountSub.setAccountSubId(accountSubId);
            AccountExample accountExample = new AccountExample();
            accountExample.createCriteria().andAccountTypeEqualTo(1);
            Account account = accountMapper.selectByExample(accountExample).stream().findFirst().orElse(null);
            accountSub.setAccountSubAccountId(account.getAccountId());//总帐户id
            accountSub.setAccountSubRelationId(id);
            accountSub.setAccountSubRelationIdType(apiRegistRequest.getUserType());
            if (apiRegistRequest.getUserType() == 1) {
                accountSub.setAccountSubName(apiRegistRequest.getUserCnName());
            } else {
                accountSub.setAccountSubName(apiRegistRequest.getCompanyName());
            }
            accountSub.setAccountSubNo(accountId);
            accountSub.setAccountSubType(1);
            accountSub.setAccountSubAccountOpeningDate(new Date());
            accountSub.setAccountSubStatus(1);
            accountSub.setAccountSubIsDelete(false);
            accountSub.setAccountSubCreateTime(new Date());
            accountSub.setAccountSubCreateUserId(userIdConst);
            accountSubMapper.insertSelective(accountSub);
            return response;
        }
    }

    public ApiResult subBind(ApiRegistRequest apiRegistRequest) {
        String accountSubBindId = CommonUtil.uuid();
        AccountSubExample accountSubExample = new AccountSubExample();
        accountSubExample.createCriteria().andAccountSubNoEqualTo(apiRegistRequest.getSubAcctNo());
        AccountSub accountSub = accountSubMapper.selectByExample(accountSubExample).stream().findFirst().orElse(null);
        if (accountSub != null) {
            AccountSubBind accountSubBind = new AccountSubBind();
            accountSubBind.setAccountSubBindId(accountSubBindId);//id
            accountSubBind.setAccountSubBindAccountSubId(accountSub.getAccountSubId());//子账户id
            accountSubBind.setAccountSubBindRelationId(apiRegistRequest.getAccountSubBindRelationId());//关联id
            accountSubBind.setAccountSubBindFrom(apiRegistRequest.getAccountSubBindFrom());//来源
            accountSubBindMapper.insertSelective(accountSubBind);
            return success(accountSubBind);
        } else {
            return error("子账户账号不存在");
        }
    }
}
