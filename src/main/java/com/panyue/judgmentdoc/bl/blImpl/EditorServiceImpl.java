package com.panyue.judgmentdoc.bl.blImpl;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.panyue.judgmentdoc.bl.EditorService;
import com.panyue.judgmentdoc.bl.ModelService;
import com.panyue.judgmentdoc.data.ArticleMapper;
import com.panyue.judgmentdoc.exception.CheckException;
import com.panyue.judgmentdoc.util.PdfUtil;
import com.panyue.judgmentdoc.util.WordUtil;
import com.panyue.judgmentdoc.vo.*;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author: panyue
 * @create: 2022-04-08
 */
@Service
public class EditorServiceImpl implements EditorService {

    @Value("${directory.temporary}")
    private String TEMPORARY_PATH;

    @Autowired
    ModelService modelService;

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public String exportPdf(DocInfoVO docInfoVO) {
        Document document = new Document();
        try {
            //创建书写器，写入临时文件目录
            String pdfPathName = TEMPORARY_PATH + UUID.randomUUID().toString().replaceAll("-", "") + ".pdf";
            PdfWriter writer = PdfUtil.createPdfWriter(pdfPathName, document);
            //设置A4
            document.setPageSize(PageSize.A4);
            document.open();
            //设置法院名
            PdfUtil.setPdfTitle(docInfoVO.getCourtName(), document);
            //设置文书名字
            PdfUtil.setPdfTitle(docInfoVO.getName(), document);
            //设置案号
            PdfUtil.setPdfMainBody(docInfoVO.getNumber(), document, Element.ALIGN_RIGHT, 0, 0);
            //设置正文
            String[] contents = docInfoVO.getContent().split("\n");
            for (String paragraph : contents) {
                PdfUtil.setPdfMainBody(paragraph.replace((char) 12288, ' ').replace((char) 160, ' ').trim(), document, Element.ALIGN_LEFT, 20, 0);
            }
            //设置落款人员
            for (String member : docInfoVO.getMemberList()) {
                PdfUtil.setPdfMainBody(member, document, Element.ALIGN_RIGHT, 0, 0);
            }
            //设置落款日期
            PdfUtil.setPdfMainBody(docInfoVO.getChineseDate(), document, Element.ALIGN_RIGHT, 0, 0);

            document.close();
            writer.close();
            return pdfPathName;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String exportWord(DocInfoVO docInfoVO) {
        XWPFDocument document = new XWPFDocument();
        try {
            String wordPathName = TEMPORARY_PATH + UUID.randomUUID().toString().replaceAll("-", "") + ".docx";

            //设置法院名
            WordUtil.createTitle(docInfoVO.getCourtName(), document, 20, "宋体");
            //设置文书名字
            WordUtil.createTitle(docInfoVO.getName(), document, 25, "宋体");
            //设置案号
            WordUtil.createParagraph(docInfoVO.getNumber(), document, ParagraphAlignment.RIGHT, 0, 15, "仿宋");
            //设置正文
            String[] contents = docInfoVO.getContent().split("\n");
            for (String para : contents) {
                WordUtil.createParagraph(para, document, ParagraphAlignment.BOTH, 550, 15, "仿宋");
            }
            //设置落款人员
            for (String member : docInfoVO.getMemberList()) {
                WordUtil.createParagraph(member, document, ParagraphAlignment.RIGHT, 0, 15, "仿宋");
            }
            //设置落款日期
            WordUtil.createParagraph(docInfoVO.getChineseDate(), document, ParagraphAlignment.RIGHT, 0, 15, "仿宋");

            WordUtil.saveDocument(document, wordPathName);

            return wordPathName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TextVO> check(String text, String crime) throws CheckException {
        ResponseVO response = modelService.check(text, crime);
        if (!response.getSuccess()) {  //检验失败
            throw new CheckException(response.getMessage());
        }
        List<Map<String, Object>> textList = (ArrayList) response.getContent();

        List<TextVO> texts = new ArrayList<>();
        for (Map<String, Object> textMap : textList) {
            TextVO curText = null;
            if ((int) textMap.get("type") == 0) {  //普通文本
                curText = new TextVO();
            } else {
                if ((int) textMap.get("type") == 1) {  //事实
                    curText = new FactVO();
                    ((FactVO) curText).setCount((int) textMap.get("count"));
                    List<Long> needs = (ArrayList<Long>) textMap.get("needs");
                    if (needs.size() > 0) {
                        ((FactVO) curText).setNeeds(articleMapper.getArticleListByIds(needs));
                    }
                } else if ((int) textMap.get("type") == 2) {  //法条
                    curText = new LawVO();
                    ((LawVO) curText).setArticle(articleMapper.getArticleById(Long.valueOf(textMap.get("articleId").toString())));
                } else if ((int) textMap.get("type") == 3) {  //结论
                    curText = new ConclusionVO();
                    ((ConclusionVO) curText).setCount((int) textMap.get("count"));
                }
                for (String relation : (ArrayList<String>) textMap.get("relations")) {
                    curText.addRelation(relation);
                }
            }
            assert curText != null;
            curText.setId((String) textMap.get("id"));
            curText.setContent((String) textMap.get("content"));
            texts.add(curText);
        }

        return texts;
    }

}
