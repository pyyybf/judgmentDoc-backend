package com.panyue.judgmentdoc.data;

import com.panyue.judgmentdoc.po.Article;
import com.panyue.judgmentdoc.po.Catalog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: panyue
 * @create: 2022-04-07
 */
@Mapper
@Repository
public interface ArticleMapper {

    /**
     * 根据条件获取法条列表
     *
     * @param keyword  关键词
     * @param number   条目
     * @param crime    主旨
     * @param catalogs 符合条件的目录id列表
     * @return List<Article> 法条列表
     */
    List<Article> getAll(@Param(value = "keyword") String keyword,
                         @Param(value = "number") String number,
                         @Param(value = "crime") String crime,
                         @Param(value = "catalogs") List<Long> catalogs);

    /**
     * 获取法条目录列表
     *
     * @return List<Catalog> 目录列表
     */
    List<Catalog> getAllCatalog();

    /**
     * 根据id获取法条
     *
     * @param id 法条id
     * @return Article 法条
     */
    Article getArticleById(@Param(value = "id") Long id);

    /**
     * 根据id列表获取法条列表
     *
     * @param ids 法条id列表
     * @return List<Article> 法条列表
     */
    List<Article> getArticleListByIds(@Param(value = "ids") List<Long> ids);

}
