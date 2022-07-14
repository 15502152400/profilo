// 全局数据对象
var globalData = {
    // 当前页面的导航选择器
    currentNav : $(".toIndex"),
    // 当前富文本
    editor: Object,
    // 分页信息
    currentPage: 1,
    pageSize : 10,
    countPage: 0,
    // 当前作品id
    currentProfiloId:0,
    // 首页显示的最新作品数量
    indexPageSize : 2,
    // 作品中文名字最大字节
    maxProfiloNameCh: 60,
    // 作品中文名字最小字节
    minProfiloNameCh: 1,
    // 作品英文名字最大字节
    maxProfiloNameEn: 60,
    // 作品英文名字最小字节
    minProfiloNameEn: 1,
    // 作品封面图宽(px)
    profiloCoverWidth: 1018,
    // 作品封面图高(px)
    profiloCoverHeight: 757,
    // 作品封面图最大容量(mb)
    profiloCoverSize: 3,
    // 分类名最小字节
    minCateName:1,
    // 分类名最大字节
    maxCateName:30,
    // 设计形式最大字节
    maxDesiName:30,
    // 设计形式最小字节
    minDesiName:1
}