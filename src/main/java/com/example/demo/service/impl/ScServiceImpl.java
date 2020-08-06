package com.example.demo.service.impl;

import com.example.demo.constant.LoginTypeEnum;
import com.example.demo.constant.UserSession;
import com.example.demo.entity.Course;
import com.example.demo.entity.Sc;
import com.example.demo.entity.Student;
import com.example.demo.repository.ScRepository;
import com.example.demo.service.ScService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.krb5.SCDynamicStoreConfig;

import java.util.List;

@Service
public class ScServiceImpl implements ScService {

    @Autowired
    StudentService studentService;

    @Autowired
    ScRepository scRepository;

    @Override
    public String order(Long cid) {
        String sno = UserSession.LOGIN_INFO.get(LoginTypeEnum.student.name());

        Student student = studentService.queryBySno(sno);
        if (student == null) {
            return "fail";
        }
        scRepository.save(new Sc(cid, student.getId()));

        return "success";
    }

    @Override
    public void cancel(Long cid) {
        List<Sc> scs = scRepository.queryByCidEquals(cid);
        for (Sc sc : scs) {
            scRepository.delete(sc);
        }
    }
}
