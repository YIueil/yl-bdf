package cn.yiueil.query;

/**
 * Author:YIueil
 * Date:2022/7/6 17:34
 * Description: 动态查询
 */
public class DynamicQuery {
    // todo 优化默认动态查询配置机制
    public static final String DEFAULT_CONFIG_PATH = DynamicQuery.class.getClassLoader().getResource("dynamicsql/employee.xml").getFile();
}
