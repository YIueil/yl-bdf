package cn.yiueil.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author:YIueil
 * Date:2022/8/18 15:29
 * Description: 工作空间 控制器
 */
@RestController
@RequestMapping(value = "/space")
public class SpaceController implements BaseController{
    @GetMapping(value = "/{id}")
    public String getSpace(@PathVariable String id) {
        return null;
    }
}
