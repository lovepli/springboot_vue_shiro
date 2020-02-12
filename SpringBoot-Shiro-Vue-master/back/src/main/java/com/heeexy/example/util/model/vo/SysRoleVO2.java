package com.heeexy.example.util.model.vo;

import lombok.Data;


/**
 * @author: lipan
 * @date: 2020-02-02
 * @description:
 */
@Data
public class SysRoleVO2 {  //extends BaseVO

    private Integer roleId;
    /**
     * 角色名
     */
    private String roleName;


    private Integer userId;
    /**
     * 昵称
     */
    private String nickname;

    private Integer permissionId;
    /**
     * 菜单编码
     */
    private String menuCode;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 权限名
     */
    private String permissionName;




}
