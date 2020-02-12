package com.heeexy.example.service;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.util.model.Article;
import com.heeexy.example.util.model.query.ArticleQueryBean;
import com.heeexy.example.util.model.vo.ArticleVO;
import lombok.val;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: hxy
 * @date: 2017/10/24 16:06
 */
public interface ArticleService {
	/**
	 * 新增文章
	 */
	JSONObject addArticle(JSONObject jsonObject);

    /**
     * 文章列表
     */
    JSONObject listArticle(JSONObject jsonObject);
	/**
	 * 更新文章
	 */
	JSONObject updateArticle(JSONObject jsonObject);

	//#######################################################

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

//    /**
//     * 校验字段内容重复
//     */
//    @Select("select count(*) from article where ${key} = #{val}")
//    Integer check(@Param("key") String key, @Param("val")Object val);
//
//    /**
//     * 校验字段内容重复-排除当前记录
//     */
//    @Select("select count(*) from article where ${key} = #{val} and id <> #{id}")
//    Integer check2(@Param("key") String key, @Param("val") String val, @Param("id") Integer id);
//
//    /**
//     * 校验id数据是否存在
//     */
//    @Select("select count(*) from article where id = #{id}")
//    Integer check3( @Param("id") Integer id);
}
