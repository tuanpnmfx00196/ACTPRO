package com.example.actproperty.history;

import com.example.actproperty.CableId;

public class History extends CableId {
    public History(int id, String province, String cableId, int hanging4fo, int hanging6fo, int hanging12fo, int hanging24fo, int du12fo, int odf6fo, int odf12fo, int odf24fo, int odf96fo, int closure6fo, int closure12fo, int closure24fo, int buloong300, int buloong400, int clamp, int poleu8, int ironpole6, int sc_lc5, int sc_lc10, int sc_sc5) {
        super(id, province, cableId, hanging4fo, hanging6fo, hanging12fo, hanging24fo, du12fo, odf6fo, odf12fo, odf24fo, odf96fo, closure6fo, closure12fo, closure24fo, buloong300, buloong400, clamp, poleu8, ironpole6, sc_lc5, sc_lc10, sc_sc5);
    }
    private int oldid;
    private String datechange, comment, userchange;

    public History(int id, String province, String cableId, int hanging4fo, int hanging6fo, int hanging12fo, int hanging24fo, int du12fo, int odf6fo, int odf12fo, int odf24fo, int odf96fo, int closure6fo, int closure12fo, int closure24fo, int buloong300, int buloong400, int clamp, int poleu8, int ironpole6, int sc_lc5, int sc_lc10, int sc_sc5, int oldid, String datechange, String comment, String userchange) {
        super(id, province, cableId, hanging4fo, hanging6fo, hanging12fo, hanging24fo, du12fo, odf6fo, odf12fo, odf24fo, odf96fo, closure6fo, closure12fo, closure24fo, buloong300, buloong400, clamp, poleu8, ironpole6, sc_lc5, sc_lc10, sc_sc5);
        this.oldid = oldid;
        this.datechange = datechange;
        this.comment = comment;
        this.userchange = userchange;
    }

    public int getOldid() {
        return oldid;
    }

    public void setOldid(int oldid) {
        this.oldid = oldid;
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
