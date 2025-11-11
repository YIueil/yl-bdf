package cc.yiueil.enums;

import lombok.Getter;

/**
 * VerifyCodeSendTypeEnum 验证码发送方式枚举
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2024/3/19 22:37
 */
@Getter
public enum VerifyCodeSendTypeEnum {
    /**
     * MAIL: 邮件
     * SMS: 短信
     */
    MAIL("邮件"),
    SMS("短信");

    private final String type;

    VerifyCodeSendTypeEnum(String type) {
        this.type = type;
    }
}
