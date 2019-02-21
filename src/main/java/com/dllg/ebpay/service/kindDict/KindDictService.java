package com.dllg.ebpay.service.kindDict;

import com.dllg.ebpay.model.generator.InterestDict;
import com.dllg.ebpay.model.generator.KindDict;
import com.dllg.ebpay.model.request.QueryParams;
import com.dllg.framework.model.AuthUser;

import java.util.List;
import java.util.Map;

public interface KindDictService {

    List<Map<String,Object>> dataList(QueryParams queryParams);

    String save(KindDict kindDict, AuthUser user);

    String add(KindDict kindDict);

    String update(KindDict kindDict);

    String deletes(List<String> ids);

    Boolean checkKey(String keyValue);

}
