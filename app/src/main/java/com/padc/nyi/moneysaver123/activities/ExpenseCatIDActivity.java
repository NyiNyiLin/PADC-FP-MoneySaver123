package com.padc.nyi.moneysaver123.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.padc.nyi.moneysaver123.MoneySaverApp;
import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.fragments.ExpenseDateFragment;
import com.padc.nyi.moneysaver123.util.MoneySaverConstant;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExpenseCatIDActivity extends AppCompatActivity {

    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;

    @BindView(R.id.tv_screen_title)
    TextView tvScreenTitle;

    private static final String paramID = "ID";

    public static Intent newIntent(int id){
        Intent intent = new Intent(MoneySaverApp.getContext(), ExpenseCatIDActivity.class);
        intent.putExtra(paramID, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_cat_id);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        int id = intent.getIntExtra(paramID, 0);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setTitle(MoneySaverConstant.expenseCategory[id]);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        tvScreenTitle.setText(MoneySaverConstant.expenseCategory[id]);



        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, ExpenseDateFragment.newInstance(id))
                .commit();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

