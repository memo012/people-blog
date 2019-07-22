var articleId = ""; // 博客id
var name = ""; //  发布博客者名称

/**
 * 渲染文章详情
 * @param data
 */
function putInArticleDetail(data) {
    $(".qz-article-top").html('');
    var center = $(
        '<div class="qz-article-top">' +
        '<div class="article-top">' + data.title + '</div>' +
        '<div class="qz-tt article-info">' +
        '<span class="am-badge am-badge-success qz-mark">&nbsp;' + data.selectType + '</span>&nbsp;&nbsp;' +
        '<span class=""><span class="am-icon-calendar"></span>&nbsp;&nbsp;' + data.createTime + '</span>&nbsp;&nbsp;' +
        '<span style="font-size: 14px;"><i class="am-icon-user">&nbsp;' + data.name + '</i></span>&nbsp;&nbsp;' +
        '<span style="font-size: 14px; color: #610024;"><i class="am-icon-eye">&nbsp;&nbsp;' + data.look + '</i></span>' +
        '</div>' +
        '</div>'
    );
    $(".qz-article-top").append(center);
    $("#wordsView").html(data.text);
}


/**
 * 从响应头中获取文章id
 */
$.ajax({
    type: 'HEAD', // 获取头信息，type=HEAD即可
    url: window.location.href,
    async: false,
    success: function (data, status, xhr) {
        articleId = xhr.getResponseHeader("articleId");
    }
});

$.ajax({
    type: "GET",
    url: "/getArticleDetail",
    // contentType: "application/x-www-form-urlencoded",
    contentType: "application/json",
    dataType: "json",
    data: {
        articleId: articleId
    },
    success: function (data) {
        name = data.data.name;
        //放入数据
        $("#article-like-span").html(data.data.like);
        putInArticleDetail(data.data);
    },
    error: function () {
        alert("出错啦...");
    }
});

/**
 * 点赞
 */
$(function () {
    $("#article-like").click(function () {
        $.ajax({
            type: "GET",
            url: "/getArticleLike",
            // contentType: "application/x-www-form-urlencoded",
            contentType: "application/json",
            dataType: "json",
            data: {
                articleId: articleId
            },
            success: function (data) {
                //放入数据
                $("#article-like-span").html(data.data);
                $(".article-btn").css({
                    "background-color": "#EA6F5A",
                    "color": "white"
                });
            },
            error: function () {
                alert("出错啦...");
            }
        });
    });

});


/**
 *  发表评论
 */
var commentBn = $("#commentBn");

commentBn.click(function () {
    var message1 = $("#desc").val();
    var data = {
        message: message1,
        blogId: articleId
    };
    $.ajax({
        type: "POST",
        url: "/insComment",
        // contentType: "application/x-www-form-urlencoded",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(data),
        success: function (data) {
            putInComment(data.data);
        },
        error: function () {
            alert("出错啦...");
        }
    });
});

/**
 * 填充评论列表
 */
