package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.util.model.vo.UserPermissionDTO;
import com.heeexy.example.util.model.vo.UserPermissionDTO2;

/**
 * @author: hxy
 * @date: 2017/10/30 13:10
 */
public interface PermissionService {
	/**
	 * 查询某用户的 角色  菜单列表   权限列表
	 */
	JSONObject getUserPermission(String username);

    /**
     * 查询某用户的 角色  菜单列表   权限列表
     */
    JSONObject getUserPermission2(String userName);
}
