package com.sa.pennappss17.android.spaceshipadventure;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class ScoreActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_score2);

        final Context c = this;
        int score = getIntent().getIntExtra("score", 0);
        int highscore = getIntent().getIntExtra("highscore", 0);

        TextView scoreTv = (TextView) findViewById(R.id.score);
        String s = "" + score;
        scoreTv.setText(s);
        TextView highscoreTv = (TextView) findViewById(R.id.highscore);
        s = "" + highscore;
        highscoreTv.setText(s);

        findViewById(R.id.restart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(c, MainActivity.class));
                finish();
            }
        });
    }
}
