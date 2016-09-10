package com.padc.nyi.moneysaver123.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.activities.AddIncomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZMTH on 9/5/2016.
 */
public class IncomeFragment extends Fragment implements View.OnClickListener{

    @BindView(R.id.rv_income_list)
    RecyclerView rvExpenseList;

    @BindView(R.id.fab_add_income)
    FloatingActionButton fabAddExpense;

    public IncomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_income, container, false);

        ButterKnife.bind(this, view);
        fabAddExpense.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        Intent intent = AddIncomeActivity.newIntent();
        startActivity(intent);
    }
}
