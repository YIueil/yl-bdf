package cn.yiueil.util;

import java.util.Random;

/**
 * Author:YIueil
 * Date:2022/7/24 21:51
 * Description: 随机工具类
 */
public class RandomUtils {
    private static final Random random = new Random();

    /**
     * 随机生成boolean值
     * @return 随机boolean值
     */
    public static boolean randomBoolean() {
        return random.nextBoolean();
    }

    /**
     * 随机生成一个范围类的int数
     * @param min 最小值(闭区间: 会取到该值)
     * @param max 最大值(开区间: 不会取到该值)
     * @return 随机int值
     */
    public static int randomInt(int min, int max) {
        return new Random().nextInt(max) % (max - min + 1) + min;
    }
}
