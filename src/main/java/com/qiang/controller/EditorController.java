package com.qiang.controller;

import com.qiang.commons.BlogJSONResult;
import com.qiang.commons.BuildArticleTabloidUtil;
import com.qiang.pojo.BlogMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.controller
 * @Description: 编辑文章
 * @Date: 2019/7/4 0004 11:05
 **/
@RestController
public class EditorController {

    @PostMapping("publishEditor")
    public BlogJSONResult publishEditor(@RequestBody BlogMessage blogMessage) {

        // 生成文章摘要
        BuildArticleTabloidUtil buildArticleTabloidUtil = new BuildArticleTabloidUtil();
        String articleHtmlContent = buildArticleTabloidUtil.buildArticleTabloid(blogMessage.getArticleHtmlContent());
        System.out.println(articleHtmlContent);
        blogMessage.setArticleTabloid(articleHtmlContent + "...");

        //标签
        String[] tagValue = blogMessage.getTagValue();
        String[] tags = new String[tagValue.length];
        for (int i = 0; i < tagValue.length; i++) {
            //去掉可能出现的换行符
            tagValue[i] = tagValue[i].replaceAll("<br>", "").replaceAll("&nbsp;", "").replaceAll("\\s+", "").trim();
            tags[i] = tagValue[i];
        }



        return BlogJSONResult.ok(blogMessage);
    }
}
