package com.padc.nyi.moneysaver123.fragments;


import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.padc.nyi.moneysaver123.MoneySaverApp;
import com.padc.nyi.moneysaver123.R;
import com.padc.nyi.moneysaver123.data.persistence.MoneySaverContract;
import com.padc.nyi.moneysaver123.data.vos.IncomeVO;
import com.padc.nyi.moneysaver123.util.DateUtil;
import com.padc.nyi.moneysaver123.util.MoneySaverConstant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IncomeGraphFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IncomeGraphFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    @BindView(R.id.chart)
    BarChart chart;

    private ArrayList<String> xAxis = new ArrayList<>();

    ArrayList<BarEntry> valueSet1 = new ArrayList<>();

    ArrayList<BarDataSet> dataSets = new ArrayList<>();;

    public IncomeGraphFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment IncomeGraphFragment.
     */
    public static IncomeGraphFragment newInstance() {
        IncomeGraphFragment fragment = new IncomeGraphFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLoaderManager().initLoader(MoneySaverConstant.LoaderConstantIncome,null,this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_expense_graph, container, false);
        ButterKnife.bind(this, view);

        //BarChart chart = (BarChart) view.findViewById(R.id.chart);

        return  view;
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(MoneySaverApp.getContext(),
                MoneySaverContract.IncomeEntry.CONTENT_URI,
                null,
                null,
                null,
                MoneySaverContract.IncomeEntry.COLUMN_INCOME_DATE + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        List<IncomeVO> incomeVOList = new ArrayList<>();
        if(data != null && data.moveToFirst()) {
            do {
                IncomeVO incomeVO = IncomeVO.parseToIncomeVO(data);
                incomeVOList.add(incomeVO);
            }while (data.moveToNext());
        }
        //assignDataForGraph(incomeVOList);
        assignDataToGraph(addHeadertoList(incomeVOList));

        BarData dataForGraph = new BarData(xAxis, dataSets);
        if(chart != null){
            chart.clear();
            chart.setData(dataForGraph);
            chart.setDescription("Report");
            chart.animateXY(2000, 2000);
            chart.invalidate();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private void assignDataToGraph(List<IncomeVO> incomeVOList){
        xAxis = new ArrayList<>();
        valueSet1 = new ArrayList<>();

        int num = 0;
        for(int a=0; a<incomeVOList.size(); a++){
            IncomeVO incomeVO = incomeVOList.get(a);
            if(incomeVO.getAmount() < 0){
                String amount = incomeVO.getAmount() + "";
                int amountInt = Integer.parseInt(amount.substring(1));
                BarEntry v1e1 = new BarEntry(amountInt, num);
                valueSet1.add(v1e1);
                xAxis.add(incomeVO.getTextDate());
                num++;
            }
        }

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Income");
        barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
    }
    private List<IncomeVO> addHeadertoList(List<IncomeVO> incomeVOList){

        if(incomeVOList.size() != 0) {
            long currentDateInMilli = incomeVOList.get(0).getDate();
            String currentDate = DateUtil.changeMilliTimeToText(currentDateInMilli);
            currentDate = currentDate.substring(3);
            int insertedPosition = 0;
            int totalAmount = 0;

            for (int a = 0; a < incomeVOList.size(); a++) {
                long anotherDateInMilli = incomeVOList.get(a).getDate();
                String anotherDate = DateUtil.changeMilliTimeToText(anotherDateInMilli);
                anotherDate = anotherDate.substring(3);
                if (currentDate.compareTo(anotherDate) != 0) {

                    incomeVOList.add(insertedPosition, new IncomeVO("zxy", (0 - totalAmount), currentDate, 1, ""));
                    insertedPosition = a+1;
                    currentDate = anotherDate;
                    totalAmount = 0;
                }else{
                    totalAmount = totalAmount + incomeVOList.get(a).getAmount();
                }
            }
            incomeVOList.add(insertedPosition, new IncomeVO("zxy", (0 - totalAmount), currentDate, 1, ""));

        }
        return incomeVOList;
    }
}
