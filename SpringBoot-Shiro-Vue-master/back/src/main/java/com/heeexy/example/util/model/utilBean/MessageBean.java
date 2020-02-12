package com.heeexy.example.util.model.utilBean;

/**
 * @author: lipan
 * @date: 2019-12-03
 * @description:
 */

import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <pre>
 * 消息bean
 * code
 * 0为正确
 * 1为未被处理(未被安装)
 * 10000+为业务信息
 * </pre>
 *            统一API响应结果封装
 * @author 方志文
 */
@XmlRootElement(name = "message")
public class MessageBean<T> {
    /**
     * <pre>
     * 0正确
     * 1信息未被处理(模块未被安装)
     * </pre>
     */
    private int code;
    /**
     * 描述
     */
    private String description;
    /**
     * 调用后返回的令牌，通过此令牌可以获取异步执行功能的结果
     */
    private String token;
    /**
     * 处理状态
     */
    private int status;
    /**
     * 数据
     */
    private T data;

    /**
     * 创建自定义MessageBean
     *
     * @param code
     * @param description
     * @return
     */
    public static <T> MessageBean<T> create(int code, String description, Class<T> clazz) {
        MessageBean<T> bean = new MessageBean<T>();
        bean.setCode(code);
        bean.setDescription(description);
        return bean;
    }

    /**
     * 获取信息编码
     *
     * @return
     */
    @XmlElement(name = "code")
    public int getCode() {
        return code;
    }

    /**
     * 设置信息编码
     *
     * @param code 编码
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @return 获取描述
     */
    @XmlElement(name = "description")
    public String getDescription() {
        return description;
    }

    /**
     * @param description 设置描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return 获取数据
     */
    @XmlElement(name = "data")
    public T getData() {
        return data;
    }

    /**
     * @param data 设置数据
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * @return 获取调用后返回的令牌，通过此令牌可以获取异步执行功能的结果
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token 设置调用后返回的令牌，通过此令牌可以获取异步执行功能的结果
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return 获取处理状态
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status 设置处理状态
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "MessageBean [code=" + code + ", description=" + description + ", token=" + token + ", status=" + status
                + ", data=" + data + "]";
    }

    /**
     * 获取JSON字符串
     *
     * @return
     */
    public String toJson() {
        return new GsonBuilder()
                .setLongSerializationPolicy(LongSerializationPolicy.STRING)
                .setDateFormat("yyyy-MM-dd HH:mm:ss SSS")
                .create().toJson(this);
    }
}