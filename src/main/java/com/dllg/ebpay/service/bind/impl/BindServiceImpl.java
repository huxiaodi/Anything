package com.dllg.ebpay.service.bind.impl;

import com.dllg.ebpay.dao.generator.AreaMapper;
import com.dllg.ebpay.dao.generator.BankCardMapper;
import com.dllg.ebpay.dao.generator.SuperInternetBankMapper;
import com.dllg.ebpay.dao.generator.ZjjzCnapsBankinfoMapper;
import com.dllg.ebpay.model.generator.*;
import com.dllg.ebpay.service.bind.BindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BindServiceImpl implements BindService {

    @Autowired
    private ZjjzCnapsBankinfoMapper zjjzCnapsBankinfoMapper;
    @Autowired
    private SuperInternetBankMapper superInternetBankMapper;
    @Autowired
    private BankCardMapper bankCardMapper;
    @Autowired
    private AreaMapper areaMapper;


    @Override
    public List<Area> selectProvince() {
        AreaExample areaExample = new AreaExample();
        areaExample.createCriteria().andAreaLevelEqualTo("1");
        List<Area> list = areaMapper.selectByExample(areaExample);
        return list;
    }

    @Override
    public List<Area> selectCity(Area area) {
        AreaExample areaExample = new AreaExample();
        areaExample.createCriteria().andAreaLevelEqualTo("2").andAreaParentIdEqualTo(area.getAreaId());
        List<Area> list = areaMapper.selectByExample(areaExample);
        return list;
    }

    @Override
    public List<Area> selectDistrict(Area area) {
        AreaExample areaExample = new AreaExample();
        areaExample.createCriteria().andAreaLevelEqualTo("3").andAreaParentIdEqualTo(area.getAreaId());
        List<Area> list = areaMapper.selectByExample(areaExample);
        return list;
    }

    @Override
    public List<ZjjzCnapsBankinfo> selectZjjzCnapsBankinfo(PubPayCity pubPayCity) {
        ZjjzCnapsBankinfoExample zbe = new ZjjzCnapsBankinfoExample();
        zbe.createCriteria().andCitycodeEqualTo(pubPayCity.getCityOraareacode());
        List<ZjjzCnapsBankinfo> list = zjjzCnapsBankinfoMapper.selectByExample(zbe);
        return list;
    }

    @Override
    public List<SuperInternetBank> selectSuperInternetBank() {
        SuperInternetBankExample sbe = new SuperInternetBankExample();
        List<SuperInternetBank> list = superInternetBankMapper.selectByExample(sbe);
        return list;
    }

    @Override
    public void insertBankCard(BankCard bankCard) {
        bankCardMapper.insert(bankCard);
    }
}
