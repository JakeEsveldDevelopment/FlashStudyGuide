package com.jakeesveld.flashstudyguide.main;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.jakeesveld.flashstudyguide.FlashApplication;
import com.jakeesveld.flashstudyguide.R;
import com.jakeesveld.flashstudyguide.data.QuizRepo;
import com.jakeesveld.flashstudyguide.model.Quiz;
import com.jakeesveld.flashstudyguide.newquiz.NewQuizActivity;
import com.jakeesveld.flashstudyguide.quizdetails.QuizDetailsActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityContract.view, MainSavedQuizAdapter.QuizIntentCallback {

    RecyclerView recyclerView;
    MainSavedQuizAdapter adapter;
    List<Quiz> quizList;
    QuizRepo repo;
    MainActivityContract.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        quizList = new ArrayList<>();
        repo = ((FlashApplication)this.getApplication()).getQuizRepo();
        presenter = new MainPresenter(repo, this);


        recyclerView = findViewById(R.id.recycler_view);
        adapter = new MainSavedQuizAdapter(quizList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, NewQuizActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getQuizList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void displayQuizList(List<Quiz> quizList) {
        this.quizList.clear();
        this.quizList.addAll(quizList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void activityIntentExecute(Quiz quiz) {

        //Wrap quiz selected in bundle and pass to quiz details activity

        Intent intent = new Intent(MainActivity.this, QuizDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(QuizDetailsActivity.QUIZ_KEY, quiz);
        intent.putExtra(QuizDetailsActivity.BUNDLE_KEY, bundle);
        startActivity(intent);
    }
}
