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

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;

import java.util.ArrayList;

public class Day extends Fragment
{
    private LineChart Daily;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.day, container, false);

        Spinner daySpinner = v.findViewById(R.id.daySpinner);
        ArrayAdapter<String> dayAdaptor = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Today));

        dayAdaptor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdaptor);

        Daily = v.findViewById(R.id.day_chart);
        Daily.setDragEnabled(true);
        Daily.setScaleEnabled(false);

        //Dummy Data
        ArrayList<Entry> HourValues = new ArrayList<>();
        HourValues.add(new Entry(0f, 3));
        HourValues.add(new Entry(1f, 3));
        HourValues.add(new Entry(2f, 2));
        HourValues.add(new Entry(3f, 2));
        HourValues.add(new Entry(4f, 2));
        HourValues.add(new Entry(5f, 2));
        HourValues.add(new Entry(6f, 2));
        HourValues.add(new Entry(7f, 3));
        HourValues.add(new Entry(8f, 4));
        HourValues.add(new Entry(9f, 5));
        HourValues.add(new Entry(10f, 6));
        HourValues.add(new Entry(11f, 6));
        HourValues.add(new Entry(12f, 6));
        HourValues.add(new Entry(13f, 6));
        HourValues.add(new Entry(14f, 6));
        HourValues.add(new Entry(15f, 7));
        HourValues.add(new Entry(16f, 7));
        HourValues.add(new Entry(17f, 7));
        HourValues.add(new Entry(18f, 7));
        HourValues.add(new Entry(19f, 6));
        HourValues.add(new Entry(20f, 5));
        HourValues.add(new Entry(21f, 3));
        HourValues.add(new Entry(22f, 3));
        HourValues.add(new Entry(23f, 2));

        LineDataSet HourSet = new LineDataSet(HourValues, "Units Consumed");
        HourSet.setDrawValues(false);
        HourSet.setFillAlpha(50);
        HourSet.setColor(Color.RED);
        HourSet.setLineWidth(3);
        ArrayList<ILineDataSet> dataset = new ArrayList<>();
        dataset.add(HourSet);

        Daily.getAxisRight().setEnabled(false);

        //Legend Config
        Legend legend = Daily.getLegend();
        legend.setEnabled(false);

        //Description Config
        Description description = Daily.getDescription();
        description.setEnabled(false);

        //yAxis Config
        YAxis yAxis = Daily.getAxisLeft();
        yAxis.setAxisMinimum(0f); // start at zero
        yAxis.setAxisMaximum(8f); // the axis maximum is 100
        yAxis.setGranularity(1f); // interval 1

        //xAxis Config
        XAxis xAxis = Daily.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f); // start at zero
        xAxis.setAxisMaximum(23f); // the axis maximum is 100
        xAxis.setGranularity(1f); // interval 1

        LineData data = new LineData(dataset);

        Daily.setData(data);
        return v;
    }
}
