package cn.yiueil.service;

import cn.yiueil.dto.PageDTO;
import cn.yiueil.entity.PageEntity;
import cn.yiueil.lang.instance.ModelTransform;
import cn.yiueil.lang.tree.Tree;

import java.util.List;

/**
 * Author:YIueil
 * Date:2022/7/30 5:47
 * Description: blog服务
 */
public interface PageService extends ModelTransform<PageDTO, PageEntity> {

    PageDTO savePage(PageDTO pageDTO);

    void deletePageById(Integer id);

    List<Tree<Integer>> listPageTree() throws RuntimeException;

    Object findPageById(Integer id);
}
