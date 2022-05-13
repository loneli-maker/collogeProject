package com.itheima;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.dao.*;
import com.itheima.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Booktest {
    @Autowired
    private UserDao userDao;
    @Autowired(required = false)
    private JudgeDao judgeDao;
    @Autowired
    private AdjuDao adjuDao;
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private ApplyDao applyDao;
    @Autowired
    private TeacherDao teacherDao;
    @Autowired
    private ProPlusDao proPlusDao;
    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private StudentDao studentDao;
//    @Autowired
//    private BookDao bookDao;
    @Autowired
    private UsertestDao usertestDao;
//    @Test
//    void select(){
//        System.out.println(bookDao.selectById(1));
//    }
//    @Test
//    void add(){
//        Book book=new Book();
//        book.setName("333");
//        book.setType("3");
//        book.setDescription("woshinidie ");
//        bookDao.insert(book);
//    }
//    @Test
//    void getAll(){
//        bookDao.selectList(null);
//    }
public static void main(String[] args) {
    double sum=100;
    double height=100;
    for(int i=1;i<=9;i++) {
        height/=2;
        sum=height*2+sum;
    }
    height=height/2;
    System.out.println("sum="+sum);   //299.7070
    System.out.println("第十次的高度是"+height);  //0.097
}
    @Test
    void getAlltest(){
        projectDao.selectList(null);
    }

    @Test
    void selectAllApplyProjects(){
//      proPlusDao.selectOneApplyProjects(5);
//        applyDao.selectById3(666,11);
        Judge j=new Judge();
        j.setStudentId(22);
        j.setProjectId(6);
        j.setAdjuId(45);
        adminDao.setAdju(j);
//        judgeDao.insert(j);报空指针异常错误
    }
  //       projectDao.selectById(1);
//        teacherDao.searchProjectGroupbyTeacher(666);
//        studentDao.selectById(26)
//        Student student = new Student();
//        student.setStudentId(26);
//        student.setStudentName("Liyuno");
//        studentDao.insert(student);
//        Apply apply = new Apply();
//        apply.setStudentId(26);
//        apply.setProjectTeach("这是测试第三个项目知道页面");
//      applyDao.selectById(26);
//        adjuDao.selectList(null);
//        adjuDao.queryAllNeedGradeAdju(001);
@Test
    void selectProjects(){
//        adjuDao.addScore2(2,10);
//        adjuDao.queryAllNeedGradeAdju(115);
//        studentDao.selectOne("2","123456");
//        studentDao.updatePasswordByUid(1,"1234");
//        teacherDao.selectOne("666","666");
//    adjuDao.selectList(null);
//    adjuDao.addScore2(123,60);
//    teacherDao.changeProjectProgress(2,"已评分,");
//        userDao.selectList(null);
//        studentDao.selectByStudentname("1");
//    teacherDao.changeProjectProgress(1,"已指导");
    teacherDao.selectByPTeacher(1);
    }
    @Test
    void getAlltestte(){
//       teacherDao.selectList(null);
//        applyDao.selectById2(2,"零食","111");
//        Page<ProPlus> objectPage = new Page<ProPlus>();
//        objectPage.getRecords();
//        proPlusDao.selectAllApplyProjects(objectPage);
//        studentDao.deleteById(6);
    }

    //条件分页查询
//    @Test
//    void getAllpage(){
//        QueryWrapper<Book> qw=new QueryWrapper<>();
//        qw.like("name", "李云鹏");
//        IPage page=new Page(1,5);
//        bookDao.selectPage(page,qw);
//    }

    @Test
    public void selectPage(){
        LambdaQueryWrapper<Teacher> userLambdaQueryWrapper = Wrappers.lambdaQuery();
//        userLambdaQueryWrapper.like(Teacher::getTeacherName , "*");

        Page<Teacher> userPage = new Page<>(2 , 2);
        IPage<Teacher> userIPage = teacherDao.selectPage(userPage , userLambdaQueryWrapper);
        System.out.println("总页数： "+userIPage.getPages());
        System.out.println("总记录数： "+userIPage.getTotal());
        userIPage.getRecords().forEach(System.out::println);
    }


}
