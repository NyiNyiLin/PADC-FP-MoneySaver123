package com.padc.nyi.moneysaver123.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.padc.nyi.moneysaver123.MoneySaverApp;
import com.padc.nyi.moneysaver123.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZMTH on 9/10/2016.
 */
public class AddIncomeActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.spinner_category)
    Spinner spinnerCategory;

    @BindView(R.id.tv_date)
    TextView textViewDate;

    SimpleDateFormat dateFormatter;

    /*static factory method*/
    public static Intent newIntent(){
        Intent intent = new Intent(MoneySaverApp.getContext(), AddIncomeActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        final String []dummyCategory={"Internet", "PhoneBill", "Clothing", "Medical", "Food", "Entertainment"};
        ArrayAdapter adapter = new ArrayAdapter(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, dummyCategory);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

        getCurrentDate();

        //ThirdPartyDatePicker
        textViewDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    showThirdPartyDatePicker();
                }
            }
        });

        textViewDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showThirdPartyDatePicker();
            }
        });

       //back to income fragment
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    private void getCurrentDate(){
        Calendar now = Calendar.getInstance();

        try {
            dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateInString = now.get(Calendar.DAY_OF_MONTH) + "/" + (now.get(Calendar.MONTH) + 1) + "/" + now.get(Calendar.YEAR);
            Date date = dateFormatter.parse(dateInString);
            dateFormatter = new SimpleDateFormat("dd/MMM/yyyy");
            textViewDate.setText(textViewDate.getText().toString() + dateFormatter.format(date).toString());

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        try {
            dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateInString = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
            Date date = dateFormatter.parse(dateInString);
            dateFormatter = new SimpleDateFormat("dd/MMM/yyyy");
            textViewDate.setText(dateFormatter.format(date).toString());

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
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this,HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
