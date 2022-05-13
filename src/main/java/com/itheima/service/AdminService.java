package com.itheima.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.domain.Adjudicator;
import com.itheima.domain.Admin;
import com.itheima.domain.Project;
import com.itheima.domain.Teacher;

public interface AdminService {
    Admin login(String adminName, String adminPassword);
    //保存员工信息
    void save(Project project);
    Boolean setSelectedProjectPass(Integer studentID);
    IPage<Teacher> selectByPage(int start, int size);//分页显示全部
    IPage<Teacher> searchByPage(int start, int size, String teacherName,Integer teacherId);//按cname分页查询

}
