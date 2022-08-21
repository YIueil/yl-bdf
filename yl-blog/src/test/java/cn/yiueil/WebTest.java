package cn.yiueil;

import cn.yiueil.data.impl.JpaBaseDao;
import cn.yiueil.entity.PageEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring/spring.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class WebTest {
    @Autowired
    JpaBaseDao baseDao;

    /**
     * 测试：
     */
    @Test
    @Commit
    @Transactional
    public void test1(){
        PageEntity front = new PageEntity(null, null, null, null, null, 0, "前端", "前端", null, null);
        baseDao.save(front);
        PageEntity front_children1 = new PageEntity(null, null, null, null, null, front.getId(), "前端基础", "前端基础", null, null);
        baseDao.save(front_children1);
        PageEntity front_children1_1 = new PageEntity(null, null, null, null, null, front_children1.getId(), "html", "html", null, null);
        PageEntity front_children1_2 = new PageEntity(null, null, null, null, null, front_children1.getId(), "css", "css", null, null);
        PageEntity front_children1_3 = new PageEntity(null, null, null, null, null, front_children1.getId(), "js", "js", null, null);
        baseDao.save(front_children1_1);
        baseDao.save(front_children1_2);
        baseDao.save(front_children1_3);
        PageEntity backend = new PageEntity(null, null, null, null, null, 0, "后端", "后端", null, null);
        baseDao.save(backend);
        PageEntity backend_children1 = new PageEntity(null, null, null, null, null, backend.getId(), "java", "java", null, null);
        PageEntity backend_children2 = new PageEntity(null, null, null, null, null, backend.getId(), "python", "python", null, null);
        baseDao.save(backend_children1);
        baseDao.save(backend_children2);
    }
}
