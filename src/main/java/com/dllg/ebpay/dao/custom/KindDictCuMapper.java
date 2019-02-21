package com.dllg.ebpay.dao.custom;

import com.dllg.ebpay.model.generator.KindDict;
import com.dllg.ebpay.model.request.QueryParams;

import java.util.List;
import java.util.Map;

public interface KindDictCuMapper { //extends DictMapper {

    /**
     * 列表
     */
    List<Map<String,Object>> dataList(QueryParams queryParams);

    List<KindDict> selectByExample();

    int add(KindDict kindDict);

    int update(KindDict kindDict);

    int deletes(List<String> ids);

    List<Map<String,Object>> selectByPrimaryKey(String keyValue);
    //List<Map<String,Object>> dataList(QueryParams queryParams);

}
