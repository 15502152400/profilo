package per.tom.clientview.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import per.tom.clientview.pojo.CatePo;
import per.tom.clientview.service.CateService;
import per.tom.clientview.vo.JsonResultVo;

@RestController
@RequestMapping("/cate/")
public class CateController {

    @Autowired
    CateService cateService;

    @GetMapping("getCateByParentCateId")
    public JsonResultVo getCateByParentCateId(Integer cateId){
        return JsonResultVo.success(cateService.getCateByParentCateId(cateId));
    }

    @PostMapping("saveCate")
    public JsonResultVo saveCate(CatePo catePo) {
        cateService.saveCate(catePo);
        return JsonResultVo.success("保存成功");
    }

    @GetMapping("getAllCate")
    public JsonResultVo getAllCate(Integer currentPage,Integer pageSize,Integer showOrder,Integer parenCateId){
        return cateService.getAllCate(currentPage ,pageSize,showOrder,parenCateId);
    }

    @GetMapping("deleteCatesByCateIdList")
    public JsonResultVo deleteCatesByCateIdList(Integer[] cateIdList){
        return cateService.deleteCatesByCateIdList(cateIdList);
    }

    @GetMapping("deleteCateByCateId")
    public JsonResultVo deleteCateByCateId(Integer cateId){
        cateService .deleteCateByCateId(cateId);
        return JsonResultVo.success("删除成功");
    }

    @PostMapping("updateCateByCateId")
    public JsonResultVo updateCateByCateId(CatePo catePo){
        cateService.updateCateByCateId(catePo);
        return  JsonResultVo.success("保存成功");
    }

}
