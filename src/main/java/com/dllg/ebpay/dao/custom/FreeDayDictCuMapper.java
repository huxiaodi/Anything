package com.dllg.ebpay.dao.custom;

import com.dllg.ebpay.model.generator.FreeDayDict;
import com.dllg.ebpay.model.generator.FreeDayDictGet;
import com.dllg.ebpay.model.generator.InterestDict;
import com.dllg.ebpay.model.generator.InterestManager;
import com.dllg.ebpay.model.request.QueryParams;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface FreeDayDictCuMapper { //extends DictMapper {

    /**
     * 列表
     */
    List<Map<String,Object>> dataList(QueryParams queryParams);

    int add(FreeDayDict freeDayDict);

    int update(FreeDayDict freeDayDict);

    int deletes(List<String> ids);

    List<Map<String,Object>> selectByPrimaryKey(String keyValue);
    //List<Map<String,Object>> dataList(QueryParams queryParams);

    FreeDayDict getFreeDay(FreeDayDict freeDayDict);

}
