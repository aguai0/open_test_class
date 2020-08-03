package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="t_course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** 课程名称 */
    private String name;

    /** 课程编号 */
    private String cno;

    /** 课程订阅数量 */
    @Transient
    private Integer orderCount = 0;

    /** 课程状态 1-有效 0-无效 */
    private Integer status;
}
