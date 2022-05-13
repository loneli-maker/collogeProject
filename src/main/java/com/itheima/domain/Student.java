package com.itheima.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Student {
    @TableId(value = "student_id")
    private Integer studentId;
    private String studentPassword;
    private String studentName;
    private Integer studentSex;
    private String studentEmail;
    private String studentPhonenumber;
    private String studentAvatar;
}
