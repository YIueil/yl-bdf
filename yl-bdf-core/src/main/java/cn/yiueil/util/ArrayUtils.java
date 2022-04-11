package cn.yiueil.util;

/**
 * Author:YIueil
 * Date:2022/4/11 22:19
 * Description: 数组工具类
 */
public class ArrayUtils {
    /**
     * 反转char数组会变化原数组
     * @param charArray char数组
     * @return 反序char数组结果
     */
    public static char[] reverse(char[] charArray) {
        if (charArray != null) {
            for (int i = 0; i < charArray.length/2 ; i++) {
                swap(charArray, i, charArray.length - (i + 1));
            }
        }
        return charArray;
    }

    private static void swap(char[] charArray, int leftIndex, int rightIndex) {
        if (charArray == null) {
            return;
        }
        char temp = charArray[leftIndex];
        charArray[leftIndex] = charArray[rightIndex];
        charArray[rightIndex] = temp;
    }
}
