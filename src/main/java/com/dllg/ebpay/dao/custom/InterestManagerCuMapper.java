package com.dllg.ebpay.dao.custom;

import com.dllg.ebpay.model.generator.InterestManager;
import com.dllg.ebpay.model.request.QueryParams;

import java.util.List;
import java.util.Map;

public interface InterestManagerCuMapper { //extends DictMapper {

    /**
     * 列表
     */
    List<Map<String,Object>> dataList(QueryParams queryParams);
    List<Map<String,Object>> dataListK(QueryParams queryParams);

    int add(InterestManager interestManager);

    int update(InterestManager interestManager);
    int updateStatus(InterestManager interestManager);

    int deletes(List<String> ids);

    List<Map<String,Object>> selectByPrimaryKey(String keyValue);
    //List<Map<String,Object>> dataList(QueryParams queryParams);

    String getNewNo(String oldNo);

    int editAgain(InterestManager interestManager);


    int interestCompute(InterestManager interestManager);

    String getMoneyUse(String ticketNo); //总使用金额 根据票号统计

    int useMoneyAvg(InterestManager interestManager); //分配总使用金额

}
