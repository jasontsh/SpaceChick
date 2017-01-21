package com.sa.pennappss17.android.spaceshipadventure;

import android.graphics.Bitmap;

public class Spaceship implements GameObj {
    private final double initialPosition;
    private double yPosition;
    private double velocity;
    private int health;
    private Bitmap bitmap;
    private int maxHeight;

    public Spaceship(double position, int height, Bitmap bitmap) {
        initialPosition = position;
        this.bitmap = Bitmap.createScaledBitmap(bitmap, 400, 300, true);
        bitmap.recycle();
        health = 100;
        maxHeight = height;

    }
    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }
    public boolean state() {
        return health > 0;
    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }

    @Override
    public int getX() {
        return (int) initialPosition;
    }

    @Override
    public int getY() {
        return (int) yPosition;
    }

    public void setAccelaration(float f) {
        if (Math.abs(f) <= .5) return;
        yPosition += 1.5 * f;
        if (yPosition <= -20) {
            yPosition = 20;
        } else if (yPosition >= maxHeight-80) {
            yPosition = maxHeight - 80;
        }
    }
}