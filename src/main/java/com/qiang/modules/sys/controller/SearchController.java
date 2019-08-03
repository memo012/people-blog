package com.qiang.modules.sys.controller;

import com.qiang.common.utils.BlogJSONResult;
import com.qiang.common.utils.TransCodingUtil;
import com.qiang.modules.sys.pojo.es.EsBlogMessage;
import com.qiang.modules.sys.service.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.modules.sys.controller
 * @Description: 搜索页面
 * @Date: 2019/7/24 0024 22:09
 **/
@RestController
public class SearchController {


    @Autowired
    private EsService esService;
    /**
     * 文章搜索
     * @param pageSize
     * @param pageNum
     * @param wordKey
     * @return
     */
    @GetMapping("/getArticleByEs")
    public BlogJSONResult getArticleByEs(@RequestParam(value = "pageSize") Integer pageSize,
                                         @RequestParam(value = "pageNum") Integer pageNum,
                                         @RequestParam(value = "wordKey") String wordKey) {

        String s = TransCodingUtil.unicodeToString(wordKey);
        List<EsBlogMessage> list = null;
        Page<EsBlogMessage> allBlog = esService.findAllBlog(pageNum, pageSize, s);
        list = allBlog.getContent();
        return BlogJSONResult.ok(list);
    }

}
