package com.qiang.modules.sys.service;

import com.qiang.modules.sys.pojo.Comment;
import com.qiang.modules.sys.pojo.CommentLikes;
import com.qiang.modules.sys.pojo.ReportComment;
import com.qiang.modules.sys.pojo.VO.ReportCommentVO;

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

    /**
     * 判断该用户是否点赞该评论
     * @param commentLikes
     * @return true -- 点赞 false -- 未点赞
     */
    boolean isCommLikes(CommentLikes commentLikes);

    /**
     * 添加该用户点赞记录
     * @param commentLikes
     * @return
     */
    int insCommLikes(CommentLikes commentLikes);

    /**
     *  增加该评论点赞数
     * @param blogId
     * @param commentId
     * @return
     */
    List<Comment> updDecCommLikes(Long blogId, Long commentId);

    /**
     * 减少该评论点赞数
     * @param blogId
     * @param commentId
     * @return
     */
    List<Comment> updInsCommLikes(Long blogId, Long commentId);

    /**
     * 删除点赞用户
     * @param commentLikes
     * @return
     */
    int delCommLikes(CommentLikes commentLikes);

    /**
     * 我的评论
     * @param username
     * @return
     */
    List<ReportCommentVO> getUserRepMessNotRead(String username);

    /**
     * 全部消息已读
     * @param username
     * @return
     */
    List<ReportCommentVO> updComIsRead(String username);

    /**
     * 部分消息已读
     * @param id
     * @return
     */
    int updOneNotComm(Long id);

}
