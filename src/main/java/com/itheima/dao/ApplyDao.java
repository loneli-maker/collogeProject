package com.itheima.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.domain.Apply;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface ApplyDao extends BaseMapper<Apply> {
    List<Apply> selectById(Integer teacherId);
    Apply selectById2(Integer student_id, String project_name, String teacher_name);
    Apply selectById3(Integer teacherId,Integer studentId);
    void updateteach(Integer studentId,String projectTeach);
}
