package cc.yiueil.service;

import cc.yiueil.dto.DynamicQueryDTO;
import cc.yiueil.vo.PageVo;
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
     * @param parameters 过滤条件
     * @param pageIndex 当前页码
     * @param pageSize 每页数量
     * @return 分页查询结果
     */
    PageVo searchPage(DynamicQueryDTO dynamicQueryDTO, Map<String, Object> parameters, Integer pageIndex, Integer pageSize) throws FileNotFoundException, DocumentException;
}
