package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.myapplication.R;


public class MainActivity extends AppCompatActivity {
    private TextView timerLabel;
    private Button startPauseButton;
    private Button resetButton;
    private Handler handler;
    private int seconds;
    private boolean isPaused;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerLabel = findViewById(R.id.timerLabel);
        startPauseButton = findViewById(R.id.startPauseButton);
        resetButton = findViewById(R.id.resetButton);

        handler = new Handler();
        isPaused = true;

        startPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPaused) {
                    handler.post(runnable);
                    startPauseButton.setText("Pause");
                } else {
                    handler.removeCallbacks(runnable);
                    startPauseButton.setText("Start");
                }
                isPaused = !isPaused;
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(runnable);
                seconds = 0;
                timerLabel.setText("00:00");
                startPauseButton.setText("Start");
                isPaused = true;
            }
        });
    }

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            seconds++;
            int minutes = seconds / 60;
            int secs = seconds % 60;
            String time = String.format("%02d:%02d", minutes, secs);
            timerLabel.setText(time);
            handler.postDelayed(this, 1000);
        }
    };
}
