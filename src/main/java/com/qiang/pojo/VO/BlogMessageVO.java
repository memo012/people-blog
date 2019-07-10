package com.qiang.pojo.VO;

import com.qiang.pojo.BlogMessage;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.pojo
 * @Description:
 * @Date: 2019/7/8 0008 17:32
 **/
public class BlogMessageVO extends BlogMessage {
    /**
     * 文章地址
     */
    private String articleUrl;

    /**
     * 规定标签
     */
    private String specificTag;

    public String getSpecificTag() {
        return specificTag;
    }

    public void setSpecificTag(String specificTag) {
        this.specificTag = specificTag;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }
}
