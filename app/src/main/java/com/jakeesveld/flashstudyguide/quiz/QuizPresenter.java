package com.jakeesveld.flashstudyguide.quiz;

import com.jakeesveld.flashstudyguide.model.Quiz;

public class QuizPresenter implements QuizContract.presenter {

    private QuizContract.view view;
    private Quiz quiz;

    public QuizPresenter(QuizContract.view view, Quiz quiz) {
        this.view = view;
        this.quiz = quiz;
    }

    @Override
    public void submitQuestion(String answer) {

    }
}
