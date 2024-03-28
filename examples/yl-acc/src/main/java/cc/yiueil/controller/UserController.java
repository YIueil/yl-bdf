package cc.yiueil.controller;

import cc.yiueil.api.CacheService;
import cc.yiueil.general.RestUrl;
import cc.yiueil.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * UserController 用户控制器
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2023/8/1 22:56
 */
@Slf4j
@RestController
@RequestMapping(value = RestUrl.BASE_PATH + "/user")
public class UserController implements LoggedController {
    @Autowired
    CacheService cacheService;

    @Autowired
    MailService mailService;

    @GetMapping(value="test/{num}")
    public String test1(HttpServletRequest request, @PathVariable String num){
        cacheService.set(num, new Date());
        return success(cacheService.get(num));
    }

    @GetMapping(value="test/mail")
    public String test1(HttpServletRequest request){
        try {
            mailService.create()
                    .addTo("511210125@qq.com")
                    .setSubject("无题")
                    .setBody("也是无题")
                    .send();
        } catch (MessagingException e) {
            log.error(e.getMessage(), e);
            return fail(null, "邮件发送失败");
        }
        return success();
    }
}
