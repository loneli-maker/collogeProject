package com.itheima.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.domain.Judge;
import com.itheima.domain.ProPlus;
import com.itheima.domain.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProPlusDao extends BaseMapper<ProPlus> {
    List<Project> selectAllApplyProjects(Integer project_id,String project_name,String teacher_name);
    List<Project> selectAllApplyProjects();
//    List<Project> selectAllApplyProjects(Integer project_id, String project_name, String teacher_name, Map<String,Object> data);
//     Project selectOneApplyProjects(@Param("projectd") Integer projectId);

}
