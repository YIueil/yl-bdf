package cc.yiueil.lang.instance;

import javax.persistence.Column;

/**
 * Author:YIueil
 * Date:2022/7/22 17:36
 * Description: guid 实体类的接口
 */
public interface HasGuid {
    void setGuid(String guid);

    @Column(name = "guid", length = 32, columnDefinition = "Guid")
    String getGuid();
}
