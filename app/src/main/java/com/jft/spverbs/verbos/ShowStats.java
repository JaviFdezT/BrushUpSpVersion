package com.jft.spverbs.verbos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ShowStats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_stats);

        DdBb myddbb = new DdBb(getApplicationContext());
        Map<String, Float> stats=myddbb.getStatsTense();

        /*if (stats.size()==0) {
            try {
                BufferedReader reader = new BufferedReader( new InputStreamReader(getAssets().open("ddbb.sql")));
                String mLine;
                while ((mLine = reader.readLine()) != null)
                    myddbb.runQuery(mLine);
                stats=myddbb.getStatsTense();
            } catch (Exception e2) {
                Intent activity2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(activity2);
            }
        }*/

        Iterator it = stats.entrySet().iterator();
        List<String> tenses=new LinkedList<String>();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            tenses.add(pair.getKey()+": "+String.format("%.02f", pair.getValue())+" %");
            it.remove(); // avoids a ConcurrentModificationException
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_listview1, tenses) {
            @Override
            public boolean isEnabled(int position) {
                return false;
            }
        };
        ListView listView = (ListView) findViewById(R.id.listviewstats);
        listView.setAdapter(adapter);

        Button goback_button = (Button)findViewById(R.id.button_mainMenu);
        goback_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activity2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(activity2);
            }
        });

    }

}
