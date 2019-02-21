package com.dllg.ebpay.service.customerDict;

import com.dllg.ebpay.model.generator.CustomerDict;
import com.dllg.ebpay.model.request.QueryParams;
import com.dllg.framework.model.AuthUser;

import java.util.List;
import java.util.Map;

public interface CustomerDictService {

    List<Map<String,Object>> dataList(QueryParams queryParams);

    String save(CustomerDict customerDict, AuthUser user);

    String add(CustomerDict customerDict);

    String update(CustomerDict customerDict);

    String deletes(List<String> ids);

    Boolean checkKey(String keyValue);



}
