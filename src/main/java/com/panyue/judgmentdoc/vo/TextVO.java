package com.panyue.judgmentdoc.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: panyue
 * @create: 2022-04-08
 */
public class TextVO {

    /**
     * 文本id：text-0，fact-0，law-0，conclusion-0
     */
    private String id;
    /**
     * 文本内容
     */
    private String content;
    /**
     * 文本类型：0普通文本，1事实，2法条，3结论
     */
    private int type;
    /**
     * 关联文本id
     */
    private List<String> relations;

    public TextVO() {
        this.setType(0);
        this.relations = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<String> getRelations() {
        return relations;
    }

    public void setRelations(List<String> relations) {
        this.relations = relations;
    }

    public void addRelation(String r) {
        this.relations.add(r);
    }

}
