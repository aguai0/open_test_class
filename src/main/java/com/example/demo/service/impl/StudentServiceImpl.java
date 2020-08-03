package com.example.demo.service.impl;

import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository repository;

    @Override
    public Student queryById(Long id) {
        Optional<Student> optional = repository.findById(id);
        Student student = optional.get();
        return student;
    }

    @Override
    public Student queryByCondition(Long id,String sno) {
        if (id!=null && StringUtils.hasText(sno)){
            return repository.queryStudentByIdNotAndSnoEquals(id,sno);
        }
        return repository.queryStudentBySno(sno);
    }

    @Override
    public List<Student> queryAll() {
        return repository.findAll();
    }

    @Override
    public Iterator<Student> queryAll(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC,"id"));
        Page<Student> students = repository.findAll(pageable);
        return  students.iterator();
    }

    @Override
    public void save(Student student) {
        repository.save(student);
    }

    @Override
    public int update(Student student) {
        repository.save(student);
        return 1;
    }

    @Override
    public void delete(long id) {
        Student student = repository.findById(id).get();
        repository.delete(student);
    }

    @Override
    public Student queryBySno(String sno) {
        return repository.queryStudentBySno(sno);
    }
}
