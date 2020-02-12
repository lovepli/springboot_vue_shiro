package com.heeexy.example.util.model.utilBean;

import com.heeexy.example.util.constants.Constants;


/**
 * 响应参数表
 * @author DaiSy
 *
 */
public class ResponseBean {

	/**
	 * 成功：0，失败：-1，未登陆：-10000
	 */
	public int code = Constants.MESSAGE_INT_SUCCESS;
	/**
	 * success", 成功：success，失败：错误信息
	 */
	public String description ="success";
	/**
	 * json数组对象，根据具体业务确定内容
	 */
	public Object data;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
