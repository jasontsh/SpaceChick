package com.sa.pennappss17.android.spaceshipadventure;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_score);

        final Context c = this;

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        int highscore = sp.getInt("highscore", 0);
        final int score = getIntent().getIntExtra("score", 0);
        if (highscore < score) {
            highscore = score;
            SharedPreferences.Editor ed = sp.edit();
            ed.putInt("highscore", highscore);
            ed.apply();
        }

        final int hs = highscore;

        findViewById(R.id.activity_score).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c, ScoreActivity2.class);
                intent.putExtra("score", score);
                intent.putExtra("highscore", hs);
                startActivity(intent);
                finish();
            }
        });
    }
}
