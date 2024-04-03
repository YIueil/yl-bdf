package cc.yiueil;

import cc.yiueil.data.impl.JpaBaseDao;
import cc.yiueil.entity.PageEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/spring.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class WebTest {
    @Autowired
    @Qualifier("jpaBaseDao")
    JpaBaseDao baseDao;

    /**
     * 测试：
     */
    @Test
    @Commit
    @Transactional
    public void test1(){
        Optional<PageEntity> byId = baseDao.findById(PageEntity.class, 1);
    }

    /**
     * 测试：
     */
    @Test
    @Transactional
    public void test2(){
        Optional<PageEntity> byGuid = baseDao.findByGuid(PageEntity.class, "e536878492f7407189f93baeb625b4d0");
        System.out.println(byGuid);
    }
}
