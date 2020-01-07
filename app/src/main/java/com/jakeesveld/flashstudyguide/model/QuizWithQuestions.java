package com.jakeesveld.flashstudyguide.model;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class QuizWithQuestions {
    @Embedded
    public Quiz quiz;
    @Relation(
            parentColumn = "quizId",
            entityColumn = "questionId")
    public List<Question> questions;

    public QuizWithQuestions(Quiz quiz, List<Question> questions) {
        this.quiz = quiz;
        this.questions = questions;
    }


    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
