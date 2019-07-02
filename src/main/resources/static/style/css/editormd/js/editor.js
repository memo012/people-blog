var testEditor;
$(function () {
    testEditor = editormd("my-editormd", {
        width: "98%",
        height: 600,
        syncScrolling: "single",
        path: "style/css/editormd/lib/", //依赖lib文件夹路径
        emoji: true,
        taskList: true,
        tocm: true,
        imageUpload: true,
        imageFormats: ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
        imageUploadURL: "/uploadImage",		//上传图片控制器Mapping
        saveHTMLToTextarea: true //注意3：这个配置，方便post提交表单
    });
});

var publishBtn = $(".publishBtn");
var qzEditorTitle = $("#qz-editor-title");
var myEditormdHtmlCode = $("#my-editormd-html-code");
var noticeBoxTitle = $(".notice-box-title");
var noticeBoxContent = $(".notice-box-content");
var noticeBox = $('.notice-box');
/**
 * 发布博客
 */
publishBtn.click(function (data) {
    var qzEditorTitleValues = qzEditorTitle.val();
    var myEditormdHtmlCodeValues = myEditormdHtmlCode.val();
    if (qzEditorTitleValues.length == 0) {
        noticeBoxTitle.show();
    } else if (myEditormdHtmlCodeValues.length == 0) {
        noticeBoxContent.show();
    } else {
        $('#my-alert').modal();
    }
    // 定时关闭错误提示框
    var closeNoticeBox = setTimeout(function () {
        noticeBox.hide();
    }, 3000);
});

// 插入标签
var addTagsBtn = $('.addTagsBtn');
$(function () {
    var i = 0;
    var $tag = $(".tag");
    var appendParam = function () {
        var param = $('<div style="display: inline-block;"><p class="tag-name" contenteditable="true"></p>' +
            '<i class="am-icon-times removeTag" style="color: #CCCCCC"></i></div>');
        $tag.append(param);
        $('.tag-name').click(function () {
            $(this).focus();
        });
    };
    addTagsBtn.on("click", function () {
        if(i >= 4){
            addTagsBtn.attr('disabled','disabled');
        }
        var value = $(".tag-name").eq(i - 1).html();
        if(value != ""){
            appendParam();
            i++;
            console.log(i);
        }
    });

    $tag.on("click", ".removeTag", function () {
        $(this).parent().remove();
        i--;
        console.log(45);
        if(i <= 4){
            addTagsBtn.removeAttr("disabled");
        }
        console.log(i);
    });

})