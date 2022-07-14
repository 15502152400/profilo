// 初始化富文本
function startEditor() {
    // 创建富文本
    let E = window.wangEditor;
    let editor = new E("#editor");

    // 注册到全局数据
    globalData.editor = editor;

    // 下面两个配置，使用其中一个即可显示“上传图片”的tab。但是两者不要同时使用！！！
    editor.customConfig.uploadImgShowBase64 = true;      // 使用 base64 保存图片
    // editor.customConfig.uploadImgServer = '/upload';  // 上传图片到服务器
    // 设置z-index
    editor.customConfig.zIndex = 100

    // 开始创建
    editor.create();
}

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
}

// 图片上传完毕显示预览
function preImg() {
    const imgUrl = doPreImg($(".upLoadPreImg"),$(".preImg"));
    // 校验图片大小
    if (getImgSize($(this))>=globalData.profiloCoverSize*1024*1024){
        inputIsUnpass($(this),"图片太大,请修改");
        return;
    }
    // 校验图片宽高
    let img = new Image();
    img.src = imgUrl;
    img.onload = () => {
        if (img.width!=globalData.profiloCoverWidth||img.height!=globalData.profiloCoverHeight){
            inputIsUnpass($(this),"图片宽或高不符合要求");
            return;
        }
    }
    inputIsPass($(this));
}

// 点击清除图片
function cleanImg() {
    cleanPreImg($(".upLoadPreImg"),$(".preImg"));
}

// 校验中文标题
function checkNameCh(){
    // 去除输入框内字符串首尾空格
    const trim = trimAndSet($(this));
    if (getBytesByUTF8(trim)>=globalData.maxProfiloNameCh){
        inputIsUnpass($(this),"作品名字太长");
    }else if (getBytesByUTF8(trim)<globalData.minProfiloNameCh){
        inputIsUnpass($(this),"作品名字太短");
    }else{
        inputIsPass($(this));
    }
}

// 校验英文标题
function checkNameEn(){
    // 去除输入框内字符串首尾空格以及中文
    const enTxt = onlyEn($(this));
    if (getBytesByUTF8(enTxt)>=globalData.maxProfiloNameEn){
        inputIsUnpass($(this),"作品名字太长");
    }else if (getBytesByUTF8(enTxt)<globalData.minProfiloNameEn){
        inputIsUnpass($(this),"作品名字太短");
    }else{
        inputIsPass($(this));
    }
}

// 校验二级分类
function checkSecondCate() {
    let val = $(".secondCate").val();
    if (!val||val<1||typeof val=="undefined"){
        return false;
    }else {
        return true;
    }
}

// 校验设计形式
function checkDesi() {
    if ($(".desi").val()<1||!$(".desi").val()||typeof ($(".desi").val())=="undefined"){
        return false;
    }else {
        return true;
    }
}

// 初始化加载一级分类
function addFirstCate (){
    // 发起请求
    $.get(globalInterface.getCateByParentCateId,{cateId:0},(r) => {
        // 有错误信息则弹出
        ajaxMsgHandle(r.message);
        // 循环重新加载一级分类
        const obj = r.result;
        for (let i=0;i<obj.length;i++){
            let option = "<option value ="+obj[i].cateId+" class='firstCateItem'>"+obj[i].cateName+"</option>"
            $(".firstCate").append(option);
        }
    });
}

// 一级分类变化触发二级分类改变
function addSecondCate (){
    // 发起请求
    $.get(globalInterface.getCateByParentCateId,{cateId:$(".firstCate").val()},(r) => {
        // 移除原有二级分类
        $(".secondCateItem").remove();
        const obj = r.result;
        // 若当前一级分类下没有二级分类
        if (obj==null||typeof (obj) == "undefined"||obj.length==0){
            // 移除提示模块
            $(".secondCateOption").remove();
            // 重新加载提示模块
            $(".secondCate").append("<option value='-1' class='secondCateOption' disabled selected style='display:none;'>"+r.message+"</option>");
            // 不再执行此方法
            return;
        }
        // 移除提示模块
        $(".secondCateOption").remove();
        // 重新添加提示模块
        $(".secondCate").append("<option value='-1' class='secondCateOption' disabled selected style='display:none;'>"+"请选择二级分类"+"</option>");
        // 循环重新加载二级分类
        for (let i=0;i<obj.length;i++){
            let option = "<option value='"+obj[i].cateId+"' class='secondCateItem'>"+obj[i].cateName+"</option>"
            $(".secondCate").append(option);
        }
    })
}

// 初始化加载设计形式
function addDesi(){
    $.get(globalInterface.getAllDesi,(r) => {
        const obj = r.result;
        // 有错误信息则弹出
        ajaxMsgHandle(r.message);
        // 循环添加设计形式
        for (let i=0;i<obj.length;i++){
            let option = "<option value='"+obj[i].desiId+"' class='desiOption'>"+obj[i].desiName+"</option>";
            $(".desi").append(option);
        }
    });
}

// 点击预览
function doPreLook(){
    // 打开弹窗
    popupOpen();
    // 清除原来内容
    $(".popupInner").empty();
    // 设置弹窗标题
    $(".popupTitle").html("作品预览");
    // 加载预览内容
    let inner = globalData.editor.txt.html();
    $(".popupInner").html(inner);
}

// 点击提交
function doSubmit(){

    // 全局检查数据是否合法
    // let checkResult = true;
    if ($("[data-ispass='0']").length>0||!checkSecondCate()||!checkDesi()){
        alert("有信息未能正确填写，请修改");
        return;
    }

    // 获取参数
    let params = new FormData();
    params.append("worksId",globalData.currentProfiloId);
    params.append("worksName",$(".profiloNameCh").val());
    params.append("worksEnName",$(".profiloNameEn").val());
    params.append("coverImg",$(".upLoadPreImg").prop("files")[0]);
    params.append("worksContent",globalData.editor.txt.html().toString());
    params.append("worksCateId",$(".secondCate").val());
    params.append("worksDesiId",$(".desi").val());

    // 发起请求
    $.ajax({
        type:"post",
        url:globalInterface.doSaveProfilo,
        data:params,
        async:false,
        cache:false,
        contentType:false,
        processData:false,
        success:(result) => {
            ajaxMsgHandle(result.message);
            $(".rightInnerArea").load(globalPath.addProfilo);
        }
    })

}