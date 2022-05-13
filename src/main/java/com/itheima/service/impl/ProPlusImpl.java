package com.itheima.service.impl;

import com.itheima.dao.ProPlusDao;
import com.itheima.domain.Project;
import com.itheima.service.ProPlusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProPlusImpl implements ProPlusService {
    @Autowired
    private ProPlusDao proPlusDao;
    @Override
    public List<Project> selectAllApplyProjects( int currPage, int pageSize) {
        //查询全部数据
        List<Project> projectPlus = proPlusDao.selectAllApplyProjects();
        //从第几条数据开始
        int firstIndex = (currPage - 1) * pageSize;
        //到第几条数据结束
        int lastIndex = currPage * pageSize;
        return projectPlus.subList(firstIndex, lastIndex); //直接在list中截取
    }
}
