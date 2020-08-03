package com.greeplant.GreenPlant.service;


import com.greeplant.GreenPlant.domin.User;

public interface UserService {
    //用户登录
    User login(User user);

    //用户注册
    void register(User user);
}
