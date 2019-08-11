package com.qiang.modules.sys.controller;

import com.qiang.common.utils.Constant;
import com.qiang.common.utils.RedisOperator;
import com.qiang.common.utils.TransCodingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.soap.Addressing;
import java.io.UnsupportedEncodingException;

/**
 * @author: qiang
 * @Date: 2019/7/06 19:24
 * Describe: 所有页面跳转
 */
@Controller
public class BackController {

    @Autowired
    private RedisOperator redisOperator;


    /**
     * 首页
     * @return
     */
    @GetMapping("/")
    public String index(){
        if(redisOperator.hasKey(Constant.BLOG_VISIT_COUNT)){
            redisOperator.incr(Constant.BLOG_VISIT_COUNT, 1);
        }
        return "index";
    }

    /**
     * 寻找密码
     * @return
     */
    @GetMapping("/findPwd")
    public String findPwd(){
        return "findPwd";
    }

    /**
     * 关于我
     * @return
     */
    @GetMapping("/aboutme")
    public String aboutme(){
        if(redisOperator.hasKey(Constant.BLOG_VISIT_COUNT)){
            redisOperator.incr(Constant.BLOG_VISIT_COUNT, 1);
        }
        return "aboutme";
    }


    /**
     * 后台管理界面
     * @return
     */
    @GetMapping("SuperAdmin")
    public String SuperAdmin(){
        return "SuperAdmin";
    }

    /**
     * 登录页面
     * @return
     */
    @GetMapping("login")
    public String login(){
        return "login";
    }

    /**
     * 搜索页面
     * @return
     */
    @GetMapping("/es/{message}")
    public String elasticsearch(@PathVariable("message") String message, HttpServletResponse response){
        if(redisOperator.hasKey(Constant.BLOG_VISIT_COUNT)){
            redisOperator.incr(Constant.BLOG_VISIT_COUNT, 1);
        }
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("message", TransCodingUtil.stringToUnicode(message));
        return "/elasticsearch";
    }

    /**
     * 注册页面
     * @return
     */
    @GetMapping("register")
    public String register(){
        return "register";
    }

    /**
     * 博客发布页面
     * @return
     */
    @GetMapping("editor")
    public String editor(HttpServletRequest request){
        String id = request.getParameter("id");
        if(!"".equals(id)){
            request.getSession().setAttribute("id", id);
        }
        return "editor";
    }

    /**
     * 归档页面
     * @return
     */
    @GetMapping("/archive")
    public String archive(){
        if(redisOperator.hasKey(Constant.BLOG_VISIT_COUNT)){
            redisOperator.incr(Constant.BLOG_VISIT_COUNT, 1);
        }
        return "archive";
    }

    /**
     * 更新页面
     * @return
     */
    @GetMapping("/update")
    public String update(){
        if(redisOperator.hasKey(Constant.BLOG_VISIT_COUNT)){
            redisOperator.incr(Constant.BLOG_VISIT_COUNT, 1);
        }
        return "update";
    }


    /**
     * 跳转文章详情页面
     * @param articleId
     * @return
     */
    @GetMapping("/article/{articleId}")
    public String show(@PathVariable("articleId") long articleId
                        , HttpServletResponse response){
        if(redisOperator.hasKey(Constant.BLOG_VISIT_COUNT)){
            redisOperator.incr(Constant.BLOG_VISIT_COUNT, 1);
        }
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //将文章id存入响应头
        response.setHeader("articleId",String.valueOf(articleId));
        return "show";
    }

    /**
     * 查过发布时间查询
     * @param createTime
     * @param response
     * @return
     */
    @GetMapping("/time/{createTime}")
    public String time(@PathVariable("createTime") String createTime
            , HttpServletResponse response){
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //将文章id存入响应头
        response.setHeader("createTime",String.valueOf(createTime));
        return "time";
    }

    /**
     * 标签归档
     * @param request
     * @param response
     * @return
     */
    @GetMapping("tags")
    public String tags(HttpServletRequest request
            , HttpServletResponse response){
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            String tag = request.getParameter("tag");
            //将文章id存入响应头
            response.setHeader("tag", TransCodingUtil.stringToUnicode(tag));
        } catch (UnsupportedEncodingException e) {
        }
        return "tags";
    }

    /**
     * 文章归类查询
     * @param request
     * @param response
     * @return
     */
    @GetMapping("categories")
    public String categories(HttpServletRequest request
                                ,HttpServletResponse response){
        if(redisOperator.hasKey(Constant.BLOG_VISIT_COUNT)){
            redisOperator.incr(Constant.BLOG_VISIT_COUNT, 1);
        }
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            String cate = request.getParameter("categorie");
            response.setHeader("categories", TransCodingUtil.stringToUnicode(cate));
        } catch (UnsupportedEncodingException e) {

        }
        return "categories";
    }

    /**
     * 留言板
     * @return
     */
    @GetMapping("/guest")
    public String guest(){
        if(redisOperator.hasKey(Constant.BLOG_VISIT_COUNT)){
            redisOperator.incr(Constant.BLOG_VISIT_COUNT, 1);
        }
        return "guest";
    }

    /**
     * 个人中心
     * @return
     */
    @GetMapping("/user")
    public String user(){
        return "user";
    }


}
