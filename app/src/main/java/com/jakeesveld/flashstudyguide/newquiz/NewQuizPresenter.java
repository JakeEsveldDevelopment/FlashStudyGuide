package com.jakeesveld.flashstudyguide.newquiz;

import com.jakeesveld.flashstudyguide.data.QuizRepo;
import com.jakeesveld.flashstudyguide.model.Question;
import com.jakeesveld.flashstudyguide.model.Quiz;

import java.util.List;

public class NewQuizPresenter implements NewQuizContract.presenter {

    List<Question> questionList;
    NewQuizContract.view view;
    QuizRepo repo;

    public NewQuizPresenter(List<Question> questionList, NewQuizContract.view view, QuizRepo repo) {
        this.questionList = questionList;
        this.view = view;
        this.repo = repo;
    }

    @Override
    public void updateList(Question question) {
        questionList.add(question);
        view.updateView(questionList);
    }

    @Override
    public void saveQuiz(Quiz quiz) {
        repo.AddQuiz(quiz);
        for(Question question: quiz.getQuestions()){
            repo.addQuestion(question);
        }
    }

    @Override
    public void updateQuiz(Quiz quiz) {
        repo.updateQuiz(quiz);
    }

    @Override
    public void deleteQuestion(Question question) {
        questionList.remove(question);
    }
}
