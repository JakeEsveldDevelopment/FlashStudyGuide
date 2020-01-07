package com.jakeesveld.flashstudyguide.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.jakeesveld.flashstudyguide.model.Question;
import com.jakeesveld.flashstudyguide.model.Quiz;
import com.jakeesveld.flashstudyguide.model.QuizWithQuestions;

import java.util.List;

@Dao
public interface QuizDAO {

    @Insert
    void insertQuiz(Quiz quiz);

    @Transaction
    @Query("SELECT * FROM quiz")
    List<QuizWithQuestions> getAllQuizes();

    @Update
    void updateQuiz(Quiz quiz);

    @Delete
    int deleteQuiz(Quiz quiz);

    @Insert
    void insertQuestion(Question question);

    @Delete
    int deleteQuestion(Question question);
}
