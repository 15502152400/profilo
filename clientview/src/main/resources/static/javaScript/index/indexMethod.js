/*============================== 首页方法 ==============================*/

// 导航区hover效果
function hoverNav() {
    $(this).addClass("selectNav");
    globalData.currentNav.removeClass("selectNav");
}
function outNav() {
    $(this).removeClass("selectNav");
    globalData.currentNav.addClass("selectNav");
}

// 加载页面模块
function loadPage(pagePath,selector) {
    // 移除当前页的样式class
    globalData.currentNav.removeClass("selectNav");
    // 将传入的选择器赋值给当前页面
    globalData.currentNav = selector;
    // 为当前页面添加样式class
    selector.addClass("selectNav");
    // 加载页面
    $("#contentArea").load(pagePath);
}

// 点击导航切换
function clickNav(){
    // 重置当前页
    resetGlobalData();
    // 判断id，跳转首页
    if ($(this).attr("data-id")==1) {
        loadPage(globalPath.indexPath, $(this));
        // 判断id，跳转到市集
    }else if ($(this).attr("data-id")==5){
        loadPage(globalPath.marketPath, $(this));
        // 判断id，跳转二级通用页
    }else {
        loadPage(globalPath.profiloListPath,$(this));
    }
}

// 获取最新作品
function getNewProfilo(cateId,appendSelector){
    // 如果是全部分类则赋值当前一级分类id
    if (cateId==0||stringIsEmpty(cateId)){
        alert("请勿爬取作品");
    }
    // 封装参数
    let params = {
        cateId:cateId,
        currentPage:1,
        pageSize: globalData.indexPageSize
    }
    // 发起获取请求
    $.get(globalInterface.getWorksByCateId,params,(e) => {
        // 处理提示信息
        ajaxMsgHandle(e.message);
        // 获取作品列表
        let worksList = e.result;
        // 校验作品列表，如果没有作品
        if($.isEmptyObject(worksList)||worksList.length==0){
            let readingWork = "<p class='reading'>作品准备中，敬请期待......</p>";
            appendSelector.append(readingWork);
            return;
        }
        // 遍历列表追加作品
        $.each(worksList,(i,v) => {
            var div = "<div class='profileItem' data-id="+v.worksId+">"+
                "<!-- 左侧缩略图 -->"+
                // "<div class='itemImg'><img src="+v.coverPath+"/></div>"+
                "<div class='itemImg'></div>"+
                "<!-- 右侧作品信息 -->"+
                "<div class='itemInfo'>"+
                "<!-- 文字信息 -->"+
                "<div class='infoWord'>"+
                "<p class='itemTitle'>"+v.worksName+"</p>"+
                "<p class='itemDetail'>类型："+v.cateName+"</p>"+
                "<p class='itemDetail'>设计："+v.desiName+"</p>"+
                "<p class='itemDetail'>日期："+v.createTime+"</p>"+
                "</div>"+
                "</div>"+
                "</div>";
            appendSelector.append(div);
        });
    });
}

// 点击作品跳转到作品详情
function toprofiloDetail(){
    // 跳转页面
    $("#contentArea").load(globalPath.profiloItemDetail);
    // 获取作品并添加
    let params = {worksId:$(this).attr("data-id")}
    $.get(globalInterface.getWorkByWorkId,params,(e) => {
        let work = e.result;
        $(".detailWorkTitleCh").html(work.worksName);
        $(".detailWorkTitleEn").html(work.worksEnName);
        $(".profiloDetail").html(work.worksContent);
    });
}

/*============================== 通用二级页方法 ==============================*/

