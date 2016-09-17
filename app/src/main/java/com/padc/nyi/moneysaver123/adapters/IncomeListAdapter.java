package com.padc.nyi.moneysaver123.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.nyi.moneysaver123.MoneySaverApp;
import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.data.vos.IncomeVOS;
import com.padc.nyi.moneysaver123.view.holder.IncomeViewHolder;

import java.util.List;

/**
 * Created by ZMTH on 9/10/2016.
 */
public class IncomeListAdapter extends RecyclerView.Adapter<IncomeViewHolder>{

    private LayoutInflater mInflater;
    private List<IncomeVOS> mIncomeVOList;
    private IncomeViewHolder.ControllerIncomeItem mControllerIncomeItem;

    public IncomeListAdapter(List<IncomeVOS> mExpenseVOList, IncomeViewHolder.ControllerIncomeItem mControllerIncomeItem) {
        mInflater = LayoutInflater.from(MoneySaverApp.getContext());
        this.mIncomeVOList = mExpenseVOList;
        this.mControllerIncomeItem = mControllerIncomeItem;
    }

    @Override
    public IncomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.view_item_income, parent, false);
        return new IncomeViewHolder(itemView, mControllerIncomeItem);
    }

    @Override
    public void onBindViewHolder(IncomeViewHolder holder, int position) {
        holder.bindData(mIncomeVOList.get(position));
    }

    @Override
    public int getItemCount() {

        return mIncomeVOList.size();
    }
}
