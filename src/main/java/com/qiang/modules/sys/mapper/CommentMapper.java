package com.qiang.modules.sys.mapper;

import com.qiang.modules.sys.pojo.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.modules.sys.mapper
 * @Description: 评论数据库
 * @Date: 2019/7/22 0022 14:56
 **/
@Repository
public interface CommentMapper {

    int insComment(Comment comment);

    List<Comment> findByBlogIdAndPid(long blogId);


}
