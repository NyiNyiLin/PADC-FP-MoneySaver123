package com.padc.nyi.moneysaver123.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.activities.AddIncomeActivity;
import com.padc.nyi.moneysaver123.adapters.IncomeListAdapter;
import com.padc.nyi.moneysaver123.data.vos.IncomeVOS;
import com.padc.nyi.moneysaver123.view.holder.IncomeViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZMTH on 9/5/2016.
 */
public class IncomeFragment extends Fragment implements View.OnClickListener, IncomeViewHolder.ControllerIncomeItem{

    @BindView(R.id.rv_income_list)
    RecyclerView rvIncomeList;

    @BindView(R.id.fab_add_income)
    FloatingActionButton fabAddIncome;

    IncomeListAdapter mIncomeListAdapter;
    List<IncomeVOS> mIncomeVOList = new ArrayList<>();


    public IncomeFragment() {
        mIncomeVOList.add(new IncomeVOS("လစဥ္ဝင္ေငြ", 300000, 3));
        mIncomeVOList.add(new IncomeVOS("စာသင္တဲ့ဝင္ေငြ", 50000, 2));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_income, container, false);

        ButterKnife.bind(this, view);

        fabAddIncome.setOnClickListener(this);
        mIncomeListAdapter = new IncomeListAdapter(mIncomeVOList, this);
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


    @Override
    public void onTapIncome(IncomeVOS incomeVOS) {
        openDetailDialogeBox(incomeVOS);
    }

    public void openDetailDialogeBox(IncomeVOS incomeVOS){
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View promptView = layoutInflater.inflate(R.layout.pop_up_detail_income, null);
        final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

        alert.setView(promptView);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        TextView tvTitle = (TextView) promptView.findViewById(R.id.tv_detail_income_title);
        TextView tvAmount = (TextView) promptView.findViewById(R.id.tv_detail_income_amount);
        TextView tvCat = (TextView) promptView.findViewById(R.id.tv_detail_income_cat);
        TextView tvDate = (TextView) promptView.findViewById(R.id.tv_detail_income_date);
        TextView tvNote = (TextView) promptView.findViewById(R.id.tv_detail_income_note);

        tvTitle.setText(incomeVOS.getTitle());
        tvAmount.setText(incomeVOS.getAmount() + "");
        tvCat.setText(incomeVOS.getCategory());
        tvDate.setText("24 Sept 2016");
        tvNote.setText("Just a Note");

        // create an alert dialog
        AlertDialog alert1 = alert.create();

        alert1.show();

    }
}
