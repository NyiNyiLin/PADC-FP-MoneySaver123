package com.padc.nyi.moneysaver123.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.padc.nyi.moneysaver123.MoneySaverApp;
import com.padc.nyi.moneysaver123.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZMTH on 9/10/2016.
 */
public class AddIncomeActivity extends AppCompatActivity{
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.spinner_category)
    Spinner spinnerCategory;

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

        final String []dummyCategory={"aaa", "bbb"};
        ArrayAdapter adapter = new ArrayAdapter(getBaseContext(), R.layout.support_simple_spinner_dropdown_item, dummyCategory);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

    }
}
