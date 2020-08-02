package com.example.demo.entity;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ScResult {

    public ScResult(Long cid, Long countSid) {
        this.cid = cid;
        this.countSid = countSid;
    }

    /** 课程id */
    private Long cid;

    /** 订阅数量 */
    private Long countSid;
}
