package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.controller.LoginController2;
import com.heeexy.example.dao.LoginDao;
import com.heeexy.example.service.LoginService;
import com.heeexy.example.service.PermissionService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.constants.Constants;
import com.heeexy.example.util.model.utilBean.MessageBean;
import com.heeexy.example.util.model.vo.SysUserDTO;
import com.heeexy.example.util.model.vo.UserPermissionDTO;
import com.heeexy.example.util.model.vo.UserPermissionDTO2;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: hxy
 * @description: 登录service实现类
 * @date: 2017/10/24 11:53
 */
@Service
public class LoginServiceImpl implements LoginService {

    private static Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Autowired
	private LoginDao loginDao;
	@Autowired
	private PermissionService permissionService;

	/**
	 * 登录表单提交
	 */
	@Override
	public JSONObject authLogin(JSONObject jsonObject) {
		String username = jsonObject.getString("username");
		String password = jsonObject.getString("password");
		JSONObject info = new JSONObject();
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			currentUser.login(token);
			info.put("result", "success");
		} catch (AuthenticationException e) {
			info.put("result", "fail");
		}
		return CommonUtil.successJson(info);
	}

	/**
	 * 根据用户名和密码查询对应的用户
	 */
	@Override
	public JSONObject getUser(String username, String password) {
		return loginDao.getUser(username, password);
	}

	/**
	 * 查询当前登录用户的权限等信息
	 */
	@Override
	public JSONObject getInfo() {
		//从session获取用户信息
		Session session = SecurityUtils.getSubject().getSession();
		JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
        //logger.info("从session获取用户信息为：{}",userInfo.toString());
		String username = userInfo.getString("username");
		JSONObject info = new JSONObject();
		JSONObject userPermission = permissionService.getUserPermission(username);
        //logger.info("222222查询到的用户权限为：{}",userPermission.toString());
		session.setAttribute(Constants.SESSION_USER_PERMISSION, userPermission);
		info.put("userPermission", userPermission);
		return CommonUtil.successJson(info);
	}

	/**
	 * 退出登录
	 */
	@Override
	public JSONObject logout() {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			currentUser.logout();
		} catch (Exception e) {
		}
		return CommonUtil.successJson();
	}

//#######################################################################################################
    /**
     * 登录表单提交
     * @return
     */
    @Override
    public MessageBean<Void> authLogin2(SysUserDTO sysUserDTO) {
        //logger.info("请求的参数为：{}",sysUserDTO.getUserName());
        String userName = sysUserDTO.getUserName();

        String password = sysUserDTO.getPassword();
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        try {
            currentUser.login(token);
        } catch (AuthenticationException e) {
            return MessageBean.create(Constants.ILLEGAL_PARAMETER, "fail", void.class);
        }
        return MessageBean.create(Constants.MESSAGE_INT_SUCCESS, "success", void.class);
    }

    /**
     * 根据用户名和密码查询对应的用户
     */
    @Override
    public SysUserDTO getUser2(String userName, String password) {
        return loginDao.getUser2(userName, password);
    }

    /**
     * 查询当前登录用户的权限等信息
     */
    @Override
    public JSONObject getInfo2() {
        //从session获取用户信息
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);//从session中获取用户信息
        String userName = userInfo.getString("username");  //当时存的是username，所以这里写成username
        //logger.info("222222从session中查询出的用户名为：{}",userName); // TODO 这里没有从session中获取到userName
        JSONObject userPermission = permissionService.getUserPermission2(userName);  //TODO 用UserPermissionDTO2这个对象来接受是否好？
        //logger.info("返回的结果为：{}",userPermission.toString());
        session.setAttribute(Constants.SESSION_USER_PERMISSION, userPermission);//存储用户权限信息到session
        JSONObject info = new JSONObject();
        info.put("userPermission", userPermission);  //在返回的JSON字符串的结果外面再封装一层
        return info;
    }

    /**
     * 退出登录
     */
    @Override
    public MessageBean<Void> logout2() {
        try {
            Subject currentUser = SecurityUtils.getSubject();
            currentUser.logout();
        } catch (Exception e) {
            return MessageBean.create(Constants.ILLEGAL_PARAMETER, "fail", void.class);
        }
        return MessageBean.create(Constants.MESSAGE_INT_SUCCESS, "success", void.class);
    }
}
