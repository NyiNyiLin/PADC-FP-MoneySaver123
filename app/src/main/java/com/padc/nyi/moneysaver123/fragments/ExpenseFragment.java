package com.padc.nyi.moneysaver123.fragments;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.activities.AddExpenseActivity;
import com.padc.nyi.moneysaver123.adapters.ExpenseListAdapter;
import com.padc.nyi.moneysaver123.data.vos.ExpenseVO;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZMTH on 9/5/2016.
 */
public class ExpenseFragment extends Fragment implements View.OnClickListener{
    @BindView(R.id.rv_expense_list)
    RecyclerView rvExpenseList;

    @BindView(R.id.fab_add_expense)
    FloatingActionButton fabAddExpense;

    ExpenseListAdapter mExpenseListAdapter;
    List<ExpenseVO> mExpenseVOList = new ArrayList<>();

    public ExpenseFragment() {
        mExpenseVOList.add(new ExpenseVO("ရံုးသြား", 300, 2));
        mExpenseVOList.add(new ExpenseVO("ေန႕လည္စာ", 2500, 0));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expense, container, false);
        ButterKnife.bind(this, view);


        fabAddExpense.setOnClickListener(this);

        mExpenseListAdapter = new ExpenseListAdapter(mExpenseVOList);
        rvExpenseList.setAdapter(mExpenseListAdapter);

        int gridColumnSpanCount = 1;
        rvExpenseList.setLayoutManager(new GridLayoutManager(getContext(), gridColumnSpanCount));
        return view;
    }


    @Override
    public void onClick(View view) {
        Intent intent = AddExpenseActivity.newIntent();
        startActivity(intent);
    }
}
