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

    ArrayList<BarDataSet> dataSets = null;

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
    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(110.000f, 0); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(40.000f, 1); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(60.000f, 2); // Mar
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(30.000f, 3); // Apr
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(90.000f, 4); // May
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(100.000f, 5); // Jun
        valueSet1.add(v1e6);

        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry v2e1 = new BarEntry(150.000f, 0); // Jan
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(90.000f, 1); // Feb
        valueSet2.add(v2e2);
        BarEntry v2e3 = new BarEntry(120.000f, 2); // Mar
        valueSet2.add(v2e3);
        BarEntry v2e4 = new BarEntry(60.000f, 3); // Apr
        valueSet2.add(v2e4);
        BarEntry v2e5 = new BarEntry(20.000f, 4); // May
        valueSet2.add(v2e5);
        BarEntry v2e6 = new BarEntry(80.000f, 5); // Jun
        valueSet2.add(v2e6);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Brand 1");
        barDataSet1.setColor(Color.rgb(0, 155, 0));
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Brand 2");
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("JAN");
        xAxis.add("FEB");
        xAxis.add("MAR");
        xAxis.add("APR");
        xAxis.add("MAY");
        xAxis.add("JUN");
        return xAxis;
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
        assignDataForGraph(incomeVOList);

        BarData dataForGraph = new BarData(xAxis, dataSets);
        chart.setData(dataForGraph);
        chart.setDescription("My Chart");
        chart.animateXY(2000, 2000);
        chart.invalidate();

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    private void assignDataForGraph(List<IncomeVO> incomeVOList){

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

                    BarEntry v1e1 = new BarEntry(totalAmount, insertedPosition);
                    valueSet1.add(v1e1);

                    xAxis.add(currentDate);

                    //incomeVOList.add(insertedPosition, new IncomeVO("zxy", (0 - totalAmount), currentDate, 1, ""));
                    insertedPosition = a+1;
                    currentDate = anotherDate;
                    totalAmount = 0;
                }else{
                    totalAmount = totalAmount + incomeVOList.get(a).getAmount();
                }
            }
            //incomeVOList.add(insertedPosition, new IncomeVO("zxy", (0 - totalAmount), currentDate, 1, ""));

            BarEntry v1e1 = new BarEntry(totalAmount, insertedPosition);
            valueSet1.add(v1e1);

            xAxis.add(currentDate);

            BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Income");
            barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);

            dataSets = new ArrayList<>();
            dataSets.add(barDataSet1);
        }
        //return incomeVOList;
    }
}
