package com.heeexy.example.util.model.vo;

import com.heeexy.example.util.model.SysPermission;
import lombok.Data;

import java.util.Date;
import java.util.Set;

/**
 * @author: lipan
 * @date: 2020-02-02
 * @description:
 */
@Data
public class SysUserVO {

    private Integer userId;
    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;
    /**
     * 角色id
     */
    private Integer roleId;

    private String createTime;

    private String  updateTime;

    private String deleteStatus;
    /**
     * 角色名
     */
    private String roleName;

    /**
     * 权限集合
     */
   // private Set<SysPermission> permissionList;
    /**
     * 权限全称 这里是菜单名+权限
     */
    private String permissionName;


}
