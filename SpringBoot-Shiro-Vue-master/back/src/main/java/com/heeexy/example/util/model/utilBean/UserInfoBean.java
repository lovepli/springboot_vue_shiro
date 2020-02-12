package com.heeexy.example.util.model.utilBean;

import java.util.List;

/**
* 登陆会话信息
* @author 周新宇
* @version 创建时间：2018年9月27日 下午6:35:09
*/
public class UserInfoBean {
	/**
	 * 001表示运维平台，002营收系统，003服务分析系统
	 */
	private String curService;

    /**
     * 业务平台用户信息
     */
	//private ServicePtUserBean userInfo;
	
	private String curWaterCode;

	private List<String> waterCode;
	
	public String getCurService() {
		return curService;
	}

	public void setCurService(String curService) {
		this.curService = curService;
	}

//	public ServicePtUserBean getUserInfo() {
//		return userInfo;
//	}
//
//	public void setUserInfo(ServicePtUserBean userInfo) {
//		this.userInfo = userInfo;
//	}

	public String getCurWaterCode() {
		return curWaterCode;
	}

	public void setCurWaterCode(String curWaterCode) {
		this.curWaterCode = curWaterCode;
	}

	public List<String> getWaterCode() {
		return waterCode;
	}

	public void setWaterCode(List<String> waterCode) {
		this.waterCode = waterCode;
	}

}
