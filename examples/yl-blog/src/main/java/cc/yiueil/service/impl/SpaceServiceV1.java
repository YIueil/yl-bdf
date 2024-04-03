package cc.yiueil.service.impl;

import cc.yiueil.data.impl.JpaBaseDao;
import cc.yiueil.dto.SpaceDTO;
import cc.yiueil.entity.SpaceEntity;
import cc.yiueil.repository.SpaceRepository;
import cc.yiueil.service.SpaceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author:YIueil
 * Date:2022/8/18 15:44
 * Description: spaceService 实现 v1.x 版本
 */
@Service
public class SpaceServiceV1 implements SpaceService {
    @Autowired
    @Qualifier("jpaBaseDao")
    JpaBaseDao baseDao;

    @Autowired
    SpaceRepository spaceRepository;

    @Override
    public SpaceEntity getSpace(Integer id) {
        return spaceRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteSpace(Integer id) {
        spaceRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Iterable<SpaceEntity> listSpace() {
        return spaceRepository.findAll();
    }

    @Override
    @Transactional
    public SpaceEntity saveSpace(SpaceDTO spaceDTO) {
        SpaceEntity spaceEntity = new SpaceEntity();
        BeanUtils.copyProperties(spaceDTO, spaceEntity);
        return baseDao.save(spaceEntity);
    }
}
