package com.jakeesveld.flashstudyguide.quizdetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jakeesveld.flashstudyguide.FlashApplication;
import com.jakeesveld.flashstudyguide.R;
import com.jakeesveld.flashstudyguide.data.QuizRepo;
import com.jakeesveld.flashstudyguide.main.MainActivity;
import com.jakeesveld.flashstudyguide.model.Question;
import com.jakeesveld.flashstudyguide.model.Quiz;
import com.jakeesveld.flashstudyguide.newquiz.NewQuizActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizDetailsActivity extends AppCompatActivity implements View.OnClickListener, DeleteConfirmationFragment.DeleteConfirmationListener {

    public static final String BUNDLE_KEY = "data";
    public static final String QUIZ_KEY = "quiz";

    TextView textTitle, textDescription;
    RecyclerView recyclerView;
    Button buttonStart, buttonEdit, buttonDelete;

    DetailsQuestionAdapter adapter;
    Quiz quiz;
    List<Question> questionList;
    QuizRepo repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_details);

        textTitle = findViewById(R.id.text_quiz_title);
        textDescription = findViewById(R.id.text_description);
        recyclerView = findViewById(R.id.recyclerView);
        buttonStart = findViewById(R.id.button_start);
        buttonEdit = findViewById(R.id.button_edit);
        buttonDelete = findViewById(R.id.button_delete);

        repo = ((FlashApplication)this.getApplication()).getQuizRepo();

        questionList = new ArrayList<>();
        adapter = new DetailsQuestionAdapter(questionList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        buttonStart.setOnClickListener(this);
        buttonEdit.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
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

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button_start:

                break;
            case R.id.button_edit:
                Intent intent = new Intent(getBaseContext(), NewQuizActivity.class);
                Bundle args = new Bundle();
                args.putSerializable(NewQuizActivity.QUIZ_KEY, quiz);
                intent.putExtra("args", args);
                startActivity(intent);
                break;
            case R.id.button_delete:
                DialogFragment fragment = DeleteConfirmationFragment
                        .newInstance(quiz, QuizDetailsActivity.this, QuizDetailsActivity.this);
                fragment.show(getSupportFragmentManager(), "Delete");
                break;
        }
    }

    @Override
    public void onConfirmed(Quiz quiz, boolean confirmed) {
        if(confirmed) {
            repo.deleteQuiz(quiz);
            startActivity(new Intent(getBaseContext(), MainActivity.class));
        }
    }
}
