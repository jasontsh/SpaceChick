package com.sa.pennappss17.android.spaceshipadventure;

import android.graphics.Bitmap;

/**
 * Created by emilytan on 1/21/17.
 */

public class Life implements GameObj {
    private int xLifePosition;
    private int yLifePosition;
    public static boolean[] lives = new boolean[5];
    private static int currentLife;

    public Life(int coordinateX,int coordinateY) {

        xLifePosition = coordinateX;
        yLifePosition = coordinateY;

    }

    public static void addLife() {
        lives[currentLife] = true;
        if (currentLife < 5) currentLife++;

    }

    public static void removeLife() {
        currentLife--;
        lives[currentLife] = false;

    }

    public static boolean alive() {
        return currentLife > 0;
    }

    @Override
    public Bitmap getBitmap() {
        return null;
    }

    @Override
    public int getX() {
        return xLifePosition;
    }

    @Override
    public int getY() {
        return yLifePosition;
    }

    @Override
    public Hitbox getHitbox() {
        return null;
    }

    @Override
    public void movement() {
    }
