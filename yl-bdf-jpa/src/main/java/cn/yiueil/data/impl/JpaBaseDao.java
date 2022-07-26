package cn.yiueil.data.impl;

import cn.yiueil.data.*;
import cn.yiueil.exception.PageException;
import cn.yiueil.util.ParseUtils;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Author:YIueil
 * Date:2022/7/22 17:50
 * Description: jpa基础查询实现
 */
public class JpaBaseDao implements BatchDao, SqlDao, GeneratorDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public <T> Optional<T> findById(Class<T> tClass, Object id) {
        return Optional.ofNullable(entityManager.find(tClass, id));
    }

    @Override
    public void save(Object entityObject) {
        generatorGuid(entityObject);
        updateModifyTime(entityObject);
        generatorCreateTime(entityObject);
        generatorCreateUser(entityObject);
        entityManager.merge(entityObject);
    }

    @Override
    public void delete(Object entityObject) {
        entityManager.remove(entityObject);
    }

    @Override
    public <T> void deleteById(Class<T> tClass, Object id) {
        findById(tClass, id).ifPresent(this::delete);
    }

    @Override
    public <T> void deleteByGuid(Class<T> tClass, String guid) {
        // todo 完成根据guid删除
    }

    @Override
    public List<Map<String, Object>> sqlAsMap(String sql) {
        return sqlAsMap(sql, null);
    }

    @Override
    public List<Map<String, Object>> sqlAsMap(String sql, int pageIndex, int pageSize) {
        return sqlAsMap(sql, null, pageIndex, pageSize);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> sqlAsMap(String sql, Map<String, Object> parameters) {
        Query query = entityManager.createNativeQuery(sql);
        setParameters(query, parameters);
        return (List<Map<String, Object>>) query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> sqlAsMap(String sql, Map<String, Object> parameters, int pageIndex, int pageSize) {
        if (pageIndex < 1) {
            throw new PageException("pageIndex: 当前页码 必须大于1");
        }
        if (pageSize < 1) {
            throw new RuntimeException("pageSize: 页面数量 必须大于1");
        }
        Query query = entityManager.createNativeQuery(sql);
        query.setFirstResult((--pageIndex) * pageSize);
        query.setMaxResults(pageSize);
        setParameters(query, parameters);
        return (List<Map<String, Object>>) query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
    }

    @Override
    public int countSize(String sql, Map<String, Object> parameters) {
        Query query = entityManager.createNativeQuery(sql);
        setParameters(query, parameters);
        return ParseUtils.getInteger(query.getResultList().get(0), 0);
    }

    @Override
    public int executeUpdate(String sql, Map<String, Object> parameters) {
        Query query = entityManager.createNativeQuery(sql);
        setParameters(query, parameters);
        return query.executeUpdate();
    }
}
