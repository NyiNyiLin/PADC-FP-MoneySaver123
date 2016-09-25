package com.padc.nyi.moneysaver123.data.vos;

/**
 * Created by ZMTH on 9/25/2016.
 */
public class BillVO {

    String title;
    String date;
    int amount;

    public BillVO() {

    }

    public BillVO(String title, String date, int amount) {
        this.title = title;
        this.date = date;
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }

}
