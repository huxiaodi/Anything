package com.dllg.ebpay.web.admin;

import com.dllg.ebpay.model.generator.KindDict;
import com.dllg.ebpay.model.request.QueryParams;
import com.dllg.ebpay.service.kindDict.KindDictService;
import com.dllg.framework.controller.BaseController;
import com.dllg.framework.model.ApiResult;
import com.dllg.framework.security.Auth;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 品种字典管理
 */
@RestController
@RequestMapping("kindDict")
public class KindDictController extends BaseController {

    @Autowired
    private KindDictService kindDictService;
    @Autowired
    private Auth auth;

    /**
     * 列表
     */
    @PostMapping("/list")
    public ApiResult list(@RequestBody(required = false) QueryParams queryParams) {

        queryParams.startPage();
        List<Map<String,Object>> list = kindDictService.dataList(queryParams);
        return success(new PageInfo<>(list));
    }


    /**
     * 修改 保存
     */
    @PostMapping("/save")
    public ApiResult save(@RequestBody(required = false) KindDict kindDict) {

        kindDictService.save(kindDict, auth.getCurrentUser());
        return success("保存字典修改成功");
    }

    /**
     * 删除
     */
    @PostMapping("/deletes")
    public ApiResult deletes(@RequestBody(required = false) List<String> ids) {

        kindDictService.deletes(ids);
        return success("删除成功");
    }

    /**
     * 检查主键
     */
    @RequestMapping(value = "/checkKey/{keyValue}", method = RequestMethod.GET)
    public ApiResult checkKey(@PathVariable String keyValue){
        Boolean b = kindDictService.checkKey(keyValue);
        try {
            return success(b);
        }catch (Exception e){
            return error("检查主键失败");
        }
    }


}
