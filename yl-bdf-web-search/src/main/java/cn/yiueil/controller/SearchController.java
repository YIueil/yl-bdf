package cn.yiueil.controller;

import cn.yiueil.entity.PageVo;
import cn.yiueil.general.Url;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author:YIueil
 * Date:2022/7/4 1:48
 * Description: 通用查询
 */
@RestController
@RequestMapping(value = Url.REQUEST_PATH)
public class SearchController implements LoggedController{
    @PostMapping(value = "searchPage")
    public String searchPage(@RequestBody @Validated PageVo pageVo) {
        System.out.println(pageVo);
        return success();
    }
}
