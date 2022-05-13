package com.itheima.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.dao.TeacherDao;
import com.itheima.domain.Project;
import com.itheima.domain.Teacher;
import com.itheima.domain.Usertest;
import com.itheima.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeacherServiceImpl implements TeacherService{
    @Autowired
    private TeacherDao teacherDao;
    @Override
    public int save(Teacher teacher) {
        return teacherDao.insert(teacher);
    }

    @Override
    public void update(Teacher teacher) {

    }

    @Override
    public void delete(Integer teacherId) {

    }

    @Override
    public Teacher getById(Integer teacherId) {
        return null;
    }

    @Override
    public List<Teacher> getAll() {
        return teacherDao.selectList(null);
    }

    @Override
    public Project searchProjectGroupbyTeacher(Integer teacherId) {
        return teacherDao.searchProjectGroupbyTeacher(teacherId);
    }

    @Override
    public Teacher login(String teacherName, String teacherPassword) {
        return teacherDao.selectOne(teacherName,teacherPassword);
    }

    @Override
    public Integer changeProjectProgress(Integer studentId, String projectProgress) {
        return teacherDao.changeProjectProgress(studentId,projectProgress);
    }


}
