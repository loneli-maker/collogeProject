package com.itheima.controller;

import com.itheima.domain.Apply;
import com.itheima.domain.Project;
import com.itheima.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplyController {
    @Autowired
    private ApplyService applyService;
//    @RequestMapping("applylist")
//    public String applylist(ModelMap map){
//        map.addAttribute("apply",applyService.getAll());
//        return "allot/index";}
}
