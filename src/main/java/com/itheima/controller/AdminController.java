package com.itheima.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.dao.*;
import com.itheima.domain.*;
import com.itheima.service.*;
import com.itheima.util.ResponseResult;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.StyledEditorKit;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class AdminController extends BaseController{
    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private AdminService adminService;
    @Autowired
    private AdjuService adjuService;
    @Autowired
    private ApplyService applyService;
    @Value("${photo.file.dir}")
    private String realpath;
    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private ProjectService projectService;
    //显示所有项目
    @RequestMapping("/projectlist")
    public Object rojectlist(HttpServletRequest request,
                       @RequestParam(required = false, defaultValue = "1", value = "pn") Integer pn){
        IPage<Project> page = projectService.selectByPage(pn, 2);
        request.setAttribute("jumpUrl", "projectlist?pn=");
        //此处得到的page对象,包含了current（当前页）,pages（总页数），total（总记录数），records（记录，就是查询到的List集合）
      request.setAttribute("page", page);
        return "projectList/index";}
    //显示所有某位老师的项目
    @RequestMapping("projectmyTeacherlist")
    public String projectmyTeacherlist(HttpSession session,HttpServletRequest request,
                                       @RequestParam(required = false, defaultValue = "1", value = "pn") Integer pn){
//        log.debug("查询项目列表");
        Integer Tid=Integer.valueOf(session.getAttribute("teacherid").toString());
        IPage<Project> page=  projectService.getAllmyProject(Tid,pn, 2);
        request.setAttribute("jumpUrl", "projectlist?pn=");
        //此处得到的page对象,包含了current（当前页）,pages（总页数），total（总记录数），records（记录，就是查询到的List集合）
        request.setAttribute("page", page);
        return "projectList/index";}
    @RequestMapping("/teacherList")
    //pn是每次传回来的当前页
    public Object view(HttpServletRequest request,
                       @RequestParam(required = false, defaultValue = "1", value = "pn") Integer pn) {
        IPage<Teacher> page = adminService.selectByPage(pn, 2);
        request.setAttribute("jumpUrl", "teacherList?pn=");
        //此处得到的page对象,包含了current（当前页）,pages（总页数），total（总记录数），records（记录，就是查询到的List集合）
        request.setAttribute("page", page);
        return "system/teacherManager";
    }
    @RequestMapping("/adjuList")
    //pn是每次传回来的当前页
    public Object view(Model model) {

       model.addAttribute("adjuList",adjuService.getAll());
        return "system/adjuManager";
    }
    @RequestMapping("/searchsubmit")
    public String searchsubmit(String teacherName,Integer teacherId, HttpServletRequest request,
                               @RequestParam(required = false, defaultValue = "1", value = "pn") Integer pn) {
        IPage<Teacher> page = adminService.searchByPage(pn, 2, teacherName,teacherId);
        request.setAttribute("jumpUrl", "searchsubmit?teacherName="+teacherName+"&pn=");
        request.setAttribute("page", page);
        return "system/teacherManager";
    }
    //上传头像方法
    private String uploadPhoto(MultipartFile img, String originalFilename) throws IOException {
        String fileNamePrefix = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        String fileNameSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = fileNamePrefix + fileNameSuffix;
        img.transferTo(new File(realpath, newFileName));
        return newFileName;
    }

    /**
     * 保存员工信息
     * 文件上传: 1.表单提交方式必须是post  2.表单enctype属性必须为 multipart/form-data
     *
     * @return
     */
    @RequestMapping("save1")
    public String save(Project project, MultipartFile img, HttpSession session) throws IOException {
//        log.debug("姓名:{}, 薪资:{}, 生日:{} ", project.getProjectName(),project.getProjectInfo(),project.getProjectPhoto());
        boolean notEmpty = !img.isEmpty();

        if (notEmpty) {
            String originalFilename = img.getOriginalFilename();
//        log.debug("头像名称: {}", originalFilename);
//        log.debug("头像大小: {}", img.getSize());
//        log.debug("上传的路径: {}", realpath);
            Integer tid = Integer.valueOf(session.getAttribute("teacherid").toString());
            project.setTeacherId(tid);
            log.debug("teacherID名称: {}", tid);
            //1.处理头像的上传&修改文件名称
            String newFileName = uploadPhoto(img, originalFilename);
            //2.保存员工信息
            project.setProjectPhoto(newFileName);//保存头像名字
        }
        projectService.save(project);
        return "redirect:/projectlist";//保存成功跳转到列表页面
    }

    @RequestMapping("pass")
    public String setpass(Integer studentId){
//        log.debug("查询项目列表");
        adminService.setSelectedProjectPass(studentId);
        teacherService.changeProjectProgress(studentId,"，并通过");
        return "redirect:/selectAllpass";
    }
    @RequestMapping("selectAllStudentList")
    public String selectAllStudentList(Model model){
        model.addAttribute("student",studentDao.selectList(null));
        return "allot/studentManager";
    }
    @RequestMapping("selectAllpass")
    public String selectAllpass(Model model,Integer studentId,String passStatus){
//        log.debug("查询项目列表");
//        adminDao.passAllApply(passStatus);
        model.addAttribute("apply",adminDao.passAllApply(passStatus));
        return "allot/passApply";
    }
    @RequestMapping("AddAdjuServlet1")
    public String AddAdjuServlet1(Model model){
//        log.debug("查询项目列表");
        //这个接口是展示三个下拉框的数据存储作用域的地方
        List<Adjudicator> adju=  adjuService.getAll();
        model.addAttribute("adju",adju);
        model.addAttribute("project",projectDao.selectList(null));
        model.addAttribute("apply",applyService.getAll());
        return "allot/index";
    }
    @RequestMapping("AddAdjuServlet2")
    public String AddAdjuServlet2(Integer studentId,Integer projectId,Integer adjuId,Model model){
        Judge judge = new Judge();
        judge.setStudentId(studentId);
        judge.setProjectId(projectId);
        judge.setAdjuId(adjuId);
         Integer result=adminDao.setAdju(judge);
        System.out.println("fen配结果"+result);
         if (result==1){
             model.addAttribute("msg", "分配成功,请继续分配或选择其他操作");
             return "redirect:/AddAdjuServlet1";
         }else{
             //分配失败
             model.addAttribute("msg", "分配失败,请重试");
             return "redirect:/AddAdjuServlet1";
         }
    }
    @ResponseBody
    @RequestMapping("/teacherListJson")
    //pn是每次传回来的当前页
    public ResponseResult<List<Teacher>> teacherListJson() {
       List<Teacher> data=teacherService.getAll();

        return new ResponseResult<>(SUCCESS,data);
    }


    @RequestMapping("/saveTeacher")
    public String saveTeacher(Teacher teacher) {

        teacherService.save(teacher);
        return "redirect:/teacherList";
    }
    @RequestMapping("/saveAdju")
    public String saveAdju(Adjudicator adjudicator) {
        adjuService.save(adjudicator);
        return "redirect:/adjuList";
    }
    @RequestMapping("/exportAllApplys")
    public void exportAllApplys(HttpServletResponse response) throws Exception{
        //调用业务层方法，查询所有的Apply
        List<Apply> apply=applyService.getAll();

//        System.out.println("======"+apply.get(1));
        //创建Excel文件，把查询的数据apply写入到Excel中
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("项目申请信息表");

        HSSFFont titleFont = wb.createFont();
        titleFont.setBold(true);
        CellStyle titleCellStyle = wb.createCellStyle();
        titleCellStyle.setBorderTop(BorderStyle.THIN);
        titleCellStyle.setBorderBottom(BorderStyle.THIN);
        titleCellStyle.setBorderLeft(BorderStyle.THIN);
        titleCellStyle.setBorderRight(BorderStyle.THIN);
        titleCellStyle.setTopBorderColor(HSSFColor.BLACK.index);
        titleCellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
        titleCellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
        titleCellStyle.setRightBorderColor(HSSFColor.BLACK.index);
        titleCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平居中
        titleCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 上下居中
        titleCellStyle.setFont(titleFont);

        HSSFRow row=sheet.createRow(0);
        HSSFCell cell=row.createCell(0);
        cell.setCellStyle(titleCellStyle);
        cell.setCellValue("学生姓名");
        cell=row.createCell(1);
        cell.setCellStyle(titleCellStyle);
        cell.setCellValue("教师姓名");
        cell=row.createCell(2);
        cell.setCellStyle(titleCellStyle);
        cell.setCellValue("项目ID");
        cell=row.createCell(3);
        cell.setCellStyle(titleCellStyle);
        cell.setCellValue("项目名称");
        cell=row.createCell(4);
        cell.setCellStyle(titleCellStyle);
        cell.setCellValue("申报内容");
        cell=row.createCell(5);
        cell.setCellStyle(titleCellStyle);
        cell.setCellValue("指导内容");
        cell=row.createCell(6);
        cell.setCellStyle(titleCellStyle);
        cell.setCellValue("申报进度");
        cell=row.createCell(7);
        cell.setCellStyle(titleCellStyle);
        cell.setCellValue("通过状态");
        cell=row.createCell(8);
        cell.setCellStyle(titleCellStyle);
        cell.setCellValue("目前总分");
        cell=row.createCell(9);
        cell.setCellStyle(titleCellStyle);
        cell.setCellValue("评审员数");
        //样式对象HSSFCellStyle对象
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);




        //遍历apply 创建HSSFRow对象，生成所有的数据行
            if (apply!=null && apply.size()>0){
                Apply apply1=null;
                for (int i=0;i<apply.size();i++){//这个apply.size()是几个apply对象，相当于是循环出几条数据
                    apply1=apply.get(i);
                    row=sheet.createRow(i+1);
                    cell=row.createCell(0);
                    cell.setCellValue(apply1.getStudentName());
                    cell=row.createCell(1);
                    cell.setCellValue(apply1.getTeacherName());
                    cell=row.createCell(2);
                    cell.setCellStyle(style);//style
                    cell.setCellValue(apply1.getProjectId());
                    cell=row.createCell(3);
                    cell.setCellValue(apply1.getProjectName());
                    cell=row.createCell(4);
                    cell.setCellValue(apply1.getProjectContent());
                    cell=row.createCell(5);
                    cell.setCellValue(apply1.getProjectTeach());
                    cell=row.createCell(6);
                    cell.setCellValue(apply1.getProjectProgress());
                    cell=row.createCell(7);
                    cell.setCellValue(apply1.getPassStatus());
                    cell=row.createCell(8);
                    cell.setCellStyle(style);//style
                    cell.setCellValue(apply1.getProjectTotalScore());
                    cell=row.createCell(9);
                    cell.setCellStyle(style);//style
                    cell.setCellValue(apply1.getHasScoredAdjuNumber());
            }
        }
        //根据wb对象生成Excel文件
        OutputStream os = new FileOutputStream("D:\\my\\Server\\applyList.xls");
            wb.write(os);
            os.close();
            wb.close();

            //把生成的EXCEL文件下载到客户端
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.addHeader("Content-Disposition","attachment;filename=applyList.xls");
        OutputStream out = response.getOutputStream();
        InputStream is=new FileInputStream("D:\\my\\Server\\applyList.xls");
        byte[] buff=new byte[256];
        int len=0;
        while ((len=is.read(buff))!=-1){
            out.write(buff,0,len);
        }
        is.close();
        out.flush();
    }

}
