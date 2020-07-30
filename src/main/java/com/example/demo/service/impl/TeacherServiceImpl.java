package com.example.demo.service.impl;

import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository repository;

    @Override
    public Teacher queryById(long id) {

        Optional<Teacher> optional = repository.findById(id);
        return optional.get();
    }

    @Override
    public Iterator<Teacher> queryAll(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC,"id"));
        Page<Teacher> teachers = repository.findAll(pageable);
        return  teachers.iterator();
    }

    @Override
    public void save(Teacher teacher) {
        repository.save(teacher);
    }

    @Override
    public int update(Teacher teacher) {
        repository.save(teacher);
        return 1;
    }

    @Override
    public void delete(Teacher teacher) {
        repository.delete(teacher);
    }
}
