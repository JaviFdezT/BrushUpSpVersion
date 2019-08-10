package com.jft.spverbs.verbos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


import java.io.InputStream;
import java.text.DecimalFormat;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import android.content.res.AssetManager;

public class DdBb extends SQLiteOpenHelper {

    private Context mycontext;
    private android.content.res.Resources res;

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "com.jft.spverbs.verbos";

    public DdBb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mycontext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_VERBS_TABLE = "CREATE TABLE IF NOT EXISTS VERBS (verb varchar(255) NOT NULL, verb_eng varchar(255) NOT NULL, mood varchar(255) NOT NULL, tense varchar(255) NOT NULL, form_1s varchar(255) NOT NULL, form_2s varchar(255) NOT NULL, form_3s varchar(255) NOT NULL, form_1p varchar(255) NOT NULL, form_2p varchar(255) NOT NULL, form_3p varchar(255) NOT NULL, gerund varchar(255) NOT NULL, pastparticiple varchar(255) NOT NULL, level int NOT NULL,CONSTRAINT norepeat UNIQUE (verb,mood, tense) ON CONFLICT IGNORE) ";
        try {
            db.execSQL(CREATE_VERBS_TABLE);
            this.loadData(db);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS VERBS");
        this.onCreate(db);
    }

    public void loadData(SQLiteDatabase db){

        try {
            InputStream inputStream = mycontext.getResources().openRawResource(R.raw.ddbb);
            BufferedReader reader = new BufferedReader( new InputStreamReader(inputStream));
            String mLine;
            while ((mLine = reader.readLine()) != null)
                db.execSQL(mLine);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void runQuery(String query){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.execSQL(query);
        } catch (IOError e){}
        this.close();
    }

    public List<ConjugatedForm> getAllVerbs() {
        List<ConjugatedForm> verbs = new LinkedList<ConjugatedForm>();
        String query = "SELECT  * FROM VERBS";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        ConjugatedForm verb = null;
        if (cursor.moveToFirst()) {
            do {
                verb = new ConjugatedForm();
                verb.setVerb(cursor.getString(0));
                verb.setVerb_eng(cursor.getString(1));
                verb.setMood(cursor.getString(2));
                verb.setTense(cursor.getString(3));
                verb.setForm_1s(cursor.getString(4));
                verb.setForm_2s(cursor.getString(5));
                verb.setForm_3s(cursor.getString(6));
                verb.setForm_1p(cursor.getString(7));
                verb.setForm_2p(cursor.getString(8));
                verb.setForm_3p(cursor.getString(9));
                verb.setGerund(cursor.getString(10));
                verb.setPastparticiple(cursor.getString(11));
                verb.setLevel((Integer) Integer.parseInt(cursor.getString(12)));
                verbs.add(verb);
            } while (cursor.moveToNext());
        }
        cursor.close();
        this.close();
        return verbs;
    }

    public List<String> getInfinitives() {
        List<String> verbs = new LinkedList<String>();
        String query = "SELECT  DISTINCT verb FROM VERBS";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int n=0;
        if (cursor.moveToFirst()) {
            do {
                n++;
                System.out.println(cursor.getString(0));
                verbs.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        this.close();
        System.out.println(n);
        return verbs;
    }


    public List<ConjugatedForm> getRandomVerb() {
        List<ConjugatedForm> verbs = new LinkedList<ConjugatedForm>();
        String query1 = "SELECT  * FROM VERBS";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query1, null);
        List<String> infs = new LinkedList<String>();
        String infinitive = null;
        if (cursor.moveToFirst()) {
            do {
                infinitive=cursor.getString(0);
                infs.add(infinitive);
            } while (cursor.moveToNext());
        }
        cursor.close();
        List<String> infinitives = new LinkedList<String>(new HashSet<String>(infs));
        ArrayList<Integer> listrRandom1 = new ArrayList<Integer>();
        for (int i=0; i<infinitives.size(); i++) {
            listrRandom1.add(new Integer(i));
        }

        Collections.shuffle(listrRandom1);
        String questionWord=null;
        try {
            questionWord = infinitives.get(listrRandom1.get(0));
        } catch (IndexOutOfBoundsException e2) {
            return verbs;
        }

        String query2 = "SELECT  * FROM VERBS WHERE verb='"+questionWord+"';";
        cursor = db.rawQuery(query2, null);
        ConjugatedForm verb = null;
        if (cursor.moveToFirst()) {
            do {
                verb = new ConjugatedForm();
                verb.setVerb(cursor.getString(0));
                verb.setVerb_eng(cursor.getString(1));
                verb.setMood(cursor.getString(2));
                verb.setTense(cursor.getString(3));
                verb.setForm_1s(cursor.getString(4));
                verb.setForm_2s(cursor.getString(5));
                verb.setForm_3s(cursor.getString(6));
                verb.setForm_1p(cursor.getString(7));
                verb.setForm_2p(cursor.getString(8));
                verb.setForm_3p(cursor.getString(9));
                verb.setGerund(cursor.getString(10));
                verb.setPastparticiple(cursor.getString(11));
                verb.setLevel((Integer) Integer.parseInt(cursor.getString(12)));
                verbs.add(verb);
            } while (cursor.moveToNext());
        }
        cursor.close();
        this.close();
        return verbs;
    }


    public ConjugatedForm getVerb(String verbSpanish, String mood, String tense) {
        List<ConjugatedForm> verbs = new LinkedList<ConjugatedForm>();
        String query2 = "SELECT  * FROM VERBS WHERE verb='"+verbSpanish+"' and mood='"+mood+"' and tense='"+tense+"';";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query2, null);
        ConjugatedForm verb = null;
        if (cursor.moveToFirst()) {
            do {
                verb = new ConjugatedForm();
                verb.setVerb(cursor.getString(0));
                verb.setVerb_eng(cursor.getString(1));
                verb.setMood(cursor.getString(2));
                verb.setTense(cursor.getString(3));
                verb.setForm_1s(cursor.getString(4));
                verb.setForm_2s(cursor.getString(5));
                verb.setForm_3s(cursor.getString(6));
                verb.setForm_1p(cursor.getString(7));
                verb.setForm_2p(cursor.getString(8));
                verb.setForm_3p(cursor.getString(9));
                verb.setGerund(cursor.getString(10));
                verb.setPastparticiple(cursor.getString(11));
                verb.setLevel((Integer) Integer.parseInt(cursor.getString(12)));
                verbs.add(verb);
            } while (cursor.moveToNext());
        }
        cursor.close();
        this.close();
        verb=verbs.get(0);
        return verb;
    }

    public List<ConjugatedForm> getVerb(String verbSpanish) {
        List<ConjugatedForm> verbs = new LinkedList<ConjugatedForm>();
        String query2 = "SELECT  * FROM VERBS WHERE verb='"+verbSpanish+"';";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query2, null);
        ConjugatedForm verb = null;
        if (cursor.moveToFirst()) {
            do {
                verb = new ConjugatedForm();
                verb.setVerb(cursor.getString(0));
                verb.setVerb_eng(cursor.getString(1));
                verb.setMood(cursor.getString(2));
                verb.setTense(cursor.getString(3));
                verb.setForm_1s(cursor.getString(4));
                verb.setForm_2s(cursor.getString(5));
                verb.setForm_3s(cursor.getString(6));
                verb.setForm_1p(cursor.getString(7));
                verb.setForm_2p(cursor.getString(8));
                verb.setForm_3p(cursor.getString(9));
                verb.setGerund(cursor.getString(10));
                verb.setPastparticiple(cursor.getString(11));
                verb.setLevel((Integer) Integer.parseInt(cursor.getString(12)));
                verbs.add(verb);
            } while (cursor.moveToNext());
        }
        cursor.close();
        this.close();
        return verbs;
    }

