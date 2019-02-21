package com.dllg.ebpay.service.home;

import com.dllg.pabank.model.request.Params;
import com.dllg.ebpay.model.generator.BankCard;
import com.dllg.ebpay.model.generator.User;

import java.util.List;

/**
 *
 */
public interface HomeService {

    User selectUser(String userId);

    void upload(User user);

    List<BankCard> selectBankCard(String userId);

    void changeAgentMobile(User user);

    Boolean changePayPassword(Params params);

    List<User> getAllUser();
}
