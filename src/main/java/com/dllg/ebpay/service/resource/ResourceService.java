package com.dllg.ebpay.service.resource;

import com.dllg.ebpay.model.dto.Menu;
import com.dllg.ebpay.model.generator.Resource;

import java.util.List;
import java.util.Map;

public interface ResourceService {

    Map<String, Object> getRoleResources(String roleId);


    /***
     *
     * @author hugo
     * @Description: 新增资源
     * @param resource
     * @return
     * @throws
     */
    void add(Resource resource);


    /***
     *
     * @author hugo
     * @Description: 修改资源
     * @param resource
     * @return
     * @throws
     */
    void modify(Resource resource);

    /***
     *
     * @author hugo
     * @Description: 根据资源ID删除资源
     * @param resourceId
     * @return
     * @throws
     */
    void delete(String resourceId);


    /***
     *
     * @author hugo
     * @Description: 获取资源列表
     * @param
     * @return
     * @throws
     */
    List<Resource> grid();

    List<Menu> getResouceTreeData();

    List<Menu> getFrontMenu(String parentId);

    List<String> getFrontButton();
}
