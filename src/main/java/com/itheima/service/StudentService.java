package com.itheima.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.domain.ProPlus;
import com.itheima.domain.Project;
import com.itheima.domain.Student;
import com.itheima.domain.Teacher;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface StudentService {
    int reg(Student student);
    void update(Student student);
    void delete(Integer studentId);
    Student getById(Integer studentId);
    List<Student> getAll();
//    List<Project> selectAllApplyProjects();
    Student login(String studentName,String studentPassword);
     void changePassword(Integer studentId, String oldPassword, String newPassword);
//    IPage<ProPlus> selectAllApplyProjects(int start, int size);//分页显示全部

    /**
     * 修改用户头像
     * @param uid 当前登录的用户的id
     * @param username 当前登录的用户名
     * @param avatar 用户的新头像的路径
     */
    void changeAvatar(Integer studengId, String studentAvatar);

}

