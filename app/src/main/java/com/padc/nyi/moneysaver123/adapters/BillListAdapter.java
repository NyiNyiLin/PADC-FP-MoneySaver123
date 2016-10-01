package com.padc.nyi.moneysaver123.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.nyi.moneysaver123.MoneySaverApp;
import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.data.vos.BillVO;
import com.padc.nyi.moneysaver123.views.holders.BillViewHolder;
import com.padc.nyi.moneysaver123.views.holders.ExpenseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IN-3442 on 9/28/2016.
 */
public class BillListAdapter extends RecyclerView.Adapter<BillViewHolder> {

    private LayoutInflater mInflater;

    private List<BillVO> mBillVOList = new ArrayList<>();
    private BillViewHolder.ControllerBillItem mControllerBillItem;

    public BillListAdapter(List<BillVO> billVOList, BillViewHolder.ControllerBillItem controllerBillItem){
        mInflater = LayoutInflater.from(MoneySaverApp.getContext());
        mBillVOList = billVOList;
        mControllerBillItem = controllerBillItem;
    }


    @Override
    public BillViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.view_item_bill, parent, false);
        return new BillViewHolder(itemView, mControllerBillItem);
    }

    @Override
    public void onBindViewHolder(BillViewHolder holder, int position) {
        BillVO billVO = mBillVOList.get(position);
        billVO.setImageID(position);
        holder.bindData(billVO);
    }

    @Override
    public int getItemCount() {
        return mBillVOList.size();
    }

    public void addAllList(List<BillVO> billVOList){
        mBillVOList.clear();
        this.mBillVOList = billVOList;
        notifyDataSetChanged();
    }
}
