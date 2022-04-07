package com.panyue.judgmentdoc.bl;

import com.panyue.judgmentdoc.vo.PageVO;
import com.panyue.judgmentdoc.vo.TreeVO;

import java.text.ParseException;
import java.util.List;

/**
 * @author: panyue
 * @create: 2022-04-07
 */
public interface DocumentService {

    /**
     * 分页获取文书列表信息
     *
     * @param keyword   关键词
     * @param courtName 法院名称
     * @param name      文书名称
     * @param number    案号
     * @param start     起始日期（字符串 yyyy-MM-dd）
     * @param end       结束日期（字符串 yyyy-MM-dd）
     * @param catalogs  符合条件的目录id列表
     * @param pageNum   页码
     * @param pageSize  页面显示条数
     * @return PageVO 分页信息
     */
    PageVO getAll(String keyword, String courtName, String name, String number, String start, String end, List<Long> catalogs, Integer pageNum, Integer pageSize) throws ParseException;

    /**
     * 获取目录树节点列表
     *
     * @return List<TreeVO>
     */
    List<TreeVO> getCatalogue();

}
