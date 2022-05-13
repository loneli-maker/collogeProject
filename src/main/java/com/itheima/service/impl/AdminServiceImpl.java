package com.itheima.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.dao.AdminDao;
import com.itheima.dao.TeacherDao;
import com.itheima.domain.Admin;
import com.itheima.domain.Project;
import com.itheima.domain.Teacher;
import com.itheima.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private TeacherDao teacherDao;
    @Override
    public Admin login(String adminName, String adminPassword) {
        return adminDao.selectOne(adminName,adminPassword);
    }

    @Override
    public void save(Project project) {
        adminDao.save(project);
    }

    @Override
    public Boolean setSelectedProjectPass(Integer studentID) {
        return adminDao.setSelectedProjectPass(studentID);
    }

    @Override
    public IPage<Teacher> selectByPage(int start, int size) {
        Page<Teacher> page=new Page<>(start,size);
        teacherDao.selectPage(page, null);
        return page;
    }

    @Override
    public IPage<Teacher> searchByPage(int start, int size, String teacherName,Integer teacherId) {
        Page<Teacher> page=new Page<>(start,size);
        teacherDao.selectPage(page, new QueryWrapper<Teacher>().like("teacher_name", teacherName).or().eq("teacher_id",teacherId));
        return page;
    }
}
