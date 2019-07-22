package com.qiang.modules.sys.service;

import com.qiang.modules.sys.pojo.Comment;

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
     * @return
     */
    List<Comment> insComment(Comment comment);

    /**
     * 评论查询
     * @return
     */
    List<Comment> getAllComment(long blogId);

}
