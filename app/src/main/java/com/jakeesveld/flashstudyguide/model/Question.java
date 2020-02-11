package com.jakeesveld.flashstudyguide.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Question implements Serializable {

    public static final int TYPE_BOOLEAN = 1;
    public static final int TYPE_MULTIPLE = 2;
    public static final int TYPE_BOTH = 3;

    @PrimaryKey(autoGenerate = true)
    public long questionId;

    public String text, correct, hint;
    public String[] answers;
    public int questionNumber, type;
    public long quizId;

    public Question(String text, String correct, String hint, int questionNumber, int type, long quizId, String[] answers) {
        this.text = text;
        this.correct = correct;
        this.hint = hint;
        this.questionNumber = questionNumber;
        this.type = type;
        this.quizId = quizId;
    }


    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }
}
