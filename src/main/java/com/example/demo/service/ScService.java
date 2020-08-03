package com.example.demo.service;

import com.example.demo.entity.Sc;
import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;

public interface ScService {

    /** 订阅 */
    String order(Long cid);

    /** 取消订阅 */
    void cancel(Long cid);
}
