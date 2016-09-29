package com.padc.nyi.moneysaver123.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.data.vos.ExpenseVO;
import com.padc.nyi.moneysaver123.data.vos.IncomeVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IN-3442 on 27-Sep-16.
 */
public class IncomeHeaderViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_item_income_header)
    TextView tvIncomeHeader;

    @BindView(R.id.tv_item_income_header_total)
    TextView tvIncomeHeaderTotal;

    public IncomeHeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public void bindData(IncomeVO incomeVO){
        tvIncomeHeader.setText(incomeVO.getTextDate());
        String amount = incomeVO.getAmount() + "";
        tvIncomeHeaderTotal.setText( amount.substring(1));
    }
}
