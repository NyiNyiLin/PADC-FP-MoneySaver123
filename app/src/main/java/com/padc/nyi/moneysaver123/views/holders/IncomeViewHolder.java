package com.padc.nyi.moneysaver123.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.data.vos.IncomeVO;
import com.padc.nyi.moneysaver123.util.MoneySaverConstant;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZMTH on 9/26/2016.
 */
public class IncomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    @BindView(R.id.tv_income_title)
    TextView tvIncomeTitle;

    @BindView(R.id.tv_income_cat)
    TextView tvIncomeCat;

    @BindView(R.id.tv_income_amount)
    TextView tvIncomeAmount;

    private ControllerIncomeItem mControllerIncomeItem;
    private IncomeVO mIncomeVO;
    private View view;

    public IncomeViewHolder(View itemView, ControllerIncomeItem mControllerIncomeItem) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        view = itemView;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        this.mControllerIncomeItem = mControllerIncomeItem;
    }

    public void bindData(IncomeVO incomeVO){
        mIncomeVO = incomeVO;
        tvIncomeTitle.setText(incomeVO.getTitle());
        tvIncomeCat.setText(MoneySaverConstant.IncomeCat[incomeVO.getCategory_id()]);
        tvIncomeAmount.setText(incomeVO.getAmount() + "");
    }

    @Override
    public void onClick(View view) {
        mControllerIncomeItem.onTapIncome(mIncomeVO);
    }

    @Override
    public boolean onLongClick(View view) {
        mControllerIncomeItem.onLongPressIncome(mIncomeVO, view);
        return true;
    }

    public interface ControllerIncomeItem{
        void onTapIncome(IncomeVO incomeVO);
        void onLongPressIncome(IncomeVO incomeVO, View view);
    }
}
