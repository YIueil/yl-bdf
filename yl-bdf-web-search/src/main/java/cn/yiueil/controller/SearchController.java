package cn.yiueil.controller;

import cn.yiueil.dto.DynamicQueryDTO;
import cn.yiueil.entity.PageVo;
import cn.yiueil.general.RestURL;
import cn.yiueil.service.SearchService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:YIueil
 * Date:2022/7/4 1:48
 * Description: 通用查询
 */
@RestController
@RequestMapping(value = RestURL.REQUEST_PATH)
public class SearchController implements LoggedController{
    @Autowired
    SearchService searchService;

    @PostMapping(value = "searchPage")
    @ApiOperation(value = "分页动态查询", notes = "通过配置查询分页结构", response = PageVo.class)
    public String searchPage(@RequestParam(defaultValue = "1") Integer pageIndex,
                             @RequestParam(defaultValue = "10") Integer pageSize,
                             @RequestBody @Validated DynamicQueryDTO dynamicQueryDTO) {
        // 1、构建查询参数
        Map<String, Object> filter = new HashMap<>();
        return success(searchService.searchPage(dynamicQueryDTO, filter, pageIndex, pageSize));
    }

    @PostMapping(value = "searchOne")
    @ApiOperation(value = "动态查询对象", notes = "通过配置查询对象结构", response = PageVo.class)
    public String searchOne(@RequestBody @Validated DynamicQueryDTO dynamicQueryDTO) {
        return success();
    }

}
