package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heeexy.example.service.UserService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.JsonUtils;
import com.heeexy.example.util.constants.Constants;
import com.heeexy.example.util.model.Article;
import com.heeexy.example.util.model.SysRole;
import com.heeexy.example.util.model.SysUser;
import com.heeexy.example.util.model.query.ArticleQueryBean;
import com.heeexy.example.util.model.query.SysUserQueryBean;
import com.heeexy.example.util.model.utilBean.MessageBean;
import com.heeexy.example.util.model.utilBean.RequestBean;
import com.heeexy.example.util.model.vo.*;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: hxy
 * @description: 用户/角色/权限相关controller
 * @date: 2017/11/2 10:19
 */
@RestController
@RequestMapping("/user2")
public class UserController2 {
	@Autowired
	private UserService userService;

    private static Logger logger = LoggerFactory.getLogger(UserController2.class);


    /**
     * 查询用户列表 分页
     *  TODO 这种返回的json数据格式不满足要求
     */
    @RequiresPermissions("user:list")
    @GetMapping("/list2")
    public String listUser2(SysUserQueryBean sysUserQueryBean) {

        MessageBean<PageInfo> info = MessageBean.create(Constants.MESSAGE_INT_SUCCESS, "获取用户信息列表", PageInfo.class);

        List<SysUserVO> data = null;
        try {
            PageHelper.startPage(sysUserQueryBean.getPage(), sysUserQueryBean.getPageCount());

            data =userService.listUser2(sysUserQueryBean);
            if (data == null || data.size() == 0) {
                logger.info("没有查询到符合条件的数据！");
            }
        } catch (Exception e) {
            logger.error("", e);
            info.setCode(Constants.ILLEGAL_PARAMETER);
            info.setDescription("操作失败");
        }
        info.setData(new PageInfo<SysUserVO>(data));
        return JsonUtils.objectToJson(info);
    }

    /**
     * 查询用户列表 分页
     */
    @RequiresPermissions("user:list")
    @GetMapping("/list3")
    public String listUser3(SysUserQueryBean sysUserQueryBean) {

        MessageBean<PageInfo> info = MessageBean.create(Constants.MESSAGE_INT_SUCCESS, "获取用户信息列表", PageInfo.class);

        List<JSONObject> data = null;
        try {
            PageHelper.startPage(sysUserQueryBean.getPage(), sysUserQueryBean.getPageCount());

            data =userService.listUser3(sysUserQueryBean);
            if (data == null || data.size() == 0) {
                logger.info("没有查询到符合条件的数据！");
            }
        } catch (Exception e) {
            logger.error("", e);
            info.setCode(Constants.ILLEGAL_PARAMETER);
            info.setDescription("操作失败");
        }
        info.setData(new PageInfo<>(data));
        return JsonUtils.objectToJson(info);
    }


    /**
     * 新增角色
     */
	@RequiresPermissions("user:add")
	@PostMapping("/addUser2")
	public MessageBean<?> addUser2(@RequestBody RequestBean req) {
        //前端传递四个参数，下拉框选择管理员之后就确定了roleId
		//CommonUtil.hasAllRequired(requestJson, "username, password, nickname,   roleId");
        logger.info("请求的参数为：{}",req.toString());
        SysUser bean = null;
        try {
            bean = JsonUtils.objectToPojo(req.getData(), SysUser.class);
            //设置操作人信息
            // bean.setCreateName(userInfo.getUserInfo().getName());
            // bean.setCreateTime(CommonUtils.getCurrentTime());
            // bean.setAccessoryTypeId(new ObjectId().toHexString());
            userService.addUser2(bean);
        } catch (Exception e) {
            logger.error("非法参数",e);
            return MessageBean.create(Constants.ILLEGAL_PARAMETER, "非法参数", void.class);
        }
        return MessageBean.create(Constants.MESSAGE_INT_SUCCESS, "success", void.class);
	}

    /**
     * 修改角色
     */
    @RequiresPermissions("user:update")
    @PostMapping("/updateUser2")
    public MessageBean<?> updateUser2(@RequestBody RequestBean req) {
        //CommonUtil.hasAllRequired(requestJson, " nickname,   roleId, deleteStatus, userId");
        SysUser bean = null;
        try {
            bean = JsonUtils.objectToPojo(req.getData(), SysUser.class);

            // bean.setUpdateName(userInfo.getUserInfo().getName());
            //bean.setUpdateTime(CommonUtils.getCurrentTime());
            if (bean.getId() == null) {
                return MessageBean.create(Constants.ILLEGAL_PARAMETER, "id为空", void.class);
            }
            userService.updateUser2(bean);
        } catch (Exception e) {
            logger.error("非法参数",e);
            return MessageBean.create(Constants.ILLEGAL_PARAMETER, "非法参数", void.class);
        }
        return MessageBean.create(Constants.MESSAGE_INT_SUCCESS, "success", void.class);
    }


