package cn.yiueil.enums;

import cn.yiueil.lang.instance.CodeStatus;
import lombok.Getter;

/**
 * Author:YIueil
 * Date:2022/7/4 1:18
 * Description: 状态码类 以后都用这个好吗
 */
@Getter
public enum ResultCode implements CodeStatus {
    SUCCESS(1200, "success"),
    FAILED(1400, "failed"),
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
