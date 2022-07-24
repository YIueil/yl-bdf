package cn.yiueil.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FakeUtilsTest {
    @Test
    void getChineseName() {
        System.out.println(FakeUtils.getChineseName());
    }

    @Test
    void testGetPhone() {
        String phone = FakeUtils.getPhone();
        System.out.println(phone);
        System.out.println(CheckUtils.isPhone(phone));
    }

    @Test
    void testGetEmail() {
        String email = FakeUtils.getEmail();
        System.out.println(email);
        System.out.println(CheckUtils.isEmail(email));
    }

    @Test
    void testGetIdCard() {
        String idCard = FakeUtils.getIdCard();
        System.out.println(idCard);
        System.out.println(CheckUtils.isIDCard(idCard));
        System.out.println(CheckUtils.isIDCard("511025199711138495"));
    }

    @Test
    void testGetChineseName() {
        System.out.println(FakeUtils.getSgChineseName());
    }

    @Test
    void getNum() {
        System.out.println(RandomUtils.randomInt(0, 1));
    }
}