package com.itheima.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.domain.Project;
import com.itheima.domain.Student;
import com.itheima.domain.Teacher;
import com.itheima.domain.Usertest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherService {
    int save(Teacher teacher);
    void update(Teacher teacher);
    void delete(Integer teacherId);
    Teacher getById(Integer teacherId);
    List<Teacher> getAll();
    Project searchProjectGroupbyTeacher(Integer teacherId);
    Teacher login(String teacherName, String teacherPassword);
    Integer changeProjectProgress(Integer studentId, String projectProgress);




//    void save(Usertest usertest);
//    void update(Usertest usertest);
//    void delete(Integer id);
//    Usertest getById(Integer id);
//    List<Usertest> getAll();
//    IPage<Usertest> getPage(int currentPage, int pageSize);
}
