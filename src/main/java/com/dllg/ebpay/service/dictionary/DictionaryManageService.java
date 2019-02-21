package com.dllg.ebpay.service.dictionary;

import com.dllg.ebpay.model.generator.Dict;
import com.dllg.ebpay.model.request.QueryParams;
import com.dllg.framework.model.AuthUser;

import java.util.List;
import java.util.Map;

public interface DictionaryManageService {

    List<Map<String,Object>> manageList(QueryParams queryParams);

    String save(Dict dict, AuthUser user);

    String add(Dict dict);

    String update(Dict dict);

    String deletes(List<String> ids);

}
