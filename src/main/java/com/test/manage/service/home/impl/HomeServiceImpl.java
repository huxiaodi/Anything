package com.test.manage.service.home.impl;

import com.test.manage.dao.generator.UserMapper;
import com.test.manage.model.generator.User;
import com.test.manage.model.generator.UserExample;
import com.test.manage.service.home.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HomeServiceImpl implements HomeService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUser(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void upload(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }


    @Override
    public void changeAgentMobile(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }


    @Override
    public List<User> getAllUser() {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIsDeleteEqualTo(false);
        return userMapper.selectByExample(userExample);
    }
}
