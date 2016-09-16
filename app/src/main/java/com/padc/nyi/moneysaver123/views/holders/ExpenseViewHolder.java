package com.padc.nyi.moneysaver123.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.data.vos.ExpenseVO;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by IN-3442 on 11-Sep-16.
 */
public class ExpenseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    @BindView(R.id.tv_expense_title)
    TextView tvExpenseTitle;

    @BindView(R.id.tv_expense_cat)
    TextView tvExpenseCat;

    @BindView(R.id.tv_expense_amount)
    TextView tvExpenseAmount;

    private ControllerExpenseItem mControllerExpenseItem;
    private ExpenseVO mExpenseVO;

    public ExpenseViewHolder(View itemView, ControllerExpenseItem mControllerExpenseItem) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        this.mControllerExpenseItem = mControllerExpenseItem;

    }

    public void bindData(ExpenseVO expenseVO){
        mExpenseVO = expenseVO;
        tvExpenseTitle.setText(expenseVO.getTitle());
        tvExpenseCat.setText(expenseVO.getCategory() + "");
        tvExpenseAmount.setText(expenseVO.getAmount() + "");
    }

    @Override
    public void onClick(View view) {
        mControllerExpenseItem.onTapExpense(mExpenseVO);
    }

    public interface ControllerExpenseItem{
        void onTapExpense(ExpenseVO expenseVO);
    }
}
