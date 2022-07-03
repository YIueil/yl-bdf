package cn.yiueil.controller;

import cn.yiueil.entity.PageVo;
import cn.yiueil.general.Params;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Params.REQUEST_PATH)
public class SearchController implements LoggedController{
    @PostMapping(value = "searchPage")
    public String searchPage(@RequestBody PageVo pageVo) {
        System.out.println(pageVo);
        return success();
    }
}
