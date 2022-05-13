package com.itheima.controller;

import com.itheima.dao.ApplyDao;
import com.itheima.dao.TeacherDao;
import com.itheima.domain.Apply;
import com.itheima.domain.Project;
import com.itheima.service.ApplyService;
import com.itheima.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TeacherController {
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private ApplyDao applyDao;
@Autowired
private ApplyService applyService;
    @Autowired
    private TeacherService teacherService;

    //显示所有已需要指导项目页面
//    @RequestMapping("teaprojectlist")
//    public String selectProjectByTid(Model model, HttpSession session){
//        Integer Tid=Integer.valueOf(session.getAttribute("teacherid").toString());
//        Project project=teacherService.searchProjectGroupbyTeacher(Tid);
//        model.addAttribute("project",project);
//        return "teacher/index";}
//示所有已需要指导项目页面
    @RequestMapping("queryAllneedguideApply")
    public String queryAllneedguideApply(Model model,HttpSession session){
        Integer Tid=Integer.valueOf(session.getAttribute("teacherid").toString());
//        List<Apply> apply = applyService.getById(Tid);
        List<Apply> apply = teacherDao.selectByPTeacher(Tid);
        if(teacherDao.selectByPTeacher(Tid)!=null){model.addAttribute("msg", "以下是您需要指导的项目。");
        }
        model.addAttribute("apply",apply);
            return "teacher/guidelist";
        }
        //进入指导页面
    @RequestMapping("guideedit2")
    public String guideedit2(Model model,HttpSession session,Integer teacherId,Integer studentId){
//        Integer Tid=Integer.valueOf(session.getAttribute("teacherid").toString());
        Apply apply = applyService.selectById3(teacherId,studentId);
        model.addAttribute("apply",apply);
        return "teacher/teach";
    }
    //提交指导内容
//    @Override
//    public boolean addProjectTeach(String studentID,String projectTeach) {
//        if (prepareMySql("UPDATE apply SET project_teach=\'" + projectTeach + "\' WHERE student_id=\'" + studentID + "\'")) {
//
//    @ResponseBody
    @RequestMapping("teacherTeach")
    public String teacherTeach(Integer studentId,String projectTeach) {
//        int studentId = 26;
//        if (apply.getStudentId() != null && apply.getStudentId() > 0) {
        applyDao.updateteach(studentId,projectTeach);
        teacherService.changeProjectProgress(studentId,"已指导，");
        return "redirect:/queryAllneedguideApply";
    }

}
