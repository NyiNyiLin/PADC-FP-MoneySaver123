package com.padc.nyi.moneysaver123.data.vos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.padc.nyi.moneysaver123.MoneySaverApp;
import com.padc.nyi.moneysaver123.data.persistence.MoneySaverContract;

/**
 * Created by ZMTH on 9/11/2016.
 */
public class IncomeVO {

    String title;
    int amount;
    int date;
    int category_id;
    String note;

    public IncomeVO() {
    }

    public IncomeVO(String title, int amount, int date, int category_id, String note) {
        this.title = title;
        this.amount = amount;
        this.date = date;
        this.category_id = category_id;
        this.note = note;
    }

    public IncomeVO(String title, int amount, int category_id) {
        this.title = title;
        this.amount = amount;
        this.category_id = category_id;
    }

    private ContentValues parseToContentValues() {
        ContentValues cv = new ContentValues();

        cv.put(MoneySaverContract.IncomeEntry.COLUMN_INCOME_TITLE, title);
        cv.put(MoneySaverContract.IncomeEntry.COLUMN_INCOME_AMOUNT, amount);
        cv.put(MoneySaverContract.IncomeEntry.COLUMN_INCOME_DATE, date);
        cv.put(MoneySaverContract.IncomeEntry.COLUMN_INCOME_CATEGORY_ID, category_id);
        cv.put(MoneySaverContract.IncomeEntry.COLUMN_INCOME_NOTE, note);
        return cv;
    }

    public static IncomeVO parseToIncomeVO(Cursor data){
        IncomeVO incomeVO = new IncomeVO();

        incomeVO.setTitle(data.getString(data.getColumnIndex(MoneySaverContract.IncomeEntry.COLUMN_INCOME_TITLE)));
        incomeVO.setAmount(data.getInt(data.getColumnIndex(MoneySaverContract.IncomeEntry.COLUMN_INCOME_AMOUNT)));
        incomeVO.setDate(data.getInt(data.getColumnIndex(MoneySaverContract.IncomeEntry.COLUMN_INCOME_DATE)));
        incomeVO.setDate(data.getInt(data.getColumnIndex(MoneySaverContract.IncomeEntry.COLUMN_INCOME_CATEGORY_ID)));
        incomeVO.setNote(data.getString(data.getColumnIndex(MoneySaverContract.IncomeEntry.COLUMN_INCOME_NOTE)));

        return incomeVO;
    }

    public static void saveIncome(IncomeVO incomeVO) {
        Context context = MoneySaverApp.getContext();
        Uri insertedUri = context.getContentResolver().insert(MoneySaverContract.IncomeEntry.CONTENT_URI, incomeVO.parseToContentValues());
        Log.d(MoneySaverApp.TAG, "Successfully inserted into income table : " + insertedUri);
    }

    public String getTitle() {
        return title;
    }

    public int getAmount() {
        return amount;
    }

    public int getDate() {
        return date;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getNote() {
        return note;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
