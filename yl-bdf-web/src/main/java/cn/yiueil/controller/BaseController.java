package cn.yiueil.controller;

import cn.yiueil.entity.ResultVo;
import cn.yiueil.util.JSONUtils;

/**
 * Author:YIueil
 * Date:2022/7/3 20:25
 * Description: 基础的控制器
 */
public interface BaseController {
    default String success() {
        return JSONUtils.toJSONString(ResultVo.success());
    }

    default String success(Object obj) {
        return JSONUtils.toJSONString(ResultVo.success(obj));
    }

    default String success(Object obj, String msg) {
        return JSONUtils.toJSONString(ResultVo.success(obj, msg));
    }

    default String fail(String msg) {
        return JSONUtils.toJSONString(ResultVo.fail(null, msg));
    }

    default String fail(Object body, String msg) {
        return JSONUtils.toJSONString(ResultVo.fail(body, msg));
    }

    default String error(String msg, Exception e) {
        return JSONUtils.toJSONString(ResultVo.error(msg, e));
    }

    //todo 完善加密部分的结果返回内容
    default String successAES(Object data, String aesKey) {
        return null;
    }
}
