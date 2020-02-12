package com.heeexy.example.util.model.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author: lipan
 * @date: 2020-02-05
 * @description:  数据传输对象 前端传递的参数
 */
@Data
public class SysRolePermissionDTO {

    /**
     * 角色名
     */
    private String roleName;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 权限id
     * 复选框的ID
     */
    private List<Integer> permissions;
}
