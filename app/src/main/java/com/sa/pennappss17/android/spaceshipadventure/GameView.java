package com.sa.pennappss17.android.spaceshipadventure;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
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


    public GameView(Context context) {
        super(context);
        ourHolder = getHolder();
        paint = new Paint();
    }

    @Override
    public void run() {

    }
}
