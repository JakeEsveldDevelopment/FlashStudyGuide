package com.jakeesveld.flashstudyguide.newquiz;

import com.jakeesveld.flashstudyguide.model.Question;
import com.jakeesveld.flashstudyguide.model.Quiz;

import java.util.List;

public interface NewQuizContract {

    interface presenter {
        void updateList(Question question);

        void saveQuiz(Quiz quiz);

        void deleteQuestion(Question question);
    }

    interface view {
        void updateView(List<Question> questionList);
    }
}
