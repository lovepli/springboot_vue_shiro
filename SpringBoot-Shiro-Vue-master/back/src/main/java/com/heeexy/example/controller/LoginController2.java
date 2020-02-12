package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.service.LoginService;
import com.heeexy.example.util.JsonUtils;
import com.heeexy.example.util.constants.Constants;
import com.heeexy.example.util.model.utilBean.MessageBean;
import com.heeexy.example.util.model.utilBean.RequestBean;
import com.heeexy.example.util.model.vo.SysRoleVO;
import com.heeexy.example.util.model.vo.SysUserDTO;
import com.heeexy.example.util.model.vo.UserPermissionDTO;
import com.heeexy.example.util.model.vo.UserPermissionDTO2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: hxy
 * @description: 登录相关Controller
 * @date: 2017/10/24 10:33
 */
@RestController
@RequestMapping("/login2")
public class LoginController2 {

	@Autowired
	private LoginService loginService;

    private static Logger logger = LoggerFactory.getLogger(LoginController2.class);

	/**
	 * 登录
     * @return
     */
	@PostMapping("/auth2")
	public MessageBean<Void> authLogin2(@RequestBody RequestBean req){

        logger.info("请求的参数为：{}",req.toString());
        SysUserDTO bean = null;
        try {
            bean = JsonUtils.objectToPojo(req.getData(), SysUserDTO.class);
        } catch (Exception e) {
            logger.error("非法参数",e);
        }
        return loginService.authLogin2(bean);
	}

	/**
	 * 查询当前登录用户的信息
	 */
	@PostMapping("/getInfo2")
	public  MessageBean<?> getInfo() {

        MessageBean<JSONObject> info = MessageBean.create(Constants.MESSAGE_INT_SUCCESS, "success", JSONObject.class);

        JSONObject data = null;
        try {
            data =  loginService.getInfo2();
            info.setData(data);
        } catch (Exception e) {
            info.setCode(Constants.MESSAGE_INT_FAIL);
            info.setDescription(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return info;
	}


	/**
	 * 登出
	 */
	@PostMapping("/logout2")
	public MessageBean<Void>  logout2() {
		return loginService.logout2();
	}
}
