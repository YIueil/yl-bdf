package cn.yiueil.controller;

import cn.yiueil.entity.RestResult;
import com.alibaba.fastjson.JSON;

public abstract class BaseController {
    public String success(Object obj) {
        return JSON.toJSONString(RestResult.success(obj));
    }

    public String success(Object obj, String msg) {
        return JSON.toJSONString(RestResult.success(obj, msg));
    }

    public String fail(String msg) {
        return JSON.toJSONString(RestResult.fail(null, msg));
    }

    public String fail(Object body, String msg) {
        return JSON.toJSONString(RestResult.fail(body, msg));
    }

    public String error(String msg, Exception e) {
        return JSON.toJSONString(RestResult.error(msg, e));
    }

    //todo 完善加密部分的结果返回内容
    protected String successAES(Object data, String aesKey) {
        return null;
    }
}
