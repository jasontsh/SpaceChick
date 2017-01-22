package com.sa.pennappss17.android.spaceshipadventure;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    static GameView gameView;
    TextView tv;
    float[] components;
    private SensorManager mSensorManager;
    static int starCount;
    private Sensor mSensor;
    private Spaceship spaceship;
    static Set<Fox> foxSet;
    private int level;
    int height;
    int width;
    Bitmap obstaclesBm;
    static Lifebar lifebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        components = new float[3];

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();

        gameView = new GameView(this);

        setContentView(gameView);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        height = displaymetrics.heightPixels - 300;
        width = displaymetrics.widthPixels;

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Bitmap spaceshipBm = BitmapFactory.decodeResource(getResources(),
                R.drawable.spaceship);
        final Bitmap eggBm = BitmapFactory.decodeResource(getResources(), R.drawable.egg);
        obstaclesBm = BitmapFactory.decodeResource(getResources(), R.drawable.egg);

        spaceship = new Spaceship(0, height, spaceshipBm);
        gameView.gameObjs = Collections.newSetFromMap(new ConcurrentHashMap<GameObj, Boolean>());
        gameView.gameObjs.add(spaceship);

        gameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Egg egg = new Egg(spaceship.getX()+200, spaceship.getY()+125, width, eggBm);
                gameView.gameObjs.add(egg);
            }
        });

        foxSet = Collections.newSetFromMap(new ConcurrentHashMap<Fox, Boolean>());;

        lifebar = new Lifebar(width, height, getResources());

        spaceshipBm.recycle();

        level = 0;
        starCount = 0;
    }

    @Override
    protected void onResume() {
        super.onResume();

        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)
                , SensorManager.SENSOR_DELAY_FASTEST);

//        View decorView = getWindow().getDecorView();
//        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(uiOptions);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (GameObj obj : gameView.gameObjs) {
                    obj.movement();
                }
            }
        }, 0, 30);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (GameObj obj : gameView.gameObjs) {
                    if (obj instanceof Obstacle) {
                        if (obj.getHitbox().collision(spaceship.getHitbox())) {
                            gameView.gameObjs.remove(obj);
                            lifebar.removeLife();
                            if (!lifebar.alive()) {
                                //Game over!
                                gameView.playing = false;
                            }
                        }
                    }
                    if (obj instanceof Egg) {
                        for (Fox fox : foxSet) {
                            if (obj.getHitbox().collision(fox.getHitbox())) {
                                gameView.gameObjs.remove(fox);
                                gameView.score += fox.getWorth();
                                gameView.gameObjs.remove(obj);
                                foxSet.remove(fox);
                            }
                        }
                    }
                }
            }
        }, 0, 30);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < Math.log10(level) && gameView.gameObjs.size() < 10 + starCount; i++) {
                    Obstacle obstacle = new Obstacle(width, height, width, level, getResources());
                    gameView.gameObjs.add(obstacle);
                }
                if (level == 60) {
                    Fox f = new Fox(width - 300, 0, 100, height, getResources(), 0);
                    gameView.gameObjs.add(f);
                    foxSet.add(f);
                }
                double r = Math.random();
                if (r < 0.1) {
                    Fox f = createFox(level < 60);
                    gameView.gameObjs.add(f);
                    foxSet.add(f);
                } else {
                    if (level > 10 && r < 0.2) {
                        Fox f = createFox(level < 60);
                        gameView.gameObjs.add(f);
                        foxSet.add(f);
                    } else if (level > 60 && r < 0.25) {
                        Fox f = createFox(level < 60);
                        gameView.gameObjs.add(f);
                        foxSet.add(f);
                    }
                }
                level++;
            }
        }, 0, 1000);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (starCount < 50) {
                    Star star = new Star(width, height, getResources());
                    gameView.gameObjs.add(star);
                    starCount++;
                }
            }
        }, 0, 80);
        gameView.resume();
    }

    private Fox createFox(boolean pre60) {
        double r = Math.random();
        if (pre60) {
            if (r < 0.7) {
                return new Fox(width-300, 0, 100, height, getResources(), 1);
            } else {
                return new Fox(width-300, 0, 100, height, getResources(), 2);
            }
        } else {
            if (r < 0.6) {
                return new Fox(width-300, 0, 100, height, getResources(), 1);
            } else if (r < .99){
                return new Fox(width-300, 0, 100, height, getResources(), 2);
            } else {
                return new Fox(width-300, 0, 100, height, getResources(), 0);
            }
        }
    }


    @Override
    protected void onPause() {
        super.onPause();

        gameView.pause();
    }

    @Override
    protected void onStop(){
        mSensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(components == null) {
            components = new float[3];
        }
        components[0] = event.values[0];
        components[1] = event.values[1];
        components[2] = event.values[2];
        spaceship.setAccelaration(components[1]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        View decorView = getWindow().getDecorView();
//        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(uiOptions);
//    }
}
