package com.jakeesveld.flashstudyguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jakeesveld.flashstudyguide.getting_started.GettingStartedActivity;
import com.jakeesveld.flashstudyguide.main.MainActivity;

public class SplashScreenActivity extends AppCompatActivity {

    Button buttonSaved, buttonGettingStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        buttonSaved = findViewById(R.id.button_saved);
        buttonGettingStarted = findViewById(R.id.button_getting_started);


        buttonSaved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
            }
        });


        buttonGettingStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SplashScreenActivity.this, GettingStartedActivity.class));
            }
        });

    }
}
