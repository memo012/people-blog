package com.qiang.modules.sys.controller;

import com.qiang.common.utils.BlogJSONResult;
import com.qiang.common.utils.Constant;
import com.qiang.common.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.modules.sys.controller
 * @Description: 首页
 * @Date: 2019/8/3 0003 10:43
 **/
@RestController
public class IndexController {

    @Autowired
    private RedisOperator redisOperator;


    /**
     * 文章总数
     * @return
     */
    @GetMapping("myArticlesCount")
    public BlogJSONResult myArticlesCount(){
        Long count = 0L;
        if(redisOperator.hasKey(Constant.BLOG_DETAIL)){
            count = redisOperator.hsize(Constant.BLOG_DETAIL);
        }
        return BlogJSONResult.ok(count);
    }

    /**
     * 标签总数
     * @return
     */
    @GetMapping("myLabelsCount")
    public BlogJSONResult myLabelsCount(){
        Long count = 0L;
        if(redisOperator.hasKey(Constant.LABEL_ALL)){
            count = redisOperator.llen(Constant.LABEL_ALL);
        }
        return BlogJSONResult.ok(count);
    }


    /**
     * 评论总数
     * @return
     */
    @GetMapping("myReportsCount")
    public BlogJSONResult myReportsCount(){
        Integer count = 0;
        if(redisOperator.hasKey(Constant.BLOG_REPORT_COUNT)){
            count = (Integer) redisOperator.get(Constant.BLOG_REPORT_COUNT);
        }

        return BlogJSONResult.ok(count);
    }

    /**
     * 留言总数
     * @return
     */
    @GetMapping("myGuestCount")
    public BlogJSONResult myGuestCount(){
        Integer count = 0;
        if(redisOperator.hasKey(Constant.BLOG_GUEST_COUNT)){
            count = (Integer)redisOperator.get(Constant.BLOG_GUEST_COUNT);
        }
        return BlogJSONResult.ok(count);
    }

    /**
     * 访客量
     * @return
     */
    @GetMapping("visitCount")
    public BlogJSONResult visitCount(){
        Integer count = 0;
        if(redisOperator.hasKey(Constant.BLOG_VISIT_COUNT)){
            count = (Integer)redisOperator.get(Constant.BLOG_VISIT_COUNT);
        }
        return BlogJSONResult.ok(count);
    }

}
