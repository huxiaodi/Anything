package com.dllg.ebpay.service.customerDict.impl;

import com.dllg.common.CommonUtil;
import com.dllg.ebpay.dao.custom.CustomerDictCuMapper;
import com.dllg.ebpay.dao.generator.UserMapper;
import com.dllg.ebpay.model.generator.CustomerDict;
import com.dllg.ebpay.model.request.QueryParams;
import com.dllg.ebpay.service.customerDict.CustomerDictService;
import com.dllg.framework.model.AuthUser;
import com.dllg.framework.security.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CustomerDictServiceImpl implements CustomerDictService {


    @Autowired
    private CustomerDictCuMapper customerDictCuMapper;

    @Autowired
    private Auth auth;

    @Autowired
    private UserMapper userMapper;

    /**
     * 列表
     */
    @Override
    public List<Map<String, Object>> dataList(QueryParams queryParams) {
        List<Map<String, Object>> list = customerDictCuMapper.dataList(queryParams);
        String createUserId;
        String updateUserId;
        for (Map<String, Object> map : list) {
            createUserId = map.get("createUserId").toString();
            if (!"".equals(createUserId)) {
                map.put("createUserId", userMapper.selectByPrimaryKey(createUserId).getUserName());
            }
            updateUserId = map.get("updateUserId").toString();
            if (!"".equals(updateUserId)) {
                map.put("updateUserId", userMapper.selectByPrimaryKey(updateUserId).getUserName());
            }
        }
        return list;
    }

    public String save(CustomerDict customerDict, AuthUser users) {

        AuthUser user = auth.getCurrentUser();


        if (customerDict.getcustomerId() == null) { //新增
//            if("退款额度字典".equals(dict.getDictType()) || "付款申请额度字典".equals(dict.getDictType())){
//                List list = dictService.getDictByType(dict.getDictType());
//                if(list != null && list.size()>= 1){
//                    throw  new AnyException("该类型的字典只能设定一个");
//                }
//            }
            customerDict.setcustomerId(CommonUtil.uuid());
            customerDict.setCreateUserId(user.getUserId());
            customerDict.setCreateTime(new Date());
            this.add(customerDict);
        } else { //修改
            customerDict.setUpdateUserId(user.getUserId());
            customerDict.setUpdateTime(new Date());
            this.update(customerDict);
        }
        return "success";
    }

    @Override
    public String add(CustomerDict customerDict) {
        customerDictCuMapper.add(customerDict);
        return "success";
    }

    @Override
    public String update(CustomerDict customerDict) {

        customerDictCuMapper.update(customerDict);
        return "success";
    }

    @Override
    public String deletes(List<String> ids) {
//        List<Dict> list = dictService.getDictByType("退款额度字典");
//        if (list == null || list.size() != 1) {
//            throw new AnyException("退款额度字典设定有误");
//        }
//        for (String i : ids) {
//            if (i.equals(list.get(0).getInterestId())) {
//                throw new AnyException("删除的字典中含有不可删除的字典类型："+list.get(0).getDictType());
//            }
//        }
        customerDictCuMapper.deletes(ids);
        return "success";
    }

    @Override
    public Boolean checkKey(String keyValue) {
        List<Map<String, Object>> list = customerDictCuMapper.selectByPrimaryKey(keyValue);
        if (list.size() != 0) {
            return true;
        }else{
            return false;
        }
    }




}
