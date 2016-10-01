package com.padc.nyi.moneysaver123.activities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.padc.nyi.moneysaver123.MoneySaverApp;
import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.data.models.MoneySaverModel;
import com.padc.nyi.moneysaver123.data.persistence.MoneySaverContract;
import com.padc.nyi.moneysaver123.data.vos.BillVO;
import com.padc.nyi.moneysaver123.data.vos.ExpenseVO;
import com.padc.nyi.moneysaver123.fragments.BillFragment;
import com.padc.nyi.moneysaver123.util.DateUtil;
import com.padc.nyi.moneysaver123.util.MoneySaverConstant;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlarmDisplayActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    private static final String PARAM_BILL_ID = "bill_id";
    private int _id = 0;

    BillVO billVO;

    @BindView(R.id.tv_detail_bill__alarm_title)
    TextView tvTitle;

    @BindView(R.id.tv_detail_bill_alarm_amount)
    TextView tvAmount;

    @BindView(R.id.tv_detail_bill_alarm_final_date)
    TextView tvFinalDate;

    @BindView(R.id.tv_detail_bill_alarm_remind_date)
    TextView tvRemindDate;

    public static Intent newIntent(int billID){
        Intent intent = new Intent(MoneySaverApp.getContext(), AlarmDisplayActivity.class);
        intent.putExtra(PARAM_BILL_ID, billID);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_display);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        _id = intent.getIntExtra(PARAM_BILL_ID, 0);

        getSupportLoaderManager().initLoader(MoneySaverConstant.LoaderConstantBillAlarmDisplay, null, this);


    }

    @OnClick(R.id.btn_bill_detail_alarm_finish)
    public void onClickFinish(View view){
        MoneySaverModel.getInstance().transferBillToExpense(billVO);
    }

    @OnClick(R.id.btn_bill_detail_alarm_not_yet)
    public void onClickNotYet(View view){
        this.finish();
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(MoneySaverApp.getContext(),
                MoneySaverContract.BillEntry.buildBillUriWithID(_id),
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        if(data != null && data.moveToFirst()) {
            do {
                billVO = BillVO.parseToBillVO(data);

            }while (data.moveToNext());

            tvTitle.setText(billVO.getTitle());
            tvAmount.setText(billVO.getAmount() + "");
            tvFinalDate.setText(DateUtil.changeMilliTimeToText(billVO.getFinalDate()));
            tvRemindDate.setText(DateUtil.changeMilliTimeToText(billVO.getRemindDate()));
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


}
