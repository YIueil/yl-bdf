package cc.yiueil.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Author:YIueil
 * Date:2022/7/5 22:24
 * Description: json工具类
 */
public class JSONUtils {
    private static class SingletonRegistry {
        private static final ObjectMapper objectMapper;
        static {
            objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); // 忽略不能反序列化的字段
            objectMapper.findAndRegisterModules(); // 注册 jsr 扩展
        }
    }

    /**
     * 单例适用ObjectMapper, 提高性能, 注意配置不变线程安全, 使用过程中不能修改配置
     * @return objectMapper实例
     */
    public static ObjectMapper getInstance() {
        return SingletonRegistry.objectMapper;
    }

    public static String toJSONString(Object obj) {
        try {
            return getInstance().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    public static <T> T parse(Class<T> clazz, String jsonStr) {
        try {
            return getInstance().readValue(jsonStr, clazz);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

}
