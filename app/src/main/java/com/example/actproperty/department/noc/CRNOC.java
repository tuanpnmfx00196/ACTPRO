package com.example.actproperty.department.noc;

public class CRNOC {
    private int id;
    private String cableidcr, codecr, commentcr, datetimecr;
    private int statuscr;

    public CRNOC(int id, String cableidcr, String codecr, String commentcr, String datetimecr, int statuscr) {
        this.id = id;
        this.cableidcr = cableidcr;
        this.codecr = codecr;
        this.commentcr = commentcr;
        this.datetimecr = datetimecr;
        this.statuscr = statuscr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCableidcr() {
        return cableidcr;
    }

    public void setCableidcr(String cableidcr) {
        this.cableidcr = cableidcr;
    }

    public String getCodecr() {
        return codecr;
    }

    public void setCodecr(String codecr) {
        this.codecr = codecr;
    }

    public String getCommentcr() {
        return commentcr;
    }

    public void setCommentcr(String commentcr) {
        this.commentcr = commentcr;
    }

    public String getDatetimecr() {
        return datetimecr;
    }

    public void setDatetimecr(String datetimecr) {
        this.datetimecr = datetimecr;
    }

    public int getStatuscr() {
        return statuscr;
    }

    public void setStatuscr(int statuscr) {
        this.statuscr = statuscr;
    }
}