    public void updateLevel(String verbSpanish, String mood, String tense, int newLevel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String level = new Integer(newLevel).toString();
        String UPDATE_TABLE = "UPDATE VERBS SET level="+level+" WHERE verb='"+verbSpanish+"' and mood='"+mood+"' and tense='"+tense+"';";
        try {
            db.execSQL(UPDATE_TABLE);
        } catch (Exception e) {}
        this.close();
    }



    public Map<String, Float> getStatsTense() {
        Map<String, Float> finalstats = new HashMap<String, Float>();
        Map<String, Integer> stats = new HashMap<String, Integer>();
        Map<String, Integer> total = new HashMap<String, Integer>();
        String query = "SELECT  * FROM VERBS";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        String key=null;
        int value1;
        int value2;
        Integer n = 0;
        if (cursor.moveToFirst()) {
            do {
                n++;
                key=cursor.getString(3)+" ("+cursor.getString(2)+")";
                if (stats.get(key) != null) {
                    value1=stats.get(key)+(Integer) Integer.parseInt(cursor.getString(12))-1;
                } else {
                    value1=(Integer) Integer.parseInt(cursor.getString(12))-1;
                }
                if (total.get(key) != null) {
                    value2=total.get(key)+9;
                } else {
                    value2=9;
                }
                stats.put(key, value1);
                total.put(key, value2);
            } while (cursor.moveToNext());
        }


        Iterator it = stats.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            key=(String) pair.getKey();
            value1=(int) pair.getValue();
            value2=total.get(key);
            float value=(float) 100 * value1 / value2;
            finalstats.put(key, value);
            it.remove(); // avoids a ConcurrentModificationException
        }

        //finalstats.put("total", (float) n);
        cursor.close();
        this.close();
        return finalstats;
    }



}
