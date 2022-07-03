package cn.yiueil.service;

import cn.yiueil.annotation.LogAnnotation;
import cn.yiueil.annotation.TimeInterval;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @LogAnnotation
    @TimeInterval
    public void test() {
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
