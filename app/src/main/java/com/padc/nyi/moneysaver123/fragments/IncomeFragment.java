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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.padc.nyi.moneysaver123.MoneySaverApp;
import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.activities.AddIncomeActivity;
import com.padc.nyi.moneysaver123.adapters.IncomeListAdapter;
import com.padc.nyi.moneysaver123.data.persistence.MoneySaverContract;
import com.padc.nyi.moneysaver123.data.vos.IncomeVO;
import com.padc.nyi.moneysaver123.views.holders.IncomeViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZMTH on 9/5/2016.
 */
public class IncomeFragment extends Fragment implements View.OnClickListener, IncomeViewHolder.ControllerIncomeItem, LoaderManager.LoaderCallbacks<Cursor>{

    @BindView(R.id.rv_income_list)
    RecyclerView rvIncomeList;

    @BindView(R.id.fab_add_income)
    FloatingActionButton fabAddIncome;

    IncomeListAdapter mIncomeListAdapter;
    List<IncomeVO> mIncomeVOList = new ArrayList<>();


    public IncomeFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLoaderManager().initLoader(1,null,this);
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
    public void onTapIncome(IncomeVO incomeVO) {
        openDetailDialogeBox(incomeVO);
    }

    public void openDetailDialogeBox(IncomeVO incomeVO){
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View promptView = layoutInflater.inflate(R.layout.popup_detail_income, null);
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

        tvTitle.setText(incomeVO.getTitle());
        tvAmount.setText(incomeVO.getAmount() + "");
        //tvCat.setText(incomeVO.getCategory_id());
        tvDate.setText("24 Sept 2016");
        tvNote.setText(incomeVO.getNote());

        // create an alert dialog
        AlertDialog alert1 = alert.create();

        alert1.show();

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(MoneySaverApp.getContext(),
                MoneySaverContract.IncomeEntry.CONTENT_URI,
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<IncomeVO> incomeVOList = new ArrayList<>();
        if(data != null && data.moveToFirst()) {
            do {
                IncomeVO incomeVO = IncomeVO.parseToIncomeVO(data);
                incomeVOList.add(incomeVO);
            }while (data.moveToNext());
        }

        mIncomeListAdapter.addAllList(incomeVOList);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
