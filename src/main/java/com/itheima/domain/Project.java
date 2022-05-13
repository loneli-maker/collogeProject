package com.itheima.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Project {
    @TableId(value = "project_id")
    private Integer projectId;
    private String projectName;
    private String projectInfo;
    private Integer teacherId;
    private String projectPhoto;

//    private Teacher teacher;//加了这句话测试项目的增删改查会报错
}
