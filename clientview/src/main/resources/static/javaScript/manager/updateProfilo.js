// 点击全选
function clickCheckAll() {
    if ($(this).prop("checked")===true){
        $(".check").prop("checked",true);
    }else{
        $(".check").prop("checked",false);
    }
}

// 点击删除所选
function deleteChecked() {
    // 获取选中的checkbox
    const dp = $(".check[type='checkbox']:checked");
    // 确认删除操作
    if (!confirm("确认删除"+dp.length+"个作品吗")){
        return;
    }
    // 创建数组,封装ID
    let delProId = new Array();
    for (let i=0;i<dp.length;i++){
        delProId[i] = dp[i].getAttribute("data-worksid");
    }
    console.log(delProId);
    // 发起删除请求
    $.get(globalInterface.deleteWorksByWorkIds,{workIds:delProId.toString()},(r) => {
        ajaxMsgHandle(r.message);
        // 重新加载作品
        resetGlobalData();
        loadProfilo();
    });
}

// 删除此作品
function deleteThisWork() {
    // 确认删除操作
    if (!confirm("确认删除此作品吗")){
        return;
    }
    // 执行删除
    $.get(globalInterface.deleteWorkByWorkId,{workId:$(this).parents(".profiloItem").attr("data-worksId")},(r) => {
        ajaxMsgHandle(r.message);
        // 重新加载作品
        resetGlobalData();
        loadProfilo();
    });
}

// 更新此作品
function updateThisWork() {

    // 绑定当前作品id到全局数据
    globalData.currentProfiloId = $(this).parents(".profiloItem").attr("data-worksId");
    // 清除弹窗原有内容
    $(".popupInner").empty();
    // 打开弹窗
    popupOpen();
    // 加载页面进入弹窗
    $(".popupInner").load(globalPath.updateProfiloPage);
    // 修改弹窗1标题
    $(".popupTitle").html("修改作品");

}

// 加载作品
function loadProfilo() {
    // 准备参数
    const params = {
        cateId:$(".selectTitle2").val()!=0?$(".selectTitle2").val():$(".selectTitle1").val(),
        desiId:$(".indexDesi").val(),
        timeOrder:$(".timeOrder").val(),
        currentPage:globalData.currentPage,
        pageSize:globalData.pageSize
    }
    // 发起请求
    $.get(globalInterface.getWorksByCateDesi,params,(r) => {
        // 处理提示信息
        ajaxMsgHandle(r.message);
        // 清空原作品
        $(".profiloDiv").empty();
        // 获取作品列表
        let worksList = r.result;
        // 校验作品列表，如果没有作品
        if($.isEmptyObject(worksList)||worksList.length==0){
            let readingWork = "<p class='reading'>作品准备中，敬请期待......</p>";
            $(".profiloDiv").append(readingWork);
            $(".currentPage").html("当前页（0）");
            $(".countPage").html("总页数（0）");
            return;
        }
        // 获取分页信息
        let pagenation = r.pagenation;
        // 遍历列表追加作品
        $.each(worksList,(i,v) => {
            var div = "<div class='profiloItem' data-worksId='"+v.worksId+"'>" +
                        "<img class='profiloCover' src='"+v.coverImg+"'>" +
                        "<div class='profiloTitle'>"+v.worksName+"</div>" +
                        "<div class='profiloDetail'>"+v.cateName+"&nbsp;|&nbsp;"+v.desiName+"&nbsp;|&nbsp;"+v.createTime+"</div>" +
                        "<div class='bottomArea'>" +
                            "<div class='deleteButtom'>删除此作品</div>" +
                            "<div class='updateButtom'>修改此作品</div>" +
                            "<div class='checkDiv'>选择：<input type='checkbox' class='check' data-worksId='"+v.worksId+"'/></div>" +
                        "</div>" +
                        "</div>"
            $(".profiloDiv").append(div);
        });

        // 添加分页信息
        $(".currentPage").html("当前页（"+pagenation.currentPage+"）");
        $(".countPage").html("总页数（"+pagenation.countPage+"）");
        globalData.currentPage = pagenation.currentPage;
        globalData.countPage = pagenation.countPage;
    });
}

// 初始化加载一级分类
function addCate1 (){
    // 发起请求
    $.get(globalInterface.getCateByParentCateId,{cateId:0},(r) => {
        // 有错误信息则弹出
        ajaxMsgHandle(r.message);
        // 循环重新加载一级分类
        const obj = r.result;
        for (let i=0;i<obj.length;i++){
            let option = "<option value ="+obj[i].cateId+" class='cate1Item'>"+obj[i].cateName+"</option>"
            $(".selectTitle1").append(option);
        }
    });
}

// 一级分类变化触发二级分类改变
function addCate2 (){
    // 发起请求
    $.get(globalInterface.getCateByParentCateId,{cateId:$(".selectTitle1").val()},(r) => {
        // 移除原有二级分类
        $(".cate2Option").remove();
        const obj = r.result;
        // 若当前一级分类下没有二级分类
        if (obj==null||typeof (obj) == "undefined"||obj.length==0){
            // 移除提示模块
            $(".cate2Alert").remove();
            // 重新加载提示模块
            $(".selectTitle2").append("<option value='-1' class='cate2Alert' disabled selected style='display:none;'>"+r.message+"</option>");
            // 不再执行此方法
            return;
        }
        // 移除提示模块
        $(".cate2Alert").remove();
        // 循环重新加载二级分类
        for (let i=0;i<obj.length;i++){
            let option = "<option value ="+obj[i].cateId+" class='cate2Option'>"+obj[i].cateName+"</option>"
            $(".selectTitle2").append(option);
        }
    })
}

// 初始化加载设计形式
function addindexDesi(){
    $.get(globalInterface.getAllDesi,(r) => {
        const obj = r.result;
        // 有错误信息则弹出
        ajaxMsgHandle(r.message);
        // 循环添加设计形式
        for (let i=0;i<obj.length;i++){
            let option = "<option value="+obj[i].desiId+" class='desiOption'>"+obj[i].desiName+"</option>";
            $(".indexDesi").append(option);
        }
    });
}

// 点击查询
function doSelect() {
    // 重置全局数据
    resetGlobalData();

    // 清除原有作品
    $(".profiloDiv").empty();

    // 加载作品
    loadProfilo();
}

/*============================== 分页方法 ==============================*/

// 点击下一页
function toNextPage() {
    // 判断是否为最后一页
    if (globalData.currentPage == globalData.countPage){
        return
    }
    // 当前页自增
    globalData.currentPage++;
    // 执行添加作品
    loadProfilo();
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
    loadProfilo();
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
    loadProfilo();
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
    loadProfilo();
}

// 选择pageSize
function choosePageSize() {
    // 重置currentPage
    resetCurrentPage();
    // 改变全局数据中的pageSize
    globalData.pageSize = $(this).attr("data-pageSize");
    $(".pageSizeItem").removeClass("currentPageSize");
    $(this).addClass("currentPageSize");
    // 执行添加作品
    loadProfilo();
}