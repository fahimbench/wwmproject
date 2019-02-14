package com.bgeiotdev.eval.Play;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bgeiotdev.eval.R;

public class PlaySuite extends AppCompatActivity {

    TextView timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_suite);

        long time = getIntent().getLongExtra("timer", 0);

        timer = findViewById(R.id.timer);

        timer.setText(""+time);


    }
}
