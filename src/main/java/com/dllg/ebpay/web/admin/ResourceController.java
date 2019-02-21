package com.dllg.ebpay.web.admin;

import com.dllg.ebpay.dao.custom.ResourceCuMapper;
import com.dllg.ebpay.model.dto.Menu;
import com.dllg.ebpay.model.dto.ResourceDto;
import com.dllg.ebpay.model.generator.Resource;
import com.dllg.ebpay.service.resource.ResourceService;
import com.dllg.framework.controller.BaseController;
import com.dllg.framework.model.ApiResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/basic")
public class ResourceController extends BaseController {
    @Autowired
    private ResourceService resourceService;

    @Autowired
    private ResourceCuMapper resourceCuMapper;



    /***
     *
     * @author hugo
     * @Description: 获取资源
     * @param
     * @return
     * @throws
     */
    @RequestMapping(value = "/resource", method = RequestMethod.GET)
    public ApiResult menus() {
        List<Resource> list = resourceService.grid();
        List<ResourceDto> listDto = new ArrayList<ResourceDto>(list.size());
        for(int i = 0;i<list.size();i++  ){
            Resource  resource = list.get(i);
            ResourceDto resourceDto = new ResourceDto();
            BeanUtils.copyProperties(resource, resourceDto);
            int hasIndex = 0;
            for(int j = 0 ;j<list.size();j++){
                if(resource.getResourceId().equals(list.get(j).getParentResourceId())){
                    hasIndex++;
                }
            }
            resourceDto.setChildNum(hasIndex);
            listDto.add(resourceDto);
        }
        return success(listDto);
    }


    /***
     *
     * @author hugo
     * @Description: 添加资源
     * @param resource
     * @return
     * @throws
     */
    @RequestMapping(value = "/resource", method = RequestMethod.POST)
    public ApiResult add(@RequestBody Resource resource) {
        resourceService.add(resource);
        return success("资源添加成功");
    }

    /***
     *
     * @author hugo
     * @Description: 修改资源
     * @param resource
     * @return
     * @throws
     */
    @RequestMapping(value = "/resource", method = RequestMethod.PUT)
    public ApiResult update(@RequestBody Resource resource) {
        resourceService.modify(resource);
        return success("资源修改成功");
    }

    /***
     *
     * @author hugo
     * @Description: 删除资源
     * @param id
     * @return
     * @throws
     */
    @RequestMapping(value = "/resource/{id}", method = RequestMethod.DELETE)
    public ApiResult delete(@PathVariable String id) {
        resourceService.delete(id);
        return success("资源删除成功");
    }

    /***
     *
     * @author hugo
     * @Description: 获取全部资源
     * @return
     * @throws
     */
    @GetMapping("/getResouceTreeData")
    public ApiResult getResouceTreeData() {
        return success(resourceService.getResouceTreeData());
    }

    @GetMapping("/getFrontResouceTreeData")
    public ApiResult getFrontResouceTreeData() {
        List<Menu> frontMenu = resourceService.getFrontMenu("2");
        List<String> frontButton = resourceService.getFrontButton();
        Map<String, Object> map = new HashMap<>();
        map.put("frontMenu",frontMenu);
        map.put("frontButton",frontButton);
        return success(map);
    }

}
