package cn.yiueil.service;

import cn.yiueil.lang.tree.Tree;

import java.util.List;

/**
 * Author:YIueil
 * Date:2022/7/30 5:47
 * Description: blog服务
 */
public interface PageService {

    List<Tree<Integer>> getPageTree();
}
