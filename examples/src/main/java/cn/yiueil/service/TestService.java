package cn.yiueil.service;

import cn.yiueil.annotation.LogAnnotation;
import cn.yiueil.annotation.TimeInterval;
import cn.yiueil.data.impl.JpaBaseDao;
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
