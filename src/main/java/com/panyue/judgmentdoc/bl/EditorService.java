package com.panyue.judgmentdoc.bl;

import com.panyue.judgmentdoc.vo.DocInfoVO;

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

}
