package com.itheima.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.dao.ProjectDao;
import com.itheima.domain.Project;
import com.itheima.domain.Teacher;
import com.itheima.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectDao projectDao;
    @Override
    public void save(Project project) {
      projectDao.insert(project);
    }

    @Override
    public void update(Project project) {
        projectDao.updateById(project);
    }

    @Override
    public void delete(Integer projectId) {
       projectDao.deleteById(projectId);
    }

    @Override
    public Project getById(Integer projectId) {
        return projectDao.selectById(projectId);
    }

    @Override
    public IPage<Project> selectByPage(int start, int size) {
        Page<Project> page=new Page<>(start,size);
        projectDao.selectPage(page, null);
        return page;
    }

//    @Override
//    public List<Project> getAll() {
//        return projectDao.selectList(null);
//    }
    @Override
    public IPage<Project> getAllmyProject(Integer teacherId,int start, int size) {
//        QueryWrapper<Project> wrapper = new QueryWrapper<>();
//        //eq() 等于
//        wrapper.eq("teacher_id",teacherId);
//        return projectDao.selectList(wrapper);

        QueryWrapper<Project> wrapper = new QueryWrapper<>();
        //eq() 等于
        wrapper.eq("teacher_id",teacherId);
        Page<Project> page=new Page<>(start,size);
        projectDao.selectPage(page, wrapper);
        return page;
    }
}
