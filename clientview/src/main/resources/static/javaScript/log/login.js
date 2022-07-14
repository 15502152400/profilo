// 点击登陆
function doLogIn() {
    let params = {
        userTel : $(".userName").val(),
        userPass : $(".userPassword").val(),
        isRememberMe : $(".rememberMe").prop("checked")
    }
    $.post(globalInterface.doLogIn,params,(e) => {
        ajaxMsgHandle(e.message);
        if (e.status==1){
            window.location = globalPath.managerPage+"?userTel="+$(".userName").val();
        }
    });
}