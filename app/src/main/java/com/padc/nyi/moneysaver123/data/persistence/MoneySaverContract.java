package com.padc.nyi.moneysaver123.data.persistence;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.padc.nyi.moneysaver123.MoneySaverApp;

/**
 * Created by ZMTH on 9/25/2016.
 */
public class MoneySaverContract {

    public static final String CONTENT_AUTHORITY = MoneySaverApp.class.getPackage().getName();
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_EXPENSE = "expense";
    public static final String PATH_INCOME = "income";
    public static final String PATH_BILL = "bill";

    public static final class ExpenseEntry implements BaseColumns{

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_EXPENSE).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EXPENSE;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_EXPENSE;


        public static final String TABLE_NAME = "expense";
        public static final String COLUMN_EXPENSE_ID = "id";
        public static final String COLUMN_EXPENSE_TITLE = "title";
        public static final String COLUMN_EXPENSE_AMOUNT = "amount";
        public static final String COLUMN_EXPENSE_DATE = "date";
        public static final String COLUMN_EXPENSE_CATEGORY_ID = "category_id";
        public static final String COLUMN_EXPENSE_NOTE = "note";


        public static Uri buildExpenseUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildExpenseUriWithID(int id) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_EXPENSE_ID, id + "")
                    .build();
        }

        public static String getExpenseIDFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_EXPENSE_ID);
        }

        public static Uri buildExpenseUriWithTitle(String moneySaverTitle) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_EXPENSE_TITLE, moneySaverTitle)
                    .build();
        }

        public static String getExpenseTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_EXPENSE_TITLE);
        }

        public static Uri buildExpenseUriWithCatID(int catID) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_EXPENSE_CATEGORY_ID, catID + "")
                    .build();
        }
        public static String getCatIDFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_EXPENSE_CATEGORY_ID);
        }

        public static Uri buildExpenseUriWithDateDifference(int dummy) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_EXPENSE_DATE, dummy + "")
                    .build();
        }
        public static String getExpenseDateDifference(Uri uri) {
            return uri.getQueryParameter(COLUMN_EXPENSE_DATE);
        }
    }

    public static final class IncomeEntry implements BaseColumns{

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_INCOME).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_INCOME;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_INCOME;

        public static final String TABLE_NAME = "income";

        public static final String COLUMN_INCOME_TITLE = "title";
        public static final String COLUMN_INCOME_AMOUNT = "amount";
        public static final String COLUMN_INCOME_DATE = "date";
        public static final String COLUMN_INCOME_CATEGORY_ID = "category_id";
        public static final String COLUMN_INCOME_NOTE = "note";

        public static Uri buildIncomeUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildIncomeUriWithTitle(String moneySaverTitle) {

            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_INCOME_TITLE, moneySaverTitle)
                    .build();
        }

        public static String getIncomeTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_INCOME_TITLE);
        }


        public static Uri buildIncomeUriWithID(int id) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(_ID, id + "")
                    .build();
        }

        public static String getIncomeIDFromParam(Uri uri) {
            return uri.getQueryParameter(_ID);
        }
    }

    public static final class BillEntry implements BaseColumns{

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_BILL).build();

        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BILL;

        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_BILL;

        public static final String TABLE_NAME = "bill";

        public static final String COLUMN_BILL_TITLE = "title";
        public static final String COLUMN_BILL_REMIND_DATE = "reminddate";
        public static final String COLUMN_BILL_FINAL_DATE = "finaldate";
        public static final String COLUMN_BILL_AMOUNT = "amount";
        public static final String COLUMN_BILL_IMAGE_ID = "imageid";
        public static final String COLUMN_BILL_CAT_ID = "catid";
        public static final String COLUMN_BILL_IS_FINISH = "isfinish";


        public static Uri buildBillUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildBillUriWithTitle(String moneySaverTitle) {

            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(COLUMN_BILL_TITLE, moneySaverTitle)
                    .build();
        }

        public static String getBillTitleFromParam(Uri uri) {
            return uri.getQueryParameter(COLUMN_BILL_TITLE);
        }
        public static Uri buildBillUriWithID(int id) {
            return CONTENT_URI.buildUpon()
                    .appendQueryParameter(_ID, id + "")
                    .build();
        }

        public static String getBillIDFromParam(Uri uri) {
            return uri.getQueryParameter(_ID);
        }

    }
}
