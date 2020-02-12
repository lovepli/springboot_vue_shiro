package com.heeexy.example.util.model.vo;

import com.heeexy.example.util.model.SysPermission;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author: lipan
 * @date: 2020-02-07
 * @description:
 */
@Data
public class UserPermissionDTO2 {

    private Integer userId;
    /**
     * 用户名
     */
    private String nickName;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 角色名
     */
    private String roleName;


    private List<SysPermission> sysPermissions;

    Set<String> menuList ;
    Set<String> permissionList ;


}
