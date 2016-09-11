package com.padc.nyi.moneysaver123.fragments;

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
import com.padc.nyi.moneysaver123.activities.AddIncomeActivity;
import com.padc.nyi.moneysaver123.adapters.IncomeListAdapter;
import com.padc.nyi.moneysaver123.data.vos.IncomeVOS;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZMTH on 9/5/2016.
 */
public class IncomeFragment extends Fragment implements View.OnClickListener{

    @BindView(R.id.rv_income_list)
    RecyclerView rvIncomeList;

    @BindView(R.id.fab_add_income)
    FloatingActionButton fabAddIncome;

    IncomeListAdapter mIncomeListAdapter;
    List<IncomeVOS> mIncomeVOList = new ArrayList<>();


    public IncomeFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIncomeVOList.add(new IncomeVOS("AAA", 120, 1));
        mIncomeVOList.add(new IncomeVOS("BBB", 120, 1));
        mIncomeVOList.add(new IncomeVOS("CCC", 120, 1));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_income, container, false);

        ButterKnife.bind(this, view);
        fabAddIncome.setOnClickListener(this);

        mIncomeListAdapter = new IncomeListAdapter(mIncomeVOList);
        rvIncomeList.setAdapter(mIncomeListAdapter);

        int gridColumnSpanCount = 1;
        rvIncomeList.setLayoutManager(new GridLayoutManager(getContext(), gridColumnSpanCount));

        return view;
    }

    @Override
    public void onClick(View view) {
        Intent intent = AddIncomeActivity.newIntent();
        startActivity(intent);
    }
}
