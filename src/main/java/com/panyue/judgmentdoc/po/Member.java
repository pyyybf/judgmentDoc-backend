package com.panyue.judgmentdoc.po;

/**
 * @author: panyue
 * @create: 2022-04-07
 */
public class Member implements Comparable<Member> {

    private final static String STATUS_TBL = "审判长审判员代理审判员人民陪审员书记员";

    private Long id;
    private String status;
    private String name;
    private Long documentId;

    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    @Override
    public int compareTo(Member m) {
        if (STATUS_TBL.indexOf(this.status) == STATUS_TBL.indexOf(m.getStatus())) {
            return m.getName().compareTo(this.name);
        } else {
            return STATUS_TBL.indexOf(this.status) < STATUS_TBL.indexOf(m.getStatus()) ? -1 : 1;
        }
    }

}
