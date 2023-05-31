package cc.yiueil.service;

import cc.yiueil.dto.DynamicQueryDTO;
import cc.yiueil.vo.PageVo;
import org.dom4j.DocumentException;

import java.io.FileNotFoundException;
import java.util.Map;

/**
 * SearchService 查询服务
 * @author 弋孓 YIueil@163.com
 * @date 2023/5/31 23:38
 * @version 1.0
 */
public interface SearchService {

    /**
     * 动态分页查询
     * @param dynamicQueryDTO 动态查询参数
     * @param parameters 过滤条件
     * @param pageIndex 当前页码
     * @param pageSize 每页数量
     * @return 分页查询结果
     * @throws FileNotFoundException fileNotFoundException
     * @throws DocumentException documentException
     */
    PageVo searchPage(DynamicQueryDTO dynamicQueryDTO, Map<String, Object> parameters, Integer pageIndex, Integer pageSize) throws FileNotFoundException, DocumentException;
}
