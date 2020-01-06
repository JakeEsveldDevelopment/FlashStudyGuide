package com.jakeesveld.flashstudyguide.newquiz;

import com.jakeesveld.flashstudyguide.model.Question;
import com.jakeesveld.flashstudyguide.model.Quiz;

import java.util.List;

public class NewQuizPresenter implements NewQuizContract.presenter {

    List<Question> questionList;
    NewQuizContract.view view;

    public NewQuizPresenter(List<Question> questionList, NewQuizContract.view view) {
        this.questionList = questionList;
        this.view = view;
    }

    @Override
    public void updateList(Question question) {
        questionList.add(question);
        view.updateView(questionList);
    }

    @Override
    public void saveQuiz(Quiz quiz) {

    }

    @Override
    public void deleteQuestion(Question question) {
        questionList.remove(question);
    }
}
