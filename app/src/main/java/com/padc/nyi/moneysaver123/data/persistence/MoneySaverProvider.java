package com.padc.nyi.moneysaver123.data.persistence;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.padc.nyi.moneysaver123.MoneySaverApp;

/**
 * Created by ZMTH on 9/25/2016.
 */
public class MoneySaverProvider  extends ContentProvider {

    public static final int EXPENSE = 100;
    public static final int INCOME = 200;
    public static final int BILL = 300;

    public static final String sExpenseIDSelection = MoneySaverContract.ExpenseEntry.COLUMN_EXPENSE_ID + " = ?";
    public static final String sIncomeIDSelection = MoneySaverContract.IncomeEntry._ID + " = ?";
    public static final String sBillIDSelection = MoneySaverContract.BillEntry._ID + " = ?";
    private static final String sExpenseTitleSelection = MoneySaverContract.ExpenseEntry.COLUMN_EXPENSE_TITLE + " = ?";
    private static final String sExpenseCatIDSelection = MoneySaverContract.ExpenseEntry.COLUMN_EXPENSE_CATEGORY_ID + " = ?";
    private static final String sIncomeTitleSelection = MoneySaverContract.IncomeEntry.COLUMN_INCOME_TITLE + " = ?";

    private MoneySaverDBHelper mMoneySaverDBHelper;
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    @Override
    public boolean onCreate() {
        mMoneySaverDBHelper = new MoneySaverDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor queryCursor;

        int matchUri = sUriMatcher.match(uri);

        switch (matchUri) {
            case EXPENSE:
                String expenseID = MoneySaverContract.ExpenseEntry.getExpenseIDFromParam(uri);
                String attractionTitle = MoneySaverContract.ExpenseEntry.getExpenseTitleFromParam(uri);
                String catID = MoneySaverContract.ExpenseEntry.getCatIDFromParam(uri);
                String dummy = MoneySaverContract.ExpenseEntry.getExpenseDateDifference(uri);
                if (!TextUtils.isEmpty(expenseID)) {
                    selection = sExpenseIDSelection;
                    selectionArgs = new String[]{expenseID};
                }
                if (!TextUtils.isEmpty(attractionTitle)) {
                    selection = sExpenseTitleSelection;
                    selectionArgs = new String[]{attractionTitle};
                }
                if(!TextUtils.isEmpty(catID)){
                    selection = sExpenseCatIDSelection;
                    selectionArgs = new String[]{catID};
                }
                if(!TextUtils.isEmpty(dummy)){

                }
                queryCursor = mMoneySaverDBHelper.getReadableDatabase().query(MoneySaverContract.ExpenseEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null, //group_by
                        null, //having
                        sortOrder);
                break;
            case INCOME:
                String incomeID = MoneySaverContract.IncomeEntry.getIncomeIDFromParam(uri);
                String title = MoneySaverContract.IncomeEntry.getIncomeTitleFromParam(uri);
                if (title != null) {
                    selection = sIncomeTitleSelection;
                    selectionArgs = new String[]{title};
                }
                if (!TextUtils.isEmpty(incomeID)) {
                    selection = sIncomeIDSelection;
                    selectionArgs = new String[]{incomeID};
                }
                queryCursor = mMoneySaverDBHelper.getReadableDatabase().query(MoneySaverContract.IncomeEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;
            case BILL:
                queryCursor = mMoneySaverDBHelper.getReadableDatabase().query(MoneySaverContract.BillEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                break;

            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }

        Context context = getContext();
        if (context != null) {
            queryCursor.setNotificationUri(context.getContentResolver(), uri);
        }

        return queryCursor;


    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int matchUri = sUriMatcher.match(uri);

        switch (matchUri){
            case EXPENSE:
                return MoneySaverContract.ExpenseEntry.DIR_TYPE;
            case INCOME:
                return MoneySaverContract.IncomeEntry.DIR_TYPE;
            case BILL:
                return MoneySaverContract.BillEntry.DIR_TYPE;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {

        final SQLiteDatabase db = mMoneySaverDBHelper.getWritableDatabase();
        final int matchUri = sUriMatcher.match(uri);
        Uri insertedUri;

        switch (matchUri) {
            case EXPENSE: {
                long _id = db.insert(MoneySaverContract.ExpenseEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    insertedUri = MoneySaverContract.ExpenseEntry.buildExpenseUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            case INCOME: {
                long _id = db.insert(MoneySaverContract.IncomeEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    insertedUri = MoneySaverContract.IncomeEntry.buildIncomeUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            }
            case BILL: {
                long _id = db.insert(MoneySaverContract.BillEntry.TABLE_NAME, null, contentValues);
                if (_id > 0) {
                    insertedUri = MoneySaverContract.BillEntry.buildBillUri(_id);
                } else {
                    throw new SQLException("Failed to insert row into " + uri);
                }
                break;
            }

            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }

        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }

        return insertedUri;
    }

    private static UriMatcher buildUriMatcher() {
        final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(MoneySaverContract.CONTENT_AUTHORITY, MoneySaverContract.PATH_EXPENSE, EXPENSE);
        uriMatcher.addURI(MoneySaverContract.CONTENT_AUTHORITY, MoneySaverContract.PATH_INCOME, INCOME);
        uriMatcher.addURI(MoneySaverContract.CONTENT_AUTHORITY, MoneySaverContract.PATH_BILL, BILL);
        return uriMatcher;
    }

    private String getTableName(Uri uri) {
        final int matchUri = sUriMatcher.match(uri);

        switch (matchUri) {
            case EXPENSE:
                return MoneySaverContract.ExpenseEntry.TABLE_NAME;
            case INCOME:
                return MoneySaverContract.IncomeEntry.TABLE_NAME;
            case BILL:
                return MoneySaverContract.BillEntry.TABLE_NAME;
            default:
                throw new UnsupportedOperationException("Unknown uri : " + uri);
        }
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        final SQLiteDatabase db = mMoneySaverDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        int insertedCount = 0;

        try {
            db.beginTransaction();
            for (ContentValues cv : values) {
                long _id = db.insert(tableName, null, cv);
                if (_id > 0) {
                    insertedCount++;
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }

        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }

        return insertedCount;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mMoneySaverDBHelper.getWritableDatabase();
        int rowUpdated;
        String tableName = getTableName(uri);

        rowUpdated = db.delete(tableName, selection, selectionArgs);
        Context context = MoneySaverApp.getContext();
        if (context != null && rowUpdated > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        Log.d(MoneySaverApp.TAG, "deleted count " + rowUpdated);
        return rowUpdated;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {

        final SQLiteDatabase db = mMoneySaverDBHelper.getWritableDatabase();
        int rowUpdated;
        String tableName = getTableName(uri);

        rowUpdated = db.update(tableName, contentValues, selection, selectionArgs);
        Context context = MoneySaverApp.getContext();
        if (context != null && rowUpdated > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        Log.d(MoneySaverApp.TAG, "upadate count " + rowUpdated);
        return rowUpdated;
    }
}
