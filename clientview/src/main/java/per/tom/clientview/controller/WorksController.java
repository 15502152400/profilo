package per.tom.clientview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.tom.clientview.service.WorksService;
import per.tom.clientview.vo.JsonResultVo;
import per.tom.clientview.vo.WorksSaveVo;

import java.util.Arrays;

@RestController
@RequestMapping("/work/")
public class WorksController {

    @Autowired
    WorksService worksService;

    @GetMapping("getWorksByCateId")
    public JsonResultVo getWorksByCateId(Integer cateId,Integer currentPage,Integer pageSize){
        return worksService.getWorksByCateId(cateId,currentPage,pageSize);
    }

    /* 获取作品名字、内容 */
    @GetMapping("getWorkByWorkId")
    public JsonResultVo getWorkByWorkId(Integer worksId){
        return JsonResultVo.success(worksService.getWorkByWorkId(worksId));
    }

    /* 获取作品所有信息 */
    @GetMapping("getWorkAllByWorkId")
    public JsonResultVo getWorkAllByWorkId(Integer worksId){
        return JsonResultVo.success(worksService.getWorkAllByWorkId(worksId));
    }

    // 添加或者修改作品
    @PostMapping("saveWork")
    public JsonResultVo doSaveProfilo(WorksSaveVo worksVo){
        worksService.saveProfilo(worksVo);
        System.out.println(worksVo);
        return JsonResultVo.success("保存作品成功");
    }

    @GetMapping("getWorksByCateDesi")
    public JsonResultVo getWorksByCateDesi(Integer cateId,Integer desiId,Integer timeOrder,Integer currentPage,Integer pageSize){
        return worksService.getWorksByCateDesi(cateId,desiId,timeOrder,currentPage,pageSize);
    }

    @GetMapping("deleteWorksByWorkIds")
    public JsonResultVo deleteWorksByWorkIds(Integer[] workIds){
        worksService.deleteWorksByWorkIds(workIds);
        return JsonResultVo.success("删除成功");
    }

    @GetMapping("deleteWorkByWorkId")
    public JsonResultVo deleteWorkByWorkId(Integer workId){
        worksService.deleteWorkByWorkId(workId);
        return JsonResultVo.success("删除成功");
    }

}
