package cc.yiueil.controller;

import cc.yiueil.service.EmployeeService;
import cc.yiueil.service.TestService;
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
