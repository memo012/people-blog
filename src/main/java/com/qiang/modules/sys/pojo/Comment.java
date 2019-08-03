package com.qiang.modules.sys.pojo;

import java.io.Serializable;
import java.util.Set;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.modules.sys.pojo
 * @Description: 评论实体类
 * @Date: 2019/7/22 0022 14:58
 **/
public class Comment implements Serializable {

    private static final long serialVersionUID = -6530678643599384025L;
    /**
     * 标识符
     */
    private Long id;

    /**
     * 发布博客者id
     */
    private String userId;

    /**
     * 博客id
     */
    private Long blogId;

    /**
     * 评论内容
     */
    private String message;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 点赞人数
     */
    private Integer likes;

    /**
     * 发布者名称
     */
    private String authorName;


    /**
     *  该条评论是否已读  1--未读   0--已读
     */
    private Integer isRead = 1;

    /**
     * 一条评论有N条消息
     */
    private Set<ReportComment> reportComments;

    public Set<ReportComment> getReportComments() {
        return reportComments;
    }

    public void setReportComments(Set<ReportComment> reportComments) {
        this.reportComments = reportComments;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }
}
