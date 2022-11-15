package cn.yiueil.controller;

import cn.yiueil.constant.SearchRestURL;
import cn.yiueil.dto.DynamicQueryDTO;
import cn.yiueil.vo.PageVo;
import cn.yiueil.general.RestURL;
import cn.yiueil.service.SearchService;
import io.swagger.annotations.ApiOperation;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:YIueil
 * Date:2022/7/4 1:48
 * Description: 通用查询
 */
@RestController
@RequestMapping(value = RestURL.BASE_PATH + SearchRestURL.QUERY)
public class SearchController implements LoggedController {
    @Autowired
    SearchService searchService;

    @PostMapping(value = "searchPage")
    @ApiOperation(value = "分页动态查询", notes = "通过配置查询分页结构", response = PageVo.class)
    public String searchPage(@RequestParam(defaultValue = "1") Integer pageIndex,
                             @RequestParam(defaultValue = "10") Integer pageSize,
                             @RequestBody @Validated DynamicQueryDTO dynamicQueryDTO) {
        // 1、构建查询参数
        Map<String, Object> filter = new HashMap<>(dynamicQueryDTO.getFilter());
        try {
            return success(searchService.searchPage(dynamicQueryDTO, filter, pageIndex, pageSize));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return error("查询服务配置文件不存在", e);
        } catch (DocumentException e) {
            return error("查询配置文件结构有误", e);
        }
    }

    @PostMapping(value = "searchOne")
    @ApiOperation(value = "动态查询对象", notes = "通过配置查询对象结构", response = PageVo.class)
    public String searchOne(@RequestBody @Validated DynamicQueryDTO dynamicQueryDTO) {
        return success();
    }

}
