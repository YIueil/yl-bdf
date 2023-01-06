package cc.yiueil.util;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Locale;

class LocalDateTimeUtilsTest {

    @Test
    void getYear() {
        System.out.println(LocalDateTimeUtils.getYear());
    }

    @Test
    void getMouth() {
        System.out.println(LocalDateTimeUtils.getMouth());
    }

    @Test
    void getDayOfMouth() {
        System.out.println(LocalDateTimeUtils.getDayOfMouth());
    }

    @Test
    void getCurrentTerm() {
        System.out.println(LocalDateTimeUtils.getCurrentTerm());
    }

    @Test
    void testGetYear() {
    }

    @Test
    void testGetMouth() {
    }

    @Test
    void testGetDayOfMouth() {
    }

    @Test
    void testGetCurrentTerm() {

    }

    @Test
    void timestampToString() {
        System.out.println(LocalDateTimeUtils.timestampToString(new Date().getTime(), Locale.CHINA, "Asia/Shanghai"));
        System.out.println(LocalDateTimeUtils.timestampToString(new Date().getTime(), Locale.US, "America/New_York"));
    }
}