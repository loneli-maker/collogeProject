package com.itheima.service.impl;

import com.itheima.dao.AdjuDao;
import com.itheima.domain.Adjudicator;
import com.itheima.domain.Judge;
import com.itheima.service.AdjuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdjuServiceImpl implements AdjuService {
    @Autowired
    private AdjuDao adjuDao;
    @Override
    public Judge queryAllNeedGradeAdju(Integer adjuId) {
        return adjuDao.queryAllNeedGradeAdju(adjuId);
    }

    @Override
    public void addScore1(Integer projectScore, Integer studentId, Integer projectId, Integer adjuId) {
        adjuDao.addScore1(projectScore,studentId,projectId,adjuId);
    }

    @Override
    public Integer addScore2(Integer studentId,Integer projectTotalScore) {
        return adjuDao.addScore2(studentId,projectTotalScore);
    }

    @Override
    public Adjudicator login(String adjudicatorName, String adjudicatorPassword) {
        return adjuDao.selectOne(adjudicatorName,adjudicatorPassword);
    }

    @Override
    public List<Adjudicator> getAll() {
        return adjuDao.selectList(null);
    }

    @Override
    public void adjuDelById(Integer adjuId) {
        adjuDao.deleteById(adjuId);
    }

    @Override
    public int save(Adjudicator adjudicator) {
        return adjuDao.insert(adjudicator);
    }

    @Override
    public void addScore(Integer projectScore, Integer studentId, Integer projectId, Integer adjuId){
        adjuDao.addScore1(projectScore,studentId,projectId,adjuId);
        adjuDao.addScore2(studentId,projectScore);
    }
}
