package cc.yiueil.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * IOUtils IO工具类
 *
 * @author 弋孓 YIueil@163.com
 * @version 1.0
 * @date 2024/1/18 16:04
 */
public class IoUtils {
    /**
     * 将 InputStream 转换为 byte[] 数组
     *
     * @param input 输入流
     * @return byte[] 数组
     * @throws IOException IO异常
     */
    public static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }
}
