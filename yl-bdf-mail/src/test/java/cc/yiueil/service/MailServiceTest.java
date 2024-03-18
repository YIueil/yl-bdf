package cc.yiueil.service;

import cc.yiueil.factory.MailSessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.mail.Session;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MailServiceTest {
    private MailService mailService;

    @BeforeEach
    void setUp() {
        mailService = new MailService();
        Session session = new MailSessionFactory().getSessionByProperties();
        mailService.setSession(session);
    }

    @Test
    void send() {
        List<String> toList = new ArrayList<>();
        List<File> fileList = new ArrayList<>();
        toList.add("yiueil@163.com");
        toList.add("511210125@qq.com");
        fileList.add(new File("C:\\Users\\Administrator\\Desktop\\123.txt"));
        fileList.add(new File("C:\\Users\\Administrator\\Desktop\\123 - 副本.txt"));
        mailService.send("这是一个群发测试邮件👻", "<h1>这是一个群发测试邮件👻</h1>", true, toList, fileList);
    }
}