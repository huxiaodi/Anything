package com.test.manage.service.home;

import com.test.manage.model.generator.User;

import java.util.List;

public interface HomeService {

    User selectUser(String userId);

    void upload(User user);


    void changeAgentMobile(User user);


    List<User> getAllUser();
}
