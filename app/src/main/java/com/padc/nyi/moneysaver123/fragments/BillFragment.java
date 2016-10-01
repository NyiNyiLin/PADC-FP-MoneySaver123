package com.padc.nyi.moneysaver123.fragments;

import android.content.Context;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.padc.nyi.moneysaver123.MoneySaverApp;
import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.activities.AddBillActivity;
import com.padc.nyi.moneysaver123.adapters.BillListAdapter;
import com.padc.nyi.moneysaver123.data.models.MoneySaverModel;
import com.padc.nyi.moneysaver123.data.persistence.MoneySaverContract;
import com.padc.nyi.moneysaver123.data.vos.BillVO;
import com.padc.nyi.moneysaver123.data.vos.ExpenseVO;
import com.padc.nyi.moneysaver123.util.DateUtil;
import com.padc.nyi.moneysaver123.util.MoneySaverConstant;
import com.padc.nyi.moneysaver123.views.holders.BillViewHolder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZMTH on 9/5/2016.
 */
public class BillFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, BillViewHolder.ControllerBillItem {

    @BindView(R.id.fab_add_bill)
    FloatingActionButton fabAddBill;

    @BindView(R.id.rv_bil_list)
    RecyclerView rvBillList;

    @BindView(R.id.iv_bill_empty_image)
    ImageView ivBillEmptyImage;

    private BillListAdapter mBillListAdapter;
    private List<BillVO> mBillVOList = new ArrayList<>();

    public BillFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLoaderManager().initLoader(MoneySaverConstant.LoaderConstantBill, null, this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bill, container, false);
        ButterKnife.bind(this, view);

        fabAddBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = AddBillActivity.newIntent();
                startActivity(intent);
            }
        });

        mBillListAdapter = new BillListAdapter(mBillVOList, this);
        rvBillList.setAdapter(mBillListAdapter);

        int gridColumnSpanCount = 2;
        rvBillList.setLayoutManager(new GridLayoutManager(getContext(), gridColumnSpanCount));

        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(MoneySaverApp.getContext(),
                MoneySaverContract.BillEntry.CONTENT_URI,
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<BillVO> billVOList = new ArrayList<>();
        if(data != null && data.moveToFirst()) {
            do {
                BillVO billVO = BillVO.parseToBillVO(data);
                billVO.setFinalDateText(DateUtil.changeMilliTimeToText(billVO.getFinalDate()));
                billVO.setRemindDateText(DateUtil.changeMilliTimeToText(billVO.getRemindDate()));
                billVOList.add(billVO);
            }while (data.moveToNext());
        }
        if(billVOList.size() > 0) ivBillEmptyImage.setVisibility(View.INVISIBLE);
        mBillListAdapter.addAllList(billVOList);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onTapBill(BillVO billVO) {
        openDetailDialogeBox(billVO, getContext());
    }


    public void openDetailDialogeBox(final BillVO billVO, Context context){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View promptView = layoutInflater.inflate(R.layout.popup_detail_bill, null);
        final AlertDialog.Builder alert = new AlertDialog.Builder(context);


        alert.setView(promptView);
        alert.setPositiveButton(R.string.lbl_bill_yes_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MoneySaverModel.getInstance().transferBillToExpense(billVO);
            }
        });
        alert.setNegativeButton(R.string.lbl_bill_no_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        TextView tvTitle = (TextView) promptView.findViewById(R.id.tv_detail_bill_title);
        TextView tvAmount = (TextView) promptView.findViewById(R.id.tv_detail_bill_amount);
        TextView tvFinalDate = (TextView) promptView.findViewById(R.id.tv_detail_bill_final_date);
        TextView tvRemindDate = (TextView) promptView.findViewById(R.id.tv_detail_bill_remind_date);

        tvTitle.setText(billVO.getTitle());
        tvAmount.setText(billVO.getAmount() + "");
        tvFinalDate.setText(billVO.getFinalDateText());
        tvRemindDate.setText(billVO.getRemindDateText());

        // create an alert dialog
        AlertDialog alert1 = alert.create();

        alert1.show();

    }
}
