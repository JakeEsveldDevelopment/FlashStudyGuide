package com.jakeesveld.flashstudyguide.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.jakeesveld.flashstudyguide.R;
import com.jakeesveld.flashstudyguide.model.Question;
import com.jakeesveld.flashstudyguide.model.Quiz;

public class QuizActivity extends AppCompatActivity implements QuizContract.view {

    public static final String QUIZ_KEY = "quiz";
    private QuizContract.presenter presenter;

    private TextView textTitle, textQuestion, textCounter;
    private RadioGroup radioGroupBoolean, radioGroupMultiple;
    private RadioButton radioTrue, radioFalse, radioA, radioB, radioC, radioD;
    private Button buttonSubmit;
    private Quiz quiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textTitle = findViewById(R.id.text_title);
        textQuestion = findViewById(R.id.text_question);
        textCounter = findViewById(R.id.text_counter);
        radioGroupBoolean = findViewById(R.id.radio_group_boolean);
        radioGroupMultiple = findViewById(R.id.radio_group_multiple);
        radioTrue = findViewById(R.id.radio_true);
        radioFalse = findViewById(R.id.radio_false);
        radioA = findViewById(R.id.radio_a);
        radioB = findViewById(R.id.radio_b);
        radioC = findViewById(R.id.radio_c);
        radioD = findViewById(R.id.radio_d);
        buttonSubmit = findViewById(R.id.button_submit);


        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(QUIZ_KEY)) {
            quiz = (Quiz) getIntent().getSerializableExtra(QUIZ_KEY);
        }
        presenter = new QuizPresenter(this, quiz);
        initializeView();


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkValidInput()) {
                    presenter.submitQuestion(getAnswer());
                }else{
                    Snackbar snack = Snackbar.make(view, "Please select an answer", Snackbar.LENGTH_SHORT);
                    snack.getView().setBackgroundColor(Color.WHITE);
                    snack.show();
                }
            }
        });


    }

    private String getAnswer() {
        if (radioGroupBoolean.getVisibility() == View.VISIBLE) {
            return radioTrue.isChecked() ? "true" : "false";
        } else {
            switch (radioGroupMultiple.getCheckedRadioButtonId()) {
                case R.id.radio_a:
                    return radioA.getText().toString();
                case R.id.radio_b:
                    return radioB.getText().toString();
                case R.id.radio_c:
                    return radioC.getText().toString();
                case R.id.radio_d:
                    return radioD.getText().toString();
                default:
                    return null;
            }
        }
    }

    private boolean checkValidInput() {
        return radioTrue.isChecked() ||
                radioFalse.isChecked() ||
                radioA.isChecked() ||
                radioB.isChecked() ||
                radioC.isChecked() ||
                radioD.isChecked();
    }

    // initial set up of view with quiz data
    private void initializeView() {
        textTitle.setText(quiz.getName());
        Question question = quiz.getQuestions().get(0);
        textQuestion.setText(question.getText());
        updateAnswers(question.getType(), question.getAnswers());

    }

    private void updateAnswers(int type, String[] answers) {
        switch (type) {
            case Question.TYPE_BOOLEAN:
                radioGroupBoolean.setVisibility(View.VISIBLE);
                radioGroupMultiple.setVisibility(View.INVISIBLE);
                break;
            case Question.TYPE_MULTIPLE:
                radioGroupMultiple.setVisibility(View.VISIBLE);
                radioGroupBoolean.setVisibility(View.INVISIBLE);
                radioA.setText(String.format("A: %s", answers[0]));
                radioB.setText(String.format("B: %s", answers[1]));
                radioC.setText(String.format("C: %s", answers[2]));
                radioD.setText(String.format("D: %s", answers[3]));
        }
    }

    @Override
    public void updateView(String question, int type, String[] answers, int counter) {
        textQuestion.setText(question);
        textCounter.setText(String.format("Question %s", String.valueOf(counter)));
        updateAnswers(type, answers);
    }
}
