package com.qiang.modules.sys.controller;

import com.qiang.common.utils.BlogJSONResult;
import com.qiang.common.utils.PagedResult;
import com.qiang.common.utils.TransCodingUtil;
import com.qiang.modules.sys.pojo.BlogMessage;
import com.qiang.modules.sys.pojo.es.EsBlogMessage;
import com.qiang.modules.sys.service.ArticleService;
import com.qiang.modules.sys.service.BlogService;
import com.qiang.modules.sys.service.EsService;
import org.pegdown.PegDownProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.controller
 * @Description: 文章controller
 * @Date: 2019/7/8 0008 15:26
 **/
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private EsService esService;

    /**
     * 首页分页查询文章
     * @param pageSize 一页几篇文章
     * @param pageNum 当前页
     * @return
     */
    @GetMapping("myArticles")
    public BlogJSONResult myArticles(@RequestParam(value = "pageSize") Integer pageSize,
                                     @RequestParam(value = "pageNum") Integer pageNum){
        PagedResult allBlog = articleService.findAllBlog(pageNum, pageSize);
        return BlogJSONResult.ok(allBlog);
    }


    /**
     * 文章详情
     * @return
     */
    @GetMapping("getArticleDetail")
    public BlogJSONResult getArticleDetail(@RequestParam("articleId") long articleId){
        BlogMessage byId = blogService.findById(articleId);
        String text = byId.getText();
        PegDownProcessor  peg= new PegDownProcessor();
        String s = peg.markdownToHtml(text);
        byId.setText(s);
        return BlogJSONResult.ok(byId);
    }

    /**
     * 通过标签查看文章
     * @param pageSize 页面大小
     * @param pageNum 当前页
     * @param tags 标签
     * @return
     */
    @GetMapping("/getTagsDetail")
    public BlogJSONResult getTagsDetail(@RequestParam("pageSize") int pageSize,
                                        @RequestParam("pageNum") int pageNum,
                                        @RequestParam("tags") String tags){
        String s = TransCodingUtil.unicodeToString(tags);
        PagedResult byTag = articleService.findByTag(pageNum, pageSize, s);
        return BlogJSONResult.ok(byTag);
    }

    /**
     * 点赞(游客即可)
     * @param articleId
     * @return
     */
    @GetMapping("getArticleLike")
    public BlogJSONResult getArticleLike(@RequestParam("articleId") long articleId){
        int result = articleService.updLike(articleId);
        if(result > 0){
            return BlogJSONResult.ok(result);
        }
        return BlogJSONResult.errorMsg("点赞失败");
    }

    @GetMapping("/getArticleByEs")
    public BlogJSONResult getArticleByEs(@RequestParam(value = "pageSize") Integer pageSize,
                                         @RequestParam(value = "pageNum") Integer pageNum,
                                         @RequestParam(value = "wordKey") String wordKey){

        String s = TransCodingUtil.unicodeToString(wordKey);
        List<EsBlogMessage> list = null;
        Page<EsBlogMessage> allBlog = esService.findAllBlog(pageNum, pageSize, s);
        list = allBlog.getContent();
        return BlogJSONResult.ok(list);
    }
}
