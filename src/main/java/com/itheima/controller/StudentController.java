package com.itheima.controller;

import com.itheima.dao.*;
import com.itheima.domain.Apply;
import com.itheima.domain.Project;
import com.itheima.domain.Student;
import com.itheima.domain.Usertest;
import com.itheima.service.ApplyService;
import com.itheima.service.ProPlusService;
import com.itheima.service.ProjectService;
import com.itheima.service.StudentService;
import com.itheima.service.ex.UsernameDuplicateException;
import com.itheima.util.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class StudentController extends BaseController{
    @Value("${photo.file.dir}")
    private String realpath;
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private ApplyService applyService;
    @Autowired
    private ProPlusDao proPlusDao;
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ApplyDao applyDao;
    @Autowired
    private ProPlusService proPlusService;
@Autowired
private ProjectService projectService;
@Autowired
private StudentDao studentDao;
    protected final Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("studentid").toString());
    }
//申报项目第一步，显示所有可申报项目  查询ProPlus
    @RequestMapping({ "applylist1"})
    public String queryAllProject(ModelMap map,Integer project_id,String project_name,String teacher_name){
        map.addAttribute("project",proPlusDao.selectAllApplyProjects(project_id,project_name,teacher_name));
        return "Student/applylist";
    }
//    @RequestMapping({ "applylist1"})
//    public String queryAllProject(HttpServletRequest request, Integer project_id, String project_name, String teacher_name, @RequestParam(required = false, defaultValue = "1", value = "pn") Integer pn){
//        request.setAttribute("project",proPlusService.selectAllApplyProjects(pn,2));
//        request.setAttribute("jumpUrl", "applylist1?pn=");
//        //此处得到的page对象,包含了current（当前页）,pages（总页数），total（总记录数），records（记录，就是查询到的List集合）
////        request.setAttribute("page", page);
//
//        return "Student/applylist";
//    }
    //shen申报项目第二步进入表单提交页面，
    @RequestMapping("apply2")
    public String edit(ModelMap map, @RequestParam(defaultValue = "0") int projectId){
        //isAdd : 向前端页面返回一个是新增与编辑的标识
        if(projectId > 0){
            map.addAttribute("isAdd",false);
            map.addAttribute("project",projectDao.selectById(projectId));
            map.addAttribute("teacher",teacherDao.selectById(projectDao.selectById(projectId).getTeacherId()));
//            map.addAttribute("project",proPlusDao.selectOneApplyProjects(projectId));

        }else{
            map.addAttribute("isAdd",true);
            map.addAttribute("project",new Project());
        }
        return "Student/applyProject";
    }
//    第三步，提交表单，点击申报按钮
    @ResponseBody
    @RequestMapping("apply3")
    public ResponseResult<Void> save(Apply apply, RedirectAttributes attributes,HttpSession session,String project_name,String teacher_name){
        Integer sid=Integer.valueOf(session.getAttribute("studentid").toString());
//        apply.setStudentId(sid);
        Apply result = applyDao.selectById2(sid,project_name,teacher_name);
        if (result!=null){
            // 是：表示用户名已被占用，则抛出UsernameDuplicateException异常
            throw new UsernameDuplicateException("存在申请项目，无法继续申请！");
        }
        applyService.save(apply);
//            attributes.addFlashAttribute("message","申报成功！");
//            return "redirect:/querymyapply";//重定向不能使用Model携带信息
        return new ResponseResult<Void>(SUCCESS);
    }
//    查看已申报项目
    @RequestMapping("querymyapply")
    public String querymyapply(ModelMap map,HttpSession session,String project_name,String teacher_name){
        Integer Sid=getUidFromSession(session);
        map.addAttribute("myapply",applyService.getById2(Sid,project_name,teacher_name));
        return "Student/applymylist";
    }


    //    查看学生信息
    @RequestMapping("queryStuInfo")
    public String queryStuInfo(ModelMap map,HttpSession session){
        Integer Sid=getUidFromSession(session);
        map.addAttribute("student",studentService.getById(Sid));
        //修改投降后，重新把头像信息放入session中,旧的头像为空，所有一定要再放一次
        session.setAttribute("studentAvatar",studentService.getById(Sid).getStudentAvatar());
        return "Student/index";
    }
    //上传头像方法
    private String uploadPhoto(MultipartFile img, String originalFilename) throws IOException {
        String fileNamePrefix = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        String fileNameSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = fileNamePrefix + fileNameSuffix;
        img.transferTo(new File(realpath, newFileName));
        return newFileName;
    }
    @RequestMapping("studentupdate")
    public String update(Student student, MultipartFile img) throws IOException {

        log.debug("姓名:{},dian电话号码:{},性别:{},", student.getStudentName(), student.getStudentPhonenumber(), student.getStudentSex());
        //1.判断是否更新头像
        boolean notEmpty = !img.isEmpty();
        log.debug("是否更新头像: {}", notEmpty);
        if (notEmpty) {
            //1.删除老的头像 根据id查询原始头像
            String oldPhoto = studentService.getById(student.getStudentId()).getStudentAvatar();
            File file = new File(realpath, oldPhoto);
            if (file.exists()) file.delete();//删除文件
            //2.处理新的头像上传
            String originalFilename = img.getOriginalFilename();
            String newFileName = uploadPhoto(img, originalFilename);
            //3.修改员工新的头像名称
            student.setStudentAvatar(newFileName);
        }
        //2.没有更新头像直接更新基本信息
       studentService.update(student);
        return "redirect:/queryStuInfo";//更新成功,跳转到员工列表
    }


    @RequestMapping("studentDel/{studentId}")
    public String studentDel(@PathVariable Integer studentId){
        studentDao.deleteById(studentId);
        return "Student/studentManager";
    }


//    //新增和编辑
//    @ResponseBody
//    @RequestMapping("save")
//    public String save(@ModelAttribute Usertest usertest){
//        if(usertest == null){
//            return "fail";
//        }
//        if(usertest.getId() != null && usertest.getId() > 0){
//            usertestService.update(usertest);
//        }else{
//            usertestService.save(usertest);
//        }
//        return "success";
//    }
}
