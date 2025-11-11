package cc.yiueil.enums;

import lombok.Getter;

/**
 * VerifyCodeUseTypeEnum 验证码使用类型枚举
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2024/3/19 22:37
 */
@Getter
public enum VerifyCodeUseTypeEnum {
    /**
     * UserInfoChange: 用户信息变更
     */
    UserInfoChange("用户信息变更");

    private final String useType;

    VerifyCodeUseTypeEnum(String useType) {
        this.useType = useType;
    }
}
