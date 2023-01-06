package cc.yiueil.data;

import cc.yiueil.lang.instance.HasGuid;
import cc.yiueil.util.StringUtils;

import java.util.UUID;

/**
 * Author:YIueil
 * Date:2022/7/22 17:49
 * Description: 提供 guid 生成策略
 */
public interface GuidDao {
    default void generatorGuid(Object entity) {
        if (entity instanceof HasGuid) {
            HasGuid guidEntity = (HasGuid) entity;
            if(StringUtils.isEmpty(guidEntity.getGuid()))
            {
                guidEntity.setGuid(UUID.randomUUID().toString().replace("-",""));
            }
        }
    }
}
