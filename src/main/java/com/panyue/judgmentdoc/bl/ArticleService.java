package com.panyue.judgmentdoc.bl;

import com.panyue.judgmentdoc.vo.PageVO;
import com.panyue.judgmentdoc.vo.TreeVO;

import java.util.List;

/**
 * @author: panyue
 * @create: 2022-04-07
 */
public interface ArticleService {

    /**
     * 分页获取法条列表信息
     *
     * @param keyword  关键词
     * @param number   条目
     * @param crime    主旨
     * @param catalogs 符合条件的目录id
     * @param pageNum  页码
     * @param pageSize 页面显示条数
     * @return PageVO 分页信息
     */
    PageVO getAll(String keyword, String number, String crime, List<Long> catalogs, Integer pageNum, Integer pageSize);

    /**
     * 获取目录树节点列表
     *
     * @return List<TreeVO>
     */
    List<TreeVO> getCatalogue();

}
