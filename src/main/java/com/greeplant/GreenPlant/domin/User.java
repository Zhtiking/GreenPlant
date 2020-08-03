package com.greeplant.GreenPlant.domin;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class User {
    private Integer userId;
    private Date createtime;
    private String userName;
    private String password;
    private String openId;
    private String phoneNum;
    private Date   lastLoginTime;
    private String weixinNickname;
    private String field1;
    private String field2;
    private String field3;
    private String field4;
    private String field5;
    private String sex;
    private String province;
    private String city;
    private String country;
    private String headimgurl;
    private String nickName;



}

