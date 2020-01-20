package com.jakeesveld.flashstudyguide.main;

import com.jakeesveld.flashstudyguide.model.Quiz;

import java.util.List;

public interface MainActivityContract {

    interface view{
        void displayQuizList(List<Quiz> quizList);

    }

    interface presenter{
        void getQuizList();

    }
}
