package com.sa.pennappss17.android.spaceshipadventure;

import android.content.Context;
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
    private Sensor mSensor;
    private Spaceship spaceship;
    private Set<Fox> foxSet;

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
        final int height = displaymetrics.heightPixels - 300;
        final int width = displaymetrics.widthPixels;

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Bitmap spaceshipBm = BitmapFactory.decodeResource(getResources(),
                R.drawable.spaceship);
        final Bitmap eggBm = BitmapFactory.decodeResource(getResources(), R.drawable.egg);

        spaceship = new Spaceship(0, height, spaceshipBm);
        gameView.gameObjs = Collections.newSetFromMap(new ConcurrentHashMap<GameObj, Boolean>());
        gameView.gameObjs.add(spaceship);

        for (int i = 0; i < 10; i++) {

            Obstacle obstacle = new Obstacle(width, height, width, spaceshipBm);
            gameView.gameObjs.add(obstacle);
        }
        Fox fox = new Fox(width - 300, 0, height, spaceshipBm);
        gameView.gameObjs.add(fox);
        foxSet = new HashSet<>();
        foxSet.add(fox);

        gameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Egg egg = new Egg(spaceship.getX()+200, spaceship.getY()+125, width, eggBm);
                gameView.gameObjs.add(egg);
            }
        });

        spaceshipBm.recycle();
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
                            Log.d("collision", "COLLISION");
                        }
                    }
                    if (obj instanceof Egg) {
                        for (Fox fox : foxSet) {
                            if (obj.getHitbox().collision(fox.getHitbox())) {
                                Log.d("Get", "points!");
                                gameView.gameObjs.remove(obj);
                            }
                        }
                    }
                }
            }
        }, 0, 30);

        gameView.resume();
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
