package com.padc.nyi.moneysaver123.fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.padc.nyi.moneysaver123.MoneySaverApp;
import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.activities.AddExpenseActivity;
import com.padc.nyi.moneysaver123.adapters.ExpenseListAdapter;
import com.padc.nyi.moneysaver123.data.persistence.MoneySaverContract;
import com.padc.nyi.moneysaver123.data.vos.ExpenseVO;
import com.padc.nyi.moneysaver123.util.DateUtil;
import com.padc.nyi.moneysaver123.util.MoneySaverConstant;
import com.padc.nyi.moneysaver123.views.holders.ExpenseViewHolder;

import java.util.ArrayList;
import java.util.List;
import android.net.Uri;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZMTH on 9/5/2016.
 */
public class ExpenseDateFragment extends BaseFragment implements View.OnClickListener, ExpenseViewHolder.ControllerExpenseItem, LoaderManager.LoaderCallbacks<Cursor>{
    @BindView(R.id.rv_expense_list)
    RecyclerView rvExpenseList;

    @BindView(R.id.fab_add_expense)
    FloatingActionButton fabAddExpense;

    @BindView(R.id.iv_expense_empty_image)
    ImageView ivExpenseEmptyImage;

    private static final String PARAM_CAT_ID = "ID";

    ExpenseListAdapter mExpenseListAdapter;
    List<ExpenseVO> mExpenseVOList = new ArrayList<>();

    private Uri uri;

    public ExpenseDateFragment() {
        /*mExpenseVOList.add(new ExpenseVO("ရံုးသြား", 300, 2));
        mExpenseVOList.add(new ExpenseVO("ေန႕လည္စာ", 2500, 0));*/
    }

    public static ExpenseDateFragment newInstance(int catId) {
        ExpenseDateFragment fragment = new ExpenseDateFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(PARAM_CAT_ID, catId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        int catID = bundle.getInt(PARAM_CAT_ID);
        if(catID < 0) uri = MoneySaverContract.ExpenseEntry.CONTENT_URI;
        else uri = MoneySaverContract.ExpenseEntry.buildExpenseUriWithCatID(catID);


        getLoaderManager().initLoader(MoneySaverConstant.LoaderConstantExpense,null,this);
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
        Intent intent = AddExpenseActivity.newIntent(AddExpenseActivity.NEWTYPE);
        startActivity(intent);
    }

    @Override
    public void onTapExpense(ExpenseVO expenseVO) {
        openDetailDialogeBox(expenseVO);
    }

    @Override
    public void onLongPressExpense(ExpenseVO expenseVO, View itemView) {
        showMenu(itemView, expenseVO.getExpenseID());
    }

    @Override
    protected void onTapItemEdit(int id) {
        super.onTapItemEdit(id);
        Intent intent = AddExpenseActivity.newIntent(id);
        startActivity(intent);
    }

    @Override
    protected void onTapItemDelete(int id) {
        super.onTapItemDelete(id);
        ExpenseVO.deleteExpense(id);
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
        tvDate.setText(DateUtil.changeMilliTimeToText(expenseVO.getDate()));
        tvNote.setText(expenseVO.getNote());

        // create an alert dialog
        AlertDialog alert1 = alert.create();

        alert1.show();

    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(MoneySaverApp.getContext(),
                uri,
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
        if(expenseVOList.size() > 0) ivExpenseEmptyImage.setVisibility(View.INVISIBLE);
        mExpenseListAdapter.addAllList(addHeadertoList(expenseVOList));
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private List<ExpenseVO> addHeadertoList(List<ExpenseVO> expenseVOList){

        if(expenseVOList.size() != 0) {
            long currentDateInMilli = expenseVOList.get(0).getDate();
            String currentDate = DateUtil.changeMilliTimeToText(currentDateInMilli);
            int insertedPosition = 0;
            int totalAmount = 0;

            for (int a = 0; a < expenseVOList.size(); a++) {
                long anotherDateInMilli = expenseVOList.get(a).getDate();
                String anotherDate = DateUtil.changeMilliTimeToText(anotherDateInMilli);
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



}
