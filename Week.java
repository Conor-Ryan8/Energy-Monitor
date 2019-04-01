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
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class Week extends Fragment
{
    private BarChart Weekly;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.week, container, false);

        Spinner weekSpinner = v.findViewById(R.id.weekSpinner);
        ArrayAdapter<String> weekAdaptor = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ThisWeek));

        weekAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        weekSpinner.setAdapter(weekAdaptor);

        Weekly = v.findViewById(R.id.week_chart);
        Weekly.setDragEnabled(true);
        Weekly.setScaleEnabled(false);

        //Dummy Data
        ArrayList<BarEntry> WeekValues = new ArrayList<>();
        WeekValues.add(new BarEntry(1f, 34));
        WeekValues.add(new BarEntry(2f, 40));
        WeekValues.add(new BarEntry(3f, 28));
        WeekValues.add(new BarEntry(4f, 36));
        WeekValues.add(new BarEntry(5f, 38));
        WeekValues.add(new BarEntry(6f, 29));
        WeekValues.add(new BarEntry(7f, 32));
        BarDataSet WeekSet = new BarDataSet(WeekValues, "Units Consumed");
        WeekSet.setColor(Color.GREEN);

        ArrayList<String> Days = new ArrayList<>();
        Days.add(" ");
        Days.add("Mon");
        Days.add("Tue");
        Days.add("Wed");
        Days.add("Thu");
        Days.add("Fri");
        Days.add("Sat");
        Days.add("Sun");
        Weekly.getXAxis().setValueFormatter(new IndexAxisValueFormatter(Days));

        Weekly.getAxisRight().setEnabled(false);
        Weekly.setFitBars(true);

        //Legend Config
        Legend legend = Weekly.getLegend();
        legend.setEnabled(false);

        //Description Config
        Description description = Weekly.getDescription();
        description.setEnabled(false);

        //yAxis Config
        YAxis yAxis = Weekly.getAxisLeft();
        yAxis.setAxisMinimum(0f); // start at zero
        yAxis.setAxisMaximum(50f); // the axis maximum is 100
        yAxis.setGranularity(5f); // interval 1

        //xAxis Config
        XAxis xAxis = Weekly.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f); // start at zero
        xAxis.setAxisMaximum(8f); // the axis maximum is 100
        xAxis.setGranularity(1f); // interval 1

        BarData data = new BarData(WeekSet);
        Weekly.setData(data);

        return v;
    }
}
