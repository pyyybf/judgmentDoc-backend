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
     * 设置一段内容
     *
     * @param text            正文内容
     * @param document        文档
     * @param fontSize        字体大小
     * @param alignment       对齐方式
     * @param firstLineIndent 首行缩进
     * @param spacingAfter    段后间隔
     * @throws Exception 异常
     */
    public static void setPdfParagraph(String text, Document document, int fontSize, int alignment, int firstLineIndent, int spacingAfter) throws Exception {
        Paragraph paragraph = new Paragraph(text, getPdfChineseFont(fontSize));
        paragraph.setAlignment(alignment);
        paragraph.setFirstLineIndent(firstLineIndent);
        paragraph.setSpacingAfter(spacingAfter);
        document.add(paragraph);
    }

    /**
     * 设置pdf字体及大小
     *
     * @param size 字体大小
     * @return 字体
     * @throws Exception 异常
     */
    public static Font getPdfChineseFont(int size) throws Exception {
        // 使用系统字体
        BaseFont bfChinese = BaseFont.createFont(FONT_SIMSUN, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        return new Font(bfChinese, size, Font.NORMAL);
    }

}
