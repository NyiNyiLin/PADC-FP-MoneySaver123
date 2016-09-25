package com.padc.nyi.moneysaver123.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.padc.nyi.moneysaver123.MoneySaverApp;
import com.padc.nyi.moneysaver123.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ZMTH on 9/24/2016.
 */
public class AddBillActivity extends AppCompatActivity  implements  DatePickerDialog.OnDateSetListener{

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.txt_date)
    TextView tvDate;

    SimpleDateFormat dateFormatter;

    public static Intent newIntent(){
        Intent intent = new Intent(MoneySaverApp.getContext(), AddBillActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill);

        ButterKnife.bind(this, this);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

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
    private void getCurrentDate(){
        Calendar now = Calendar.getInstance();

        try {
            dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateInString = now.get(Calendar.DAY_OF_MONTH) + "/" + (now.get(Calendar.MONTH) + 1) + "/" + now.get(Calendar.YEAR);
            Date date = dateFormatter.parse(dateInString);
            dateFormatter = new SimpleDateFormat("dd/MMM/yyyy");
            tvDate.setText(tvDate.getText().toString() + dateFormatter.format(date).toString());

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
            dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateInString = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
            Date date = dateFormatter.parse(dateInString);
            dateFormatter = new SimpleDateFormat("dd/MMM/yyyy");
            tvDate.setText(dateFormatter.format(date).toString());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
