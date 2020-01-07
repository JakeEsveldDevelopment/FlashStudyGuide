package com.jakeesveld.flashstudyguide.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "quiz")
public class Quiz {

    @PrimaryKey(autoGenerate = true)
    public long quizId;

    private String name, description;
    @Ignore
    private List<Question> questions;
    private int type;

    @Ignore
    public Quiz(String name, String description, List<Question> questions, int type) {
        this.name = name;
        this.description = description;
        this.questions = questions;
        this.type = type;
    }

    public Quiz(String name, String description, int type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    @Ignore
    public Quiz(QuizWithQuestions quizWithQuestions){
        this.name = quizWithQuestions.getQuiz().getName();
        this.description = quizWithQuestions.getQuiz().getDescription();
        this.type = quizWithQuestions.getQuiz().getType();
        this.questions = quizWithQuestions.getQuestions();
    }

    @Ignore
    public Quiz() {
    }

    public long getQuizId() {
        return quizId;
    }

    public void setQuizId(long quizId) {
        this.quizId = quizId;
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
