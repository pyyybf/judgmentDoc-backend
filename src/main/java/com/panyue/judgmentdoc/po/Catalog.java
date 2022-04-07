package com.panyue.judgmentdoc.po;

/**
 * @author: panyue
 * @create: 2022-04-07
 */
public class Catalog {

    private Long id;
    private String name;
    private Long parentId;

    public Catalog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

}
