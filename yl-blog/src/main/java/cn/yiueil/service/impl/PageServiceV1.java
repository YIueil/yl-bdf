package cn.yiueil.service.impl;

import cn.yiueil.data.impl.JpaBaseDao;
import cn.yiueil.dto.PageDTO;
import cn.yiueil.entity.PageEntity;
import cn.yiueil.exception.ResourceNotFoundException;
import cn.yiueil.lang.tree.Tree;
import cn.yiueil.lang.tree.TreeNode;
import cn.yiueil.repository.PageRepository;
import cn.yiueil.service.PageService;
import cn.yiueil.util.TreeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.*;
import java.util.function.Supplier;
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
    @Transactional
    public PageDTO savePage(PageDTO pageDTO) {
        return entity2Dto(baseDao.save(dto2Entity(pageDTO)));
    }

    @Override
    @Transactional
    public void deletePageById(Integer id) {
        baseDao.deleteById(PageEntity.class, id);
    }

    @Override
    public PageDTO findPageById(Integer id) {
        return entity2Dto(baseDao.findById(PageEntity.class, id).orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public List<Tree<Integer>> listPageTree() throws RuntimeException {
        List<PageEntity> pages = pageRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
        return TreeUtils.build(pages.stream()
                .map(page -> {
                    Map<String, Object> extra = new HashMap<>();
                    extra.put("id", page.getId());
                    extra.put("guid", page.getGuid());
                    extra.put("title", page.getTitle());
                    extra.put("parentId", page.getParentId());
                    extra.put("content", page.getContent());
                    return new TreeNode<>(page.getId(), page.getParentId(), page.getTitle(), 1, extra);
                })
                .collect(Collectors.toList()), 0);
    }

    @Override
    public PageEntity dto2Entity(PageDTO pageDTO) {
        PageEntity pageEntity = new PageEntity();
        pageEntity.setId(pageDTO.getId());
        pageEntity.setGuid(pageDTO.getGuid());
        pageEntity.setCreateUser(pageDTO.getCreateUser());
        pageEntity.setCreateTime(pageDTO.getCreateTime());
        pageEntity.setModifyTime(pageDTO.getModifyTime());
        pageEntity.setIconUrl(pageDTO.getIconUrl());
        pageEntity.setTitle(pageDTO.getTitle());
        pageEntity.setContent(pageDTO.getContent());
        pageEntity.setParentId(pageDTO.getParentId());
        pageEntity.setTags(pageDTO.getTags());
        return pageEntity;
    }

    @Override
    public PageDTO entity2Dto(PageEntity pageEntity) {
        PageDTO pageDTO = new PageDTO();
        pageDTO.setId(pageEntity.getId());
        pageDTO.setGuid(pageEntity.getGuid());
        pageDTO.setCreateUser(pageEntity.getCreateUser());
        pageDTO.setCreateTime(pageEntity.getCreateTime());
        pageDTO.setModifyTime(pageEntity.getModifyTime());
        pageDTO.setIconUrl(pageEntity.getIconUrl());
        pageDTO.setTitle(pageEntity.getTitle());
        pageDTO.setContent(pageEntity.getContent());
        pageDTO.setParentId(pageEntity.getParentId());
        pageDTO.setTags(pageEntity.getTags());
        return pageDTO;
    }
}
