package com.sa.pennappss17.android.spaceshipadventure;

import android.graphics.Bitmap;

/**
 * Created by gustavo on 1/20/17.
 */

public class Obstacle implements GameObj {
    private double xPosition;
    private final double yPosition;
    private double velocity;

    public Obstacle(int xInitial, int boundary) {
        xPosition = xInitial;
        yPosition = Math.random() * boundary;
        velocity = Math.random() * 5 + 5;
    }

    public void movement() {
        xPosition = -1 * velocity;
    }

    @Override
    public Bitmap getBitmap() {
        return null;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }
}
