package com.jakeesveld.flashstudyguide.data;

import android.app.Application;

import com.jakeesveld.flashstudyguide.model.Question;
import com.jakeesveld.flashstudyguide.model.Quiz;
import com.jakeesveld.flashstudyguide.model.QuizWithQuestions;

import java.util.List;

public class QuizRepo {
    private QuizDAO quizDAO;
    private List<Quiz> quizList;

    public QuizRepo(Application application){
        QuizRDB db = QuizRDB.getInstance(application);
        quizDAO = db.quizDAO();
        List<QuizWithQuestions> quizWithQuestions = quizDAO.getAllQuizes();
        convertToQuizFromDBObject(quizWithQuestions);
    }

    public List<Quiz> getQuizList(){
        if(quizList == null){
            List<QuizWithQuestions> quizDBList = quizDAO.getAllQuizes();
            for(QuizWithQuestions quizDB: quizDBList){
                quizList.add(new Quiz(quizDB));
            }
        }
        return quizList;
    }

    public void AddQuiz(Quiz quiz){
        quizDAO.insertQuiz(quiz);
        quizList.add(quiz);
    }

    public List<Quiz> updateQuiz(Quiz quiz){
        quizDAO.updateQuiz(quiz);
        quizList.clear();
        convertToQuizFromDBObject(quizDAO.getAllQuizes());
        return quizList;
    }

    public void addQuestion(Question question){
        quizDAO.insertQuestion(question);
    }

    public void deleteQuestion(Question question){
        quizDAO.deleteQuestion(question);
    }

    private void convertToQuizFromDBObject(List<QuizWithQuestions> quizWithQuestions) {
        for(QuizWithQuestions quizDBObject: quizWithQuestions){
            quizList.add(new Quiz(quizDBObject));
        }
    }
}
