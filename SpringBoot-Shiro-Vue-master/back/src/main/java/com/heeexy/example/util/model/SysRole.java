package com.heeexy.example.util.model;

import lombok.Data;

import java.util.Date;

/**
 * @author: lipan
 * @date: 2020-02-01
 * @description: 后台角色表
 */
@Data
public class SysRole {

    private Integer id;
    /**
     * 角色名
     */
    private String roleName;

    private Date createTime;

    private Date  updateTime;

    private String deleteStatus;
}
