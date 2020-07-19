package com.example.actproperty.department.accounting;

import java.io.Serializable;

public class DeliverObject implements Serializable {
    private int id;
    private String codeStore;
    private int hanging6fo, hanging12fo, hanging24fo, odf6fo, odf12fo, odf24fo, mx6fo,mx12fo, mx24fo,
                bl300, bl400, clamp, sc_lc5, sc_lc10;
    private String userdeliver, timedeliver, commentdeliver;
    private int flag;

    public DeliverObject(int id, String codeStore, int hanging6fo, int hanging12fo, int hanging24fo, int odf6fo, int odf12fo, int odf24fo, int mx6fo, int mx12fo, int mx24fo, int bl300, int bl400, int clamp, int sc_lc5, int sc_lc10, String userdeliver, String timedeliver, String commentdeliver, int flag) {
        this.id = id;
        this.codeStore = codeStore;
        this.hanging6fo = hanging6fo;
        this.hanging12fo = hanging12fo;
        this.hanging24fo = hanging24fo;
        this.odf6fo = odf6fo;
        this.odf12fo = odf12fo;
        this.odf24fo = odf24fo;
        this.mx6fo = mx6fo;
        this.mx12fo = mx12fo;
        this.mx24fo = mx24fo;
        this.bl300 = bl300;
        this.bl400 = bl400;
        this.clamp = clamp;
        this.sc_lc5 = sc_lc5;
        this.sc_lc10 = sc_lc10;
        this.userdeliver = userdeliver;
        this.timedeliver = timedeliver;
        this.commentdeliver = commentdeliver;
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeStore() {
        return codeStore;
    }

    public void setCodeStore(String codeStore) {
        this.codeStore = codeStore;
    }

    public int getHanging6fo() {
        return hanging6fo;
    }

    public void setHanging6fo(int hanging6fo) {
        this.hanging6fo = hanging6fo;
    }

    public int getHanging12fo() {
        return hanging12fo;
    }

    public void setHanging12fo(int hanging12fo) {
        this.hanging12fo = hanging12fo;
    }

    public int getHanging24fo() {
        return hanging24fo;
    }

    public void setHanging24fo(int hanging24fo) {
        this.hanging24fo = hanging24fo;
    }

    public int getOdf6fo() {
        return odf6fo;
    }

    public void setOdf6fo(int odf6fo) {
        this.odf6fo = odf6fo;
    }

    public int getOdf12fo() {
        return odf12fo;
    }

    public void setOdf12fo(int odf12fo) {
        this.odf12fo = odf12fo;
    }

    public int getOdf24fo() {
        return odf24fo;
    }

    public void setOdf24fo(int odf24fo) {
        this.odf24fo = odf24fo;
    }

    public int getMx6fo() {
        return mx6fo;
    }

    public void setMx6fo(int mx6fo) {
        this.mx6fo = mx6fo;
    }

    public int getMx12fo() {
        return mx12fo;
    }

    public void setMx12fo(int mx12fo) {
        this.mx12fo = mx12fo;
    }

    public int getMx24fo() {
        return mx24fo;
    }

    public void setMx24fo(int mx24fo) {
        this.mx24fo = mx24fo;
    }

    public int getBl300() {
        return bl300;
    }

    public void setBl300(int bl300) {
        this.bl300 = bl300;
    }

    public int getBl400() {
        return bl400;
    }

    public void setBl400(int bl400) {
        this.bl400 = bl400;
    }

    public int getClamp() {
        return clamp;
    }

    public void setClamp(int clamp) {
        this.clamp = clamp;
    }

    public int getSc_lc5() {
        return sc_lc5;
    }

    public void setSc_lc5(int sc_lc5) {
        this.sc_lc5 = sc_lc5;
    }

    public int getSc_lc10() {
        return sc_lc10;
    }

    public void setSc_lc10(int sc_lc10) {
        this.sc_lc10 = sc_lc10;
    }

    public String getUserdeliver() {
        return userdeliver;
    }

    public void setUserdeliver(String userdeliver) {
        this.userdeliver = userdeliver;
    }

    public String getTimedeliver() {
        return timedeliver;
    }

    public void setTimedeliver(String timedeliver) {
        this.timedeliver = timedeliver;
    }

    public String getCommentdeliver() {
        return commentdeliver;
    }

    public void setCommentdeliver(String commentdeliver) {
        this.commentdeliver = commentdeliver;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
