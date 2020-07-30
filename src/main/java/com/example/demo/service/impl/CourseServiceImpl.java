package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository repository;

    @Override
    public Course queryById(long id) {
        Optional<Course> optional = repository.findById(id);
        return  optional.get();
    }

    @Override
    public Iterator<Course> queryAll(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by(Sort.Direction.DESC,"id"));
        Page<Course> courses = repository.findAll(pageable);
        return  courses.iterator();
    }

    @Override
    public void save(Course course) {
        repository.save(course);
    }

    @Override
    public int update(Course course) {
        repository.save(course);
        return 1;
    }

    @Override
    public void delete(Course course) {
        repository.delete(course);
    }
}
