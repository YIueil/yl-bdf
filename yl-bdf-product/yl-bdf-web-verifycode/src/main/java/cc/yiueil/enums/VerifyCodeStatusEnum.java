package cc.yiueil.enums;

import lombok.Getter;

/**
 * VerifyCodeStatusEnum 验证码状态枚举
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2024/3/19 22:37
 */
@Getter
public enum VerifyCodeStatusEnum {
    /**
     * Expire: 已过期
     * Used: 已使用
     * UnUsed: 未使用
     */
    Expire("已过期"),
    Used("已使用"),
    UnUsed("未使用");

    private final String status;

    VerifyCodeStatusEnum(String status) {
        this.status = status;
    }
}