// 分页加载此二级页下所有作品
function loadProfiloByCateIdPage(){
    // 获取当前分类id
    let cateId = $(".selectICI").attr("data-id");
    // 如果是全部分类则赋值当前一级分类id
    if (cateId==0||stringIsEmpty(cateId)){
        cateId = globalData.currentNav.attr("data-id");
    }
    // 封装参数
    let params = {
        cateId:cateId,
        currentPage:globalData.currentPage,
        pageSize: globalData.pageSize
    }
    // 发起获取请求
    $.get(globalInterface.getWorksByCateId,params,(e) => {
        // 处理提示信息
        ajaxMsgHandle(e.message);
        // 清空原作品
        $(".itemArea").empty();
        // 获取作品列表
        let worksList = e.result;
        // 校验作品列表，如果没有作品
        if($.isEmptyObject(worksList)||worksList.length==0){
            let readingWork = "<p class='reading'>作品准备中，敬请期待......</p>";
            $(".itemArea").append(readingWork);
            $(".currentPage").html("当前页（0）");
            $(".countPage").html("总页数（0）");
            return;
        }
        // 获取分页信息
        let pagenation = e.pagenation;
        // 遍历列表追加作品
        $.each(worksList,(i,v) => {
            var div = "<div class='profileItem' data-id="+v.worksId+">"+
                "<!-- 左侧缩略图 -->"+
                // "<div class='itemImg'><img src="+v.coverPath+"/></div>"+
                "<div class='itemImg'></div>"+
                "<!-- 右侧作品信息 -->"+
                "<div class='itemInfo'>"+
                "<!-- 文字信息 -->"+
                "<div class='infoWord'>"+
                "<p class='itemTitle'>"+v.worksName+"</p>"+
                "<p class='itemDetail'>类型："+v.cateName+"</p>"+
                "<p class='itemDetail'>设计："+v.desiName+"</p>"+
                "<p class='itemDetail'>日期："+v.createTime+"</p>"+
                "</div>"+
                "</div>"+
                "</div>";
            $(".itemArea").append(div);
        });
        // 添加分页信息
        $(".currentPage").html("当前页（"+pagenation.currentPage+"）");
        $(".countPage").html("总页数（"+pagenation.countPage+"）");
        globalData.currentPage = pagenation.currentPage;
        globalData.countPage = pagenation.countPage;
    });
}

/*============================== 分页方法 ==============================*/

// hover pageSize页面
function hoverPageSize(){
    // 显示出所有pageSize
    $(".pageSizeItem").removeClass("hidden");
}
function outPageSize(){
    // 隐藏除当前pageSize意外的所有pageSize
    $(".pageSizeItem:not(.currentPageSize)").addClass("hidden");
}

// 点击下一页
function toNextPage() {
    // 判断是否为最后一页
    if (globalData.currentPage == globalData.countPage){
        return
    }
    // 当前页自增
    globalData.currentPage++;
    // 执行添加作品
    loadProfiloByCateIdPage();
}

// 点击上一页
function toPrePage() {
    // 判断是否为首页
    if (globalData.currentPage <= 1){
        return
    }
    // 当前页自减
    globalData.currentPage--;
    // 执行添加作品
    loadProfiloByCateIdPage();
}

// 点击首页
function toFirstPage() {
    // 判断是否为首页
    if (globalData.currentPage <= 1){
        return
    }
    // 当前页设为1
    globalData.currentPage = 1;
    // 执行添加作品
    loadProfiloByCateIdPage();
}

// 点击尾页
function toLastPage() {
    // 判断是否为最后一页
    if (globalData.currentPage == globalData.countPage){
        return
    }
    // 当前页设为最后页
    globalData.currentPage = globalData.countPage;
    // 执行添加作品
    loadProfiloByCateIdPage();
}

// 选择pageSize
function choosePageSize() {
    // 改变全局数据中的pageSize
    globalData.pageSize = $(this).attr("data-pageSize");
    // 执行添加作品
    loadProfiloByCateIdPage();
}

/*============================== 二级分类方法 ==============================*/

// 获取当前一级页所属的二级分类
function getInnerCate(){
    // 获取一级分类id
    var params = {
        cateId:globalData.currentNav.attr("data-id")
    }
    // 发起get请求
    $.get(globalInterface.getCateByCateId,params,(e) => {
        // 弹出提示信息
        ajaxMsgHandle(e.message);
        // 获取分类列表
        let cateList = e.result;
        // 遍历列表追加
        $.each(cateList,(i,v) => {
            let div = "<div class='innerClassifyItem' data-id="+v.cateId+">"+v.cateName+"</div>";
            $(".innerClassify").append(div);
        });
    });
}

// 点击选择二级分类
function chooseInnerCate(){
    // 重置当前页
    resetGlobalData();
    // 移除当前二级分类的样式class
    $(".selectICI").removeClass("selectICI");
    // 为被点击的二级分类添加样式class
    $(this).addClass("selectICI");
    // 执行添加作品
    loadProfiloByCateIdPage();
}
