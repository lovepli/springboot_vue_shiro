package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.util.model.utilBean.MessageBean;
import com.heeexy.example.util.model.vo.SysUserDTO;
import com.heeexy.example.util.model.vo.UserPermissionDTO;
import com.heeexy.example.util.model.vo.UserPermissionDTO2;

/**
 * @author: hxy
 * @description: 登录Service
 * @date: 2017/10/24 11:02
 */
public interface LoginService {
	/**
	 * 登录表单提交
	 */
	JSONObject authLogin(JSONObject jsonObject);

	/**
	 * 根据用户名和密码查询对应的用户
	 *
	 * @param username 用户名
	 * @param password 密码
	 */
	JSONObject getUser(String username, String password);

	/**
	 * 查询当前登录用户的权限等信息
	 */
	JSONObject getInfo();

	/**
	 * 退出登录
	 */
	JSONObject logout();

	//##############################################################
    /**
     * 登录表单提交
     * @return
     */
    MessageBean<Void> authLogin2(SysUserDTO sysUserDTO);

    /**
     * 根据用户名和密码查询对应的用户
     *
     * @param userName 用户名
     * @param password 密码
     */
    SysUserDTO getUser2(String userName, String password);

    /**
     * 查询当前登录用户的权限等信息
     */
    JSONObject getInfo2();

    /**
     * 退出登录
     */
    MessageBean<Void> logout2();


}
