package com.example.demo.controller;

import com.example.demo.constant.LoginTypeEnum;
import com.example.demo.constant.UserSession;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String login(){
        //map.addAttribute("user", new User());
        return "login";
    }


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(String userName,String password,String loginType,ModelMap map) {

        User u = userService.login(userName, password);
        if (u == null) {
            map.put("information", "登陆失败,请检查用户名和密码");
            return "login";
        }
        if ((LoginTypeEnum.admin.name().equals(loginType) && !"admin".equalsIgnoreCase(userName)) ||
                (!LoginTypeEnum.admin.name().equals(loginType) && "admin".equalsIgnoreCase(userName))){
            map.put("information", "请选择Admin登录类型");
            return "login";
        }
        map.put("loginType", loginType);
        UserSession.LOGIN_INFO.set(userName);
        return "index";
    }
}
