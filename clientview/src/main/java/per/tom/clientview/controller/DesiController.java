package per.tom.clientview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
