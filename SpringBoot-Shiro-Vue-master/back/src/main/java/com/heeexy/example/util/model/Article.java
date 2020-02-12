package com.heeexy.example.util.model;

import lombok.Data;

import java.util.Date;

/**
 * @author: lipan
 * @date: 2020-01-30
 * @description:  发布号作者表
 */
@Data
public class Article {

    /**
     *主键
     */
    private Integer id;
    /**
     *文章内容
     */
    private String content;
    /**
     *创建时间
     */
    private Date  createTime;
    /**
     *更新时间
     */
    private Date  updateTime;
    /**
     *是否有效  1.有效  2无效
     */
    private String deleteStatus;

}
