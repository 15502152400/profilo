// 分类行中的上级分类选择框代码
var fristCateSelect;

// 当前筛选条件
var query = {
    parentCateId:-1,
    showOrder:0
}

// 正在修改某个分类而没有保存
var isUpdating = false;

// 正在被修改的分类
var isUpdatingCate;

// 加载一级分类
function loadFirstCate(){
    // 表格中的上级分类选择框,data-ispass=0：值不合法、data-ispass=1：值合法
    let select = "<select class='cateParent contentInput' disabled data-ispass='1'><option value='0'>一级分类</option>";
    // 表格中的上级分类选择框结尾
    const selectEnd = "</select>"
    $.ajax({
        url: globalInterface.getCateByParentCateId,
        async: false,
        data: {cateId: 0},
        type: "GET",
        success: (r) => {
            const cateItem = r.result;
            for (let i = 0; i < cateItem.length; i++) {
                const cate = "<option value='" + cateItem[i].cateId + "'>" + cateItem[i].cateName + "</option>";
                $(".firstCate").append(cate);
                select+=cate;
            }
        }
    });
    // 返回拼接好的上级分类选择框
    fristCateSelect = select+selectEnd;
}

// 分页加载分类
function loadCate() {
    // 清空原有分类
    $(".cateItem").remove();
    // 加载分类
    let params = {
        currentPage:globalData.currentPage,
        pageSize:globalData.pageSize,
        showOrder:query.showOrder,
        parenCateId:query.parentCateId
    }
    $.get(globalInterface.getAllCateByParentCateId,params,(r) => {
       const p = r.pagenation;
       const o = r.result;

        // 校验作品列表，如果没有作品
        if($.isEmptyObject(o)||o.length==0){
            let readingWork = "<tr><td colspan='5' class='cateItem'><p class='reading'>当前无分类，请添加......</p></td></tr>";
            $(".tableArea").append(readingWork);
            $(".currentPage").html("当前页（0）");
            $(".countPage").html("总页数（0）");
            return;
        }

        // 遍历列表追加作品
        $.each(o,(i,v) => {
            let tr = "<tr class='cateItem' data-cateId='"+v.cateId+"'>" +
                        "<td class='tableContent'>" +
                            "<input type='text' class='cateName contentInput' value='"+v.cateName+"' disabled data-ispass='1' data-catename='"+v.cateName+"'/>" +
                        "</td>" +
                        "<td class='tableContent'>" +
                            fristCateSelect+
                        "</td>" +
                        "<td class='tableContent'>" +
                            v.createTime +
                        "</td>" +
                        "<td class='tableContent'>" +
                            v.updateTime +
                        "</td>" +
                        "<td class='tableContent'>" +
                            "选中：" +
                            "<input type='checkbox' class='checkThis' data-cateId='"+v.cateId+"'/>" +
                            "<div class='deleteThis contentButton'>删除</div>" +
                            "<div class='updateThis contentButton'>修改</div>" +
                            "<div class='saveUpdate contentButton' data-ischange='0' style='display: none'>保存</div>" +
                        "</td>" +
                     "</tr>";
            $(".tableArea").append(tr);
            $(".cateItem").last().find(".cateParent").val(v.parentCateId);
            $(".cateItem").last().find(".cateParent").attr("data-parentcateid",v.parentCateId);
        });

        // 添加分页信息
        $(".currentPage").html("当前页（"+p.currentPage+"）");
        $(".countPage").html("总页数（"+p.countPage+"）");
        globalData.currentPage = p.currentPage;
        globalData.countPage = p.countPage;
    });
}

// 点击查询
function doQuery() {
    // 修改查询条件对象中的数据
    query.showOrder=$(".createTimeOrder").val();
    query.parentCateId=$(".firstCate").val();
    // 重置当前页
    resetCurrentPage();
    // 执行查询
    loadCate();
}

// 点击全选
function checkedAll() {
    if ($(this).prop("checked")==true){
        $(".checkThis").prop("checked",true);
    }else {
        $(".checkThis").prop("checked",false);
    }
}

// 删除所选
function deleteChecked() {
    let checkedCateId = new Array();
    $(".checkThis[type='checkbox']:checked").each((i,v) => {
        checkedCateId.push($(v).attr("data-cateId"));
    });

    if (checkedCateId.length<1){
        alert("当前没选中分类");
        return;
    }

    if (!confirm("确认删除"+checkedCateId.length+"个分类吗")){
        return;
    }

    const params = {cateIdList:checkedCateId.toString()};

    $.get(globalInterface.deleteCatesByCateIdList,params,(r) => {
        ajaxMsgHandle(r.message);
        resetCurrentPage();
        loadCate();

    });
}

// 点击删除
function deleteThis() {
    if (!confirm("确认删除此分类吗？")){
        return;
    }

    const params = {
        cateId:$(this).siblings(".checkThis").attr("data-cateId")
    };

    $.get(globalInterface.deleteCateByCateId,params,(r) => {
        ajaxMsgHandle(r.message);
        resetCurrentPage();
        loadCate();
    });

}

