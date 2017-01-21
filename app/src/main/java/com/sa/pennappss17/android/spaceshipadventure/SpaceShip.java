package com.sa.pennappss17.android.spaceshipadventure;

public class Spaceship {
    private final double initialPosition;
    private double yPosition;
    private float velocity;
    private int health;

    public Spaceship(double position) {
        initialPosition = position;
        health = 100;

    }
    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }
    public boolean state() {
        return health > 0;
    }




}