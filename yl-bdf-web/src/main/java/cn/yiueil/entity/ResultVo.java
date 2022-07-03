package cn.yiueil.entity;

import cn.yiueil.entity.instance.CodeStatus;
import cn.yiueil.enums.ResultCode;
import lombok.Getter;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Author:YIueil
 * Date:2022/7/3 21:00
 * Description: 接口返回包装视图类
 */
@Getter
public class ResultVo implements Serializable {
    private CodeStatus codeStatus; // 请求状态码(必须)
    private String stackTrace;// 错误请求栈信息(错误时必须)
    private String tips; // 接口提示信息(可选)
    private Object body;// 返回结果请求体(可选)

    private ResultVo() {
    }

    private ResultVo(CodeStatus codeStatus, String tips, String stackTrace, Object body) {
        this.codeStatus = codeStatus;
        this.stackTrace = stackTrace;
        this.tips = tips;
        this.body = body;
    }

    public static ResultVo success() {
        return new ResultVo(ResultCode.SUCCESS, null, null, null);
    }

    public static ResultVo success(Object body) {
        return new ResultVo(ResultCode.SUCCESS, null, null, body);
    }

    public static ResultVo success(Object body, String tips) {
        return new ResultVo(ResultCode.SUCCESS, tips, null, body);
    }

    public static ResultVo fail(Object body, String tips) {
        return new ResultVo(ResultCode.FAILED, tips, null, body);
    }

    public static ResultVo error(String tips, Exception e) {
        return new ResultVo(ResultCode.ERROR, tips, Arrays.toString(e.getStackTrace()), null);
    }

    public static ResultVo error(Object body, String tips, Exception e) {
        return new ResultVo(ResultCode.ERROR, tips, Arrays.toString(e.getStackTrace()), body);
    }

    public static ResultVo validate_fail(Object body, String tips) {
        return new ResultVo(ResultCode.VALIDATE_FAIL, tips, null, body);
    }

}
