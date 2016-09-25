package com.padc.nyi.moneysaver123.data.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ZMTH on 9/25/2016.
 */
public class MoneySaverDBHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "moneySaver.db";

    private static final String SQL_CREATE_EXPENSE_TABLE = "CREATE TABLE " + MoneySaverContract.ExpenseEntry.TABLE_NAME + " (" +
            MoneySaverContract.ExpenseEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MoneySaverContract.ExpenseEntry.COLUMN_EXPENSE_TITLE + " TEXT NOT NULL, " +
            MoneySaverContract.ExpenseEntry.COLUMN_EXPENSE_AMOUNT + " INTEGER, " +
            MoneySaverContract.ExpenseEntry.COLUMN_EXPENSE_DATE + " INTEGER, " +
            MoneySaverContract.ExpenseEntry.COLUMN_EXPENSE_CATEGORY_ID + " INTEGER, " +
            MoneySaverContract.ExpenseEntry.COLUMN_EXPENSE_NOTE + " TEXT " +
            " );";

    private static final String SQL_CREATE_INCOME_TABLE = "CREATE TABLE " + MoneySaverContract.IncomeEntry.TABLE_NAME + " (" +
            MoneySaverContract.IncomeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MoneySaverContract.IncomeEntry.COLUMN_INCOME_TITLE + " TEXT NOT NULL, " +
            MoneySaverContract.IncomeEntry.COLUMN_INCOME_AMOUNT + " TEXT, " +
            MoneySaverContract.IncomeEntry.COLUMN_INCOME_DATE + " TEXT, " +
            MoneySaverContract.IncomeEntry.COLUMN_INCOME_CATEGORY_ID + " TEXT, " +
            MoneySaverContract.IncomeEntry.COLUMN_INCOME_NOTE + " TEXT " +
            " );";

    private static final String SQL_CREATE_BILL_TABLE = "CREATE TABLE " + MoneySaverContract.BillEntry.TABLE_NAME + " (" +
            MoneySaverContract.BillEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MoneySaverContract.BillEntry.COLUMN_BILL_TITLE + " TEXT NOT NULL, " +
            MoneySaverContract.BillEntry.COLUMN_BILL_DATE + " TEXT, " +
            MoneySaverContract.BillEntry.COLUMN_BILL_AMOUNT + " TEXT " +
            " );";

    public MoneySaverDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_EXPENSE_TABLE);
        /*sqLiteDatabase.execSQL(SQL_CREATE_INCOME_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_BILL_TABLE);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MoneySaverContract.ExpenseEntry.TABLE_NAME);
       /* sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MoneySaverContract.IncomeEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MoneySaverContract.BillEntry.TABLE_NAME);*/

        onCreate(sqLiteDatabase);
    }

}
