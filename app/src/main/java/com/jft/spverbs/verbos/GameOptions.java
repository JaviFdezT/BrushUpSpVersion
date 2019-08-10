package com.jft.spverbs.verbos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GameOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_options);

        List<ConjugatedForm> verbs = new LinkedList<ConjugatedForm>();
        DdBb myddbb = new DdBb(getApplicationContext());
        verbs = myddbb.getRandomVerb();
        int n=verbs.size();


        verbs = myddbb.getRandomVerb();
        n=verbs.size();
        /*if (n==0) {
            try {
                BufferedReader reader = new BufferedReader( new InputStreamReader(getAssets().open("ddbb.sql")));
                String mLine;
                while ((mLine = reader.readLine()) != null)
                    myddbb.runQuery(mLine);
                verbs = myddbb.getRandomVerb();
                n=verbs.size();
            } catch (Exception e2) {}
        }*/

        if (n<7) {
            Toast.makeText(this, "Too few verbs in the dictionary. Please, try again", 20).show();
            Intent activity = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(activity);
        }

        ArrayList<Integer> listrRandom1 = new ArrayList<Integer>();
        ArrayList<Integer> listrRandom2 = new ArrayList<Integer>();
        ConjugatedForm questionWord=null;
        ConjugatedForm falseWord1=null;
        ConjugatedForm falseWord2=null;
        ConjugatedForm falseWord3=null;
        ConjugatedForm falseWord4=null;
        ConjugatedForm falseWord5=null;
        try {
            for (int i=0; i<n; i++) {
                listrRandom1.add(new Integer(i));
                if (i<6) {
                    listrRandom2.add(new Integer(i));
                }
            }
            Collections.shuffle(listrRandom1);
            Collections.shuffle(listrRandom2);
            int person = listrRandom1.get(0);
            questionWord = verbs.get(listrRandom1.get(0));
            falseWord1 = verbs.get(listrRandom1.get(1));
            falseWord2 = verbs.get(listrRandom1.get(2));
            falseWord3 = verbs.get(listrRandom1.get(3));
            falseWord4 = verbs.get(listrRandom1.get(4));
            falseWord5 = verbs.get(listrRandom1.get(5));

            ArrayList<ConjugatedForm> questionList = new ArrayList<ConjugatedForm>();
            questionList.add(falseWord1);
            questionList.add(falseWord2);
            questionList.add(falseWord3);
            questionList.add(falseWord4);
            questionList.add(falseWord5);
            questionList.add(questionWord);
            Collections.shuffle(questionList);

            TextView textView0 = (TextView) findViewById(R.id.question0);
            TextView textView1 = (TextView) findViewById(R.id.question1);
            TextView textView2 = (TextView) findViewById(R.id.question2);
            Button p1_button = (Button)findViewById(R.id.buttoncheck1);
            Button p2_button = (Button)findViewById(R.id.buttoncheck2);
            Button p3_button = (Button)findViewById(R.id.buttoncheck3);
            Button p4_button = (Button)findViewById(R.id.buttoncheck4);
            Button p5_button = (Button)findViewById(R.id.buttoncheck5);
            Button p6_button = (Button)findViewById(R.id.buttoncheck6);

            String question0=" "+questionWord.getVerb().toUpperCase()+" ";
            String question1=" "+questionWord.getTense()+" ("+questionWord.getMood()+") ";
            String question2=null;

            String option1=null;
            String option2=null;
            String option3=null;
            String option4=null;
            String option5=null;
            String option6=null;
            if (person==0) {
                question2="1ª persona del singular";
                option1=questionList.get(0).getForm_1s();
                option2=questionList.get(1).getForm_1s();
                option3=questionList.get(2).getForm_1s();
                option4=questionList.get(3).getForm_1s();
                option5=questionList.get(4).getForm_1s();
                option6=questionList.get(5).getForm_1s();
            } else if (person==1) {
                question2="2ª persona del singular";
                option1=questionList.get(0).getForm_2s();
                option2=questionList.get(1).getForm_2s();
                option3=questionList.get(2).getForm_2s();
                option4=questionList.get(3).getForm_2s();
                option5=questionList.get(4).getForm_2s();
                option6=questionList.get(5).getForm_2s();
            } else if (person==2) {
                question2="3ª persona del singular";
                option1=questionList.get(0).getForm_3s();
                option2=questionList.get(1).getForm_3s();
                option3=questionList.get(2).getForm_3s();
                option4=questionList.get(3).getForm_3s();
                option5=questionList.get(4).getForm_3s();
                option6=questionList.get(5).getForm_3s();
            } else if (person==3) {
                question2="1ª persona del plural";
                option1=questionList.get(0).getForm_1p();
                option2=questionList.get(1).getForm_1p();
                option3=questionList.get(2).getForm_1p();
                option4=questionList.get(3).getForm_1p();
                option5=questionList.get(4).getForm_1p();
                option6=questionList.get(5).getForm_1p();
            } else if (person==4) {
                question2="2ª persona del plural";
                option1=questionList.get(0).getForm_2p();
                option2=questionList.get(1).getForm_2p();
                option3=questionList.get(2).getForm_2p();
                option4=questionList.get(3).getForm_2p();
                option5=questionList.get(4).getForm_2p();
                option6=questionList.get(5).getForm_2p();
            } else {
                question2="3ª persona del plural";
                option1=questionList.get(0).getForm_3p();
                option2=questionList.get(1).getForm_3p();
                option3=questionList.get(2).getForm_3p();
                option4=questionList.get(3).getForm_3p();
                option5=questionList.get(4).getForm_3p();
                option6=questionList.get(5).getForm_3p();
            }

            if ((option1.length()==0) || (option2.length()==0) || (option3.length()==0) || (option4.length()==0) || (option5.length()==0) || (option6.length()==0)) {
                Intent activity = new Intent(getApplicationContext(), GameOptions.class);
                startActivity(activity);
            }
            p1_button.setText(option1);
            p2_button.setText(option2);
            p3_button.setText(option3);
            p4_button.setText(option4);
            p5_button.setText(option5);
            p6_button.setText(option6);
            textView0.setText(question0);
            textView1.setText(question1);
            textView2.setText(question2);

            final Intent activity = new Intent(getApplicationContext(), GameCheck.class);
            activity.putExtra("rightWord",questionWord.getVerb());
            activity.putExtra("rightMood",questionWord.getMood());
            activity.putExtra("rightTense",questionWord.getTense());
            final int indexInList=questionList.indexOf(questionWord);
            boolean rightAnswer;
            p1_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (0==indexInList) {
                        activity.putExtra("rightAnswer",true);
                    } else {
                        activity.putExtra("rightAnswer",false);
                    }
                    startActivity(activity);
                    finish();
                }
            });
            p2_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (1==indexInList) {
                        activity.putExtra("rightAnswer",true);
                    } else {
                        activity.putExtra("rightAnswer",false);
                    }
                    startActivity(activity);
                    finish();
                }
            });
            p3_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (2==indexInList) {
                        activity.putExtra("rightAnswer",true);
                    } else {
                        activity.putExtra("rightAnswer",false);
                    }
                    startActivity(activity);
                    finish();
                }
            });
            p4_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (3==indexInList) {
                        activity.putExtra("rightAnswer",true);
                    } else {
                        activity.putExtra("rightAnswer",false);
                    }
                    startActivity(activity);
                    finish();
                }
            });
            p5_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (4==indexInList) {
                        activity.putExtra("rightAnswer",true);
                    } else {
                        activity.putExtra("rightAnswer",false);
                    }
                    startActivity(activity);
                    finish();
                }
            });
            p6_button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (5==indexInList) {
                        activity.putExtra("rightAnswer",true);
                    } else {
                        activity.putExtra("rightAnswer",false);
                    }
                    startActivity(activity);
                    finish();
                }
            });
        } catch (Exception e){
            Toast.makeText(this, "Too few verbs in the dictionary. Please, try again", 20).show();
            Intent activity = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(activity);
        }

    }
}
