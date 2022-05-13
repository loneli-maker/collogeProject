package com.itheima.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.domain.Project;
import com.itheima.domain.Student;
import com.itheima.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentDao extends BaseMapper<Student> {
//    List<Project> selectAllApplyProjects();
    Student selectOne(String studentName,String studentPassword);
    Integer updatePasswordByUid( Integer studentId,String studentPassword);


    Integer updateAvatarByUid(
            Integer studentId,
             String studentAvatar);

    Student selectByStudentname(String studentName);

}
