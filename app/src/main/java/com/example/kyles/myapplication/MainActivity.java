package com.example.kyles.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button playGame = findViewById(R.id.button);
        playGame.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(new GameView(MainActivity.this));
            }
        });
    }
}
