var noticeBox = $(".notice-box");

/**
 * 修改密码
 */
$("#register-btn").click(function () {
    var phone = $("#phone").val().trim();
    var yan1 = $("#yan").val();
    var password = $("#password").val();
    var SurePwd = $("#passwordSure").val();
    var myreg = /^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\d{8})$/;
    if (phone.length == 0) {
        $(".notice-box-phone").show();
    } else if (phone.length != 11) {
        $(".notice-box-phone-num").show();
    } else if (!myreg.test(phone)) {
        $(".notice-box-phone-num").show();
    }else if(checkPhone1(phone) == false){
        $(".notice-box-phone-exit").show();
    } else if (yan1.length == 0) {
        $(".notice-box-yan").show();
    }else if (password.length == 0 || SurePwd.length == 0) {
        $(".notice-box-password").show();
    } else if (password.length < 6 || SurePwd.length > 18) {
        $(".notice-box-password-num").show();
    } else if (password != SurePwd) {
        $(".notice-box-password-ps").show();
    } else {
        $.ajax({
            type: "GET",
            url: "findUsersPwd",
            // contentType: "application/x-www-form-urlencoded",
            contentType: "application/json",
            dataType: "json",
            data: {
                phone: phone,
                password: password
            },
            success: function (data) {
                //放入数据
               console.log(data);
               if(data.status == 200){
                   $("#publish-success").modal();
               }else {
                   $(".notice-box-reg").show();
                   setTimeout(function () {
                       noticeBox.hide();
                   }, 3000);
               }
            },
            error: function (res) {
                alert("客官，慢点按(⊙o⊙)？");
            }
        });
    }

    // 定时关闭错误提示框
    var closeNoticeBox = setTimeout(function () {
        noticeBox.hide();
    }, 3000);
});


/**
 * 修改成功跳转页面
 * @type {*|jQuery|HTMLElement}
 */
var issueSuccess = $("#issue-success");
issueSuccess.click(function () {
    window.location.href="login";
});

/**
 * 手机号校验
 * @param data
 */
function checkPhone1(data) {
    var str = {phone: data};
    var mes = true;
    $.ajax({
        type: "GET",
        async:false,
        url: "phoneCheck",
        // contentType: "application/x-www-form-urlencoded",
        contentType: "application/json",
        dataType: "json",
        data: str,
        success: function (data) {
            console.log(data);
            //放入数据
            if(data.status == 200){
                mes = false;
            }
        },
        error: function (res) {
            alert("客官，慢点按(⊙o⊙)？");
        }
    });
    console.log(mes);
    return mes;
}