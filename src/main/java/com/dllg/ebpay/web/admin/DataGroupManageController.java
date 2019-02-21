package com.dllg.ebpay.web.admin;

import com.dllg.ebpay.model.form.TeamForm;
import com.dllg.ebpay.model.generator.Dict;
import com.dllg.ebpay.model.generator.Team;
import com.dllg.ebpay.model.request.QueryParams;
import com.dllg.ebpay.service.dataGroup.DataGroupManageService;
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

import java.util.ArrayList;
import java.util.List;

/**
 * 数据组管理
 */
@RestController
@RequestMapping("admin/dataGroupManage")
public class DataGroupManageController extends BaseController {

    @Autowired
    private DataGroupManageService dataGroupManageService;
    @Autowired
    private Auth auth;

    /**
     * 列表
     */
    @PostMapping("/list")
    public ApiResult list(@RequestBody(required = false) QueryParams queryParams) {

         queryParams.startPage();
         List list = dataGroupManageService.manageList(queryParams);
         return success(new PageInfo<>(list));
    }



    @PostMapping("/getRelationInfo")
    public ApiResult getRelationInfo(@RequestBody(required = false) Team team) {

           List<Object> list = new ArrayList<Object>();
                        list.add(dataGroupManageService.getGroup(team));
                        list.add(dataGroupManageService.getTeamDict(team));
        return success(list);
    }


    /**
     * 修改 保存
     */
    @PostMapping("/save")
    public ApiResult save(@RequestBody(required = false) TeamForm teamForm) {

        dataGroupManageService.save(teamForm,auth.getCurrentUser());

        return success("保存成功");
    }


    @PostMapping("/deletes")
    public ApiResult deletes(@RequestBody(required = false) List<String> ids) {

        dataGroupManageService.deletes(ids);
        return success("删除成功");
    }


}
