package com.sa.pennappss17.android.spaceshipadventure;

import android.graphics.Bitmap;

public class Spaceship implements GameObj{
    private final double initialPosition;
    private double yPosition;
    private double velocity;
    private int health;

    public Spaceship(double position) {
        initialPosition = position;
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
        return null;
    }

    @Override
    public int getX() {
        return (int) initialPosition;
    }

    @Override
    public int getY() {
        return (int) yPosition;
    }
}