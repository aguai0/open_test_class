package com.example.demo.repository;

import com.example.demo.entity.Student;
import com.example.demo.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher queryTeacherByTno(String tno);

    Teacher queryTeacherByIdNotAndTnoEquals(Long id, String tno);
}
