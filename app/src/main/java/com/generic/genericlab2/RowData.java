package com.generic.genericlab2;

public class RowData {
    public String cityName;
    private int uid;
    private static int nextUid = 0;

    public RowData(String cityName) {
        uid = nextUid;
        this.cityName = cityName;
        ++nextUid ;
    }

    public int getId() {
        return this.uid;
    }
}
