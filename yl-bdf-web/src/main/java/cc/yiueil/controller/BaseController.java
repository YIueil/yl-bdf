package cc.yiueil.controller;

import cc.yiueil.vo.ResultVo;
import cc.yiueil.util.JsonUtils;

/**
 * Author:YIueil
 * Date:2022/7/3 20:25
 * Description: 基础的控制器
 */
public interface BaseController {
    default String success() {
        return JsonUtils.toJsonString(ResultVo.success());
    }

    default String success(Object obj) {
        return JsonUtils.toJsonString(ResultVo.success(obj));
    }

    default String success(Object obj, String msg) {
        return JsonUtils.toJsonString(ResultVo.success(obj, msg));
    }

    default String fail(String msg) {
        return JsonUtils.toJsonString(ResultVo.fail(null, msg));
    }

    default String fail(Object body, String msg) {
        return JsonUtils.toJsonString(ResultVo.fail(body, msg));
    }

    default String error(String msg, Exception e) {
        return JsonUtils.toJsonString(ResultVo.error(msg, e));
    }

    //todo 完善加密部分的结果返回内容
    default String successAES(Object data, String aesKey) {
        return null;
    }
}
