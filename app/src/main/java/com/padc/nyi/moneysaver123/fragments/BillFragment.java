package com.padc.nyi.moneysaver123.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.activities.AddBillActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZMTH on 9/5/2016.
 */
public class BillFragment extends Fragment {

    @BindView(R.id.fab_add_bill)
    FloatingActionButton fabAddBill;

    public BillFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bill, container, false);
        ButterKnife.bind(this, view);

        fabAddBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = AddBillActivity.newIntent();
                startActivity(intent);
            }
        });

        return view;
    }
}
