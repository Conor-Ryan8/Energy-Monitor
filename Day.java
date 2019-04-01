package com.example.energymonitor;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Day extends Fragment
{
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

        return v;
    }
}
