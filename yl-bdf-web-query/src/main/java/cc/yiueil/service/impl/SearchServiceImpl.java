package cc.yiueil.service.impl;

import cc.yiueil.data.impl.JpaBaseDao;
import cc.yiueil.dto.DynamicQueryDTO;
import cc.yiueil.exception.BusinessException;
import cc.yiueil.query.ConfigResolver;
import cc.yiueil.query.instance.DynamicQuery;
import cc.yiueil.query.DynamicQueryNode;
import cc.yiueil.query.DynamicQueryPool;
import cc.yiueil.query.instance.DynamicQueryInst;
import cc.yiueil.service.SearchService;
import cc.yiueil.util.StringUtils;
import cc.yiueil.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.net.URL;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    JpaBaseDao baseDao;

    @Autowired
    ConfigResolver configResolver;

    @Autowired
    DynamicQueryPool dynamicQueryPool;

    @Override
    @Transactional
    public PageVo searchPage(DynamicQueryDTO dynamicQueryDTO, Map<String, Object> parameters, Integer pageIndex, Integer pageSize) {
        PageVo pageVo = new PageVo();
        pageVo.setPageIndex(pageIndex);
        pageVo.setPageSize(pageSize);
        if (StringUtils.isEmpty(dynamicQueryDTO.getConfigPath())) {
            dynamicQueryDTO.setConfigPath("dynamicsql");
        }
        if (StringUtils.isEmpty(dynamicQueryDTO.getConfigFile())) {
            dynamicQueryDTO.setConfigFile("database.xml");
        }
        // todo 判断文件是否已经变更过
        URL url = SearchServiceImpl.class.getClassLoader().getResource(dynamicQueryDTO.getFullPath());
        if (url == null) {
            throw new RuntimeException("没有找到配置文件:" + dynamicQueryDTO.getFullPath());
        }
        String filepath = url.getFile();
        File file = new File(filepath);
        DynamicQueryNode dynamicQueryNode = dynamicQueryPool.findDynamicNode(dynamicQueryDTO).orElseThrow(() -> new BusinessException("没有找到配置节点"));
        if (file.lastModified() != dynamicQueryNode.getLastModified()) { // 文件已更新, 需要重新加载节点
            dynamicQueryNode = configResolver.buildDynamicQueryNode(file);
        }
        DynamicQuery dynamicQuery = dynamicQueryNode.getDynamicQueryMap().get(dynamicQueryDTO.getConfigId());
        if (dynamicQuery == null) {
            throw new BusinessException("没有找到对应配置服务: " + dynamicQueryDTO.getConfigId());
        }
        DynamicQueryInst dynamicQueryInst = configResolver.constructInst(dynamicQuery, parameters); // 仅construct时使用外部parameters
        pageVo.setBody(baseDao.sqlAsMap(dynamicQueryInst.getSql(), dynamicQueryInst.getParameters(), pageIndex, pageSize));
        pageVo.setItemCounts(baseDao.countSize(dynamicQueryInst.getCountSql(), dynamicQueryInst.getParameters()));
        pageVo.setPageTotal((pageVo.getItemCounts() / pageSize) + 1);
        return pageVo;
    }
}