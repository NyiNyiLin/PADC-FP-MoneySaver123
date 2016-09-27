package com.padc.nyi.moneysaver123.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.data.vos.ExpenseVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IN-3442 on 27-Sep-16.
 */
public class ExpenseHeaderViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_item_expense_header)
    TextView tvExpenseHeader;

    @BindView(R.id.tv_item_expense_header_total)
    TextView tvExpenseHeaderTotal;

    public ExpenseHeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
    public void bindData(ExpenseVO expenseVO){
        tvExpenseHeader.setText(expenseVO.getTextDate());
        String amount = expenseVO.getAmount() + "";
        tvExpenseHeaderTotal.setText( amount.substring(1));
    }
}
