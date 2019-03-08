package com.test.manage.web;


import com.test.common.CommonUtil;
import com.test.manage.configurer.EbpayProperties;
import com.test.manage.dao.generator.ResourceMapper;
import com.test.manage.dao.generator.RoleMapper;
import com.test.manage.dao.generator.RoleResourceMapper;
import com.test.manage.dao.generator.UserMapper;
import com.test.manage.model.generator.*;
import com.test.manage.service.home.HomeService;
import com.test.framework.controller.BaseController;
import com.test.framework.model.ApiResult;
import com.test.framework.utils.FileUploadUtils;
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
    private EbpayProperties ebpayProperties;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private com.test.manage.dao.generator.userRoleMapper userRoleMapper;
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @PostMapping("/user")
    public ApiResult user(@RequestBody User user) {
        User user2 = homeService.selectUser(user.getUserId());
        return success(user2);
    }


    @PostMapping("/upload")
    public void upload(MultipartFile font, String userId) {
        String userHead = fileUploadUtils.upload(font, userId, FileUploadUtils.FOLDER_USER);
        User user = new User();
        user.setUserHeadImg(userHead);
        user.setUserId(userId);
        homeService.upload(user);
    }

    @PostMapping("/changeAgentMobile")
    public void changeAgentMobile(@RequestBody User user) {
        homeService.changeAgentMobile(user);
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

