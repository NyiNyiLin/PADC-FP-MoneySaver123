package com.padc.nyi.moneysaver123.data.vos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import com.padc.nyi.moneysaver123.MoneySaverApp;
import com.padc.nyi.moneysaver123.data.persistence.MoneySaverContract;
import com.padc.nyi.moneysaver123.data.persistence.MoneySaverProvider;
import com.padc.nyi.moneysaver123.util.MoneySaverConstant;

/**
 * Created by IN-3442 on 11-Sep-16.
 */
public class ExpenseVO{

    int expenseID;
    String title;
    int amount;
    long date;
    String textDate;
    int category_id;
    String note;

    public ExpenseVO() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public ExpenseVO(String title, int amount, long date, int category_id, String note) {
        this.title = title;
        this.amount = amount;
        this.date = date;
        this.category_id = category_id;
        this.note = note;
    }
    public ExpenseVO(String title, int amount, String textDate, int category_id, String note) {
        this.title = title;
        this.amount = amount;
        this.textDate = textDate;
        this.category_id = category_id;
        this.note = note;
    }

    public ExpenseVO(String title, int amount, int category_id) {
        this.title = title;
        this.amount = amount;
        this.category_id = category_id;
    }

    private ContentValues parseToContentValues() {
        ContentValues cv = new ContentValues();

        cv.put(MoneySaverContract.ExpenseEntry.COLUMN_EXPENSE_TITLE, title);
        cv.put(MoneySaverContract.ExpenseEntry.COLUMN_EXPENSE_AMOUNT, amount);
        cv.put(MoneySaverContract.ExpenseEntry.COLUMN_EXPENSE_DATE, date);
        cv.put(MoneySaverContract.ExpenseEntry.COLUMN_EXPENSE_CATEGORY_ID, category_id);
        cv.put(MoneySaverContract.ExpenseEntry.COLUMN_EXPENSE_NOTE, note);
        return cv;
    }

    public static ExpenseVO parseToExpenseVO(Cursor data){
        ExpenseVO expenseVO = new ExpenseVO();

        expenseVO.setExpenseID(data.getInt(data.getColumnIndex(MoneySaverContract.ExpenseEntry.COLUMN_EXPENSE_ID)));
        expenseVO.setTitle(data.getString(data.getColumnIndex(MoneySaverContract.ExpenseEntry.COLUMN_EXPENSE_TITLE)));
        expenseVO.setAmount(data.getInt(data.getColumnIndex(MoneySaverContract.ExpenseEntry.COLUMN_EXPENSE_AMOUNT)));
        expenseVO.setDate(data.getLong(data.getColumnIndex(MoneySaverContract.ExpenseEntry.COLUMN_EXPENSE_DATE)));
        expenseVO.setCategory_id(data.getInt(data.getColumnIndex(MoneySaverContract.ExpenseEntry.COLUMN_EXPENSE_CATEGORY_ID)));
        expenseVO.setNote(data.getString(data.getColumnIndex(MoneySaverContract.ExpenseEntry.COLUMN_EXPENSE_NOTE)));

        return expenseVO;
    }

    public static void saveExpense(ExpenseVO expenseVO) {
        Context context = MoneySaverApp.getContext();
        Uri insertedUri = context.getContentResolver().insert(MoneySaverContract.ExpenseEntry.CONTENT_URI, expenseVO.parseToContentValues());
        Log.d(MoneySaverApp.TAG, "Successfully inserted into expense table : " + insertedUri);
    }

    public static void updateExpense(ExpenseVO expenseVO) {
        Context context = MoneySaverApp.getContext();
        context.getContentResolver().update(MoneySaverContract.ExpenseEntry.CONTENT_URI, expenseVO.parseToContentValues(), MoneySaverProvider.sExpenseIDSelection, new String[]{expenseVO.getExpenseID()+""});
    }

    public static void deleteExpense(int id) {
        Context context = MoneySaverApp.getContext();
        context.getContentResolver().delete(MoneySaverContract.ExpenseEntry.CONTENT_URI, MoneySaverProvider.sExpenseIDSelection, new String[]{id+""});
    }

    public int getExpenseID() {
        return expenseID;
    }

    public void setExpenseID(int expenseID) {
        this.expenseID = expenseID;
    }

    public String getTextDate() {
        return textDate;
    }

    public void setTextDate(String textDate) {
        this.textDate = textDate;
    }

    public String getTitle() {
        return title;
    }

    public int getAmount() {
        return amount;
    }

    public long getDate() {
        return date;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getNote() {
        return note;
    }

    //This method is only for dummy data
    //Category item may be extracted from database
    public String getCategory(){
        return MoneySaverConstant.expenseCategory[category_id];
    }
}
