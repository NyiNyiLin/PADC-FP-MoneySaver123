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

import com.padc.nyi.moneysaver123.MoneySaverApp;
import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.data.models.MoneySaverModel;
import com.padc.nyi.moneysaver123.data.vos.BillVO;
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

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.et_title)
    EditText etBillTitle;

    @BindView(R.id.et_amount)
    EditText etBillAmount;

    @BindView(R.id.txt_date)
    TextView tvDate;

    @BindView(R.id.btn_bill_save)
    Button btnBillSave;

    SimpleDateFormat dateFormatter;
    BillVO billVO;


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
        }

        getCurrentDate();
        saveBill();

    }

    private void saveBill(){
        btnBillSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(isEmptyUserInputData()){
                   billVO = new BillVO();

                   billVO.setTitle(etBillTitle.getText().toString());
                   billVO.setAmount(Integer.parseInt(etBillAmount.getText().toString()));
                   //billVO.setDate(tvDate.getText());

                   MoneySaverModel.getInstance().saveReminderForBill(billVO);
                   clearBillUserInputData();
                   successfullySaveDataDialogBox();
               }
            }
        });
    }

    public void successfullySaveDataDialogBox(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddBillActivity.this);
        alertDialog.setMessage("Successfully save data.");
        alertDialog.setPositiveButton("CLOSE", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {

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

    @OnClick(R.id.txt_date)
    public void onClickDate(View view){
        showThirdPartyDatePicker();
    }
    private void getCurrentDate(){
        Calendar now = Calendar.getInstance();

        try {
            dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateInString = now.get(Calendar.DAY_OF_MONTH) + "/" + (now.get(Calendar.MONTH) + 1) + "/" + now.get(Calendar.YEAR);
            Date date = dateFormatter.parse(dateInString);
            dateFormatter = new SimpleDateFormat("dd/MMM/yyyy");
            tvDate.setText(tvDate.getText().toString() + dateFormatter.format(date).toString());

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
            dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateInString = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
            Date date = dateFormatter.parse(dateInString);
            dateFormatter = new SimpleDateFormat("dd/MMM/yyyy");
            tvDate.setText(dateFormatter.format(date).toString());

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
