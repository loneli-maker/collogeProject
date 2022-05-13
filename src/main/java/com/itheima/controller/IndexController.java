package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller

//@RequestMapping("/user")
public class IndexController {
    @RequestMapping({"/", "/index"})
    public String toIndex(){
    return "index";
    }
    @RequestMapping({ "/login"})
    public String login(){
        return "login";
    }

    @RequestMapping({ "/article"})
    public String article(){
        return "article/index";
    }
//    @RequestMapping({ "/editArticle"})
//    public String edit(){
//        return "article/edit";
//    }
        @RequestMapping({ "/projectList"})
    public String projectList(){
        return "projectList/index";
    }
    @RequestMapping({ "/projectAdd"})
    public String projectAdd(){
        return "projectList/edit";
    }

//    @RequestMapping({ "/teaproject"})
//    public String teaproject(){
//        return "teacher/index";
//    }
    @RequestMapping({ "/teaaddproject"})
    public String teaaddproject(){
        return "guidelist";
    }

    @RequestMapping({ "/tag"})
    public String tag(){
        return "tag/index";
    }

    @RequestMapping({ "/cpassword"})
    public String editUser(){
        return "student/edit";
    }

    @RequestMapping({ "/show_userinfo"})
    public String user(){
        return "student/index";
    }

    @RequestMapping({ "/allotindex"})
    public String allotindex(){
        return "allot/index";
    }
    @RequestMapping({ "/allotedit"})
    public String allotedit(){
        return "allot/edit";
    }


}


