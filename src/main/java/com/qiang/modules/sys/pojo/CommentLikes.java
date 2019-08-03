package com.qiang.modules.sys.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.modules.sys.pojo
 * @Description: 判断用户是否点赞 实体类
 * @Date: 2019/7/24 0024 11:02
 **/
@Data
public class CommentLikes implements Serializable {

    private static final long serialVersionUID = -2289974245263677375L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 博客id
     */
    private Long blogId;

    /**
     * 评论id
     */
    private Long commentId;

    /**
     * 用户名
     */
    private String likeName;

    /**
     * 点赞时间
     */
    private String likeTime;

    /**
     * 1 -- 未读 0 -- 已读
     */
    private Integer isRead = 1;

    /**
     * 博客标题
     */
    private String title;

}
