var articleId = "";


/**
 * 渲染文章详情
 * @param data
 */
function putInArticleDetail(data){
    $(".qz-article-top").html('');
    var center = $(
        '<div class="qz-article-top">'+
        '<div class="article-top">'+data.title+'</div>'+
        '<div class="qz-tt article-info">'+
        '<span class="am-badge am-badge-success qz-mark">&nbsp;'+data.selectType+'</span>&nbsp;&nbsp;'+
        '<span class=""><span class="am-icon-calendar"></span>&nbsp;&nbsp;'+data.createTime+'</span>&nbsp;&nbsp;'+
        '<span style="font-size: 14px;"><i class="am-icon-user">&nbsp;'+data.name+'</i></span>&nbsp;&nbsp;'+
        '<span style="font-size: 14px; color: #610024;"><i class="am-icon-eye">&nbsp;&nbsp;'+data.look+'</i></span>'+
        '</div>'+
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
    url : window.location.href,
    async:false,
    success:function (data, status, xhr) {
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
                     "color":"white"
                 });
            },
            error: function () {
                alert("出错啦...");
            }
        });
    });

});