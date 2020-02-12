package com.heeexy.example.util.model.utilBean;

/**
 * 请求体参数
 * 
 * @author DaiSy
 *
 */
public class RequestBean {
	/**
	 * 服务标识 (service_code)
	 */
	private String busicode;
	/**
	 * 系统类型(system_code)
	 */
	private String sysType;
	/**
	 * 请求页面地址
	 */
	private String requestUrl;

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	/**
	 * token
	 */
	private String token;
	/**
	 * json数组对象，根据具体业务确定内容
	 */
	private Object data;

	public String getBusicode() {
		return busicode;
	}

	public void setBusicode(String busicode) {
		this.busicode = busicode;
	}

	public String getSysType() {
		return sysType;
	}

	public void setSysType(String sysType) {
		this.sysType = sysType;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "RequestBean [busicode=" + busicode + ", sysType=" + sysType + ", requestUrl=" + requestUrl + ", token="
				+ token + ", data=" + data + "]";
	}

}
