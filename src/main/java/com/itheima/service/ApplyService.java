package com.itheima.service;

import com.itheima.domain.Apply;
import com.itheima.domain.Student;
import com.itheima.domain.Usertest;

import java.util.List;

public interface ApplyService {
    void save(Apply apply);//把返回值改了
//    void update(Usertest usertest);
    void update(Apply apply);
    void delete(Integer projectId);

    List<Apply> getById(Integer teacherId);

    Apply getById2(Integer studentId,String project_name,String teacher_name);

    Apply selectById3(Integer teacherId,Integer studentId);
    List<Apply> getAll();


}
