package com.sa.pennappss17.android.spaceshipadventure;

import android.graphics.Bitmap;

/**
 * Created by gustavo on 1/21/17.
 */

public class Star implements GameObj{
    private int xStarPosition;
    private int yStarPosition;
    private int velocity;

    public Star(int width, int length) {
        xStarPosition = (int) (Math.random() * width);
        yStarPosition = (int) (Math.random() * length);
        velocity = 5;
    }

    @Override
    public Bitmap getBitmap() {
        return null;
    }

    @Override
    public int getX() {
        return xStarPosition;
    }

    @Override
    public int getY() {
        return yStarPosition;
    }

    @Override
    public Hitbox getHitbox() {
        return null;
    }

    @Override
    public void movement() {
        xStarPosition += velocity;
    }
}
