package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
    User login(String userName, String password);
}
