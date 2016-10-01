package com.padc.nyi.moneysaver123.views.holders;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.data.vos.BillVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IN-3442 on 29-Sep-16.
 */
public class BillViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private ControllerBillItem mControllerBillItem;
    private BillVO billVO;

    @BindView(R.id.tv_item_bill_name)
    TextView tvItemBillName;

    @BindView(R.id.tv_item_bill_last_date)
    TextView tvItemBillLastDate;

    @BindView(R.id.tv_item_bill_amount)
    TextView tvItemBillAmount;

    @BindView(R.id.iv_item_bill_image)
    ImageView ivItemBillImage;

    @BindView(R.id.ll_bill_background)
    LinearLayout linearLayout;

    private int colorString[] = {R.color.bill_blue, R.color.bill_red};

    public BillViewHolder(View itemView, ControllerBillItem controllerBillItem) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mControllerBillItem = controllerBillItem;
    }

    public void bindData(BillVO billVO){
        this.billVO = billVO;

        tvItemBillName.setText(billVO.getTitle());
        tvItemBillAmount.setText(billVO.getAmount() + "");
        tvItemBillLastDate.setText(billVO.getFinalDateText());

    }

    @Override
    public void onClick(View view) {
        mControllerBillItem.onTapBill(billVO);
    }

    public interface ControllerBillItem{
        void onTapBill(BillVO billVO);
    }
}
