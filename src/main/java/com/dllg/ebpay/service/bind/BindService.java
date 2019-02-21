package com.dllg.ebpay.service.bind;

import com.dllg.ebpay.model.generator.*;

import java.util.List;

/**
 *
 */
public interface BindService {

    List<Area> selectProvince();

    List<Area> selectCity(Area area);

    List<Area> selectDistrict(Area area);

    List<ZjjzCnapsBankinfo> selectZjjzCnapsBankinfo(PubPayCity pubPayCity);

    List<SuperInternetBank> selectSuperInternetBank();

    void insertBankCard(BankCard bankCard);
}
