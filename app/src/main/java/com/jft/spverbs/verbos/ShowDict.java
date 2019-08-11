package com.jft.spverbs.verbos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView.OnItemClickListener;

public class ShowDict extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private EditText inputSearch;
    private List<String> fullwords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_dict);

        DdBb myddbb = new DdBb(this);
        fullwords=myddbb.getInfinitives();

        final List<String> words=new ArrayList<String>();
        final List<Integer> absoluteTemporaryList=new ArrayList<Integer>();
        final List<Integer> mTemporaryList=new ArrayList<Integer>();
        for(int i = 0; i < fullwords.size(); ++i) {
            if (! words.contains(fullwords.get(i))){
                words.add(fullwords.get(i));
                mTemporaryList.add(i);
                absoluteTemporaryList.add(i);
            }
        }

        adapter = new ArrayAdapter<String>(this,R.layout.activity_listview1, words);
        listView = (ListView) findViewById(R.id.listview);

        listView.setAdapter(adapter);
        inputSearch = (EditText) findViewById(R.id.inputSearch);

        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int arg1, int arg2, int arg3) {
                adapter.getFilter().filter(charSequence);

                String searchWord = charSequence.toString().toLowerCase();
                if (words.size() > 0) {
                    if (searchWord.length() > 0) {
                        mTemporaryList.clear();
                        for (int i = 0; i < words.size(); i++) {
                            if ((words.get(i).startsWith(searchWord))||(words.get(i).contains(" "+searchWord))) {
                                mTemporaryList.add(i);
                            }
                        }
                    } else {
                        mTemporaryList.clear();
                        mTemporaryList.addAll(absoluteTemporaryList);
                    }
                }

            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub
            }
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
            }
        });

        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent activity = new Intent(getApplicationContext(), ShowVerb.class);
                try {
                    activity.putExtra("showVerb",fullwords.get(mTemporaryList.get(position)));
                    startActivity(activity);
                } catch (Exception e) {}
            }
        });


        Button button = findViewById(R.id.button_mainMenu_fromDict);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(activity);
            }
        });
    }



}
