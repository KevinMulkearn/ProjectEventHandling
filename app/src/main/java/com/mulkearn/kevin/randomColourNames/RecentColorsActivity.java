package com.mulkearn.kevin.randomColourNames;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.widget.TextView;

public class RecentColorsActivity extends AppCompatActivity {

    TextView col1, col2, col3, col4, col5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_colors);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width * 0.3), (int)(height * 0.6));
        getWindow().setGravity(Gravity.LEFT | Gravity.TOP);

        col1 = (TextView) findViewById(R.id.col1);
        col2 = (TextView) findViewById(R.id.col2);
        col3 = (TextView) findViewById(R.id.col3);
        col4 = (TextView) findViewById(R.id.col4);
        col5 = (TextView) findViewById(R.id.col5);

        SharedPreferences sharedPref = getSharedPreferences("colors", Context.MODE_PRIVATE);
        String color0 = sharedPref.getString("color0", "#FFFFFF");
        String color1 = sharedPref.getString("color1", "#FFFFFF");
        String color2 = sharedPref.getString("color2", "#FFFFFF");
        String color3 = sharedPref.getString("color3", "#FFFFFF");
        String color4 = sharedPref.getString("color4", "#FFFFFF");

        col1.setText(color0);
        col1.setBackgroundColor(Color.parseColor(color0));
        col2.setText(color1);
        col2.setBackgroundColor(Color.parseColor(color1));
        col3.setText(color2);
        col3.setBackgroundColor(Color.parseColor(color2));
        col4.setText(color3);
        col4.setBackgroundColor(Color.parseColor(color3));
        col5.setText(color4);
        col5.setBackgroundColor(Color.parseColor(color4));
    }

}
