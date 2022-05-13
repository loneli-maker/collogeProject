package com.itheima.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.dao.ProPlusDao;
import com.itheima.dao.StudentDao;
import com.itheima.domain.*;
import com.itheima.service.StudentService;
import com.itheima.service.ex.PasswordNotMatchException;
import com.itheima.service.ex.UpdateException;
import com.itheima.service.ex.UserNotFoundException;
import com.itheima.service.ex.UsernameDuplicateException;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private ProPlusDao proPlusDao;
    @Autowired
    private StudentDao studentDao;
    @Override
    public int reg(Student student) {
        String studentname=student.getStudentName();
        Student result = studentDao.selectByStudentname(studentname);
        if (result != null) {
            // 是：表示用户名已被占用，则抛出UsernameDuplicateException异常
            throw new UsernameDuplicateException("尝试注册的用户名[" + student + "]已经被占用1");
        }else{
        // 表示用户名没有被占用，则允许注册
        // 调用持久层Integer insert(User user)方法，执行注册并获取返回值(受影响的行数)
        int rows = studentDao.insert(student);
            return rows;
        }

    }
    @Override
    public void update(Student student) {
        studentDao.updateById(student);
    }

    @Override
    public void delete(Integer studentId) {

    }

    @Override
    public Student getById(Integer studentId) {
        return studentDao.selectById(studentId);
    }

    @Override
    public List<Student> getAll() {
        return null;
    }

    @Override
    public Student login(String studentName, String studentPassword) {
        return studentDao.selectOne(studentName,studentPassword);
    }

//    @Override
//    public IPage<ProPlus> selectByPage(int start, int size) {
//        Page<ProPlus> page=new Page<>(start,size);
//        proPlusDao.selectPage(page,null);
//        return page;
//    }

    @Override
    public void changePassword(Integer studentId, String studentPassword, String newPassword) {
        Student result = studentDao.selectById(studentId);
        if (result == null) {
            // 是：抛出UserNotFoundException异常
            throw new UserNotFoundException("用户数据不存在");
        }
        System.out.println(result.getStudentPassword());
//        String oldPassword = result.getStudentPassword();
//         判断查询结果中的password与oldMd5Password是否不一致
        if (!result.getStudentPassword().equals(studentPassword)) {
            // 是：抛出PasswordNotMatchException异常
            throw new PasswordNotMatchException("原密码错误");
        }
        // 调用userMapper的updatePasswordByUid()更新密码，并获取返回值
        Integer rows = studentDao.updatePasswordByUid(studentId,newPassword);
        // 判断以上返回的受影响行数是否不为1
        if (rows != 1) {
            // 是：抛出UpdateException异常
            throw new UpdateException("更新用户数据时出现未知错误，请联系系统管理员");
        }
    }

//    @Override
//    public IPage<ProPlus> selectByPage(int start, int size) {
//        Page<Teacher> page=new Page<>(start,size);
//        teacherDao.selectPage(page, null);
//        return page;
//    }

    @Override
    public void changeAvatar(Integer studentId, String studentAvatar) {
        // 调用userMapper的findByUid()方法，根据参数uid查询用户数据
        Student result = studentDao.selectById(studentId);
        // 检查查询结果是否为null
        if (result == null) {
            // 是：抛出UserNotFoundException
            throw new UserNotFoundException("用户数据不存在");
        }
        // 调用userMapper的updateAvatarByUid()方法执行更新，并获取返回值
        Integer rows = studentDao.updateAvatarByUid(studentId,studentAvatar);
        // 判断以上返回的受影响行数是否不为1
        if (rows != 1) {
            // 是：抛出UpdateException
            throw new UpdateException("更新用户数据时出现未知错误，请联系系统管理员");
        }
    }


//    @Override
//    public List<Project> selectAllApplyProjects() {
//       return studentDao.selectAllApplyProjects();
//    }
}
