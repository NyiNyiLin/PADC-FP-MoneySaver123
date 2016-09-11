package com.padc.nyi.moneysaver123.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.data.vos.IncomeVOS;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZMTH on 9/10/2016.
 */
public class IncomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    @BindView(R.id.tv_income_title)
    TextView tvIncomeTitle;

    @BindView(R.id.tv_income_cat)
    TextView tvIncomeCat;

    @BindView(R.id.tv_income_amount)
    TextView tvIncomeAmount;

    public IncomeViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindData(IncomeVOS incomeVO){
        tvIncomeTitle.setText(incomeVO.getTitle());
        tvIncomeCat.setText(incomeVO.getCategory_id() + "");
        tvIncomeAmount.setText(incomeVO.getAmount() + "");
    }

    @Override
    public void onClick(View view) {

    }
}
