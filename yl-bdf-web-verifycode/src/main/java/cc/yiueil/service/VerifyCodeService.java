package cc.yiueil.service;

import cc.yiueil.dto.UserDto;
import cc.yiueil.instance.VerifyCode;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

/**
 * VerifyCodeService 验证码服务
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2024/3/19 23:08
 */
public interface VerifyCodeService {

    /**
     * 验证码发送
     * @param verifyCode 验证码实体
     * @param user 发送用户
     * @throws MessagingException 邮件异常
     */
    void send(VerifyCode verifyCode, UserDto user) throws MessagingException;

    /**
     * 发送邮件变更链接邮件
     * @param verifyCode 验证码
     * @param newMailAddress 新邮件地址
     * @param user 操作用户
     * @param request 请求体
     * @throws MessagingException 邮件异常
     */
    void sendMailChangeLink(String verifyCode, String newMailAddress, UserDto user, HttpServletRequest request) throws MessagingException;
}
