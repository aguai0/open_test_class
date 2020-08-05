package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.entity.Sc;
import com.example.demo.entity.ScResult;
import com.example.demo.entity.Student;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.ScRepository;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository repository;

    @Autowired
    ScRepository scRepository;

    @Override
    public Course queryById(long id) {
        Optional<Course> optional = repository.findById(id);
        return  optional.get();
    }

    @Override
    public List<Course> queryAll() {
        return repository.findAll();
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
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        Course course = repository.findById(id).get();
        repository.delete(course);

        List<Sc> scList = scRepository.queryByCidEquals(id);
        if (!scList.isEmpty()){
            for (Sc sc: scList) {
                scRepository.delete(sc);
            }
        }
    }

    @Override
    public Course queryByCondition(Long id, String cno) {
        if (id!=null && StringUtils.hasText(cno)){
            return repository.queryCourseByIdNotAndCnoEquals(id,cno);
        }
        return repository.queryCourseByCno(cno);
    }

    @Override
    public List<Course> queryOrderAll() {
        List<Course> courseList = repository.findAll();
        List<ScResult> scList = scRepository.countSc();
        for (Course c: courseList) {
            for (ScResult sc: scList) {
                if (c.getId().equals(sc.getCid())){
                    c.setOrderCount(sc.getCountSid().intValue());
                }
            }
        }
       return courseList;
    }
}
