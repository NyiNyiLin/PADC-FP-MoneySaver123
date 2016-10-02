package com.padc.nyi.moneysaver123.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.padc.nyi.moneysaver123.MoneySaverApp;
import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.data.vos.BillVO;
import com.padc.nyi.moneysaver123.fragments.BillFragment;
import com.padc.nyi.moneysaver123.fragments.ExpenseMainFragment;
import com.padc.nyi.moneysaver123.fragments.IncomeFragment;
import com.padc.nyi.moneysaver123.fragments.IncomeMainFrgment;
import com.padc.nyi.moneysaver123.receiver.BillAlarm;
import com.padc.nyi.moneysaver123.receiver.DailylAlarm;
import com.padc.nyi.moneysaver123.util.AlarmiUtil;
import com.padc.nyi.moneysaver123.util.DateUtil;
import com.padc.nyi.moneysaver123.util.MMFontUtils;
import com.padc.nyi.moneysaver123.util.MoneySaverConstant;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tv_screen_title)
    TextView tvScreenTitle;

    ActionBar actionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayShowTitleEnabled(false);
        }


        if(savedInstanceState == null){
            navigateToExpense();
        }

        Menu leftMenu = navigationView.getMenu();
        MMFontUtils.applyMMFontToMenu(leftMenu);

        //Drawer Layout
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        createDailyNoti();
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_home, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        drawer.closeDrawer(GravityCompat.START);

        switch (item.getItemId()) {
            case R.id.nav_expense:
                navigateToExpense();
                return true;
            case R.id.nav_income:
                navigateToIncome();
                return true;
            case R.id.nav_bill:
                navigateToBill();
                return true;
        }
        return false;
    }

    //call fragment
    private void navigateToExpense(){
        tvScreenTitle.setText("အသံုးစရိတ္စာရင္း");

        Fragment expenseFragment = ExpenseMainFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_container, expenseFragment)
                .commit();
    }

    private void navigateToIncome(){
        tvScreenTitle.setText("ဝင္ေငြစာရင္း");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_container, IncomeMainFrgment.newInstance())
                .commit();
    }


    private void navigateToBill(){
        tvScreenTitle.setText("ေပးေဆာင္ရမည့္ေဘစာရင္း");
        BillFragment billFragment = new BillFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_container, billFragment)
                .commit();
    }

    private void createDailyNoti(){
        Intent intent = new Intent(MoneySaverApp.getContext(), DailylAlarm.class);
        AlarmiUtil.setRepeatingAlarm(MoneySaverApp.getContext(), intent);
    }
}
