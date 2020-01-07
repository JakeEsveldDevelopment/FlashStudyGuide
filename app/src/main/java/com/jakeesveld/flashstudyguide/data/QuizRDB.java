package com.jakeesveld.flashstudyguide.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.jakeesveld.flashstudyguide.model.Question;
import com.jakeesveld.flashstudyguide.model.Quiz;

@Database(entities = {Quiz.class, Question.class}, version = 1, exportSchema = false)
public abstract class QuizRDB extends RoomDatabase {

    private static volatile QuizRDB INSTANCE;
    public abstract QuizDAO quizDAO();

    public static QuizRDB getInstance(final Context context){
        if(INSTANCE == null){
            synchronized (QuizRDB.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        QuizRDB.class,
                        "QuizRoomDatabase").build();
            }
        }
        return INSTANCE;
    }
}
