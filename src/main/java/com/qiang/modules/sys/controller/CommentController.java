package com.qiang.modules.sys.controller;

import com.qiang.common.utils.BlogJSONResult;
import com.qiang.modules.sys.pojo.Comment;
import com.qiang.modules.sys.pojo.ReportComment;
import com.qiang.modules.sys.pojo.Users;
import com.qiang.modules.sys.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.modules.sys.controller
 * @Description: 评论controller
 * @Date: 2019/7/21 0021 21:36
 **/
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 新增评论
     * @return
     */
    @PostMapping("/insComment")
    public BlogJSONResult InsComment(@RequestBody Comment comment, HttpServletRequest request){
        Users user = (Users) request.getSession().getAttribute("user");
        if(user == null){
            return BlogJSONResult.errorMsg("用户已过期");
        }
        Comment comments = new Comment();
        comments.setMessage(comment.getMessage());
        comments.setBlogId(comment.getBlogId());
        comments.setAuthorName(user.getUsername());
        comments.setUserId(user.getId());
        List<Comment> result = commentService.insComment(comments);
        if(result != null){
            return BlogJSONResult.ok(result);
        }
        return BlogJSONResult.errorMsg("新增评论失败");
    }

    @PostMapping("/InsRepComment")
    public BlogJSONResult InsRepComment(@RequestBody ReportComment reportComment, HttpServletRequest request){
        Users user = (Users) request.getSession().getAttribute("user");
        if(user == null){
            return BlogJSONResult.errorTokenMsg("用户已过期");
        }
        reportComment.setRepName(user.getUsername());
        reportComment.setReportedId(user.getId());
        List<Comment> allComment = commentService.insRepComment(reportComment);
        if(allComment != null){
            return BlogJSONResult.ok(allComment);
        }else{
            return BlogJSONResult.errorMsg("无果");
        }

    }

    /**
     * 评论查询
     * @return
     */
    @GetMapping("/getComment")
    public BlogJSONResult getComment(@RequestParam("blogId") long blogId){
        List<Comment> allComment = commentService.getAllComment(blogId);
        if(allComment != null){
            return BlogJSONResult.ok(allComment);
        }else{
            return BlogJSONResult.errorMsg("无果");
        }
    }

}
