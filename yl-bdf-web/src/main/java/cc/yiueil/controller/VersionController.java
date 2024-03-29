package cc.yiueil.controller;

import cc.yiueil.enums.ResultCode;
import cc.yiueil.general.RestUrl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * VersionController
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2023/9/14 14:11
 */
@Api(value = "version-应用版本信息")
@RestController
@RequestMapping(value = RestUrl.BASE_PATH + "/version")
public class VersionController implements BaseController{
    @ApiOperation(value = "获取状态码列表")
    @GetMapping(value="getStatusCode")
    public String statusCode(){
        return success(Arrays.stream(ResultCode.values()).map(ResultCode::toString).collect(Collectors.toList()));
    }
}
