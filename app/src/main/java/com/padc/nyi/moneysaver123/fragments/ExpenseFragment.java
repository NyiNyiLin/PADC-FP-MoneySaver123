package com.padc.nyi.moneysaver123.fragments;

import android.app.ActionBar;
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
import android.widget.Toast;

import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.activities.AddExpenseActivity;
import com.padc.nyi.moneysaver123.adapters.ExpenseListAdapter;
import com.padc.nyi.moneysaver123.data.vos.ExpenseVO;
import com.padc.nyi.moneysaver123.views.holders.ExpenseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZMTH on 9/5/2016.
 */
public class ExpenseFragment extends Fragment implements View.OnClickListener, ExpenseViewHolder.ControllerExpenseItem{
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

        mExpenseListAdapter = new ExpenseListAdapter(mExpenseVOList, this);
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

    @Override
    public void onTapExpense(ExpenseVO expenseVO) {
        openDetailDialogeBox(expenseVO);
    }

    public void openDetailDialogeBox(ExpenseVO expenseVO){
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View promptView = layoutInflater.inflate(R.layout.popup_detail_expense, null);
        final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

        alert.setView(promptView);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        TextView tvTitle = (TextView) promptView.findViewById(R.id.tv_detail_expense_title);
        TextView tvAmount = (TextView) promptView.findViewById(R.id.tv_detail_expense_amount);
        TextView tvCat = (TextView) promptView.findViewById(R.id.tv_detail_expense_cat);
        TextView tvDate = (TextView) promptView.findViewById(R.id.tv_detail_expense_date);
        TextView tvNote = (TextView) promptView.findViewById(R.id.tv_detail_expense_note);

        tvTitle.setText(expenseVO.getTitle());
        tvAmount.setText(expenseVO.getAmount() + "");
        tvCat.setText(expenseVO.getCategory());
        tvDate.setText("24 Sept 2016");
        tvNote.setText("Just a Note");

        // create an alert dialog
        AlertDialog alert1 = alert.create();

        alert1.show();

    }
}
