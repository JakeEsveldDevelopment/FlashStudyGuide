package com.jakeesveld.flashstudyguide;

import android.app.Application;

import com.jakeesveld.flashstudyguide.data.QuizRepo;

public class FlashApplication extends Application {
    private QuizRepo quizRepo;

    public QuizRepo getQuizRepo(){
        if(quizRepo == null){
            quizRepo = new QuizRepo(this);
        }
        return quizRepo;
    }
}
