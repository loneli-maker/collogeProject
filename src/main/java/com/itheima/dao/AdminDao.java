package com.itheima.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.domain.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminDao extends BaseMapper<Admin> {
    Admin selectOne(String adminName, String adminPassword);
    //保存员工信息
    void save(Project project);
    Integer setAdju(Judge judge);

    Boolean setSelectedProjectPass(Integer studentID);
    List<Apply> passAllApply(String passStatus);

}
