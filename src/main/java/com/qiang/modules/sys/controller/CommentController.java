package com.qiang.modules.sys.controller;

import com.qiang.common.utils.BlogJSONResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.modules.sys.controller
 * @Description: 评论controller
 * @Date: 2019/7/21 0021 21:36
 **/
@RestController
public class CommentController {

    /**
     * 新增评论
     * @return
     */
    @PostMapping("InsComment")
    public BlogJSONResult InsComment(@RequestParam("message") String message){
        return BlogJSONResult.ok();
    }

}
