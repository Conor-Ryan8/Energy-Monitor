package com.example.energymonitor;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class Month extends Fragment
{
    private BarChart Monthly;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.month, container, false);

        Spinner monthSpinner = v.findViewById(R.id.monthSpinner);
        ArrayAdapter<String> monthAdaptor = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ThisMonth));

        monthAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdaptor);

        Monthly = v.findViewById(R.id.month_chart);
        Monthly.setDragEnabled(true);
        Monthly.setScaleEnabled(false);

        //Dummy Data
        ArrayList<BarEntry> MonthValues = new ArrayList<>();
        MonthValues.add(new BarEntry(1f, 34));
        MonthValues.add(new BarEntry(2f, 40));
        MonthValues.add(new BarEntry(3f, 28));
        MonthValues.add(new BarEntry(4f, 36));
        MonthValues.add(new BarEntry(5f, 38));
        MonthValues.add(new BarEntry(6f, 29));
        MonthValues.add(new BarEntry(7f, 18));
        MonthValues.add(new BarEntry(8f, 20));
        MonthValues.add(new BarEntry(9f, 40));
        MonthValues.add(new BarEntry(10f, 28));
        MonthValues.add(new BarEntry(11f, 36));
        MonthValues.add(new BarEntry(12f, 38));
        MonthValues.add(new BarEntry(13f, 29));
        MonthValues.add(new BarEntry(14f, 32));
        MonthValues.add(new BarEntry(15f, 34));
        MonthValues.add(new BarEntry(16f, 40));
        MonthValues.add(new BarEntry(17f, 41));
        MonthValues.add(new BarEntry(18f, 36));
        MonthValues.add(new BarEntry(19f, 38));
        MonthValues.add(new BarEntry(20f, 29));
        MonthValues.add(new BarEntry(21f, 32));
        MonthValues.add(new BarEntry(22f, 34));
        MonthValues.add(new BarEntry(23f, 40));
        MonthValues.add(new BarEntry(24f, 28));
        MonthValues.add(new BarEntry(25f, 36));
        MonthValues.add(new BarEntry(26f, 38));
        MonthValues.add(new BarEntry(27f, 29));
        MonthValues.add(new BarEntry(28f, 32));
        MonthValues.add(new BarEntry(29f, 34));
        MonthValues.add(new BarEntry(30f, 36));

        BarDataSet MonthSet = new BarDataSet(MonthValues, "Units Consumed");
        MonthSet.setColor(Color.BLUE);

        Monthly.getAxisRight().setEnabled(false);
        Monthly.setFitBars(true);

        //Legend Config
        Legend legend = Monthly.getLegend();
        legend.setEnabled(false);

        //Description Config
        Description description = Monthly.getDescription();
        description.setEnabled(false);

        //yAxis Config
        YAxis yAxis = Monthly.getAxisLeft();
        yAxis.setAxisMinimum(0f); // start at zero
        yAxis.setAxisMaximum(50f); // the axis maximum is 100
        yAxis.setGranularity(5f); // interval 1

        //xAxis Config
        XAxis xAxis = Monthly.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f); // start at zero
        xAxis.setAxisMaximum(31f); // the axis maximum is 100
        xAxis.setGranularity(1f); // interval 1

        BarData data = new BarData(MonthSet);
        Monthly.setData(data);

        return v;
    }
}
