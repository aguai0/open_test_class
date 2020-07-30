package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import com.example.demo.service.CourseService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @RequestMapping(method = RequestMethod.GET,value = "/{id}/select")
    public Course select(@PathVariable("id")long id){
        return courseService.queryById(id);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/selectAll/{pageNum}/{pageSize}")
    public List<Course> selectAll(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
        Iterator<Course> iterator = courseService.queryAll(pageNum, pageSize);
        List<Course> list = new ArrayList<>();
        while(iterator.hasNext()){
            list.add(iterator.next());
        }
        return list;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/delete/{id}")
    public void delete(@PathVariable("id")long id){
        courseService.delete(id);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/insert")
    public void insert(Course course){
        courseService.save(course);
    }
    @RequestMapping(method = RequestMethod.POST,value = "/update/{id}")
    public void update(@RequestParam Course course){
        courseService.update(course);
    }

}
