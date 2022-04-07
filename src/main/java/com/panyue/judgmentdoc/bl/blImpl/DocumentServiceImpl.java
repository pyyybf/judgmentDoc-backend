package com.panyue.judgmentdoc.bl.blImpl;

import com.github.pagehelper.PageHelper;
import com.panyue.judgmentdoc.bl.DocumentService;
import com.panyue.judgmentdoc.data.DocumentMapper;
import com.panyue.judgmentdoc.po.Catalog;
import com.panyue.judgmentdoc.po.Document;
import com.panyue.judgmentdoc.util.TreeUtil;
import com.panyue.judgmentdoc.vo.PageVO;
import com.panyue.judgmentdoc.vo.TreeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: panyue
 * @create: 2022-04-07
 */
@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    DocumentMapper documentMapper;

    @Override
    public PageVO getAll(String keyword, String courtName, String name, String number, String start, String end, List<Long> catalogs, Integer pageNum, Integer pageSize) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null, endDate = null;
        if (!start.isEmpty()) {
            startDate = sdf.parse(start);
        }
        if (!end.isEmpty()) {
            endDate = sdf.parse(end);
        }
        PageHelper.startPage(pageNum, pageSize);
        return PageVO.restPage(documentMapper.getAll(keyword, courtName, name, number, startDate, endDate, catalogs));
    }

    @Override
    public List<TreeVO> getCatalogue() {
        List<TreeVO> catalogs = new ArrayList<>();
        for (Catalog catalog : documentMapper.getAllCatalog()) {
            catalogs.add(new TreeVO(catalog));
        }
        return TreeUtil.buildTree(catalogs);
    }

    @Override
    public Document getDocumentById(Long id) {
        Document document = documentMapper.getDocumentById(id);
        document.setMembers(documentMapper.getMembersByDocumentId(id));
        return document;
    }

    @Override
    public PageVO getDocumentsByUserId(Long userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return PageVO.restPage(documentMapper.getDocumentsByUserId(userId));
    }

}
