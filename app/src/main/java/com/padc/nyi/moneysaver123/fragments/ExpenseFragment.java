package com.padc.nyi.moneysaver123.fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.padc.nyi.moneysaver123.MoneySaverApp;
import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.activities.AddExpenseActivity;
import com.padc.nyi.moneysaver123.adapters.ExpenseListAdapter;
import com.padc.nyi.moneysaver123.data.persistence.MoneySaverContract;
import com.padc.nyi.moneysaver123.data.vos.ExpenseVO;
import com.padc.nyi.moneysaver123.views.holders.ExpenseViewHolder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZMTH on 9/5/2016.
 */
public class ExpenseFragment extends Fragment implements View.OnClickListener, ExpenseViewHolder.ControllerExpenseItem, LoaderManager.LoaderCallbacks<Cursor>{
    @BindView(R.id.rv_expense_list)
    RecyclerView rvExpenseList;

    @BindView(R.id.fab_add_expense)
    FloatingActionButton fabAddExpense;

    ExpenseListAdapter mExpenseListAdapter;
    List<ExpenseVO> mExpenseVOList = new ArrayList<>();

    public ExpenseFragment() {
        /*mExpenseVOList.add(new ExpenseVO("ရံုးသြား", 300, 2));
        mExpenseVOList.add(new ExpenseVO("ေန႕လည္စာ", 2500, 0));*/
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mExpenseVOList.add(new ExpenseVO("a", 200, 1, 1, "asd"));
        mExpenseVOList.add(new ExpenseVO("aa", 200, 1, 1, "asd"));
        mExpenseVOList.add(new ExpenseVO("aaaa", 200, 1, 1, "asd"));
        mExpenseVOList.add(new ExpenseVO("b", 200, 2, 2, "asd"));
        mExpenseVOList.add(new ExpenseVO("bb", 200, 2, 2, "asd"));
        mExpenseVOList.add(new ExpenseVO("c", 200, 3, 3, "asd"));
        mExpenseVOList.add(new ExpenseVO("d", 200, 4, 4, "asd"));

        getLoaderManager().initLoader(1,null,this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expense, container, false);
        ButterKnife.bind(this, view);


        fabAddExpense.setOnClickListener(this);

        mExpenseListAdapter = new ExpenseListAdapter(addHeadertoList(mExpenseVOList), this);
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
        tvDate.setText(changeMiliTimetoDate(expenseVO.getDate()));
        tvNote.setText(expenseVO.getNote());

        // create an alert dialog
        AlertDialog alert1 = alert.create();

        alert1.show();

    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(MoneySaverApp.getContext(),
                MoneySaverContract.ExpenseEntry.CONTENT_URI,
                null,
                null,
                null,
                MoneySaverContract.ExpenseEntry.COLUMN_EXPENSE_DATE + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<ExpenseVO> expenseVOList = new ArrayList<>();
        if(data != null && data.moveToFirst()) {
            do {
                ExpenseVO expenseVO = ExpenseVO.parseToExpenseVO(data);
                expenseVOList.add(expenseVO);
            }while (data.moveToNext());
        }

        mExpenseListAdapter.addAllList(addHeadertoList(expenseVOList));
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private List<ExpenseVO> addHeadertoList(List<ExpenseVO> expenseVOList){

        if(expenseVOList.size() != 0) {
            long currentDateInMilli = expenseVOList.get(0).getDate();
            String currentDate = changeMiliTimetoDate(currentDateInMilli);
            int insertedPosition = 0;
            int totalAmount = 0;

            for (int a = 0; a < expenseVOList.size(); a++) {
                long anotherDateInMilli = expenseVOList.get(a).getDate();
                String anotherDate = changeMiliTimetoDate(anotherDateInMilli);
                if (currentDate.compareTo(anotherDate) != 0) {

                    expenseVOList.add(insertedPosition, new ExpenseVO("zxy", (0 - totalAmount), currentDate, 1, ""));
                    insertedPosition = a+1;
                    currentDate = anotherDate;
                    totalAmount = 0;
                }else{
                    totalAmount = totalAmount + expenseVOList.get(a).getAmount();
                }
            }
            expenseVOList.add(insertedPosition, new ExpenseVO("zxy", (0 - totalAmount), currentDate, 1, ""));

        }
        return expenseVOList;
    }

    private String changeMiliTimetoDate(Long miliTime){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(miliTime);

        Date date1 = calendar.getTime();
        Log.d(MoneySaverApp.TAG, date1.toString());
        DateFormat dateFormatter = new SimpleDateFormat("dd/MMM/yyyy");

        return dateFormatter.format(date1).toString();
    }
}
