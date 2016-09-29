package com.padc.nyi.moneysaver123.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.nyi.moneysaver123.MoneySaverApp;
import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.data.vos.ExpenseVO;
import com.padc.nyi.moneysaver123.views.holders.ExpenseHeaderViewHolder;
import com.padc.nyi.moneysaver123.views.holders.ExpenseViewHolder;

import java.util.List;

/**
 * Created by IN-3442 on 11-Sep-16.
 */
public class ExpenseListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int HEADER = 1;
    private final int DATA = 2;

    private LayoutInflater mInflater;
    private List<ExpenseVO> mExpenseVOList;
    private ExpenseViewHolder.ControllerExpenseItem mControllerExpenseItem;

    public ExpenseListAdapter(List<ExpenseVO> mExpenseVOList, ExpenseViewHolder.ControllerExpenseItem mControllerExpenseItem) {
        mInflater = LayoutInflater.from(MoneySaverApp.getContext());
        this.mExpenseVOList = mExpenseVOList;
        this.mControllerExpenseItem = mControllerExpenseItem;
    }

    public void addAllList(List<ExpenseVO> expenseVOList){
        mExpenseVOList.clear();
        mExpenseVOList = expenseVOList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == DATA) {
            View itemView = mInflater.inflate(R.layout.view_item_expense1, parent, false);
            return new ExpenseViewHolder(itemView, mControllerExpenseItem);
        }else if(viewType == HEADER){
            View itemView = mInflater.inflate(R.layout.view_item_expense_header, parent, false);
            return new ExpenseHeaderViewHolder(itemView);
        }else return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ExpenseVO expenseVO = mExpenseVOList.get(position);
        if(expenseVO.getAmount() < 0){
            ExpenseHeaderViewHolder expenseHeaderViewHolder = (ExpenseHeaderViewHolder) holder;
            expenseHeaderViewHolder.bindData(expenseVO);
        }else{
            ExpenseViewHolder expenseViewHolder = (ExpenseViewHolder) holder;
            expenseViewHolder.bindData(expenseVO);
        }

    }

    @Override
    public int getItemCount() {
        return mExpenseVOList.size();
    }

    @Override
    public int getItemViewType(int position) {
        ExpenseVO expenseVO = mExpenseVOList.get(position);
        if(expenseVO.getAmount() < 0){
            return HEADER;
        } else return DATA;
    }
}
