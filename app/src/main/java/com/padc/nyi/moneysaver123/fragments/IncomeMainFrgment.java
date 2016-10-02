package com.padc.nyi.moneysaver123.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
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
 * Use the {@link IncomeMainFrgment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IncomeMainFrgment extends Fragment {

    @BindView(R.id.pager_income)
    ViewPager pagerIncome;

    @BindView(R.id.tl_income)
    TabLayout tlIncome;

    private ExpensePagerAdapter mIncomePagerAdapter;

    public IncomeMainFrgment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment IncomeMainFrgment.
     */
    public static IncomeMainFrgment newInstance() {
        IncomeMainFrgment fragment = new IncomeMainFrgment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mIncomePagerAdapter = new ExpensePagerAdapter(getActivity().getSupportFragmentManager());
        mIncomePagerAdapter.addTab(new IncomeFragment(), "Month");
        mIncomePagerAdapter.addTab(IncomeGraphFragment.newInstance(), "Graph");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_income_main_frgment, container, false);

        ButterKnife.bind(this, view);

        pagerIncome.setAdapter(mIncomePagerAdapter);
        pagerIncome.setOffscreenPageLimit(mIncomePagerAdapter.getCount());

        tlIncome.setupWithViewPager(pagerIncome);

        MMFontUtils.applyMMFontToTabLayout(tlIncome);

        return view;
    }

}
