package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.entity.Course;
import com.example.demo.service.CourseService;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    private static final String COURSE_FORM_PATH_NAME = "courseForm";
    private static final String COURSE_LIST_PATH_NAME = "courseList";
    private static final String REDIRECT_TO_COURSE_URL = "redirect:/course";

    @Autowired
    CourseService courseService;

    /**
     * 获取 Course 列表
     * 处理 "/course" 的 GET 请求，用来获取 Course 列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getCourseList(ModelMap map) {
        map.addAttribute("courseList",courseService.queryAll());
        map.put("loginType", "teacher");
        return COURSE_LIST_PATH_NAME;
    }

    /**
     * 获取创建 Course 表单
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createCourseForm(ModelMap map) {
        map.addAttribute("course", new Course());
        map.addAttribute("action", "create");
        return COURSE_FORM_PATH_NAME;
    }

    /**
     * 创建 Course
     * 处理 "/course/create" 的 POST 请求，用来新建 Course 信息
     * 通过 @ModelAttribute 绑定表单实体参数，也通过 @RequestParam 传递参数
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String postCourse(@ModelAttribute Course course, ModelMap map) {
        Course s = courseService.queryByCondition(course.getId(),course.getCno());

        if (s != null){
            FieldError fieldError = new FieldError(s.getCno(),"编号已经存在","编号已经存在");
            map.addAttribute("error", fieldError);
            return REDIRECT_TO_COURSE_URL;
        }
        courseService.save(course);
        return REDIRECT_TO_COURSE_URL;
    }

    /**
     * 获取更新 Course 表单
     *    处理 "/course/update/{id}" 的 GET 请求，通过 URL 中的 id 值获取 Course 信息
     *    URL 中的 id ，通过 @PathVariable 绑定参数
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable Long id, ModelMap map) {
        map.addAttribute("course", courseService.queryById(id));
        map.addAttribute("action", "update");
        return COURSE_FORM_PATH_NAME;
    }

    /**
     * 更新 Course
     * 处理 "/update" 的 PUT 请求，用来更新 Course 信息
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String putCourse(@ModelAttribute Course course, ModelMap map) {

        Course s = courseService.queryByCondition(course.getId(),course.getCno());

        if (s != null){
            FieldError fieldError = new FieldError(s.getCno(),"编号已经存在","编号已经存在");
            map.addAttribute("error", fieldError);
            return REDIRECT_TO_COURSE_URL;
        }
        courseService.update(course);
        return REDIRECT_TO_COURSE_URL;
    }

    /**
     * 删除 Course
     * 处理 "/course/{id}" 的 GET 请求，用来删除 Course 信息
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteCourse(@PathVariable Long id) {
        courseService.delete(id);
        return REDIRECT_TO_COURSE_URL;
    }

}
