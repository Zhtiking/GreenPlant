package com.greeplant.GreenPlant.dao;


import com.greeplant.GreenPlant.entity.User;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * Created by lenovo on 2020/7/31.
 */
@Mapper
@Component(value = "UserDaoMapper")
public interface UserDao {
    void save(User user);
    User findByUserName(String username);
}
