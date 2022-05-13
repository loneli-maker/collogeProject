package com.itheima.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.domain.Apply;
import com.itheima.domain.Project;
import com.itheima.domain.Student;
import com.itheima.domain.Teacher;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeacherDao extends BaseMapper<Teacher> {
      Project searchProjectGroupbyTeacher(Integer teacherId);
      Teacher selectOne(String teacherName, String teacherPassword);
      Integer changeProjectProgress(Integer studentId,@Param("new_projectProgress") String projectProgress);
      List<Apply> selectByPTeacher(Integer teacherId);

}