function putInComment(data) {
    console.log(12);
    $(".comment").empty();
    var comment = $(".comment");
    var length = data.length;
    $.each(data, function (index, obj) {
        var center = (
            '<div class="am-g">' +
            '<div class="visitorCommentImg am-u-sm-2 am-u-lg-1">' +
            '<img src="https://zhy-myblog.oss-cn-shenzhen.aliyuncs.com/public/user/avatar/noLogin_male.jpg">' +
            '</div>' +
            '<div class="am-u-sm-10 am-u-lg-11 cn">' +
            '<div class="visitorInfo">' +
            '<span class="visitorName">' + obj['authorName'] + '</span>&nbsp;&nbsp;' +
            '<span class="visitorFloor">#' + (length--) + '楼</span>' +
            '<span class="visitorPublishDate">' + obj['createTime'] + '</span>' +
            '</div>' +
            ' <div class="visitorSay">' + obj['message'] + '</div>' +
            '<div class="tool-group">' +
            '<a>' +
            '<i class="like am-icon-thumbs-o-up">&nbsp;&nbsp;<span>' + obj['likes'] + '</span>人赞</i>&nbsp;&nbsp;' +
            '</a>' +
            '<a>' +
            '<i class="reply am-icon-comment-o">&nbsp;&nbsp;回复</i></a>' +
            '</div>' +
            '<div class="sub-comment">' +
            '<div class="sub-comment-list">' +
            '<div class="visitorReplies">' +

            '</div>' +
            '<div class="more-comment">' +
            '<a class="moreComment"><i class="moreComment am-icon-edit"> 添加新评论</i></a>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '<hr>'
        );

        var repor = $(
            '<div class="reply-sub-comment-list am-animation-slide-bottom">' +
            '<div class="replyWord">' +
            '<div class="replyWordBtn">' +
            '<textarea class="replyWordTextarea" placeholder="写下你的评论..."></textarea>' +
            '<button type="button" class="sendReplyWordBtn am-btn am-btn-success">发送' +
            '</button>' +
            ' <button type="button" class="quitReplyWordBtn am-btn">取消</button>' +
            '</div>' +
            '</div>' +
            '</div>'
        );
        $(".sub-comment").append(repor);
        var author = obj['authorName'];
        comment.append(center);
        var reLen = obj['reportComments'].length;// 回复的数量
        if (reLen > 0) {
            var report = $(".visitorReplies");
            $.each(obj['reportComments'], function (ind, obj) {
                var cent = $(
                    '<div class="visitorReply">' +
                    '<div class="visitorReplyWords">' +
                    '<a class="answerer">' + obj['repName'] + '</a>' +
                    '：' +
                    '<a class="respondent">@' + obj ['author'] + '</a>' + obj['repmess'] +
                    '</div>' +
                    '<div class="visitorReplyTime">' +
                    '<span class="visitorReplyTimeTime">' + obj['rcreateTime'] + '</span>&nbsp;&nbsp;' +
                    '<a>' +
                    '<i class="replyReply am-icon-comment-o">&nbsp;&nbsp;回复</i>' +
                    '</a>' +
                    '</div>' +
                    '<hr data-am-widget="divider" style="" class="am-divider am-divider-dashed">' +
                    '</div>'
                );
                report.eq(index).append(cent);
            })
        }
    })

    /**
     * 添加评论
     */

    $(".moreComment").click(function () {
        var $this = $(this);
        $.ajax({
            type: 'get',
            url: '/isLogin',
            dataType: 'json',
            async: false,
            data: {},
            success: function (data) {
                if (data.status == 500) {
                    toLogin();
                } else {
                    $this.parent().parent().next().css("display", "block");
                    $this.parent().parent().next().find($('.replyWordTextarea')).focus();
                }
            },
            error: function () {
            }
        });
    });

    /**
     * 回复
     */
    $(".replyReply").click(function () {
        console.log(123);
        var $this = $(this);
        $.ajax({
            type: 'get',
            url: '/isLogin',
            dataType: 'json',
            async: false,
            data: {},
            success: function (data) {
                if (data.status == 500) {
                    toLogin();
                } else {
                    $this.parent().parent().next().css("display", "block");
                    $this.parent().parent().next().find($('.replyWordTextarea')).focus();
                }
            },
            error: function () {
            }
        });
    });

    /**
     * 取消发送
     */
    $(".quitReplyWordBtn").click(function () {
        var $this = $(this);
        $this.parent().parent().parent().css("display", "none");
    });
}

/**
 * 评论查询
 */
$.ajax({
    type: "GET",
    url: "/getComment",
    // contentType: "application/x-www-form-urlencoded",
    contentType: "application/json",
    dataType: "json",
    data: {
        blogId: articleId
    },
    success: function (data) {
        if (data.data.length > 0) {
            putInComment(data.data);
        } else {
            putInNotComment();
        }
    },
    error: function () {
    }
});

/**
 * 填充不存在的评论
 */
function putInNotComment() {
    $(".comment").empty();
    var center = $(
        '<div class="am-g">' +
        '<div class="comment-com am-kai">' +
        '暂无评论，抢个沙发吧' +
        '</div>' +
        '</div>'
    );
    $(".comment").append(center);
}

/**
 * 登录
 */
function toLogin() {
    window.location.href = "/login";
}

