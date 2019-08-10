package com.jft.spverbs.verbos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ShowVerbTense extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_verb_tense);

        Intent intent = getIntent();
        String showVerb = intent.getStringExtra("showVerb");
        String showMood = intent.getStringExtra("showMood");
        String showTense = intent.getStringExtra("showTense");

        DdBb myddbb = new DdBb(getApplicationContext());
        ConjugatedForm verb=myddbb.getVerb(showVerb,showMood,showTense);


        List<String> forms=new LinkedList<String>();
        forms.add(verb.getForm_1s());
        forms.add(verb.getForm_2s());
        forms.add(verb.getForm_3s());
        forms.add(verb.getForm_1p());
        forms.add(verb.getForm_2p());
        forms.add(verb.getForm_3p());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_listview1, forms) {
            @Override
            public boolean isEnabled(int position) {
                return false;
            }
        };
        ListView listView = (ListView) findViewById(R.id.listviewstats);
        listView.setAdapter(adapter);

        TextView textView1 = (TextView) findViewById(R.id.verb);
        TextView textView2 = (TextView) findViewById(R.id.tense);
        textView1.setText(showVerb.toUpperCase());
        textView2.setText(" "+showTense+" ("+showMood+") ");

        Button goback_button = (Button)findViewById(R.id.button_mainMenu);
        goback_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activity2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(activity2);
            }
        });

    }
}
