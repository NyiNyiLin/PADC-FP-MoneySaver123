package com.padc.nyi.moneysaver123.data.vos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.padc.nyi.moneysaver123.MoneySaverApp;
import com.padc.nyi.moneysaver123.data.persistence.MoneySaverContract;
import com.padc.nyi.moneysaver123.data.persistence.MoneySaverProvider;

/**
 * Created by ZMTH on 9/25/2016.
 */
public class BillVO {

    int billID;
    String title;
    long remindDate;
    long finalDate;
    int imageID;
    int amount;
    int isFinish;
    int catID = 9;

    String remindDateText;
    String finalDateText;

    public BillVO() {

    }

    public BillVO(String title, int fnalDate, int amount) {
        this.title = title;
        this.finalDate = fnalDate;
        this.amount = amount;

    }

    public void setRemindDateText(String remindDateText) {
        this.remindDateText = remindDateText;
    }

    public void setFinalDateText(String finalDateText) {
        this.finalDateText = finalDateText;
    }

    public String getRemindDateText() {
        return remindDateText;
    }

    public String getFinalDateText() {
        return finalDateText;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public void setRemindDate(long remindDate) {
        this.remindDate = remindDate;
    }

    public void setFinalDate(long finalDate) {
        this.finalDate = finalDate;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public void setIsFinish(int isFinish) {
        this.isFinish = isFinish;
    }

    public void setCatID(int catID) {
        this.catID = catID;
    }

    public long getRemindDate() {
        return remindDate;
    }

    public long getFinalDate() {
        return finalDate;
    }

    public int getImageID() {
        return imageID;
    }

    public int getIsFinish() {
        return isFinish;
    }

    public int getCatID() {
        return catID;
    }

    private ContentValues parseToContentValues() {
        ContentValues cv = new ContentValues();

        cv.put(MoneySaverContract.BillEntry.COLUMN_BILL_TITLE, title);
        cv.put(MoneySaverContract.BillEntry.COLUMN_BILL_REMIND_DATE, remindDate);
        cv.put(MoneySaverContract.BillEntry.COLUMN_BILL_FINAL_DATE, finalDate);
        cv.put(MoneySaverContract.BillEntry.COLUMN_BILL_IMAGE_ID, imageID);
        cv.put(MoneySaverContract.BillEntry.COLUMN_BILL_AMOUNT, amount);
        cv.put(MoneySaverContract.BillEntry.COLUMN_BILL_CAT_ID, catID);
        cv.put(MoneySaverContract.BillEntry.COLUMN_BILL_IS_FINISH, isFinish);

        return cv;
    }

    public static BillVO parseToBillVO(Cursor data){
        BillVO billVO = new BillVO();
        billVO.setBillID(data.getInt(data.getColumnIndex(MoneySaverContract.BillEntry._ID)));
        billVO.setTitle(data.getString(data.getColumnIndex(MoneySaverContract.BillEntry.COLUMN_BILL_TITLE)));
        billVO.setRemindDate(data.getLong(data.getColumnIndex(MoneySaverContract.BillEntry.COLUMN_BILL_REMIND_DATE)));
        billVO.setFinalDate(data.getLong(data.getColumnIndex(MoneySaverContract.BillEntry.COLUMN_BILL_FINAL_DATE)));
        billVO.setAmount(data.getInt(data.getColumnIndex(MoneySaverContract.BillEntry.COLUMN_BILL_AMOUNT)));
        billVO.setImageID(data.getInt(data.getColumnIndex(MoneySaverContract.BillEntry.COLUMN_BILL_IMAGE_ID)));
        billVO.setCatID(data.getInt(data.getColumnIndex(MoneySaverContract.BillEntry.COLUMN_BILL_CAT_ID)));
        billVO.setIsFinish(data.getInt(data.getColumnIndex(MoneySaverContract.BillEntry.COLUMN_BILL_IS_FINISH)));

        return billVO;
    }

    public static int saveReminderForBill(BillVO billVO) {
        Context context = MoneySaverApp.getContext();
        Uri insertedUri = context.getContentResolver().insert(MoneySaverContract.BillEntry.CONTENT_URI, billVO.parseToContentValues());
        String insertedString = insertedUri.toString();
        String billInsertedID = insertedString.substring(42);
        Log.d(MoneySaverApp.TAG, "Successfully inserted into bill table : " + insertedUri);
        return Integer.parseInt(billInsertedID);
    }

    public static void updateBill(BillVO billVO) {
        Context context = MoneySaverApp.getContext();
        context.getContentResolver().update(MoneySaverContract.BillEntry.CONTENT_URI, billVO.parseToContentValues(), MoneySaverProvider.sBillIDSelection, new String[]{billVO.getBillID()+""});
    }

    public static void deleteBill(int id) {
        Context context = MoneySaverApp.getContext();
        context.getContentResolver().delete(MoneySaverContract.BillEntry.CONTENT_URI, MoneySaverProvider.sBillIDSelection, new String[]{id+""});
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
