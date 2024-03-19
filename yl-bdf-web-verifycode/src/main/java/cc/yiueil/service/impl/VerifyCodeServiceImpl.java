package cc.yiueil.service.impl;

import cc.yiueil.data.impl.JpaBaseDao;
import cc.yiueil.dto.UserDto;
import cc.yiueil.entity.VerifyCodeEntity;
import cc.yiueil.enums.VerifyCodeSendTypeEnum;
import cc.yiueil.enums.VerifyCodeStatusEnum;
import cc.yiueil.enums.VerifyCodeUseTypeEnum;
import cc.yiueil.exception.VerifyFailException;
import cc.yiueil.instance.VerifyCode;
import cc.yiueil.repository.VerifyCodeRepository;
import cc.yiueil.service.MailService;
import cc.yiueil.service.OrupService;
import cc.yiueil.service.VerifyCodeService;
import cc.yiueil.util.CollectionUtils;
import cc.yiueil.util.GlobalProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * VerifyCodeServiceV1
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2024/3/19 23:13
 */
@Slf4j
@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {
    @Autowired
    JpaBaseDao baseDao;

    @Autowired
    OrupService orupService;

    @Autowired
    MailService mailService;

    @Autowired
    VerifyCodeRepository verifyCodeRepository;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void send(VerifyCode verifyCode, UserDto user) throws MessagingException {
        UserDto userDto = orupService.findUserById(user.getId());
        // todo 让baseDao获取到用户信息保存时注入createUserId
        verifyCode.setCreateUserId(userDto.getId());
        if (VerifyCodeSendTypeEnum.MAIL.getType().equals(verifyCode.getSendType())) {
            mailService.create()
                    .setSubject("验证码")
                    .setBody(verifyCode.getCode())
                    .addTo(userDto.getEmail())
                    .send();
            baseDao.save(verifyCode);
        } else {
            log.error(String.format("验证码发送失败: %s", verifyCode));
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void sendMailChangeLink(String verifyCode, String newMailAddress, UserDto user, HttpServletRequest request) throws MessagingException {
        // todo 抽取工具类
        StringBuffer requestUrl = request.getRequestURL();
        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        int startIndex = requestUrl.indexOf(requestUri);
        String baseUrl = requestUrl.substring(0, startIndex + contextPath.length());
        List<VerifyCodeEntity> verifyCodeEntityList =
                verifyCodeRepository.findVerifyCodeEntityByCreateUserIdEqualsAndUseTypeEqualsAndStatusEqualsAndCodeEqualsAndExpireAfter(user.getId(), VerifyCodeUseTypeEnum.UserInfoChange.getUseType(), VerifyCodeStatusEnum.UnUsed.getStatus(), verifyCode, LocalDateTime.now());
        if (CollectionUtils.isNotEmpty(verifyCodeEntityList)) {
            updateVerifyCodeStatus(verifyCodeEntityList);
            // todo htmlContent 也可以封装一下
            String htmlContent = "<body>\n" +
                    "    <img src=\"https://s2.loli.net/2024/03/12/gxt1MwlUsXJ4LGr.png\" alt=\"\"/>\n" +
                    "    <a href=" + baseUrl + GlobalProperties.getProperties("callback.url.mailChange", "/orup/mailChange") + ">确认修改</a>\n";
            mailService.create()
                    .addTo(newMailAddress)
                    .setSubject("邮件变更确认")
                    .setBody(htmlContent)
                    .setHtml(true)
                    .send();
        } else {
            throw new VerifyFailException("验证失败");
        }
    }

    private void updateVerifyCodeStatus(List<VerifyCodeEntity> verifyCodeEntityList) {
        verifyCodeEntityList.forEach(verifyCodeEntity -> verifyCodeEntity.setStatus(VerifyCodeStatusEnum.Used.getStatus()));
        baseDao.batchSave(verifyCodeEntityList);
    }
}
