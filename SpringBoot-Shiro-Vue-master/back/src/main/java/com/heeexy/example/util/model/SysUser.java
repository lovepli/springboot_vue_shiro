package com.heeexy.example.util.model;

import lombok.Data;

import java.util.Date;

/**
 * @author: lipan
 * @date: 2020-02-01
 * @description: 运营后台用户表
 */
@Data
public class SysUser {

    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 角色id
     */
    private Integer roleId;

    private Date createTime;

    private Date  updateTime;

    private String deleteStatus;
}
