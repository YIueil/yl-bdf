package cn.yiueil.controller;

import cn.yiueil.service.BlogService;
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

    @ApiOperation(value = "blog: 获取博客树")
    @GetMapping(value = "blogTree")
    public String getBlogTree() {
        return success(blogService.getBlogTree());
    }
}
