package com.panyue.judgmentdoc.bl;

import com.panyue.judgmentdoc.vo.ResponseVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: panyue
 * @create: 2022-04-08
 */
@Service
@FeignClient(url = "${model.url}", name = "${model.name}")
public interface ModelService {

    /**
     * 文书检验python模型
     *
     * @param text  正文
     * @param crime 案由
     * @return ResponseVO 检验结果
     */
    @GetMapping(value = "/check")
    ResponseVO check(@RequestParam(value = "text") String text,
                     @RequestParam(value = "crime", defaultValue = "traffic") String crime);

}
