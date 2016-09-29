package com.padc.nyi.moneysaver123.data.vos;

/**
 * Created by IN-3442 on 28-Sep-16.
 */
public class ExpenseCatVO {
    private int catID;
    private String expenseCatTitle;
    private int total;
    private int amount;

    public ExpenseCatVO(int catID, String expenseCatTitle, int total, int amount) {
        this.catID = catID;
        this.expenseCatTitle = expenseCatTitle;
        this.total = total;
        this.amount = amount;
    }

    public ExpenseCatVO(String expenseCatTitle, int total, int amount) {
        this.expenseCatTitle = expenseCatTitle;
        this.total = total;
        this.amount = amount;
    }

    public ExpenseCatVO(int catID, int total) {
        this.catID = catID;
        this.total = total;
    }

    public String getExpenseCatTitle() {
        return expenseCatTitle;
    }

    public int getCatID() {
        return catID;
    }

    public int getTotal() {
        return total;
    }

    public int getAmount() {
        return amount;
    }
}
