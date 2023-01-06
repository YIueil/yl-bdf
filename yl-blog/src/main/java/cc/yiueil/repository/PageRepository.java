package cc.yiueil.repository;

import cc.yiueil.entity.PageEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface PageRepository extends CrudRepository<PageEntity, Integer>, JpaSpecificationExecutor<PageEntity> {

}
