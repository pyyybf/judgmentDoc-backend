package com.panyue.judgmentdoc.vo;

import com.panyue.judgmentdoc.po.Article;

/**
 * @author: panyue
 * @create: 2022-04-08
 */
public class LawVO extends TextVO {

    private Article article;

    public LawVO() {
        this.setType(2);
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

}
