package com.jakeesveld.flashstudyguide.newquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.snackbar.Snackbar;
import com.jakeesveld.flashstudyguide.R;
import com.jakeesveld.flashstudyguide.model.Question;

import java.util.ArrayList;
import java.util.List;

public class NewQuizActivity extends AppCompatActivity implements NewQuestionFragment.OnFragmentInteractionListener{

    public static final String TYPE_BOOLEAN = "boolean";
    public static final String TYPE_MULTIPLE = "multiple";
    public static final String TYPE_BOTH = "both";
    public static final String NEW_QUESTION_FRAGMENT_TAG = "newQuestion";


    EditText editName, editDescription;
    RadioButton radioMultiple, radioBoolean, radioBoth;
    Button buttonAddQuestion;
    RecyclerView recyclerQuestions;
    QuestionsAdapter adapter;
    List<Question> questionsList;
    DialogFragment newQuestionFragment;
    RadioGroup radioGroupType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_quiz);

        questionsList = new ArrayList<>();
        newQuestionFragment = new NewQuestionFragment();

        editName = findViewById(R.id.edit_name);
        editDescription = findViewById(R.id.edit_description);
        radioMultiple = findViewById(R.id.radio_multiple);
        radioBoolean = findViewById(R.id.radio_boolean);
        radioBoth = findViewById(R.id.radio_both);
        buttonAddQuestion = findViewById(R.id.button_add_question);
        recyclerQuestions = findViewById(R.id.recycler_questions);
        radioGroupType = findViewById(R.id.radio_group_type);
        adapter = new QuestionsAdapter(questionsList);
        recyclerQuestions.setAdapter(adapter);
        recyclerQuestions.setLayoutManager(new LinearLayoutManager(this));

        buttonAddQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkTypeSelected()) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(NewQuestionFragment.newInstance(getQuestionType()), NEW_QUESTION_FRAGMENT_TAG)
                            .addToBackStack(null)
                            .commit();
                }else{
                    Snackbar.make(view, "Please select question type to continue", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean checkTypeSelected() {
        return radioGroupType.getCheckedRadioButtonId() != 0;
    }

    private String getQuestionType() {
        switch(radioGroupType.getCheckedRadioButtonId()){
            case R.id.radio_boolean:
                return TYPE_BOOLEAN;
            case R.id.radio_multiple:
                return TYPE_MULTIPLE;
            case R.id.radio_both:
                return TYPE_BOTH;
        }
        return null;
    }

    @Override
    public void onQuestionCompleted(Question question) {

    }
}
