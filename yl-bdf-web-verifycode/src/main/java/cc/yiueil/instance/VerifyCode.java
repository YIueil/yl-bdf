package cc.yiueil.instance;

import cc.yiueil.lang.instance.BaseEntity;

import java.time.LocalDateTime;

/**
 * VerifyCode 验证码接口
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2024/3/19 22:35
 */
public interface VerifyCode extends BaseEntity<Long>, VerifyCodeUseType {

    /**
     * 获取代码
     * @return 代码
     */
    String getCode();

    /**
     * 设置代码
     * @param code 代码
     */
    void setCode(String code);

    /**
     * 获取发送方式
     * @return 发送方式
     */
    String getSendType();

    /**
     * 设置发送方式
     * @param sendType 发送方式
     */
    void setSendType(String sendType);

    /**
     * 获取状态
     * @return 发送方式
     */
    String getStatus();

    /**
     * 设置状态
     * @param status 状态
     */
    void setStatus(String status);

    /**
     * 获取超时时间
     * @return 超时时间
     */
    LocalDateTime getExpire();

    /**
     * 设置超时时间
     * @param expire 超时时间
     */
    void setExpire(LocalDateTime expire);
}
