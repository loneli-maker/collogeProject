package com.itheima.domain;

import lombok.Data;

@Data
public class User {
    private Long userId;
    private String username;
    private String password;
    private String email;
    private Integer isEnable;
    private String salt;
}
