package com.itheima.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ProPlus {
    @TableId(value = "projectId")
    private Integer projectId;
    private String projectName;
    private String projectPhoto;
    private String projectInfo;
    private Integer teacherId;
    private String teacherName;
    private Teacher teacher;
}
