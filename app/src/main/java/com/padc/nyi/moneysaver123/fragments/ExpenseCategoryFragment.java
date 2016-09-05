package com.padc.nyi.moneysaver123.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.nyi.moneysaver123.R;

/**
 * Created by ZMTH on 9/5/2016.
 */
public class ExpenseCategoryFragment extends Fragment {

    public ExpenseCategoryFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expense_category, container, false);
        return view;
    }

}
