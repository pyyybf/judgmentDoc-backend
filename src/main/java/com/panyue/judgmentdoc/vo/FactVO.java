package com.panyue.judgmentdoc.vo;

import com.panyue.judgmentdoc.po.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: panyue
 * @create: 2022-04-08
 */
public class FactVO extends TextVO {

    /**
     * 事实计数
     */
    private int count;
    /**
     * 缺失法条
     */
    private List<Article> needs;

    public FactVO() {
        this.setType(1);
        this.needs = new ArrayList<>();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Article> getNeeds() {
        return needs;
    }

    public void setNeeds(List<Article> needs) {
        this.needs = needs;
    }

    public void addNeed(Article article) {
        this.needs.add(article);
    }

}
