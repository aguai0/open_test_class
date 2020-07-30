package com.example.demo.service;

import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;

import java.util.Iterator;

public interface TeacherService {

    /** 查询单个*/
    Teacher queryById(long id);

    /** 查询全部列表*/
    Iterator<Teacher> queryAll(int pageNum, int pageSize);

    /** 新增 */
    void save(Teacher teacher);

    /** 修改 */
    int update(Teacher teacher);

    /** 删除 */
    void delete(Teacher teacher);
}
