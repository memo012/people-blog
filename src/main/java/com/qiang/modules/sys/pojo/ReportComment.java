package com.qiang.modules.sys.pojo;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.modules.sys.pojo
 * @Description:
 * @Date: 2019/7/22 0022 18:15
 **/
public class ReportComment {

    /**
     * 标识符
     */
    private Long rid;

    /**
     * 评论者id
     */
    private Long commentId;

    /**
     * 内容
     */
    private String repMess;

    /**
     *  回复者id
     */
    private String reportedId;

    /**
     * 创建时间
     */
    private String rcreateTime;

    /**
     *  该条评论是否已读  1--未读   0--已读
     */
    private Integer risRead = 1;

    /**
     * 回复者名字
     */
    private String repName;

    /**
     * 被评论者名字
     */
    private String comName;

    /**
     * 博客id
     */
    private Long blogId;

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getRepName() {
        return repName;
    }

    public void setRepName(String repName) {
        this.repName = repName;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getRepMess() {
        return repMess;
    }

    public void setRepMess(String repMess) {
        this.repMess = repMess;
    }

    public String getReportedId() {
        return reportedId;
    }

    public void setReportedId(String reportedId) {
        this.reportedId = reportedId;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getRcreateTime() {
        return rcreateTime;
    }

    public void setRcreateTime(String rcreateTime) {
        this.rcreateTime = rcreateTime;
    }

    public Integer getRisRead() {
        return risRead;
    }

    public void setRisRead(Integer risRead) {
        this.risRead = risRead;
    }
}
