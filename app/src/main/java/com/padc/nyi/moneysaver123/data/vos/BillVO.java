package com.padc.nyi.moneysaver123.data.vos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.padc.nyi.moneysaver123.MoneySaverApp;
import com.padc.nyi.moneysaver123.data.persistence.MoneySaverContract;

/**
 * Created by ZMTH on 9/25/2016.
 */
public class BillVO {

    String title;
    int date;
    int amount;

    public BillVO() {

    }

    public BillVO(String title, int date, int amount) {
        this.title = title;
        this.date = date;
        this.amount = amount;
    }

    private ContentValues parseToContentValues() {
        ContentValues cv = new ContentValues();

        cv.put(MoneySaverContract.BillEntry.COLUMN_BILL_TITLE, title);
        cv.put(MoneySaverContract.BillEntry.COLUMN_BILL_AMOUNT, amount);
        cv.put(MoneySaverContract.BillEntry.COLUMN_BILL_DATE, date);
        return cv;
    }

    public static BillVO parseToBillVO(Cursor data){
        BillVO billVO = new BillVO();
        billVO.setTitle(data.getString(data.getColumnIndex(MoneySaverContract.BillEntry.COLUMN_BILL_TITLE)));
        billVO.setAmount(data.getInt(data.getColumnIndex(MoneySaverContract.BillEntry.COLUMN_BILL_AMOUNT)));
        billVO.setDate(data.getInt(data.getColumnIndex(MoneySaverContract.BillEntry.COLUMN_BILL_DATE)));

        return billVO;
    }

    public static void saveReminderForBill(BillVO billVO) {
        Context context = MoneySaverApp.getContext();
        Uri insertedUri = context.getContentResolver().insert(MoneySaverContract.BillEntry.CONTENT_URI, billVO.parseToContentValues());
        Log.d(MoneySaverApp.TAG, "Successfully inserted into bill table : " + insertedUri);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
