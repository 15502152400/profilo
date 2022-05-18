// 绑定上传封面的hover方法
function hoverPreImg() {
    // 显示蒙版
    $(".buttomDiv").removeClass("hidden");
}

// 绑定上传封面的out方法
function outPreImg() {
    // 隐藏蒙版
    $(".buttomDiv").addClass("hidden");
}

// 点击上传图片
function uploadImg() {
    $(".upLoadPreImg").click();
    doPreImg($(".upLoadPreImg"),$(".preImg"));
    doPreImg();
}

// 点击清除图片
function cleanImg() {

}