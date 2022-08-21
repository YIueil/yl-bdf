package cn.yiueil.controller;

import cn.yiueil.dto.SpaceDTO;
import cn.yiueil.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Author:YIueil
 * Date:2022/8/18 15:29
 * Description: 工作空间 控制器
 */
@RestController
@RequestMapping(value = "/space")
public class SpaceController implements LoggedController{
    @Autowired
    SpaceService spaceService;

    @GetMapping(value = "/{id}")
    public String getSpace(@PathVariable Integer id) {
        return success(spaceService.getSpace(id));
    }

    @GetMapping(value = "/list")
    public String getSpaceList() {
        return success(spaceService.listSpace());
    }

    @DeleteMapping(value = "/{id}")
    public String removeSpace(@PathVariable Integer id) {
        spaceService.deleteSpace(id);
        return success();
    }

    @PostMapping
    public String addSpace(@RequestBody @Validated SpaceDTO spaceDTO) {
        return success(spaceService.saveSpace(spaceDTO));
    }
}
