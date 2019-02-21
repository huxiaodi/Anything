package com.dllg.ebpay.web.admin;

import com.dllg.ebpay.model.generator.Dict;
import com.dllg.ebpay.model.request.QueryParams;
import com.dllg.ebpay.service.dictionary.DictionaryManageService;
import com.dllg.framework.controller.BaseController;
import com.dllg.framework.model.ApiResult;
import com.dllg.framework.security.Auth;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 字典管理
 */
@RestController
@RequestMapping("admin/dictionaryManage")
public class DictionaryManageController extends BaseController {

    @Autowired
    private DictionaryManageService dictionaryManageService;
    @Autowired
    private Auth auth;

    /**
     * 列表
     */
    @PostMapping("/list")
    public ApiResult list(@RequestBody(required = false) QueryParams queryParams) {

        queryParams.startPage();
        List<Map<String,Object>> list = dictionaryManageService.manageList(queryParams);
        return success(new PageInfo<>(list));
    }


    /**
     * 修改 保存
     */
    @PostMapping("/save")
    public ApiResult save(@RequestBody(required = false) Dict dict) {

        dictionaryManageService.save(dict, auth.getCurrentUser());
        return success("保存字典修改成功");
    }


    @PostMapping("/deletes")
    public ApiResult deletes(@RequestBody(required = false) List<String> ids) {

        dictionaryManageService.deletes(ids);
        return success("删除成功");
    }


}
