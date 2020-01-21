package com.jakeesveld.flashstudyguide.data;

import android.app.Application;

import com.jakeesveld.flashstudyguide.model.Question;
import com.jakeesveld.flashstudyguide.model.Quiz;
import com.jakeesveld.flashstudyguide.model.QuizWithQuestions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class QuizRepo {
    private QuizDAO quizDAO;
    private List<Quiz> quizList;
    private CompositeDisposable compositeDisposable;

    public QuizRepo(Application application) {
        QuizRDB db = QuizRDB.getInstance(application);
        quizDAO = db.quizDAO();
        quizList = new ArrayList<>();
        compositeDisposable = new CompositeDisposable();
        Disposable disposable = quizDAO.getAllQuizes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<QuizWithQuestions>>() {
                    @Override
                    public void accept(List<QuizWithQuestions> quizWithQuestions) throws Exception {
                        convertToQuizFromDBObject(quizWithQuestions);
                    }
                });
        compositeDisposable.add(disposable);
    }

    public void getQuizList(final ListCallback callback) {
        if (quizList.size() == 0) {
            Disposable disposable = quizDAO.getAllQuizes()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<List<QuizWithQuestions>>() {
                        @Override
                        public void accept(List<QuizWithQuestions> quizWithQuestions) throws Exception {
                            convertToQuizFromDBObject(quizWithQuestions);
                            callback.execute(quizList);
                        }
                    });
            compositeDisposable.add(disposable);
        }
    }

    public void AddQuiz(Quiz quiz) {
        Disposable disposable = quizDAO.insertQuiz(quiz).subscribeOn(Schedulers.io()).subscribe();
        compositeDisposable.add(disposable);
        quizList.add(quiz);
    }

    public void updateQuiz(Quiz quiz) {
        Disposable disposable = quizDAO.updateQuiz(quiz).subscribeOn(Schedulers.io()).subscribe();
        compositeDisposable.add(disposable);
    }

    public void addQuestion(Question question) {
        compositeDisposable.add(
                quizDAO.insertQuestion(question)
                        .subscribeOn(Schedulers.io())
                        .subscribe());
    }

    public void deleteQuestion(Question question) {
        compositeDisposable.add(
                quizDAO.deleteQuestion(question)
                        .subscribeOn(Schedulers.io())
                        .subscribe());
    }

    private void convertToQuizFromDBObject(List<QuizWithQuestions> quizWithQuestions) {
        for (QuizWithQuestions quizDBObject : quizWithQuestions) {
            quizList.add(new Quiz(quizDBObject));
        }
    }

    public interface ListCallback{
        void execute(List<Quiz> quizList);
    }
}
