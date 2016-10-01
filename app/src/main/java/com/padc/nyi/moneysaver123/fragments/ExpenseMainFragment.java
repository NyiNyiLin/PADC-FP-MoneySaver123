package com.padc.nyi.moneysaver123.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.adapters.ExpensePagerAdapter;
import com.padc.nyi.moneysaver123.util.MMFontUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExpenseMainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExpenseMainFragment extends Fragment {

    @BindView(R.id.tl_expense)
    TabLayout tlExpense;

    @BindView(R.id.pager_expense)
    ViewPager pagerExpense;

    ExpensePagerAdapter mExpensePagerAdapter;
    public ExpenseMainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ExpenseMainFragment.
     */
    public static ExpenseMainFragment newInstance() {
        ExpenseMainFragment fragment = new ExpenseMainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mExpensePagerAdapter = new ExpensePagerAdapter(getActivity().getSupportFragmentManager());
        mExpensePagerAdapter.addTab(ExpenseDateFragment.newInstance(-1), "Date");
        mExpensePagerAdapter.addTab(ExpenseCatFragment.newInstance(), "Category");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expense_main, container, false);
        ButterKnife.bind(this, view);

        pagerExpense.setAdapter(mExpensePagerAdapter);
        pagerExpense.setOffscreenPageLimit(mExpensePagerAdapter.getCount());

        tlExpense.setupWithViewPager(pagerExpense);

        MMFontUtils.applyMMFontToTabLayout(tlExpense);

        return view;
    }

}
