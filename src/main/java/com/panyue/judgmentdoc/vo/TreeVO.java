package com.panyue.judgmentdoc.vo;

import com.panyue.judgmentdoc.po.Catalog;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: panyue
 * @create: 2022-04-07
 */
public class TreeVO {

    /**
     * 节点id
     */
    private Long id;
    /**
     * 节点标签
     */
    private String label;
    /**
     * 父节点id
     */
    private Long parentId;
    /**
     * 子节点列表
     */
    private List<TreeVO> children;

    public TreeVO(Catalog catalog) {
        this.id = catalog.getId();
        this.label = catalog.getName();
        this.parentId = catalog.getParentId();
        this.children = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<TreeVO> getChildren() {
        return children;
    }

    public void setChildren(List<TreeVO> children) {
        this.children = children;
    }
}
