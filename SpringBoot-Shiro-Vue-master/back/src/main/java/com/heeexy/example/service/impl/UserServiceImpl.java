package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.controller.UserController2;
import com.heeexy.example.dao.UserDao;
import com.heeexy.example.service.UserService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.constants.ErrorEnum;
import com.heeexy.example.util.model.SysRole;
import com.heeexy.example.util.model.SysUser;
import com.heeexy.example.util.model.query.SysUserQueryBean;
import com.heeexy.example.util.model.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author: hxy
 * @description: 用户/角色/权限
 * @date: 2017/11/2 10:18
 */
@Service
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	/**
	 * 用户列表
	 */
	@Override
	public JSONObject listUser(JSONObject jsonObject) {
		CommonUtil.fillPageParam(jsonObject);
		int count = userDao.countUser(jsonObject);
		List<JSONObject> list = userDao.listUser(jsonObject);
		return CommonUtil.successPage(jsonObject, list, count);
	}

	/**
	 * 添加用户
	 */
	@Override
	public JSONObject addUser(JSONObject jsonObject) {
		int exist = userDao.queryExistUsername(jsonObject);
		if (exist > 0) {
			return CommonUtil.errorJson(ErrorEnum.E_10009);
		}
		userDao.addUser(jsonObject);
		return CommonUtil.successJson();
	}

	/**
	 * 查询所有的角色
	 * 在添加/修改用户的时候要使用此方法
	 */
	@Override
	public JSONObject getAllRoles() {
		List<JSONObject> roles = userDao.getAllRoles();
		return CommonUtil.successPage(roles);
	}

	/**
	 * 修改用户
	 */
	@Override
	public JSONObject updateUser(JSONObject jsonObject) {
		userDao.updateUser(jsonObject);
		return CommonUtil.successJson();
	}

	/**
	 * 角色列表
	 */
	@Override
	public JSONObject listRole() {
		List<JSONObject> roles = userDao.listRole();
		return CommonUtil.successPage(roles);
	}

	/**
	 * 查询所有权限, 给角色分配权限时调用
	 */
	@Override
	public JSONObject listAllPermission() {
		List<JSONObject> permissions = userDao.listAllPermission();
		return CommonUtil.successPage(permissions);
	}

	/**
	 * 添加角色
	 */
	@Transactional(rollbackFor = Exception.class)
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject addRole(JSONObject jsonObject) {
		userDao.insertRole(jsonObject);
		userDao.insertRolePermission(jsonObject.getString("roleId"), (List<Integer>) jsonObject.get("permissions"));
		return CommonUtil.successJson();
	}

	/**
	 * 修改角色
	 */
	@Transactional(rollbackFor = Exception.class)
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject updateRole(JSONObject jsonObject) {
		String roleId = jsonObject.getString("roleId");
		List<Integer> newPerms = (List<Integer>) jsonObject.get("permissions");
		JSONObject roleInfo = userDao.getRoleAllInfo(jsonObject);
		Set<Integer> oldPerms = (Set<Integer>) roleInfo.get("permissionIds");
		//修改角色名称
		dealRoleName(jsonObject, roleInfo);
		//添加新权限
		saveNewPermission(roleId, newPerms, oldPerms);
		//移除旧的不再拥有的权限
		removeOldPermission(roleId, newPerms, oldPerms);
		return CommonUtil.successJson();
	}

	/**
	 * 修改角色名称
	 */
	private void dealRoleName(JSONObject paramJson, JSONObject roleInfo) {
		String roleName = paramJson.getString("roleName");
		if (!roleName.equals(roleInfo.getString("roleName"))) {
			userDao.updateRoleName(paramJson);
		}
	}

	/**
	 * 为角色添加新权限
	 */
	private void saveNewPermission(String roleId, Collection<Integer> newPerms, Collection<Integer> oldPerms) {
		List<Integer> waitInsert = new ArrayList<>();
		for (Integer newPerm : newPerms) {
			if (!oldPerms.contains(newPerm)) {
				waitInsert.add(newPerm);
			}
		}
		if (waitInsert.size() > 0) {
			userDao.insertRolePermission(roleId, waitInsert);
		}
	}

	/**
	 * 删除角色 旧的 不再拥有的权限
	 */
	private void removeOldPermission(String roleId, Collection<Integer> newPerms, Collection<Integer> oldPerms) {
		List<Integer> waitRemove = new ArrayList<>();
		for (Integer oldPerm : oldPerms) {
			if (!newPerms.contains(oldPerm)) {
				waitRemove.add(oldPerm);
			}
		}
		if (waitRemove.size() > 0) {
			userDao.removeOldPermission(roleId, waitRemove);
		}
	}

	/**
	 * 删除角色
	 */
	@Transactional(rollbackFor = Exception.class)
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject deleteRole(JSONObject jsonObject) {
		JSONObject roleInfo = userDao.getRoleAllInfo(jsonObject);
		List<JSONObject> users = (List<JSONObject>) roleInfo.get("users");
		if (users != null && users.size() > 0) {
			return CommonUtil.errorJson(ErrorEnum.E_10008);
		}
		userDao.removeRole(jsonObject);
		userDao.removeRoleAllPermission(jsonObject);
		return CommonUtil.successJson();
	}

    /**
     * 用户列表
     * @param sysUserQueryBean
     * @return
     */
    @Override
    public List<SysUserVO> listUser2(SysUserQueryBean sysUserQueryBean) {
        List<SysUserVO> list = userDao.listUser2(sysUserQueryBean);
        return list;
    }

    /**
     * 用户列表
     * @param sysUserQueryBean
     * @return
     */
    @Override
    public List<JSONObject> listUser3(SysUserQueryBean sysUserQueryBean) {
        List<JSONObject> list = userDao.listUser3(sysUserQueryBean);
        //JSONObject info = new JSONObject();
       // info.put("list", list);  // TODO 集合外面又封装了一层，最终又返回的是一个json对象
        //logger.info("查询出的用户列表为：{}",info.toString());
        return list;
    }

    @Override
    public Integer addUser2(SysUser sysUser) {
        return userDao.addUser2(sysUser);
    }

    @Override
    public Integer updateUser2(SysUser sysUser) {
        return userDao.updateUser2(sysUser);
    }

    @Override
    public List<SysRoleVO> getAllRoles2() {
        List<SysRoleVO> list = userDao.getAllRoles2();
        return list;
    }

    @Override
    public List<SysRoleVO2> listRole2() {
        List<SysRoleVO2> list = userDao.listRole2();
        return list;
    }

    @Override
    public List<JSONObject> listRole3() {
        List<JSONObject> list = userDao.listRole3();
        return list;
    }

    @Override
    public List<SysPermissionVO> listAllPermission2() {
        List<SysPermissionVO> list = userDao.listAllPermission2();
        return list;
    }

    @Override
    public List<JSONObject> listAllPermission3() {
        List<JSONObject> list = userDao.listAllPermission3();
        return list;
    }


    /**
     * 添加角色
     *  TODO 添加角色+添加角色的权限
     *
     */
    @Transactional(rollbackFor = Exception.class)
    @SuppressWarnings("unchecked")
    @Override
    public void addRole2(SysRolePermissionDTO sysRolePermissionDTO) {
        userDao.insertRole2(sysRolePermissionDTO);
        userDao.insertRolePermission2(sysRolePermissionDTO.getRoleId(),  sysRolePermissionDTO.getPermissions());
    }

    /**
     * 修改角色
     */
    @Transactional(rollbackFor = Exception.class)
    @SuppressWarnings("unchecked")
    @Override
    public void updateRole2(SysRolePermissionDTO sysRolePermissionDTO) {
        Integer roleId = sysRolePermissionDTO.getRoleId(); //获取角色ID
        List<Integer> newPerms = sysRolePermissionDTO.getPermissions(); //获取勾选的权限 新的权限ID集合
        List<SysRolePermissionDTO2> roleInfo = userDao.getRoleAllInfo2(sysRolePermissionDTO);  //TODO 这里的返回值不能为list集合
        logger.info("获取的对象为：{}",roleInfo.toString());
        Set<Integer> oldPerms =new HashSet<>();
        for(SysRolePermissionDTO2 roleInfo2:roleInfo ){  // TODO 注意这里的逻辑判断 而且是逻辑删除，不是物理删除
            oldPerms.add( roleInfo2.getPermissionId()); //旧的权限ID集合
        }
      //  Set<Integer> oldPerms = roleInfo.getPermissionIds();  //旧的权限ID集合
        //修改角色名称
        dealRoleName2(sysRolePermissionDTO, roleInfo);
        //添加新权限
        saveNewPermission2(roleId, newPerms, oldPerms);
        //移除旧的不再拥有的权限
        removeOldPermission2(roleId, newPerms, oldPerms);
    }

    /**
     * 修改角色名称
     */
    private void dealRoleName2(SysRolePermissionDTO paramJson, List<SysRolePermissionDTO2> roleInfo) {
        String roleName = paramJson.getRoleName();
        for(SysRolePermissionDTO2 roleInfo2:roleInfo ){  //TODO 这里不能循环更新
            if (!roleName.equals(roleInfo2.getRoleName())) {
                userDao.updateRoleName2(paramJson);
            }
        }

    }

    /**
     * 为角色添加新权限
     */
    private void saveNewPermission2(Integer roleId, Collection<Integer> newPerms, Collection<Integer> oldPerms) {
        List<Integer> waitInsert = new ArrayList<>();
        for (Integer newPerm : newPerms) {  //遍历新的ID集合
            if (!oldPerms.contains(newPerm)) { //如果旧的ID集合不包含新的勾选的ID,则将新的勾选的ID添加到集合
                waitInsert.add(newPerm);
            }
        }
        if (waitInsert.size() > 0) { //集合有元素，即新勾选增加了新的权限ID
            userDao.insertRolePermission2(roleId, waitInsert);
        }
    }

    /**
     * 删除角色 旧的 不再拥有的权限
     */
    private void removeOldPermission2(Integer roleId, Collection<Integer> newPerms, Collection<Integer> oldPerms) {
        List<Integer> waitRemove = new ArrayList<>();
        for (Integer oldPerm : oldPerms) {
            if (!newPerms.contains(oldPerm)) {//如果旧的ID集合不包含新的勾选的ID，则将旧的ID移除掉
                waitRemove.add(oldPerm);
            }
        }
        if (waitRemove.size() > 0) {
            userDao.removeOldPermission2(roleId, waitRemove);
        }
    }

    /**
     * 删除角色
     */
    @Transactional(rollbackFor = Exception.class)
    @SuppressWarnings("unchecked")
    @Override
    public void deleteRole2(SysRolePermissionDTO sysRolePermissionDTO) {
       // JSONObject roleInfo = userDao.getRoleAllInfo2(jsonObject);
        List<SysRolePermissionDTO2> roleInfo = userDao.getRoleAllInfo2(sysRolePermissionDTO);
//        if (roleInfo != null && roleInfo.size() > 0) {
//            return CommonUtil.errorJson(ErrorEnum.E_10008);
//        }
            for(SysRolePermissionDTO2 roleInfo2:roleInfo ){   //TODO 不要循环删除  SysRolePermissionDTO2 这个对象需要改造
                userDao.removeRole2(roleInfo2.getRoleId());
                userDao.removeRoleAllPermission2(roleInfo2.getRoleId());
            }


//        List<JSONObject> users = (List<JSONObject>) roleInfo.get("users");
//        if (users != null && users.size() > 0) {
//            return CommonUtil.errorJson(ErrorEnum.E_10008);
//        }
//        userDao.removeRole2(jsonObject);
//        userDao.removeRoleAllPermission2(jsonObject);
//        return CommonUtil.successJson();
    }
}
