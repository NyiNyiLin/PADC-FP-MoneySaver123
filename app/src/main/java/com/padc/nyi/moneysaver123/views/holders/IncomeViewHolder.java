package com.padc.nyi.moneysaver123.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.data.vos.IncomeVO;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZMTH on 9/26/2016.
 */
public class IncomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    @BindView(R.id.tv_income_title)
    TextView tvIncomeTitle;

    @BindView(R.id.tv_income_cat)
    TextView tvIncomeCat;

    @BindView(R.id.tv_income_amount)
    TextView tvIncomeAmount;

    private ControllerIncomeItem mControllerIncomeItem;
    private IncomeVO mIncomeVO;

    public IncomeViewHolder(View itemView, ControllerIncomeItem mControllerIncomeItem) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        this.mControllerIncomeItem = mControllerIncomeItem;
    }

    public void bindData(IncomeVO incomeVO){
        mIncomeVO = incomeVO;
        tvIncomeTitle.setText(incomeVO.getTitle());
        tvIncomeCat.setText(incomeVO.getCategory_id() + "");
        tvIncomeAmount.setText(incomeVO.getAmount() + "");
    }

    @Override
    public void onClick(View view) {
        mControllerIncomeItem.onTapIncome(mIncomeVO);
    }

    public interface ControllerIncomeItem{
        void onTapIncome(IncomeVO incomeVO);
    }
}
