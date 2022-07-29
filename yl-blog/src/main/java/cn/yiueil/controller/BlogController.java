package cn.yiueil.controller;

import cn.yiueil.service.BlogService;
import cn.yiueil.vo.ResultVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blog")
public class BlogController implements BaseController{
    @Autowired
    BlogService blogService;

    @ApiOperation(value = "blog: 获取博客树", notes = "查询整个博客列表, 组装树形结构", response = ResultVo.class)
    @GetMapping(value = "blogTree")
    public String getBlogTree() {
        return success(blogService.getBlogTree());
    }
}
