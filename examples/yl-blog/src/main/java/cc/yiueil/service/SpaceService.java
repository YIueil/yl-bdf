package cc.yiueil.service;

import cc.yiueil.dto.SpaceDTO;
import cc.yiueil.entity.SpaceEntity;

/**
 * Author:YIueil
 * Date:2022/8/18 15:43
 * Description: 空间服务
 */
public interface SpaceService {

    SpaceEntity getSpace(Integer id);

    void deleteSpace(Integer id);

    Iterable<SpaceEntity> listSpace();

    SpaceEntity saveSpace(SpaceDTO spaceDTO);
}
