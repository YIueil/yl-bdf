package cc.yiueil.data;

import cc.yiueil.util.CollectionUtils;
import cc.yiueil.util.MapUtils;

import javax.persistence.Parameter;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Author:YIueil
 * Date:2022/7/22 18:01
 * Description: 原生sql执行 查询相关内容
 */
public interface SqlDao {
    List<Map<String, Object>> sqlAsMap(String sql);

    List<Map<String, Object>> sqlAsMap(String sql, int pageIndex, int pageSize);

    List<Map<String, Object>> sqlAsMap(String sql, Map<String, Object> parameters);

    List<Map<String, Object>> sqlAsMap(String sql, Map<String, Object> parameters, int pageIndex, int pageSize);

    int executeUpdate(String sql, Map<String, Object> parameters);

    int countSize(String sql, Map<String, Object> parameters);

    default void setParameters(Query query, Map<String, Object> args) {
        // 获取到真实的参数列表
        List<String> parameters = query.getParameters().stream().map(Parameter::getName).collect(Collectors.toList());
        if (MapUtils.isNotEmpty(args) && CollectionUtils.isNotEmpty(parameters)) {
            Set<String> keySet = args.keySet();
            for (String key : keySet) {
                if (parameters.contains(key)) {
                    query.setParameter(key, args.get(key));
                }
            }
        }
    }
}