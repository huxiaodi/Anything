package com.dllg.ebpay.dao.custom;

import com.dllg.ebpay.model.generator.CustomerDict;
import com.dllg.ebpay.model.request.QueryParams;

import java.util.List;
import java.util.Map;

public interface CustomerDictCuMapper { //extends DictMapper {

    /**
     * 列表
     */
    List<Map<String,Object>> dataList(QueryParams queryParams);

    List<CustomerDict> selectByExample();

    int add(CustomerDict customerDict);

    int update(CustomerDict customerDict);

    int deletes(List<String> ids);

    List<Map<String,Object>> selectByPrimaryKey(String keyValue);
    //List<Map<String,Object>> dataList(QueryParams queryParams);

}
