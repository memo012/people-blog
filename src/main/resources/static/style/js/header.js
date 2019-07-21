$(".logou").click(function () {
    $.ajax({
        type: "GET",
        url: "/logout",
        // contentType: "application/x-www-form-urlencoded",
        contentType: "application/json",
        dataType: "json",
        success: function (data) {
            if(data.status == 200){
                window.location.href="/";
            }
        }
    })
});