package cc.yiueil.util;

import org.junit.jupiter.api.Test;

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
        System.out.println(CheckUtils.isIdCard(idCard));
        System.out.println(CheckUtils.isIdCard("511025199711138495"));
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