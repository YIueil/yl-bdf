package cc.yiueil.service;

import cc.yiueil.annotation.LogAnnotation;
import cc.yiueil.annotation.TimeInterval;
import cc.yiueil.data.impl.JpaBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    @Autowired
    JpaBaseDao jpaBaseDao;

    @LogAnnotation
    @TimeInterval
    public void test() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void test2() {

    }
}
