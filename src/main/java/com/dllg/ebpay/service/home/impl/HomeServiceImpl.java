package com.dllg.ebpay.service.home.impl;

import com.dllg.framework.exception.AnyException;
import com.dllg.pabank.model.request.Params;
import com.dllg.ebpay.dao.generator.BankCardMapper;
import com.dllg.ebpay.dao.generator.UserMapper;
import com.dllg.ebpay.model.generator.BankCard;
import com.dllg.ebpay.model.generator.BankCardExample;
import com.dllg.ebpay.model.generator.User;
import com.dllg.ebpay.model.generator.UserExample;
import com.dllg.ebpay.service.home.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HomeServiceImpl implements HomeService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BankCardMapper bankCardMapper;

    @Override
    public User selectUser(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void upload(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public List<BankCard> selectBankCard(String userId) {
        BankCardExample bankCardExample = new BankCardExample();
//        bankCardExample.createCriteria().andBankCardAccountIdEqualTo(userId);
        return bankCardMapper.selectByExample(bankCardExample);
    }

    @Override
    public void changeAgentMobile(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public Boolean changePayPassword(Params params) {
        User user = userMapper.selectByPrimaryKey(params.getUserId());
        if (!user.getUserPayPassword().equals(params.getOldPayPassword())) {
            throw new AnyException("原密码错误");
        } else {
            user.setUserPayPassword(params.getNewPayPassword());
            userMapper.updateByPrimaryKeySelective(user);
            return true;
        }
    }

    @Override
    public List<User> getAllUser() {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIsDeleteEqualTo(false);
        return userMapper.selectByExample(userExample);
    }
}
