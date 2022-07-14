// 正在修改某个时间形式而没有保存
var isUpdating = false;

// 正在被修改的分类
var isUpdatingDesi;

function loadDesi() {
    // 清空原有形式
    $(".desiItem").remove();
    // 获取参数
    let param = {
        currentPage:globalData.currentPage,
        pageSize:globalData.pageSize,
        showOrder:$(".createTimeOrder").val()
    }
    // 发起查询
    $.get(globalInterface.getAllDesiByPagenation,param,(r) => {

        ajaxMsgHandle(r.message);
        const list = r.result;
        const pageNation = r.pagenation;

        // 校验形式列表，如果没有形式
        if($.isEmptyObject(list)||list.length==0){
            let readingWork = "<tr><td colspan='5' class='cateItem'><p class='reading'>当前无分类，请添加......</p></td></tr>";
            $(".tableArea").append(readingWork);
            $(".currentPage").html("当前页（0）");
            $(".countPage").html("总页数（0）");
            return;
        }

        // 遍历添加
        $.each(list,(i,v) => {
            let tr = "<tr class= 'desiItem'>"
                        +"<td class= 'tableContent'>"
                            +"<input type='text' class='desiName contentInput' value='"+v.desiName+"' placeholder='"+v.desiName+"' disabled data-ispass='1' data-desiName='"+v.desiName+"'/>"
                        +"</td>"
                        +"<td class='tableContent'>"
                            +v.createTime
                        +"</td>"
                        +"<td class='tableContent'>"
                            +v.updateTime
                        +"</td>"
                        +"<td class='tableContent'>"
                            +"选中:"
                            +"<input type='checkbox' class='checkThis' data-id='"+v.desiId+"'/>"
                            +"<div class='deleteThis contentButton'>删除</div>"
                            +"<div class='updateThis contentButton'>修改</div>"
                            +"<div class='saveUpdate contentButton' data-ischange='0' style='display: none'>保存</div>"
                        +"</td>"
                     +"</tr>";
            $(".tableArea").append(tr);
        })

        // 添加分页
        $(".currentPage").html("当前页（"+pageNation.currentPage+"）");
        $(".countPage").html("总页数（"+pageNation.countPage+"）");
        globalData.currentPage = pageNation.currentPage;
        globalData.countPage = pageNation.countPage;
    })
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
    let checkedDesiId = new Array();
    $(".checkThis[type='checkbox']:checked").each((i,v) => {
        checkedDesiId.push($(v).attr("data-id"));
    });

    if (checkedDesiId.length<1){
        alert("当前没选中分类");
        return;
    }

    if (!confirm("确认删除"+checkedDesiId.length+"个分类吗")){
        return;
    }

    const params = {desiIdList:checkedDesiId.toString()};

    $.get(globalInterface.deleteDesiByDesiIdList,params,(r) => {
        ajaxMsgHandle(r.message);
        resetCurrentPage();
        loadDesi();
    });
}

// 点击删除
function deleteThis() {
    if (!confirm("确认删除此分类吗？")){
        return;
    }

    const params = {
        desiId:$(this).siblings(".checkThis").attr("data-id")
    };

    $.get(globalInterface.deleteDesiByDesiId,params,(r) => {
        ajaxMsgHandle(r.message);
        resetCurrentPage();
        loadDesi();
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
    isUpdatingDesi = $(this).parents(".desiItem").find(".desiName");

    // 解锁输入框
    $(this).parents(".desiItem").find(".desiName").prop("disabled",false);

    // 隐藏修改按钮、显示保存按钮
    showSaveAndHidenUpdate($(this));

    // 让分类名获取焦点
    $(this).parents(".desiItem").find(".desiName").focus();

}

// 点击保存
function saveUpdate() {
    // 如果未改动
    if ($(this).attr("data-ischange")==0){
        // 后置处理
        saveHandle($(this));
        return;
    }

    const desiNameInput = $(this).parents(".desiItem").find(".desiName");

    // 如果校验失败
    if (desiNameInput.attr("data-ispass")==0){
        alert("您有数据填写错误，请修改");
        return;
    }

    // 校验成功
    const params = {
        desiName:desiNameInput.val(),
        desiId:$(this).siblings(".checkThis").attr("data-id")
    }
    // 发起保存请求
    $.post(globalInterface.updateDesiByDesiId,params,(r) => {
        ajaxMsgHandle(r.message);
        saveHandle($(this));
    });
}

// 校验输入设计形式名的合法性
function checkInput() {
    // 去除前后空格
    const trim = trimAndSet($(this));
    // 校验合法性
    const checkResult = checkString(trim,globalData.minDesiName,globalData.maxDesiName);
    if (!checkResult){
        $(this).parent().css("height","60");
        $(this).focus();
        inputIsUnpass($(this),"设计形式名必须输入且不大于"+globalData.maxDesiName+"字节");
    }else {
        inputIsPass($(this));
        $(this).parent().css("height","38");
    }
}

// 校验数据是否改动
function isChange() {

    // 如果没改变
    if ($(this).val()===$(this).attr("data-desiName")){
        $(this).parent().siblings().last().find(".saveUpdate").attr("data-ischange","0");
        return false;
        // 如果发生改变
    }else {
        $(this).parent().siblings().last().find(".saveUpdate").attr("data-ischange","1");
        return true;
    }

}

// 保存成功或未改动的后置处理
function saveHandle(thisSelection) {
    // 解锁
    isUpdating = false;

    // 注销本页变量
    isUpdatingDesi = null;

    // 锁定输入框
    thisSelection.parents(".desiItem").find(".desiName").prop("disabled",true);

    // 隐藏保存按钮、显示修改按钮
    showUpdateAndHiddenSave(thisSelection);

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
    loadDesi();
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
    loadDesi();
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
    loadDesi();
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
    loadDesi();
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
    loadDesi();
}