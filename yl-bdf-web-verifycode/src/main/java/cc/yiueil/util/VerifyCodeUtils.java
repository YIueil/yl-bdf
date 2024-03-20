package cc.yiueil.util;

import cc.yiueil.entity.VerifyCodeEntity;
import cc.yiueil.enums.VerifyCodeSendTypeEnum;
import cc.yiueil.enums.VerifyCodeStatusEnum;
import cc.yiueil.enums.VerifyCodeUseTypeEnum;
import cc.yiueil.instance.VerifyCode;

import java.time.LocalDateTime;

/**
 * VerifyCodeUtils 验证码工具类
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2024/3/19 23:16
 */
public class VerifyCodeUtils {
    /**
     * 生成验证码
     *
     * @param length        验证码长度
     * @param expireSeconds 超时时间单位秒
     * @param sendType      发送方式
     * @param useType       使用类型
     * @return 生成的验证码对象
     */
    public static VerifyCode generateVerifyCode(int length, int expireSeconds, VerifyCodeSendTypeEnum sendType, VerifyCodeUseTypeEnum useType) {
        VerifyCodeEntity verifyCodeEntity = new VerifyCodeEntity();
        verifyCodeEntity.setStatus(VerifyCodeStatusEnum.UnUsed.getStatus());
        verifyCodeEntity.setSendType(sendType.getType());
        verifyCodeEntity.setUseType(useType.getUseType());
        verifyCodeEntity.setExpire(LocalDateTime.now().plusSeconds(expireSeconds));
        verifyCodeEntity.setCode(RandomUtils.randomNumberVerifyCode(length));
        return verifyCodeEntity;
    }
}
