// 全局数据获取接口
var globalInterface = {
    // 添加分类
    saveCate:"/cate/saveCate",
    // 根据上级分类id分页获取所有作品分类
    getAllCateByParentCateId: "/cate/getAllCate",
    // 根据上级分类id获取分类
    getCateByParentCateId : "/cate/getCateByParentCateId",
    // 根据分类id修改分类
    updateCateByCateId: "/cate/updateCateByCateId",
    // 根据分类id获取作品
    getWorksByCateId : "/work/getWorksByCateId",
    // 根据分类id数组批量删除分类
    deleteCatesByCateIdList: "/cate/deleteCatesByCateIdList",
    // 根据分类id单独删除分类
    deleteCateByCateId: "/cate/deleteCateByCateId",
    // 根据作品id获取作品名字、内容
    getWorkByWorkId : "/work/getWorkByWorkId",
    // 获取全部设计形式
    getAllDesi : "/desi/getAllDesi",
    // 添加设计形式
    saveDesi: "/desi/saveDesi",
    //分页获取所有设计形式
    getAllDesiByPagenation: "/desi/getAllDesiByPagenation",
    // 根据设计形式id数组删除设计形式
    deleteDesiByDesiIdList: "/desi/deleteDesiByDesiIdList",
    // 根据设计形式id删除设计形式
    deleteDesiByDesiId:"/desi/deleteDesiByDesiId",
    // 根据设计形式id保存设计形式
    updateDesiByDesiId:"/desi/updateDesiByDesiId",
    // 添加新用户
    addUser : "/user/addUser",
    // 用户登陆
    doLogIn : "/user/doLogIn",
    // 登出
    doLogOut : "/logout",
    // 添加或更新作品
    doSaveProfilo: "/work/saveWork",
    // 根据分类ID和设计形式获取作品
    getWorksByCateDesi:"/work/getWorksByCateDesi",
    // 根据作品id数组删除作品
    deleteWorksByWorkIds:"/work/deleteWorksByWorkIds",
    // 根据作品id删除作品
    deleteWorkByWorkId:"/work/deleteWorkByWorkId",
    // 根据作品id获取所有作品内容
    getWorkAllByWorkId:"/work/getWorkAllByWorkId"
}