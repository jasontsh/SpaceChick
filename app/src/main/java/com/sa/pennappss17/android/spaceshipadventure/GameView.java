package com.sa.pennappss17.android.spaceshipadventure;

import android.content.Context;
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

    public GameView(Context context) {
        super(context);
        ourHolder = getHolder();
        paint = new Paint();
        playing = true;
        score = 0;
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

            paint.setColor(Color.WHITE);
            String s = "Score: " + score;
            canvas.drawText(s, 200, 300, paint);
            Log.d("Score", s);
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
