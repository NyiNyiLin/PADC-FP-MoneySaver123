package com.padc.nyi.moneysaver123.fragments;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.padc.nyi.moneysaver123.MoneySaverApp;
import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.activities.ExpenseCatIDActivity;
import com.padc.nyi.moneysaver123.adapters.ExpenseCatListAdapter;
import com.padc.nyi.moneysaver123.data.persistence.MoneySaverContract;
import com.padc.nyi.moneysaver123.data.vos.ExpenseCatVO;
import com.padc.nyi.moneysaver123.data.vos.ExpenseVO;
import com.padc.nyi.moneysaver123.util.DateUtil;
import com.padc.nyi.moneysaver123.util.MoneySaverConstant;
import com.padc.nyi.moneysaver123.views.holders.ExpenseCatViewHolder;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * A simple {@link Fragment} subclass.
 * to handle interaction events.
 * Use the {@link ExpenseCatFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExpenseCatFragment extends Fragment implements ExpenseCatViewHolder.ControllerExpenseCatItem, LoaderManager.LoaderCallbacks<Cursor>{

    @BindView(R.id.rv_expense_cat_list)
    RecyclerView rvExpenseCatList;

    ExpenseCatListAdapter adapter;

    List<ExpenseCatVO> mExpenseCatList = new ArrayList<>();

    public ExpenseCatFragment() {
        // Required empty public constructor
    }

    public static ExpenseCatFragment newInstance() {
        ExpenseCatFragment fragment = new ExpenseCatFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLoaderManager().initLoader(MoneySaverConstant.LoaderConstantExpenseCat,null,this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expense_cat, container, false);
        ButterKnife.bind(this, view);

        adapter = new ExpenseCatListAdapter(mExpenseCatList, this);
        rvExpenseCatList.setAdapter(adapter);

        int gridColumnSpanCount = 1;
        rvExpenseCatList.setLayoutManager(new GridLayoutManager(getContext(), gridColumnSpanCount));

        return view;
    }

    @Override
    public void onTapExpense(ExpenseCatVO expenseCatVO) {
        startActivity(ExpenseCatIDActivity.newIntent(expenseCatVO.getCatID()));

    }



    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new CursorLoader(MoneySaverApp.getContext(),
                MoneySaverContract.ExpenseEntry.CONTENT_URI,
                null,
                null,
                null,
                null);
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

/*
        int total = 0;
        for(int a=0; a<expenseVOList.size(); a++){
            total = total + expenseVOList.get(a).getAmount();
        }
        List<ExpenseCatVO> expenseCatVOList = new ArrayList<>();
        expenseCatVOList.add(new ExpenseCatVO("aa", 100090, total));

        adapter.addList(expenseCatVOList);*/

        List<ExpenseVO> expenseVOList0 = new ArrayList<>();
        List<ExpenseVO> expenseVOList1 = new ArrayList<>();
        List<ExpenseVO> expenseVOList2 = new ArrayList<>();
        List<ExpenseVO> expenseVOList3 = new ArrayList<>();
        List<ExpenseVO> expenseVOList4 = new ArrayList<>();
        List<ExpenseVO> expenseVOList5 = new ArrayList<>();
        List<ExpenseVO> expenseVOList6 = new ArrayList<>();
        List<ExpenseVO> expenseVOList7 = new ArrayList<>();
        List<ExpenseVO> expenseVOList8 = new ArrayList<>();
        List<ExpenseVO> expenseVOList9 = new ArrayList<>();

        for (int a=0; a<expenseVOList.size(); a++){
            ExpenseVO expenseVO = expenseVOList.get(a);

            if(expenseVO.getCategory_id() == 0) expenseVOList0.add(expenseVO);
            else if(expenseVO.getCategory_id() == 1) expenseVOList1.add(expenseVO);
            else if(expenseVO.getCategory_id() == 2) expenseVOList2.add(expenseVO);
            else if(expenseVO.getCategory_id() == 3) expenseVOList3.add(expenseVO);
            else if(expenseVO.getCategory_id() == 4) expenseVOList4.add(expenseVO);
            else if(expenseVO.getCategory_id() == 5) expenseVOList5.add(expenseVO);
            else if(expenseVO.getCategory_id() == 6) expenseVOList6.add(expenseVO);
            else if(expenseVO.getCategory_id() == 7) expenseVOList7.add(expenseVO);
            else if(expenseVO.getCategory_id() == 8) expenseVOList8.add(expenseVO);
            else if(expenseVO.getCategory_id() == 9) expenseVOList9.add(expenseVO);
        }
        int mainTotal = 0;
        int total0 = 0;
        int total1 =0;
        int total2 =0;
        int total3 =0;
        int total4 =0;
        int total5 =0;
        int total6 =0;
        int total7 =0;
        int total8 =0;
        int total9 =0;
        if(expenseVOList0.size() != 0){
            for(int a= 0; a<expenseVOList0.size(); a++){
                total0 = total0 + expenseVOList0.get(a).getAmount();
                mainTotal +=  expenseVOList0.get(a).getAmount();
            }
        }

        if(expenseVOList1.size() != 0){
            for(int a= 0; a<expenseVOList1.size(); a++){
                total1 = total1 + expenseVOList1.get(a).getAmount();
                mainTotal +=  expenseVOList1.get(a).getAmount();
            }
        }

        if(expenseVOList2.size() != 0){
            for(int a= 0; a<expenseVOList2.size(); a++){
                total2 = total2 + expenseVOList2.get(a).getAmount();
                mainTotal +=  expenseVOList2.get(a).getAmount();
            }
        }
        if(expenseVOList3.size() != 0){
            for(int a= 0; a<expenseVOList3.size(); a++){
                total3 = total3 + expenseVOList3.get(a).getAmount();
                mainTotal +=  expenseVOList3.get(a).getAmount();
            }
        }
        if(expenseVOList4.size() != 0){
            for(int a= 0; a<expenseVOList4.size(); a++){
                total4 = total4 + expenseVOList4.get(a).getAmount();
                mainTotal +=  expenseVOList4.get(a).getAmount();
            }
        }
        if(expenseVOList5.size() != 0){
            for(int a= 0; a<expenseVOList5.size(); a++){
                total5 = total5 + expenseVOList5.get(a).getAmount();
                mainTotal +=  expenseVOList5.get(a).getAmount();
            }
        }
        if(expenseVOList6.size() != 0){
            for(int a= 0; a<expenseVOList6.size(); a++){
                total6 = total6 + expenseVOList6.get(a).getAmount();
                mainTotal +=  expenseVOList6.get(a).getAmount();
            }
        }
        if(expenseVOList7.size() != 0){
            for(int a= 0; a<expenseVOList7.size(); a++){
                total7 = total7 + expenseVOList7.get(a).getAmount();
                mainTotal +=  expenseVOList7.get(a).getAmount();
            }
        }
        if(expenseVOList8.size() != 0){
            for(int a= 0; a<expenseVOList8.size(); a++){
                total8 = total8 + expenseVOList8.get(a).getAmount();
                mainTotal +=  expenseVOList8.get(a).getAmount();
            }
        }
        if(expenseVOList9.size() != 0){
            for(int a= 0; a<expenseVOList9.size(); a++){
                total9 = total9 + expenseVOList9.get(a).getAmount();
                mainTotal +=  expenseVOList9.get(a).getAmount();
            }
        }

        List<ExpenseCatVO> expenseCatVOList = new ArrayList<>();
        expenseCatVOList.add(new ExpenseCatVO(0, MoneySaverConstant.expenseCategory[0], mainTotal, total0));
        expenseCatVOList.add(new ExpenseCatVO(1, MoneySaverConstant.expenseCategory[1], mainTotal, total1));
        expenseCatVOList.add(new ExpenseCatVO(2, MoneySaverConstant.expenseCategory[2], mainTotal, total2));
        expenseCatVOList.add(new ExpenseCatVO(3, MoneySaverConstant.expenseCategory[3], mainTotal, total3));
        expenseCatVOList.add(new ExpenseCatVO(4, MoneySaverConstant.expenseCategory[4], mainTotal, total4));
        expenseCatVOList.add(new ExpenseCatVO(5, MoneySaverConstant.expenseCategory[5], mainTotal, total5));
        expenseCatVOList.add(new ExpenseCatVO(6, MoneySaverConstant.expenseCategory[6], mainTotal, total6));
        expenseCatVOList.add(new ExpenseCatVO(7, MoneySaverConstant.expenseCategory[7], mainTotal, total7));
        expenseCatVOList.add(new ExpenseCatVO(8, MoneySaverConstant.expenseCategory[8], mainTotal, total8));
        expenseCatVOList.add(new ExpenseCatVO(9, MoneySaverConstant.expenseCategory[9], mainTotal, total9));

        adapter.addList(expenseCatVOList);

    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

/*    new CursorLoader(MoneySaverApp.getContext(),
    MoneySaverContract.ExpenseEntry.CONTENT_URI,
            null,
    MoneySaverContract.ExpenseEntry.COLUMN_EXPENSE_DATE + " BETWEEN ? AND ?", new String[] {datteInNumStart + "", datteInNumEnd + ""},
    MoneySaverContract.ExpenseEntry.COLUMN_EXPENSE_DATE + " DESC");*/

}
