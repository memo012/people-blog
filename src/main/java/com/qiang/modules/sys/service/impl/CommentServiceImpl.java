package com.qiang.modules.sys.service.impl;

import com.qiang.common.utils.Constant;
import com.qiang.common.utils.RedisOperator;
import com.qiang.common.utils.TimeUtil;
import com.qiang.modules.sys.mapper.CommentMapper;
import com.qiang.modules.sys.mapper.UsersMapper;
import com.qiang.modules.sys.pojo.Comment;
import com.qiang.modules.sys.pojo.CommentLikes;
import com.qiang.modules.sys.pojo.ReportComment;
import com.qiang.modules.sys.pojo.Users;
import com.qiang.modules.sys.pojo.VO.ReportCommentVO;
import com.qiang.modules.sys.service.CommentService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.modules.sys.service.impl
 * @Description: 评论业务逻辑层
 * @Date: 2019/7/22 0022 15:04
 **/
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private RedisOperator redisOperator;


    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int insCommLikes(CommentLikes commentLikes) {
        TimeUtil timeUtil = new TimeUtil();
        long id = timeUtil.getLongTime();
        commentLikes.setId(id);
        commentLikes.setLikeTime(timeUtil.getParseDateForSix());
        return commentMapper.insCommIsLikes(commentLikes);
    }

    @Override
    public int updOneNotComm(Long id) {
        return commentMapper.updOneNotComm(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ReportCommentVO> updComIsRead(String username) {
        List<Long> rid = commentMapper.selComByUsername(username);
        List<ReportCommentVO> userRepMessNotRead = null;
        if(rid.size() == 0){
            return userRepMessNotRead;
        }
        int i = commentMapper.updComIsRead(rid);
        if(i > 0){
            userRepMessNotRead = getUserRepMessNotRead(username);
        }
        return userRepMessNotRead;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Comment> updDecCommLikes(Long blogId, Long commentId) {
        int i = commentMapper.updDesCommIsLikes(blogId, commentId);
        List<Comment> allComment = null;
        if(i > 0){
            redisOperator.lpop(Constant.BLOG_REPORT);
            getAllComment(blogId);
            allComment = (List<Comment>)redisOperator.range(Constant.BLOG_REPORT, 0, -1);
        }
        return allComment;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int delCommLikes(CommentLikes commentLikes) {
        return commentMapper.delCommLikes(commentLikes);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ReportCommentVO> getUserRepMessNotRead(String username) {
        return commentMapper.getUserRepMessNotRead(username);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Comment> updInsCommLikes(Long blogId, Long commentId) {
        int i = commentMapper.updInsCommIsLikes(blogId, commentId);
        List<Comment> allComment = null;
        if(i > 0){
            redisOperator.lpop(Constant.BLOG_REPORT);
            getAllComment(blogId);
            allComment = (List<Comment>)redisOperator.range(Constant.BLOG_REPORT, 0, -1);
        }
        return allComment;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean isCommLikes(CommentLikes commentLikes) {
        return commentMapper.findCommIsLikes(commentLikes) == 1 ? true : false;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Comment> insRepComment(ReportComment reportComment) {
        TimeUtil timeUtil = new TimeUtil();
        long id = timeUtil.getLongTime();
        reportComment.setRid(id);
        reportComment.setRcreateTime(timeUtil.getParseDateForSix());
        reportComment.setRisRead(1);
        List<Comment> list = null;
        int result = commentMapper.insRepComment(reportComment);
        if(result > 0){
            list = commentMapper.findByBlogIdAndPid(reportComment.getBlogId());
            redisOperator.lpop(Constant.BLOG_REPORT);
            redisOperator.lpush(Constant.BLOG_REPORT, list);
            list = getAllComment(reportComment.getBlogId());
        }
        // 增加博客评论（缓存）
        redisOperator.incr(Constant.BLOG_REPORT_COUNT, 1);
        return list;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Comment> getAllComment(long blogId) {
        List<Comment> list = null;
        if(redisOperator.hasKey(Constant.BLOG_REPORT)){
            list = (List<Comment>)redisOperator.range(Constant.BLOG_REPORT, 0, -1);
        }else{
            list = commentMapper.findByBlogIdAndPid(blogId);
            if(list.size() > 0){
                redisOperator.lpush(Constant.BLOG_REPORT, list);
            }else{
                redisOperator.lpush(Constant.BLOG_REPORT, "");
            }
        }
        return list;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public List<Comment> insComment(Comment comment) {
        TimeUtil timeUtil = new TimeUtil();
        long id = timeUtil.getLongTime();
        comment.setId(id);
        comment.setCreateTime(timeUtil.getParseDateForSix());
        comment.setLikes(0);
        List<Comment> byBlogId = null;
        List<Comment> list = null;
        int result = commentMapper.insComment(comment);
        long blogId = comment.getBlogId();
        if (result > 0) {
            // 查询评论
            byBlogId = commentMapper.findByBlogIdAndPid(blogId);
            redisOperator.lpop(Constant.BLOG_REPORT);
            redisOperator.lpush(Constant.BLOG_REPORT, byBlogId);
            list = getAllComment(blogId);
        }
        // 增加博客评论（缓存）
        redisOperator.incr(Constant.BLOG_REPORT_COUNT, 1);
        return list;
    }

}
