package com.panyue.judgmentdoc.controller;

import com.panyue.judgmentdoc.bl.EditorService;
import com.panyue.judgmentdoc.util.FileUtil;
import com.panyue.judgmentdoc.vo.DocInfoVO;
import com.panyue.judgmentdoc.vo.ResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: panyue
 * @create: 2022-04-08
 */
@RestController
@RequestMapping("/api/editor")
@Api(tags = "EditorController", description = "裁判文书编辑管理")
public class EditorController {

    private final static String CHECK_ERROR = "检验失败";

    @Autowired
    EditorService editorService;

    @ApiOperation("导出裁判文书pdf")
    @PostMapping("/export/pdf")
    public void exportPdf(@RequestBody DocInfoVO docInfoVO, HttpServletResponse response) {
        try {
            String pdfPath = editorService.exportPdf(docInfoVO);
            FileUtil.returnStream(response, pdfPath);
            FileUtil.delFile(pdfPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ApiOperation("导出裁判文书word")
    @PostMapping("/export/word")
    public void exportWord(@RequestBody DocInfoVO docInfoVO, HttpServletResponse response) {
        try {
            String wordPath = editorService.exportWord(docInfoVO);
            FileUtil.returnStream(response, wordPath);
            FileUtil.delFile(wordPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ApiOperation("检验裁判文书")
    @GetMapping("/check")
    public ResponseVO check(@RequestParam(value = "text") String text,
                            @RequestParam(value = "crime", defaultValue = "traffic") String crime) {
        try {
            return ResponseVO.buildSuccess(editorService.check(text, crime));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure(CHECK_ERROR);
        }
    }

}
