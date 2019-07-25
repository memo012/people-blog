package com.qiang.modules.sys.controller;

import com.qiang.common.utils.BlogJSONResult;
import com.qiang.common.utils.BuildArticleTabloidUtil;
import com.qiang.common.utils.StringAndArray;
import com.qiang.modules.sys.pojo.VO.BlogMessageVO;
import com.qiang.modules.sys.service.BlogService;
import com.qiang.modules.sys.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.controller
 * @Description: 编辑文章
 * @Date: 2019/7/4 0004 11:05
 **/
@RestController
public class EditorController {


    @Autowired
    private LabelService labelService;

    @Autowired
    private BlogService blogService;

    /**
     * 发布博客
     * @param blogMessage
     * @param request
     * @return
     */
    @PostMapping("publishEditor")
    public BlogJSONResult publishEditor(@RequestBody BlogMessageVO blogMessage
            , HttpServletRequest request) {

        // 生成文章摘要
        BuildArticleTabloidUtil buildArticleTabloidUtil = new BuildArticleTabloidUtil();
        String articleHtmlContent = buildArticleTabloidUtil.buildArticleTabloid(blogMessage.getArticleHtmlContent());
        blogMessage.setArticleTabloid(articleHtmlContent + "...");

        //标签
        String[] tagValue = blogMessage.getTagValue();
        for (int i = 0; i < tagValue.length; i++) {
            //去掉可能出现的换行符
            tagValue[i] = tagValue[i].replaceAll("<br>", "").replaceAll("&nbsp;", "").replaceAll("\\s+", "").trim();
        }
        labelService.insLabel(tagValue);
        String label = StringAndArray.arrayToString(tagValue);
        blogMessage.setLabelValues(label);
        blogMessage.setName("强子");
        blogMessage.setOriginalAuthor("");

        String id = request.getParameter("id");

        //修改文章
        if (!"".equals(id) && id != null) {

        }
        blogService.publishBlog(blogMessage);
        return BlogJSONResult.ok(blogMessage);
    }
}
