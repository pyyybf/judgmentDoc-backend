package com.panyue.judgmentdoc.vo;

/**
 * @author: panyue
 * @create: 2022-04-08
 */
public class ConclusionVO extends TextVO {
    /**
     * 结论计数
     */
    private int count;

    public ConclusionVO() {
        this.setType(3);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
