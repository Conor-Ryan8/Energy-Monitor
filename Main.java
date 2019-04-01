package com.example.energymonitor;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        BottomNavigationView nav = findViewById(R.id.navigation_bar);
        nav.setOnNavigationItemSelectedListener(navigation_listener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Now()).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navigation_listener =
            new BottomNavigationView.OnNavigationItemSelectedListener()
    {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            Fragment selectedFragment = null;
            switch (item.getItemId())
            {
                case R.id.navigation_now:
                    selectedFragment = new Now();
                    break;

                case R.id.navigation_daily:
                    selectedFragment = new Day();
                    break;

                case R.id.navigation_weekly:
                    selectedFragment = new Week();
                    break;

                case R.id.navigation_monthly:
                    selectedFragment = new Month();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    selectedFragment).commit();
            return true;
        }
    };
}
