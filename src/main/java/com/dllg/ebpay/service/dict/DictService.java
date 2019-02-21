package com.dllg.ebpay.service.dict;

import com.dllg.ebpay.model.generator.Dict;

import java.util.List;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 15:49 2018/4/17
 */
public interface DictService {
    List<Dict> getDictByType(String  type);
    List<Dict> getDictByKey(String key);

}
