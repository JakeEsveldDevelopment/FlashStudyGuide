package com.jakeesveld.flashstudyguide.main;

import com.jakeesveld.flashstudyguide.data.QuizRepo;

public class MainPresenter implements MainActivityContract.presenter{
    QuizRepo repo;
    MainActivityContract.view view;

    public MainPresenter(QuizRepo repo, MainActivityContract.view view) {
        this.repo = repo;
        this.view = view;
    }

    @Override
    public void getQuizList() {
        view.displayQuizList(repo.getQuizList());
    }
}
