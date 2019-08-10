package com.jft.spverbs.verbos;

public class ConjugatedForm {

    private String verb;
    private String verb_eng;
    private String mood;
    private String tense;
    private String form_1s;
    private String form_2s;
    private String form_3s;
    private String form_1p;
    private String form_2p;
    private String form_3p;
    private String gerund;
    private String pastparticiple;
    private int level;

    public ConjugatedForm() {
    }

    public ConjugatedForm(String verb, String verb_eng, String mood, String tense, String form_1s, String form_2s, String form_3s, String form_1p, String form_2p, String form_3p, String gerund, String pastparticiple, int level) {
        this.verb = verb;
        this.verb_eng = verb_eng;
        this.mood = mood;
        this.tense = tense;
        this.form_1s = form_1s;
        this.form_2s = form_2s;
        this.form_3s = form_3s;
        this.form_1p = form_1p;
        this.form_2p = form_2p;
        this.form_3p = form_3p;
        this.gerund = gerund;
        this.pastparticiple = pastparticiple;
        this.level = level;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getVerb_eng() {
        return verb_eng;
    }

    public void setVerb_eng(String verb_eng) {
        this.verb_eng = verb_eng;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getTense() {
        return tense;
    }

    public void setTense(String tense) {
        this.tense = tense;
    }

    public String getForm_1s() {
        return form_1s;
    }

    public void setForm_1s(String form_1s) {
        this.form_1s = form_1s;
    }

    public String getForm_2s() {
        return form_2s;
    }

    public void setForm_2s(String form_2s) {
        this.form_2s = form_2s;
    }

    public String getForm_3s() {
        return form_3s;
    }

    public void setForm_3s(String form_3s) {
        this.form_3s = form_3s;
    }

    public String getForm_1p() {
        return form_1p;
    }

    public void setForm_1p(String form_1p) {
        this.form_1p = form_1p;
    }

    public String getForm_2p() {
        return form_2p;
    }

    public void setForm_2p(String form_2p) {
        this.form_2p = form_2p;
    }

    public String getForm_3p() {
        return form_3p;
    }

    public void setForm_3p(String form_3p) {
        this.form_3p = form_3p;
    }

    public String getGerund() {
        return gerund;
    }

    public void setGerund(String gerund) {
        this.gerund = gerund;
    }

    public String getPastparticiple() {
        return pastparticiple;
    }

    public void setPastparticiple(String pastparticiple) {
        this.pastparticiple = pastparticiple;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
