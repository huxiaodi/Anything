package com.dllg.ebpay.dao.custom;

import com.dllg.ebpay.dao.generator.DictMapper;
import com.dllg.ebpay.model.generator.Dict;
import com.dllg.ebpay.model.request.QueryParams;

import java.util.List;
import java.util.Map;

public interface DictionaryManageCuMapper extends DictMapper {

    /**
     * 列表
     */
    List<Map<String,Object>> manageList(QueryParams queryParams);

    int add(Dict dict);

    int update(Dict dict);

    int deletes(List<String> ids);

}
