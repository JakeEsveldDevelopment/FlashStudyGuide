package com.jakeesveld.flashstudyguide.model;

public class Question {

    private String text, answer, hint;
    private int id;

    public Question(String text, String answer, String hint, int id) {
        this.text = text;
        this.answer = answer;
        this.hint = hint;
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
