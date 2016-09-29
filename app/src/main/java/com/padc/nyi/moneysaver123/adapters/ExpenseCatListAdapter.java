package com.padc.nyi.moneysaver123.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.nyi.moneysaver123.MoneySaverApp;
import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.data.vos.ExpenseCatVO;
import com.padc.nyi.moneysaver123.views.holders.ExpenseCatViewHolder;

import java.util.List;

/**
 * Created by IN-3442 on 28-Sep-16.
 */
public class ExpenseCatListAdapter extends RecyclerView.Adapter<ExpenseCatViewHolder>{
    private List<ExpenseCatVO> mExpenseCatVOList;
    private ExpenseCatViewHolder.ControllerExpenseCatItem mControllerExpenseCatItem;
    private LayoutInflater mInflater;

    public  ExpenseCatListAdapter(List<ExpenseCatVO> expenseCatVOs, ExpenseCatViewHolder.ControllerExpenseCatItem controllerExpenseCatItem){
        mInflater = LayoutInflater.from(MoneySaverApp.getContext());
        this.mExpenseCatVOList = expenseCatVOs;
        this.mControllerExpenseCatItem = controllerExpenseCatItem;
    }


    @Override
    public ExpenseCatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = mInflater.inflate(R.layout.view_item_expense_cat, parent, false);
        return new ExpenseCatViewHolder(itemview, mControllerExpenseCatItem);
    }

    @Override
    public void onBindViewHolder(ExpenseCatViewHolder holder, int position) {
        holder.bindData(mExpenseCatVOList.get(position));
    }

    @Override
    public int getItemCount() {
        return mExpenseCatVOList.size();
    }

    public void addList(List<ExpenseCatVO> expenseCatVOList) {
        this.mExpenseCatVOList = expenseCatVOList;
        notifyDataSetChanged();
    }
}
