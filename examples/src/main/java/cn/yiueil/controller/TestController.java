package cn.yiueil.controller;

import cn.yiueil.service.EmployeeService;
import cn.yiueil.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController implements BaseController{
    @Autowired
    TestService testService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping(value = "test")
    public String test() {
        return success();
    }


}
