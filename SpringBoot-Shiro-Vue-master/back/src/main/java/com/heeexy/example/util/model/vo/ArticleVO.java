package com.heeexy.example.util.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author: lipan
 * @date: 2020-02-02
 * @description:
 */
@Data
public class ArticleVO {


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
     * 数据库表字段类型为timestamp
     *
     */
    private String createTime;
    /**
     *更新时间
     */
    private String  updateTime;
    /**
     *是否有效  1.有效  2无效
     */
    private String deleteStatus;
}
