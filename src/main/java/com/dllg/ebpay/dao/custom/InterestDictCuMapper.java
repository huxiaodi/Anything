package com.dllg.ebpay.dao.custom;

import com.dllg.ebpay.model.generator.InterestDict;
import com.dllg.ebpay.model.request.QueryParams;

import java.util.List;
import java.util.Map;

public interface InterestDictCuMapper { //extends DictMapper {

    /**
     * 列表
     */
    List<Map<String,Object>> dataList(QueryParams queryParams);

    List<InterestDict> selectByExample();

    int add(InterestDict interestDict);

    int update(InterestDict interestDict);

    int deletes(List<String> ids);

    List<Map<String,Object>> selectByPrimaryKey(String keyValue);
    //List<Map<String,Object>> dataList(QueryParams queryParams);

    String selectInterest(String interestMonth);

}
