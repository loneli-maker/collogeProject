package com.itheima.service;

import com.itheima.domain.Adjudicator;
import com.itheima.domain.Apply;
import com.itheima.domain.Judge;
import com.itheima.domain.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdjuService {
    void addScore( Integer projectScore,
                  Integer studentId,
                   Integer projectId,
                   Integer adjuId
                   );
    Judge queryAllNeedGradeAdju(Integer adjuId);
    void addScore1(Integer projectScore, Integer studentId,Integer projectId, Integer adjuId);
    Integer addScore2(Integer studentId,Integer projectTotalScore);

    Adjudicator login(String adjudicatorName, String adjudicatorPassword);
    List<Adjudicator> getAll();
    void adjuDelById(Integer adjuId);
    int save(Adjudicator adjudicator);
}
