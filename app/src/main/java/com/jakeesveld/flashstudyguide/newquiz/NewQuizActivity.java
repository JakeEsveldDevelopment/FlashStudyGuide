package com.jakeesveld.flashstudyguide.newquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.jakeesveld.flashstudyguide.R;
import com.jakeesveld.flashstudyguide.model.Question;

import java.util.ArrayList;
import java.util.List;

public class NewQuizActivity extends AppCompatActivity {

    EditText editName, editDescription;
    RadioButton radioMultiple, radioBoolean, radioBoth;
    Button buttonAddQuestion;
    RecyclerView recyclerQuestions;
    QuestionsAdapter adapter;
    List<Question> questionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_quiz);

        questionsList = new ArrayList<>();

        editName = findViewById(R.id.edit_name);
        editDescription = findViewById(R.id.edit_description);
        radioMultiple = findViewById(R.id.radio_multiple);
        radioBoolean = findViewById(R.id.radio_boolean);
        radioBoth = findViewById(R.id.radio_both);
        buttonAddQuestion = findViewById(R.id.button_add_question);
        recyclerQuestions = findViewById(R.id.recycler_questions);
        adapter = new QuestionsAdapter(questionsList);
        recyclerQuestions.setAdapter(adapter);
        recyclerQuestions.setLayoutManager(new LinearLayoutManager(this));



    }
}
