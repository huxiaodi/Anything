package com.dllg.ebpay.web.admin;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.dllg.ebpay.model.generator.Dict;
import com.dllg.ebpay.model.generator.InterestDict;
import com.dllg.ebpay.model.request.ApiRegistRequest;
import com.dllg.ebpay.model.request.QueryParams;
import com.dllg.ebpay.service.dictionary.DictionaryManageService;
import com.dllg.ebpay.service.interestDict.InterestDictService;
import com.dllg.framework.controller.BaseController;
import com.dllg.framework.model.ApiResult;
import com.dllg.framework.security.Auth;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 利率字典管理
 */
@RestController
@RequestMapping("interestDict")
public class InterestDictController extends BaseController {

    @Autowired
    private InterestDictService interestDictService;
    @Autowired
    private Auth auth;

    /**
     * 列表
     */
    @PostMapping("/list")
    public ApiResult list(@RequestBody(required = false) QueryParams queryParams) {

        queryParams.startPage();
        List<Map<String,Object>> list = interestDictService.dataList(queryParams);
        return success(new PageInfo<>(list));
    }


    /**
     * 修改 保存
     */
    @RequestMapping("/save")
    @Transactional
    public ApiResult register(MultipartFile attachedFile, String baseInterest) throws Exception {

        InterestDict interestDict = JSONObject.parseObject(baseInterest, new TypeReference<InterestDict>() {
        });

        return interestDictService.save(attachedFile, interestDict, auth.getCurrentUser());
    }

//    @PostMapping("/save")
//    public ApiResult save(@RequestBody(required = false) InterestDict interestDict) {
//
//        interestDictService.save(interestDict, auth.getCurrentUser());
//        return success("保存字典修改成功");
//    }

    /**
     * 删除
     */
    @PostMapping("/deletes")
    public ApiResult deletes(@RequestBody(required = false) List<String> ids) {

        interestDictService.deletes(ids);
        return success("删除成功");
    }

    /**
     * 检查主键
     */
    @RequestMapping(value = "/checkKey/{keyValue}", method = RequestMethod.GET)
    public ApiResult checkKey(@PathVariable String keyValue){
        Boolean b = interestDictService.checkKey(keyValue);
        try {
            return success(b);
        }catch (Exception e){
            return error("检查主键失败");
        }
    }


}
