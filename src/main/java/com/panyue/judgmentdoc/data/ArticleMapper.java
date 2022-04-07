package com.panyue.judgmentdoc.data;

import com.panyue.judgmentdoc.po.Article;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
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

}
