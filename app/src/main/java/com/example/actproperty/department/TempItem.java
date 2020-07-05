package com.example.actproperty.department;

public class TempItem {
    private String nameItem;
    private int valueItem;

    public TempItem(String nameItem, int valueItem) {
        this.nameItem = nameItem;
        this.valueItem = valueItem;
    }

    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

    public int getValueItem() {
        return valueItem;
    }

    public void setValueItem(int valueItem) {
        this.valueItem = valueItem;
    }
}
