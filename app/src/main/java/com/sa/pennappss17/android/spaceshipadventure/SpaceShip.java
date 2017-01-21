package com.sa.pennappss17.android.spaceshipadventure;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Spaceship implements GameObj {
    private final double initialPosition;
    private double yPosition;
    private double velocity;
    private int health;
    private Bitmap bitmap;

    public Spaceship(double position, Bitmap bitmap) {
        initialPosition = position;
        this.bitmap = bitmap;
        health = 100;

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
        yPosition += 1.1 * f;
    }
}