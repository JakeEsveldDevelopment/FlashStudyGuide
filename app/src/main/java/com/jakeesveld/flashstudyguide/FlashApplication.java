package com.jakeesveld.flashstudyguide;

import android.app.Application;

import com.jakeesveld.flashstudyguide.data.QuizRepo;

public class FlashApplication extends Application {
    private QuizRepo quizRepo;

    public QuizRepo getQuizRepo(){
        if(quizRepo == null){
            getRepoThread.start();
            try {
                getRepoThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return quizRepo;
    }

    private Thread getRepoThread = new Thread(new Runnable() {
        @Override
        public void run() {
            quizRepo = new QuizRepo(FlashApplication.this);
        }
    });
}
