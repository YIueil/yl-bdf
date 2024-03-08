package cc.yiueil.controller;

import cc.yiueil.CacheService;
import cc.yiueil.general.RestUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * UserController 用户控制器
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2023/8/1 22:56
 */
@RestController
@RequestMapping(value = RestUrl.BASE_PATH + "/user")
public class UserController implements LoggedController {
    @Autowired
    CacheService cacheService;

    @GetMapping(value="test/{num}")
    public String test1(HttpServletRequest request, @PathVariable String num){
        cacheService.set(num, new Date());
        return success(cacheService.get(num));
    }

}
