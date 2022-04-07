package com.panyue.judgmentdoc.util;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author: panyue
 * @create: 2022-04-08
 */
public class WordUtil {

    /**
     * 存储为docx文件
     *
     * @param document 文档内容
     * @param path     目标存储文件路径和名称
     */
    public static void saveDocument(XWPFDocument document, String path) throws IOException {
        OutputStream out = new FileOutputStream(path);
        document.write(out);
        out.close();
    }

    /**
     * 写入新标题段落
     *
     * @param text       文本内容
     * @param document   文档内容
     * @param fontSize   字体大小
     * @param fontFamily 字体
     */
    public static void createTitle(String text, XWPFDocument document, int fontSize, String fontFamily) {
        XWPFParagraph titleParagraph = document.createParagraph(); //标题段落
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = titleParagraph.createRun();
        run.setText(text);
        run.setFontSize(fontSize);
        CTFonts font = run.getCTR().addNewRPr().addNewRFonts();
        font.setEastAsia(fontFamily);
    }

    /**
     * 写入新段落
     *
     * @param text            文本内容
     * @param document        文档
     * @param alignment       对齐方式
     * @param firstLineIndent 首行缩进
     * @param fontSize        字体大小
     * @param fontFamily      字体
     */
    public static void createParagraph(String text, XWPFDocument document, ParagraphAlignment alignment, int firstLineIndent, int fontSize, String fontFamily) {
        XWPFParagraph paragraph = document.createParagraph(); //段落
        paragraph.setAlignment(alignment); //对齐
        paragraph.setIndentationFirstLine(firstLineIndent);
        XWPFRun run = paragraph.createRun(); //文本对象
        run.setText(text); //设置内容
        run.setFontSize(fontSize); //字体大小
        CTFonts font = run.getCTR().addNewRPr().addNewRFonts();
        font.setEastAsia(fontFamily); //字体
    }

}
