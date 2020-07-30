package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "t_subscription")
public class Subscription {

    private Long id;

    /** 课程id */
    private Long cid;

    /** 学生id */
    private Long sid;

}
