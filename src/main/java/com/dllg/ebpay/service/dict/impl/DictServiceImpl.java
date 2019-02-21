package com.dllg.ebpay.service.dict.impl;

import com.dllg.ebpay.dao.generator.DictMapper;
import com.dllg.ebpay.model.generator.Dict;
import com.dllg.ebpay.model.generator.DictExample;
import com.dllg.ebpay.service.dict.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 15:50 2018/4/17
 */
@Service
@Transactional
public class DictServiceImpl  implements DictService{

    @Autowired
    private DictMapper dictMapper;


    @Override
    public List<Dict> getDictByType(String type) {
        DictExample dictExample = new DictExample();
        dictExample.createCriteria().andDictTypeEqualTo(type).andDictIsDeleteEqualTo(false);
        return dictMapper.selectByExample(dictExample);
    }

    @Override
    public List<Dict> getDictByKey(String key) {
        DictExample dictExample = new DictExample();
        dictExample.createCriteria().andDictKeyEqualTo(key).andDictIsDeleteEqualTo(false);
        return dictMapper.selectByExample(dictExample);
    }
}
