package cn.yiueil.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
public class TestController implements LoggedController {
    @GetMapping(value = "test1")
    public String test1() {
        return success();
    }
}
