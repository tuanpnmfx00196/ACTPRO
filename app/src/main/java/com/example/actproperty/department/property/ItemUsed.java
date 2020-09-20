package com.example.actproperty.department.property;

import java.io.Serializable;

public class ItemUsed implements Serializable {
    private int id, oldid;
    private String cableid, province, codecr;
    private int hanging4fo, hanging6fo, hanging12fo, hanging24fo, du12fo, odf6fo, odf12fo, odf24fo,
            mx6fo, mx12fo, mx24fo, bl300, bl400, clamp, poleu8, ironpole6, sc_lc5, sc_lc10, sc_sc5;
    private String datechange, comment, userchange;

    public ItemUsed(int id, int oldid, String cableid, String province, String codecr, int hanging4fo, int hanging6fo, int hanging12fo, int hanging24fo, int du12fo, int odf6fo, int odf12fo, int odf24fo, int mx6fo, int mx12fo, int mx24fo, int bl300, int bl400, int clamp, int poleu8, int ironpole6, int sc_lc5, int sc_lc10, int sc_sc5, String datechange, String comment, String userchange) {
        this.id = id;
        this.oldid = oldid;
        this.cableid = cableid;
        this.province = province;
        this.codecr = codecr;
        this.hanging4fo = hanging4fo;
        this.hanging6fo = hanging6fo;
        this.hanging12fo = hanging12fo;
        this.hanging24fo = hanging24fo;
        this.du12fo = du12fo;
        this.odf6fo = odf6fo;
        this.odf12fo = odf12fo;
        this.odf24fo = odf24fo;
        this.mx6fo = mx6fo;
        this.mx12fo = mx12fo;
        this.mx24fo = mx24fo;
        this.bl300 = bl300;
        this.bl400 = bl400;
        this.clamp = clamp;
        this.poleu8 = poleu8;
        this.ironpole6 = ironpole6;
        this.sc_lc5 = sc_lc5;
        this.sc_lc10 = sc_lc10;
        this.sc_sc5 = sc_sc5;
        this.datechange = datechange;
        this.comment = comment;
        this.userchange = userchange;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOldid() {
        return oldid;
    }

    public void setOldid(int oldid) {
        this.oldid = oldid;
    }

    public String getCableid() {
        return cableid;
    }

    public void setCableid(String cableid) {
        this.cableid = cableid;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCodecr() {
        return codecr;
    }

    public void setCodecr(String codecr) {
        this.codecr = codecr;
    }

    public int getHanging4fo() {
        return hanging4fo;
    }

    public void setHanging4fo(int hanging4fo) {
        this.hanging4fo = hanging4fo;
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

    public int getDu12fo() {
        return du12fo;
    }

    public void setDu12fo(int du12fo) {
        this.du12fo = du12fo;
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

    public int getPoleu8() {
        return poleu8;
    }

    public void setPoleu8(int poleu8) {
        this.poleu8 = poleu8;
    }

    public int getIronpole6() {
        return ironpole6;
    }

    public void setIronpole6(int ironpole6) {
        this.ironpole6 = ironpole6;
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

    public int getSc_sc5() {
        return sc_sc5;
    }

    public void setSc_sc5(int sc_sc5) {
        this.sc_sc5 = sc_sc5;
    }

    public String getDatechange() {
        return datechange;
    }

    public void setDatechange(String datechange) {
        this.datechange = datechange;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserchange() {
        return userchange;
    }

    public void setUserchange(String userchange) {
        this.userchange = userchange;
    }
}
