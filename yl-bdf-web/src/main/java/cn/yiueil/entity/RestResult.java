package cn.yiueil.entity;

import lombok.Data;

import java.util.Arrays;

@Data
public class RestResult {
    public static final String OK = "success";
    public static final String FAIL = "fail";
    public static final String ERROR = "error";
    public static final String UNAUTHORIZED = "unauthorized";
    public static final String EXPIRED = "expired";

    private int code;// http请求结果状态编码
    private String status;// http请求结果状态解释
    private String stackTrace;// 错误请求栈信息
    private Object body;// 返回结果请求体
    private String msg;// 返回请求封装消息

    private RestResult() {

    }

    private RestResult(int code, String status, String msg, String stackTrace, Object body) {
        this.code = code;
        this.status = status;
        this.msg = msg;
        this.stackTrace = stackTrace;
        this.body = body;
    }

    public static RestResult success(Object body) {
        return new RestResult(200, OK, null, null, body);
    }

    public static RestResult success(Object body, String msg) {
        return new RestResult(200, OK, msg, null, body);
    }

    public static RestResult fail(Object body, String msg) {
        return new RestResult(400, FAIL, msg, null, body);
    }

    public static RestResult error(String msg, Exception e) {
        return new RestResult(500, ERROR, msg, Arrays.toString(e.getStackTrace()), null);
    }
}
