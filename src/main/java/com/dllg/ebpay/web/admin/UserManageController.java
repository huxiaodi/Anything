package com.dllg.ebpay.web.admin;

import com.dllg.ebpay.dao.generator.TeamMapper;
import com.dllg.ebpay.model.form.UserForm;
import com.dllg.ebpay.model.generator.User;
import com.dllg.ebpay.model.request.QueryParams;
import com.dllg.ebpay.model.request.UserRequest;
import com.dllg.ebpay.service.resource.ResourceService;
import com.dllg.ebpay.service.role.RoleService;
import com.dllg.ebpay.service.team.TeamService;
import com.dllg.ebpay.service.user.UserService;
import com.dllg.framework.controller.BaseController;
import com.dllg.framework.model.ApiResult;
import com.dllg.framework.model.AuthUser;
import com.dllg.framework.security.Auth;
import com.dllg.framework.utils.FileUploadUtils;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 个人会员管理
 */
@RestController
@RequestMapping("admin/userManage")
public class UserManageController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private FileUploadUtils fileUploadUtils;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private Auth auth;

    /**
     * 用户列表
     */
    @PostMapping("/list")
    public ApiResult list(@RequestBody(required = false) QueryParams queryParams) {
        queryParams.startPage();
        List list = userService.manageList(queryParams);
        return success(new PageInfo<>(list));
    }

    /**
     * 启用
     */
    @PostMapping("/switch/enable")
    public ApiResult enable(@RequestBody(required = false) QueryParams queryParams) {
        final String id = queryParams.getId();
        if (StringUtils.isBlank(id)) {
            return error("参数错误");
        }
        User user = userService.selectByPrimaryKey(id);
        Integer userStatus = user.getUserStatus();
        if (userStatus != null && userStatus.intValue() == 1) {
            return error("该用户已启用，请勿重复操作");
        }
        User u = new User();
        u.setUserId(id);
        u.setUserStatus(1);
        userService.updateByPrimaryKeySelective(u);
        return success("操作成功");
    }

    /**
     * 禁用
     */
    @PostMapping("/switch/disable")
    public ApiResult disable(@RequestBody(required = false) QueryParams queryParams) {
        final String id = queryParams.getId();
        if (StringUtils.isBlank(id)) {
            return error("参数错误");
        }
        User user = userService.selectByPrimaryKey(id);
        Integer userStatus = user.getUserStatus();
        if (userStatus != null && userStatus.intValue() == 0) {
            return error("该用户已禁用，请勿重复操作");
        }
        User u = new User();
        u.setUserId(id);
        u.setUserStatus(0);
        u.setUserStatusExplain(queryParams.getKeyword());
        userService.updateByPrimaryKeySelective(u);
        return success("操作成功");
    }

    /**
     * 修改前
     */
    @PostMapping("/user/info")
    public ApiResult userInfo(@RequestBody(required = false) QueryParams queryParams) {
        final String id = queryParams.getId();
        if (StringUtils.isBlank(id)) {
            return error("参数错误");
        }
        User user = userService.selectByPrimaryKey(id);
        User u = new User();
        u.setUserId(user.getUserId());
        u.setUserName(user.getUserName());
        u.setUserMobile(user.getUserMobile());
        u.setUserCnName(user.getUserCnName());
        u.setUserCertificateType(user.getUserCertificateType());
        u.setUserCertificateNumber(user.getUserCertificateNumber());
        u.setUserIdCardFontImg(user.getUserIdCardFontImg());
        u.setUserIdCardBackImg(user.getUserIdCardBackImg());
        return success(u);
    }

    /**
     * 图片上传
     */
    @PostMapping("/file/upload")
    public ApiResult fileUpload(String userId, MultipartFile file) {
        String url = "";
        if (file != null && !file.isEmpty()) {
            url = fileUploadUtils.upload(file, userId, FileUploadUtils.FOLDER_USER);
        }
        return success(url);
    }

    /**
     * 修改 保存
     */
    @PostMapping("/user/save")
    public ApiResult userSave(@RequestBody(required = false) User user, MultipartFile font, MultipartFile back) {
        final String id = user.getUserId();
        if (StringUtils.isBlank(id)) {
            return error("参数错误");
        }
        String fontImg = user.getUserIdCardFontImg();
        String backImg = user.getUserIdCardBackImg();
        User u = new User();
        u.setUserIdCardFontImg(fontImg);
        u.setUserIdCardBackImg(backImg);
        u.setUserId(user.getUserId());
        u.setUserName(user.getUserName());
        u.setUserMobile(user.getUserMobile());
        u.setUserCertificateType(user.getUserCertificateType());
        u.setUserCertificateNumber(user.getUserCertificateNumber());
        userService.updateByPrimaryKeySelective(u);
        return success("用户信息修改成功");
    }

    /**
     * 获取用户管理列表
     *
     * @return
     */
    @PostMapping("/getUserMemberList")
    public ApiResult getUserMemberList(@RequestBody UserForm userForm) {
        List<Map<String, Object>> list = userService.getUserMemberList(userForm);
        return success(new PageInfo<>(list));
    }

    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    @GetMapping("/getUserInfo")
    public ApiResult getUserInfo() {
        UserForm userForm = new UserForm();
        userForm.setUserId(auth.getCurrentUser().getUserId());
        List<Map<String, Object>> list = userService.getUserMemberList(userForm);
        return success(new PageInfo<>(list));
    }

    /**
     * 获取所有角色和分组
     *
     * @return
     */
    @PostMapping("/getAllRoleAndTeam")
    public ApiResult getAllRoleAndTeam() {
        Map<String,Object> map = new HashMap<>();
        map.put("allRoles",roleService.getAllRole());
        map.put("allTeams",teamService.getAllTeams());
        return success(map);
    }

    /**
     * 获取角色的资源
     *
     * @return
     */
    @GetMapping("/roleResources")
    public ApiResult roleResources(@RequestParam String roleId) {
        return success(resourceService.getRoleResources(roleId));
    }

    /**
     * 后台管理用户管理创建用户
     *
     * @return
     */
    @PostMapping("/addUser")
    public ApiResult addUser(@RequestBody UserRequest userRequest){
        userService.addUser(userRequest);
        return success("创建成功");
    }

    /**
     * 后台管理用户管理修改用户
     *
     * @param userRequest
     * @return
     */
    @PostMapping("/modify")
    public ApiResult modify(@RequestBody UserRequest userRequest) {
        userService.modify(userRequest);
        return success("修改成功");
    }

}
