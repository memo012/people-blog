package com.qiang.modules.sys.pojo;

import lombok.Data;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.modules.sys.pojo
 * @Description: 判断用户是否点赞 实体类
 * @Date: 2019/7/24 0024 11:02
 **/
@Data
public class CommentLikes {

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

}
