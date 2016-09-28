package com.padc.nyi.moneysaver123.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.data.vos.ExpenseCatVO;
import com.padc.nyi.moneysaver123.data.vos.ExpenseVO;
import com.padc.nyi.moneysaver123.util.MoneySaverConstant;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by IN-3442 on 28-Sep-16.
 */
public class ExpenseCatViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

    @BindView(R.id.tv_expense_title)
    TextView tvExpenseCat;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.iv_expense_cat)
    ImageView ivExpenseCat;

    @BindView(R.id.tv_expense_total)
    TextView tvExpenseAmount;

    @BindView(R.id.tv_expense_percentage)
    TextView tvExpensePercentage;

    private ControllerExpenseCatItem mControllerExpenseCatItem;
    private ExpenseCatVO mExpenseCatVO;

    public ExpenseCatViewHolder(View itemView, ControllerExpenseCatItem controllerExpenseCatItem) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        this.mControllerExpenseCatItem = controllerExpenseCatItem;
    }

    public void bindData(ExpenseCatVO expenseCatVO){
        mExpenseCatVO = expenseCatVO;
        tvExpenseCat.setText(mExpenseCatVO.getExpenseCatTitle());
        progressBar.setMax(expenseCatVO.getTotal());
        progressBar.setProgress(expenseCatVO.getAmount());
        progressBar.setScaleY(3f);

        ivExpenseCat.setImageResource(MoneySaverConstant.expenseCatImage[expenseCatVO.getCatID()]);

        tvExpenseAmount.setText(expenseCatVO.getAmount() + "");

        if(expenseCatVO.getTotal() != 0)tvExpensePercentage.setText( ((expenseCatVO.getAmount() * 100) / expenseCatVO.getTotal()) + " %");
        else tvExpensePercentage.setText("0 %");
    }

    @Override
    public void onClick(View view) {
        mControllerExpenseCatItem.onTapExpense(mExpenseCatVO);
    }

    public interface ControllerExpenseCatItem{
        void onTapExpense(ExpenseCatVO expenseCatVO);
    }
}
