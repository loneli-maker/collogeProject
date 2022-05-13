package com.itheima.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.domain.Adjudicator;
import com.itheima.domain.Judge;
import com.itheima.domain.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdjuDao extends BaseMapper<Adjudicator> {

     Judge queryAllNeedGradeAdju(@Param("adjud") Integer adjuId);
     void addScore1(@Param("project_score") Integer projectScore,
                    @Param("student_id") Integer studentId,
                    @Param("project_id") Integer projectId,
                    @Param("adju_id") Integer adjuId);
     Integer addScore2( @Param("studentd") Integer studentId,@Param("new_total_score") Integer projectScore);//String has_scored_adju_number,String project_total_score
     //update apply set has_scored_adju_number=(has_scored_adju_number+1) where student_id=2;
     //update apply set project_total_score=(project_total_score+20) where student_id=2;

     Adjudicator selectOne(String adjudicatorName, String adjudicatorPassword);
}
