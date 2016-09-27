package com.padc.nyi.moneysaver123.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
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
import com.padc.nyi.moneysaver123.data.vos.ExpenseVO;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddExpenseActivity extends AppCompatActivity implements  DatePickerDialog.OnDateSetListener{
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

    SimpleDateFormat dateFormatter;

    ExpenseVO expenseVO;

    long dateInNum;
    int catID;

    public static Intent newIntent(){
        Intent intent = new Intent(MoneySaverApp.getContext(), AddExpenseActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("သံုးလိုက္ေသာ ေငြထည့္ရန္");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        final String []dummyCategory={"အစားအေသာက္", "အဝတ္အစား", "လမ္းစရိတ္", "ေဖ်ာ္ေျဖေရး", "ကားအသံုးစရိတ္", "အေထြေထြ"};
        ArrayAdapter adapter = new ArrayAdapter(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, dummyCategory);
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

        btnExpenseSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isEmptyUserInputData()){
                    expenseVO = new ExpenseVO();

                    expenseVO.setTitle(etExpenseTitle.getText().toString());
                    expenseVO.setAmount(Integer.parseInt(etExpenseAmount.getText().toString()));
                    //expenseVO.setDate(tvDate.getText());
                    expenseVO.setNote(etExpenseNote.getText().toString());

                    MoneySaverModel.getInstance().saveExpense(expenseVO);

                    clearExpenseUserInputData();
                    successfullySaveDataDialogBox();
                  }
            }
        });
    }

    public void successfullySaveDataDialogBox(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddExpenseActivity.this);
        alertDialog.setMessage("Successfully save data.");
        alertDialog.setPositiveButton("CLOSE", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {

                expenseVO.setTitle(etExpenseTitle.getText().toString());
                expenseVO.setAmount(Integer.parseInt(etExpenseAmount.getText().toString()));
                expenseVO.setCategory_id(catID);
                expenseVO.setDate(dateInNum);
                expenseVO.setNote(etExpenseNote.getText().toString());

                MoneySaverModel.getInstance().saveExpense(expenseVO);

            }
        });
        alertDialog.show();
    }

    public void unsuccessfullySaveDataDialogBox(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddExpenseActivity.this);
        alertDialog.setMessage("Please fill require fields.");
        alertDialog.setPositiveButton("CLOSE", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {

            }
        });
        alertDialog.show();
    }

    //clear user input data
    private  void clearExpenseUserInputData(){
        expenseVO = new ExpenseVO();
        etExpenseTitle.getText().clear();
        etExpenseAmount.getText().clear();
        etExpenseNote.getText().clear();
    }

    //check validation
    private boolean isEmptyUserInputData(){
        if(TextUtils.isEmpty(etExpenseTitle.getText().toString())||
        TextUtils.isEmpty(etExpenseAmount.getText().toString())||
        TextUtils.isEmpty(etExpenseNote.getText().toString())){
            unsuccessfullySaveDataDialogBox();
            return false;
        }
        return true;
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

    private void getCurrentDate(){
        Calendar now = Calendar.getInstance();

        try {

            tvDate.setText(channgeTimeToMilliTime(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH)));

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
            /*dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateInString = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
            Date date = dateFormatter.parse(dateInString);
            dateFormatter = new SimpleDateFormat("dd/MMM/yyyy");
            tvDate.setText(dateFormatter.format(date).toString());*/


            tvDate.setText(channgeTimeToMilliTime(year, monthOfYear, dayOfMonth));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String channgeTimeToMilliTime(int year, int monthOfYear, int dayOfMonth){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth);

        dateInNum = calendar.getTimeInMillis();
        Log.d(MoneySaverApp.TAG, "Milli selected date" + dateInNum);

        calendar.setTimeInMillis(dateInNum);
        Log.d(MoneySaverApp.TAG, "Milli calendar date" + calendar.getTimeInMillis());


        Date date1 = calendar.getTime();
        Log.d(MoneySaverApp.TAG, date1.toString());
        dateFormatter = new SimpleDateFormat("dd/MMM/yyyy");

        return dateFormatter.format(date1).toString();
    }
}
