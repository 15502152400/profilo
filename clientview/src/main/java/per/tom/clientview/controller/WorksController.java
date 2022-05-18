package per.tom.clientview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.tom.clientview.service.WorksService;
import per.tom.clientview.vo.JsonResultVo;

@RestController
@RequestMapping("/work/")
public class WorksController {

    @Autowired
    WorksService worksService;

    @GetMapping("getWorksByCateId")
    public JsonResultVo getWorksByCateId(Integer cateId,Integer currentPage,Integer pageSize){
        return worksService.getWorksByCateId(cateId,currentPage,pageSize);
    }

    @GetMapping("getWorkByWorkId")
    public JsonResultVo getWorkByWorkId(Integer worksId){
        return JsonResultVo.success(worksService.getWorkByWorkId(worksId));
    }

}
