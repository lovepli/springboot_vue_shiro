package com.heeexy.example.util.model.vo;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author: lipan
 * @date: 2020-02-05
 * @description:
 */
@Data
public class SysRolePermissionDTO2 {
    /**
     * 角色名
     */
    private String roleName;
    /**
     * sys_role
     * 角色id
     */
    private Integer roleId;

    /**
     * sys_permission
     * 权限ID
     */
    private Integer permissionId;

    /**
     * sys_user
     * 用户ID
     */
    private Integer userId;

}
