package com.greeplant.GreenPlant.controller;

import com.greeplant.GreenPlant.entity.User;
import com.greeplant.GreenPlant.service.UserService;
import com.greeplant.GreenPlant.util.Result;
import com.greeplant.GreenPlant.util.VerifyCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by lenovo on 2020/8/1.
 */
@RestController
@CrossOrigin
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

//    @Autowired
//    @Value("${photo.dir}")
//    private String realPath;

    /**
     * 用户登录
     */
    @PostMapping("login")
    public Result login( User user){
        log.info("当前登录用户的信息: [{}]",user.toString());
        try {
            User userDB = userService.login(user);
            return new Result(userDB,null,"登录成功",1);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(null,null,"登录失败",0);
        }
    }

    /**
     * 生成验证码图片
     */
    @GetMapping("getImage")
    public String getImageCode(HttpServletRequest request) throws IOException {
        //1.使用工具类生成验证码
        String code = VerifyCodeUtils.generateVerifyCode(4);
        //2.将验证码放入servletContext作用域
        request.getServletContext().setAttribute("code", code);
        //3.将图片转为base64
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(120, 30, byteArrayOutputStream, code);
        return "data:image/png;base64," + Base64Utils.encodeToString(byteArrayOutputStream.toByteArray());
    }

    /**
     * 用来处理用户注册方法
     */
    @PostMapping("register")
    public Result register( User user, String code,
                           HttpServletRequest request) {// MultipartFile photo
        log.info("用户信息:[{}]",user.toString());
        log.info("用户输入的验证码信息:[{}]",code);
//        log.info("用户输入的头像信息:[{}]",photo.getOriginalFilename());
        try {
            //头像
//            String newFileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(photo.getOriginalFilename());
//            photo.transferTo(new File(realPath, newFileName));
//            //设置头像地址
//            user.setHEADIMGURL(newFileName);
            //验证码
            String key = (String) request.getServletContext().getAttribute("code");
//            if (key.equalsIgnoreCase(code)) {
//                log.info("生成的验证码信息:[{}]",key);
                userService.register(user);
                return new Result(null,null,"注册成功!",1);
//            } else {
//                throw new RuntimeException("验证码出现错误!");
//            }

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(null,null,"注册失败!",0);
        }
    }
}

