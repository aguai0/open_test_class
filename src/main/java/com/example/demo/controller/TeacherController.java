package com.example.demo.controller;

import com.example.demo.entity.Teacher;
import com.example.demo.service.TeacherService;
import com.example.demo.service.ScService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teacher")
public class TeacherController {


    private static final String STUDENT_FORM_PATH_NAME = "teacherForm";
    private static final String STUDENT_LIST_PATH_NAME = "teacherList";
    private static final String REDIRECT_TO_STUDENT_URL = "redirect:/teacher";

    @Autowired
    TeacherService teacherService;
    @Autowired
    ScService scService;

    /**
     * 获取 Teacher 列表
     * 处理 "/teacher" 的 GET 请求，用来获取 Teacher 列表
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getTeacherList(ModelMap map) {
        map.addAttribute("teacherList",teacherService.queryAll());
        map.put("loginType", "admin");
        return STUDENT_LIST_PATH_NAME;
    }

    /**
     * 获取创建 Teacher 表单
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createTeacherForm(ModelMap map) {
        map.addAttribute("teacher", new Teacher());
        map.addAttribute("action", "create");
        return STUDENT_FORM_PATH_NAME;
    }

    /**
     * 创建 Teacher
     * 处理 "/teacher/create" 的 POST 请求，用来新建 Teacher 信息
     * 通过 @ModelAttribute 绑定表单实体参数，也通过 @RequestParam 传递参数
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String postTeacher(@ModelAttribute Teacher teacher, ModelMap map) {
        Teacher s = teacherService.queryByCondition(teacher.getId(),teacher.getTno());

        if (s != null){
            FieldError fieldError = new FieldError(s.getTno(),"编号已经存在","编号已经存在");
            map.addAttribute("error", fieldError);
            return REDIRECT_TO_STUDENT_URL;
        }
        teacherService.save(teacher);
        return REDIRECT_TO_STUDENT_URL;
    }

    /**
     * 获取更新 Teacher 表单
     *    处理 "/teacher/update/{id}" 的 GET 请求，通过 URL 中的 id 值获取 Teacher 信息
     *    URL 中的 id ，通过 @PathVariable 绑定参数
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable Long id, ModelMap map) {
        map.addAttribute("teacher", teacherService.queryById(id));
        map.addAttribute("action", "update");
        return STUDENT_FORM_PATH_NAME;
    }

    /**
     * 更新 Teacher
     * 处理 "/update" 的 PUT 请求，用来更新 Teacher 信息
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String putTeacher(@ModelAttribute Teacher teacher, ModelMap map) {

        Teacher s = teacherService.queryByCondition(teacher.getId(),teacher.getTno());

        if (s != null){
            FieldError fieldError = new FieldError(s.getTno(),"编号已经存在","编号已经存在");
            map.addAttribute("error", fieldError);
            return REDIRECT_TO_STUDENT_URL;
        }
        teacherService.update(teacher);
        return REDIRECT_TO_STUDENT_URL;
    }

    /**
     * 删除 Teacher
     * 处理 "/teacher/{id}" 的 GET 请求，用来删除 Teacher 信息
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteTeacher(@PathVariable Long id) {
        teacherService.delete(id);
        return REDIRECT_TO_STUDENT_URL;
    }

}
