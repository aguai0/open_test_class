package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
import com.example.demo.service.StudentService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    private static final String STUDENT_FORM_PATH_NAME = "studentForm";
    private static final String STUDENT_LIST_PATH_NAME = "studentList";
    private static final String REDIRECT_TO_STUDENT_URL = "redirect:/student";

    @Autowired
    StudentService studentService;

    /**
     * 获取 Student 列表
     * 处理 "/student" 的 GET 请求，用来获取 Student 列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getStudentList(ModelMap map) {
        map.addAttribute("studentList",studentService.queryAll());
        map.put("loginType", "admin");
        return STUDENT_LIST_PATH_NAME;
    }

    /**
     * 获取创建 Student 表单
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createStudentForm(ModelMap map) {
        map.addAttribute("student", new Student());
        map.addAttribute("action", "create");
        return STUDENT_FORM_PATH_NAME;
    }

    /**
     * 创建 Student
     * 处理 "/student/create" 的 POST 请求，用来新建 Student 信息
     * 通过 @ModelAttribute 绑定表单实体参数，也通过 @RequestParam 传递参数
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String postStudent(@ModelAttribute Student student, ModelMap map) {
        Student s = studentService.queryByCondition(student.getId(),student.getSno());

        if (s != null){
            FieldError fieldError = new FieldError(s.getSno(),"编号已经存在","编号已经存在");
            map.addAttribute("error", fieldError);
            return REDIRECT_TO_STUDENT_URL;
        }
        studentService.save(student);
        return REDIRECT_TO_STUDENT_URL;
    }

    /**
     * 获取更新 Student 表单
     *    处理 "/student/update/{id}" 的 GET 请求，通过 URL 中的 id 值获取 Student 信息
     *    URL 中的 id ，通过 @PathVariable 绑定参数
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable Long id, ModelMap map) {
        map.addAttribute("student", studentService.queryById(id));
        map.addAttribute("action", "update");
        return STUDENT_FORM_PATH_NAME;
    }

    /**
     * 更新 Student
     * 处理 "/update" 的 PUT 请求，用来更新 Student 信息
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String putStudent(@ModelAttribute Student student, ModelMap map) {

        Student s = studentService.queryByCondition(student.getId(),student.getSno());

        if (s != null){
            FieldError fieldError = new FieldError(s.getSno(),"编号已经存在","编号已经存在");
            map.addAttribute("error", fieldError);
            return REDIRECT_TO_STUDENT_URL;
        }
        studentService.update(student);
        return REDIRECT_TO_STUDENT_URL;
    }

    /**
     * 删除 Student
     * 处理 "/student/{id}" 的 GET 请求，用来删除 Student 信息
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteStudent(@PathVariable Long id) {
        studentService.delete(id);
        return REDIRECT_TO_STUDENT_URL;
    }

}
