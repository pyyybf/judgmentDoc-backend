package com.panyue.judgmentdoc.vo;

import com.panyue.judgmentdoc.po.Member;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: panyue
 * @create: 2022-04-08
 */
public class DocInfoVO {

    private String content;
    private String courtName;
    private String name;
    private String number;
    private String date;
    private List<Member> members;

    public DocInfoVO() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCourtName() {
        return courtName;
    }

    public void setCourtName(String courtName) {
        this.courtName = courtName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public String getChineseDate() {
        try {
            String[] dates = this.date.split("-");
            StringBuilder dateBuilder = new StringBuilder(dates[0] + "年");
            // 年，直接改就行
            for (int i = 0; i < 4; i++) {
                dateBuilder.setCharAt(i, chineseNum(dateBuilder.charAt(i)));
            }
            // 月，十、十一、十二前面加十，十月不用加个位
            if (dates[1].charAt(0) == '1') {
                dateBuilder.append('十');
            }
            if (dates[1].charAt(1) != '0') {
                dateBuilder.append(chineseNum(dates[1].charAt(1)));
            }
            dateBuilder.append('月');
            // 日
            if (dates[2].charAt(0) == '2' || dates[2].charAt(0) == '3') {
                dateBuilder.append(chineseNum(dates[2].charAt(0)) + "十");
            } else if (dates[2].charAt(0) == '1') {
                dateBuilder.append("十");
            }
            if (dates[2].charAt(1) != '0') {
                dateBuilder.append(chineseNum(dates[1].charAt(1)));
            }
            dateBuilder.append('日');
            return dateBuilder.toString();
        } catch (Exception e) {
            return "";
        }
    }

    public List<String> getMemberList() {
        List<String> memberList = new ArrayList<>();
        Collections.sort(this.members);
        for (Member member : this.members) {
            String m = member.getStatus();
            if (member.getName().length() == 2) {
                m = m + "　" + member.getName().charAt(0) + "　" + member.getName().charAt(1);
            } else {
                m = m + "　" + member.getName();
            }
            memberList.add(m);
        }
        return memberList;
    }

    private char chineseNum(char n) {
        switch (n) {
            case '0':
                return '〇';
            case '1':
                return '一';
            case '2':
                return '二';
            case '3':
                return '三';
            case '4':
                return '四';
            case '5':
                return '五';
            case '6':
                return '六';
            case '7':
                return '七';
            case '8':
                return '八';
            case '9':
                return '九';
            default:
                return '〇';
        }
    }

}
