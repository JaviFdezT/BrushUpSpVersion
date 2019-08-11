package com.jft.spverbs.verbos;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameCheck extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_check);

        Intent intent = getIntent();
        String rightWord = intent.getStringExtra("rightWord");
        String rightMood = intent.getStringExtra("rightMood");
        String rightTense = intent.getStringExtra("rightTense");
        String person = intent.getStringExtra("rightPerson");
        System.out.println(person);
        Integer rightPerson = Integer.parseInt(person);
        boolean rightAnswer = intent.getExtras().getBoolean("rightAnswer");
        int newLevel=1;
        String color1;
        String color2;
        if (rightAnswer) {
            color1="#00FF00";
            color2="#254117";
        } else {
            color1="#F9966B";
            color2="#990012";
        }

        TextView textView0 = (TextView) findViewById(R.id.answer);
        TextView textView1 = (TextView) findViewById(R.id.verb);
        TextView textView2 = (TextView) findViewById(R.id.tense);
        TextView textView3 = (TextView) findViewById(R.id.p1s);
        TextView textView4 = (TextView) findViewById(R.id.p2s);
        TextView textView5 = (TextView) findViewById(R.id.p3s);
        TextView textView6 = (TextView) findViewById(R.id.p1p);
        TextView textView7 = (TextView) findViewById(R.id.p2p);
        TextView textView8 = (TextView) findViewById(R.id.p3p);
        TextView textView9 = (TextView) findViewById(R.id.meaning);


        DdBb myddbb = new DdBb(getApplicationContext());
        ConjugatedForm verb=myddbb.getVerb(rightWord,rightMood,rightTense);

        try {
            if (rightAnswer) {
                textView0.setText("¡Correcto!");
                newLevel = 1;
                myddbb.updateLevel(rightWord,rightMood,rightTense,newLevel);
            } else {
                textView0.setText("¡Incorrecto!");
                newLevel = 0;
                myddbb.updateLevel(rightWord,rightMood,rightTense,newLevel);
            }

            textView1.setText(rightWord.toUpperCase());
            textView2.setText(" "+rightTense+" ("+rightMood+") ");
            textView3.setText("(yo) "+verb.getForm_1s());
            textView4.setText("(tú) "+verb.getForm_2s());
            textView5.setText("(él/ella) "+verb.getForm_3s());
            textView6.setText("(nosotr@s) "+verb.getForm_1p());
            textView7.setText("(vosotr@s) "+verb.getForm_2p());
            textView8.setText("(ell@s) "+verb.getForm_3p());
            textView9.setText("("+verb.getVerb_eng()+")");

            if (rightPerson==0) {
                textView3.setBackgroundColor(Color.parseColor(color1));
            } else if (rightPerson==1) {
                textView4.setBackgroundColor(Color.parseColor(color1));
            } else if (rightPerson==2) {
                textView5.setBackgroundColor(Color.parseColor(color1));
            } else if (rightPerson==3) {
                textView6.setBackgroundColor(Color.parseColor(color1));
            } else if (rightPerson==4) {
                textView7.setBackgroundColor(Color.parseColor(color1));
            } else {
                textView8.setBackgroundColor(Color.parseColor(color1));
            }
            if (rightAnswer) {
                textView0.setTextColor(Color.parseColor(color2));
            } else {
                textView0.setTextColor(Color.parseColor(color2));
            }

        } catch (Exception e) {
            Intent activity2 = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(activity2);
        }



        Button cont_button = (Button)findViewById(R.id.button_cont);
        Button goback_button = (Button)findViewById(R.id.button_mainMenu);
        cont_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activity1 = new Intent(getApplicationContext(), GameOptions.class);
                startActivity(activity1);
            }
        });
        goback_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activity2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(activity2);
            }
        });

    }
}
