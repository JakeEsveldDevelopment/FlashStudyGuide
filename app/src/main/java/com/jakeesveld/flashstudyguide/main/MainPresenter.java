package com.jakeesveld.flashstudyguide.main;

import com.jakeesveld.flashstudyguide.data.QuizRepo;
import com.jakeesveld.flashstudyguide.model.Quiz;

import java.util.List;

public class MainPresenter implements MainActivityContract.presenter{
    QuizRepo repo;
    MainActivityContract.view view;

    public MainPresenter(QuizRepo repo, MainActivityContract.view view) {
        this.repo = repo;
        this.view = view;
    }

    @Override
    public void getQuizList() {
        repo.getQuizList(new QuizRepo.ListCallback() {
            @Override
            public void execute(List<Quiz> quizList) {
                view.displayQuizList(quizList);
            }
        });
    }
}
