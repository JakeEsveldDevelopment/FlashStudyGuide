package com.jakeesveld.flashstudyguide.quiz;

public interface QuizContract {

    interface view{
        void updateView(String question, int type, String[] answers, int counter);
    }

    interface presenter{
        void submitQuestion(String answer);
    }
}
