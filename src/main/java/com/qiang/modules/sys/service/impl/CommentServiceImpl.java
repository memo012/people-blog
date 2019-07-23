package com.qiang.modules.sys.service.impl;

import com.qiang.common.utils.TimeUtil;
import com.qiang.modules.sys.mapper.CommentMapper;
import com.qiang.modules.sys.mapper.UsersMapper;
import com.qiang.modules.sys.pojo.Comment;
import com.qiang.modules.sys.pojo.ReportComment;
import com.qiang.modules.sys.pojo.Users;
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
        }
        return list;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Comment> getAllComment(long blogId) {
        return commentMapper.findByBlogIdAndPid(blogId);
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
        int result = commentMapper.insComment(comment);
        long blogId = comment.getBlogId();
        if (result > 0) {
            // 查询评论
            byBlogId = commentMapper.findByBlogIdAndPid(blogId);
        }
        return byBlogId;
    }

}
