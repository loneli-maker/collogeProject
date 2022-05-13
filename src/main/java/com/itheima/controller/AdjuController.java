package com.itheima.controller;

import com.itheima.service.AdjuService;
import com.itheima.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AdjuController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private AdjuService adjuService;
    @RequestMapping("listneed")
    public String applylist(Model model, HttpSession session){
        Integer adjuId=Integer.valueOf(session.getAttribute("adjuId").toString());
        model.addAttribute("judge",adjuService.queryAllNeedGradeAdju(adjuId));
        if(adjuService.queryAllNeedGradeAdju(adjuId)!=null){
            model.addAttribute("msg", "以下是您需要评审的项目,请进行打分。");
        }
        return "adju/listneed";}

    @RequestMapping("score1")
    public String score(Model model, HttpSession session){
//        int adjuId=115;
//        adjuService.addScore(80, 2, 2, 115, 5);

        Integer adjuId=Integer.valueOf(session.getAttribute("adjuId").toString());
        model.addAttribute("adjuId",adjuId);
        model.addAttribute("judge",adjuService.queryAllNeedGradeAdju(adjuId));
        return "adju/score";}
    @RequestMapping("adjuDel/{adjudicatorId}")
    public String adjuDel(@PathVariable Integer adjudicatorId){
        adjuService.adjuDelById(adjudicatorId);
        return "system/adjuManager";
    }

    @RequestMapping("score2")
    public String score2(Model model,Integer projectScore,Integer studentId,Integer projectId,Integer projectTotalScore, HttpSession session){
        Integer adjuId=Integer.valueOf(session.getAttribute("adjuId").toString());
        adjuService.addScore(projectScore, studentId, projectId,adjuId);
        teacherService.changeProjectProgress(studentId,"已评分");
//        model.addAttribute("judge",adjuService.queryAllNeedGradeAdju(adjuId));
        return "redirect:listneed";}
}
