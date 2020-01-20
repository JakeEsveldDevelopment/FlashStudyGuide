package com.jakeesveld.flashstudyguide.newquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.snackbar.Snackbar;
import com.jakeesveld.flashstudyguide.FlashApplication;
import com.jakeesveld.flashstudyguide.R;
import com.jakeesveld.flashstudyguide.main.MainActivity;
import com.jakeesveld.flashstudyguide.model.Question;
import com.jakeesveld.flashstudyguide.model.Quiz;

import java.util.ArrayList;
import java.util.List;

public class NewQuizActivity extends AppCompatActivity implements NewQuestionFragment.OnFragmentInteractionListener, NewQuizContract.view{

    public static final int TYPE_BOOLEAN = 1;
    public static final int TYPE_MULTIPLE = 2;
    public static final int TYPE_BOTH = 3;
    public static final String NEW_QUESTION_FRAGMENT_TAG = "newQuestion";

    NewQuizContract.presenter presenter;
    Quiz quiz;
    Context context;

    EditText editName, editDescription;
    RadioButton radioMultiple, radioBoolean, radioBoth;
    Button buttonAddQuestion, buttonSubmitQuiz;
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
        context = this;
        presenter = new NewQuizPresenter(questionsList, this, ((FlashApplication)this.getApplication()).getQuizRepo());
        quiz = new Quiz();

        editName = findViewById(R.id.edit_name);
        editDescription = findViewById(R.id.edit_description);
        radioMultiple = findViewById(R.id.radio_group_multiple);
        radioBoolean = findViewById(R.id.radio_boolean);
        radioBoth = findViewById(R.id.radio_both);
        buttonAddQuestion = findViewById(R.id.button_add_question);
        buttonSubmitQuiz = findViewById(R.id.button_submit_quiz);
        recyclerQuestions = findViewById(R.id.recycler_questions);
        radioGroupType = findViewById(R.id.radio_group_type);
        adapter = new QuestionsAdapter(questionsList);
        recyclerQuestions.setAdapter(adapter);
        recyclerQuestions.setLayoutManager(new LinearLayoutManager(this));

        buttonAddQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //If type of question has been selected, show new question fragment
                
                if(checkTypeSelected()) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(NewQuestionFragment.newInstance(getQuestionType(), questionsList.size(), quiz.getQuizId()), NEW_QUESTION_FRAGMENT_TAG)
                            .addToBackStack(null)
                            .commit();
                }else{
                    Snackbar.make(view, "Please select question type to continue", Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        buttonSubmitQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //If quiz has name and at least one question, submit. Else show warning

                if(checkFieldsValid()){
                    submitQuiz();
                }else{
                    Snackbar.make(view,
                            "Please name quiz and give at least one question before submitting",
                            Snackbar.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void submitQuiz() {
        Quiz quiz = new Quiz(
                editName.getText().toString(),
                editDescription.getText().toString(),
                questionsList,
                getQuestionType()
        );
        presenter.saveQuiz(quiz);
        startActivity(new Intent(NewQuizActivity.this, MainActivity.class));
    }

    private boolean checkFieldsValid() {
        if(!editName.getText().toString().equals("") && questionsList.size() > 0){
            return true;
        }
        return false;
    }

    private boolean checkTypeSelected() {
        return radioGroupType.getCheckedRadioButtonId() != -1;
    }

    private int getQuestionType() {
        switch(radioGroupType.getCheckedRadioButtonId()){
            case R.id.radio_boolean:
                return TYPE_BOOLEAN;
            case R.id.radio_group_multiple:
                return TYPE_MULTIPLE;
            case R.id.radio_both:
                return TYPE_BOTH;
        }
        return 0;
    }

    @Override
    public void onQuestionCompleted(Question question) {
        presenter.updateList(question);
    }

    @Override
    public void updateView(List<Question> questionList) {
        this.questionsList = questionList;
        adapter.notifyDataSetChanged();
    }
}
