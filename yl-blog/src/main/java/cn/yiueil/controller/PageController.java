package cn.yiueil.controller;

import cn.yiueil.service.PageService;
import cn.yiueil.vo.ResultVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/page")
public class PageController implements LoggedController{
    @Autowired
    PageService pageService;

    @ApiOperation(value = "page: 获取文章树", notes = "查询整个文章列表, 组装树形结构", response = ResultVo.class)
    @GetMapping(value = "pageTree")
    public String getPageTree() {
        return success(pageService.getPageTree());
    }
}
