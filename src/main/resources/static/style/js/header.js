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

var $word = "";
$(".es").keyup(function (event) {
    if (event.which == "13") {
        $word = $(".es").val().trim();
        if ($word != undefined && $word.length != 0 && $word != "") {
            window.location.href="/es/"+$word;
        }
    }
});



