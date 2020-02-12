package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.PermissionDao;
import com.heeexy.example.service.PermissionService;
import com.heeexy.example.util.model.vo.UserPermissionDTO;
import com.heeexy.example.util.model.vo.UserPermissionDTO2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author: hxy
 * @date: 2017/10/30 13:15
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    private static Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);

	@Autowired
	private PermissionDao permissionDao;

	/**
	 * 查询某用户的 角色  菜单列表   权限列表
	 */
	@Override
	public JSONObject getUserPermission(String username) {
		JSONObject userPermission = getUserPermissionFromDB(username);
		return userPermission;
	}

	/**
	 * 从数据库查询用户权限信息
	 */
	private JSONObject getUserPermissionFromDB(String username) {
		JSONObject userPermission = permissionDao.getUserPermission(username);
        logger.info("1111111查询出的userPermission结果为：{}",userPermission.toString());
		//管理员角色ID为1
		int adminRoleId = 1;
		//如果是管理员
		String roleIdKey = "roleId";
		if (adminRoleId == userPermission.getIntValue(roleIdKey)) {
			//查询所有菜单  所有权限
			Set<String> menuList = permissionDao.getAllMenu();
			Set<String> permissionList = permissionDao.getAllPermission();
			userPermission.put("menuList", menuList);
			userPermission.put("permissionList", permissionList);
		}
		return userPermission;
	}

	//#####################################################################
    /**
     * 查询某用户的 角色  菜单列表   权限列表
     */
    @Override
    public JSONObject getUserPermission2(String userName) {
        JSONObject userPermission = getUserPermissionFromDB2(userName);
        return userPermission;
    }

    /**
     * 从数据库查询用户权限信息
     */
    private JSONObject getUserPermissionFromDB2(String userName) {
        logger.info("查询出的用户名为：{}",userName);
        JSONObject userPermission = permissionDao.getUserPermission2(userName);  //TODO 这里不应该写固定值
        logger.info("查询出的UserPermissionDTO结果为：{}",userPermission.toString());

        //管理员角色ID为1
        int adminRoleId = 1;
        //如果是管理员，获得所有权限
        String roleIdKey = "roleId";
        if (adminRoleId == userPermission.getIntValue(roleIdKey)){
            //查询所有菜单  所有权限
            Set<String> menuList2 = permissionDao.getAllMenu2();
            Set<String> permissionList2 = permissionDao.getAllPermission2();
            userPermission.put("menuList", menuList2);
            userPermission.put("permissionList", permissionList2);
        }
        return userPermission;
    }




}
