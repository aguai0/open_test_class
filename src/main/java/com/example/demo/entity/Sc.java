package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_sc")
public class Sc {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** 课程id */
    private Long cid;

    /** 学生id */
    private Long sid;

    public Sc() {
    }

    public Sc(Long cid, Long sid) {
        this.cid = cid;
        this.sid = sid;
    }
}
