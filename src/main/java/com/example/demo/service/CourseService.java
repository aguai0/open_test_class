package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.entity.Teacher;

import java.util.Iterator;
import java.util.List;

public interface CourseService {
    /** 查询单个*/
    Course queryById(long id);

    /** 查询所有 */
    List<Course> queryAll();

    /** 查询全部列表*/
    Iterator<Course> queryAll(int pageNum, int pageSize);

    /** 新增 */
    void save(Course course);

    /** 修改 */
    int update(Course course);

    /** 删除 */
    void delete(Long id);

    /** 根据条件查询 */
    Course queryByCondition(Long id, String cno);

    /** 查询所有课程及课程的订阅数量 */
    List<Course> queryOrderAll();
}
