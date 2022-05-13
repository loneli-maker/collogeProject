package com.itheima.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.domain.Project;
import com.itheima.domain.Teacher;

import java.util.List;

public interface ProjectService {
    void save(Project project);
    void update(Project project);
    void delete(Integer projectId);
    Project getById(Integer projectId);

//    List<Project> getAll();
    IPage<Project> selectByPage(int start, int size);//分页显示全部

//    List<Project> getAllmyProject(Integer teacherId);
    IPage<Project> getAllmyProject(Integer teacherId,int start, int size);
}
