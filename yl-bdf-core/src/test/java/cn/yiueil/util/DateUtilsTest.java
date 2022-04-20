package cn.yiueil.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateUtilsTest {

    @Test
    void getYear() {
        System.out.println(DateUtils.getYear());
    }

    @Test
    void getMouth() {
        System.out.println(DateUtils.getMouth());
    }

    @Test
    void getDayOfMouth() {
        System.out.println(DateUtils.getDayOfMouth());
    }

    @Test
    void localDate2Date() {
    }

    @Test
    void str2Date() {
    }

    @Test
    void localDateTime2Date() {
    }

    @Test
    void getCurrentTerm() {
        System.out.println(DateUtils.getCurrentTerm());
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
    void testGetYear1() {
    }

    @Test
    void testGetMouth1() {
    }

    @Test
    void testGetDayOfMouth1() {
    }

    @Test
    void testGetCurrentTerm1() {
    }

    @Test
    void testLocalDate2Date() {
        LocalDate now = LocalDate.now();
        System.out.println(now);
        Date date = DateUtils.localDate2Date(now);
        System.out.println(date);
    }

    @Test
    void testStr2Date() {
        Date date = DateUtils.str2Date("2022-04-20");
        System.out.println(date);
    }

    @Test
    void testLocalDateTime2Date() {
    }

    @Test
    void timestampToString() {
    }
}