package com.greeplant.GreenPlant.service.impl;

import com.greeplant.GreenPlant.dao.UserDao;
import com.greeplant.GreenPlant.entity.User;
import com.greeplant.GreenPlant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public User login(User user) {
        //1.根据用户输入用户名进行查询
        User userDB = userDao.findByUserName(user.getUSER_NAME());
        if(!ObjectUtils.isEmpty(userDB)){
            //2.比较密码
            if (userDB.getPASSWORD().equals(user.getPASSWORD())) {
                userDB.setLAST_LOGIN_TIME(new Date());
                return  userDB;
            }else{
                throw new RuntimeException("密码输入不正确!");
            }
        }else{
            throw  new  RuntimeException("用户名输入错误!");
        }
    }

    public void register(User user) {
        //根据用户输入用户名判断用户是否存在
        User userDB = userDao.findByUserName(user.getUSER_NAME());
        if(userDB==null){
            //设置用户注册时间
            user.setCREATETIME(new Date());
            //调用DAO
            userDao.save(user);
        }else{
            throw new RuntimeException("用户名已存在!");
        }
    }
}
