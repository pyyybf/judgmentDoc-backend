package com.panyue.judgmentdoc.bl;

import com.panyue.judgmentdoc.exception.CheckException;
import com.panyue.judgmentdoc.vo.DocInfoVO;
import com.panyue.judgmentdoc.vo.TextVO;

import java.util.List;

/**
 * @author: panyue
 * @create: 2022-04-08
 */
public interface EditorService {

    /**
     * 导出文书为pdf文件
     *
     * @param docInfoVO 文书信息
     * @return String 临时pdf文件路径
     */
    String exportPdf(DocInfoVO docInfoVO);

    /**
     * 导出文书为word文件
     *
     * @param docInfoVO 文书信息
     * @return String 临时pdf文件路径
     */
    String exportWord(DocInfoVO docInfoVO);

    /**
     * 检验裁判文书，返回组织过的文本节点列表
     *
     * @param text  正文
     * @param crime 案由
     * @return List<TextVO> 文本节点列表
     * @throws CheckException 检验异常
     */
    List<TextVO> check(String text, String crime) throws CheckException;

}
