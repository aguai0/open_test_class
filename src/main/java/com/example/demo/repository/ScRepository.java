package com.example.demo.repository;

import com.example.demo.entity.Sc;
import com.example.demo.entity.ScResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScRepository extends JpaRepository<Sc, Long> {

    @Query("select new com.example.demo.entity.ScResult(s.cid,count(s.sid)) from Sc s group by s.cid")
    List<ScResult> countSc();
}
