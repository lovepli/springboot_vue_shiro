package com.heeexy.example.util.model;

import lombok.Data;

import java.util.Date;

/**
 * @author: lipan
 * @date: 2020-02-01
 * @description: 角色-权限关联表
 */
@Data
public class SysRolePermission {

    private Integer id;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 权限id
     */
    private Integer permissionId;

    private Date createTime;

    private Date  updateTime;

    private String deleteStatus;
}
