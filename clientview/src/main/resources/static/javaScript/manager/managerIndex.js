// 点击一级选项
function clickTitle1(){
    const me = $(this);
    // 如果已经被选择
    if(me.parent().hasClass("selected")){
        // 隐藏二级选项
        me.siblings().addClass("hidden");
        // 取消选择的class
        me.parent().removeClass("selected");
        // 如果没有被选择
    }else {
        // 显示二级选项
        me.siblings().removeClass("hidden");
        // 添加选择class
        me.parent().addClass("selected");
    }
}

// 点击二级选项
function clickTitle2(){
    // 读取html
    const me = $(this);
    $(".rightInnerArea").load(me.attr("data-url"));
    // 重置全局数据
    resetGlobalData();
}

// 点击退出登陆
function doLogOut(){
    // 发送退出请求
    $.post(globalInterface.doLogOut);
    // 重定向到主页
    window.location = globalPath.toIndex;
    // 重置全局数据
    globalData.currentPage = 1;
    globalData.editor = Object;
}

// 点击关闭弹窗
function popupClose() {
    if ($(this).parent().next().find("input").length!=0){
        if (confirm("现在退出将不会保存")){
            // 隐藏弹窗
            $(this).parent().parent().prev().addClass("hidden");
            $(this).parent().parent().addClass("hidden");
            // 移除模糊滤镜
            $(".managerDIv").removeClass("popupBlurFilter");
        }
    }else {
        // 隐藏弹窗
        $(this).parent().parent().prev().addClass("hidden");
        $(this).parent().parent().addClass("hidden");
        // 移除模糊滤镜
        $(".managerDIv").removeClass("popupBlurFilter");
    }

    //清空内容
    $(this).parent().next().empty();
}

// 点击打开第一层弹窗
function popupOpen() {
    // 显示弹窗
    $(".popup").removeClass("hidden");
    $(".popupMask").removeClass("hidden");
    // 添加模糊滤镜
    $(".managerDIv").addClass("popupBlurFilter");
}

// 点击打开第二层弹窗
function popupOpen2() {
    // 显示弹窗
    $(".popup2").removeClass("hidden");
    $(".popupMask2").removeClass("hidden");
    // 添加模糊滤镜
    $(".managerDIv").addClass("popupBlurFilter");
}