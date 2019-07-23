package com.qiang.modules.sys.service;

import com.qiang.modules.sys.pojo.Comment;
import com.qiang.modules.sys.pojo.ReportComment;

import java.util.List;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.modules.sys.service
 * @Description: 评论接口
 * @Date: 2019/7/22 0022 15:03
 **/
public interface CommentService {

    /**
     * 新增评论
     * @param comment
     * @return 全部评论消息
     */
    List<Comment> insComment(Comment comment);

    /**
     * 评论查询
     * @return 全部评论消息
     */
    List<Comment> getAllComment(long blogId);

    /**
     *  新增回复评论
     * @param reportComment
     * @return 全部评论消息
     */
    List<Comment> insRepComment(ReportComment reportComment);

}
