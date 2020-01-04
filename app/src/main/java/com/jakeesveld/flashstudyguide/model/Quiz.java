package com.jakeesveld.flashstudyguide.model;

import java.util.List;

public class Quiz {

    private String name, description;
    private List<Question> questions;
    private int type;


    public Quiz(String name, String description, List<Question> questions, int type) {
        this.name = name;
        this.description = description;
        this.questions = questions;
        this.type = type;
    }

    public Quiz() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
