package com.test.manage.service.role.impl;

import com.test.common.CommonUtil;
import com.test.manage.dao.custom.RoleCuMapper;
import com.test.manage.dao.generator.*;
import com.test.manage.model.generator.*;
import com.test.manage.model.request.RolePageParams;
import com.test.manage.model.request.RoleParams;
import com.test.manage.service.role.RoleService;
import com.test.framework.model.ApiResult;
import com.test.framework.model.AuthUser;
import com.test.framework.security.Auth;
import com.test.framework.utils.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.test.framework.utils.ApiResultUtil.error;
import static com.test.framework.utils.ApiResultUtil.success;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleCuMapper roleCuMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private Auth auth;
    @Autowired
    private RoleResourceMapper roleResourceMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private userRoleMapper userRoleMapper;

    private String passwordReg = "^(?=[^A-Z\\u4e00-\\u9fa5]*[0-9])(?=[^A-Z\\u4e00-\\u9fa5]*[a-z])[^A-Z\\u4e00-\\u9fa5]{8,50}$";

    @Override
    public List<Role> getAllRole() {
        RoleExample roleExample = new RoleExample();
        roleExample.setOrderByClause("role_index");
        RoleExample.Criteria criteria = roleExample.createCriteria();
        AuthUser authUser = auth.getCurrentUser();
        Integer userType = authUser.getUserType();
        // 角色类型 1、前台 2、后台
        if (userType == 0) {
            criteria.andRoleTypeEqualTo(2).andRoleIsDeleteEqualTo(false);
        } else {
            criteria.andRoleTypeEqualTo(1).andRoleCompanyIdEqualTo(authUser.getUserCompanyId()).andRoleIsDeleteEqualTo(false);
        }
        return roleMapper.selectByExample(roleExample);
    }

    @Override
    public ApiResult checkMobile(RoleParams roleParams) {
        User user = userMapper.selectByPrimaryKey(roleParams.getUserId());
        if (!roleParams.getMobile().equals(user.getUserMobile())) {
            return error("预留手机错误");
        } else {
            return success("");
        }
    }

    @Override
    public ApiResult setUserPayPassword(User user) {
        try {
            user.setUserPayPassword(SecurityUtils.encrypt(user.getUserPayPassword()));
            userMapper.updateByPrimaryKeySelective(user);
            return success("设置成功");
        } catch (Exception e) {
            return error("设置失败" + e);
        }
    }

    @Override
    public ApiResult checkUserPayPassword(RoleParams roleParams) {
        User user = userMapper.selectByPrimaryKey(roleParams.getUserId());
        if (!roleParams.getOldPassword().equals(user.getUserPayPassword())) {
            return error("原密码错误");
        } else {
            return success("");
        }
    }

    @Override
    public ApiResult changeUserPayPassword(RoleParams roleParams) {
        try {
            User user = new User();
            user.setUserId(roleParams.getUserId());
            user.setUserPayPassword(SecurityUtils.encrypt(roleParams.getUserPayPassword()));
            userMapper.updateByPrimaryKeySelective(user);
            return success("修改成功");
        } catch (Exception e) {
            return error("修改失败" + e);
        }
    }

    @Override
    public boolean checkSet(String userId) {
        User user1 = userMapper.selectByPrimaryKey(userId);
        if ("".equals(user1.getUserPayPassword())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Map<String, Object>> manageList(RolePageParams rolePageParams) {
        return roleCuMapper.manageList(rolePageParams);
    }
    @Override
    public List<Map<String, Object>> manageListFront(RolePageParams rolePageParams) {
        rolePageParams.setRoleCompanyId(auth.getCurrentUser().getUserCompanyId());
        return roleCuMapper.manageListFront(rolePageParams);
    }
    @Override
    public void deleteRole(String roleId) {
        AuthUser authUser = auth.getCurrentUser();
        Role role = new Role();
        role.setRoleId(roleId);
        role.setRoleIsDelete(true);
        role.setRoleUpdateTime(new Date());
        role.setRoleUpdateUserId(authUser.getUserId());
        roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public List<Resource> allRoleResources() {
        return roleCuMapper.allRoleResources();
    }
    @Override
    public List<Resource> frontRoleResources() {
        return roleCuMapper.frontRoleResources();
    }
    @Override
    public void addRole(RoleParams roleParams) {
        AuthUser authUser = auth.getCurrentUser();
        Role role = new Role();
        RoleResource roleResource = new RoleResource();
        role.setRoleId(CommonUtil.uuid());
        role.setRoleName(roleParams.getRoleName());
        role.setRoleEnName(roleParams.getRoleEnName());
        role.setRoleIndex(roleParams.getRoleIndex());
        role.setRoleCreateUserId(authUser.getUserId());
        role.setRoleCreateTime(new Date());
        role.setRoleType(2);
        roleMapper.insertSelective(role);
        roleParams.setRoleId(role.getRoleId());
        updateRoleResource(roleParams);
    }

    @Override
    public void addRoleFront(RoleParams roleParams) {
        AuthUser authUser = auth.getCurrentUser();
        Role role = new Role();
        RoleResource roleResource = new RoleResource();
        role.setRoleId(CommonUtil.uuid());
        role.setRoleName(roleParams.getRoleName());
        role.setRoleEnName(roleParams.getRoleEnName());
        role.setRoleIndex(roleParams.getRoleIndex());
        role.setRoleCreateUserId(authUser.getUserId());
        role.setRoleCreateTime(new Date());
        role.setRoleCompanyId(authUser.getUserCompanyId());
        role.setRoleType(1);
        roleMapper.insertSelective(role);
        roleParams.setRoleId(role.getRoleId());
        updateRoleResource(roleParams);
    }
    @Override
    public void updateRole(RoleParams roleParams) {
        AuthUser authUser = auth.getCurrentUser();
        Role role = new Role();
        BeanUtils.copyProperties(roleParams, role);
        role.setRoleUpdateUserId(authUser.getUserId());
        role.setRoleUpdateTime(new Date());
        roleMapper.updateByPrimaryKeySelective(role);

        roleParams.setRoleId(role.getRoleId());
        updateRoleResource(roleParams);
    }

    public void updateRoleResource(RoleParams roleParams) {
        AuthUser authUser = auth.getCurrentUser();
        //先删除
        RoleResourceExample example = new RoleResourceExample();
        example.createCriteria()
                .andRoleResourceRoleIdEqualTo(roleParams.getRoleId());
        roleResourceMapper.deleteByExample(example);
        //再增加
        if (roleParams.getResourceIds() != null) {
            roleParams.getResourceIds().forEach(item -> {
                RoleResource roleResource = new RoleResource();
                roleResource.setRoleResourceRoleId(roleParams.getRoleId());
                roleResource.setRoleResourceResourceId(item);
                roleResource.setRoleResourceCreateUserId(authUser.getUserId());
                roleResource.setRoleResourceCreateTime(new Date());
                roleResourceMapper.insertSelective(roleResource);
            });
        }
    }


    public void addFrontUserRole(User user){
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
