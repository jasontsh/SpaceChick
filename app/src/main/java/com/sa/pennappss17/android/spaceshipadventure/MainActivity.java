package com.sa.pennappss17.android.spaceshipadventure;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.Collections;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    static GameView gameView;
    float[] components;
    private SensorManager mSensorManager;
    static int starCount;
    private volatile Sensor mSensor;
    private Spaceship spaceship;
    static Set<Fox> foxSet;
    private int level;
    int height;
    int width;
    int lastShot;
    static LifeBar lifeBar;
    Bitmap splat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        components = new float[3];

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().hide();

        gameView = new GameView(this);
        gameView.playing = false;

        setContentView(gameView);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        height = displaymetrics.heightPixels - 300;
        width = displaymetrics.widthPixels;

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Bitmap spaceshipBm = BitmapFactory.decodeResource(getResources(),
                R.drawable.spaceship);
        final Bitmap eggBm = BitmapFactory.decodeResource(getResources(), R.drawable.egg);

        spaceship = new Spaceship(-100, height, spaceshipBm);
        gameView.gameObjs = Collections.newSetFromMap(new ConcurrentHashMap<GameObj, Boolean>());
        gameView.gameObjs.add(spaceship);

        gameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (level != lastShot) {
                    Egg egg = new Egg(spaceship.getX()+300, spaceship.getY()+200, width, eggBm);
                    gameView.gameObjs.add(egg);
                    lastShot = level;
                }
            }
        });

        foxSet = Collections.newSetFromMap(new ConcurrentHashMap<Fox, Boolean>());;

        lifeBar = new LifeBar(width, height, getResources());

        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.planet1);
        Obstacle.OBSTACLES[0] = Bitmap.createScaledBitmap(bm, 200, 200, true);
        bm.recycle();
        bm = BitmapFactory.decodeResource(getResources(), R.drawable.planet2);
        Obstacle.OBSTACLES[1] = Bitmap.createScaledBitmap(bm, 200, 200, true);
        bm.recycle();
        bm = BitmapFactory.decodeResource(getResources(), R.drawable.planet3);
        Obstacle.OBSTACLES[2] = Bitmap.createScaledBitmap(bm, 200, 300, true);
        bm.recycle();
        bm = BitmapFactory.decodeResource(getResources(), R.drawable.planet4);
        Obstacle.OBSTACLES[3] = Bitmap.createScaledBitmap(bm, 200, 300, true);
        bm.recycle();

        bm = BitmapFactory.decodeResource(getResources(), R.drawable.fox3);
        Fox.FOXES[0] = Bitmap.createScaledBitmap(bm, 300, 300, true);
        bm.recycle();
        bm = BitmapFactory.decodeResource(getResources(), R.drawable.fox1);
        Fox.FOXES[1] = Bitmap.createScaledBitmap(bm, 300, 300, true);
        bm.recycle();
        bm = BitmapFactory.decodeResource(getResources(), R.drawable.fox2);
        Fox.FOXES[2] = Bitmap.createScaledBitmap(bm, 350, 350, true);
        bm.recycle();

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.star1);
        Star.STARS[0][0] = Bitmap.createScaledBitmap(bitmap, 120, 120, true);
        bitmap.recycle();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.star2);
        Star.STARS[1][0] = Bitmap.createScaledBitmap(bitmap, 50, 50, true);
        bitmap.recycle();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.star3);
        Star.STARS[2][0] = Bitmap.createScaledBitmap(bitmap, 50, 50, true);
        bitmap.recycle();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.star4);
        Star.STARS[3][0] = Bitmap.createScaledBitmap(bitmap, 50, 50, true);
        bitmap.recycle();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.star5);
        Star.STARS[4][0] = Bitmap.createScaledBitmap(bitmap, 50, 50, true);
        bitmap.recycle();

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.star1_move);
        Star.STARS[0][1] = Bitmap.createScaledBitmap(bitmap, 120, 120, true);
        bitmap.recycle();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.star2_move);
        Star.STARS[1][1] = Bitmap.createScaledBitmap(bitmap, 50, 50, true);
        bitmap.recycle();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.star3_move);
        Star.STARS[2][1] = Bitmap.createScaledBitmap(bitmap, 50, 50, true);
        bitmap.recycle();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.star4_move);
        Star.STARS[3][1] = Bitmap.createScaledBitmap(bitmap, 50, 50, true);
        bitmap.recycle();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.star5_move);
        Star.STARS[4][1] = Bitmap.createScaledBitmap(bitmap, 50, 50, true);
        bitmap.recycle();

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.egg_splat);
        splat = Bitmap.createScaledBitmap(bitmap, 200, 200, true);
        bitmap.recycle();

        spaceshipBm.recycle();

        level = 0;
        starCount = 0;
        lastShot = 0;

        gameView.playing = true;
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

        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (gameView.playing) {
                    for (GameObj obj : gameView.gameObjs) {
                        obj.movement();
                    }
                }
            }
        }, 0, 30);
        final Context c = this;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (gameView.playing) {
                    for (GameObj obj : gameView.gameObjs) {
                        if (obj instanceof Obstacle) {
                            if (obj.getHitbox().collision(spaceship.getHitbox())) {
                                gameView.gameObjs.remove(obj);
                                lifeBar.removeLife();
                                if (!lifeBar.alive()) {
                                    //Game over!
                                    gameView.playing = false;
                                    Intent intent = new Intent(c, ScoreActivity.class);
                                    intent.putExtra("score", gameView.score);
                                    startActivity(intent);
                                    timer.cancel();
                                    finish();
                                }
                            }
                        }
                        if (obj instanceof Egg) {
                            for (Fox fox : foxSet) {
                                if (obj.getHitbox().collision(fox.getHitbox())) {
                                    gameView.gameObjs.remove(fox);
                                    gameView.score += fox.getWorth();
                                    if (fox.getWorth() == 10) {
                                        lifeBar.addLife();
                                    }
                                    gameView.gameObjs.remove(obj);
                                    foxSet.remove(fox);
                                    Splat sp = new Splat(fox.getX()+20, fox.getY()+20, splat);
                                    gameView.gameObjs.add(sp);
                                }
                            }
                        }
                    }
                }
            }
        }, 0, 30);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (gameView.playing) {
                    int increase = gameView.score > 50 ? 3 : 0;
                    increase = gameView.score > 100 ? 5 : increase;
                    for (int i = 0; i < Math.log(level) && gameView.gameObjs.size() < 11 + increase + starCount; i++) {
                        Obstacle obstacle = new Obstacle(width, height, width, level, getResources());
                        gameView.gameObjs.add(obstacle);
                    }
                    if (level == 60) {
                        Fox f = new Fox(width - 450, 0, 100, height, getResources(), 0);
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
            }
        }, 0, 1000);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (gameView.playing) {
                    if (starCount < 50) {
                        Star star = new Star(width, height, getResources());
                        gameView.gameObjs.add(star);
                        starCount++;
                    }
                }
            }
        }, 0, 80);
        gameView.resume();
    }

    private Fox createFox(boolean pre60) {
        double r = Math.random();
        if (pre60) {
            if (r < 0.7) {
                return new Fox(width-450, 0, 100, height, getResources(), 1);
            } else {
                return new Fox(width-450, 0, 100, height, getResources(), 2);
            }
        } else {
            if (r < 0.6) {
                return new Fox(width-450, 0, 100, height, getResources(), 1);
            } else if (r < .99){
                return new Fox(width-450, 0, 100, height, getResources(), 2);
            } else {
                return new Fox(width-450, 0, 100, height, getResources(), 0);
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
