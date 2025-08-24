package cc.yiueil.controller;

import cc.yiueil.dto.PageDTO;
import cc.yiueil.service.PageService;
import cc.yiueil.util.ObjectUtils;
import cc.yiueil.vo.ResultVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/page")
public class PageController implements LoggedController {
    @Autowired
    PageService pageService;

    @ApiOperation(value = "page: 添加文章", notes = "添加文章")
    @PostMapping
    public String addPage(@RequestBody @Validated PageDTO pageDTO) {
        return success(pageService.savePage(pageDTO));
    }

    @ApiOperation(value = "page: 删除文章", notes = "删除文章")
    @DeleteMapping("/{id}")
    public String deletePage(@PathVariable Integer id) {
        pageService.deletePageById(id);
        return success();
    }

    @ApiOperation(value = "page: 修改文章", notes = "修改文章")
    @PutMapping("/{id}")
    public String updatePage(@PathVariable Integer id, @RequestBody @Validated PageDTO pageDTO) {
        return success(pageService.savePage(pageDTO));
    }

    @ApiOperation(value = "page: 获取文章树", notes = "查询整个文章列表, 组装树形结构", response = ResultVo.class)
    @GetMapping(value = "/tree")
    public String listPageTree(@RequestParam Integer spaceId) {
        return success(ObjectUtils.defaultIfNull(pageService.listPageTree(), new ArrayList<>()));
    }

    @ApiOperation(value = "page: 获取文章", notes = "查询整个文章列表, 组装树形结构", response = ResultVo.class)
    @GetMapping(value = "/{id}")
    public String getPage(@PathVariable Integer id) {
        return success(ObjectUtils.defaultIfNull(pageService.findPageById(id), new ArrayList<>()));
    }
}
