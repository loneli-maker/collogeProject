package com.itheima;

import com.itheima.dao.ProPlusDao;
import com.itheima.dao.TeacherDao;
import com.itheima.domain.Apply;
import com.itheima.domain.Teacher;
import com.itheima.domain.User;
import com.itheima.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ServiceTest {
    @Autowired
    private ProPlusService proPlusService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private AdjuService adjuService;
    @Autowired
    private UsertestService usertestService;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private ApplyService applyService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private AdminService adminService;
    @Test
    void selectAllApplyProjects(){
        Apply apply = new Apply();
        apply.setProjectId(3);
        apply.setStudentId(2);
        apply.setTeacherId(1007);
        apply.setProjectContent("sasas");

//        applyService.save(a);
//        usertestService.getAll();
    }
    @Test
    void selectAllApplyProjects4(){
//        adjuService.addScore(50,123,1,45,45);
//            teacherService.changeProjectProgress(2,"易容过，");
//        adjuService.queryAllNeedGradeAdju(115);
//        adminService.setSelectedProjectPass(2);
//        adjuService.login("45","45");
     proPlusService.selectAllApplyProjects(1,1);

    }

    public static void main(String[] args) {
        ArrayList<String> objects = new ArrayList<>();
        objects.add("来打打1");
        objects.add("来打打2");
        objects.add("来打打3");
        objects.add("来打打4");
        objects.add("来打打5");
        objects.add("来打打6");
        //从第几条数据开始
//        int firstIndex = (currPage - 1) * pageSize;
//        //到第几条数据结束
//        int lastIndex = currPage * pageSize;
        System.out.println(objects.subList(1,3));
    }
}
