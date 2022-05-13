package com.itheima.service;

import com.itheima.domain.Project;

import java.util.List;

public interface ProPlusService {
//    List<Student> queryStudentsByArray(int currPage, int pageSize);
    List<Project> selectAllApplyProjects(int currPage, int pageSize);
}
