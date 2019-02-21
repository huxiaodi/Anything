package com.dllg.ebpay.service.user.impl;

import com.dllg.common.CommonUtil;
import com.dllg.ebpay.configurer.EbpayProperties;
import com.dllg.ebpay.dao.custom.RoleCuMapper;
import com.dllg.ebpay.dao.custom.TeamCuMapper;
import com.dllg.ebpay.dao.custom.UserCuMapper;
import com.dllg.ebpay.dao.generator.AccountMapper;
import com.dllg.ebpay.dao.generator.AccountSubBindMapper;
import com.dllg.ebpay.dao.generator.AccountSubMapper;
import com.dllg.ebpay.dao.generator.CompanyMapper;
import com.dllg.ebpay.dao.generator.UserMapper;
import com.dllg.ebpay.dao.generator.UserTeamMapper;
import com.dllg.ebpay.dao.generator.userRoleMapper;
import com.dllg.ebpay.model.form.UserForm;
import com.dllg.ebpay.model.generator.Account;
import com.dllg.ebpay.model.generator.AccountExample;
import com.dllg.ebpay.model.generator.AccountSub;
import com.dllg.ebpay.model.generator.AccountSubBind;
import com.dllg.ebpay.model.generator.AccountSubExample;
import com.dllg.ebpay.model.generator.Company;
import com.dllg.ebpay.model.generator.CompanyExample;
import com.dllg.ebpay.model.generator.Resource;
import com.dllg.ebpay.model.generator.Role;
import com.dllg.ebpay.model.generator.Team;
import com.dllg.ebpay.model.generator.User;
import com.dllg.ebpay.model.generator.UserExample;
import com.dllg.ebpay.model.generator.UserTeam;
import com.dllg.ebpay.model.generator.UserTeamExample;
import com.dllg.ebpay.model.generator.userRole;
import com.dllg.ebpay.model.generator.userRoleExample;
import com.dllg.ebpay.model.request.LoginRequest;
import com.dllg.ebpay.model.request.QueryParams;
import com.dllg.ebpay.model.request.UserRequest;
import com.dllg.ebpay.service.role.RoleService;
import com.dllg.ebpay.service.user.UserLogService;
import com.dllg.ebpay.service.user.UserService;
import com.dllg.framework.enums.AuthEnum;
import com.dllg.framework.exception.AnyException;
import com.dllg.framework.model.ApiResult;
import com.dllg.framework.model.AuthUser;
import com.dllg.framework.security.Auth;
import com.dllg.framework.utils.ApiResultUtil;
import com.dllg.framework.utils.FileUploadUtils;
import com.dllg.framework.utils.JsonUtils;
import com.dllg.framework.utils.RedisUtils;
import com.dllg.framework.utils.SecurityUtils;
import com.dllg.pabank.model.request.OpenCustAcctIdRequest;
import com.dllg.pabank.model.response.OpenCustAcctIdResponse;
import com.dllg.pabank.service.PABankService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.dllg.framework.utils.ApiResultUtil.error;
import static com.dllg.framework.utils.ApiResultUtil.success;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private Auth auth;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserCuMapper userCuMapper;
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
    private AccountSubBindMapper accountSubBindMapper;
    @Autowired
    private RoleCuMapper roleCuMapper;
    @Autowired
    private userRoleMapper userRoleMapper;
    @Autowired
    private UserTeamMapper userTeamMapper;
    @Autowired
    private TeamCuMapper teamCuMapper;
    @Autowired
    private UserLogService userLogService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RedisUtils redisUtils;

    private String passwordReg = "^(?=[^A-Z\\u4e00-\\u9fa5]*[0-9])(?=[^A-Z\\u4e00-\\u9fa5]*[a-z])[^A-Z\\u4e00-\\u9fa5]{8,50}$";

    @Override
    public ApiResult login(LoginRequest loginRequest) {
        String userName = loginRequest.getUserName();
        String password = loginRequest.getUserPassword();
        Integer userType = loginRequest.getUserType();
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if (userType != null && userType == 0) {
            criteria.andUserNameEqualTo(userName).andUserTypeEqualTo(userType);
        } else {
            criteria.andUserNameEqualTo(userName).andUserTypeNotEqualTo(0);
            ;
        }
        User user = userMapper.selectByExample(userExample).stream().findFirst().orElse(null);
        if (user == null) {
            throw new AnyException("账号不存在");
        }
        if (user.getUserStatus() == 0) {
            throw new AnyException("此用户已禁用");
        }
        if (!user.getPassword().equals(SecurityUtils.encrypt(password))) {
            throw new AnyException("密码错误");
        }
        AccountSubExample accountSubExample = new AccountSubExample();
        AuthUser authUser = new AuthUser();
        authUser.setUserId(user.getUserId());
        authUser.setUserName(user.getUserName());
        authUser.setUserType(user.getUserType());
        authUser.setUserCompanyId(user.getUserCompanyId());
        authUser.setUserHeadImg(user.getUserHeadImg());
        authUser.setUserCnName(user.getUserCnName());
        AccountSub accountSub = null;
        if (user.getUserType().equals(2)) { // 企业用户
            accountSubExample.createCriteria().andAccountSubRelationIdEqualTo(user.getUserCompanyId());
            accountSub = accountSubMapper.selectByExample(accountSubExample).stream().findFirst().orElse(null);
            authUser.setAccountSubNo(accountSub.getAccountSubNo());
            Company company = companyMapper.selectByPrimaryKey(user.getUserCompanyId());
            if (accountSub == null) {
                throw new AnyException("账号异常");
            } else if (accountSub.getAccountSubStatus() == 0) {
                throw new AnyException("此账号已禁用");
            }
            authUser.setUserCompanyName(company.getCompanyName());
        } else if (user.getUserType().equals(1)) {//个人用户
            accountSubExample.createCriteria().andAccountSubRelationIdEqualTo(user.getUserId());
            accountSub = accountSubMapper.selectByExample(accountSubExample).stream().findFirst().orElse(null);
            if (accountSub == null) {
                throw new AnyException("账号异常");
            } else if (accountSub.getAccountSubStatus() == 0) {
                throw new AnyException("此账号已禁用");
            }
            authUser.setAccountSubNo(accountSub.getAccountSubNo());
        }
        String token = auth.createJWToken(authUser);

        redisUtils.hset(token, AuthEnum.AUTH_USER.getValue(), JsonUtils.toJSONString(authUser));
        // token 有效期 15分钟
        redisUtils.expire(token, 15L, TimeUnit.MINUTES);

        authUser.setUserToken(token);
        //添加登录日志----------------------------------
        userLogService.addUser(authUser);

        return ApiResultUtil.success(authUser);
    }

    /**
     * 调用平安开户接口
     *
     * @param user
     * @param company
     * @param id
     * @return
     * @throws Exception
     */
    public OpenCustAcctIdResponse OpenCustAcctIdRequest(User user, Company company, String id, String userIdConst, String mobile) throws Exception {
        OpenCustAcctIdRequest request = new OpenCustAcctIdRequest();
        request.setFunctionFlag("1");
        request.setFundSummaryAcctNo(ebpayProperties.getFundSummaryAcctNo());
        request.setTranNetMemberCode(id);
        request.setMemberProperty("00");
        request.setUserNickname(user.getUserName());
        request.setMobile(mobile);
        request.setEmail("");
        request.setReservedMsg("");
        OpenCustAcctIdResponse response = paBankService.openCustAcctId(request);
        if (!"000000".equals(response.getTxnReturnCode())) {
            return response;
        }
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
        accountSub.setAccountSubRelationIdType(user.getUserType());
        if (user.getUserType() == 2) {
            accountSub.setAccountSubName(company.getCompanyName());
        } else {
            accountSub.setAccountSubName(user.getUserCnName());
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

    @Override
    public ApiResult save(MultipartFile font, MultipartFile back, MultipartFile companyOrganizationCodeImg, MultipartFile companyLicenseNoImg, MultipartFile companyTaxNoImg, User user, Company company, String mobile) throws Exception {
        String userId = CommonUtil.uuid();
        String companyId = CommonUtil.uuid();
        String userIdConst = userId;
        OpenCustAcctIdResponse response = new OpenCustAcctIdResponse();
        if (user.getUserType() == 1) {
            response = OpenCustAcctIdRequest(user, company, userId, userIdConst, mobile);
            if ("000000".equals(response.getTxnReturnCode())) {
                // 存储身份证正反面图片
                user.setUserIdCardFontImg(fileUploadUtils.upload(font, userId, FileUploadUtils.FOLDER_USER));
                user.setUserIdCardBackImg(fileUploadUtils.upload(back, userId, FileUploadUtils.FOLDER_USER));
                user.setUserId(userId);
                user.setPassword(SecurityUtils.encrypt(user.getPassword()));
                user.setUserStatus(1);
                user.setUserMobile(mobile);
                user.setUserCertificateType(user.getUserCertificateType());
                user.setUserCreateTime(new Date());
                user.setUserIsDelete(false);
                user.setUserRegisterFrom("德邻E宝");
                userMapper.insertSelective(user);
                String accountSubBindRelationId = userId;
                String accountSubBindFrom = "德邻E宝";
                String SubAcctNo = response.getSubAcctNo();
                subBind(accountSubBindRelationId, accountSubBindFrom, SubAcctNo);
                return success("注册成功");
            } else {
                return error("开户失败");
            }
        } else {
            response = OpenCustAcctIdRequest(user, company, companyId, userIdConst, mobile);
            if ("000000".equals(response.getTxnReturnCode())) {
                if (company.getCompanyIsThreeInOne() == false) {
                    company.setCompanyOrganizationCodeImg(fileUploadUtils.upload(companyOrganizationCodeImg, companyId, FileUploadUtils.FOLDER_USER));
                    company.setCompanyLicenseNoImg(fileUploadUtils.upload(companyLicenseNoImg, companyId, FileUploadUtils.FOLDER_USER));
                    company.setCompanyTaxNoImg(fileUploadUtils.upload(companyTaxNoImg, companyId, FileUploadUtils.FOLDER_USER));
                    company.setCompanyUnifiedSocialCreditLdentifier("");
                    user.setUserCertificateType("68");
                } else {
                    company.setCompanyUnifiedSocialImg(fileUploadUtils.upload(font, companyId, FileUploadUtils.FOLDER_USER));
                    company.setCompanyOrganizationCode("");
                    company.setCompanyTaxNo("");
                    company.setCompanyLicenseNo("");
                    user.setUserCertificateType("5");
                }
                user.setUserId(userId);
                user.setPassword(SecurityUtils.encrypt(user.getPassword()));
                user.setUserStatus(1);
                user.setUserMobile(mobile);
                user.setUserCreateTime(new Date());
                user.setUserIsDelete(false);
                user.setUserCompanyId(companyId);
                userMapper.insertSelective(user);
                company.setCompanyId(companyId);
                company.setCompanyStatus(1);
                company.setCompanyCreateTime(new Date());
                company.setCompanyIsDelete(false);
                user.setUserRegisterFrom("德邻E宝");
                companyMapper.insertSelective(company);
                String accountSubBindRelationId = companyId;
                String accountSubBindFrom = "德邻E宝";
                String SubAcctNo = response.getSubAcctNo();
                subBind(accountSubBindRelationId, accountSubBindFrom, SubAcctNo);
                roleService.addFrontUserRole(user);
                return success("注册成功");
            } else {
                return error("开户失败");
            }
        }
    }

    /**
     * 关联/绑定
     *
     * @param accountSubBindRelationId
     * @param accountSubBindFrom
     * @param SubAcctNo
     */
    public void subBind(String accountSubBindRelationId, String accountSubBindFrom, String SubAcctNo) {
        String accountSubBindId = CommonUtil.uuid();
        AccountSubExample accountSubExample = new AccountSubExample();
        accountSubExample.createCriteria().andAccountSubNoEqualTo(SubAcctNo);
        AccountSub accountSub = accountSubMapper.selectByExample(accountSubExample).stream().findFirst().orElse(null);
        AccountSubBind accountSubBind = new AccountSubBind();
        accountSubBind.setAccountSubBindId(accountSubBindId);//id
        accountSubBind.setAccountSubBindAccountSubId(accountSub.getAccountSubId());//子账户id
        accountSubBind.setAccountSubBindRelationId(accountSubBindRelationId);//关联id
        accountSubBind.setAccountSubBindFrom(accountSubBindFrom);//来源
        accountSubBindMapper.insertSelective(accountSubBind);
    }

    /**
     * 管理端 用户列表
     */
    @Override
    public List manageList(QueryParams queryParams) {
        return userCuMapper.manageList(queryParams);
    }


    @Override
    public Boolean checkMobile(String mobile) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserMobileEqualTo(mobile);
        List<User> users = userMapper.selectByExample(userExample);
        CompanyExample companyExample = new CompanyExample();
        companyExample.createCriteria().andCompanyContactTelEqualTo(mobile);
        List<Company> companies = companyMapper.selectByExample(companyExample);
        if (users.isEmpty() && companies.isEmpty()) {
            return true;
        }
        throw new AnyException("手机号已注册");
    }

    @Override
    public User checkUser(String userName) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserNameEqualTo(userName);
        return userMapper.selectByExample(userExample).stream().findFirst().orElse(null);
    }

    @Override
    public ApiResult resetPassword(UserRequest userRequest) {
        String userName = userRequest.getUserName();
        User user = checkUser(userName);
        if (user == null) {
            throw new AnyException("账号不存在");
        }
        String password = userRequest.getNewPassword();
        if (password.matches(passwordReg)) {
            user.setPassword(SecurityUtils.encrypt(password));
            userMapper.updateByPrimaryKeySelective(user);
            return success("成功重置密码");
        } else {
            return error("密码格式错误");
        }
    }


    @Override
    public boolean checkMobile(UserRequest userRequest) {
        User user = checkUser(userRequest.getUserName());
        Integer userType = user.getUserType();
        String mobile = userRequest.getMobile();
        String reserveMobile = user.getUserMobile();
        if (userType == 2) {
            reserveMobile = companyMapper.selectByPrimaryKey(user.getUserCompanyId()).getCompanyContactTel();
        }
        return mobile.equals(reserveMobile);
    }

    @Override
    public ApiResult changePassword(UserRequest userRequest) {
        String userPassword = SecurityUtils.encrypt(userRequest.getUserPassword());
        String newPassword = SecurityUtils.encrypt(userRequest.getNewPassword());
        AuthUser authUser = auth.getCurrentUser();
        UserExample example = new UserExample();
        example.createCriteria().andUserIdEqualTo(authUser.getUserId()).andPasswordEqualTo(userPassword);
        User user = userMapper.selectByExample(example).stream().findFirst().orElse(null);
        if (user == null) {
            throw new AnyException("旧密码错误");
        }
        if (userPassword.equals(newPassword)) {
            throw new AnyException("新旧密码相同");
        }
        if (newPassword.matches(passwordReg)) {
            user.setPassword(newPassword);
            userMapper.updateByPrimaryKeySelective(user);
            return success("您的密码已修改成功");
        } else {
            return error("密码格式错误");
        }

    }

    @Override
    public Integer changeMobile(UserRequest userRequest) {
        String mobile = userRequest.getNewMobile();
        AuthUser authUser = auth.getCurrentUser();
        Company company = new Company();
        company.setCompanyId(authUser.getUserCompanyId());
        company.setCompanyContactTel(mobile);
        Integer i = companyMapper.updateByPrimaryKeySelective(company);
        User user = new User();
        user.setUserId(authUser.getUserId());
        user.setUserMobile(mobile);
        Integer j = userMapper.updateByPrimaryKeySelective(user);
        return i + j;
    }

    @Override
    public Company getCompanyInformation(String userCompanyId) {
        return companyMapper.selectByPrimaryKey(userCompanyId);
    }

    @Override
    public void upload(Company company) {
        companyMapper.updateByPrimaryKeySelective(company);
    }

    @Override
    public void changeCompanyInformation(Company company) {
        companyMapper.updateByPrimaryKeySelective(company);
    }

    @Override
    public User getPersonalInformation(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void changePersonalInformation(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void upload(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    public User selectByPrimaryKey(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public void updateByPrimaryKeySelective(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public Boolean checkUserName(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserNameEqualTo(user.getUserName());
        List<User> list = userMapper.selectByExample(userExample);
        if (list.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Map<String, Object>> getUserMemberList(UserForm userForm) {
        AuthUser authUser = auth.getCurrentUser();
        Integer userType = authUser.getUserType();
        userForm.setUserType(userType);
        // 类型,0-后台用户,1-个人,2-企业
        if (userType == 2) {
            userForm.setUserCompanyId(authUser.getUserCompanyId());
        }
        PageHelper.startPage(userForm);
        List<Map<String, Object>> list = userCuMapper.getUserMemberList(userForm);
        for (Map map : list) {
            String userId = (String) map.get("userId");
            List<Role> roles = roleCuMapper.getRoleByUserId(userId);
            String roleNames = roles.stream().map(Role::getRoleName).collect(Collectors.joining(","));
            List<String> roleIds = roles.stream().map(Role::getRoleId).collect(Collectors.toList());
            map.put("roleNames", roleNames);
            map.put("roleIds", roleIds);

            List<Team> teams = teamCuMapper.getTeamByUserId(userId);
            String teamNames = teams.stream().map(Team::getTeamName).collect(Collectors.joining(","));
            List<String> teamIds = teams.stream().map(Team::getTeamId).collect(Collectors.toList());
            map.put("teamNames", teamNames);
            map.put("teamIds", teamIds);
        }
        return list;
    }

    @Override
    public void addUser(UserRequest userRequest) {
        String userName = userRequest.getUserName();
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserNameEqualTo(userName);
        User user = userMapper.selectByExample(userExample).stream().findFirst().orElse(null);
        if (user != null) {
            throw new AnyException("用户名已存在");
        }
        AuthUser authUser = auth.getCurrentUser();
        String userId = CommonUtil.uuid();
        user = new User();
        user.setUserId(userId);
        user.setUserCompanyId(authUser.getUserCompanyId());
        user.setUserType(authUser.getUserType());
        user.setUserName(userRequest.getUserName());
        user.setPassword(SecurityUtils.encrypt(userRequest.getUserPassword()));
        user.setUserMobile(userRequest.getMobile());
        user.setUserCreateUserId(authUser.getUserId());
        user.setUserCreateTime(new Date());
        user.setUserIsDelete(false);
        user.setUserStatus(1);
        userMapper.insertSelective(user);
        userRequest.setUserId(userId);
        updateRolesAndTeams(userRequest);
    }

    @Override
    public void modify(UserRequest userRequest) {
        AuthUser authUser = auth.getCurrentUser();
        User user = new User();
        user.setUserId(userRequest.getUserId());
        user.setUserMobile(userRequest.getMobile());
        user.setUserCnName(userRequest.getUserCnName());
        user.setUserUpdateUserId(authUser.getUserId());
        user.setUserUpdateTime(new Date());
        userMapper.updateByPrimaryKeySelective(user);
        updateRolesAndTeams(userRequest);
    }

    @Override
    public List<Resource> getResource(String userId) {
        return userCuMapper.getResource(userId);
    }

    @Override
    public boolean checkAdjustPsw(String userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (user.getUserPayPassword() != null && !"".equals(user.getUserPayPassword())) {
            return true;
        }
        return false;
    }

    @Override
    public void loginOut(String token) {
        AuthUser authUser = auth.getCurrentUser();
        AuthUser user = Auth.parseJWToken(token);
        if (!StringUtils.equals(user.getUserName(), authUser.getUserName())) {
            throw new AnyException("登出失败");
        }
        redisUtils.del(token);
    }

    //更新用户的角色和分组
    public void updateRolesAndTeams(UserRequest userRequest) {
        String authUserId = auth.getCurrentUser().getUserId();
        String userId = userRequest.getUserId();
        // 更新用户的角色
        userRoleExample example = new userRoleExample();
        example.createCriteria().andUserRoleUserIdEqualTo(userId);
        userRoleMapper.deleteByExample(example);
        if (userRequest.getRoleIds() != null) {
            userRequest.getRoleIds().forEach(roleId -> {
                userRole userRole = new userRole();
                userRole.setUserRoleUserId(userId);
                userRole.setUserRoleRoleId(roleId);
                userRole.setUserRoleCreateUserId(authUserId);
                userRole.setUserRoleCreateTime(new Date());
                userRoleMapper.insertSelective(userRole);
            });
        }
        // 更新用户的分组
        UserTeamExample userTeamExample = new UserTeamExample();
        userTeamExample.createCriteria().andUserTeamUserIdEqualTo(userId);
        userTeamMapper.deleteByExample(userTeamExample);
        if (userRequest.getTeamIds() != null) {
            userRequest.getTeamIds().forEach(teamId -> {
                UserTeam userTeam = new UserTeam();
                userTeam.setUserTeamUserId(userId);
                userTeam.setUserTeamTeamId(teamId);
                userTeam.setUserTeamCreateUserId(authUserId);
                userTeam.setUserTeamCreateTime(new Date());
                userTeamMapper.insertSelective(userTeam);
            });
        }
    }

}
