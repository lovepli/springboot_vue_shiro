package com.heeexy.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.heeexy.example.service.ArticleService;
import com.heeexy.example.util.CommonUtil;
import com.heeexy.example.util.JsonUtils;
import com.heeexy.example.util.constants.Constants;
import com.heeexy.example.util.model.Article;
import com.heeexy.example.util.model.query.ArticleQueryBean;
import com.heeexy.example.util.model.utilBean.MessageBean;
import com.heeexy.example.util.model.utilBean.RequestBean;
import com.heeexy.example.util.model.vo.ArticleVO;
import com.sun.corba.se.spi.ior.ObjectId;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author: hxy
 * @description: 文章相关Controller
 * @date: 2017/10/24 16:04
 */
@RestController
@RequestMapping("/article2")
public class ArticleController2 {

	@Autowired
	private ArticleService articleService;

    private static Logger logger = LoggerFactory.getLogger(ArticleController2.class);

	/**
	 * 查询文章列表
	 */
	@RequiresPermissions("article:list")
	@GetMapping("/listArticle2")
	public String listArticle(ArticleQueryBean articleQueryBean) {

        MessageBean<PageInfo> info = MessageBean.create(Constants.MESSAGE_INT_SUCCESS, "获取操作日志列表", PageInfo.class);

        List<ArticleVO> data = null;
        try {
            PageHelper.startPage(articleQueryBean.getPage(), articleQueryBean.getPageCount());

            data =articleService.listArticle2(articleQueryBean);
            if (data == null || data.size() == 0) {
                logger.info("没有查询到符合条件的数据！");
            }
        } catch (Exception e) {
            logger.error("", e);
            info.setCode(Constants.ILLEGAL_PARAMETER);
            info.setDescription("操作失败");
        }
        info.setData(new PageInfo<ArticleVO>(data));
        return JsonUtils.objectToJson(info);
	}

    /**
     * 新增文章
     */
    @RequiresPermissions("article:add")
    @PostMapping("/addArticle2")
    public MessageBean<?> addArticle(@RequestBody RequestBean req) {
        logger.info("请求的参数为：{}",req.toString());

        Article bean = null;
        try {

            bean = JsonUtils.objectToPojo(req.getData(), Article.class);

            //设置操作人信息
           // bean.setCreateName(userInfo.getUserInfo().getName());
           // bean.setCreateTime(CommonUtils.getCurrentTime());
           // bean.setAccessoryTypeId(new ObjectId().toHexString());

            // 校验字段重复 不添加ID重复的文章 ，这里ID是自增长的，所以不用校验
//            if (articleService.check("id", bean.getId()) > 0) {
//                return MessageBean.create(Constants.ILLEGAL_PARAMETER, "编号：" + bean.getId() + "的信息已存在。",
//                        void.class);
//            }
//            //校验字段重复 不添加文章内容重复的文章
//            if (articleService.check("content", bean.getContent()) > 0) {
//                return MessageBean.create(Constants.ILLEGAL_PARAMETER, "文章：" + bean.getContent() + "的信息已存在。",
//                        void.class);
//            }
            articleService.addArticle2(bean);
        } catch (Exception e) {
            logger.error("非法参数",e);
            return MessageBean.create(Constants.ILLEGAL_PARAMETER, "非法参数", void.class);
        }
        return MessageBean.create(Constants.MESSAGE_INT_SUCCESS, "success", void.class);
    }

    /**
     * 修改文章
     */
    @RequiresPermissions("article:update")
    @PostMapping("/updateArticle2")
    public MessageBean<?>  updateArticle(@RequestBody RequestBean req) {

        Article bean = null;
        try {
            bean = JsonUtils.objectToPojo(req.getData(), Article.class);

           // bean.setUpdateName(userInfo.getUserInfo().getName());
            //bean.setUpdateTime(CommonUtils.getCurrentTime());
            if (bean.getId() == null) {
                return MessageBean.create(Constants.ILLEGAL_PARAMETER, "id为空", void.class);
            }
            //校验字段重复 不修改文章ID不存在的文章
//            if (articleService.check3(bean.getId()) == 0) { // 存在则>0 不存在则=0
//                return MessageBean.create(Constants.ILLEGAL_PARAMETER, "id不存在",
//                        void.class);
//            }
//            // 校验字段重复
//            if (articleService.check2("content", bean.getContent(), bean.getId()) > 0) {
//                return MessageBean.create(Constants.ILLEGAL_PARAMETER, "文章：" + bean.getContent() + "的信息已存在。",
//                        void.class);
//            }
            articleService.updateArticle2(bean);
        } catch (Exception e) {
            logger.error("非法参数",e);
            return MessageBean.create(Constants.ILLEGAL_PARAMETER, "非法参数", void.class);
        }
        return MessageBean.create(Constants.MESSAGE_INT_SUCCESS, "success", void.class);
    }

    /**
     * 删除文章
     */
    @RequiresPermissions("article:update")
    @PostMapping("/deleteArticle2")
    public MessageBean<?>  deleteArticle(@RequestBody RequestBean req) {

        MessageBean<List> info = MessageBean.create(Constants.MESSAGE_INT_SUCCESS, "success", List.class);
        ArticleQueryBean bean = null;
        try {

            bean = JsonUtils.objectToPojo(req.getData(), ArticleQueryBean.class);
            // if(StringUtils.isBlank(bean.getId())){  //字符串判断
            if (bean.getId() == null) {  //TODO 这种验证主要是防止前端没有传id参数
                return MessageBean.create(Constants.ILLEGAL_PARAMETER, "id为空", void.class);
            }
            //校验字段重复 不删除文章ID不存在的文章
//            if (articleService.check3(bean.getId()) == 0) { // 存在则>0 不存在则=0
//                return MessageBean.create(Constants.ILLEGAL_PARAMETER, "id不存在",
//                        void.class);
//            }
            articleService.deleteArticle2(bean);
            info.setCode(Constants.MESSAGE_INT_SUCCESS);
            info.setDescription("success");
        } catch (Exception e) {
            info.setCode(Constants.MESSAGE_DBFAIL);
            info.setDescription("数据库异常");
            logger.error("数据库异常", e);
        }
        return info;
    }

}
