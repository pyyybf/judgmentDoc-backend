package com.panyue.judgmentdoc.bl.blImpl;

import com.github.pagehelper.PageHelper;
import com.panyue.judgmentdoc.bl.ArticleService;
import com.panyue.judgmentdoc.data.ArticleMapper;
import com.panyue.judgmentdoc.po.Catalog;
import com.panyue.judgmentdoc.util.TreeUtil;
import com.panyue.judgmentdoc.vo.PageVO;
import com.panyue.judgmentdoc.vo.TreeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: panyue
 * @create: 2022-04-07
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public PageVO getAll(String keyword, String number, String crime, List<Long> catalogs, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return PageVO.restPage(articleMapper.getAll(keyword, number, crime, catalogs));
    }

    @Override
    public List<TreeVO> getCatalogue() {
        List<TreeVO> catalogs = new ArrayList<>();
        for (Catalog catalog : articleMapper.getAllCatalog()) {
            catalogs.add(new TreeVO(catalog));
        }
        return TreeUtil.buildTree(catalogs);
    }

}
