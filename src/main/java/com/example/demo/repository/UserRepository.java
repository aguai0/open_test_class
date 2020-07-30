package com.example.demo.repository;

import com.example.demo.entity.Course;
import com.example.demo.entity.Teacher;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

   @Query("from User u where u.userName=:username and u.password=:password")
   User login(@Param("username") String username,@Param("password") String password);
}
