package com.panyue.judgmentdoc.util;

import com.panyue.judgmentdoc.vo.TreeVO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: panyue
 * @create: 2022-04-07
 */
public class TreeUtil {

    /**
     * 构建目录树
     *
     * @param nodes 所有树节点列表
     * @return List<TreeVO> 顶层树节点列表
     */
    public static List<TreeVO> buildTree(List<TreeVO> nodes) {
        Map<Long, List<TreeVO>> children = nodes.stream().filter(node -> node.getParentId() != 0)
                .collect(Collectors.groupingBy(node -> node.getParentId()));
        nodes.forEach(node -> node.setChildren(children.get(node.getId())));
        return nodes.stream().filter(node -> node.getParentId() == 0).collect(Collectors.toList());
    }

}
