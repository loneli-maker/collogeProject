package com.itheima.controller;

import com.itheima.dao.UsertestDao;
import com.itheima.domain.Usertest;
import com.itheima.service.UsertestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class TestController {
    @Autowired
    private UsertestService usertestService;
    @RequestMapping({ "test"})
    public String usertestList(ModelMap map){
        map.addAttribute("user",usertestService.getAll());
        return "test/usertest";
    }

    @RequestMapping("edit")
    public String edit(ModelMap map, @RequestParam(defaultValue = "0") int id){
        //isAdd : 向前端页面返回一个是新增与编辑的标识
        if(id > 0){
            map.addAttribute("isAdd",false);
            map.addAttribute("user",usertestService.getById(id));
        }else{
            map.addAttribute("isAdd",true);
            map.addAttribute("user",new Usertest());
        }
        return "test/edit";
    }


    //新增和编辑
    @ResponseBody
    @RequestMapping("save")
    public String save(@ModelAttribute Usertest usertest){
        if(usertest == null){
            return "fail";
        }
        if(usertest.getId() != null && usertest.getId() > 0){
            usertestService.update(usertest);
        }else{
            usertestService.save(usertest);
        }
        return "test/usertest";
    }

}
