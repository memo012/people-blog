$(function () {
    $(".list-item").click(function () {
        $(this).addClass("active").siblings().removeClass("active");
        var idx = $(this).index();
        $(".admin-content-body").eq(idx).addClass("qz-show").siblings().removeClass("qz-show");
    });
});