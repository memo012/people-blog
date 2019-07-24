package com.qiang.modules.sys.pojo;

import lombok.Data;

import java.util.Set;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.modules.sys.pojo
 * @Description: 留言实体类
 * @Date: 2019/7/24 0024 17:13
 **/
@Data
public class Guest {

    /**
     * 主键
     */
    private Long id;

    /**
     * 留言者id
     */
    private String userId;

    /**
     * 留言内容
     */
    private String message;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 点赞数
     */
    private Integer likes;

    /**
     * 1  -- 未读  0 -- 已读
     */
    private Integer isRead = 1;

    /**
     * 留言者名称
     */
    private String authorName;

    /**
     * 一条留言可以有多个评论
     */
    private Set<RepGuest> reportCommentSet;

}