// 点击修改
function updateThis() {

    // 检查锁
    if (isUpdating){
        alert("只能同时修改一个分类，请先保存正在修改的分类："+isUpdatingCate.val());
        return;
    }

    // 加锁
    isUpdating = true;

    // 注册此分类到本页变量
    isUpdatingCate = $(this).parents(".cateItem").find(".cateName");

    // 解锁输入框
    $(this).parents(".cateItem").find(".cateName,.cateParent").prop("disabled",false);

    // 隐藏修改按钮、显示保存按钮
    showSaveAndHidenUpdate($(this));

    // 让分类名获取焦点
    $(this).parents(".cateItem").find(".cateName").focus();

}

// 点击保存
function saveUpdate() {
    // 如果未改动
    if ($(this).attr("data-ischange")==0){
        // 后置处理
        saveHandle($(this));
        return;
    }

    const cateNameInput = $(this).parents(".cateItem").find(".cateName");
    const parentCateInput = $(this).parents(".cateItem").find(".cateParent");

    // 如果校验失败
    if (cateNameInput.attr("data-ispass")==0){
        alert("您有数据填写错误，请修改");
        return;
    }

    // 校验成功
    const params = {
        cateName:cateNameInput.val(),
        parentCateId:parentCateInput.val(),
        cateId:$(this).siblings(".checkThis").attr("data-cateId")
    }
    // 发起保存请求
    $.post(globalInterface.updateCateByCateId,params,(r) => {
        ajaxMsgHandle(r.message);
        saveHandle($(this));
    });
}

// 显示修改按钮，隐藏保存按钮
function showUpdateAndHiddenSave(thisSelection) {
    thisSelection.siblings(".updateThis").css("display","inline-block");
    thisSelection.css("display","none");
}

// 隐藏修改按钮，显示保存按钮
function showSaveAndHidenUpdate(thisSelection) {
    thisSelection.siblings(".saveUpdate").css("display","inline-block");
    thisSelection.css("display","none");
}

// 保存成功或未改动的后置处理
function saveHandle(thisSelection) {
    // 解锁
    isUpdating = false;

    // 注销本页变量
    isUpdatingCate = null;

    // 锁定输入框
    thisSelection.parents(".cateItem").find(".cateName,.cateParent").prop("disabled",true);

    // 隐藏保存按钮、显示修改按钮
    showUpdateAndHiddenSave(thisSelection);

}

// 校验输入分类名的合法性
function checkInput() {
    // 去除前后空格
    const trim = trimAndSet($(this));
    // 校验合法性
    const checkResult = checkString(trim,globalData.minCateName,globalData.maxCateName);
    if (!checkResult){
        $(this).parent().css("height","60");
        $(this).focus();
        inputIsUnpass($(this),"分类名必须输入且不大于30字节");
    }else {
        inputIsPass($(this));
        $(this).parent().css("height","38");

    }
}

// 校验数据是否改动
function isChange() {
    // 判断哪一个input引发的事件
    if ($(this).is(".cateName")){
        const select = $(this).parent().next().find(".cateParent");
        // 如果分类名和上级分类都没改变
        if ($(this).val()===$(this).attr("data-catename") && select.val()===select.attr("data-parentcateid")){
            $(this).parent().siblings().last().find(".saveUpdate").attr("data-ischange","0");
            return false;
        // 如果发生改变
        }else {
            $(this).parent().siblings().last().find(".saveUpdate").attr("data-ischange","1");
            return true;
        }
    }else {
        const input = $(this).parent().prev().find(".cateName");
        // 如果分类名和上级分类都没改变
        if ($(this).val()===$(this).attr("data-parentcateid") && input.val()===input.attr("data-catename")){
            $(this).parent().siblings().last().find(".saveUpdate").attr("data-ischange","0");
            return false;
        // 如果发生改变
        }else {
            $(this).parent().siblings().last().find(".saveUpdate").attr("data-ischange","1");
            return true;
        }
    }
}

/* =======================================> 分页方法 <======================================= */

// 点击下一页
function toNextPage() {
    // 判断是否为最后一页
    if (globalData.currentPage == globalData.countPage){
        return
    }
    // 当前页自增
    globalData.currentPage++;
    // 执行添加
    loadCate();
}

// 点击上一页
function toPrePage() {
    // 判断是否为首页
    if (globalData.currentPage <= 1){
        return
    }
    // 当前页自减
    globalData.currentPage--;
    // 执行添加
    loadCate();
}

// 点击首页
function toFirstPage() {
    // 判断是否为首页
    if (globalData.currentPage <= 1){
        return
    }
    // 当前页设为1
    globalData.currentPage = 1;
    // 执行添加
    loadCate();
}

// 点击尾页
function toLastPage() {
    // 判断是否为最后一页
    if (globalData.currentPage == globalData.countPage){
        return
    }
    // 当前页设为最后页
    globalData.currentPage = globalData.countPage;
    // 执行添加
    loadCate();
}

// 选择pageSize
function choosePageSize() {
    // 重置currentPage
    resetCurrentPage();
    // 改变全局数据中的pageSize
    globalData.pageSize = $(this).attr("data-pageSize");
    $(".pageSizeItem").removeClass("currentPageSize");
    $(this).addClass("currentPageSize");
    // 执行添加
    loadCate();
}