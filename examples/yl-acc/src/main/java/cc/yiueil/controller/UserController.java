package cc.yiueil.controller;

import cc.yiueil.general.RestUrl;
import cc.yiueil.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = RestUrl.BASE_PATH + "/user")
public class UserController implements LoggedController{
    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "list")
    public String getUserList() {
        return success(userRepository.findAll());
    }
}
