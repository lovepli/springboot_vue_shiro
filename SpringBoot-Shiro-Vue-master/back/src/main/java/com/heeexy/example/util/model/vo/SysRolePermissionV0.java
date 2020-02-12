package com.heeexy.example.util.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author: lipan
 * @date: 2020-02-02
 * @description:
 */
@Data
public class SysRolePermissionV0 {

    private Integer id;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 权限id
     */
    private Integer permissionId;

    private String createTime;

    private String  updateTime;

    private String deleteStatus;
}
