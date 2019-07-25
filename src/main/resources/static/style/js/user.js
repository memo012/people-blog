var username = "";

$(function () {
    $(".list-item").click(function () {
        $(this).addClass("active").siblings().removeClass("active");
        var idx = $(this).index();
        $(".admin-content-body").eq(idx).addClass("qz-show").siblings().removeClass("qz-show");
    });
});

$(function () {
    username = $(".username").text();
    $.ajax({
        type: "GET",
        url: "/getUserMess",
        // contentType: "application/x-www-form-urlencoded",
        contentType: "application/json",
        dataType: "json",
        data: {
            username: username
        },
        success: function (data) {
            //放入数据
            putInMessage(data.data);
        },
        error: function () {
        }
    })
})

var userName = $("#user-name");
var userQQ = $("#user-QQ");
var userEmail = $("#user-email");
var userIntro = $("#user-intro");
var noticeBox = $(".notice");

/**
 * 个人信息渲染
 * @param data
 */
function putInMessage(data) {
    userName.attr("value", data['realname']);
    $("#user-phone").attr("value", data['phone']);
    userQQ.attr("value", data['qq']);
    userEmail.attr("value", data['email']);
    userIntro.val(data['intro']);
}

/**
 * 保存修改
 */
var username1 = "";
var userqq = "";
var useremail = "";
var userintro = "";
$(".savebtn").click(function () {
    username1 = userName.val().trim();
    userqq = userQQ.val().trim();
    useremail = userEmail.val().trim();
    userintro = userIntro.val();
    var qqnum = /^[1-9][0-9]{4,14}$/;
    var emailnum = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
    if (username1.length > 4) {
        $(".notice-name-len").show();
        close();
        return;
    }
    if (userqq.length != 0) {
        if (!qqnum.test(userqq)) {
            $(".notice-qq").show();
            close();
            return;
        }
    }
    if (useremail.length != 0) {
        if (!emailnum.test(useremail)) {
            $(".notice-email").show();
            close();
            return;
        }
    }
    updMessage();
});

function updMessage() {

    var data = {
        realname: username1,
        qq: userqq,
        email: useremail,
        intro: userintro,
        username: username
    };
    $.ajax({
        type: "POST",
        url: "insUserMess",
        // contentType: "application/x-www-form-urlencoded",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(data),
        success: function (data) {
            //放入数据
            console.log(data);
            if (data.status == 200) {
                console.log(12);
                $(".notice-box-suc").show();
                putInMessage(data.data);
            } else if (data.status == 500) {
                $(".notice-box1").show();
            }
        },
        error: function () {
        }
    })
    close();
}

function close() {
    // 定时关闭错误提示框
    var closeNoticeBox = setTimeout(function () {
        noticeBox.hide();
    }, 3000);
}