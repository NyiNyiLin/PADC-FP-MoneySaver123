package com.padc.nyi.moneysaver123.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.nyi.moneysaver123.MoneySaverApp;
import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.data.vos.ExpenseVO;
import com.padc.nyi.moneysaver123.data.vos.IncomeVO;
import com.padc.nyi.moneysaver123.views.holders.ExpenseHeaderViewHolder;
import com.padc.nyi.moneysaver123.views.holders.ExpenseViewHolder;
import com.padc.nyi.moneysaver123.views.holders.IncomeHeaderViewHolder;
import com.padc.nyi.moneysaver123.views.holders.IncomeViewHolder;

import java.util.List;

/**
 * Created by ZMTH on 9/10/2016.
 */
public class IncomeListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final int HEADER = 1;
    private final int DATA = 2;

    private LayoutInflater mInflater;
    private List<IncomeVO> mIncomeVOList;

    private IncomeViewHolder.ControllerIncomeItem mControllerIncomeItem;

    public IncomeListAdapter(List<IncomeVO> mExpenseVOList,  IncomeViewHolder.ControllerIncomeItem mControllerIncomeItem) {
        mInflater = LayoutInflater.from(MoneySaverApp.getContext());
        this.mIncomeVOList = mExpenseVOList;
        this.mControllerIncomeItem = mControllerIncomeItem;
    }

    public void addAllList(List<IncomeVO> incomeVOList){
        mIncomeVOList.clear();
        mIncomeVOList = incomeVOList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == DATA) {
            View itemView = mInflater.inflate(R.layout.view_item_income, parent, false);
            return new IncomeViewHolder(itemView, mControllerIncomeItem);
        }else if(viewType == HEADER){
            View itemView = mInflater.inflate(R.layout.view_item_income_header, parent, false);
            return new IncomeHeaderViewHolder(itemView);
        }else return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        IncomeVO incomeVO = mIncomeVOList.get(position);
        if(incomeVO.getAmount() < 0){
            IncomeHeaderViewHolder incomeHeaderViewHolder = (IncomeHeaderViewHolder) holder;
            incomeHeaderViewHolder.bindData(incomeVO);
        }else{
            IncomeViewHolder incomeViewHolder = (IncomeViewHolder) holder;
            incomeViewHolder.bindData(incomeVO);
        }
    }


    @Override
    public int getItemCount() {
        return mIncomeVOList.size();
    }

    @Override
    public int getItemViewType(int position) {
        IncomeVO incomeVO = mIncomeVOList.get(position);
        if(incomeVO.getAmount() < 0){
            return HEADER;
        } else return DATA;
    }
}
