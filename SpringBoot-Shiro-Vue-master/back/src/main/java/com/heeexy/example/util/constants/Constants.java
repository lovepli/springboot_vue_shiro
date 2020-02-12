package com.heeexy.example.util.constants;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: hxy
 * @description: 通用常量类, 单个业务的常量请单开一个类, 方便常量的分类管理
 * @date: 2017/10/24 10:15
 */
public class Constants {

	public static final String SUCCESS_CODE = "100";
	public static final String SUCCESS_MSG = "请求成功";

	/**
	 * session中存放用户信息和用户权限的key值
	 */
	public static final String SESSION_USER_INFO = "userInfo";
	public static final String SESSION_USER_PERMISSION = "userPermission";

    /**
     * 	3。redis缓存中，数据的存放key值
     */


    // ////////////////////////4。接口错误说明/////////////
    /**
     * 操作成功
     */
    public static final int MESSAGE_INT_SUCCESS = 0;
    /**
     * 操作失败
     */
    public static final int MESSAGE_INT_FAIL = 1;

    /**
     * 服务器异常
     */
    public static final int SERVER_EXCEPTION = 11201;

    /**
     * 非法参数
     */
    public static final int ILLEGAL_PARAMETER = 11205;


    /**
     * 分配100个错误吗给数据库
     */
    public static final int MESSAGE_DBFAIL = 20101;

    /**
     * 自定义层别缓存
     */
    public static final HashMap<String, Integer> layerCache = new HashMap<>();

    public static final Map<Object,Object> cache = new ConcurrentHashMap<Object,Object>();

}
