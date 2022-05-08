package com.panyue.judgmentdoc.data;

import com.panyue.judgmentdoc.po.Catalog;
import com.panyue.judgmentdoc.po.Document;
import com.panyue.judgmentdoc.po.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author: panyue
 * @create: 2022-04-07
 */
@Mapper
@Repository
public interface DocumentMapper {

    /**
     * 根据条件获取文书列表
     *
     * @param keyword   关键词
     * @param courtName 法院名称
     * @param name      文书名称
     * @param number    案号
     * @param startDate 起始日期
     * @param endDate   结束日期
     * @param catalogs  符合条件的目录id列表
     * @return List<Document> 文书列表
     */
    List<Document> getAll(@Param(value = "keyword") String keyword,
                          @Param(value = "courtName") String courtName,
                          @Param(value = "name") String name,
                          @Param(value = "number") String number,
                          @Param(value = "startDate") Date startDate,
                          @Param(value = "endDate") Date endDate,
                          @Param(value = "catalogs") List<Long> catalogs);

    /**
     * 获取文书目录列表
     *
     * @return List<Catalog> 目录列表
     */
    List<Catalog> getAllCatalog();

    /**
     * 根据id获取文书
     *
     * @param id 文书id
     * @return Document 文书
     */
    Document getDocumentById(@Param(value = "id") Long id);

    /**
     * 根据文书id获取成员列表
     *
     * @param documentId 文书id
     * @return List<Member> 成员列表
     */
    List<Member> getMembersByDocumentId(@Param(value = "documentId") Long documentId);

    /**
     * 根据用户id获取文书列表
     *
     * @param userId 用户id
     * @return List<Document> 文书列表
     */
    List<Document> getDocumentsByUserId(@Param(value = "userId") Long userId);

}
