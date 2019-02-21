package com.dllg.ebpay.service.resource.impl;


import com.dllg.common.CommonUtil;
import com.dllg.ebpay.dao.custom.ResourceCuMapper;
import com.dllg.ebpay.dao.generator.ResourceMapper;
import com.dllg.ebpay.dao.generator.userRoleMapper;
import com.dllg.ebpay.model.dto.Menu;
import com.dllg.ebpay.model.generator.*;
import com.dllg.ebpay.service.resource.ResourceService;
import com.dllg.framework.model.AuthUser;
import com.dllg.framework.security.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private ResourceCuMapper resourceCuMapper;

    @Autowired
    private Auth auth;

    @Autowired
    private userRoleMapper userRoleMappers;





    @Override
    public void add(Resource resource) {
        resource.setResourceId(CommonUtil.uuid());
        resource.setResourceCreateUserId(auth.getCurrentUser().getUserId());
        resource.setResourceCreateTime(new Date());
        resourceMapper.insertSelective(resource);
    }


    /*
     * 修改资源
     */
    @Override
    public void modify(Resource resource) {
        resource.setResourceUpdateUserId(auth.getCurrentUser().getUserId());
        resource.setResourceUpdateTime(new Date());
        resource.setResourceIsDelete(false);
        resourceMapper.updateByPrimaryKey(resource);
    }

    /*
     * 根据资源id 删除资源
     */
    @Override
    public void delete(String id) {
        // 查询自己及所有子节点
        List<Menu> menus = resourceCuMapper.root(id);
        List<String> keys = new ArrayList<>();
        // 转换出所有的主键
        CommonUtil.keys(menus, keys);
        // 删除自己及所有子节点
        ResourceExample resourceExample = new ResourceExample();
        resourceExample.createCriteria().andResourceIdIn(keys);
        Resource resource = new Resource();
        resource.setResourceIsDelete(true);
        resource.setResourceUpdateUserId(auth.getCurrentUser().getUserId());
        resource.setResourceUpdateTime(new Date());
        resourceMapper.updateByExampleSelective(resource, resourceExample);
    }




    @Override
    public Map<String, Object> getRoleResources(String roleId) {
        ResourceExample resourceExample = new ResourceExample();
        // 资源类型0-后台,1-前台
        AuthUser authUser = auth.getCurrentUser();
        Integer userType = authUser.getUserType();
        if(userType != 0){
            userType = 1;
        }
        resourceExample.createCriteria().andResourceTypeEqualTo(userType).andResourceIsDeleteEqualTo(false);
        List<Resource> allResources = resourceMapper.selectByExample(resourceExample);
        List<String> roleResources = resourceCuMapper.getRoleResources(roleId).stream().map(Resource::getResourceId).collect(Collectors.toList());
        Map<String,Object> map = new HashMap<>();
        map.put("allResources",allResources);
        map.put("roleResources",roleResources);
        return map;
    }
    public List<Resource> grid() {
        ResourceExample resourceExample = new ResourceExample();
        resourceExample.setOrderByClause("resource_index");
        resourceExample.createCriteria()
                .andResourceIsDeleteEqualTo(false);
        return resourceMapper.selectByExample(resourceExample);
    }

    @Override
    public List<Menu> getResouceTreeData() {
        return  resourceCuMapper.getResouceTreeData();
    }

    @Override
    public List<Menu> getFrontMenu(String parentId){
        AuthUser currentUser = auth.getCurrentUser();
        Map<String, Object> map = new HashMap<>();
        map.put("parentResourceId",parentId);
        map.put("userId",currentUser.getUserId());
        List<Menu> frontMenu = resourceCuMapper.getFrontMenu(map);
        if(frontMenu != null && frontMenu.size() > 0){
            for(Menu menu : frontMenu){
                menu.setChildren(getFrontMenu(menu.getResourceId()));
            }
        }
        return frontMenu;
    }

    @Override
    public List<String> getFrontButton() {
        AuthUser currentUser = auth.getCurrentUser();
        List<String> frontButton = resourceCuMapper.getFrontButton(currentUser.getUserId());
        return frontButton;
    }
}
