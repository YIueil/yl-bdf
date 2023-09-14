package cc.yiueil.enums;

import cc.yiueil.lang.instance.CodeStatus;
import lombok.Getter;

/**
 * ResultCode 状态码类 以此为准
 * @author 弋孓 YIueil@163.com
 * @date 2023/5/31 23:11
 * @version 1.0
 */
@Getter
public enum ResultCode implements CodeStatus {
    /**
     * SUCCESS: 请求成功
     * FAILED: 请求失败
     * ERROR: 服务器错误
     * UNAUTHORIZED: 未授权
     * EXPIRED: 授权到期
     * VALIDATE_FAIL: 认证失败
     */
    SUCCESS(1200, "success"),
    FAILED(0, "failed"),
    ERROR(1500, "error"),
    UNAUTHORIZED(1401,"unauthorized"),
    EXPIRED(1409,"expired"),
    VALIDATE_FAIL(1400, "validate_fail");

    private final Integer code;
    private final String msg;

    ResultCode(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }
}
