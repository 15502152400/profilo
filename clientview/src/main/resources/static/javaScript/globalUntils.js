/*============================== Ajax方法 ==============================*/

// 重置全局数据
function resetGlobalData() {
    globalData.currentPage = 1;
    globalData.editor = Object;
    globalData.currentProfiloId=0;
}
// 重置当前页
function resetCurrentPage() {
    globalData.currentPage = 1;
}


// 如果有信息则弹出信息
function ajaxMsgHandle(str){
    if (!stringIsEmpty(str)){
        alert(str);
    }
}

/*======================》 图片工具 《======================*/

/*
* 预览图片
* upDiv: 上传图片按钮的标签选择器
* showDiv：需要显示预览图片的标签选择器
* return: 图片src
*/
function doPreImg(upDiv,showDiv) {
    let img = upDiv.prop("files")[0];
    let URL = window.URL;
    let imgUrl = URL.createObjectURL(img);
    showDiv.attr("src",imgUrl);
    return imgUrl;
}

/*
* 清除预览图片
* upDiv:需要清除的input容器
* cleanDiv: 图片预览容器的选择器
*/
function cleanPreImg(upDiv,cleanDiv) {
    upDiv.val("");
    cleanDiv.removeAttr("src");
    console.log(upDiv.prop("files")[0]);
}

/*
* 返回图片大小
* imgSelection: 图片上传input的选择器
*/
function getImgSize(imgSelection) {
    const img = imgSelection.prop("files")[0];
    return img.size;
}


/*======================》 表单项工具 《======================*/

/*
*  表单项校验不通过
*  selection: 被检查的选择器
*  altWord: 提示语言
*/
function inputIsUnpass(selection,altWrod) {
    // 表单项不合法
    selection.attr("data-ispass",0);
    if(stringIsEmpty(altWrod)){
        return;
    }
    // 如果有提示文字则直接替换
    if (selection.next().hasClass("altWord")){
         selection.next().html(altWrod);
         return;
    }
    // 没有则追加
    const alt = "<p class='altWord' style='font-size: 10px;color: crimson;text-align: center;margin-top: 5px;'>"+altWrod+"</p>";
    // 如果改input不是隐藏的
    if(!selection.hasClass("hidden")){
        selection.after(alt);
    }else{
        selection.parent().after(alt);
    }
}

/*
*  表单项校验通过
*  selection: 被校验的选择器
*/
function inputIsPass(selection) {
    // 表单项如果有提示文字则删除
    if (!selection.hasClass("hidden")){
        if (selection.next().hasClass("altWord")){
            selection.next().remove();
        }
    }else {
        if (selection.parent().next().hasClass("altWord")){
            selection.parent().next().remove();
        }
    }

    // 设置状态属性为通过
    selection.attr("data-ispass",1);
}

/*======================》 字符串工具 《======================*/

// 字符串判空
function stringIsEmpty(str){
    if (str==null||typeof(str)=="undefined"||str==""){
        return true;
    }else {
        return false;
    }
}

/*
*  去除输入框内字符串首尾空格
*  selection: 被校验表单项的选择器
*  return: 去除首位空格后的字符串
*/
function trimAndSet(selection){
    const trim=selection.val().trim().toString();
    selection.val(trim);
    return trim;
}

/*
*  去除输入框内字符串的中文
*  selection: 被校验表单项的选择器
*  return: 处理后的字符串
*/
function onlyEn(selection){
    // 去除首尾空格
    let enTxt = trimAndSet(selection);
    // 替换中文
    enTxt=enTxt.replace(/[\u4e00-\u9fa5]/ig,'')
    selection.val(enTxt);
    return enTxt;
}

/* 判断字符串为空的条件 */
String.prototype.isEmpty = function () {
    const s1 = this.replace(/[\r\n]/g, '').replace(/[ ]/g, ''),
        s2 = (s1 == '') ? true : false;
    return s2;
};

/*
*  返回字符串以UTF-8计算的字节数
*  checkString: 被计算的字符串
*/
function getBytesByUTF8(checkString){
    let stringLength=0;
    const regx=/^[\u0000-\u00ff]$/;
    for(var i=0;i<checkString.length;i++){
        let char=checkString.charAt(i);
        if(regx.test(char)){
            stringLength++;
        }else{
            stringLength+=3;
        }
    }
    return stringLength;
}

/*
* 基于UTF-8校验字符串的大小
*   target:被校验的字符串
*   minVal:允许的最小值
*   maxVal:允许的最大值
*/
function checkString(target,minVal,maxVal) {
    // 去除空格
    target = target.trim().toString();
    // 计算大小
    const size = getBytesByUTF8(target);
    // 判断并返回
    if (size <= maxVal && size >= minVal){
        return true;
    }else {
        return false;
    }
}

/*======================》 其他DOM工具 《======================*/


