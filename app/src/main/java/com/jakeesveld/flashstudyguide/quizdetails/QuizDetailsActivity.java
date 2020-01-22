package com.jakeesveld.flashstudyguide.quizdetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.jakeesveld.flashstudyguide.R;
import com.jakeesveld.flashstudyguide.model.Question;
import com.jakeesveld.flashstudyguide.model.Quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizDetailsActivity extends AppCompatActivity {

    public static final String BUNDLE_KEY = "data";
    public static final String QUIZ_KEY = "quiz";

    TextView textTitle, textDescription;
    RecyclerView recyclerView;
    Button buttonStart, buttonEdit, buttonDelete;

    Quiz quiz;
    List<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_details);

        textTitle = findViewById(R.id.text_title);
        textDescription = findViewById(R.id.text_description);
        recyclerView = findViewById(R.id.recyclerView);
        buttonStart = findViewById(R.id.button_start);
        buttonEdit = findViewById(R.id.button_edit);
        buttonDelete = findViewById(R.id.button_delete);

        questionList = new ArrayList<>();

        // Get Quiz passed by intent creating activity
        Intent creationIntent = getIntent();
        Bundle bundle = creationIntent.getBundleExtra(BUNDLE_KEY);
        if (bundle != null) {
            quiz = (Quiz) bundle.getSerializable(QUIZ_KEY);
        }

        if(quiz != null){
            populateUI();
        }


    }

    private void populateUI() {
        textTitle.setText(quiz.getName());
        textDescription.setText(
                quiz.getDescription().equals("") ?
                        "No Description Available" :
                        quiz.getDescription()
        );

        questionList.addAll(quiz.getQuestions());

        //TODO: Update adapter'
    }
}
