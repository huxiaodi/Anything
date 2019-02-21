package com.dllg.ebpay.service.interestManager;

import com.dllg.ebpay.model.generator.CustomerDict;
import com.dllg.ebpay.model.generator.InterestDict;
import com.dllg.ebpay.model.generator.InterestManager;
import com.dllg.ebpay.model.generator.KindDict;
import com.dllg.ebpay.model.request.QueryParams;
import com.dllg.framework.model.AuthUser;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface InterestManagerService {

    List<Map<String,Object>> dataList(QueryParams queryParams);
    List<Map<String,Object>> dataListK(QueryParams queryParams);

    String save(InterestManager interestManager, AuthUser user);

    String add(InterestManager interestManager);

    String copyAdd(InterestManager interestManager, AuthUser user);

    String update(InterestManager interestManager);

    String deletes(List<String> ids);

    Boolean checkKey(String keyValue);


    List<KindDict> getKindDictSelect();

    List<CustomerDict> getCustomerDictSelect();

    List<InterestDict> getInterestDictSelect();

    String editAgain(InterestManager interestManager, AuthUser user);

    String interestCompute(InterestManager interestManager, AuthUser user);


    public String impFile(MultipartFile[] file, AuthUser user);

    String useMoneyAvg(InterestManager interestManager, AuthUser user);

    String setFinish(InterestManager interestManager, AuthUser user);



}