    /**
     * 查询所有角色 下拉框
     * @return
     */
    @RequiresPermissions(value = {"user:add", "user:update"}, logical = Logical.OR)
    @GetMapping("/getAllRoles2")
    public MessageBean<?> getAllRoles2() {

        MessageBean<List> info = MessageBean.create(Constants.MESSAGE_INT_SUCCESS, "success", List.class);

        List<SysRoleVO> list = null;
        try {
           list =  userService.getAllRoles2();
            info.setData(list);
        } catch (Exception e) {
            info.setCode(Constants.MESSAGE_INT_FAIL);
            info.setDescription(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return info;
    }

	/**
	 * 角色列表
	 */
	@RequiresPermissions("role:list")
	@GetMapping("/listRole2")
	public MessageBean<?> listRole2() {

        MessageBean<List> info = MessageBean.create(Constants.MESSAGE_INT_SUCCESS, "success", List.class);

        List<SysRoleVO2> list = null;
        try {
            list =  userService.listRole2();
            info.setData(list);
        } catch (Exception e) {
            info.setCode(Constants.MESSAGE_INT_FAIL);
            info.setDescription(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return info;
	}

    /**
     * 角色列表不分页  TODO 返回数据的封装不正确
     */
    @RequiresPermissions("role:list")
    @GetMapping("/listRole3")
    public MessageBean<?> listRole3() {

        MessageBean<List> info = MessageBean.create(Constants.MESSAGE_INT_SUCCESS, "success", List.class);

        List<JSONObject> list = null;
        try {
            list =  userService.listRole3();
            info.setData(list);
        } catch (Exception e) {
            info.setCode(Constants.MESSAGE_INT_FAIL);
            info.setDescription(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return info;
    }

    /**
     * 查询所有权限, 给角色分配权限时调用  TODO 返回json格式不正确
     */
    @RequiresPermissions("role:list")
    @GetMapping("/listAllPermission2")
    public MessageBean<?> listAllPermission2() {

        MessageBean<List> info = MessageBean.create(Constants.MESSAGE_INT_SUCCESS, "success", List.class);

        List<SysPermissionVO> list = null;
        try {
            list =  userService.listAllPermission2();
            info.setData(list);
        } catch (Exception e) {
            info.setCode(Constants.MESSAGE_INT_FAIL);
            info.setDescription(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return info;
    }

    /**
     * 查询所有权限, 给角色分配权限时调用  TODO 返回json格式不正确
     */
    @RequiresPermissions("role:list")
    @GetMapping("/listAllPermission3")
    public MessageBean<?> listAllPermission3() {

        MessageBean<List> info = MessageBean.create(Constants.MESSAGE_INT_SUCCESS, "success", List.class);

        List<JSONObject> list = null;
        try {
            list =  userService.listAllPermission3();
            info.setData(list);
        } catch (Exception e) {
            info.setCode(Constants.MESSAGE_INT_FAIL);
            info.setDescription(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return info;
    }

    /**
     * 新增角色
     * 添加角色+添加角色的权限
     */
    @RequiresPermissions("role:add")
    @PostMapping("/addRole2")
    public MessageBean<?> addRole2(@RequestBody RequestBean req) {
        //验证是否含有所有比填字段
       // CommonUtil.hasAllRequired(requestJson, "roleName,permissions");
        logger.info("请求的参数为：{}",req.toString());
        SysRolePermissionDTO bean = null;
        try {
            bean = JsonUtils.objectToPojo(req.getData(), SysRolePermissionDTO.class);
            //设置操作人信息
            // bean.setCreateName(userInfo.getUserInfo().getName());
            // bean.setCreateTime(CommonUtils.getCurrentTime());
            // bean.setAccessoryTypeId(new ObjectId().toHexString());
            userService.addRole2(bean);
        } catch (Exception e) {
            logger.error("非法参数",e);
            return MessageBean.create(Constants.ILLEGAL_PARAMETER, "非法参数", void.class);
        }
        return MessageBean.create(Constants.MESSAGE_INT_SUCCESS, "success", void.class);
    }


    /**
     * 修改角色
     */
    @RequiresPermissions("role:update")
    @PostMapping("/updateRole2")
    public MessageBean<?> updateRole2(@RequestBody RequestBean req) {
       // CommonUtil.hasAllRequired(requestJson, "roleId,roleName,permissions");

        SysRolePermissionDTO bean = null;
        try {
            bean = JsonUtils.objectToPojo(req.getData(), SysRolePermissionDTO.class);

            // bean.setUpdateName(userInfo.getUserInfo().getName());
            //bean.setUpdateTime(CommonUtils.getCurrentTime());
            if (bean.getRoleId() == null) {
                return MessageBean.create(Constants.ILLEGAL_PARAMETER, "id为空", void.class);
            }
            userService.updateRole2(bean);
        } catch (Exception e) {
            logger.error("非法参数",e);
            return MessageBean.create(Constants.ILLEGAL_PARAMETER, "非法参数", void.class);
        }
        return MessageBean.create(Constants.MESSAGE_INT_SUCCESS, "success", void.class);
    }


    /**
     * 删除角色
     */
    @RequiresPermissions("role:delete")
    @PostMapping("/deleteRole2")
    public MessageBean<?> deleteRole2(@RequestBody RequestBean req) {
        //CommonUtil.hasAllRequired(requestJson, "roleId");
       // return userService.deleteRole(requestJson);

        MessageBean<List> info = MessageBean.create(Constants.MESSAGE_INT_SUCCESS, "success", List.class);
        SysRolePermissionDTO bean = null;
        try {
            bean = JsonUtils.objectToPojo(req.getData(), SysRolePermissionDTO.class);
            // if(StringUtils.isBlank(bean.getId())){  //字符串判断
            if (bean.getRoleId() == null) {  //TODO 这种验证主要是防止前端没有传id参数
                return MessageBean.create(Constants.ILLEGAL_PARAMETER, "id为空", void.class);
            }
            //校验字段重复 不删除文章ID不存在的文章
//            if (articleService.check3(bean.getId()) == 0) { // 存在则>0 不存在则=0
//                return MessageBean.create(Constants.ILLEGAL_PARAMETER, "id不存在",
//                        void.class);
//            }
            userService.deleteRole2(bean);
            info.setCode(Constants.MESSAGE_INT_SUCCESS);
            info.setDescription("success");
        } catch (Exception e) {
            info.setCode(Constants.MESSAGE_DBFAIL);
            info.setDescription("数据库异常");
            logger.error("数据库异常", e);
        }
        return info;
    }
}
