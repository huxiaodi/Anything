package com.dllg.ebpay.service.freeDayDict;

import com.dllg.ebpay.model.generator.CustomerDict;
import com.dllg.ebpay.model.generator.FreeDayDict;
import com.dllg.ebpay.model.generator.KindDict;
import com.dllg.ebpay.model.request.QueryParams;
import com.dllg.framework.model.AuthUser;

import java.util.List;
import java.util.Map;

public interface FreeDayDictService {

    List<Map<String,Object>> dataList(QueryParams queryParams);

    String save(FreeDayDict freeDayDict, AuthUser user);

    String add(FreeDayDict freeDayDict);

    String update(FreeDayDict freeDayDict);

    String deletes(List<String> ids);

    Boolean checkKey(String keyValue);


    List<KindDict> getKindDictSelect();

    List<CustomerDict> getCustomerDictSelect();

}
