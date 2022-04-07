package com.panyue.judgmentdoc.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;

/**
 * @author: panyue
 * @create: 2022-04-08
 */
@Component
public class PdfUtil {

    private static String FONT_SIMSUN;

    @Value("${directory.static.fonts.simsun}")
    public void setTempExProduct(String fontSimsun) {
        FONT_SIMSUN = fontSimsun;
    }

    /**
     * 建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中
     *
     * @param document    文档
     * @param pdfPathName 文件保存路径和名称
     * @return 书写器(Writer)
     * @throws Exception 异常
     */
    public static PdfWriter createPdfWriter(String pdfPathName, Document document) throws Exception {
        File file = new File(pdfPathName);

        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        writer.setViewerPreferences(PdfWriter.PageModeUseThumbs);

        return writer;
    }

    /**
     * 设置标题内容
     *
     * @param title    标题文本
     * @param document 文档
     * @throws Exception 异常
     */
    public static void setPdfTitle(String title, Document document) throws Exception {
        Paragraph paragraph = new Paragraph(title, getPdfChineseFont(0));
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setSpacingAfter(2);
        document.add(paragraph);
    }

    /**
     * 设置正文内容
     *
     * @param text            正文内容
     * @param document        文档
     * @param alignment       对齐方式
     * @param firstLineIndent 首行缩进
     * @param spacingAfter    段后间隔
     * @throws Exception 异常
     */
    public static void setPdfMainBody(String text, Document document, int alignment, int firstLineIndent, int spacingAfter) throws Exception {
        Paragraph lsh = new Paragraph(text, getPdfChineseFont(1));
        lsh.setAlignment(alignment);
        lsh.setFirstLineIndent(firstLineIndent);
        lsh.setSpacingAfter(spacingAfter);
        document.add(lsh);
    }

    /**
     * 设置表格内容，并将表格加入文档中
     *
     * @param dates    数据
     * @param document 文档
     * @param table    表格
     * @throws Exception 异常
     */
    public static void setPdfTableContent(Object[][] dates, Document document, PdfPTable table) throws Exception {
        for (int i = 0; i < dates.length; i++) {
            for (int j = 0; j < dates[i].length; j++) {
                //表格的单元格
                PdfPCell pdfCell = new PdfPCell();
                //设置表格行高
                if (i > 0 && i < 3) {
                    pdfCell.setMinimumHeight(50);
                } else {
                    pdfCell.setMinimumHeight(25);
                }
                Paragraph paragraph = new Paragraph(dates[i][j] + "", getPdfChineseFont(1));
                pdfCell.setPhrase(paragraph);

                setCellStyle(pdfCell);
                table.addCell(pdfCell);
            }
        }

        document.add(table);
    }

    /**
     * 设置表格单元格样式
     *
     * @param pdfCell 单元格对象
     */
    public static void setCellStyle(PdfPCell pdfCell) {
        pdfCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        pdfCell.setBackgroundColor(new BaseColor(0xdd7e6b));
        pdfCell.setBorderWidthTop(0.1f);
        pdfCell.setBorderWidthBottom(0.1f);
        pdfCell.setBorderWidthLeft(0.1f);
        pdfCell.setBorderWidthRight(0.1f);
    }

    /**
     * 设置pdf字体及大小
     *
     * @param type 标题或者内容 0-标题 1-内容
     * @return 字体
     * @throws Exception 异常
     */
    public static Font getPdfChineseFont(int type) throws Exception {
        // 使用系统字体
        BaseFont bfChinese = BaseFont.createFont(FONT_SIMSUN, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        Font font;
        if (type == 1) {
            font = new Font(bfChinese, 10, Font.NORMAL);
        } else {
            font = new Font(bfChinese, 14, Font.BOLD);
        }
        return font;
    }

}
