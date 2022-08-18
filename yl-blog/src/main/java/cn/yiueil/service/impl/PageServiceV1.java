package cn.yiueil.service.impl;

import cn.yiueil.data.impl.JpaBaseDao;
import cn.yiueil.entity.PageEntity;
import cn.yiueil.lang.tree.Tree;
import cn.yiueil.lang.tree.TreeNode;
import cn.yiueil.repository.PageRepository;
import cn.yiueil.service.PageService;
import cn.yiueil.util.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author:YIueil
 * Date:2022/7/30 5:52
 * Description: blogServer实现 v1.x 版本
 */
@Service
public class PageServiceV1 implements PageService {
    @Autowired
    JpaBaseDao baseDao;

    @Autowired
    PageRepository pageRepository;

    @Override
    public List<Tree<Integer>> getPageTree() {
        List<PageEntity> pages = pageRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
        return TreeUtils.build(pages.stream()
                .map(page -> new TreeNode<>(page.getId(), page.getParentId(), page.getTitle(), 1, null))
                .collect(Collectors.toList()), 0);
    }
}
