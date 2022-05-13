package com.itheima.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Apply {
//    @TableId(value = "student_id")//这行注释掉studentId在申报项目是就是插入数据
//    @TableId(value = "studentId")//这行注释掉studentId在申报项目是就是插入数据
    private Integer studentId;
    private Integer projectId;
//    @TableId(value = "teacher_id")
    private Integer teacherId;
    private String studentName;
    private String projectName;
    private String teacherName;
    private String projectContent;//申报内容
    private String projectProgress;//申报进度
    private String projectTeach;//指导内容
    private String passStatus; //通过状态
    private Integer projectTotalScore;
    private String hasScoredAdjuNumber;
    private String adjuNumber;
}
