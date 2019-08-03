package com.qiang.modules.sys.mapper;

import com.qiang.modules.sys.pojo.Comment;
import com.qiang.modules.sys.pojo.CommentLikes;
import com.qiang.modules.sys.pojo.ReportComment;
import com.qiang.modules.sys.pojo.VO.ReportCommentVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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

    int insRepComment(ReportComment reportComment);

    @Select("select count(*) from commentlikes where blogId = #{blogId} and commentId = #{commentId} and likeName = #{likeName}")
    int findCommIsLikes(CommentLikes commentLikes);

    int insCommIsLikes(CommentLikes commentLikes);

    @Update("update comment set likes = likes + 1 where blogId = #{arg0} and id = #{arg1}")
    int updInsCommIsLikes(Long blogId, Long commentId);

    @Update("update comment set likes = likes - 1 where blogId = #{arg0} and id = #{arg1}")
    int updDesCommIsLikes(Long blogId, Long commentId);

    @Delete("delete from commentlikes where blogId = #{blogId} and commentId = #{commentId} and likeName = #{likeName}")
    int delCommLikes(CommentLikes commentLikes);

    List<ReportCommentVO> getUserRepMessNotRead(String username);

    int updComIsRead(List<Long> rid);

    @Select("select rid from reportcomment where comName = #{arg0} and risRead = 1")
    List<Long> selComByUsername(String usrename);


    @Update("update reportcomment set risRead = 0 where rid = #{arg0}")
    int updOneNotComm(Long id);


}
