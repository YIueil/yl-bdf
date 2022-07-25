package cn.yiueil.service.impl;

import cn.yiueil.dto.DynamicQueryDTO;
import cn.yiueil.vo.PageVo;
import cn.yiueil.query.DynamicQuery;
import cn.yiueil.query.DynamicQueryPool;
import cn.yiueil.query.SQLBuilder;
import cn.yiueil.service.SearchService;
import cn.yiueil.util.StringUtils;
import cn.yiueil.util.XmlUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Optional;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    SQLBuilder sqlBuilder;

    @Override
    @Transactional
    public PageVo searchPage(DynamicQueryDTO dynamicQueryDTO, Map<String, Object> filter, Integer pageIndex, Integer pageSize) throws FileNotFoundException, DocumentException {
        PageVo pageVo = new PageVo();
        pageVo.setPageIndex(pageIndex);
        pageVo.setPageSize(pageSize);
        Document document;
        if (StringUtils.isEmpty(dynamicQueryDTO.getConfigPath())) {
            dynamicQueryDTO.setConfigFile("database.xml");
            dynamicQueryDTO.setConfigPath(DynamicQueryPool.DEFAULT_CONFIG_PATH);
        }
        // todo 判断文件是否已经变更过
        Optional<DynamicQuery> dynamicSql = DynamicQueryPool.findDynamicSql(dynamicQueryDTO);
        if (dynamicSql.isPresent()) {
            sqlBuilder.buildPageVo(sqlBuilder.build(dynamicSql.get(), filter), filter, pageVo);
            return pageVo;
        }
        // 不走缓存
        document = XmlUtils.parse(new FileInputStream(dynamicQueryDTO.getConfigPath() + dynamicQueryDTO.getConfigFile()));
        XmlUtils.selectSingleNode(document,
                "//yl:config[@id='"+ dynamicQueryDTO.getConfigId() +"']",
                "yl").ifPresent(node -> {
            String sql = sqlBuilder.build(node, filter);
            sqlBuilder.buildPageVo(sql, filter, pageVo);
        });
        return pageVo;
    }
}
