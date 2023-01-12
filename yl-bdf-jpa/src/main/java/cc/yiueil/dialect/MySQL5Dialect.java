package cc.yiueil.dialect;


import org.hibernate.dialect.MySQL57Dialect;

/**
 * Author:YIueil
 * Date:2023/1/12 14:22
 * Description: mysql5 方言配置类
 */
public class MySQL5Dialect extends MySQL57Dialect {
    @Override
    public String getTableTypeString() {
        return "ENGINE=InnoDB DEFAULT CHARSET=UTF8";
    }
}
