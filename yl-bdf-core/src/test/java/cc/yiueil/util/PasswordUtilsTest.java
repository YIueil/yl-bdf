package cc.yiueil.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordUtilsTest {

    @Test
    void checkPasswordStrength() {
        System.out.println(PasswordUtils.checkPasswordStrength("123456"));
        System.out.println(PasswordUtils.checkPasswordStrength("abcd."));
        System.out.println(PasswordUtils.checkPasswordStrength("Fk12345."));
    }

    @Test
    void estimateCrackTime() {
        System.out.println(PasswordUtils.estimateCrackTime("123456]cAX."));
    }
}