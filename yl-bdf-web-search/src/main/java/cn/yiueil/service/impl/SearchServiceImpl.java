package cn.yiueil.service.impl;

import cn.yiueil.data.impl.JpaBaseDao;
import cn.yiueil.dto.DynamicQueryDTO;
import cn.yiueil.entity.PageVo;
import cn.yiueil.query.DynamicQuery;
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

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    JpaBaseDao jpaBaseDao;

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
            dynamicQueryDTO.setConfigPath(DynamicQuery.DEFAULT_CONFIG_PATH);
        }
        document = XmlUtils.parse(new FileInputStream(dynamicQueryDTO.getConfigPath()));
        XmlUtils.selectSingleNode(document,
                "//yl:config[@id='"+ dynamicQueryDTO.getConfigId() +"']",
                "yl").ifPresent(node -> {
            String sql = sqlBuilder.build(node, filter);
            pageVo.setBody(jpaBaseDao.sqlAsMap(sql, filter, pageIndex, pageSize));
            pageVo.setItemCounts(jpaBaseDao.countSize(sqlBuilder.buildCount(sql), filter));
            pageVo.setPageTotal((pageVo.getItemCounts() / pageSize) + 1);
        });
        return pageVo;
    }
}
