package per.tom.clientview.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import per.tom.clientview.service.CateService;
import per.tom.clientview.vo.JsonResultVo;

@RestController
@RequestMapping("/cate/")
public class CateController {

    @Autowired
    CateService cateService;

    @GetMapping("getCateByCateId")
    public JsonResultVo getCateByCateId(Integer cateId){
        return JsonResultVo.success(cateService.getCateByParentCateId(cateId));
    }

}
