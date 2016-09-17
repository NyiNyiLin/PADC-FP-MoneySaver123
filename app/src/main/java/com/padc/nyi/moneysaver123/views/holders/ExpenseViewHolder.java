package com.padc.nyi.moneysaver123.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.data.vos.ExpenseVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IN-3442 on 11-Sep-16.
 */
public class ExpenseViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.tv_income_title)
    TextView tvExpenseTitle;

    @BindView(R.id.tv_income_cat)
    TextView tvExpenseCat;

    @BindView(R.id.tv_income_amount)
    TextView tvExpenseAmount;

    public ExpenseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindData(ExpenseVO expenseVO){
        tvExpenseTitle.setText(expenseVO.getTitle());
        tvExpenseCat.setText(expenseVO.getCategory_id() + "");
        tvExpenseAmount.setText(expenseVO.getAmount() + "");
    }
}
