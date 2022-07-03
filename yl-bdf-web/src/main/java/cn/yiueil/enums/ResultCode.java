package cn.yiueil.enums;

import cn.yiueil.entity.instance.CodeStatus;
import lombok.Getter;

@Getter
public enum ResultCode implements CodeStatus {
    SUCCESS(1200, "success"),
    FAILED(1400, "failed"),
    ERROR(1500, "error"),
    UNAUTHORIZED(1401,"unauthorized"),
    EXPIRED(1409,"expired"),
    VALIDATE_ERROR(1400, "validate_error");

    private final int code;
    private final String msg;

    ResultCode(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }
}
