package com.example.actproperty;

public class CableId {
    private int id;
    private String province;
    private String cableId;

    public CableId(int id, String province, String cableId) {
        this.id = id;
        this.province = province;
        this.cableId = cableId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCableId() {
        return cableId;
    }

    public void setCableId(String cableId) {
        this.cableId = cableId;
    }
}
