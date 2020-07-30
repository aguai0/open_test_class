package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public String login(String userName,String password,Integer loginType){

        User user = userService.login(userName,password);
       if (user != null){
           if (userName.equals("Admin") && password.equals("Admin")){
               return "/userManage";
           }else{
               if ("1".equals(loginType)){
                   return "/studentHome";
               }else{
                   return "/teacherHome";
               }
           }
       }
        return "fail";
    }
}
