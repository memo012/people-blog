package com.qiang.modules.sys.pojo.es;

import com.qiang.modules.sys.pojo.BlogMessage;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;

import java.io.Serializable;

/**
 * @Author: qiang
 * @ProjectName: adminsystem
 * @Package: com.qiang.modules.sys.pojo.es
 * @Description: ES文档类
 * @Date: 2019/7/13 0013 19:06
 **/
@Document(indexName = "blog", type = "blog")
public class EsBlogMessage implements Serializable {

    /**
     * 标识符
     */
    @Id
    @Field(index = FieldIndex.not_analyzed) // 不做全文检索字段
    private long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 正文
     */
    @Field(index = FieldIndex.not_analyzed) // 不做全文检索字段
    private String text;

    /**
     * 标签id
     */
    @Field(index = FieldIndex.not_analyzed) // 不做全文检索字段
    private String labelValues;

    /**
     * 文章类型
     */
    @Field(index = FieldIndex.not_analyzed) // 不做全文检索字段
    private String selectType;

    /**
     * 博客分类
     */
    @Field(index = FieldIndex.not_analyzed) // 不做全文检索字段
    private String selectCategories;

    /**
     * 文章等级
     */
    @Field(index = FieldIndex.not_analyzed) // 不做全文检索字段
    private int selectGrade;

    /**
     * 原文章作者
     */
    private String originalAuthor;

    /**
     * 文章（0-公开  1-私密）
     */
    @Field(index = FieldIndex.not_analyzed) // 不做全文检索字段
    private String message;

    /**
     * 创建时间
     */
    @Field(index = FieldIndex.not_analyzed) // 不做全文检索字段
    private String createTime;


    /**
     * 点赞
     */
    @Field(index = FieldIndex.not_analyzed) // 不做全文检索字段
    private Integer likes;

    /**
     * 作者名字
     */
    private String name;

    /**
     * 文章摘要
     * @return
     */
    private String articleTabloid;

    /**
     * 含HTML文章
     */
    @Field(index = FieldIndex.not_analyzed) // 不做全文检索字段
    private String articleHtmlContent;

    /**
     * 标签数组
     */
    @Field(index = FieldIndex.not_analyzed) // 不做全文检索字段
    private String[] tagValue;

    /**
     * 浏览次数
     */
    @Field(index = FieldIndex.not_analyzed) // 不做全文检索字段
    private int look;

    /**
     * 文章地址
     */
    @Field(index = FieldIndex.not_analyzed) // 不做全文检索字段
    private String articleUrl;

    /**
     * 规定标签
     */
    @Field(index = FieldIndex.not_analyzed) // 不做全文检索字段
    private String specificTag;

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getSpecificTag() {
        return specificTag;
    }

    public void setSpecificTag(String specificTag) {
        this.specificTag = specificTag;
    }

    public int getLook() {
        return look;
    }

    public void setLook(int look) {
        this.look = look;
    }

    public String[] getTagValue() {
        return tagValue;
    }

    public void setTagValue(String[] tagValue) {
        this.tagValue = tagValue;
    }

    public String getArticleHtmlContent() {
        return articleHtmlContent;
    }

    public void setArticleHtmlContent(String articleHtmlContent) {
        this.articleHtmlContent = articleHtmlContent;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getArticleTabloid() {
        return articleTabloid;
    }

    public void setArticleTabloid(String articleTabloid) {
        this.articleTabloid = articleTabloid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLabelValues() {
        return labelValues;
    }

    public void setLabelValues(String labelValues) {
        this.labelValues = labelValues;
    }

    public String getSelectType() {
        return selectType;
    }

    public void setSelectType(String selectType) {
        this.selectType = selectType;
    }

    public String getSelectCategories() {
        return selectCategories;
    }

    public void setSelectCategories(String selectCategories) {
        this.selectCategories = selectCategories;
    }

    public int getSelectGrade() {
        return selectGrade;
    }

    public void setSelectGrade(int selectGrade) {
        this.selectGrade = selectGrade;
    }

    public String getOriginalAuthor() {
        return originalAuthor;
    }

    public void setOriginalAuthor(String originalAuthor) {
        this.originalAuthor = originalAuthor;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getLike() {
        return likes;
    }

    public void setLike(Integer likes) {
        this.likes = likes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected EsBlogMessage(){} // JPA 的规范要求无参构造函数；设为 protected 防止直接使用

    public EsBlogMessage(BlogMessage blogMessage){
        this.id = blogMessage.getId();
        this.title = blogMessage.getTitle();
        this.text = blogMessage.getText();
        this.labelValues = blogMessage.getLabelValues();
        this.articleHtmlContent = blogMessage.getArticleHtmlContent();
        this.likes = blogMessage.getLike();
        this.look = blogMessage.getLook();
        this.selectCategories = blogMessage.getSelectCategories();
        this.selectGrade = blogMessage.getSelectGrade();
        this.selectType = blogMessage.getSelectType();
        this.originalAuthor = blogMessage.getOriginalAuthor();
        this.message = blogMessage.getMessage();
        this.createTime = blogMessage.getCreateTime();
        this.name = blogMessage.getName();
        this.articleTabloid = blogMessage.getArticleTabloid();
        this.tagValue = blogMessage.getTagValue();
    }

    public void update(BlogMessage blogMessage){
        this.id = blogMessage.getId();
        this.title = blogMessage.getTitle();
        this.text = blogMessage.getText();
        this.labelValues = blogMessage.getLabelValues();
        this.articleHtmlContent = blogMessage.getArticleHtmlContent();
        this.likes = blogMessage.getLike();
        this.look = blogMessage.getLook();
        this.selectCategories = blogMessage.getSelectCategories();
        this.selectGrade = blogMessage.getSelectGrade();
        this.selectType = blogMessage.getSelectType();
        this.originalAuthor = blogMessage.getOriginalAuthor();
        this.message = blogMessage.getMessage();
        this.createTime = blogMessage.getCreateTime();
        this.name = blogMessage.getName();
        this.articleTabloid = blogMessage.getArticleTabloid();
        this.tagValue = blogMessage.getTagValue();
    }

}
