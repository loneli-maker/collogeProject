package com.itheima.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "teacher")
public class Teacher {
    @TableId(value = "teacher_id")//加这行才能保证id能查到
    private Integer teacherId;
    private String teacherPassword;
    private String teacherName;
}
