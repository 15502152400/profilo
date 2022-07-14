package per.tom.clientview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.tom.clientview.pojo.DesiPo;
import per.tom.clientview.service.DesiService;
import per.tom.clientview.vo.JsonResultVo;

@RestController
@RequestMapping("/desi/")
public class DesiController {

    @Autowired
    DesiService desiService;

    @GetMapping("getAllDesi")
    public JsonResultVo getAllDesi(){
        return JsonResultVo.success(desiService.getAllDesi());
    }

    @PostMapping("saveDesi")
    public JsonResultVo saveDesi(String desiName){
        desiService.saveDesi(desiName);
        return JsonResultVo.success("保存成功");
    }

    @GetMapping("getAllDesiByPagenation")
    public JsonResultVo getAllDesiByPagenation(Integer currentPage,Integer pageSize,Integer showOrder) {
        return desiService.getAllDesiByPagenation(currentPage, pageSize, showOrder);
    }

    @GetMapping("deleteDesiByDesiIdList")
    public JsonResultVo deleteDesiByDesiIdList(Integer[] desiIdList){
        desiService.deleteDesiByDesiIdList(desiIdList);
        return JsonResultVo.success("删除成功");
    }

    @GetMapping("deleteDesiByDesiId")
    public JsonResultVo deleteDesiByDesiId(Integer desiId){
        desiService.deleteDesiByDesiId(desiId);
        return JsonResultVo.success("删除成功");
    }

    @PostMapping("updateDesiByDesiId")
    public JsonResultVo updateDesiByDesiId(Integer desiId,String desiName){
        desiService.updateDesiByDesiId(desiId,desiName);
        return JsonResultVo.success("修改成功");
    }

}
