package cn.yiueil.data;

import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * Author:YIueil
 * Date:2022/7/22 18:01
 * Description: 原生sql执行 查询相关内容
 */
public interface SqlDao {
    List<Map<String, Object>> sqlAsMap(String sql, Map<String, Object> parameters);

    int executeUpdate(String sql, Map<String, Object> parameters);

    void setParameters(Query query, Map<String, Object> args);
}
