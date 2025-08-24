package cc.yiueil.controller;

import cc.yiueil.constant.VerifyCodeUrl;
import cc.yiueil.dto.UserDto;
import cc.yiueil.enums.VerifyCodeSendTypeEnum;
import cc.yiueil.enums.VerifyCodeUseTypeEnum;
import cc.yiueil.general.RestUrl;
import cc.yiueil.service.VerifyCodeService;
import cc.yiueil.util.VerifyCodeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

/**
 * VerifyCodeController 验证码控制器
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2024/3/19 22:32
 */
@Api(value = "VerifyCode-验证码模块")
@Slf4j
@RestController
@RequestMapping(value = RestUrl.BASE_PATH + VerifyCodeUrl.VERIFY_CODE_V1)
public class VerifyCodeController implements LoggedController {
    @Autowired
    VerifyCodeService verifyCodeService;

    /**
     * 发送邮件变更验证码
     *
     * @param request 请求体
     * @return 发送结果
     */
    @ApiOperation(value = "发送邮件变更验证码")
    @PostMapping(value = "sendMailChangeVerifyCode")
    public String sendMailChangeVerifyCode(HttpServletRequest request) {
        UserDto user = getUser(request);
        try {
            verifyCodeService.send(VerifyCodeUtils.generateVerifyCode(6, 60 * 5, VerifyCodeSendTypeEnum.MAIL, VerifyCodeUseTypeEnum.UserInfoChange), user);
        } catch (MessagingException e) {
            log.error(e.getMessage(), e);
            return fail(null, e.getMessage());
        }
        return success();
    }

    /**
     * 发送邮件变更链接
     *
     * @param request 请求体
     * @return 发送结果
     */
    @ApiOperation(value = "发送邮件变更链接")
    @PostMapping(value = "sendMailChangeLink")
    public String sendMailChangeLink(@RequestParam String newMailAddress,
                                     @RequestParam String verifyCode,
                                     HttpServletRequest request) {
        UserDto user = getUser(request);
        try {
            verifyCodeService.sendMailChangeLink(verifyCode, newMailAddress, user, request);
        } catch (MessagingException e) {
            log.error(e.getMessage(), e);
            return fail(null, e.getMessage());
        }
        return success();
    }
}
