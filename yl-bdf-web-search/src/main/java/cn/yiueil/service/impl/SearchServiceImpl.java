package cn.yiueil.service.impl;

import cn.yiueil.dto.DynamicQueryDTO;
import cn.yiueil.entity.PageVo;
import cn.yiueil.service.SearchService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {
    @Override
    public PageVo searchPage(DynamicQueryDTO dynamicQueryDTO, Map<String, Object> filter, Integer pageIndex, Integer pageSize) {
        return null;
    }
}
