package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.util.model.vo.UserPermissionDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @author: hxy
 * @date: 2017/10/30 13:28
 */
public interface PermissionDao {
	/**
	 * 查询用户的角色 菜单 权限
	 */
	JSONObject getUserPermission(String username);

	/**
	 * 查询所有的菜单
	 */
	Set<String> getAllMenu();

	/**
	 * 查询所有的权限
	 */
	Set<String> getAllPermission();



	//##############################################

    /**
     * 查询用户的角色 菜单 权限
     */
    JSONObject getUserPermission2(@Param("userName") String userName);

    /**
     * 查询所有的菜单
     * set集合过滤掉重复的元素
     */
    Set<String> getAllMenu2();

    /**
     * 查询所有的权限
     */
    Set<String> getAllPermission2();
}
