package com.heeexy.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.heeexy.example.dao.ArticleDao;
import com.heeexy.example.service.ArticleService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.model.Article;
import com.heeexy.example.util.model.query.ArticleQueryBean;
import com.heeexy.example.util.model.vo.ArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: hxy
 * @date: 2017/10/24 16:07
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDao articleDao;

	/**
	 * 新增文章
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject addArticle(JSONObject jsonObject) {
		articleDao.addArticle(jsonObject);
		return CommonUtil.successJson();
	}

	/**
	 * 文章列表
	 */
	@Override
	public JSONObject listArticle(JSONObject jsonObject) {
		CommonUtil.fillPageParam(jsonObject);  //分页处理
		int count = articleDao.countArticle(jsonObject);//总纪录数
		List<JSONObject> list = articleDao.listArticle(jsonObject);//集合
		return CommonUtil.successPage(jsonObject, list, count);
	}

	/**
	 * 更新文章
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject updateArticle(JSONObject jsonObject) {
		articleDao.updateArticle(jsonObject);
		return CommonUtil.successJson();
	}

    /**
     * 文章列表
     * @param articleQueryBean
     * @return
     */
    @Override
    public List<ArticleVO> listArticle2(ArticleQueryBean articleQueryBean) {
        List<ArticleVO> list = articleDao.listArticle2(articleQueryBean);
        return list;
    }

    @Override
    public Integer addArticle2(Article bean) {
        return articleDao.addArticle2(bean);
    }

    @Override
    public Integer updateArticle2(Article bean) {
        return articleDao.updateArticle2(bean);
    }

    @Override
    public void deleteArticle2(ArticleQueryBean bean) {
        articleDao.deleteArticle2(bean);
    }



}
