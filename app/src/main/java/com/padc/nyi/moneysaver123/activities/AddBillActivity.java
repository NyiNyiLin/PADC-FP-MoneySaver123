package com.padc.nyi.moneysaver123.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.padc.nyi.moneysaver123.MoneySaverApp;
import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.data.models.MoneySaverModel;
import com.padc.nyi.moneysaver123.data.vos.BillVO;
import com.padc.nyi.moneysaver123.receiver.BillAlarm;
import com.padc.nyi.moneysaver123.util.AlarmiUtil;
import com.padc.nyi.moneysaver123.util.DateUtil;
import com.padc.nyi.moneysaver123.util.MoneySaverConstant;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ZMTH on 9/24/2016.
 */
public class AddBillActivity extends AppCompatActivity  implements  DatePickerDialog.OnDateSetListener{

    public static final String NOTI_TITLE = "title";
    public static final String NOTI_BODY = "body";
    public static final String BILL_ID = "bill_id";

    private final int FINAL_DATE = 1;
    private final int REMIND_DATE = 2;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.et_bill_title)
    EditText etBillTitle;

    @BindView(R.id.et__bill_amount)
    EditText etBillAmount;

    @BindView(R.id.txt_bill_final_date)
    TextView tvFinalDate;

    @BindView(R.id.txt_bill_remind_date)
    TextView tvRemindDate;

    @BindView(R.id.btn_bill_save)
    Button btnBillSave;

    @BindView(R.id.tv_screen_title)
    TextView tvScreenTitle;

    private BillVO billVO;

    private long dateInNumFinal;
    private long dateInNumRemind;

    private int dateType;

    public static Intent newIntent(){
        Intent intent = new Intent(MoneySaverApp.getContext(), AddBillActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill);

        ButterKnife.bind(this, this);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        tvScreenTitle.setText(getResources().getText(R.string.title_activity_add_bill));

        getCurrentDate();
    }

    public void successfullySaveDataDialogBox(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddBillActivity.this);
        alertDialog.setMessage("Successfully save data.");
        alertDialog.setPositiveButton("CLOSE", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                finishThisActivity();
            }
        });
        alertDialog.show();
    }

    public void unsuccessfullySaveDataDialogBox(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddBillActivity.this);
        alertDialog.setMessage("Please fill require fields.");
        alertDialog.setPositiveButton("CLOSE", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {

            }
        });
        alertDialog.show();
    }

    //clear user input data
    private  void clearBillUserInputData(){
        billVO = new BillVO();
        etBillTitle.getText().clear();
        etBillAmount.getText().clear();
    }

    //check validation
    private boolean isEmptyUserInputData(){
        if(TextUtils.isEmpty(etBillTitle.getText().toString())||TextUtils.isEmpty(etBillAmount.getText().toString()) ){
            unsuccessfullySaveDataDialogBox();
            return false;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                this.finish();
                break;
        }
        return true;
    }

    @OnClick(R.id.txt_bill_final_date)
    public void onClickFinalDate(View view){
        dateType = FINAL_DATE;
        showThirdPartyDatePicker();
    }

    @OnClick(R.id.txt_bill_remind_date)
    public void onClickRemindDate(View view){
        dateType = REMIND_DATE;
        showThirdPartyDatePicker();
    }

    @OnClick(R.id.btn_bill_save)
    public void onClickBillSave(View view){
        if(dateInNumFinal < dateInNumRemind){
            tvRemindDate.setError(getResources().getString(R.string.lbl_bill_warning));
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.lbl_bill_warning), Toast.LENGTH_SHORT).show();
        }
        else if(isEmptyUserInputData()){
            billVO = new BillVO();

            billVO.setTitle(etBillTitle.getText().toString());
            billVO.setAmount(Integer.parseInt(etBillAmount.getText().toString()));
            billVO.setFinalDate(dateInNumFinal);
            billVO.setRemindDate(dateInNumRemind);
            billVO.setIsFinish(MoneySaverConstant.billNotYet);
            billVO.setCatID(9);

            int billID = MoneySaverModel.getInstance().saveReminderForBill(billVO);
            billVO.setBillID(billID);
            createNoti(billVO);
            clearBillUserInputData();
            successfullySaveDataDialogBox();
        }
    }

    private void finishThisActivity(){
        this.finish();
    }

    private void getCurrentDate(){
        Calendar now = Calendar.getInstance();

        try {
            dateInNumFinal = DateUtil.channgeTimeToMilliTime(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
            dateInNumRemind = dateInNumFinal;
            tvFinalDate.setText(DateUtil.changeMilliTimeToText(dateInNumFinal));
            tvRemindDate.setText(DateUtil.changeMilliTimeToText(dateInNumRemind));

        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private  void showThirdPartyDatePicker(){
        Calendar now = Calendar.getInstance();
        DatePickerDialog thirdPartyDatePicker = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );

        thirdPartyDatePicker.show(this.getFragmentManager(), "ThirdPartyDatePicker");
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        try {
            if(dateType == FINAL_DATE){
                dateInNumFinal = DateUtil.channgeTimeToMilliTime(year, monthOfYear, dayOfMonth);
                tvFinalDate.setText(DateUtil.changeMilliTimeToText(dateInNumFinal));
            }else if(dateType == REMIND_DATE){
                dateInNumRemind = DateUtil.channgeTimeToMilliTime(year, monthOfYear, dayOfMonth);
                tvRemindDate.setText(DateUtil.changeMilliTimeToText(dateInNumRemind));
            }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void createNoti(BillVO billVO){
        Intent intent = new Intent(MoneySaverApp.getContext(), BillAlarm.class);
        intent.putExtra(NOTI_TITLE, billVO.getTitle());
        intent.putExtra(NOTI_BODY, DateUtil.changeMilliTimeToText(billVO.getFinalDate()) + " " + MoneySaverConstant.textLastDayRemin);
        intent.putExtra(BILL_ID, billVO.getBillID());
        //AlarmiUtil.setOneTimeAlarm(MoneySaverApp.getContext(), intent, billVO.getRemindDate());
        AlarmiUtil.setOneTimeAlarm(MoneySaverApp.getContext(), intent, System.currentTimeMillis() + (5 * 1000));
    }
}
