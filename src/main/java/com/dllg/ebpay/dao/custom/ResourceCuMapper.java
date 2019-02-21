package com.dllg.ebpay.dao.custom;


import com.dllg.ebpay.model.dto.Menu;
import com.dllg.ebpay.model.generator.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ResourceCuMapper {

    List<Resource> getRoleResources(String roleId);

    //查询根及所有子
    List<Menu> root(String resource_id);

    List<Menu> getResouceTreeData();

    List<Menu> getFrontMenu( Map<String, Object> map);

    List<String> getFrontButton(String userId);
}