package com.qiang.controller;

import com.qiang.commons.BlogJSONResult;
import com.qiang.pojo.Label;
import com.qiang.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.controller
 * @Description: 标签云
 * @Date: 2019/7/8 0008 19:05
 **/
@RestController
@RequestMapping("/tag")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @GetMapping("/getTags")
    public BlogJSONResult getTags(){
        List<Label> labels = labelService.selAllLabel();
        return BlogJSONResult.ok(labels);
    }
}
