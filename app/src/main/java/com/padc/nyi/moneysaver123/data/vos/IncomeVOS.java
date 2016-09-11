package com.padc.nyi.moneysaver123.data.vos;

/**
 * Created by ZMTH on 9/11/2016.
 */
public class IncomeVOS {

    String title;
    int amount;
    String date;
    int category_id;
    String note;

    public IncomeVOS() {
    }

    public IncomeVOS(String title, int amount, String date, int category_id, String note) {
        this.title = title;
        this.amount = amount;
        this.date = date;
        this.category_id = category_id;
        this.note = note;
    }

    public IncomeVOS(String title, int amount, int category_id) {
        this.title = title;
        this.amount = amount;
        this.category_id = category_id;
    }

    public String getTitle() {
        return title;
    }

    public int getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getNote() {
        return note;
    }
}
