package com.example.energymonitor;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Now extends Fragment
{
    TextView TVCurrentLoad;
    TextView TVUsageDay;
    TextView TVUsageWeek;
    TextView TVUsageMonth;
    TextView TVUsageBill;
    TextView TVCostDay;
    TextView TVCostWeek;
    TextView TVCostMonth;
    TextView TVCostBill;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        //Dummy Data
        int CurrentLoad = 1234;
        int UsageDay = 43;
        int UsageWeek = 129;
        int UsageMonth = 341;
        int UsageBill = 556;
        double CostDay = 6.88;
        double CostWeek = 20.64;
        double CostMonth = 54.56;
        double CostBill = 90.65;

        return inflater.inflate(R.layout.now, container, false);
    }
}
