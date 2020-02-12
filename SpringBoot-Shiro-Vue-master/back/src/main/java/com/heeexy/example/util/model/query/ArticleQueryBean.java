package com.heeexy.example.util.model.query;

import com.heeexy.example.util.model.utilBean.BaseQueryBean;
import lombok.Data;

import java.util.Date;

/**
 * @author: lipan
 * @date: 2020-02-02
 * @description:
 */
@Data
public class ArticleQueryBean extends BaseQueryBean {
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
