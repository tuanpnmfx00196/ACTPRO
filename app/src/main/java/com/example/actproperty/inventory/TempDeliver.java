package com.example.actproperty.inventory;

import java.io.Serializable;

public class TempDeliver implements Serializable {
    private String storeCode;
    private int id, hanging6fo, hanging12fo, hanging24fo, odf6fo, odf12fo, odf24fo, closure6fo,
            closure12fo, closure24fo, bl300, bl400, clamp, sc_lc5, sc_lc10, flag;
    private String userDeliver, timeDeliver, commentDeliver;

    public TempDeliver(int id, String storeCode, int hanging6fo, int hanging12fo, int hanging24fo, int odf6fo, int odf12fo, int odf24fo, int closure6fo, int closure12fo, int closure24fo, int bl300, int bl400, int clamp, int sc_lc5, int sc_lc10, String userDeliver, String timeDeliver, String commentDeliver,int flag) {
        this.id = id;
        this.storeCode = storeCode;
        this.hanging6fo = hanging6fo;
        this.hanging12fo = hanging12fo;
        this.hanging24fo = hanging24fo;
        this.odf6fo = odf6fo;
        this.odf12fo = odf12fo;
        this.odf24fo = odf24fo;
        this.closure6fo = closure6fo;
        this.closure12fo = closure12fo;
        this.closure24fo = closure24fo;
        this.bl300 = bl300;
        this.bl400 = bl400;
        this.clamp = clamp;
        this.sc_lc5 = sc_lc5;
        this.sc_lc10 = sc_lc10;
        this.flag = flag;
        this.userDeliver = userDeliver;
        this.timeDeliver = timeDeliver;
        this.commentDeliver = commentDeliver;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
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

    public int getClosure6fo() {
        return closure6fo;
    }

    public void setClosure6fo(int closure6fo) {
        this.closure6fo = closure6fo;
    }

    public int getClosure12fo() {
        return closure12fo;
    }

    public void setClosure12fo(int closure12fo) {
        this.closure12fo = closure12fo;
    }

    public int getClosure24fo() {
        return closure24fo;
    }

    public void setClosure24fo(int closure24fo) {
        this.closure24fo = closure24fo;
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

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getUserDeliver() {
        return userDeliver;
    }

    public void setUserDeliver(String userDeliver) {
        this.userDeliver = userDeliver;
    }

    public String getTimeDeliver() {
        return timeDeliver;
    }

    public void setTimeDeliver(String timeDeliver) {
        this.timeDeliver = timeDeliver;
    }

    public String getCommentDeliver() {
        return commentDeliver;
    }

    public void setCommentDeliver(String commentDeliver) {
        this.commentDeliver = commentDeliver;
    }
}
