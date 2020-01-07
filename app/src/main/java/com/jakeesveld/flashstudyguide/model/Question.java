package com.jakeesveld.flashstudyguide.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Question {

    @PrimaryKey(autoGenerate = true)
    public long questionId;

    public String text, answer, hint;
    public int questionNumber, type;
    public long quizId;

    public static final int TYPE_MULTIPLE = 0;
    public static final int TYPE_BOOLEAN = 1;

    public Question(String text, String answer, String hint, int questionNumber, int type, long quizId) {
        this.text = text;
        this.answer = answer;
        this.hint = hint;
        this.questionNumber = questionNumber;
        this.type = type;
        this.quizId = quizId;
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

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }
}
