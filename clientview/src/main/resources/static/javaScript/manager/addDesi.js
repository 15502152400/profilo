
// 检查分类名
function checkDesiName(){
    // 去除分类名字前后空格
    let desiName = trimAndSet($(".desiName"));
    // 获取分类名字大小
    let desiNameSize = getBytesByUTF8(desiName);
    // 执行判断
    if (desiNameSize>globalData.maxDesiName){
        inputIsUnpass($(".desiName"),"形式名字应在"+globalData.maxDesiName+"字节以内");
    }else if (desiNameSize<globalData.minDesiName){
        inputIsUnpass($(".desiName"),"形式名字是必填项");
    }else{
        inputIsPass($(".desiName"));
    }
}

// 提交分类
function submit() {
    // 检查数据
    if($(".desiName").attr("data-ispass")!=1){
        alert("形式名字填写错误");
    }else {
        let params = {
            desiName:$(".desiName").val(),
        }
        $.post(globalInterface.saveDesi,params,(r) => {
            ajaxMsgHandle(r.message);
            $(".rightInnerArea").load(globalPath.addDesi);
        });
    }
}