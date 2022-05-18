package per.tom.clientview.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import per.tom.clientview.pojo.CatePo;
import per.tom.clientview.pojo.UserPo;
import per.tom.clientview.service.CateService;
import per.tom.clientview.service.UserService;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    CateService cateService;

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String toIndex1(Model model){
        List<CatePo> catePoList = cateService.getCateByParentCateId(0);
        model.addAttribute("catePoList",catePoList);
        return "index";
    }

    @RequestMapping("/index")
    public String toIndex2(Model model){
        List<CatePo> catePoList = cateService.getCateByParentCateId(0);
        model.addAttribute("catePoList",catePoList);
        return "index";
    }

    @RequestMapping("/manager")
    public String toIndex(String userTel,Model model){
        UserPo userPo = userService.findUserByTel(userTel);
        model.addAttribute("user",userPo);
        return "manager";
    }

    @RequestMapping("/login")
    public String toLogIn(){
        return "login";
    }

    @RequestMapping("/common/{file}")
    public String commonForward(@PathVariable String file){
        return "common/"+file;
    }

    @RequestMapping("/indexInner/{file}")
    public String commonIndexForward(@PathVariable String file){
        return "indexInner/"+file;
    }

    @RequestMapping("/managerInner/{file}")
    public String commonManagerForward(@PathVariable String file){
        return "managerInner/"+file;
    }

}
