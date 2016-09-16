package com.padc.nyi.moneysaver123.activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.padc.nyi.moneysaver123.MoneySaverApp;
import com.padc.nyi.moneysaver123.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddExpenseActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.spinner_category)
    Spinner spinnerCategory;

    @BindView(R.id.txt_date)
    TextView txtDate;

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
        spinnerCategory.setAdapter(adapter);


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

    }

}
