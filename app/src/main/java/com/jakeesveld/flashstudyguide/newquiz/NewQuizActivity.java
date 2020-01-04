package com.jakeesveld.flashstudyguide.newquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.jakeesveld.flashstudyguide.R;

public class NewQuizActivity extends AppCompatActivity {

    EditText editName, editDescription;
    RadioButton radioMultiple, radioBoolean, radioBoth;
    Button buttonNext;
    RecyclerView recyclerQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_quiz);

        editName = findViewById(R.id.edit_name);
        editDescription = findViewById(R.id.edit_description);
        radioMultiple = findViewById(R.id.radio_multiple);
        radioBoolean = findViewById(R.id.radio_boolean);
        radioBoth = findViewById(R.id.radio_both);
        buttonNext = findViewById(R.id.button_next);
        recyclerQuestions = findViewById(R.id.recycler_questions);



    }
}
