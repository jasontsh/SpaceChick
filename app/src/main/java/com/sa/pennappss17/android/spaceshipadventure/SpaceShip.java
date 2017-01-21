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
        this.bitmap = bitmap;
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
        if (f <= Math.abs(.2)) return;
        yPosition += 1.5 * f;
        if (yPosition <= 0) {
            yPosition = 0;
        } else if (yPosition >= maxHeight-100) {
            yPosition = maxHeight - 100;
        }
    }
}