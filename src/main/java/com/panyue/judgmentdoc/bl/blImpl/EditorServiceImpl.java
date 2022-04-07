package com.panyue.judgmentdoc.bl.blImpl;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.panyue.judgmentdoc.bl.EditorService;
import com.panyue.judgmentdoc.util.PdfUtil;
import com.panyue.judgmentdoc.util.WordUtil;
import com.panyue.judgmentdoc.vo.DocInfoVO;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author: panyue
 * @create: 2022-04-08
 */
@Service
public class EditorServiceImpl implements EditorService {

    @Value("${directory.temporary}")
    private String TEMPORARY_PATH;

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

}
