package com.sa.pennappss17.android.spaceshipadventure;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Set;


/**
 * Created by Jason on 1/20/2017.
 */

public class GameView extends SurfaceView implements Runnable {

    private Thread gameThread = null;
    private SurfaceHolder ourHolder;
    volatile boolean playing;
    private Canvas canvas;
    private Paint paint;
    private double fps;
    private long timeThisFrame;
    Set<GameObj> gameObjs;
    int score;
    Bitmap[] numbers;

    public GameView(Context context) {
        super(context);
        ourHolder = getHolder();
        paint = new Paint();
        playing = true;
        score = 0;
        numbers = new Bitmap[10];
        int x = 100, y = 60;
        Bitmap buffer = BitmapFactory.decodeResource(getResources(), R.drawable.no0);
        numbers[0] = Bitmap.createScaledBitmap(buffer, x, y, true);
        buffer.recycle();
        buffer = BitmapFactory.decodeResource(getResources(), R.drawable.no1);
        numbers[1] = Bitmap.createScaledBitmap(buffer, x, y, true);
        buffer.recycle();
        buffer = BitmapFactory.decodeResource(getResources(), R.drawable.no2);
        numbers[2] = Bitmap.createScaledBitmap(buffer, x, y, true);
        buffer.recycle();
        buffer = BitmapFactory.decodeResource(getResources(), R.drawable.no3);
        numbers[3] = Bitmap.createScaledBitmap(buffer, x, y, true);
        buffer.recycle();
        buffer = BitmapFactory.decodeResource(getResources(), R.drawable.no4);
        numbers[4] = Bitmap.createScaledBitmap(buffer, x, y, true);
        buffer.recycle();
        buffer = BitmapFactory.decodeResource(getResources(), R.drawable.no5);
        numbers[5] = Bitmap.createScaledBitmap(buffer, x, y, true);
        buffer.recycle();
        buffer = BitmapFactory.decodeResource(getResources(), R.drawable.no6);
        numbers[6] = Bitmap.createScaledBitmap(buffer, x, y, true);
        buffer.recycle();
        buffer = BitmapFactory.decodeResource(getResources(), R.drawable.no7);
        numbers[7] = Bitmap.createScaledBitmap(buffer, x, y, true);
        buffer.recycle();
        buffer = BitmapFactory.decodeResource(getResources(), R.drawable.no8);
        numbers[8] = Bitmap.createScaledBitmap(buffer, x, y, true);
        buffer.recycle();
        buffer = BitmapFactory.decodeResource(getResources(), R.drawable.no9);
        numbers[9] = Bitmap.createScaledBitmap(buffer, x, y, true);
        buffer.recycle();


    }

    @Override
    public void run() {
        while (playing) {
            long startFrameTime = System.currentTimeMillis();

            draw();

            timeThisFrame = System.currentTimeMillis();
            if (timeThisFrame > 0) {
                fps = 1000 / timeThisFrame;
            }
        }
    }

    public void draw() {
        if (ourHolder.getSurface().isValid()) {
            canvas = ourHolder.lockCanvas();

            canvas.drawColor(Color.BLACK);
            //draw background here
            for (GameObj go : gameObjs) {
                if (go instanceof Star) {
                    canvas.drawBitmap(go.getBitmap(), go.getX(), go.getY(), paint);
                }
            }
            for (GameObj go : gameObjs) {
                if (!(go instanceof Star)) {
                    Log.d("star", go instanceof Egg ? "egg" : go instanceof Fox ? "fox" : go instanceof
                     Obstacle ? "obstacle" : "wtf?");
                   canvas.drawBitmap(go.getBitmap(), go.getX(), go.getY(), paint);
                }
            }

            for (int i = 0; i < MainActivity.lifebar.getLives().length; i++) {
                canvas.drawBitmap(MainActivity.lifebar.getBitmap(i), MainActivity.lifebar.getLives()[i].getX(),
                        MainActivity.lifebar.getLives()[i].getY(), paint);
            }
            String s = score + "";
            for (int i = 0; i < s.length(); i++) {
                canvas.drawBitmap(numbers[s.charAt(i) - '0'], MainActivity.lifebar.getLives()[0].getX(),
                        10 + 50 * i, paint);
            }
            ourHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error:", "joining thread");
        }
    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }
}
