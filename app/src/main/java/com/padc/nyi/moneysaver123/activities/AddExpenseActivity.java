package com.padc.nyi.moneysaver123.activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.padc.nyi.moneysaver123.MoneySaverApp;
import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.data.models.MoneySaverModel;
import com.padc.nyi.moneysaver123.data.persistence.MoneySaverContract;
import com.padc.nyi.moneysaver123.data.persistence.MoneySaverProvider;
import com.padc.nyi.moneysaver123.data.vos.ExpenseVO;
import com.padc.nyi.moneysaver123.util.DateUtil;
import com.padc.nyi.moneysaver123.util.MoneySaverConstant;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddExpenseActivity extends AppCompatActivity implements  DatePickerDialog.OnDateSetListener, LoaderManager.LoaderCallbacks<Cursor>{
    private static String PARAM_ID = "id";

    public static final int NEWTYPE = -1;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.txt_date)
    TextView tvDate;

    @BindView(R.id.et_title)
    EditText etExpenseTitle;

    @BindView(R.id.et_amount)
    EditText etExpenseAmount;

    @BindView(R.id.spinner_category)
    Spinner spinnerCategory;

    @BindView(R.id.et_note)
    EditText etExpenseNote;

    @BindView(R.id.btn_expense_save)
    Button btnExpenseSave;

    @BindView(R.id.tv_screen_title)
    TextView tvScreenTitle;


    ExpenseVO expenseVO;

    long dateInNum;
    int catID;
    int _id = NEWTYPE;

    public static Intent newIntent(int id){
        Intent intent = new Intent(MoneySaverApp.getContext(), AddExpenseActivity.class);
        if(id >= 0)intent.putExtra(PARAM_ID, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        ButterKnife.bind(this, this);

        Intent intent = getIntent();
        _id = intent.getIntExtra(PARAM_ID, NEWTYPE);

        if (_id != NEWTYPE) {
            getSupportLoaderManager().initLoader(MoneySaverConstant.LoaderConstantexpenseEdit,null, this);
        }

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("သံုးလိုက္ေသာ ေငြထည့္ရန္");
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        tvScreenTitle.setText(getResources().getText(R.string.title_activity_add_expense));


        ArrayAdapter adapter = new ArrayAdapter(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, MoneySaverConstant.expenseCategory);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                catID = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinnerCategory.setAdapter(adapter);

        getCurrentDate();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                this.finish();
                break;
        }
        return true;
    }

    @OnClick(R.id.txt_date)
    public void onClickDate(View view){
        showThirdPartyDatePicker();
    }

    @OnClick(R.id.btn_expense_save)
    public void onClickSave(View view){
        expenseVO = new ExpenseVO();

        expenseVO.setTitle(etExpenseTitle.getText().toString());
        expenseVO.setAmount(Integer.parseInt(etExpenseAmount.getText().toString()));
        expenseVO.setCategory_id(catID);
        expenseVO.setDate(dateInNum);
        expenseVO.setNote(etExpenseNote.getText().toString());

        if(_id == NEWTYPE) MoneySaverModel.getInstance().saveExpense(expenseVO);
        else {
            expenseVO.setExpenseID(_id);
            Log.d("a", "as");
            MoneySaverModel.getInstance().updateExpense(expenseVO);
        }

        this.finish();
    }

    private void getCurrentDate(){
        Calendar now = Calendar.getInstance();

        try {
            dateInNum = DateUtil.channgeTimeToMilliTime(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
            tvDate.setText(DateUtil.changeMilliTimeToText(dateInNum));

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private void showThirdPartyDatePicker(){
        Calendar now = Calendar.getInstance();
        DatePickerDialog thirdPartyDatePicker = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );

        thirdPartyDatePicker.show(this.getFragmentManager(), "ThirdPartyDatePicker");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        try {
            dateInNum = DateUtil.channgeTimeToMilliTime(year, monthOfYear, dayOfMonth);
            tvDate.setText(DateUtil.changeMilliTimeToText(dateInNum));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setDataToEdit(ExpenseVO expenseVO){

        etExpenseTitle.setText(expenseVO.getTitle());
        etExpenseAmount.setText(expenseVO.getAmount() + "");
        spinnerCategory.setSelection(expenseVO.getCategory_id());
        etExpenseNote.setText(expenseVO.getNote());
        tvDate.setText(DateUtil.changeMilliTimeToText(expenseVO.getDate()));

        catID = expenseVO.getCategory_id();
        dateInNum = expenseVO.getDate();

        btnExpenseSave.setText("Update");
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(MoneySaverApp.getContext(),
                MoneySaverContract.ExpenseEntry.buildExpenseUriWithID(_id),
                null,
                null,
                null,
                MoneySaverContract.ExpenseEntry.COLUMN_EXPENSE_DATE + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        ExpenseVO expenseVO;
        if(data != null && data.moveToFirst()) {
            do {
                expenseVO = ExpenseVO.parseToExpenseVO(data);
                setDataToEdit(expenseVO);

            }while (data.moveToNext());
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
