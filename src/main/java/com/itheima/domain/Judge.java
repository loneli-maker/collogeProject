package com.itheima.domain;

import lombok.Data;

@Data
public class Judge {
    private Integer studentId;
    private Integer projectId;
    private Integer adjuId;
    private Integer projectScore;
    private Apply apply;
//    private Project project;
}
