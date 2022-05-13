package com.itheima.service.impl;

import com.itheima.dao.ApplyDao;
import com.itheima.domain.Apply;
import com.itheima.domain.Student;
import com.itheima.service.ApplyService;
import com.itheima.service.ex.UsernameDuplicateException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyServiceImpl implements ApplyService {
    @Autowired
    private ApplyDao applyDao;
    @Override
    public void save(Apply apply) {

       applyDao.insert(apply);
    }

    @Override
    public void update(Apply apply) {
           applyDao.updateById(apply);
    }

    @Override
    public void delete(Integer projectId) {

    }

    @Override
    public List<Apply> getById(Integer teacherId) {
//        select * from apply where teacher_id=1 and project_teach <>'';
//        查询Apply表指导内容为空的记录，展示出来作为教师待指导项目列表
        return applyDao.selectById(teacherId);
    }

    @Override
    public Apply getById2(Integer studentId,String project_name,String teacher_name) {
        return applyDao.selectById2(studentId,project_name,teacher_name);
    }

    @Override
    public Apply selectById3(Integer teacherId, Integer studentId) {
        return applyDao.selectById3(teacherId,studentId);
    }

    @Override
    public List<Apply> getAll() {
        return applyDao.selectList(null);
    }
}
