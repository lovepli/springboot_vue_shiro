package com.heeexy.example.dao;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.util.model.Article;
import com.heeexy.example.util.model.query.ArticleQueryBean;
import com.heeexy.example.util.model.vo.ArticleVO;

import java.util.List;

/**
 * @author: hxy
 * @description: 文章Dao层
 * @date: 2017/10/24 16:06
 */
public interface ArticleDao {
	/**
	 * 新增文章
	 */
	int addArticle(JSONObject jsonObject);

	/**
	 * 统计文章总数
	 */
	int countArticle(JSONObject jsonObject);

	/**
	 * 文章列表
	 */
	List<JSONObject> listArticle(JSONObject jsonObject);

	/**
	 * 更新文章
	 */
	int updateArticle(JSONObject jsonObject);


    /**
     * 文章列表
     */
    List<ArticleVO> listArticle2(ArticleQueryBean articleQueryBean);

    /**
     * 新增文章
     */
    Integer addArticle2(Article bean);

    /**
     * 更新文章
     */
    Integer updateArticle2(Article bean);

    /**
     *  删除文章
     */
    void deleteArticle2(ArticleQueryBean bean);
}
