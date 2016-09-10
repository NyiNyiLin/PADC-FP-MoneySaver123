package com.padc.nyi.moneysaver123.activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.fragments.BillFragment;
import com.padc.nyi.moneysaver123.fragments.ExpenseFragment;
import com.padc.nyi.moneysaver123.fragments.IncomeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this, this);

        setSupportActionBar(toolbar);

        if(savedInstanceState == null){
            navigateToExpense();
        }

        //Drawer Layout
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

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
            case R.id.nav_expense_category:
                navigateToExpenseCategory();
                return true;
            case R.id.nav_bill:
                navigateToBill();
                return true;
        }
        return false;
    }

    //call fragment
    private void navigateToExpense(){

        ExpenseFragment expenseFragment = new ExpenseFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_container, expenseFragment)
                .commit();
    }

    private void navigateToIncome(){

        IncomeFragment incomeFragment = new IncomeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_container, incomeFragment)
                .commit();
    }

    private void navigateToExpenseCategory(){

        ExpenseFragment expenseFragment = new ExpenseFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_container, expenseFragment)
                .commit();
    }

    private void navigateToBill(){

        BillFragment billFragment = new BillFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_container, billFragment)
                .commit();
    }
}
