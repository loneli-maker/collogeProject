package com.itheima.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.dao.UserDao;
import com.itheima.dao.UsertestDao;
import com.itheima.domain.User;
import com.itheima.domain.Usertest;
import com.itheima.service.UsertestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Service
public class UsertestServiceImpl implements UsertestService {
    @Autowired
    private UserDao userDao;
@Autowired
private UsertestDao usertestDao;


    @Override
    public void save(Usertest usertest) {
        usertestDao.insert(usertest);
    }

    @Override
    public void update(Usertest usertest) {
        usertestDao.updateById(usertest);
    }

    @Override
    public void delete(Integer id) {
        usertestDao.deleteById(id);
    }

    @Override
    public Usertest getById(Integer id) {
        return usertestDao.selectById(id);
    }

    @Override
    public List<Usertest> getAll() {
        return usertestDao.selectList(null);
    }

    @Override
    public IPage<Usertest> getPage(int currentPage, int pageSize) {
        IPage page = new Page(currentPage,pageSize);
        usertestDao.selectPage(page,null);
        return page;
    }
    /**
     * 执行密码加密
     * @param password 原始密码
     * @param salt 盐值
     * @return 加密后的密文
     */
    private String getMd5Password(String password, String salt) {
        /*
         * 加密规则：
         * 1、无视原始密码的强度
         * 2、使用UUID作为盐值，在原始密码的左右两侧拼接
         * 3、循环加密3次
         */
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }
    @Override
    public void reg(User user) {
        String username=user.getUsername();
        User result = userDao.selectByUsername(username);
        if (result != null) {
            // 是：表示用户名已被占用，则抛出UsernameDuplicateException异常
            System.out.println("尝试注册的用户名[" + username + "]已经被占用");
        }
        // 补全数据：加密后的密码
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = getMd5Password(user.getPassword(), salt);
        user.setPassword(md5Password);
        // 补全数据：盐值
        user.setSalt(salt);
        // 表示用户名没有被占用，则允许注册
        // 调用持久层Integer insert(User user)方法，执行注册并获取返回值(受影响的行数)
        Integer rows = userDao.insert(user);
    }
    @Override
    public User login(String username, String password) {
        // 调用userMapper的findByUsername()方法，根据参数username查询用户数据
        User result = userDao.selectByUsername(username);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：抛出UserNotFoundException异常
            System.out.println("用户数据不存在的错误");
        }
        // 从查询结果中获取盐值
        String salt = result.getSalt();
        String oldpassword = result.getPassword();
        // 调用getMd5Password()方法，将参数password和salt结合起来进行加密
        String newmd5Password = getMd5Password(password, salt);
        // 判断查询结果中的密码，与以上加密得到的密码是否不一致
        if (!oldpassword.equals(newmd5Password)) {
            // 是：抛出PasswordNotMatchException异常
            System.out.println("密码验证失败的错误");
        }
        return result;
    }

}
