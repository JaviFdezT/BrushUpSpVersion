package com.jft.spverbs.verbos;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;



import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ShowStats extends AppCompatActivity {

    private HorizontalBarChart horizontalChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_show_stats);

        DdBb myddbb = new DdBb(getApplicationContext());
        Map<String, Float> stats=myddbb.getStatsTense();

        Iterator it = stats.entrySet().iterator();
        List<String> tensesName=new LinkedList<String>();
        ArrayList<BarEntry> entries = new ArrayList<>();
        Float n=0f;
        String tense;
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            tense=(String) pair.getKey();
            tense=tense.replace("imperativo","IMP");
            tense=tense.replace("subjuntivo","SUB");
            tense=tense.replace("indicativo","IND");
            tense=tense.replace("negativo","neg.");
            tense=tense.replace(" (IND)","");
            tense=tense.replace(" afirmativo","");
            tensesName.add(tense);
            entries.add(new BarEntry(n,(Float) pair.getValue()));
            n=n+1f;
            it.remove(); // avoids a ConcurrentModificationException
        }


        Button goback_button = (Button)findViewById(R.id.button_mainMenu);
        goback_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activity2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(activity2);
            }
        });

        horizontalChart = (HorizontalBarChart)findViewById(R.id.chart);
        BarDataSet barDataSet = new BarDataSet(entries, "ser");
        barDataSet.setBarBorderWidth(0.9f);
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData barData = new BarData(barDataSet);
        XAxis xAxis = horizontalChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        IndexAxisValueFormatter formatter = new IndexAxisValueFormatter(tensesName);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(formatter);

        horizontalChart.getLegend().setEnabled(false);
        horizontalChart.getDescription().setEnabled(false);
        horizontalChart.setTouchEnabled(false);
        horizontalChart.setExtraRightOffset(30f);
        horizontalChart.setData(barData);
        horizontalChart.getXAxis().setLabelCount(tensesName.size());
        horizontalChart.setFitBars(true);
        horizontalChart.animateXY(5000, 5000);
        horizontalChart.invalidate();
    }

}
