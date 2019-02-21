package com.dllg.ebpay.service.dictionary.impl;

import com.dllg.common.CommonUtil;
import com.dllg.ebpay.dao.custom.DictionaryManageCuMapper;
import com.dllg.ebpay.dao.generator.DictMapper;
import com.dllg.ebpay.dao.generator.UserMapper;
import com.dllg.ebpay.model.generator.Dict;
import com.dllg.ebpay.model.request.QueryParams;
import com.dllg.ebpay.service.dict.DictService;
import com.dllg.ebpay.service.dictionary.DictionaryManageService;
import com.dllg.framework.exception.AnyException;
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
public class DictionaryManageServiceImpl implements DictionaryManageService {


    @Autowired
    private DictionaryManageCuMapper dictionaryManageCuMapper;

    @Autowired
    private Auth auth;

    @Autowired
    private DictService dictService;

    @Autowired
    private DictMapper dictMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 列表
     */
    @Override
    public List<Map<String, Object>> manageList(QueryParams queryParams) {
        List<Map<String, Object>> list = dictionaryManageCuMapper.manageList(queryParams);
        String createUserId;
        String updateUserId;
        for (Map<String, Object> map : list) {
            createUserId = map.get("dictCreateUserId").toString();
            if (!"".equals(createUserId)) {
                map.put("dictCreateUserId", userMapper.selectByPrimaryKey(createUserId).getUserName());
            }
            updateUserId = map.get("dictUpdateUserId").toString();
            if (!"".equals(updateUserId)) {
                map.put("dictUpdateUserId", userMapper.selectByPrimaryKey(updateUserId).getUserName());
            }
        }
        return list;
    }

    public String save(Dict dict, AuthUser users) {

        AuthUser user = auth.getCurrentUser();

        if (dict.getDictDesc() == null) {
            dict.setDictDesc("");
        }
        if (dict.getDictId() == null) { //新增
            if("退款额度字典".equals(dict.getDictType()) || "付款申请额度字典".equals(dict.getDictType())){
                List list = dictService.getDictByType(dict.getDictType());
                if(list != null && list.size()>= 1){
                    throw  new AnyException("该类型的字典只能设定一个");
                }
            }
            dict.setDictId(CommonUtil.uuid());
            dict.setDictCreateUserId(user.getUserId());
            dict.setDictCreateTime(new Date());
            this.add(dict);
        } else { //修改
            dict.setDictUpdateUserId(user.getUserId());
            dict.setDictUpdateTime(new Date());
            this.update(dict);
        }
        return "success";
    }

    @Override
    public String add(Dict dict) {

        dictionaryManageCuMapper.add(dict);
        return "success";
    }

    @Override
    public String update(Dict dict) {

        dictionaryManageCuMapper.update(dict);
        return "success";
    }

    @Override
    public String deletes(List<String> ids) {
        List<Dict> list = dictService.getDictByType("退款额度字典");
        if (list == null || list.size() != 1) {
            throw new AnyException("退款额度字典设定有误");
        }
        for (String i : ids) {
            if (i.equals(list.get(0).getDictId())) {
                throw new AnyException("删除的字典中含有不可删除的字典类型："+list.get(0).getDictType());
            }
        }
        dictionaryManageCuMapper.deletes(ids);
        return "success";
    }


}
