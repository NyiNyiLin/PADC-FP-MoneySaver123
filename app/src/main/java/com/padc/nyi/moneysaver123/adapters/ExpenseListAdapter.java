package com.padc.nyi.moneysaver123.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.nyi.moneysaver123.MoneySaverApp;
import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.data.vos.ExpenseVO;
import com.padc.nyi.moneysaver123.views.holders.ExpenseViewHolder;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by IN-3442 on 11-Sep-16.
 */
public class ExpenseListAdapter extends RecyclerView.Adapter<ExpenseViewHolder> {
    private LayoutInflater mInflater;
    private List<ExpenseVO> mExpenseVOList;
    private ExpenseViewHolder.ControllerExpenseItem mControllerExpenseItem;

    public ExpenseListAdapter(List<ExpenseVO> mExpenseVOList, ExpenseViewHolder.ControllerExpenseItem mControllerExpenseItem) {
        mInflater = LayoutInflater.from(MoneySaverApp.getContext());
        this.mExpenseVOList = mExpenseVOList;
        this.mControllerExpenseItem = mControllerExpenseItem;
    }

    @Override
    public ExpenseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.view_item_expense1, parent, false);
        return new ExpenseViewHolder(itemView, mControllerExpenseItem);
    }

    @Override
    public void onBindViewHolder(ExpenseViewHolder holder, int position) {
        holder.bindData(mExpenseVOList.get(position));
    }

    @Override
    public int getItemCount() {
        return mExpenseVOList.size();
    }
}
