package cn.yiueil.service;

import cn.yiueil.dto.DynamicQueryDTO;
import cn.yiueil.entity.PageVo;
import org.dom4j.DocumentException;

import java.io.FileNotFoundException;
import java.util.Map;

/**
 * Author:YIueil
 * Date:2022/7/4 1:49
 * Description: 查询服务
 */
public interface SearchService {

    /**
     * 动态分页查询
     * @param dynamicQueryDTO 动态查询参数
     * @param filter 过滤条件
     * @param pageIndex 当前页码
     * @param pageSize 每页数量
     * @return 分页查询结果
     */
    PageVo searchPage(DynamicQueryDTO dynamicQueryDTO, Map<String, Object> filter, Integer pageIndex, Integer pageSize) throws FileNotFoundException, DocumentException;
}
