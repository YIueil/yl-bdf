package cn.yiueil.service.impl;

import cn.yiueil.data.impl.JpaBaseDao;
import cn.yiueil.entity.Blog;
import cn.yiueil.lang.instance.Node;
import cn.yiueil.lang.tree.Tree;
import cn.yiueil.lang.tree.TreeNode;
import cn.yiueil.repository.BlogRepository;
import cn.yiueil.service.BlogService;
import cn.yiueil.util.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author:YIueil
 * Date:2022/7/30 5:52
 * Description: blogServer实现 v1.x 版本
 */
@Service
public class BlogServiceV1 implements BlogService {
    @Autowired
    JpaBaseDao baseDao;

    @Autowired
    BlogRepository blogRepository;

    @Override
    public List<Tree<Integer>> getBlogTree() {
        List<Blog> blogs = blogRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
        return TreeUtils.build(blogs.stream()
                .map(blog -> new TreeNode<>(blog.getId(), blog.getParentId(), blog.getTitle(), 1, null))
                .collect(Collectors.toList()), 0);
    }
}
