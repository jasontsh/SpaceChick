package com.sa.pennappss17.android.spaceshipadventure;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScoreActivity2 extends AppCompatActivity {

    public static Bitmap[] numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_score2);

        if (numbers == null) {
            numbers = new Bitmap[10];
        }

        if (numbers[0] == null) {
            int x = 100, y = 150;
            Bitmap buffer = BitmapFactory.decodeResource(getResources(), R.drawable.reg0);
            numbers[0] = Bitmap.createScaledBitmap(buffer, x, y, true);
            buffer.recycle();
            buffer = BitmapFactory.decodeResource(getResources(), R.drawable.reg1);
            numbers[1] = Bitmap.createScaledBitmap(buffer, x, y, true);
            buffer.recycle();
            buffer = BitmapFactory.decodeResource(getResources(), R.drawable.reg2);
            numbers[2] = Bitmap.createScaledBitmap(buffer, x, y, true);
            buffer.recycle();
            buffer = BitmapFactory.decodeResource(getResources(), R.drawable.reg3);
            numbers[3] = Bitmap.createScaledBitmap(buffer, x, y, true);
            buffer.recycle();
            buffer = BitmapFactory.decodeResource(getResources(), R.drawable.reg4);
            numbers[4] = Bitmap.createScaledBitmap(buffer, x, y, true);
            buffer.recycle();
            buffer = BitmapFactory.decodeResource(getResources(), R.drawable.reg5);
            numbers[5] = Bitmap.createScaledBitmap(buffer, x, y, true);
            buffer.recycle();
            buffer = BitmapFactory.decodeResource(getResources(), R.drawable.reg6);
            numbers[6] = Bitmap.createScaledBitmap(buffer, x, y, true);
            buffer.recycle();
            buffer = BitmapFactory.decodeResource(getResources(), R.drawable.reg7);
            numbers[7] = Bitmap.createScaledBitmap(buffer, x, y, true);
            buffer.recycle();
            buffer = BitmapFactory.decodeResource(getResources(), R.drawable.reg8);
            numbers[8] = Bitmap.createScaledBitmap(buffer, x, y, true);
            buffer.recycle();
            buffer = BitmapFactory.decodeResource(getResources(), R.drawable.reg9);
            numbers[9] = Bitmap.createScaledBitmap(buffer, x, y, true);
            buffer.recycle();
        }

        final Context c = this;
        int score = getIntent().getIntExtra("score", 0);
        int highscore = getIntent().getIntExtra("highscore", 0);

        LinearLayout sll = (LinearLayout) findViewById(R.id.score);

        String s = "" + score;
        for (int i = 0; i < s.length(); i++) {
            ImageView iv = new ImageView(this);
            iv.setImageBitmap(numbers[s.charAt(i) - '0']);
            sll.addView(iv);
        }
        s = "" + highscore;
        LinearLayout hsll = (LinearLayout) findViewById(R.id.highscore);
        for (int i = 0; i < s.length(); i++) {
            ImageView iv = new ImageView(this);
            iv.setImageBitmap(numbers[s.charAt(i) - '0']);
            hsll.addView(iv);
        }
        findViewById(R.id.restart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(c, MainActivity.class));
                finish();
            }
        });
    }
}
