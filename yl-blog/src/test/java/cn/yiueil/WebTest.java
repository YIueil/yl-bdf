package cn.yiueil;

import cn.yiueil.data.impl.JpaBaseDao;
import cn.yiueil.entity.Blog;
import cn.yiueil.entity.Tag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
        Blog front = new Blog(null, null, null, null, null, 0, "前端", "前端", null);
        baseDao.save(front);
        Blog front_children1 = new Blog(null, null, null, null, null, front.getId(), "前端基础", "前端基础", null);
        baseDao.save(front_children1);
        Blog front_children1_1 = new Blog(null, null, null, null, null, front_children1.getId(), "html", "html", null);
        Blog front_children1_2 = new Blog(null, null, null, null, null, front_children1.getId(), "css", "css", null);
        Blog front_children1_3 = new Blog(null, null, null, null, null, front_children1.getId(), "js", "js", null);
        baseDao.save(front_children1_1);
        baseDao.save(front_children1_2);
        baseDao.save(front_children1_3);
        Blog backend = new Blog(null, null, null, null, null, 0, "后端", "后端", null);
        baseDao.save(backend);
        Blog backend_children1 = new Blog(null, null, null, null, null, backend.getId(), "java", "java", null);
        Blog backend_children2 = new Blog(null, null, null, null, null, backend.getId(), "python", "python", null);
        baseDao.save(backend_children1);
        baseDao.save(backend_children2);
    }
}
