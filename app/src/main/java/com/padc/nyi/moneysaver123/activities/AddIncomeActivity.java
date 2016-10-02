package com.padc.nyi.moneysaver123.activities;

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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.padc.nyi.moneysaver123.MoneySaverApp;
import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.data.models.MoneySaverModel;
import com.padc.nyi.moneysaver123.data.persistence.MoneySaverContract;
import com.padc.nyi.moneysaver123.data.vos.ExpenseVO;
import com.padc.nyi.moneysaver123.data.vos.IncomeVO;
import com.padc.nyi.moneysaver123.util.DateUtil;
import com.padc.nyi.moneysaver123.util.MoneySaverConstant;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ZMTH on 9/10/2016.
 */
public class AddIncomeActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, LoaderManager.LoaderCallbacks<Cursor>{

    private static String PARAM_ID = "id";

    public static final int NEWTYPE = -1;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.et_title)
    EditText etIncomeTitle;

    @BindView(R.id.et_amount)
    EditText etIncomeAmount;

    @BindView(R.id.spinner_category)
    Spinner spinnerIncomeCategory;

    @BindView(R.id.et_note)
    EditText etIncomeNote;

    @BindView(R.id.txt_date)
    TextView tvDate;

    @BindView(R.id.btn_income_save)
    TextView btnIncomeSave;

    @BindView(R.id.tv_screen_title)
    TextView tvScreenTitle;

    SimpleDateFormat dateFormatter;
    IncomeVO incomeVO;

    int catID;
    long dateInNum;
    int _id = NEWTYPE;

    /*static factory method*/
    public static Intent newIntent(int id){
        Intent intent = new Intent(MoneySaverApp.getContext(), AddIncomeActivity.class);
        if(id >= 0)intent.putExtra(PARAM_ID, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        ButterKnife.bind(this, this);

        Intent intent = getIntent();
        _id = intent.getIntExtra(PARAM_ID, NEWTYPE);

        if (_id != NEWTYPE) {
            getSupportLoaderManager().initLoader(MoneySaverConstant.LoaderConstantIncomeEdit,null, this);
        }

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("ဝင္ေငြထည့္ရန္");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        tvScreenTitle.setText(getResources().getText(R.string.title_activity_add_income));

        final String dummyIncomeCat[] = {"လစာ", "ေရာင္းရေငြ", "ေခ်းေငြ"};
        ArrayAdapter adapter = new ArrayAdapter(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, dummyIncomeCat);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerIncomeCategory.setAdapter(adapter);

       spinnerIncomeCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               catID = i;
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });

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

    @OnClick(R.id.btn_income_save)
    public void onClickSave(View view){
        incomeVO = new IncomeVO();

        incomeVO.setTitle(etIncomeTitle.getText().toString());
        incomeVO.setAmount(Integer.parseInt(etIncomeAmount.getText().toString()));
        incomeVO.setCategory_id(catID);
        incomeVO.setDate(dateInNum);
        incomeVO.setNote(etIncomeNote.getText().toString());

        if(_id == NEWTYPE) MoneySaverModel.getInstance().saveIncome(incomeVO);
        else {
            incomeVO.setId(_id);
            IncomeVO.updateIncome(incomeVO);
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
    private  void showThirdPartyDatePicker(){
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

    private void setDataToEdit(IncomeVO incomeVO){

        etIncomeTitle.setText(incomeVO.getTitle());
        etIncomeAmount.setText(incomeVO.getAmount() + "");
        spinnerIncomeCategory.setSelection(incomeVO.getCategory_id());
        etIncomeNote.setText(incomeVO.getNote());
        tvDate.setText(DateUtil.changeMilliTimeToText(incomeVO.getDate()));

        catID = incomeVO.getCategory_id();
        dateInNum = incomeVO.getDate();

        btnIncomeSave.setText("Update");
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(MoneySaverApp.getContext(),
                MoneySaverContract.IncomeEntry.buildIncomeUriWithID(_id),
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        IncomeVO incomeVO;
        if(data != null && data.moveToFirst()) {
            do {
                incomeVO = IncomeVO.parseToIncomeVO(data);
                setDataToEdit(incomeVO);

            }while (data.moveToNext());
        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
