package com.jft.spverbs.verbos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = findViewById(R.id.buttonplay);
        Button button2 = findViewById(R.id.buttonstats);
        Button button4 = findViewById(R.id.buttondict);
        Button button5 = findViewById(R.id.buttoncredits);
        Button button6 = findViewById(R.id.buttonexit);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity = new Intent(getApplicationContext(), GameOptions.class);
                startActivity(activity);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity = new Intent(getApplicationContext(), ShowStats.class);
                startActivity(activity);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity = new Intent(getApplicationContext(), ShowDict.class);
                startActivity(activity);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity = new Intent(getApplicationContext(), ShowCredits.class);
                startActivity(activity);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory( Intent.CATEGORY_HOME );
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }
        });
    }
}
