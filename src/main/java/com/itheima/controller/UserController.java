package com.itheima.controller;

import com.itheima.controller.ex.*;
import com.itheima.domain.*;
import com.itheima.service.*;
import com.itheima.util.JsonResult;
import com.itheima.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.UUID;

@Controller
public class UserController extends BaseController{
    @Autowired
    private UsertestService usertestService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private AdjuService adjuService;

  @Autowired(required = false)
  private AdminService adminService;
    protected final String  getUsernameFromSession(HttpSession session) {
        return session.getAttribute("username").toString();
    }
    protected final Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("studentid").toString());
    }
    public static final int OK = 200;
    @RequestMapping("change_password")
    public String changePassword(String studentPassword, String newPassword, HttpSession session, Model model) {
        ModelAndView mv=new ModelAndView();
        // 调用session.getAttribute("")获取uid和username
        Integer uid = getUidFromSession(session);
        System.out.println(uid);
        // 调用业务对象执行修改密码
        studentService.changePassword(uid,studentPassword,newPassword);
       // 返回成功
        model.addAttribute("message","密码修改成功，请重新登录");
        return "student/edit";

    }
    @RequestMapping("reg")
    public String reg(Student student,Map<String, Object> map) {
        // 调用业务对象执行注册
           int rows= studentService.reg(student);
           if (rows>0){
               map.put("msg", "注册成功，请登录！");
               return "login";
           }else{
               map.put("msg", "用户名已存在，请重新注册！");
               return "login";
           }
    }
    @ResponseBody
    @RequestMapping("/regJson")
    //pn是每次传回来的当前页
    public ResponseResult<Void> regJson(Student student) {
        studentService.reg(student);
        return new ResponseResult<Void>(SUCCESS);
    }
    @GetMapping("/loginout")
    public String logout(HttpSession session){
        session.invalidate(); //退出登录则清除session中的用户信息
        return "login";
    }
    @RequestMapping("loginT")
    public String loginT(String username, String password,HttpSession session){
        User result= usertestService.login(username,password);
        session.setAttribute("username",result.getUsername());
        return "1";
    }
    @RequestMapping("loginServelt")
    public String login(  Map<String, Object> map,
                          HttpServletRequest request, HttpSession session, Model model) {
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

//        Student student = new Student();
        Object user = null;
//        System.out.println(role);
        if ("student".equals(role)) {
            user = studentService.login(name, password);
            if(user!=null){
            //强行把ID存入session
            Student student = studentService.login(name, password);
            //把头像放入session中，登陆后也要放一次，否则登陆进去不知道头像信息
            session.setAttribute("studentAvatar",student.getStudentAvatar());
//            System.out.println("头像"+session.getAttribute("studentAvatar"));
            session.setAttribute("studentid",student.getStudentId());
            session.setAttribute("username", student.getStudentName());
            }
            else{ map.put("msg", "您不属于这个角色，无法登录");
                return "login";}
        } else if ("teacher".equals(role)) {
            user = teacherService.login(name, password);
            if(user!=null){
            Teacher teacher = teacherService.login(name, password);
            session.setAttribute("teacherid",teacher.getTeacherId());
//                session.setAttribute("username", teacher.getTeacherName());//拦截登陆加的
            }else {
//                throw new RoleMatchException("您不属于这个角色，无法登录");
                //登陆失败
                map.put("msg", "您不属于这个角色，无法登录");
                return "login";
            }


        } else if ("adju".equals(role)) {
            user = adjuService.login(name, password);
            if(user!=null) {
                Adjudicator adjudicator = adjuService.login(name, password);

                session.setAttribute("adjuId", adjudicator.getAdjudicatorId());//这里报了空指针异常，这里等下还要改一下。
            }else{map.put("msg", "您不属于这个角色，无法登录");
                return "login";}
        } else if ("admin".equals(role)) {
            user = adminService.login(name, password);//这个种类的用户会提示报错信息
        }
        //把权限保存到session中
        session.setAttribute("role", role);
        if (user != null) {
//        if (StringUtils.isEmpty()) {
            //不能跟上面学生那设置的username重复了,要不然姓名那会显示用户测试1
            session.setAttribute("userInterceptor", "用户测试1");//拦截登录，如果username不为空，才，可以进入，所以随便加了个字符
            session.setAttribute("userLogin", user);
            System.out.println(session.getAttribute("userLogin"));
            return "redirect:/index";
        } else {
            //登陆失败
            map.put("msg", "用户名或密码错误");
            return "login";
        }

    }

    /** 头像文件大小的上限值(10MB) */
    public static final int AVATAR_MAX_SIZE = 10 * 1024 * 1024;
    /** 允许上传的头像的文件类型 */
    public static final List<String> AVATAR_TYPES = new ArrayList<String>();

    /** 初始化允许上传的头像的文件类型 */
    static {
        AVATAR_TYPES.add("image/jpg");
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/gif");
    }

    @PostMapping("change_avatar")
    public JsonResult<String> changeAvatar(@RequestParam("file") MultipartFile file, HttpSession session) {
        // 判断上传的文件是否为空
        if (file.isEmpty()) {
            // 是：抛出异常
            throw new FileEmptyException("上传的头像文件不允许为空");
        }

        // 判断上传的文件大小是否超出限制值
        if (file.getSize() > AVATAR_MAX_SIZE) { // getSize()：返回文件的大小，以字节为单位
            // 是：抛出异常
            throw new FileSizeException("不允许上传超过" + (AVATAR_MAX_SIZE / 1024) + "KB的头像文件");
        }
        // 判断上传的文件类型是否超出限制
        String contentType = file.getContentType();
        // boolean contains(Object o)：当前列表若包含某元素，返回结果为true；若不包含该元素，返回结果为false
        if (!AVATAR_TYPES.contains(contentType)) {
            // 是：抛出异常
            throw new FileTypeException("不支持使用该类型的文件作为头像，允许的文件类型：" + AVATAR_TYPES);
        }

        // 获取当前项目的绝对磁盘路径
        String parent = session.getServletContext().getRealPath("upload");

        System.out.println(parent);
        // 保存头像文件的文件夹
        File dir = new File(parent);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 保存的头像文件的文件名
        String suffix = "";
        String originalFilename = file.getOriginalFilename();
        int beginIndex = originalFilename.lastIndexOf(".");
        if (beginIndex > 0) {
            suffix = originalFilename.substring(beginIndex);
        }
        String filename = UUID.randomUUID().toString() + suffix;
        // 创建文件对象，表示保存的头像文件
        File dest = new File(dir, filename);
        // 执行保存头像文件
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            // 抛出异常
            throw new FileStateException("文件状态异常，可能文件已被移动或删除");
        } catch (IOException e) {
            // 抛出异常
            throw new FileUploadIOException("上传文件时读写错误，请稍后重新尝试");
        }

        // 头像路径
        String avatar = "/upload/" + filename;
        // 从Session中获取uid和username
        Integer uid = getUidFromSession(session);
        System.out.println(uid);
//        String username = getUsernameFromSession(session);
        // 将头像写入到数据库中
        studentService.changeAvatar(uid, avatar);

        // 返回成功头像路径
        return new JsonResult<String>(OK, avatar);
//        return "student/index";
    }

}

