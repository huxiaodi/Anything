package com.dllg.ebpay.service.interestDict;

import com.dllg.ebpay.model.generator.InterestDict;
import com.dllg.ebpay.model.request.QueryParams;
import com.dllg.framework.model.ApiResult;
import com.dllg.framework.model.AuthUser;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface InterestDictService {

    List<Map<String,Object>> dataList(QueryParams queryParams);

    //String save(InterestDict interestDict, AuthUser user);
    ApiResult save(MultipartFile attachedFile,  InterestDict interestDict, AuthUser user) throws Exception;


    String add(InterestDict interestDict);

    String update(InterestDict interestDict);

    String deletes(List<String> ids);

    Boolean checkKey(String keyValue);

}
