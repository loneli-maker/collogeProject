package com.itheima.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.domain.Book;
import com.itheima.domain.User;
import com.itheima.domain.Usertest;

import java.util.List;

public interface UsertestService {
    void save(Usertest usertest);
    void update(Usertest usertest);
    void delete(Integer id);
    Usertest getById(Integer id);
    List<Usertest> getAll();
    IPage<Usertest> getPage(int currentPage, int pageSize);

    /**
     * 用户注册
     * @param user 用户数据
     */
    void reg(User user);

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 登录成功的用户数据
     */
    User login(String username, String password);
}
