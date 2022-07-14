// 加载一级分类
function loadParentCate() {
    $.get(globalInterface.getCateByParentCateId,{cateId:0},(r) => {
        ajaxMsgHandle(r.message);
        const cates = r.result;
        for (let i=0;i<cates.length;i++){
            let option = "<option value='"+cates[i].cateId+"' class='cateItem'>"+cates[i].cateName+"</option>";
            $(".parentCateId").append(option);
        }
    });
}

// 检查分类名
function checkCateName(){
    // 去除分类名字前后空格
   let cateName = trimAndSet($(".cateName"));
   // 获取分类名字大小
   let cateNameSize = getBytesByUTF8(cateName);
   // 执行判断
   if (cateNameSize>globalData.maxCateName){
       inputIsUnpass($(".cateName"),"分类名字应在"+globalData.maxCateName+"字节以内");
   }else if (cateNameSize<globalData.minCateName){
       inputIsUnpass($(".cateName"),"分类名字是必填项");
   }else{
       inputIsPass($(".cateName"));
   }
}

// 提交分类
function submit() {
    // 检查数据
    if($(".cateName").attr("data-ispass")!=1){
        alert("分类名字填写错误");
    }else if ($(".parentCateId").val()<0){
        alert("必须选择一级分类");
    }else {
        let params = {
            cateName:$(".cateName").val(),
            parentCateId:$(".parentCateId").val()
        }
        $.post(globalInterface.saveCate,params,(r) => {
            ajaxMsgHandle(r.message);
        });
    }
}