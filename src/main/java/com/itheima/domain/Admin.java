package com.itheima.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Admin {
    @TableId(value = "admin_id")
    private String adminId;
    private String adminPassword;
    private String adminName;


}
