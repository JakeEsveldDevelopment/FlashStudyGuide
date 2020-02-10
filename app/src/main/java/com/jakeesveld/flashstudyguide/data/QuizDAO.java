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

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface QuizDAO {

    @Insert
    Completable insertQuiz(Quiz quiz);

    @Transaction
    @Query("SELECT * FROM quiz")
    Single<List<QuizWithQuestions>> getAllQuizes();

    @Update
    Completable updateQuiz(Quiz quiz);

    @Query("DELETE FROM quiz WHERE quizId = :quizId")
    Completable deleteQuiz(long quizId);

    @Insert
    Completable insertQuestion(Question question);

    @Delete
    Completable deleteQuestion(Question question);
}
