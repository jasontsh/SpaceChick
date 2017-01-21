package com.sa.pennappss17.android.spaceshipadventure;

import android.graphics.Bitmap;

/**
 * Created by gustavo on 1/20/17.
 */

public class Obstacle implements GameObj {
    private double xPosition;
    private final double yPosition;
    private double velocity;
    private Bitmap bitmap;

    public Obstacle(int xInitial, int boundary, Bitmap bitmap) {
        xPosition = xInitial;
        this.bitmap = Bitmap.createScaledBitmap(bitmap, 400, 300, true);
        yPosition = Math.random() * boundary;
        velocity = Math.random() * 5 + 5;
    }

    @Override
    public void movement() {
        xPosition -= 1 * velocity;
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

    @Override
    public int getX() {
        return (int) xPosition;
    }

    @Override
    public int getY() {
        return (int) yPosition;
    }

    @Override
    public Hitbox getHitbox() {
        return new Hitbox((int) (xPosition+10), (int) (yPosition+10), 20, 20);
    }
}
