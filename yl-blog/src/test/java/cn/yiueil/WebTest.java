package cn.yiueil;

import cn.yiueil.data.impl.JpaBaseDao;
import cn.yiueil.entity.Blog;
import cn.yiueil.entity.Tag;
import cn.yiueil.repository.BlogRepository;
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

    @Autowired
    BlogRepository repository;

    /**
     * 测试：
     */
    @Test
    @Commit
    @Transactional
    public void test1(){
        Blog blog = new Blog();
        blog.setTitle("hello world");
        blog.setContent("内容哦123444");
        List<Tag> tagList = new ArrayList<>();
        tagList.add(new Tag("杂谈"));
        tagList.add(new Tag("技术"));
        blog.setTags(tagList);
        baseDao.batchSave(tagList);
        baseDao.save(blog);
    }
}
