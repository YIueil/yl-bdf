package cc.yiueil.service;

import cc.yiueil.dto.PageDTO;
import cc.yiueil.entity.PageEntity;
import cc.yiueil.lang.instance.ModelTransform;
import cc.yiueil.lang.tree.Tree;

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

    PageDTO findPageById(Integer id);
}
