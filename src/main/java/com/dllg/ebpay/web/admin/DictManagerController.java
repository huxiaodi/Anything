package com.dllg.ebpay.web.admin;

import com.dllg.ebpay.model.generator.Dict;
import com.dllg.ebpay.service.dict.DictService;
import com.dllg.framework.controller.BaseController;
import com.dllg.framework.model.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 15:48 2018/4/17
 */
@RestController
@RequestMapping("/dictManager")
public class DictManagerController extends BaseController{

    @Autowired
    private DictService dictService;

    @GetMapping("/getBusinesTradeTypeDict")
    private ApiResult getBusinesTradeTypeDict(){
        return success(dictService.getDictByType("业务来源"));
    }

    @GetMapping("/getBusinesSourceDict")
    private ApiResult getBusinesSourceDict(){
        return success(dictService.getDictByType("业务类型"));
    }

    @GetMapping("/getDict")
    private ApiResult getDict(String type){
        return success(dictService.getDictByType(type));
    }

    @GetMapping("/getPayRate")
    private ApiResult getPayRate(String key){
        BigDecimal bigDecimal = BigDecimal.ZERO;
        try {
            Dict dict = dictService.getDictByKey(key).stream().findFirst().orElse(null);
            if(dict != null){
                String rate = dict.getDictValue();

                bigDecimal = new BigDecimal(rate.substring(0,rate.length() - 1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success(bigDecimal);
    }
}
