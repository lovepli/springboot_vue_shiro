package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.util.model.SysRole;
import com.heeexy.example.util.model.SysUser;
import com.heeexy.example.util.model.query.SysUserQueryBean;
import com.heeexy.example.util.model.vo.*;

import java.util.List;

/**
 * @author: hxy
 * @description: 用户/角色/权限
 * @date: 2017/11/2 10:18
 */
public interface UserService {
	/**
	 * 用户列表
	 */
	JSONObject listUser(JSONObject jsonObject);

	/**
	 * 查询所有的角色
	 * 在添加/修改用户的时候要使用此方法
	 */
	JSONObject getAllRoles();

	/**
	 * 添加用户
	 */
	JSONObject addUser(JSONObject jsonObject);

	/**
	 * 修改用户
	 */
	JSONObject updateUser(JSONObject jsonObject);

	/**
	 * 角色列表
	 */
	JSONObject listRole();

	/**
	 * 查询所有权限, 给角色分配权限时调用
	 */
	JSONObject listAllPermission();

	/**
	 * 添加角色
	 */
	JSONObject addRole(JSONObject jsonObject);

	/**
	 * 修改角色
	 */
	JSONObject updateRole(JSONObject jsonObject);

	/**
	 * 删除角色
	 */
	JSONObject deleteRole(JSONObject jsonObject);


	//######################################################

    /**
     * 用户列表
     * @param sysUserQueryBean
     * @return
     */
    List<SysUserVO> listUser2(SysUserQueryBean sysUserQueryBean);

    List<JSONObject> listUser3(SysUserQueryBean sysUserQueryBean);

    /**
     * 添加用户
     */
    Integer addUser2(SysUser sysUser);

    /**
     * 修改用户
     */
    Integer updateUser2(SysUser sysUser);

    /**
     * 查询所有的角色
     * 在添加/修改用户的时候要使用此方法  下拉框
     */
    List<SysRoleVO> getAllRoles2();

    /**
     * 角色列表
     */
    List<SysRoleVO2> listRole2();

    List<JSONObject> listRole3();

    /**
     * 查询所有权限, 给角色分配权限时调用
     */
    List<SysPermissionVO> listAllPermission2();

    List<JSONObject> listAllPermission3();

    /**
     * 添加角色
     */
    void addRole2(SysRolePermissionDTO sysRolePermissionDTO);

    /**
     * 修改角色
     */
    void updateRole2(SysRolePermissionDTO sysRolePermissionDTO);


    /**
     * 删除角色
     */
    void deleteRole2(SysRolePermissionDTO sysRolePermissionDTO);

}
