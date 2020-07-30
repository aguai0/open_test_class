package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.entity.User;

import java.util.Iterator;

public interface StudentService {

    /** 查询单个*/
    Student queryById(long id);

    /** 查询全部列表*/
    Iterator<Student> queryAll(int pageNum, int pageSize);

    /** 新增 */
    void save(Student student);

    /** 修改 */
    int update(Student student);

    /** 删除 */
    void delete(long id);
}
