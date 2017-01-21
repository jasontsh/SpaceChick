package com.sa.pennappss17.android.spaceshipadventure;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.HashSet;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    GameView gameView;
    TextView tv;
    float[] components;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private Spaceship spaceship;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        components = new float[3];

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);


        gameView = new GameView(this);
        setContentView(gameView);

        spaceship = new Spaceship(200, BitmapFactory.decodeResource(getResources(),
                R.drawable.spaceship));
        gameView.gameObjs = new HashSet<>();
        gameView.gameObjs.add(spaceship);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)
                , SensorManager.SENSOR_DELAY_FASTEST);
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
        Log.d("please", "" + components[0] + " " + components[1] + " " + components[2]);
        spaceship.setAccelaration(components[1]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
