package com.example.demo.entity;

import lombok.Data;

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
}
